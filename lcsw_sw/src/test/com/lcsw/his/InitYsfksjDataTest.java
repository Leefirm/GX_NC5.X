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
import nc.vo.lcsw.sw08.YsfksjHVO;
import nc.vo.lcsw.sw08.YsfksjBVO;
import nc.vo.lcsw.sw08.YsfksjHVOC;

import nc.vo.pub.BusinessException;

public class InitYsfksjDataTest {
	
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
		//�ж� �������ڲ���
		String sqlwhere1 = "where  YanShouHeGeBiaoZhi ='1' and yanshoubuhegeyuanyin =' ' and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' or yanshoubuhegeyuanyin <> ' ' group by  GCMC having COUNT(gcmc)>0";
		List<YsfksjHVO> list1 = (List<YsfksjHVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(  //ʩ��Ա = ʩ��������
						"select min(shigongyuan) ysy,min(yijiaozhenggairiqi) yjzgrq,min(shoulibianhao) RESERVE1, min(chushixingdu) ckxd, min(ShiGongYuan) sgfzr,min((case YanShouHeGeBiaoZhi when 1 then 'Y' when 0 then 'N' end) ) yshgbz, min(YanShoubuHeGeYuanYin)  bhgyy,min( ShiWaiShiGongDanWei) sgbm_name, min(ShiGongYuan) sgfzr,GCMC pk_jobbasfil,min( YiJiaoYanShouRiQi) yjysrq, min(AnZhuangRiQi) azrq,min(ZhuangBiaoRiQi) zbrq, min(YanShouRiQi) ysrq,min(AnZhuangFanKuiRiQi) azfkrq,min( hetonghao) hth,min(ziliaofankuiriqi) zlfkrq,min(BiaoBie) biaobie,min(BianMa) bianma,min(BiaoMa) biaoma,min(BiaoWei) biaowei, min(GuanChang) sggchang,min(ChanDi) chandi, min(shigongyuan) sgfzr,min(SheJiTuHao) sjth,min(BeiZhu) remark from bz "
						+ sqlwhere1,
						new BeanListProcessor(YsfksjHVO.class));
	
		
		//��ʼ����������map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (list1 != null) {
			for (YsfksjHVO Kcpg1 : list1) {
				//��������
				//�����Ƶ��� 
				Kcpg1.setVoperatorid("0001A1100000000001M2");
				Kcpg1.setPk_billtype("SW08");
		        Kcpg1.setSglxdh("0");
				Kcpg1.setPk_corp("1003");
			    Kcpg1.setSggcai("������");  //ʩ���ܲ�
				Kcpg1.setDr(0);
			    //����״̬
				Kcpg1.setVbillstatus(1);
				//����������Ա
				if(Kcpg1.getYsy() != null){
					Map<String,String> NCysy =	new HashMap<String,String>();
					NCysy.put("�ع���", "01");
					NCysy.put("������", "02");
					NCysy.put("��Զ��", "03");
					NCysy.put("���ĺ�", "04");
					NCysy.put("�·���", "05"); 
					NCysy.put("����", "06");
					NCysy.put("������", "07"); 
					NCysy.put("���˿���", "08"); 
					NCysy.put("���", "09"); 
					for( Entry<String, String>  entry: NCysy.entrySet()){
						//	System.out.println(entry.getKey());
						 if(Kcpg1.getYsy().equals(entry.getValue())){
							 Kcpg1.setYsy(entry.getKey());
						 }
						}
					}
				//���յ绰������Ա�ĵ绰����ʩ��Ա�ĵ绰
				if(Kcpg1.getYsy()!= null){
					Map<String,String> NCfkfs = new HashMap<String,String>();
					NCfkfs.put("�ع���", "13978690911");
					NCfkfs.put("������", "13036860005");
					NCfkfs.put("��Զ��", "13014994775");
					NCfkfs.put("���ĺ�", "13077777332");
					NCfkfs.put("�·���", "13978633876"); 
					NCfkfs.put("����", "13097714929");
					NCfkfs.put("������", "13217815978"); 
					NCfkfs.put("���˿���", "13978690911"); 
					NCfkfs.put("���", "13878863273"); 
					for( Entry<String, String>  entry: NCfkfs.entrySet()){
					//	System.out.println(entry.getKey());
					 if(Kcpg1.getYsy().equals(entry.getKey())){
						 Kcpg1.setYslxdh(entry.getValue());
					 }
					}
					}
				
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
				
				//��Ŀ����
				Kcpg1.setPk_jobbasfil(BaseDataUtil.jobMap.get(Kcpg1
						.getPk_jobbasfil()));
			    
			}
		}
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(list1);
		//�м�����
				InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
				String sqlwhereMill = "where  YanShouHeGeBiaoZhi ='1' and yanshoubuhegeyuanyin =' ' and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' or yanshoubuhegeyuanyin <> ' ' ";
				List<YsfksjHVOC> listMill = (List<YsfksjHVOC>) NCLocator
						.getInstance()
						.lookup(IUAPQueryBS.class)
						.executeQuery(
								"select yanshouxingdu ysxd,shigongyuan ysy,yijiaozhenggairiqi yjzgrq,baozhuangbianhao RESERVE1, chushixingdu ckxd, ShiGongYuan sgfzr,(case YanShouHeGeBiaoZhi when 1 then 'Y' when 0 then 'N' end)  yshgbz, YanShoubuHeGeYuanYin  bhgyy, ShiWaiShiGongDanWei sgbm_name, ShiGongYuan sgfzr,GCMC pk_jobbasfil, YiJiaoYanShouRiQi yjysrq, AnZhuangRiQi azrq,ZhuangBiaoRiQi zbrq, YanShouRiQi ysrq,AnZhuangFanKuiRiQi azfkrq, hetonghao hth,ziliaofankuiriqi zlfkrq,BiaoBie biaobie,BianMa bianma,BiaoMa biaoma,BiaoWei biaowei, GuanChang sggchang,ChanDi chandi, shigongyuan sgfzr,SheJiTuHao sjth,BeiZhu remark from bz  " //�����ɹ���Ա=������Ա�������ɹ�����=��������
								+ sqlwhereMill
								,new BeanListProcessor(YsfksjHVOC.class));    
				System.out.println("listMill ����Ϊ��"+listMill .size());
				
