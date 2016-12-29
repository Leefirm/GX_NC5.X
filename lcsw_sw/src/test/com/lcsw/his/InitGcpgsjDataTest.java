package com.lcsw.his;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.common.RuntimeEnv;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.itf.uap.bd.job.IJobbasedoc;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.md.util.EnvInit;
import nc.vo.bd.b38.JobbasfilVO;
import nc.vo.jcom.lang.StringUtil;

import nc.vo.lcsw.sw03.KcpgHVOC;
import nc.vo.lcsw.sw05.GcpgsjBVO;
import nc.vo.lcsw.sw05.GcpgsjHVO;
import nc.vo.lcsw.sw05.GcpgsjHVOC;

import nc.vo.pub.BusinessException;

public class InitGcpgsjDataTest {
	
	static final String NCDS = "nc57";
	static final String BZDS = "sw_bz";

	/**
	 * @param args
	 * @throws BusinessException
	 * 
	 */
	public static void main(String[] args) throws BusinessException {
		// 初始化环境
		Properties props = EnvInit.initClientEnv();
		String baseURL = "http://localhost:8085";
		RuntimeEnv.getInstance().setProperty("SERVICEDISPATCH_URL",
				baseURL + "/ServiceDispatcherServlet");
		props.setProperty("SERVICEDISPATCH_URL", baseURL
				+ "/ServiceDispatcherServlet");

		long conntime = System.currentTimeMillis();

		// RuntimeEnv.getInstance().setRunningInServer(true);

		// 项目档案
		// initJobData();
		//
		initFinishedData();

		long resetTime = System.currentTimeMillis();
		System.out
				.println("获取结果集成功，耗时：" + (resetTime - conntime) / 1000 + "秒；");
	}

	

	@SuppressWarnings({ "unchecked", "unused" })
	public static void initFinishedData() throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
		//判断 室外施工单位 不空
		String sqlwhere1 = "where   Shiwaishigongdanwei is not null and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' group by  GCMC having COUNT(gcmc)>0 ";
		List<GcpgsjHVO> list1 = (List<GcpgsjHVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(  // 
						"select GCMC pk_jobbasfil, min(SheJiDanWei) sjdw, min(GuanChang) gc, min(ShiWaiShiGongDanWei) swsgdw, min(ShuiBiaoGeShu) sbgs,min(ShiNeiShiGongDanWei) snsgdw,min(ShiGongTianShu) sgts,min(SheJiTuHao) sjth,min(BeiZhu) remark from bz "
						+ sqlwhere1,
						new BeanListProcessor(GcpgsjHVO.class));
	
		
		//初始化基础数据map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (list1 != null) {
			for (GcpgsjHVO Kcpg1 : list1) {
				//单据类型
				Kcpg1.setPk_billtype("SW05");
		
				Kcpg1.setPk_corp("1003");
				//设置制单人 
				Kcpg1.setVoperatorid("0001A1100000000001M2");
				Kcpg1.setDr(0);
			    //单据状态
				Kcpg1.setVbillstatus(1);
				
				//项目档案
				Kcpg1.setPk_jobbasfil(BaseDataUtil.jobMap.get(Kcpg1
						.getPk_jobbasfil()));
			    
			}
		}
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(list1);
		//中间表插入
				InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
				String sqlwhereMill = "where   Shiwaishigongdanwei is not null and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' ";
				List<GcpgsjHVOC> listMill = (List<GcpgsjHVOC>) NCLocator
						.getInstance()
						.lookup(IUAPQueryBS.class)
						.executeQuery(
								"select baozhuangbianhao RESERVE1,GCMC pk_jobbasfil, SheJiDanWei sjdw, GuanChang gc, ShiWaiShiGongDanWei swsgdw, ShuiBiaoGeShu sbgs,ShiNeiShiGongDanWei snsgdw,ShiGongTianShu sgts,SheJiTuHao sjth,BeiZhu remark from bz " //勘察派工人员=勘察人员，勘察派工日期=勘察日期
								+ sqlwhereMill
								,new BeanListProcessor(GcpgsjHVOC.class));    
				System.out.println(listMill .size());
				
