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

	

	@SuppressWarnings({ "unchecked" })
	public static void initFinishedData() throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
		//判断 设计人员不空 RESERVE1保存工程名称
		String sqlwhere1 = "where  gcmc is not null and baozhuangbianhao is not null and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' order by gcmc";
		List<BzfyHVO> list1 = (List<BzfyHVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(  
						"select gcmc gcmc, Gcmc RESERVE1, KanChaSheJiFei kcsjf,YongShuiHuShu yshs, YuSuanGongChengKuan ysgck1,YuSuanGongChengKuan2 ysgck2,YuSuanGongChengKuan3 ysgck3,BuJiaoKanSheFei bjkcf,JueSuanGongChengKuan jsgck, ShouLiBianHao slbh,Xieyibianhao xybh, LianXiDiZhi lxdz,LianXiRen lxr, FuKuanFangShi fkfs,jianmianleixing jmlx,KaiHuHang khyh,ZhangHao yhzh,LianXiDianHua lxdh,AnZhuangDiZhi azdz,GongChengXingZhi gcxz,YongShuiXingZhi ysxz,YuanGuanJing ygj,ShiNeiKouJing snkj,ShiWaiKouJing swkj,ShiNeiGuanJing sngj, ShiWaiGuanJing  swgj,ZuiGaoCengShu zgcs,JianZhuMianJi jzmj,YongShuiRenShu ysrs,ZuiDaShuiLiang zdsl,BaoZhuangJingBanRenYuan jbry,zonglianxifangshi zlxfs, (case SheJiBiaoZhi when 1 then 'Y' when 0 then 'N' end) sjbs,KanChaXuKe kcxk,BeiZhu remark from bz "
						+ sqlwhere1,
						new BeanListProcessor(BzfyHVO.class));
		
	
		
		//初始化基础数据map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (list1 != null) {
			for (BzfyHVO Kcpg1 : list1) {
				//单据类型
				Kcpg1.setPk_billtype("SW10");
		     
				Kcpg1.setPk_corp("1003");
			 
				Kcpg1.setDr(0);
			    //单据状态
				Kcpg1.setVbillstatus(8);
				//项目档案
				Kcpg1.setReserve1(BaseDataUtil.jobMap.get(Kcpg1
						.getReserve1()));
				Kcpg1.setGcmc(BaseDataUtil.jobMap.get(Kcpg1
						.getGcmc()));
				//报装申请pk
				String PkBzsq = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class)
						        .executeQuery("select  b.pk_bzsq from lcsw_bzsq b where b.pk_jobbasfil ="+"'"+ Kcpg1.getReserve1()+"'"
						        		, new ColumnProcessor(null));
		//		System.out.println(PkBzsq);
				Kcpg1.setPk_bzsq(PkBzsq);			   
			}
			System.out.println("查询出未去重项目数据list条数："+list1.size());
			//去掉重复项目
			List<BzfyHVO> listDistinct = new ArrayList<BzfyHVO>();//新的对象数组装去重后的容器
			Integer hushu=0;//用水户数
			String gcxm = null;//工程项目
			BzfyHVO DistinctGc =new BzfyHVO();
			int count = 0;
			UFDouble bjkcsjf0 = new UFDouble(new Double(0));
				for (BzfyHVO Bzfy : list1)  {
					//去掉补交勘设费用为0的
					if(Bzfy.getBjkcf() != null && Bzfy.getBjkcf().equals(bjkcsjf0)){
						Bzfy.setBjkcf(null);
					}
					//去掉勘设费用为0的
					if(Bzfy.getKcsjf() != null && Bzfy.getKcsjf() .equals(bjkcsjf0)){
						Bzfy.setKcsjf(null);
					}
					//去掉预算工程款费用为0的
					if(Bzfy.getYsgck1() !=null && Bzfy.getYsgck1().equals(bjkcsjf0)){
						Bzfy.setYsgck1(null);
					}
					//去掉预算工程款费2用为0的
					if(Bzfy.getYsgck2() != null && Bzfy.getYsgck2().equals(bjkcsjf0)){
						Bzfy.setYsgck2(null);
					}
					//去掉预算工程款费3用为0的
					if(Bzfy.getYsgck3() !=null && Bzfy.getYsgck3().equals(bjkcsjf0)){
						Bzfy.setYsgck3(null);
					}
					//去掉决算工程款用为0的
					if(Bzfy.getJsgck() !=null && Bzfy.getJsgck().equals(bjkcsjf0)){
						Bzfy.setJsgck(null);
					}
					count=count+1;
					if(Bzfy.getReserve1() != null){//项目不为空
					if (gcxm ==null ){				
						gcxm = Bzfy.getReserve1();
				        DistinctGc = (BzfyHVO) Bzfy.clone();//克隆
				        hushu=1;	 	    						
		 	    	}else if( gcxm.equals(Bzfy.getReserve1()) ) {
		 	    		hushu=hushu+1;
		 	    	} else  {
		 	    		DistinctGc.setYshs(hushu);
		 	    		listDistinct.add(DistinctGc);
		 	    		gcxm = Bzfy.getReserve1();  
		 	    		hushu=1;
		 	    		DistinctGc = (BzfyHVO) Bzfy.clone();
		 	    		if(count==list1.size()){ //最后一个加入容器中
		 		    		  listDistinct.add(DistinctGc);
		 	    		}
		 	    	}
		    	  } 
					else{
		    		  listDistinct.add(Bzfy);
		    	  }
					
				}	
				
				NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(listDistinct);
				System.out.println("插入主表数据listDistinct条数："+listDistinct.size());
		}
		
		
		
		//临时报装费用子表列
		List<BzfyBVO> newList = new ArrayList<BzfyBVO>();
		//查询勘察设计费
		String sqlwhere2 = " where baozhuangbianhao is not null and KanChaSheJiFei <>'0' and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' " ;
		InvocationInfoProxy.getInstance().setUserDataSource(BZDS);

		List<BzfyBVO> list2 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(//受理编号=RESERVE1
				"select shoukanshefeirenyuan sfry, shoulibianhao  RESERVE1 ,Gcmc Reserve2,KanSheFeiFuKuanFangShi PK_BALATYPE, KanChaSheJiFei amount, (case KanSheFeiXiaoZhangBiaoZhi when 1 then 'Y' when 0 then 'N' end) xzbz, KanSheFeiXiaoZhangRiQi xzrq,beizhu remark from bz "
				+sqlwhere2
				,new BeanListProcessor(BzfyBVO.class));
		//添加至临时列
		if(list2 !=null ){
		for(BzfyBVO bzfy:list2){
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDSS");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				NCSfry.put( "0001AA1000000001QS1E" , "郝惟玮" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
				  }
				}
		   }
			newList.add(bzfy);
		 }
		}
		// 查询预算工程款
		String sqlwhere3 = "where baozhuangbianhao is not null and YuSuanGongChengKuan <>'0'  and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624' ";
		List<BzfyBVO> list3 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						// 受理编号=RESERVE1
						"select shouyusuankuanrenyuan sfry,shoulibianhao  RESERVE1, Gcmc Reserve2,   YuSuanKuanFuKuanFangShi PK_BALATYPE,YuSuanGongChengKuan amount,  (case YuSuanKuanXiaoZhangBiaoZhi when 1 then 'Y' when 0 then 'N' end) xzbz,YuSuanKuanXiaoZhangRiQi  xzrq,beizhu remark from bz "
								+ sqlwhere3,
						new BeanListProcessor(BzfyBVO.class));
		//添加至临时列
		if(list3 !=null ){
		for(BzfyBVO bzfy:list3){
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDST");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				
				NCSfry.put( "0001AA1000000001QS1E" , "郝惟玮" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
				  }
				}
			   }
			newList.add(bzfy);
		  }
		}	
		//查询预算工程款2
		String sqlwhere4 = "where baozhuangbianhao is not null and YuSuanGongChengKuan2 <>'0' and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624'";
		List<BzfyBVO> list4 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						// 受理编号=RESERVE1
						"select shouyusuankuanrenyuan2 sfry, shoulibianhao  RESERVE1,Gcmc Reserve2,    YuSuanKuanFuKuanFangShi2 PK_BALATYPE,YuSuanGongChengKuan2 amount,  (case YuSuanKuanXiaoZhangBiaoZhi2 when 1 then 'Y' when 0 then 'N' end)xzbz,YuSuanKuanXiaoZhangRiQi2  xzrq,beizhu remark from bz "
								+ sqlwhere4,
						new BeanListProcessor(BzfyBVO.class));
		
		//添加至临时列
		if(list4 !=null ){
		for(BzfyBVO bzfy:list4){
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDSU");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				
				NCSfry.put( "0001AA1000000001QS1E" , "郝惟玮" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
				  }
				}
			}
			newList.add(bzfy);
		 }
		}
		// 查询预算工程款3
		String sqlwhere5 = "where baozhuangbianhao is not null and YuSuanGongChengKuan3 <>'0' and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624'";
		List<BzfyBVO> list5 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						// 受理编号=RESERVE1
						"select shouyusuankuanrenyuan3 sfry,shoulibianhao  RESERVE1,Gcmc Reserve2,    YuSuanKuanFuKuanFangShi3 PK_BALATYPE,YuSuanGongChengKuan3 amount,  (case YuSuanKuanXiaoZhangBiaoZhi3 when 1 then 'Y' when 0 then 'N' end)xzbz,YuSuanKuanXiaoZhangRiQi3  xzrq,beizhu remark from bz "
								+ sqlwhere5,
						new BeanListProcessor(BzfyBVO.class));

		// 添加至临时列
		if(list5 != null ){
		for (BzfyBVO bzfy : list5) {
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDSV");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				
				NCSfry.put( "0001AA1000000001QS1E" , "郝惟玮" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
				  }
				}
			}
			newList.add(bzfy);
		}
		}
		// 查询補交勘設費
		String sqlwhere6 = " where baozhuangbianhao is not null and BuJiaoKanSheFei <>'0'  and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624'";
		List<BzfyBVO> list6 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						// 受理编号=RESERVE1
						"select  shoubujiaofeirenyuan sfry,shoulibianhao  RESERVE1, Gcmc Reserve2,    BuJiaoFeiFuKuanFangShi PK_BALATYPE,BuJiaoKanSheFei amount,   (case  BuJiaoFeiXiaoZhangBiaoZHi when 1 then 'Y' when 0 then 'N' end) xzbz,BuJiaoFeiXiaoZhangRiQi xzrq,beizhu remark from bz "
								+ sqlwhere6,
						new BeanListProcessor(BzfyBVO.class));

		// 添加至临时列
		if(list6 != null){
		for (BzfyBVO bzfy : list6) {
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDSW");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				
				NCSfry.put( "0001AA1000000001QS1E" , "郝惟玮" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
			    	 }
				 }
			}
			newList.add(bzfy);
		}
		}
		// 查询决算工程款
		String sqlwhere7 = "where baozhuangbianhao is not null and JueSuanGongChengKuan <>'0' and baozhuangbianhao >= '2015000084' and baozhuangbianhao <='2015001624'";
		List<BzfyBVO> list7 = (List<BzfyBVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						// 受理编号=RESERVE1
						"select shoujuesuankuanrenyuan sfry,shoulibianhao  RESERVE1,  Gcmc Reserve2,    JueSuanKuanFuKuanFangShi PK_BALATYPE, JueSuanGongChengKuan amount,  (case  JueSuanKuanXiaoZhangBiaoZhi when 1 then 'Y' when 0 then 'N' end)  xzbz,JueSuanKuanXiaoZhangRiQi xzrq,beizhu remark from bz "
								+ sqlwhere7,
						new BeanListProcessor(BzfyBVO.class));

		// 添加至临时列
		if(list7 !=null ) {
		for (BzfyBVO bzfy : list7) {
			bzfy.setReserve2(BaseDataUtil.jobMap.get(bzfy
					.getReserve2()));
			bzfy.setPk_costsubj("0001AA1000000000KDSX");
			if(bzfy.getSfry() !=null){
				Map<String,String> NCSfry = new HashMap();
				
				NCSfry.put( "0001AA1000000001QS1E" , "郝惟玮" );
				for(Entry<String, String>  entry: NCSfry.entrySet()){
				if(bzfy.getSfry().equals(entry.getValue())){
					bzfy.setSfry(entry.getKey());
				 }
				}
			}
			newList.add(bzfy);
		}
	}
		System.out.println("子表的长度："+ newList.size());
		//初始化基础数据map
        BaseDataUtil.init();
		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (newList != null) {
			for (BzfyBVO bzfy : newList) {
				bzfy.setDr(0);
				//付款方式
				if(bzfy.getPk_balatype() != null){
					Map<String,String> NCfkfs = new HashMap<String,String>();
					NCfkfs.put("7", "0001AA1000000000KDSS");
					NCfkfs.put("托收", "0001A31000000000QATL");//托收
					NCfkfs.put("现金", "0001A310000000001BF1");//现金
					NCfkfs.put("代扣", "0001A31000000000QATK");//代扣
					
					NCfkfs.put("转账", "0001A310000000001BF3"); //转账（原企业网银）
				//	NCfkfs.put("4", "0001A31000000000LKCM");//转账（新企业直连）
					NCfkfs.put("冲账", "0001A31000000000LM5J"); //冲账（供应链直接冲总账）
					for( Entry<String, String>  entry: NCfkfs.entrySet()){
					//	System.out.println(entry.getKey());
					 if(bzfy.getPk_balatype().replace(" ", "").equals(entry.getKey().trim())){
						 bzfy.setPk_balatype(entry.getValue());
					 }
					}
				}
				//插入报装信息主键，报装费用主表主键
				//String	sqlwhere = " where  a.slbh = ";
				String bzxx =  (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select a.pk_bzxx from lcsw_bzxx a where  a.slbh = "
						+"'"+bzfy.getReserve1()+"'",  new ColumnProcessor(null));//受理编号
				bzfy.setPk_bzxx(bzxx);
				String bzfypk = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select a.pk_bzfy_h from lcsw_bzfy_h a where a.Reserve1 = "
						+"'"+bzfy.getReserve2()+"'", new ColumnProcessor(null));//工程名称
				bzfy.setPk_bzfy_h(bzfypk);
					
			}
			InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		}	
		
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(newList);
		System.out.println("插入子表数据newList："+newList.size()+" 条");
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
