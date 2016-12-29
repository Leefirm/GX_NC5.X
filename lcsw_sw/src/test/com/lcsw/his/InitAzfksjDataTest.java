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

import nc.vo.lcsw.sw06.FbyjazHVOC;
import nc.vo.lcsw.sw07.AzxxfkBVO;
import nc.vo.lcsw.sw07.AzxxfkHVO;
import nc.vo.lcsw.sw07.AzxxfkHVOC;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

public class InitAzfksjDataTest {
	
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
		//�ж� ��װ�������ڲ���
		String sqlwhere1 = "where AnZhuangFanKuiRiQi is not null and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' group by  GCMC having COUNT(gcmc)>0";
		List<AzxxfkHVO> list1 = (List<AzxxfkHVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(  //����ʩ����λ=ʩ������,ʩ��Ա = ʩ��������,��װ����=�������ڣ�ԭ�ܾ�=��װ������װ��ַ=·�Σ�·���յ㣬·�����
						"select min(yijiaoyanshouriqi) yjysrq,min(chushixingdu) ckxd, min(AnZhuangDiZhi) ld,min(AnZhuangDiZhi) ldqd,min(AnZhuangDiZhi) ldzd, min(shiwaikoujing) azbj,min(ShiWaiShiGongDanWei) sgbm, min(ShiGongYuan) sgfzr,GCMC pk_jobbasfil, min(YiJiaoAnZhuangRiQi) yjazrq,min( AnZhuangRiQi) azrq,min(AnZhuangRiQi) kgrq,min(ZhuangBiaoRiQi) zbrq, min(AnZhuangFanKuiRiQi) azfkrq,min(JunGongRiQi) jgrq, min(hetonghao) hth,min(ziliaofankuiriqi) zlfkrq,min(BiaoBie) biaobie,min(BianMa) bianma,min(BiaoMa) biaoma,min(BiaoWei) biaowei, min(GuanChang) sggchang,min(ChanDi) chandi, min(shigongyuan) sgfzr,min(SheJiTuHao) sjth,min(BeiZhu) remark from bz "
						+ sqlwhere1,
						new BeanListProcessor(AzxxfkHVO.class));
	
		
		//��ʼ����������map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (list1 != null) {
			for (AzxxfkHVO Kcpg1 : list1) {
				//�����Ƶ��� 
				Kcpg1.setVoperatorid("0001A1100000000001M2");
				//��������
				Kcpg1.setPk_billtype("SW07");
		        Kcpg1.setSglxdh("0");
				Kcpg1.setPk_corp("1003");
			    Kcpg1.setSggcai("������");  //ʩ���ܲ�
				Kcpg1.setDr(0);
			    //����״̬
				Kcpg1.setVbillstatus(1);
				//�Ƿ��ƽ�����
				if(Kcpg1.getYjazrq() != null ){
					UFBoolean sfyjys = new UFBoolean("Y");
					Kcpg1.setSfyjsy(sfyjys);
				}
				//��Ŀ����
				Kcpg1.setPk_jobbasfil(BaseDataUtil.jobMap.get(Kcpg1
						.getPk_jobbasfil()));
				//ʩ��������
				Map<String,String> NCsgfzr = new HashMap<String,String>();
				NCsgfzr.put( "01","�ع���");
				NCsgfzr.put( "02","������");
				NCsgfzr.put("03","��Զ��");
				NCsgfzr.put("04","���ĺ�");
				NCsgfzr.put("05","�·���");
				NCsgfzr.put( "06","����");
				NCsgfzr.put( "07","������");
				NCsgfzr.put( "08","���˿���");
				NCsgfzr.put("09","���" );
		
				
				for( Entry<String, String>  entry: NCsgfzr.entrySet()){
					if(Kcpg1.getSgfzr() != null){
				 if(Kcpg1.getSgfzr().equals(entry.getKey())){
					 Kcpg1.setSgfzr(entry.getValue().toString());
				 }
				}
				
				}
			}
		}
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(list1);
		
		//�м�����
		InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
		String sqlwhereMill = "where AnZhuangFanKuiRiQi is not null and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' ";
		List<AzxxfkHVOC> listMill = (List<AzxxfkHVOC>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select baozhuangbianhao RESERVE1,chushixingdu ckxd, AnZhuangDiZhi ld,AnZhuangDiZhi ldqd,AnZhuangDiZhi ldzd, shiwaikoujing azbj,ShiWaiShiGongDanWei sgbm, ShiGongYuan sgfzr,GCMC pk_jobbasfil, YiJiaoAnZhuangRiQi yjazrq, AnZhuangRiQi azrq,AnZhuangRiQi kgrq,ZhuangBiaoRiQi zbrq, AnZhuangFanKuiRiQi azfkrq,JunGongRiQi jgrq, hetonghao hth,ziliaofankuiriqi zlfkrq,BiaoBie biaobie,BianMa bianma,BiaoMa biaoma,BiaoWei biaowei, GuanChang sggchang,ChanDi chandi, shigongyuan sgfzr,SheJiTuHao sjth,BeiZhu remark from bz  " //�����ɹ���Ա=������Ա�������ɹ�����=��������
						+ sqlwhereMill
						,new BeanListProcessor(AzxxfkHVOC.class));    
		System.out.println("listMill ����Ϊ��"+listMill .size());
		//��ʼ����������map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (listMill != null) {
			for (AzxxfkHVOC Kcpg1 : listMill) {
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
		String sqlwhere2 = "where   b.bzbh = a.RESERVE1 ";
		List<AzxxfkBVO> list2 = (List<AzxxfkBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select a.azbj,a.biaobie,a.ckxd, a.kgrq,a.jgrq,a.azfkrq,a.azrq, b.bzbh,b.lxr,b.lxdd, b.hm, a.zbrq, b.pk_bzxx, a.biaoma, a.chandi, a.biaowei, a.ckxd cchd,a.pk_jobbasfil,a.remark, a.sjth  " 
                        + "from  lcsw_azxxfk_hCopy  a ,lcsw_bzxx  b  " 
						+ sqlwhere2,
						new BeanListProcessor(AzxxfkBVO.class));
		System.out.println(list2.size());
		if (list2 != null) {
			for (AzxxfkBVO Kcfksj2 : list2) {
				
			//	Kcfksj2.setPk_corp("0001");
				
				Kcfksj2.setDr(0);
				//���ù�������id
				  String pk_azxxfk = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select  min (distinct (a.pk_azxxfkhid) ) from lcsw_azxxfk_h a,lcsw_azxxfk_hCopy b where a.pk_jobbasfil ="
					+"'"+Kcfksj2.getPk_jobbasfil()+"' "
					 , new ColumnProcessor(null));
				   Kcfksj2.setPk_azxxfkhid(pk_azxxfk);
                //���ù�������pk_bzsq
				   if( Kcfksj2.getPk_azxxfkhid() != null ){
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