				//初始化基础数据map
				BaseDataUtil.init();

				InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
				if (listMill != null) {
					for (GcpgsjHVOC Kcpg1 : listMill) {
						//单据类型
						Kcpg1.setPk_billtype("SW03");
				
						Kcpg1.setPk_corp("1003");
					
						Kcpg1.setDr(0);
					    //单据状态
						Kcpg1.setVbillstatus(1);
						
						//项目档案
						Kcpg1.setPk_jobbasfil(BaseDataUtil.jobMap.get(Kcpg1
								.getPk_jobbasfil()));
					    
					}
				}
				NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(listMill);
		//子表插入报装信息pk，报装申请pk，主表pk
		String sqlwhere2 = "where  b.bzbh = a.RESERVE1 ";
		List<GcpgsjBVO> list2 = (List<GcpgsjBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select  b.bzbh,b.lxr,b.lxdd, b.hm, b.pk_bzxx, a.pk_jobbasfil, a.sjdw, a.gc, a.swsgdw, a.sbgs,a.snsgdw,a.sgts,a.sjth,a.remark " 
                        + "from  lcsw_Gcpgsj_hCopy  a ,lcsw_bzxx  b " 
						+ sqlwhere2,
						new BeanListProcessor(GcpgsjBVO.class));
		System.out.println(list2.size());
		if (list2 != null) {
			for (GcpgsjBVO Kcfksj2 : list2) {
				
			//	Kcfksj2.setPk_corp("0001");
				
				Kcfksj2.setDr(0);
				//设置关联主表id
				  String pk_gcpgsjh = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select   a.pk_gcpgsjhid from lcsw_gcpgsj_h a  where a.pk_jobbasfil = "
					+"'"+Kcfksj2.getPk_jobbasfil()+"' "
					 , new ColumnProcessor(null));
				   Kcfksj2.setPk_gcpgsjhid(pk_gcpgsjh);
				//设置报装申请pk
				   if( Kcfksj2.getPk_gcpgsjhid() != null ){
				   String pk_bzsqPk = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select  b.pk_bzsq from lcsw_bzsq b  where  b.pk_jobbasfil ="
							+"'"+Kcfksj2.getPk_jobbasfil()+"' "
							 , new ColumnProcessor(null));
						   Kcfksj2.setPk_bzsq(pk_bzsqPk);
					
				   }
			}
		}
		
		
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(list2);
	}

	@SuppressWarnings("unchecked")
	public static void initJobData() throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource("sw_bz");

		List<String> list = (List<String>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery("select distinct gcmc from bz",
						new ColumnListProcessor());
		InvocationInfoProxy.getInstance().setUserDataSource("nc57");

		int code = 31065;

		for (int i = 0; i < list.size(); i++) {

			String name = list.get(i);
			if (StringUtil.isEmptyWithTrim(name)) {
				continue;
			}

			JobbasfilVO jvo = new JobbasfilVO();
			jvo.setPk_corp("1003");
			jvo.setJobname(name);
			jvo.setJobcode((code + i) + "");
			jvo.setPk_jobtype("0001AA1000000000QNVO");// 项目类型-在建工程
			NCLocator.getInstance().lookup(IJobbasedoc.class)
					.insertJobbasfilVO(jvo);
		}
		System.out.println(list.size());
	}

	
	/**
	 * 
	 * @throws BusinessException
	 */
	public static void initCheckDataCount() throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource("sw_bz");
		for (int i = 1994; i < 2015; i++) {
			Integer count = (Integer) NCLocator
					.getInstance()
					.lookup(IUAPQueryBS.class)
					.executeQuery(
							"select count(1) from bzcnk where baozhuangbianhao like '"
									+ i + "%'", new ColumnProcessor());
			System.out.println(i + ":" + (count));
		}
	}

}
