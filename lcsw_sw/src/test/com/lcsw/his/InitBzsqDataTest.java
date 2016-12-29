package com.lcsw.his;

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
import nc.vo.lcsw.sw02.BzsqVOCopy;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
//先执行 delete from lcsw_bzsq
public class InitBzsqDataTest {
	
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
		//initJobData();
		//
		initFinishedData();

		long resetTime = System.currentTimeMillis();
		System.out
				.println("获取结果集成功，耗时：" + (resetTime - conntime) / 1000 + "秒；");
	}

	

	@SuppressWarnings({ "unchecked", "unused", "static-access" })
	public static void initFinishedData() throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource(BZDS);
		//入户编号非空
		String sqlwhere = "where  BaoZhuangbianhao is not null and baozhuangbianhao >='2015000084' and baozhuangbianhao <='2015001624' order by gcmc";
		List<BzsqVO> list = (List<BzsqVO>) NCLocator
				.getInstance()
				.lookup(IUAPQueryBS.class)
				.executeQuery(
						"select KanChaSheJiFei kcsjf,kaihuhang khyh,zhanghao yhzh, GCMC pk_jobbasfil, BaoZhuangBianHao bzbh, ShouLiBianHao slbh, Xieyibianhao xybh, RuHuBianHao rhbh, HuMing hm, BaoZhuangRiQi bzrq, (case ErQiGongChengBiaoZhi when 1 then 'Y' when 0 then 'N' end) eqgcbz, LianXiDiZhi lxdz, LianXiRen lxr, LianXiDianHua lxdd, AnZhuangDiZhi azdz, YongShuiXingZhi ysxz, YuanGuanJing ygj, ShiWaiKouJing swkj, ShiWaiGuanJing swgj, ShiNeiKouJing snkj, ShiNeiGuanJing sngj, YongShuiRenShu yshs, ZuiGaoCengShu zgcs, JianZhuMianJi jzmj, YongShuiRenShu ysrs, ZuiDaShuiLiang zdsl, GongChengXingZhi gcxz, JieShenQingBiaoRiQi jsqbrq, (case KanChaXuKe when 1 then 1 when 0 then 0 end)   kcxk, KanChaRiQi kcrq, BaoZhuangJingBanRenYuan jbry, jianmianleixing jmlx, zonglianxifangshi zlxfs, shejibiaozhi sjbs, FuKuanFangShi fkfs, ZhangHao yhzh, KaiHuHang khyh, KanChaRenYuan kcry, KanChaFanKuiRiQi kcfkrq, kanchashejirenyuan kcsjry, YiJiaoSheJiRiQi yjsjrq, YiJiaoHuaHongXianRiQi yjhhxrq, JieHongXianTuRiQi jhxtrq, YiJiaoSheJiPingShenRiQi yjsjpsrq, SheJiPingShenWanChengRiQi sjpswcrq, SheJiDanWei sjdw, GuanChang gc, ShiWaiShiGongDanWei swsgdw, ShuiBiaoGeShu sbgs, ShiNeiShiGongDanWei snsgdw, ShiGongTianShu sgts, YiJiaoAnZhuangRiQi yjazrq, AnZhuangRiQi azrq, AnZhuangFanKuiRiQi azfkrq, JunGongRiQi jgrq, hetonghao hth, (case ziliaofankuibiaozhi when 1 then 'Y' when 0 then 'N' end) zlfkrq, ZhuangBiaoRiQi zbrq, YanShouRiQi ysrq, YiJiaoYanShouRiQi yjysrq, BiaoMa biaoma, ChanDi chandi, BianMa bianma, BiaoBie biaobie, BiaoWei biaowei, (case YanShouHeGeBiaoZhi when 1 then 1 when 0 then 1 end) yshgbz, YiJiaoZhengGaiRiQi yjzgrq, yanshouxingdu ysxd, ZhengGaiRiQi zgrq,(case ZhengGaiHeGeBiaoZhi when 1 then 1 when 0 then 0 end) zghgbz, BeiZhu remark from bz " 
	    				+ sqlwhere
						,new BeanListProcessor(BzsqVO.class));
		System.out.println(list.size());
		
		//初始化基础数据map
		BaseDataUtil.init();

		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		if (list != null) {
			for (BzsqVO Bzsq : list) {
				//默认设置为不是三优先用户
				UFBoolean sfsyxN = new UFBoolean('N');
				Bzsq.setSfsyx(sfsyxN);
				//单据类型
				Bzsq.setPk_billtype("SW02");
				//报装状态
				Bzsq.setBzzt(1);
				//营业区域-南宁
				Bzsq.setYyqy("南宁");
				Bzsq.setPk_corp("1003");
				//用水户数、用水人数默认=1
				Bzsq.setDr(0);
				Bzsq.setYshs(1);
				Bzsq.setYsrs(1);
				Bzsq.setVbillstatus(1);
				//经办人员
				Map<String,String> NCjbry = new HashMap<String,String>();
				NCjbry.put("李娟", "0001F51000000000SL11");
				NCjbry.put("唐学艳", "0001AA1000000000TPG6");
				NCjbry.put("吴捷凯", "0001AA1000000000TPG7");
				NCjbry.put("梁丽云", "0001AA1000000000JB8N");
				NCjbry.put("吴波", "0001AA1000000000TPG3");
				NCjbry.put("欧玲", "0001AA1000000000TPG4");
				NCjbry.put("张晶", "0001AA1000000000TPG5");
				NCjbry.put("陈秋玲", "0001AA1000000000JB8O");
				NCjbry.put("黄丽冰", "0001F51000000000OLUT");
				NCjbry.put("梁静", "0001A31000000000QB1J");
				
				for( Entry<String, String>  entry: NCjbry.entrySet()){
					if(Bzsq.getJbry() != null ){
				 if(Bzsq.getJbry().equals(entry.getKey())){
					 Bzsq.setJbry(entry.getValue().toString());
				 }
				}
				}
				//开户行
				if(Bzsq.getKhyh() != null){
					HashMap<String, String> NCKfyh = new HashMap<String,String>();
					//中国工商银行
					NCKfyh.put("000", "0001ZZ1000000001OCUC");NCKfyh.put("001", "0001ZZ1000000001OCUC");NCKfyh.put("002", "0001ZZ1000000001OCUC");NCKfyh.put("006", "0001ZZ1000000001OCUC");NCKfyh.put("009", "0001ZZ1000000001OCUC");//中国工商银行
					//中国建设银行
					NCKfyh.put("105", "0001ZZ1000000001OCUD");	NCKfyh.put("AAA", "0001ZZ1000000001OCUD");NCKfyh.put("107", "0001ZZ1000000001OCUD");NCKfyh.put("125", "0001ZZ1000000001OCUD");	NCKfyh.put("131", "0001ZZ1000000001OCUD");NCKfyh.put("179", "0001ZZ1000000001OCUD");NCKfyh.put("179", "0001ZZ1000000001OCUD");
					//交通银行
					NCKfyh.put("267", "0001ZZ1000000001WIUD");NCKfyh.put("BBB", "0001ZZ1000000001WIUD");
					//北部湾银行
					NCKfyh.put("400", "0001A31000000000JYJW");NCKfyh.put("401", "0001A31000000000JYJW");NCKfyh.put("408", "0001A31000000000JYJW");NCKfyh.put("432", "0001A31000000000JYJW");
					//农业银行
					NCKfyh.put("600", "0001ZZ1000000001WIUA");NCKfyh.put("649", "0001ZZ1000000001WIUA");
					//中国银行
					NCKfyh.put("900", "0001ZZ1000000001WIUB");NCKfyh.put("EEE", "0001ZZ1000000001WIUB");
					NCKfyh.put("CCC", "0001ZZ1000000001WIU9");//光大银行
					NCKfyh.put("DDD", "0001ZZ1000000001WIUA");//农业银行
					for( Entry<String, String>  entry: NCKfyh.entrySet()){
						 if(Bzsq.getKhyh().equals(entry.getKey())){
							 Bzsq.setKhyh(entry.getValue());
						  }
							}
					}
				
				//付款方式
			//	Bzsq.setFkfs("0001A310000000001BF1");
				if(Bzsq.getFkfs() != null){
				Map<String,String> NCfkfs = new HashMap<String,String>();
				NCfkfs.put("2", "0001AA1000000000KDSS");//托收
				NCfkfs.put("3", "0001A31000000000QATL");//代扣
				NCfkfs.put("1", "0001A310000000001BF1");//现金
			//	NCfkfs.put("7", "0001A310000000001BF2");//现金支票
				NCfkfs.put("4", "0001A310000000001BF3"); //转账（原企业网银）
			//	NCfkfs.put("4", "0001A31000000000LKCM");//转账（新企业直连）
			//	NCfkfs.put("5", "0001A31000000000LM5J"); //冲账（供应链直接冲总账）
				for( Entry<String, String>  entry: NCfkfs.entrySet()){
				//	System.out.println(entry.getKey());
				 if(Bzsq.getFkfs().equals(entry.getKey())){
					 Bzsq.setFkfs(entry.getValue());
				 }
				}
				}
				//用水性质
				if(Bzsq.getYsxz() != null){
					Map<String,String> NCfkfs = new HashMap<String,String>();
					NCfkfs.put("1001", "补收差价");
					NCfkfs.put("1101", "户表");
					NCfkfs.put("1102", "免污户表");
					NCfkfs.put("1103", "优惠户表");
					NCfkfs.put("1201", "单元表"); 
					NCfkfs.put("1301", "集体表");
					NCfkfs.put("1401", "免代收生活");
					NCfkfs.put("1402", "生活原价");
					NCfkfs.put("1403", "五保户");
					NCfkfs.put("1405", "邕宁生活原");
					NCfkfs.put("1501", "免污水生活");
					NCfkfs.put("1502", "批发用水"); 
					NCfkfs.put("1601", "本公司用水");
					NCfkfs.put("1701", "二阶梯用水");
					NCfkfs.put("1702", "优惠二阶梯");
					NCfkfs.put("1703", "三阶梯用水");
					NCfkfs.put("1704", "优惠三阶梯");
					NCfkfs.put("1801", "低保户用水");
					NCfkfs.put("1901", "优惠生活");
					NCfkfs.put("1A01", "学校用水");
					NCfkfs.put("2101", "政府机关");
					NCfkfs.put("2102", "政府驻邕处");
					NCfkfs.put("2103", "团体.公司");
					NCfkfs.put("2104", "科研单位");
					NCfkfs.put("2105", "文化体育");
					NCfkfs.put("2106", "新闻出版");
					NCfkfs.put("2107", "图片社");
					NCfkfs.put("2108", "机关其他");
					NCfkfs.put("2109", "村居委会");
					NCfkfs.put("2201", "大专院校");
					NCfkfs.put("2202", "中专");
					NCfkfs.put("2203", "技工学校");
					NCfkfs.put("2204", "中学");
					NCfkfs.put("2205", "小学");
					NCfkfs.put("2205", "小学");
					NCfkfs.put("2206", "幼儿园");
					NCfkfs.put("2207", "职工学校");
					NCfkfs.put("2208", "学校其他");
					NCfkfs.put("2209", "社会办学1");
					NCfkfs.put("2301", "部队办公");
					NCfkfs.put("2303", "部队其他");
					NCfkfs.put("2302", "营房用水");
					NCfkfs.put("2402", "环卫");
					NCfkfs.put("2502", "消防");
					NCfkfs.put("2503", "公园");
					NCfkfs.put("2504", "医院医疗");
					NCfkfs.put("2505", "公益其他");
					NCfkfs.put("2601", "公用事业水");
					NCfkfs.put("2701", "免污水行政");
					NCfkfs.put("2702", "优惠行政");
					NCfkfs.put("3101", "机械冶金");
					NCfkfs.put("3102", "电子");
					NCfkfs.put("3103", "化工");
					NCfkfs.put("3104", "纺织纤维");
					NCfkfs.put("3105", "食品糖烟");
					NCfkfs.put("3106", "种养业");
					NCfkfs.put("3107", "农产品加工");
					NCfkfs.put("3107", "农产品加工");
					NCfkfs.put("3108", "工业其他");
					NCfkfs.put("3109", "部队生产");
					NCfkfs.put("3110", "机关生产");
					NCfkfs.put("3111", "学校生产");
					NCfkfs.put("3112", "免污水工业");
					NCfkfs.put("3113", "工业仓储");
					NCfkfs.put("3114", "木材加工");
					NCfkfs.put("3115", "制造业");
					NCfkfs.put("3201", "低质工业水");
					NCfkfs.put("3301", "食品原水");
					NCfkfs.put("3302", "原水");
					NCfkfs.put("3401", "南湖补水");
					NCfkfs.put("3501", "工业原价");
					NCfkfs.put("3502", "免污工业原");
					NCfkfs.put("3503", "邕宁工业原");
					NCfkfs.put("4101", "商店.商场");
					NCfkfs.put("4102", "粮店");
					NCfkfs.put("4103", "仓储");
					NCfkfs.put("4104", "贸易中心");
					NCfkfs.put("4105", "机关营业");
					NCfkfs.put("4106", "部队营业");
					NCfkfs.put("4107", "学校营业");
					NCfkfs.put("4108", "副食品加工");
					NCfkfs.put("4109", "商业其他");
					NCfkfs.put("4110", "市场");
					NCfkfs.put("4201", "电影院");
					NCfkfs.put("4202", "照相馆");
					NCfkfs.put("4203", "游泳池");
					NCfkfs.put("4204", "营利医院");
					NCfkfs.put("4205", "文化其他");
					NCfkfs.put("4301", "邮电");
					NCfkfs.put("4302", "通讯");
					NCfkfs.put("4303", "银行");
					NCfkfs.put("4304", "证券");
					NCfkfs.put("4305", "保险");
					NCfkfs.put("4306", "信托");
					NCfkfs.put("4307", "金融其他");
					NCfkfs.put("4401", "宾馆");
					NCfkfs.put("4402", "饭店");
					NCfkfs.put("4402", "饭店");
					NCfkfs.put("4403", "招待所");
					NCfkfs.put("4404", "旅社");
					NCfkfs.put("4405", "客栈");
					NCfkfs.put("4406", "饮食业");
					NCfkfs.put("4408", "洗衣店");
					NCfkfs.put("4409", "茶座");
					NCfkfs.put("4411", "餐厅.酒楼");
					NCfkfs.put("4413", "健身");
					NCfkfs.put("4414", "娱乐场所");
					NCfkfs.put("4415", "交通运输业");
					NCfkfs.put("4417", "营业");
					NCfkfs.put("4418", "免污水营业");
					NCfkfs.put("4419", "修理业");
					NCfkfs.put("4501", "优惠行业1");
					NCfkfs.put("4502", "营业原价");
					NCfkfs.put("4503", "邕宁营业原");
					NCfkfs.put("4504", "优惠营业2");
					NCfkfs.put("4601", "美容美发");
					NCfkfs.put("4602", "歌舞厅");
					NCfkfs.put("4603", "桑拿浴足");
					NCfkfs.put("4604", "洗车业");
					NCfkfs.put("4607", "免污水特种");
					NCfkfs.put("4608", "邕宁特种原");
					NCfkfs.put("4609", "特种原价");
					NCfkfs.put("4701", "优惠宾馆");
					NCfkfs.put("4701", "优惠宾馆");
					NCfkfs.put("4801", "半污营业");
					NCfkfs.put("5101", "住宅施工");
					NCfkfs.put("5102", "单位施工");
					NCfkfs.put("5103", "施工其他");
					NCfkfs.put("5201", "房地产建筑");
					NCfkfs.put("5203", "房地产其他");
					NCfkfs.put("5204", "免污水建筑");
					NCfkfs.put("5205", "免代收建筑");
					NCfkfs.put("5205", "免代收建筑");
					NCfkfs.put("5206", "道路施工");
					NCfkfs.put("5301", "建筑原价");
					NCfkfs.put("5302", "免污建筑原");
					NCfkfs.put("5303", "邕宁建筑原");
					NCfkfs.put("6101", "自备水源");
					NCfkfs.put("6102", "停接水");
					NCfkfs.put("6103", "赔偿损失水");
					NCfkfs.put("6104", "优惠水");
					NCfkfs.put("6105", "优惠代生活");
					for( Entry<String, String>  entry: NCfkfs.entrySet()){
					//	System.out.println(entry.getKey());
				
					 if(Bzsq.getYsxz().equals(entry.getKey())){
						 Bzsq.setYsxz(entry.getValue());
					  }
						}
					}
				
				//项目档案
				Bzsq.setPk_jobbasfil(BaseDataUtil.jobMap.get(Bzsq
						.getPk_jobbasfil()));
				
				//设置制单人 
				Bzsq.setVoperatorid("0001A1100000000001M2");
				//报装状态
				if( Bzsq.getSlbh() != null && Bzsq.getKcry() == null ){
					Bzsq.setBzzt(1);//提交申请
//					if(Bzsq.getKcsjf() != null) {        //已交勘察设计费 ，单据已过审核
//					Bzsq.setVbillstatus(4);
//					}
//					Bzsq.setVbillstatus(1);
				}
				else if( Bzsq.getKcry() != null && Bzsq.getKcfkrq() == null){
					Bzsq.setBzzt(2);//勘察派工
				}
				else if( Bzsq.getKcfkrq() != null && Bzsq.getSjbs().equals( new UFBoolean( "N"))  ){
					Bzsq.setBzzt(3);//勘察成功
				}
				else if( Bzsq.getSjbs().equals(new UFBoolean("Y")) &&  Bzsq.getSwsgdw() == null){
					Bzsq.setBzzt(4);//设计成功
				}
				else if(Bzsq.getSwsgdw() !=null && Bzsq.getAzfkrq() == null){
					Bzsq.setBzzt(5);//派工中
				}
				else if (Bzsq.getAzfkrq() != null && Bzsq.getYsrq() == null){
					Bzsq.setBzzt(6);//安装完成
				}
				else if ( Bzsq.getYsrq() != null && Bzsq.getYshgbz().equals( new Integer(1)) && Bzsq.getRhbh() ==null){
					Bzsq.setBzzt(7);//验收合格
				}
				else if( Bzsq.getYsrq() != null && Bzsq.getYshgbz().equals( new Integer(0))){
					Bzsq.setBzzt(8);//验收不合格
				}else if( Bzsq.getRhbh() != null ){
					Bzsq.setBzzt(9);//已入户
				}				
			}
			System.out.println("list长度为："+list.size());
		}
		//NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(list);
		//去掉重复工程项目
		//入户编号非空
