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
import nc.vo.lcsw.sw04.KcfksjHVOC;
import nc.vo.lcsw.sw11.SjfksjBVO;
import nc.vo.lcsw.sw11.SjfksjHVO;
import nc.vo.lcsw.sw11.SjfksjHVOC;
import nc.vo.pub.BusinessException;

public class InitSjfksjDataTest {
	
	static final String NCDS = "nc57";
	static final String BZDS = "sw_bz";

	/**
	 * @param args
	 * @throws BusinessException
	 * 
	 */
	public static void main(String[] args) throws BusinessException {
		// ��ʼ������
		Properties props = EnvInit.initClientEnv();
		String baseURL = "http://localhost:8085";
		RuntimeEnv.getInstance().setProperty("SERVICEDISPATCH_URL",
				baseURL + "/ServiceDispatcherServlet");
		props.setProperty("SERVICEDISPATCH_URL", baseURL
				+ "/ServiceDispatcherServlet");

		long conntime = System.currentTimeMillis();

		// RuntimeEnv.getInstance().setRunningInServer(true);

		// ��Ŀ����
		// initJobData();
		//
		initFinishedData();

		long resetTime = System.currentTimeMillis();
		System.out
				.println("��ȡ������ɹ�����ʱ��" + (resetTime - conntime) / 1000 + "�룻");
	}

	

	@SuppressWarnings({ "unchecked", "unused" })
	public static void initFinishedData() throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
		//�ж� �����Ա����
		String sqlwhere1 = "where SheJiRenYuan is not null and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' group by  GCMC having COUNT(gcmc)>0 ";
		List<SjfksjHVO> list1 = (List<SjfksjHVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select GCMC pk_jobbasfil,min(jieturiqi) atrq, min(qianshouriqi) qsrq, min(YiJiaoSheJiRiQi)  yjsjrq,min(YuSuanGongChengKuan) ysgck, min(BuJiaoKanSheFei) bjksf, min(SheJiJingBanRenYuan) jbry, min(SheJiRenYuan) sjry, min(SheJiTuHao) sjth, min(FaDanRiQi) fdrq,min(QianShouRiQi) qsrq,min(BuJiaoKanSheFei) bjkcf,min(BuJiaoKanSheFei) bjksf, min(BeiZhu) remark from bz "
						+ sqlwhere1,
						new BeanListProcessor(SjfksjHVO.class));
	
		
		//��ʼ����������map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (list1 != null) {
			for (SjfksjHVO Kcpg1 : list1) {
				//��������
				Kcpg1.setPk_billtype("SW11");
				//�����Ƶ��� 
				Kcpg1.setVoperatorid("0001A1100000000001M2");
				Kcpg1.setPk_corp("1003");
			
				Kcpg1.setDr(0);
			    //����״̬
				Kcpg1.setVbillstatus(1);
				
				//��Ŀ����
				Kcpg1.setPk_jobbasfil(BaseDataUtil.jobMap.get(Kcpg1
						.getPk_jobbasfil()));
			    
			}
		}
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(list1);
		
		//�м�����
		InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
		String sqlwhereMill = "where SheJiRenYuan is not null and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' ";
		List<SjfksjHVOC> listMill = (List<SjfksjHVOC>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select  qianshouriqi qsrq, YiJiaoSheJiRiQi  yjsjrq,YuSuanGongChengKuan ysgck, BuJiaoKanSheFei bjksf, baozhuangbianhao RESERVE1,GCMC pk_jobbasfil, SheJiJingBanRenYuan jbry, SheJiRenYuan sjry, SheJiTuHao sjth, FaDanRiQi fdrq,QianShouRiQi qsrq,BuJiaoKanSheFei bjksf,BeiZhu remark from bz  " //�����ɹ���Ա=������Ա�������ɹ�����=��������
						+ sqlwhereMill
						,new BeanListProcessor(SjfksjHVOC.class));    
		System.out.println(listMill .size());
		
		//��ʼ����������map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (listMill != null) {
			for (SjfksjHVOC Kcpg1 : listMill) {
				//��������
				Kcpg1.setPk_billtype("SW03");
		
				Kcpg1.setPk_corp("1003");
			
				Kcpg1.setDr(0);
			    //����״̬
				Kcpg1.setVbillstatus(1);
				
				//��Ŀ����
				Kcpg1.setPk_jobbasfil(BaseDataUtil.jobMap.get(Kcpg1
						.getPk_jobbasfil()));
			    
			}
		}
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(listMill);
		
		//�ӱ���뱨װ��Ϣpk����װ����pk������pk
		String sqlwhere2 = "where b.bzbh = a.RESERVE1 ";
		List<SjfksjBVO> list2 = (List<SjfksjBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select b.bzbh,b.lxr,b.lxdd, b.hm, a.qsrq,b.pk_bzxx,a.pk_jobbasfil,  b.hm, a.jbry, a.sjry, a.sjth, a.fdrq,a.qsrq,a.bjkcf,a.remark " 
                        + "from  lcsw_sjfksj_hCopy  a ,lcsw_bzxx  b " 
						+ sqlwhere2,
						new BeanListProcessor(SjfksjBVO.class));
		System.out.println(list2.size());
		if (list2 != null) {
			for (SjfksjBVO Kcfksj2 : list2) {
				
			//	Kcfksj2.setPk_corp("0001");
				
				Kcfksj2.setDr(0);
			//���ù�������id
			  String pk_sjfksjh = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select  min (distinct (a.pk_sjfksjhid) ) from lcsw_sjfksj_h a,lcsw_sjfksj_hCopy b where a.pk_jobbasfil ="
				+"'"+Kcfksj2.getPk_jobbasfil()+"' "
				 , new ColumnProcessor(null));
			   Kcfksj2.setPk_sjfksjhid(pk_sjfksjh);
			   //���ù�����װ����pk_bzsq
			   if( Kcfksj2.getPk_sjfksjhid() != null ){
			   String pk_bzsqPk = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select  b.pk_bzsq from lcsw_bzsq b where b.pk_jobbasfil ="
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
			jvo.setPk_jobtype("0001AA1000000000QNVO");// ��Ŀ����-�ڽ�����
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
