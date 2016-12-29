package com.lcsw.his;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

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
import nc.vo.pub.BusinessException;

public class InitHisDataTest {
	
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

	

	@SuppressWarnings("unchecked")
	public static void initFinishedData() throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
		String sqlwhere = "where ruhubiaozhi=1 and ruhubianhao is not null and BaoZhuangBianHao='2015000001'";
		List<LcswBzxxHVO> list = (List<LcswBzxxHVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select BaoZhuangBianHao bzbh, Xieyibianhao xybh,ShouLiBianHao slbh, RuHuBianHao rhbh, HuMing hm, BaoZhuangRiQi bzrq,  (case ErQiGongChengBiaoZhi when 1 then 'Y' when 0 then 'N' end) eqgcbz, LianXiDiZhi lxdz, LianXiRen lxr, LianXiDianHua lxdd, AnZhuangDiZhi azdz, YongShuiXingZhi ysxz, YuanGuanJing ygj, ShiWaiKouJing swkj, ShiWaiGuanJing swgj, ShiNeiKouJing snkj, ShiNeiGuanJing sngj, YongShuiHuShu yshs, ZuiGaoCengShu zgcs, JianZhuMianJi jzmj, YongShuiRenShu ysrs, ZuiDaShuiLiang zdsl, GongChengXingZhi gcxz, JieShenQingBiaoRiQi jsqbrq, (case KanChaXuKe when 1 then 1 when 0 then 0 end)   kcxk, KanChaRiQi kcrq, BaoZhuangJingBanRenYuan bzjbry, KanChaSheJiFei kcsjf, FuKuanFangShi fkfs, ZhangHao yhzh, KaiHuHang khyh, KanChaRenYuan kcry, KanChaFanKuiRiQi kcfkrq, kanchashejirenyuan kcsjry, YiJiaoSheJiRiQi yjsjrq, YiJiaoHuaHongXianRiQi yjhhxrq, JieHongXianTuRiQi jhxtrq, YiJiaoSheJiPingShenRiQi yjsjpsrq, SheJiPingShenWanChengRiQi sjpswcrq, SheJiDanWei sjdw, GuanChang gc, ShiWaiShiGongDanWei swsgdw, ShuiBiaoGeShu sbgs, ShiNeiShiGongDanWei snsgdw, ShiGongTianShu sgts, yijiaoanzhuangriqi yjazrq, AnZhuangRiQi azrq, AnZhuangFanKuiRiQi azfkrq, JunGongRiQi jgrq, hetonghao hth,  (case ziliaofankuibiaozhi when 1 then 'Y' when 0 then 'N' end) zlfkrq, ZhuangBiaoRiQi zbrq, YanShouRiQi ysrq, YiJiaoYanShouRiQi yjysrq, BiaoMa biaoma, ChanDi chandi, BianMa bianma, BiaoBie biaobie, BiaoWei biaowei, (case YanShouHeGeBiaoZhi when 1 then 1 when 0 then 1 end)  yshgbz, yanshoubuhegeyuanyin bhgyy, YiJiaoZhengGaiRiQi yjzgrq, yanshouxingdu ysxd, ZhengGaiRiQi zgrq,  (case ZhengGaiHeGeBiaoZhi when 1 then 1 when 0 then 0 end) zghgbz, BeiZhu remark, SheJiRenYuan sjry, SheJiJingBanRenYuan sjjbry, SheJiTuHao sjth, fadanriqi sjfdrq, KanChaJingBanRenYuan kcjbr, shigongyuan sgfzr,gcmc pk_jobbasfil,jianmianleixing jmlx,zonglianxifangshi zlxfs,BaoZhuangJingBanRenYuan jbry from bz "
								+ sqlwhere,
						new BeanListProcessor(LcswBzxxHVO.class));

		System.out.println(list.size());
		
		//��ʼ����������map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (list != null) {
			for (LcswBzxxHVO bzxx : list) {
				//��������
				bzxx.setPk_billtype("SW01");
				//��װ״̬
				bzxx.setBzzt(7);
				//Ӫҵ����-����
//				bzxx.setYyqy("0001AA1000000000JB8R");
				bzxx.setPk_corp("1003");
				//��ˮ��������ˮ����Ĭ��=1
				bzxx.setYshs(1);
				bzxx.setYsrs(1);
				//��Ŀ����
				BaseDataUtil.init();
				bzxx.setPk_jobbasfil(BaseDataUtil.jobMap.get(bzxx
						.getPk_jobbasfil()));
//				//��ˮ����
//				bzxx.setYsxz(BaseDataUtil.ysxzMap.get(bzxx.getYsxz()));
//				//����ھ���
//				String[] kjs = new String[]{"ygj","swkj","swgj","snkj","sngj"};
//				for (String kj : kjs) {
//					bzxx.setAttributeValue(kj, BaseDataUtil.kjbMap.get(bzxx.getAttributeValue(kj)));
//				}
//				//��������
//				bzxx.setJmlx(BaseDataUtil.jmlxMap.get(bzxx.getJmlx()));
//				//��������
//				bzxx.setGcxz(BaseDataUtil.gcxzMap.get(bzxx.getGcxz()));
//				//FIXME
//				//���ʽ
//				if("�ֽ�".equals(bzxx.getFkfs().replaceAll(" ", ""))) {
//					
//				}
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
				 if(bzxx.getJbry().equals(entry.getKey())){
					 bzxx.setJbry(entry.getValue().toString());
				 }
				}
//				String[] jbrys = new String[]{"jbry","sjjbry","kcjbr"};
//				for (String jb : jbrys) {
//					bzxx.setAttributeValue(jb, BaseDataUtil.jbryMap.get(bzxx.getAttributeValue(jb)));
//				}
//				//������Ա
//				bzxx.setKcry(BaseDataUtil.kcryMap.get(bzxx.getKcry()));
//				//�����Ա
//				String[] sjrys = new String[]{"kcsjry","sjry"};
//				for (String sj : sjrys) {
//					bzxx.setAttributeValue(sj, BaseDataUtil.sjryMap.get(bzxx.getAttributeValue(sj)));
//				}
//				//��λ
//				String[] danweis = new String[]{"sjdw","swsgdw","snsgdw"};
//				for (String dw : danweis) {
//					bzxx.setAttributeValue(dw, BaseDataUtil.sjsgcorpMap.get(bzxx.getAttributeValue(dw)));
//				}
			}
		}
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(list);
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
