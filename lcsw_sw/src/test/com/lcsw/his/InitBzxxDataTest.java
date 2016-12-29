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

import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.lcsw.sw10.BzfyHVO;

import nc.vo.pub.BusinessException;

public class InitBzxxDataTest {
	
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
		 //initJobData();
		//
		initFinishedData();

		long resetTime = System.currentTimeMillis();
		System.out
				.println("��ȡ������ɹ�����ʱ��" + (resetTime - conntime) / 1000 + "�룻");
	}

	

	@SuppressWarnings({ "unchecked", "unused" })
	public static void initFinishedData() throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
		String sqlwhere = "where BaoZhuangbianhao is not null and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' and BaoZhuangJingBanRenYuan is not null ";
		List<LcswBzxxHVO> list1 = (List<LcswBzxxHVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select ruhuriqi lhrq, jieturiqi sjatrq,shigongyuan ysy,chushixingdu ckxd,yijiaoyanshouriqi yjbwysrq,shiwaikoujing azbj,BaoZhuangBianHao bzbh, (case shejibiaozhi when 1 then 'Y' when 0 then 'N' end) sjbs,Xieyibianhao xybh,ShouLiBianHao slbh, RuHuBianHao rhbh, HuMing hm, BaoZhuangRiQi bzrq,  (case ErQiGongChengBiaoZhi when 1 then 'Y' when 0 then 'N' end) eqgcbz, LianXiDiZhi lxdz, LianXiRen lxr, LianXiDianHua lxdd, AnZhuangDiZhi azdz, YongShuiXingZhi ysxz, YuanGuanJing ygj, ShiWaiKouJing swkj, ShiWaiGuanJing swgj, ShiNeiKouJing snkj, ShiNeiGuanJing sngj, YongShuiHuShu yshs, ZuiGaoCengShu zgcs, JianZhuMianJi jzmj, YongShuiRenShu ysrs, ZuiDaShuiLiang zdsl, GongChengXingZhi gcxz, JieShenQingBiaoRiQi jsqbrq, (case KanChaXuKe when 1 then 1 when 0 then 0 end)   kcxk, KanChaRiQi kcrq, BaoZhuangJingBanRenYuan bzjbry, KanChaSheJiFei kcsjf, FuKuanFangShi fkfs, ZhangHao yhzh, KaiHuHang khyh, KanChaRenYuan kcry, KanChaFanKuiRiQi kcfkrq, kanchashejirenyuan kcsjry, YiJiaoSheJiRiQi yjsjrq, YiJiaoHuaHongXianRiQi yjhhxrq, JieHongXianTuRiQi jhxtrq, YiJiaoSheJiPingShenRiQi yjsjpsrq, SheJiPingShenWanChengRiQi sjpswcrq, SheJiDanWei sjdw, GuanChang gc, ShiWaiShiGongDanWei swsgdw, ShuiBiaoGeShu sbgs, ShiNeiShiGongDanWei snsgdw, ShiGongTianShu sgts, yijiaoanzhuangriqi yjazrq, AnZhuangRiQi azrq, AnZhuangFanKuiRiQi azfkrq, JunGongRiQi jgrq, hetonghao hth,  (case ziliaofankuibiaozhi when 1 then 'Y' when 0 then 'N' end) zlfkrq, ZhuangBiaoRiQi zbrq, YanShouRiQi ysrq, YiJiaoYanShouRiQi yjysrq, BiaoMa biaoma, ChanDi chandi, BianMa bianma, BiaoBie biaobie, BiaoWei biaowei, (case YanShouHeGeBiaoZhi when 1 then 'Y' when 0 then 'N' end)  yshgbz, yanshoubuhegeyuanyin bhgyy, YiJiaoZhengGaiRiQi yjzgrq, yanshouxingdu ysxd, ZhengGaiRiQi zgrq,  (case ZhengGaiHeGeBiaoZhi when 1 then 1 when 0 then 0 end) zghgbz, BeiZhu remark, SheJiRenYuan sjry, SheJiJingBanRenYuan sjjbry, SheJiTuHao sjth, fadanriqi sjfdrq, KanChaJingBanRenYuan kcjbr, shigongyuan sgfzr,gcmc pk_jobbasfil,jianmianleixing jmlx,zonglianxifangshi zlxfs,BaoZhuangJingBanRenYuan jbry from bz "
							+ sqlwhere
						,new BeanListProcessor(LcswBzxxHVO.class));
	
		System.out.println(list1.size());
		//��ʼ����������map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (list1 != null) {
			for (LcswBzxxHVO bzxx : list1) {
				//��������
				bzxx.setPk_billtype("SW01");
				//��װ״̬
				if( bzxx.getSlbh() != null && bzxx.getKcry() == null ){
					bzxx.setBzzt(2);//�����ɹ�
				}
				else if( bzxx.getKcry() != null && bzxx.getKcfkrq() == null){
					bzxx.setBzzt(2);//�����ɹ�
				}
				else if( bzxx.getKcfkrq() != null && bzxx.getSjry() == null ){
					bzxx.setBzzt(3);//����ɹ�
				}
				else if( bzxx.getSjry() != null && bzxx.getSwsgdw() == null){
					bzxx.setBzzt(4);//��Ƴɹ�
				}
				else if(bzxx.getSwsgdw() !=null && bzxx.getAzfkrq() == null){
					bzxx.setBzzt(5);//�ɹ���
				}
				else if (bzxx.getAzfkrq() != null && bzxx.getYsrq() == null){
					bzxx.setBzzt(6);//��װ���
				}
				else if ( bzxx.getYsrq() != null && bzxx.getYshgbz().equals( new Integer(1))  && bzxx.getRhbh() ==null){
					bzxx.setBzzt(7);//���պϸ�
				}
				else if( bzxx.getYsrq() != null && bzxx.getYshgbz().equals( new Integer(0))){
					bzxx.setBzzt(8);//���ղ��ϸ�
				}
				else if(bzxx.getRhbh() != null){
					bzxx.setBzzt(9);//���뻧
				}
				//������Ա����
				if(bzxx.getYsy()!= null){
					Map<String,String> NCfkfs = new HashMap<String,String>();
					NCfkfs.put("�ع���", "01");
					NCfkfs.put("������", "02");
					NCfkfs.put("��Զ��", "03");
					NCfkfs.put("���ĺ�", "04");
					NCfkfs.put("�·���", "05"); 
					NCfkfs.put("����", "06");
					NCfkfs.put("������", "07"); 
					NCfkfs.put("���˿���", "08"); 
					NCfkfs.put("���", "09"); 
					for( Entry<String, String>  entry: NCfkfs.entrySet()){
					//	System.out.println(entry.getKey());
					 if(bzxx.getYsy().equals(entry.getValue())){
						 bzxx.setYsy(entry.getKey());
					 }
					}
					}
				//���յ绰������Ա�ĵ绰����ʩ��Ա�ĵ绰
				if(bzxx.getYsy()!= null){
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
					 if(bzxx.getYsy().equals(entry.getKey())){
						 bzxx.setYslxdh(entry.getValue());
						 bzxx.setYsdh(entry.getValue());
					 }
					}
					}
				//���ʽ 1Ϊ�ֽ�2Ϊ���գ�3Ϊ���ۣ�4Ϊת��
				//	bzxx.setFkfs("0001A310000000001BF1");
					if(bzxx.getFkfs() != null){
					Map<String,String> NCfkfs = new HashMap<String,String>();
					NCfkfs.put("7", "0001AA1000000000KDSS");
					NCfkfs.put("2", "0001A31000000000QATL");//����
					NCfkfs.put("1", "0001A310000000001BF1");//�ֽ�
					NCfkfs.put("3", "0001A31000000000QATK");//����
					
					NCfkfs.put("4", "0001A310000000001BF3"); //ת�ˣ�ԭ��ҵ������
				//	NCfkfs.put("4", "0001A31000000000LKCM");//ת�ˣ�����ҵֱ����
					NCfkfs.put("5", "0001A31000000000LM5J"); //���ˣ���Ӧ��ֱ�ӳ����ˣ�
					for( Entry<String, String>  entry: NCfkfs.entrySet()){
					//	System.out.println(entry.getKey());
					 if(bzxx.getFkfs().equals(entry.getKey())){
						 bzxx.setFkfs(entry.getValue());
					 }
					}
					}
					//��ˮ����
					//��ˮ����
					if(bzxx.getYsxz() != null){
						Map<String,String> NCfkfs = new HashMap<String,String>();
						NCfkfs.put("1001", "���ղ��");
						NCfkfs.put("1101", "����");
						NCfkfs.put("1102", "���ۻ���");
						NCfkfs.put("1103", "�Żݻ���");
						NCfkfs.put("1201", "��Ԫ��"); 
						NCfkfs.put("1301", "�����");
						NCfkfs.put("1401", "���������");
						NCfkfs.put("1402", "����ԭ��");
						NCfkfs.put("1403", "�屣��");
						NCfkfs.put("1405", "��������ԭ");
						NCfkfs.put("1501", "����ˮ����");
						NCfkfs.put("1502", "������ˮ"); 
						NCfkfs.put("1601", "����˾��ˮ");
						NCfkfs.put("1701", "��������ˮ");
						NCfkfs.put("1702", "�Żݶ�����");
						NCfkfs.put("1703", "��������ˮ");
						NCfkfs.put("1704", "�Ż�������");
						NCfkfs.put("1801", "�ͱ�����ˮ");
						NCfkfs.put("1901", "�Ż�����");
						NCfkfs.put("1A01", "ѧУ��ˮ");
						NCfkfs.put("2101", "��������");
						NCfkfs.put("2102", "����פ�ߴ�");
						NCfkfs.put("2103", "����.��˾");
						NCfkfs.put("2104", "���е�λ");
						NCfkfs.put("2105", "�Ļ�����");
						NCfkfs.put("2106", "���ų���");
						NCfkfs.put("2107", "ͼƬ��");
						NCfkfs.put("2108", "��������");
						NCfkfs.put("2109", "���ί��");
						NCfkfs.put("2201", "��רԺУ");
						NCfkfs.put("2202", "��ר");
						NCfkfs.put("2203", "����ѧУ");
						NCfkfs.put("2204", "��ѧ");
						NCfkfs.put("2205", "Сѧ");
						NCfkfs.put("2205", "Сѧ");
						NCfkfs.put("2206", "�׶�԰");
						NCfkfs.put("2207", "ְ��ѧУ");
						NCfkfs.put("2208", "ѧУ����");
						NCfkfs.put("2209", "����ѧ1");
						NCfkfs.put("2301", "���Ӱ칫");
						NCfkfs.put("2303", "��������");
						NCfkfs.put("2302", "Ӫ����ˮ");
						NCfkfs.put("2402", "����");
						NCfkfs.put("2502", "����");
						NCfkfs.put("2503", "��԰");
						NCfkfs.put("2504", "ҽԺҽ��");
						NCfkfs.put("2505", "��������");
						NCfkfs.put("2601", "������ҵˮ");
						NCfkfs.put("2701", "����ˮ����");
						NCfkfs.put("2702", "�Ż�����");
						NCfkfs.put("3101", "��еұ��");
						NCfkfs.put("3102", "����");
						NCfkfs.put("3103", "����");
						NCfkfs.put("3104", "��֯��ά");
						NCfkfs.put("3105", "ʳƷ����");
						NCfkfs.put("3106", "����ҵ");
						NCfkfs.put("3107", "ũ��Ʒ�ӹ�");
						NCfkfs.put("3107", "ũ��Ʒ�ӹ�");
						NCfkfs.put("3108", "��ҵ����");
						NCfkfs.put("3109", "��������");
						NCfkfs.put("3110", "��������");
						NCfkfs.put("3111", "ѧУ����");
						NCfkfs.put("3112", "����ˮ��ҵ");
						NCfkfs.put("3113", "��ҵ�ִ�");
						NCfkfs.put("3114", "ľ�ļӹ�");
						NCfkfs.put("3115", "����ҵ");
						NCfkfs.put("3201", "���ʹ�ҵˮ");
						NCfkfs.put("3301", "ʳƷԭˮ");
						NCfkfs.put("3302", "ԭˮ");
						NCfkfs.put("3401", "�Ϻ���ˮ");
						NCfkfs.put("3501", "��ҵԭ��");
						NCfkfs.put("3502", "���۹�ҵԭ");
						NCfkfs.put("3503", "������ҵԭ");
						NCfkfs.put("4101", "�̵�.�̳�");
						NCfkfs.put("4102", "����");
						NCfkfs.put("4103", "�ִ�");
						NCfkfs.put("4104", "ó������");
						NCfkfs.put("4105", "����Ӫҵ");
						NCfkfs.put("4106", "����Ӫҵ");
						NCfkfs.put("4107", "ѧУӪҵ");
						NCfkfs.put("4108", "��ʳƷ�ӹ�");
						NCfkfs.put("4109", "��ҵ����");
						NCfkfs.put("4110", "�г�");
						NCfkfs.put("4201", "��ӰԺ");
						NCfkfs.put("4202", "�����");
						NCfkfs.put("4203", "��Ӿ��");
						NCfkfs.put("4204", "Ӫ��ҽԺ");
						NCfkfs.put("4205", "�Ļ�����");
						NCfkfs.put("4301", "�ʵ�");
						NCfkfs.put("4302", "ͨѶ");
						NCfkfs.put("4303", "����");
						NCfkfs.put("4304", "֤ȯ");
						NCfkfs.put("4305", "����");
						NCfkfs.put("4306", "����");
						NCfkfs.put("4307", "��������");
						NCfkfs.put("4401", "����");
						NCfkfs.put("4402", "����");
						NCfkfs.put("4402", "����");
						NCfkfs.put("4403", "�д���");
						NCfkfs.put("4404", "����");
						NCfkfs.put("4405", "��ջ");
						NCfkfs.put("4406", "��ʳҵ");
						NCfkfs.put("4408", "ϴ�µ�");
						NCfkfs.put("4409", "����");
						NCfkfs.put("4411", "����.��¥");
						NCfkfs.put("4413", "����");
						NCfkfs.put("4414", "���ֳ���");
						NCfkfs.put("4415", "��ͨ����ҵ");
						NCfkfs.put("4417", "Ӫҵ");
						NCfkfs.put("4418", "����ˮӪҵ");
						NCfkfs.put("4419", "����ҵ");
						NCfkfs.put("4501", "�Ż���ҵ1");
						NCfkfs.put("4502", "Ӫҵԭ��");
						NCfkfs.put("4503", "����Ӫҵԭ");
						NCfkfs.put("4504", "�Ż�Ӫҵ2");
						NCfkfs.put("4601", "��������");
						NCfkfs.put("4602", "������");
						NCfkfs.put("4603", "ɣ��ԡ��");
						NCfkfs.put("4604", "ϴ��ҵ");
						NCfkfs.put("4607", "����ˮ����");
						NCfkfs.put("4608", "��������ԭ");
						NCfkfs.put("4609", "����ԭ��");
						NCfkfs.put("4701", "�Żݱ���");
						NCfkfs.put("4701", "�Żݱ���");
						NCfkfs.put("4801", "����Ӫҵ");
						NCfkfs.put("5101", "סլʩ��");
						NCfkfs.put("5102", "��λʩ��");
						NCfkfs.put("5103", "ʩ������");
						NCfkfs.put("5201", "���ز�����");
						NCfkfs.put("5203", "���ز�����");
						NCfkfs.put("5204", "����ˮ����");
						NCfkfs.put("5205", "����ս���");
						NCfkfs.put("5205", "����ս���");
						NCfkfs.put("5206", "��·ʩ��");
						NCfkfs.put("5301", "����ԭ��");
						NCfkfs.put("5302", "���۽���ԭ");
						NCfkfs.put("5303", "��������ԭ");
						NCfkfs.put("6101", "�Ա�ˮԴ");
						NCfkfs.put("6102", "ͣ��ˮ");
						NCfkfs.put("6103", "�⳥��ʧˮ");
						NCfkfs.put("6104", "�Ż�ˮ");
						NCfkfs.put("6105", "�Żݴ�����");
						for( Entry<String, String>  entry: NCfkfs.entrySet()){
						//	System.out.println(entry.getKey());
					
						 if(bzxx.getYsxz().equals(entry.getKey())){
							 bzxx.setYsxz(entry.getValue());
						  }
							}
						}
			
			//	bzxx.setBzzt(7);
				//Ӫҵ����-����
				bzxx.setYyqy("����");
				bzxx.setPk_corp("1003");
				//��ˮ��������ˮ����Ĭ��=1
				bzxx.setYshs(1);
				bzxx.setYsrs(1);
				//��Ŀ����
				BaseDataUtil.init();
				bzxx.setPk_jobbasfil(BaseDataUtil.jobMap.get(bzxx
						.getPk_jobbasfil()));
				//��������
				if(bzxx.getKhyh() != null){
					HashMap<String, String> NCKfyh = new HashMap<String,String>();
					//�й���������
					NCKfyh.put("000", "0001ZZ1000000001OCUC");NCKfyh.put("001", "0001ZZ1000000001OCUC");NCKfyh.put("002", "0001ZZ1000000001OCUC");NCKfyh.put("006", "0001ZZ1000000001OCUC");NCKfyh.put("009", "0001ZZ1000000001OCUC");//�й���������
					//�й���������
					NCKfyh.put("105", "0001ZZ1000000001OCUD");	NCKfyh.put("AAA", "0001ZZ1000000001OCUD");NCKfyh.put("107", "0001ZZ1000000001OCUD");NCKfyh.put("125", "0001ZZ1000000001OCUD");	NCKfyh.put("131", "0001ZZ1000000001OCUD");NCKfyh.put("179", "0001ZZ1000000001OCUD");NCKfyh.put("179", "0001ZZ1000000001OCUD");
					//��ͨ����
					NCKfyh.put("267", "0001ZZ1000000001WIUD");NCKfyh.put("BBB", "0001ZZ1000000001WIUD");
					//����������
					NCKfyh.put("400", "0001A31000000000JYJW");NCKfyh.put("401", "0001A31000000000JYJW");NCKfyh.put("408", "0001A31000000000JYJW");NCKfyh.put("432", "0001A31000000000JYJW");
					//ũҵ����
					NCKfyh.put("600", "0001ZZ1000000001WIUA");NCKfyh.put("649", "0001ZZ1000000001WIUA");
					//�й�����
					NCKfyh.put("900", "0001ZZ1000000001WIUB");NCKfyh.put("EEE", "0001ZZ1000000001WIUB");
					NCKfyh.put("CCC", "0001ZZ1000000001WIU9");//�������
					NCKfyh.put("DDD", "0001ZZ1000000001WIUA");//ũҵ����
					for( Entry<String, String>  entry: NCKfyh.entrySet()){
						 if(bzxx.getKhyh().equals(entry.getKey())){
							 bzxx.setKhyh(entry.getValue());
						  }
					}
					}