				//��ʼ����������map
				BaseDataUtil.init();

				InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
				if (listMill != null) {
					for (YsfksjHVOC Kcpg1 : listMill) {
						//��������
						Kcpg1.setPk_billtype("SW03");
				
						Kcpg1.setPk_corp("1003");
					
						Kcpg1.setDr(0);
					    //����״̬
						Kcpg1.setVbillstatus(1);
						//����������Ա
						if(Kcpg1.getYsy() != null){
							Map<String,String> NCysy =	new HashMap<String,String>();
							NCysy.put("�ع���", "01");
							NCysy.put("������", "02");
							NCysy.put("��Զ��", "03");
							NCysy.put("���ĺ�", "04");
							NCysy.put("�·���", "05"); 
							NCysy.put("����", "06");
							NCysy.put("������", "07"); 
							NCysy.put("���˿���", "08"); 
							NCysy.put("���", "09"); 
							for( Entry<String, String>  entry: NCysy.entrySet()){
								//	System.out.println(entry.getKey());
								 if(Kcpg1.getYsy().equals(entry.getValue())){
									 Kcpg1.setYsy(entry.getKey());
								 }
								}
							}
						//���յ绰������Ա�ĵ绰����ʩ��Ա�ĵ绰
						if(Kcpg1.getYsy()!= null){
							Map<String,String> NCfkfs = new HashMap<String,String>();
							NCfkfs.put("�ع���", "13978690911");
							NCfkfs.put("������", "13036860005");
							NCfkfs.put("��Զ��", "13014994775");
							NCfkfs.put("���ĺ�", "13077777332");
							NCfkfs.put("�·���", "13978633876"); 
							NCfkfs.put("����", "13097714929");
							NCfkfs.put("������", "13217815978"); 
							NCfkfs.put("���˿���", "13978690911"); 
							NCfkfs.put("���", "13878863273"); 
							for( Entry<String, String>  entry: NCfkfs.entrySet()){
							//	System.out.println(entry.getKey());
							 if(Kcpg1.getYsy().equals(entry.getKey())){
								 Kcpg1.setYslxdh(entry.getValue());
							 }
							}
							}
						//��Ŀ����
						Kcpg1.setPk_jobbasfil(BaseDataUtil.jobMap.get(Kcpg1
								.getPk_jobbasfil()));
					    
					}
				}
				NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(listMill);
		//�ӱ���뱨װ��Ϣpk����װ����pk������pk
		String sqlwhere2 = "where  b.bzbh = a.RESERVE1 ";
		List<YsfksjBVO> list2 = (List<YsfksjBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select a.ysxd, a.ysy,a.yslxdh, a.yjzgrq,b.yjysrq,a.bianma,a.biaobie,a.bhgyy,a.ckxd,b.bzbh,b.lxr,b.lxdd, b.hm,a.zbrq,a.ysrq, b.pk_bzxx, a.biaoma, a.chandi, a.biaowei, a.yshgbz, a.yjzgrq, a.ckxd cchd,a.pk_ysfksjHID,a.pk_jobbasfil,a.remark " 
                        + "from    lcsw_ysfksj_hCopy  a ,lcsw_bzxx  b " 
						+ sqlwhere2,
						new BeanListProcessor(YsfksjBVO.class));
		System.out.println(list2.size());
		if (list2 != null) {
			for (YsfksjBVO Kcfksj2 : list2) {
				
			//	Kcfksj2.setPk_corp("0001");
				
				Kcfksj2.setDr(0);
				//���ù�������id
				  String pk_ysfksj = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select  min (distinct (a.pk_ysfksjHID) ) from lcsw_ysfksj_h a,lcsw_ysfksj_hCopy b where a.pk_jobbasfil ="
					+"'"+Kcfksj2.getPk_jobbasfil()+"' "
					 , new ColumnProcessor(null));
				   Kcfksj2.setPk_ysfksjhid(pk_ysfksj);
				    //���ù�������pk_bzsq
				   if( Kcfksj2.getPk_ysfksjhid() !=null ){
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
