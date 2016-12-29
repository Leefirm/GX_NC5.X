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
import nc.vo.lcsw.sw04.KcfksjBVO;
import nc.vo.lcsw.sw04.KcfksjHVO;
import nc.vo.lcsw.sw04.KcfksjHVOC;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

public class InitKcfkDataTest {
	
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
		//�ж�����  ���췴�����ڲ���
		String sqlwhere1 = "where  kanchafankuiriqi is not  null and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' group by  GCMC having COUNT(gcmc)>0 ";
		List<KcfksjHVO> list1 = (List<KcfksjHVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select   GCMC pk_jobbasfil, min (BaoZhuangJingBanRenYuan) jbry, min(KanChaRiQi) kcrq, min(KanChaRenYuan) kcry, min(KanChaFanKuiRiQi) kcfkrq, min(kanchashejirenyuan) kcsjry, min(YiJiaoSheJiRiQi) yjsjrq, min(YiJiaoHuaHongXianRiQi) yjhhxrq, min(JieHongXianTuRiQi) jhxtrq, min(YiJiaoSheJiPingShenRiQi) yjsjpsrq, min(SheJiPingShenWanChengRiQi) sjpswcrq, min(BeiZhu) remark from bz " 
						+ sqlwhere1,
						new BeanListProcessor(KcfksjHVO.class));
	
		
		//��ʼ����������map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (list1 != null) {
			for (KcfksjHVO Kcfksj1 : list1) {
				//��������
				Kcfksj1.setPk_billtype("SW04");
		        
				Kcfksj1.setPk_corp("1003");
				//��ˮ��������ˮ����Ĭ��=1
				Kcfksj1.setDr(0);
			    //����״̬
				Kcfksj1.setVbillstatus(1);
				//���ÿ�����Ʊ�־
		        if(Kcfksj1.getYjsjrq() != null ){
		        	UFBoolean sfyjsj = new UFBoolean("Y");
		        	Kcfksj1.setSfyjsj(sfyjsj);
		        }
				//��Ŀ����
				Kcfksj1.setPk_jobbasfil(BaseDataUtil.jobMap.get(Kcfksj1
						.getPk_jobbasfil()));
				//�����Ƶ��� 
				Kcfksj1.setVoperatorid("0001A1100000000001M2");
			}
		}
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(list1);
		
		//�м�����
				InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
				String sqlwhereMill = "where KanChaFanKuiRiQi  is not null  and  KanChaRenYuan <> ' ' and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' ";
				List<KcfksjHVOC> listMill = (List<KcfksjHVOC>) NCLocator
						.getInstance()
						.lookup(IUAPQueryBS.class)
						.executeQuery(
								"select  baozhuangbianhao reserve1, GCMC pk_jobbasfil, BaoZhuangJingBanRenYuan jbry, KanChaRiQi kcrq, KanChaRenYuan kcry, KanChaFanKuiRiQi kcfkrq, kanchashejirenyuan kcsjry, YiJiaoSheJiRiQi yjsjrq,YiJiaoHuaHongXianRiQi yjhhxrq, JieHongXianTuRiQi jhxtrq, YiJiaoSheJiPingShenRiQi yjsjpsrq, SheJiPingShenWanChengRiQi sjpswcrq, BeiZhu remark from bz " //�����ɹ���Ա=������Ա�������ɹ�����=��������
								+ sqlwhereMill
								,new BeanListProcessor(KcfksjHVOC.class));    
				System.out.println(listMill .size());
				
				//��ʼ����������map
				BaseDataUtil.init();

				InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
				if (listMill != null) {
					for (KcfksjHVOC Kcpg1 : listMill) {
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
		List<KcfksjBVO> list2 = (List<KcfksjBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select b.bzbh,b.lxr,b.lxdd, b.pk_bzxx, b.hm, a.pk_jobbasfil, a.jbry,  a.kcrq,  a.kcry, a.kcfkrq,  a.kcsjry, a.yjsjrq,  a.yjhhxrq, a.jhxtrq,  a.yjsjpsrq,  a.sjpswcrq,  a.remark " 
                        + "from  lcsw_kcfksj_hCopy  a ,lcsw_bzxx  b " 
						+ sqlwhere2,
						new BeanListProcessor(KcfksjBVO.class));
		System.out.println(list2.size());
		if (list2 != null) {
			for (KcfksjBVO Kcfksj2 : list2) {
				
				Kcfksj2.setPk_corp("1003");
				
				Kcfksj2.setDr(0);
				//���ù�������id
				  String pk_kcfksjh = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select  min (distinct (a.pk_kcfksjhid) ) from lcsw_kcfksj_h a,lcsw_kcfksj_hCopy b where a.pk_jobbasfil ="
					+"'"+Kcfksj2.getPk_jobbasfil()+"' "
					 , new ColumnProcessor(null));
				   Kcfksj2.setPk_kcfksjhid(pk_kcfksjh);
               //���ù�����װ����pk_id
				   if(Kcfksj2.getPk_kcfksjhid() !=null ){
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