//				//������Ա
				
				Map<String,String> NCjbry = new HashMap<String,String>();
				NCjbry.put("���", "0001F51000000000SL11");
				NCjbry.put("��ѧ��", "0001AA1000000000TPG6");
				NCjbry.put("��ݿ�", "0001AA1000000000TPG7");
				NCjbry.put("������", "0001AA1000000000JB8N");
				NCjbry.put("�Ⲩ", "0001AA1000000000TPG3");
				NCjbry.put("ŷ��", "0001AA1000000000TPG4");
				NCjbry.put("�ž�", "0001AA1000000000TPG5");
				NCjbry.put("������", "0001AA1000000000JB8O");
				NCjbry.put("������", "0001F51000000000OLUT");
				NCjbry.put("����", "0001A31000000000QB1J");
				
				for( Entry<String, String>  entry: NCjbry.entrySet()){
				if(bzxx.getJbry() != null){
				 if(bzxx.getJbry().equals(entry.getKey())){
					 bzxx.setJbry(entry.getValue().toString());
				 }
				}
			}
				//��bzxx�в���pk_bzsq
				String PkBzsq = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class)
				        .executeQuery("select  b.pk_bzsq from lcsw_bzsq b where b.pk_jobbasfil ="+"'"+ bzxx.getPk_jobbasfil()+"'"
				        		, new ColumnProcessor(null));
				bzxx.setPk_bzsq(PkBzsq);
			}
		}
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(list1);
		System.out.println(list1.size());

	}

	@SuppressWarnings("unchecked")
	public static void initJobData() throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource("sw_bz");

		List<String> list = (List<String>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery("select distinct gcmc from bz",
						new ColumnListProcessor());
		InvocationInfoProxy.getInstance().setUserDataSource("nc_psj");

		int code = 31065;

		for (int i = 0; i < list.size(); i++) {

			String name = list.get(i);
			if (StringUtil.isEmptyWithTrim(name)) {
				continue;
			}

			JobbasfilVO jvo = new JobbasfilVO();
			jvo.setPk_corp("0001");
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
