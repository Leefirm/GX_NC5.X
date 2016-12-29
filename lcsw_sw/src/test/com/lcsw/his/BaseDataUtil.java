package com.lcsw.his;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BaseProcessor;
import nc.vo.pub.BusinessException;

public class BaseDataUtil {
	/**
	 * 用水性质
	 */
	static Map<String, String> ysxzMap = new HashMap<String, String>();
	/**
	 * 安装人员
	 */
	static Map<String, String> azryMap = new HashMap<String, String>();
	/**
	 * 经办人员
	 */
	static Map<String, String> jbryMap = new HashMap<String, String>();
	/**
	 * 减免类型
	 */
	static Map<String, String> jmlxMap = new HashMap<String, String>();
	/**
	 * 勘察人员
	 */
	static Map<String, String> kcryMap = new HashMap<String, String>();
	/**
	 * 口径表
	 */
	static Map<String, String> kjbMap = new HashMap<String, String>();
	/**
	 * 设计人员
	 */
	static Map<String, String> sjryMap = new HashMap<String, String>();
	/**
	 * 设计施工单位
	 */
	static Map<String, String> sjsgcorpMap = new HashMap<String, String>();
	/**
	 * 工程性质
	 */
	static Map<String, String> gcxzMap = new HashMap<String, String>();
	/**
	 * 项目档案
	 */
	static Map<String, String> jobMap = new HashMap<String, String>();
	
	@SuppressWarnings("unchecked")
	public static void init() throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource(InitHisDataTest.NCDS);
		
		// 用水性质
		ysxzMap = initDefdoc("docname", "pk_defdoc", "0001AA1000000000J85H");
		// 安装人员
		azryMap = initDefdoc("docname", "pk_defdoc", "0001AA1000000000J85F");
		// 经办人员
		jbryMap = initDefdoc("docname", "pk_defdoc", "0001AA1000000000J4AA");
		// 减免类型
		jmlxMap = initDefdoc("docname", "pk_defdoc", "0001AA1000000000J4AB");
		// 勘察人员
		kcryMap = initDefdoc("docname", "pk_defdoc", "0001AA1000000000J4AC");
		// 口径
		kjbMap = initDefdoc("docname", "pk_defdoc", "0001F51000000000OEVS");
		// 设计人员
		sjryMap = initDefdoc("docname", "pk_defdoc", "0001AA1000000000J4AE");
		// 设计施工单位
		sjsgcorpMap = initDefdoc("docname", "pk_defdoc", "0001AA1000000000J4AF");
		// 工程性质
		gcxzMap = initDefdoc("docname", "pk_defdoc", "0001AA1000000000J4A9");
		// 项目档案
		String jobSql = "select jobname,pk_jobbasfil  from bd_jobbasfil  where nvl(dr,0)=0 and pk_jobtype='0001AA1000000000QNVO'";
		jobMap = (Map<String, String>) NCLocator.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(jobSql, new BaseProcessor() {
					Map<String, String> jobMap = new HashMap<String, String>();

					@Override
					public Object processResultSet(ResultSet rs)
							throws SQLException {
						while (rs != null && rs.next()) {
							jobMap.put(rs.getString("jobname"),
									rs.getString("pk_jobbasfil"));
						}
						return jobMap;
					}
				});
	}
	@SuppressWarnings("unchecked")
	private static Map<String, String> initDefdoc(final String keyname,
			final String valuename, String pk_defdoclist)
			throws BusinessException {
		String ysxzSql = "select " + keyname + ", " + valuename
				+ " from bd_defdoc where nvl(dr,0)=0 and pk_defdoclist='"
				+ pk_defdoclist + "'";
		return (Map<String, String>) NCLocator.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(ysxzSql, new BaseProcessor() {
					Map<String, String> ysxzMap = new HashMap<String, String>();

					@Override
					public Object processResultSet(ResultSet rs)
							throws SQLException {
						while (rs != null && rs.next()) {
							ysxzMap.put(rs.getString(keyname),
									rs.getString(valuename));
						}
						return ysxzMap;
					}
				});
	}

}
