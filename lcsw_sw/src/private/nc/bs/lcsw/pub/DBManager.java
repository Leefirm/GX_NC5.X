package nc.bs.lcsw.pub;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import nc.bs.dao.DAOException;
import nc.bs.logging.Logger;
import nc.jdbc.framework.JdbcPersistenceManager;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.exception.DbException;
//import nc.jdbc.framework.generator.SequenceGenerator;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.jdbc.framework.util.DBConsts;
import nc.jdbc.framework.util.DBUtil;
import nc.jdbc.framework.util.SQLHelper;
import nc.vo.pub.SuperVO;

/**
 * 建立数据库连接
 * 
 * @author crf
 * 
 */
@SuppressWarnings("unchecked")
public class DBManager  extends JdbcPersistenceManager{
	// 数据库会话
	JdbcSession session;

	// 数据源名称
	String dataSource = null;
	private static Map cache = new ConcurrentHashMap();;

	protected DBManager(String dataSource) throws DbException{
		this.dataSource = dataSource;
		init();
	}
	
	protected DBManager() throws DbException{
		init();
	}
	
	protected DBManager(JdbcSession session)throws DbException {
		session.setMaxRows(maxRows);
		this.session = session;
	}
	/**
	 * 释放资源
	 */
	public void release() {
		if (session != null) {
			session.closeAll();
			session = null;
		}
	}
	
	/**
	 * 得到JdbcSession
	 * 
	 */
	public JdbcSession getJdbcSession() {
		return session;
	}
	/**
	 * 初始化数据库会话连接
	 * 
	 * @throws DbException
	 */
	private void init() throws DbException {
		if (dataSource == null)
			session = new JdbcSession();
		else{
			session = new JdbcSession(dataSource);
		}
		//剔除ts时间戳字段
		session.setAddTimeStamp(false);
		session.setMaxRows(maxRows);
	}
	
	private PersistenceManager createPersistenceManager(String ds) throws DbException {
		PersistenceManager manager = PersistenceManager.getInstance(ds);
		manager.setMaxRows(maxRows);
		return manager;
	}
	
	/**
	 * 将验证合格用户数据导入中间表
	 * @throws DAOException
	 * @throws SQLException 
	 */
	public void insertVOs(SuperVO[] vos,boolean withPK) throws DAOException, SQLException{
		if(vos == null) {
			throw new IllegalArgumentException("值对象参数为空");
		}
		PersistenceManager manager = null;
		try{
			manager = createPersistenceManager(dataSource);
			//表名
			String tableName = vos[0].getTableName();
			Map<String, Integer> types = getColmnTypes(tableName);
			//有效列
			String names[] = getValidNames(vos[0], types);
			String sql = SQLHelper.getInsertSQL(tableName, names);
			
			//panqh临时处理
			sql = sql.replace("ZZ5_DA_BZDRZJB", "NNMIS.ZZ5_DA_BZDRZJB");
//			sql = sql.replace("LCSW_CYFK", "LCSW.LCSW_CYFK");
			
			//panqh temp
//			String sqlprint = "(";
////			System.out.print("(");/
//			for (int i = 0; i < names.length; i++) {
//				if (names[i].equalsIgnoreCase("ts"))
//					continue;
//				int type = types.get(names[i].toUpperCase());
//				Object value = vos[0].getAttributeValue(names[i]);
//				if (value == null) {
//					sqlprint += ("null,");
//					continue;
//				}
//				else {
//					sqlprint += ("'"+value+"',");
//				}
//			}
//			sqlprint += (")");
//			
//			Logger.error("inser middle sql:" + sql);
//			Logger.error("inser middle param:" + sqlprint);
//			
//			//panqh end
			
			
//			preparePK(vos, withPK);
			if (vos.length == 1) {
				SQLParameter parameter = getSQLParam(vos[0], names, types);
				session.executeUpdate(sql, parameter);
			} else {
				for (int i = 0; i < vos.length; i++) {
					if (vos[i] == null)
						continue;
 					SQLParameter parameter = getSQLParam(vos[i], names, types);
					session.addBatch(sql, parameter);
				}
				//批量insert
				session.executeBatch();
			}
		}catch (DbException e) {
			Logger.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		} finally {
			if (manager != null)
				manager.release();
		}
	}
	
