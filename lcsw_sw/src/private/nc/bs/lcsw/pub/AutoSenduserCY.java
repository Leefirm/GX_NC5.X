package nc.bs.lcsw.pub;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.trade.business.HYPubBO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.lcsw.pub.LcswCYVO;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.guid.UUID;
import nc.vo.pub.guid.UUIDGenerator;
/**
 * 
 * @author 李劼
 * 插页反馈功能，把营销系统的立户日期，抄表员，抄表日期，反馈 插页到 NC 系统
 */
public class AutoSenduserCY implements IBackgroundWorkPlugin {
	static final String YXXT = "lcsw";//营销系统
	static final String BZXT = "nc57";//报装系统
	
	private String message = "";
	private HYPubBO bo;

	public HYPubBO getHYPubBo() {
		if (bo == null)
			bo = new HYPubBO();
		return bo;
	}

	// 主任务
	public String executeTask(BgWorkingContext bgwc) throws BusinessException {
		String reMsg = null;
		try {
			Logger.debug("------------------查询视图---------------------");
			reMsg = checkView();
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			reMsg = e.getMessage();
		}
		return reMsg;
	}

	private String checkView() throws Exception {
		//String source = YXXT; // 营销系统数据源
		String source = BZXT; // NC测试系统数据源
		// 从插页视图获取需要更新的数据,比对中间表lcsw_cyfk
		Logger.debug("------------------从视图获取数据---------------------");
		DBManager db = new DBManager(source);
        //从NC系统中查出有入户编号的数据
		String sqlRhbh="select * from lcsw_bzxx where rhbh is not null and bch is null and bzzt='9' ";
		@SuppressWarnings("unchecked")
		List<LcswBzxxHVO> bzxxList = (List<LcswBzxxHVO>) db.executeQuery(sqlRhbh, new BeanListProcessor(LcswBzxxHVO.class));
		List<String> rhbhList=new ArrayList<String>();
		for(LcswBzxxHVO bzxx:bzxxList){
			rhbhList.add(bzxx.getRhbh());
		}
		source = YXXT; // 营销系统数据源
		db=new DBManager(source);
		List<LcswCYVO> list = new ArrayList<LcswCYVO>();
		if(rhbhList !=null){
		for(String rhbh:rhbhList){
		
			String sql=" select rhbh, bch, lhrq,cby,cbrq  from v_cyfk where cbrq is not null and rhbh = " +
					"'"+rhbh+"' and rhbh not in(select rhbh from lcsw_cyfk)";
			LcswCYVO cyvo =  (LcswCYVO) db.executeQuery(sql, new BeanProcessor(LcswCYVO.class));
			
			
			if(cyvo !=null){
			list.add(cyvo);
			}
		}
		}
//		// String sqlWhere = "where a.rhbh <> b.rhbh";
//		String sql = "select rhbh, bch, lhrq,cby,cbrq  from v_cyfk where cbrq is not null "
//				+ "minus (select a.rhbh, a.bch, a.lhrq,a.cby,a.cbrq  from v_cyfk a ,lcsw_cyfk b where a.rhbh =b.rhbh and a.cbrq is not null) ";
//		@SuppressWarnings("unchecked")
//		List<LcswCYVO> list = (List<LcswCYVO>) db.executeQuery(sql,  
//				new BeanListProcessor(LcswCYVO.class));
//		
		
		if (list != null && list.size() > 0) {		
			// 数据更新到报装系统
			source = BZXT;//报装系统数据源
			BaseDAO dao = new BaseDAO(source);
			//统计插入的数据容器
			ArrayList<LcswCYVO> cyfkMiddle = new ArrayList<LcswCYVO>();
			for (LcswCYVO cyfk : list) {
				try {
				
					String isRhbh = (String) dao.executeQuery("select rhbh from lcsw_bzxx where rhbh="+"'"+cyfk.getRhbh()+"'"
							, new ColumnProcessor(null));
					//设置UUID pk
					if(isRhbh !=null ){
					cyfk.setPk_cyfk(UUIDGenerator.getInstance().generateRandomBasedUUID().toString());
					cyfkMiddle.add(cyfk);
					dao.executeUpdate("update lcsw_bzxx set bch =" + "'"
							+ cyfk.getBch() + "'" + ','+"cby=" + "'"
							+ cyfk.getCby() + "'" + ','+"cbrq=" + "'"
							+ cyfk.getCbrq() + "'" + ','+"lhrq=" + "'"
							+ cyfk.getLhrq() + "'" + " where rhbh=" + "'"
							+ cyfk.getRhbh() + "'"); 
					
					}
				} catch (Exception e) {
					Logger.error("插页反馈报错", e);
					message = "插页反馈报错:" + e.getMessage();
				}

			}
			if(cyfkMiddle.size() !=0) {
			message = "插页反馈成功处理：" + cyfkMiddle.size() + "条数据";
			}
			else {
			message = "插页反馈本次没有数据需要处理";
			}
			//插入反馈成功，把数据更新到中间表
			Logger.debug("------------把需要更新的用户数据插入中间表---------------");
			source = YXXT; // 营销系统数据源
//			for (LcswCYVO lcswCYVO : list) {
//				lcswCYVO.setPk_cyfk(UUIDGenerator.getInstance().generateRandomBasedUUID().toString());
//			}
			new BaseDAO(source).insertVOArrayWithPK(cyfkMiddle.toArray(new LcswCYVO[cyfkMiddle.size()]));
				
		}else{
		message = "插页反馈本次没有数据需要处理";
	
		}
		return message;
	}

	

}
