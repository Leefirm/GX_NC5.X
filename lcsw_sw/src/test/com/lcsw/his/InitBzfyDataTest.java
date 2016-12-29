package com.lcsw.his;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
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

import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.lcsw.sw10.BzfyHVO;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

public class InitBzfyDataTest {
	
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

	

	@SuppressWarnings({ "unchecked" })
	public static void initFinishedData() throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
		//�ж� �����Ա���� RESERVE1���湤������
		String sqlwhere1 = "where  gcmc is not null and baozhuangbianhao is not null and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' order by gcmc";
		List<BzfyHVO> list1 = (List<BzfyHVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(  
						"select gcmc gcmc, Gcmc RESERVE1, KanChaSheJiFei kcsjf,YongShuiHuShu yshs, YuSuanGongChengKuan ysgck1,YuSuanGongChengKuan2 ysgck2,YuSuanGongChengKuan3 ysgck3,BuJiaoKanSheFei bjkcf,JueSuanGongChengKuan jsgck, ShouLiBianHao slbh,Xieyibianhao xybh, LianXiDiZhi lxdz,LianXiRen lxr, FuKuanFangShi fkfs,jianmianleixing jmlx,KaiHuHang khyh,ZhangHao yhzh,LianXiDianHua lxdh,AnZhuangDiZhi azdz,GongChengXingZhi gcxz,YongShuiXingZhi ysxz,YuanGuanJing ygj,ShiNeiKouJing snkj,ShiWaiKouJing swkj,ShiNeiGuanJing sngj, ShiWaiGuanJing  swgj,ZuiGaoCengShu zgcs,JianZhuMianJi jzmj,YongShuiRenShu ysrs,ZuiDaShuiLiang zdsl,BaoZhuangJingBanRenYuan jbry,zonglianxifangshi zlxfs, (case SheJiBiaoZhi when 1 then 'Y' when 0 then 'N' end) sjbs,KanChaXuKe kcxk,BeiZhu remark from bz "
						+ sqlwhere1,
						new BeanListProcessor(BzfyHVO.class));
		
	
		
		//��ʼ����������map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (list1 != null) {
			for (BzfyHVO Kcpg1 : list1) {
				//��������
				Kcpg1.setPk_billtype("SW10");
		     
				Kcpg1.setPk_corp("1003");
			 
				Kcpg1.setDr(0);
			    //����״̬
				Kcpg1.setVbillstatus(8);
				//��Ŀ����
				Kcpg1.setReserve1(BaseDataUtil.jobMap.get(Kcpg1
						.getReserve1()));
				Kcpg1.setGcmc(BaseDataUtil.jobMap.get(Kcpg1
						.getGcmc()));
				//��װ����pk
				String PkBzsq = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class)
						        .executeQuery("select  b.pk_bzsq from lcsw_bzsq b where b.pk_jobbasfil ="+"'"+ Kcpg1.getReserve1()+"'"
						        		, new ColumnProcessor(null));
		//		System.out.println(PkBzsq);
				Kcpg1.setPk_bzsq(PkBzsq);			   
			}
			System.out.println("��ѯ��δȥ����Ŀ����list������"+list1.size());
			//ȥ���ظ���Ŀ
			List<BzfyHVO> listDistinct = new ArrayList<BzfyHVO>();//�µĶ�������װȥ�غ������
			Integer hushu=0;//��ˮ����
			String gcxm = null;//������Ŀ
			BzfyHVO DistinctGc =new BzfyHVO();
			int count = 0;
			UFDouble bjkcsjf0 = new UFDouble(new Double(0));
				for (BzfyHVO Bzfy : list1)  {
					//ȥ�������������Ϊ0��
					if(Bzfy.getBjkcf() != null && Bzfy.getBjkcf().equals(bjkcsjf0)){
						Bzfy.setBjkcf(null);
					}
					//ȥ���������Ϊ0��
					if(Bzfy.getKcsjf() != null && Bzfy.getKcsjf() .equals(bjkcsjf0)){
						Bzfy.setKcsjf(null);
					}
					//ȥ��Ԥ�㹤�̿����Ϊ0��
					if(Bzfy.getYsgck1() !=null && Bzfy.getYsgck1().equals(bjkcsjf0)){
						Bzfy.setYsgck1(null);
					}
					//ȥ��Ԥ�㹤�̿��2��Ϊ0��
					if(Bzfy.getYsgck2() != null && Bzfy.getYsgck2().equals(bjkcsjf0)){
						Bzfy.setYsgck2(null);
					}
					//ȥ��Ԥ�㹤�̿��3��Ϊ0��
					if(Bzfy.getYsgck3() !=null && Bzfy.getYsgck3().equals(bjkcsjf0)){
						Bzfy.setYsgck3(null);
					}
					//ȥ�����㹤�̿���Ϊ0��
					if(Bzfy.getJsgck() !=null && Bzfy.getJsgck().equals(bjkcsjf0)){
						Bzfy.setJsgck(null);
					}
					count=count+1;
					if(Bzfy.getReserve1() != null){//��Ŀ��Ϊ��
					if (gcxm ==null ){				
						gcxm = Bzfy.getReserve1();
				        DistinctGc = (BzfyHVO) Bzfy.clone();//��¡
				        hushu=1;	 	    						
		 	    	}else if( gcxm.equals(Bzfy.getReserve1()) ) {
		 	    		hushu=hushu+1;
		 	    	} else  {
		 	    		DistinctGc.setYshs(hushu);
		 	    		listDistinct.add(DistinctGc);
		 	    		gcxm = Bzfy.getReserve1();  
		 	    		hushu=1;
		 	    		DistinctGc = (BzfyHVO) Bzfy.clone();
		 	    		if(count==list1.size()){ //���һ������������
		 		    		  listDistinct.add(DistinctGc);
		 	    		}
		 	    	}
		    	  } 
					else{
		    		  listDistinct.add(Bzfy);
		    	  }
					
				}	
				
				NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(listDistinct);
				System.out.println("������������listDistinct������"+listDistinct.size());
		}
		
		
		
		//��ʱ��װ�����ӱ���
		List<BzfyBVO> newList = new ArrayList<BzfyBVO>();
		//��ѯ������Ʒ�
		String sqlwhere2 = " where baozhuangbianhao is not null and KanChaSheJiFei <>'0' and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' " ;
		InvocationInfoProxy.getInstance().setUserDataSource(BZDS);

		List<BzfyBVO> list2 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(//������=RESERVE1
				"select shoukanshefeirenyuan sfry, shoulibianhao  RESERVE1 ,Gcmc Reserve2,KanSheFeiFuKuanFangShi PK_BALATYPE, KanChaSheJiFei amount, (case KanSheFeiXiaoZhangBiaoZhi when 1 then 'Y' when 0 then 'N' end) xzbz, KanSheFeiXiaoZhangRiQi xzrq,beizhu remark from bz "
				+sqlwhere2
				,new BeanListProcessor(BzfyBVO.class));
		//�������ʱ��
		if(list2 !=null ){
		for(BzfyBVO bzfy:list2){
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDSS");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				NCSfry.put( "0001AA1000000001QS1E" , "��Ω��" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
				  }
				}
		   }
			newList.add(bzfy);
		 }
		}
		// ��ѯԤ�㹤�̿�
		String sqlwhere3 = "where baozhuangbianhao is not null and YuSuanGongChengKuan <>'0'  and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' ";
		List<BzfyBVO> list3 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						// ������=RESERVE1
						"select shouyusuankuanrenyuan sfry,shoulibianhao  RESERVE1, Gcmc Reserve2,   YuSuanKuanFuKuanFangShi PK_BALATYPE,YuSuanGongChengKuan amount,  (case YuSuanKuanXiaoZhangBiaoZhi when 1 then 'Y' when 0 then 'N' end) xzbz,YuSuanKuanXiaoZhangRiQi  xzrq,beizhu remark from bz "
								+ sqlwhere3,
						new BeanListProcessor(BzfyBVO.class));
		//�������ʱ��
		if(list3 !=null ){
		for(BzfyBVO bzfy:list3){
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDST");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				
				NCSfry.put( "0001AA1000000001QS1E" , "��Ω��" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
				  }
				}
			   }
			newList.add(bzfy);
		  }
		}	
		//��ѯԤ�㹤�̿�2
		String sqlwhere4 = "where baozhuangbianhao is not null and YuSuanGongChengKuan2 <>'0' and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624'";
		List<BzfyBVO> list4 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						// ������=RESERVE1
						"select shouyusuankuanrenyuan2 sfry, shoulibianhao  RESERVE1,Gcmc Reserve2,    YuSuanKuanFuKuanFangShi2 PK_BALATYPE,YuSuanGongChengKuan2 amount,  (case YuSuanKuanXiaoZhangBiaoZhi2 when 1 then 'Y' when 0 then 'N' end)xzbz,YuSuanKuanXiaoZhangRiQi2  xzrq,beizhu remark from bz "
								+ sqlwhere4,
						new BeanListProcessor(BzfyBVO.class));
		
		//�������ʱ��
		if(list4 !=null ){
		for(BzfyBVO bzfy:list4){
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDSU");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				
				NCSfry.put( "0001AA1000000001QS1E" , "��Ω��" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
				  }
				}
			}
			newList.add(bzfy);
		 }
		}
		// ��ѯԤ�㹤�̿�3
		String sqlwhere5 = "where baozhuangbianhao is not null and YuSuanGongChengKuan3 <>'0' and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624'";
		List<BzfyBVO> list5 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						// ������=RESERVE1
						"select shouyusuankuanrenyuan3 sfry,shoulibianhao  RESERVE1,Gcmc Reserve2,    YuSuanKuanFuKuanFangShi3 PK_BALATYPE,YuSuanGongChengKuan3 amount,  (case YuSuanKuanXiaoZhangBiaoZhi3 when 1 then 'Y' when 0 then 'N' end)xzbz,YuSuanKuanXiaoZhangRiQi3  xzrq,beizhu remark from bz "
								+ sqlwhere5,
						new BeanListProcessor(BzfyBVO.class));

		// �������ʱ��
		if(list5 != null ){
		for (BzfyBVO bzfy : list5) {
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDSV");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				
				NCSfry.put( "0001AA1000000001QS1E" , "��Ω��" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
				  }
				}
			}
			newList.add(bzfy);
		}
		}
		// ��ѯ�a�����O�M
		String sqlwhere6 = " where baozhuangbianhao is not null and BuJiaoKanSheFei <>'0'  and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624'";
		List<BzfyBVO> list6 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						// ������=RESERVE1
						"select  shoubujiaofeirenyuan sfry,shoulibianhao  RESERVE1, Gcmc Reserve2,    BuJiaoFeiFuKuanFangShi PK_BALATYPE,BuJiaoKanSheFei amount,   (case  BuJiaoFeiXiaoZhangBiaoZHi when 1 then 'Y' when 0 then 'N' end) xzbz,BuJiaoFeiXiaoZhangRiQi xzrq,beizhu remark from bz "
								+ sqlwhere6,
						new BeanListProcessor(BzfyBVO.class));

		// �������ʱ��
		if(list6 != null){
		for (BzfyBVO bzfy : list6) {
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDSW");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				
				NCSfry.put( "0001AA1000000001QS1E" , "��Ω��" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
			    	 }
				 }
			}
			newList.add(bzfy);
		}
		}
		// ��ѯ���㹤�̿�
		String sqlwhere7 = "where baozhuangbianhao is not null and JueSuanGongChengKuan <>'0' and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624'";
		List<BzfyBVO> list7 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						// ������=RESERVE1
						"select shoujuesuankuanrenyuan sfry,shoulibianhao  RESERVE1,  Gcmc Reserve2,    JueSuanKuanFuKuanFangShi PK_BALATYPE, JueSuanGongChengKuan amount,  (case  JueSuanKuanXiaoZhangBiaoZhi when 1 then 'Y' when 0 then 'N' end)  xzbz,JueSuanKuanXiaoZhangRiQi xzrq,beizhu remark from bz "
								+ sqlwhere7,
						new BeanListProcessor(BzfyBVO.class));

		// �������ʱ��
		if(list7 !=null ) {
		for (BzfyBVO bzfy : list7) {
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDSX");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				
				NCSfry.put( "0001AA1000000001QS1E" , "��Ω��" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
				 }
				}
			}
			newList.add(bzfy);
		}
	}
		System.out.println("�ӱ�ĳ��ȣ�"+ newList.size());
		//��ʼ����������map
        BaseDataUtil.init();
		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (newList != null) {
			for (BzfyBVO bzfy : newList) {
				bzfy.setDr(0);
				//���ʽ
				if(bzfy.getPk_balatype() != null){
					Map<String,String> NCfkfs = new HashMap<String,String>();
					NCfkfs.put("7", "0001AA1000000000KDSS");
					NCfkfs.put("����", "0001A31000000000QATL");//����
					NCfkfs.put("�ֽ�", "0001A310000000001BF1");//�ֽ�
					NCfkfs.put("����", "0001A31000000000QATK");//����
					
					NCfkfs.put("ת��", "0001A310000000001BF3"); //ת�ˣ�ԭ��ҵ������
				//	NCfkfs.put("4", "0001A31000000000LKCM");//ת�ˣ�����ҵֱ����
					NCfkfs.put("����", "0001A31000000000LM5J"); //���ˣ���Ӧ��ֱ�ӳ����ˣ�
					for( Entry<String, String>  entry: NCfkfs.entrySet()){
					//	System.out.println(entry.getKey());
					 if(bzfy.getPk_balatype().replace(" ", "").equals(entry.getKey().trim())){
						 bzfy.setPk_balatype(entry.getValue());
					 }
					}
				}
				//���뱨װ��Ϣ��������װ������������
				//String	sqlwhere = " where  a.slbh = ";
				String bzxx =  (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select a.pk_bzxx from lcsw_bzxx a where  a.slbh = "
						+"'"+bzfy.getReserve1()+"'",  new ColumnProcessor(null));//������
				bzfy.setPk_bzxx(bzxx);
				String bzfypk = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select a.pk_bzfy_h from lcsw_bzfy_h a where a.Reserve1 = "
						+"'"+bzfy.getReserve2()+"'", new ColumnProcessor(null));//��������
				bzfy.setPk_bzfy_h(bzfypk);
					
			}
			InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		}	
		
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(newList);
		System.out.println("�����ӱ�����newList��"+newList.size()+" ��");
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