//		List<BzsqVO> listMiddle = (List<BzsqVO>) NCLocator
//				.getInstance()
//				.lookup(IUAPQueryBS.class)
//				.executeQuery(
//						"select * from lcsw_bzsqCopy " 
//						,new BeanListProcessor(BzsqVO.class));
		List<BzsqVO> listDistinct = new ArrayList<BzsqVO>();//新的对象数组装去重后的容器
	//	BzsqVO DistictGc = new BzsqVO();
		String gcxm = null;//工程项目
		Integer hushu=0;//用水户数
		int count = 0;
		 BzsqVO DistinctGc =new BzsqVO ();
		if (list != null ) {
			for (BzsqVO Bzsq : list) {
				count=count+1;
				if(Bzsq.getPk_jobbasfil() != null){//项目不为空
				if (gcxm ==null ){				
					gcxm = Bzsq.getPk_jobbasfil();
			        DistinctGc = (BzsqVO) Bzsq.clone();//克隆
			        hushu=1;	 	    						
	 	    	}else if( gcxm.equals(Bzsq.getPk_jobbasfil()) ) {
	 	    		hushu=hushu+1;
	 	    	} else  {
	 	    		DistinctGc.setYshs(hushu);
	 	    		listDistinct.add(DistinctGc);
	 	    		gcxm = Bzsq.getPk_jobbasfil();  
	 	    		hushu=1;
	 	    		DistinctGc = (BzsqVO) Bzsq.clone();
	 	    		if(count==list.size()){ //最后一个加入容器中
	 		    		  listDistinct.add(DistinctGc);
	 	    		}
	 	    	}
	    	  } 
				else{
	    		  listDistinct.add(Bzsq);
	    	  }
			}	
			System.out.println("listDistinct长度为:"+listDistinct.size());
		}
		NCLocator.getInstance().lookup(IVOPersistence.class).insertVOList(listDistinct);
		
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