	public static void clearColumnTypes(String table) {
		cache.remove(table);
	}
	/**
	 * 得到列的类型
	 * 
	 * @param table
	 * @return
	 */
	private Map<String, Integer> getColmnTypes(String table) {
		Map<String, Integer> result = (Map<String, Integer>) cache.get(table);
		if (result == null) {
			Map<String, Integer> typeMap = new HashMap<String, Integer>();
			ResultSet rsColumns = null;
			try {
				if (getDBType() == DBConsts.SQLSERVER)
					rsColumns = getMetaData().getColumns(null, null,
							table.toUpperCase(), "%");
				else if(getDBType() == DBConsts.DB2) {
					rsColumns = getMetaData().getColumns(null, null,
							table.toLowerCase(), "%");
				} else {
					rsColumns = getMetaData().getColumns(null, getSchema(),
							table.toUpperCase(), "%");
				}
				while (rsColumns.next()) {
					String columnName = rsColumns.getString("COLUMN_NAME")
							.toUpperCase();
					int columnType = rsColumns.getShort("DATA_TYPE");
					typeMap.put(columnName, columnType);
				}
				cache.put(table, typeMap);
				result = typeMap;
			} catch (SQLException e) {
				Logger.error("get table metadata error", e);
			} finally {
				DBUtil.closeRs(rsColumns);
			}
		}
		return result;
	}
	
//	private String[] preparePK(final SuperVO vos[], boolean withPK) {
//		String corpPk = (String) vos[0].getAttributeValue("pk_corp");
//		if (corpPk == null || (corpPk = corpPk.trim()).equals("")
//				|| corpPk.equals("null"))
//			corpPk = SQLHelper.getCorpPk();
//		if (withPK) {
//			String[] pks = new String[vos.length];
//			int[] idx = new int[vos.length];
//			int length = 0;
//			for (int i = 0; i < vos.length; i++) {
//				if (vos[i] == null) {
//					continue;
//				} else {
//					String thePK = vos[i].getPrimaryKey();
//					if (thePK == null || thePK.trim().length() == 0) {
//						idx[length++] = i;
//					} else {
//						pks[i] = thePK;
//					}
//				}
//			}
//
//			if (length > 0) {
//				String[] npks = new SequenceGenerator(dataSource).generate(
//						corpPk, length);
//				for (int i = 0; i < length; i++) {
//					vos[idx[i]].setPrimaryKey(npks[i]);
//					pks[idx[i]] = npks[i];
//				}
//			}
//			return pks;
//
//		} else {
//			String[] pks = new SequenceGenerator(dataSource).generate(corpPk,
//					vos.length);
//			for (int i = 0; i < vos.length; i++) {
//				if (vos[i] != null) {
//					vos[i].setPrimaryKey(pks[i]);
//				} else {
//					pks[i] = null;
//				}
//			}
//			return pks;
//		}
//	}
	
	/**
	 * 得到有效的列名称
	 * 
	 * @param vo
	 * @param types
	 * @return
	 */
	private String[] getValidNames(final SuperVO vo, Map types) {
		String names[] = vo.getAttributeNames();
		List nameList = new ArrayList();
		for (int i = 0; i < names.length; i++) {
			if (types.get(names[i].toUpperCase()) != null
					&& !names[i].equalsIgnoreCase("ts")) {
				nameList.add(names[i]);
			}
		}
		return (String[]) nameList.toArray(new String[] {});
	}
	
	/**
	 * 得到参数类型对象
	 * 
	 * @param vo
	 * @param names
	 * @param types
	 * @return
	 */
	private SQLParameter getSQLParam(SuperVO vo, String names[],
			Map<String, Integer> types) {
		SQLParameter params = new SQLParameter();
		for (int i = 0; i < names.length; i++) {
			if (names[i].equalsIgnoreCase("ts"))
				continue;
			int type = types.get(names[i].toUpperCase());
			Object value = vo.getAttributeValue(names[i]);
			if (value == null) {
				params.addNullParam(type);
				continue;
			}
			if (type == Types.BLOB || type == Types.LONGVARBINARY
					|| type == Types.VARBINARY || type == Types.BINARY) {
				params.addBlobParam(value);
				continue;
			}
			if (type == Types.CLOB || type == Types.LONGVARCHAR) {
				params.addClobParam(String.valueOf(value));
				continue;
			}
			params.addParam(value);

		}
		return params;
	}
	
	public Object executeQuery(String sql, ResultSetProcessor processor)
			throws DAOException {
		PersistenceManager manager = null;
		Object value = null;
		try {
			manager = createPersistenceManager(this.dataSource);
			JdbcSession session = manager.getJdbcSession();
			value = session.executeQuery(sql, processor);
		} catch (DbException e) {
		} finally {
			if (manager != null)
				manager.release();
		}
		return value;
	}
}
