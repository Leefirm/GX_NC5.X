package nc.bs.lcsw.pub;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.trade.business.HYPubBO;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.JdbcPersistenceManager;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.MapProcessor;

import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**insert
 * 验证合格用户信息
 * 从NC系统筛选合格用户插入营销系统，再返写入户编号和入户日期到NC系统
 * 后台预警任务
 * @author crf
 *
 */
@SuppressWarnings("unchecked")
public class AutoSenduserInfo implements IBackgroundWorkPlugin{
	//中间表数据
	private HashMap<String, LcswMiddleVO> midMap = new HashMap<String, LcswMiddleVO>();
	private String message = "";
	private HYPubBO bo;

	public HYPubBO getHYPubBo() {
		if(bo == null)
			bo = new HYPubBO();
		return bo;
	}
	
	// 主任务
	public String executeTask(BgWorkingContext bgwc) throws BusinessException {
		String reMsg = null;
		try {
			Logger.debug("------------------校验验证合格用户---------------------");
			reMsg = checkPass();
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			reMsg = e.getMessage();
		}
		return reMsg;
	}
	
	/**
	 * 校验测试通过用户
	 * @return
	 * @throws BusinessException 
	 * @throws DbException 
	 * @throws Exception 
	 */
	private String checkPass() throws Exception {
		String source = "lcsw";//数据源(需配置营销系统的数据源)insert
		/* 得到验证合格用户 */
		LcswBzxxHVO[] bzxx = (LcswBzxxHVO[]) getHYPubBo().queryByCondition(LcswBzxxHVO.class, getCheckpassSql());
		if(bzxx == null || bzxx.length ==0){
			return "不存在验收合格用户!!!";
		}else{
			//存在，则往中间表zz5_da_bzdrzjb插入数据
			Logger.debug("------------------往中间表插入合格用户入户数据---------------------");
			
			message = splitInsertVos(source);
			//如存在验收合格用户，则远程调用接口，传输用户信息到营销系统
			Logger.debug("------------------读取营销系统入户数据---------------------");
			//更新报装系统入户数据
			readSysMiddle(source);
			Logger.debug("------------------完成新用户入户---------------------");
			
		}
		return message;
	}
		
	/**
	 * 解析营销系统以及报装系统新用户入户数据，并更新报装系统入户数据
	 * @throws BusinessException insert
	 * @throws Exception 
	 */
	private void readSysMiddle(String source) throws Exception {
		if (!midMap.isEmpty()) {
			String strWhere = getClauseByMap4mid(midMap);//得到新入户用户查询条件
			if (strWhere != null && strWhere.length() > 0) {
//				// 报装信息
//				LcswBzxxHVO[] bzxx = (LcswBzxxHVO[]) getHYPubBo().queryByCondition(
//						LcswBzxxHVO.class, getPass_user(strWhere, true)); //报装编号 命名规则有 bz20160001232  2016090090 截取会出问题
				
				LcswBzxxHVO[] bzxx = (LcswBzxxHVO[]) getHYPubBo().queryByCondition(LcswBzxxHVO.class, getCheckpassSql());
				// 营销系统信息
				strWhere = getClauseByMap4mid(midMap);//得到新入户用户查询条件
				LcswMiddleVO[] midvos = retrieveByClause(source, strWhere);
				//分析两者关系，实现数据更新
				resolveInfo(bzxx, midvos);
			}
		}
	}
	
	/**
	 * 分析报装系统与营销系统新用户入户数据
	 * 回写报装系统入户信息：
	 * @param bzxx
	 * @param midvos
	 * @throws BusinessException 
	 */
	private HashMap<String, LcswBzxxHVO> lcsw_bzxx = new HashMap<String, LcswBzxxHVO>();
	private void resolveInfo(LcswBzxxHVO[] bzxx, LcswMiddleVO[] midvos)
			throws BusinessException {
 		BaseDAO dao = new BaseDAO();
		List<LcswBzxxHVO> bzxx_list = new ArrayList<LcswBzxxHVO>();
//		String insertLength=" "+bzxx.length-2;
		
		spliteBzxx(bzxx);//封装报装系统入户信息
		
		if (midvos != null && midvos.length > 0) {
			for (LcswMiddleVO midvo : midvos) {
				Integer key = midvo.getBzbh();// 报装编号
				Integer rhbh = midvo.getHh();// 入户编号
				if (rhbh != null) {
					Iterator it = lcsw_bzxx.keySet().iterator();
					while (it.hasNext()) {
						String mapkey = (String) it.next();
				
						
						//
//						for (LcswBzxxHVO vo : bzxx)
//						{
//							if (vo.getBzbh().contains(midvo.getAzgcbh())){
//								
//							}
//						}
						String strkey = String.valueOf(key);
						if(strkey.length()==8){
							strkey="20"+strkey;                 //如果是8位前面+20 15000969
						}
						if (mapkey.substring(2,mapkey.length()).equals(strkey.substring(2, strkey.length()))) {
							
							LcswBzxxHVO vo = lcsw_bzxx.get(mapkey);
							/* ***更新验证合格用户，回写其入户编码**** */
							vo.setRhbh(String.valueOf(rhbh));
							//立户日期
							
						
							//立户日期
							vo.setBzzt(9);
							
							vo.setLhrq(new UFDate(new Date()));
							
							//预留字段一，以区分重复扫描
							vo.setReserve1("Y");
							bzxx_list.add(vo);
						}
					}
				}
			}
			if (bzxx_list != null && bzxx_list.size() > 0) {
				dao.updateVOList(bzxx_list);//更新报装系统入户信息
			}
			message = "完成新用户入户数：" + bzxx_list.size();
		}
	
	}
	/**
	 * 封装报装系统入户信息
	 * @param bzxx
	 */
	private void spliteBzxx(LcswBzxxHVO[] bzxx) {
		if (bzxx != null && bzxx.length > 0){
			for (LcswBzxxHVO vo : bzxx) {
				String key = vo.getBzbh();// 报装编号
				if (!lcsw_bzxx.containsKey(key)) {
					lcsw_bzxx.put(key, vo);
				}
			}
		}
	}
	/**
	 * 封装查询条件
	 * @param map insert
	 * @return
	 */
	private String getClauseByMap(HashMap<String, LcswMiddleVO> map) {
		boolean isfirst = true;
		Iterator it =  map.keySet().iterator();
		String strWhere = "";
		while(it.hasNext()){
			String key = (String) it.next();
			if(key.length()==8){
				key="20"+key;                 //如果是8位前面+20 15000969
			}
			if(isfirst){
				strWhere = "'" + key + "'";  //"2015000818"
				isfirst = false;
			}else {
				strWhere = strWhere + ",'" + key + "'";// ,"2015000818"
			}
		}
		if(strWhere != null && strWhere.length() > 0){
			return strWhere;
		}
		return null;
	}
	/**
	 * 封装查询条件
	 * @param map
	 * @return
	 */
	private String getClauseByMap4mid(HashMap<String, LcswMiddleVO> map) {
		boolean isfirst = true;
		Iterator it =  map.keySet().iterator();
		String strWhere = "";
		while(it.hasNext()){
			String key = (String) it.next();
			if(isfirst){
				strWhere = "'" + key + "'";  //"2015000818"
				isfirst = false;
			}else {
				strWhere = strWhere + ",'" + key + "'";// ,"2015000818"
			}
		}
		if(strWhere != null && strWhere.length() > 0){
			return strWhere;
		}
		return null;
	}
	
	/**
	 * 获取符合新用户入户信息
	 * @param source
	 * @return
	 * @throws Exception
	 */
	private LcswMiddleVO[] retrieveByClause(String source,String con) throws Exception {
		/* 访问营销系统数据库 */
		DBManager db = new DBManager(source);
		
		Collection col = (Collection) db.executeQuery("select * from NNMIS.ZZ5_DA_BZDRZJB where " + getPass_user(con,false), new BeanListProcessor(LcswMiddleVO.class));
		
//		Collection col = db.retrieveByClause(LcswMiddleVO.class, getPass_user(con,false));
		if (col == null || col.size() <= 0)
			return null;
		return 
		((LcswMiddleVO[]) col.toArray(new LcswMiddleVO[0]));
	}
	/**
	 * 报装编号查询条件
	 * @param con
	 * @return
	 */
	private String getPass_user(String con,boolean islocal) {
		String strWhere = "";
		if(islocal) {
			//报装系统
			if(con.contains("bz")){
				strWhere = "substr(bzbh,3,length(bzbh)) in (" + con + ") and substr(bzbh,0,2) in ('bz')";
			}else{
				
			
			strWhere = "substr(bzbh,3,length(bzbh)) in (" + con + ") ";//baozhuangbianhao 2015 000818
			}
		}
			else{
			//营销系统
			strWhere = "bzbh in (" + con + ")";
			System.out.println("select * from NNMIS.ZZ5_DA_BZDRZJB where " +strWhere);
		}
		return strWhere;
	}
	
	/**
	 * 整理数据，重新封装中间表vo信息
	 * @param bzxx
	 * @throws DbException 
	 * @throws DbException 
	 */
	private String splitInsertVos(String source) throws DAOException, DbException {
		/* 访问营销系统数据库 */
		DBManager db = new DBManager(source);
		midMap.clear();
		try{
			//从报装信息中获取验证通过用户信息
			LcswMiddleVO[] vos = getLcsw_bzxx();
			if(vos != null && vos.length > 0){
				for (LcswMiddleVO vo : vos) {
					String key = vo.getAzgcbh();//报装编号
					if(!midMap.containsKey(key))
						midMap.put(key, vo);
					String sfzbh=vo.getSfzbh(); 
					if(sfzbh !=null){
					vo.setSfzbh(sfzbh);//身份证编号
					}
					String sgfzr = vo.getAzrxm();
					if (sgfzr !=null){
					vo.setAzrxm(sgfzr);//施工负责人
					}
					Integer yyqy = vo.getYyqy();//营业区域
					Integer swkj = vo.getKj();//室外口径
					Integer rhbh = vo.getRhbh();//入户编号
					String cby = vo.getCby();//首次抄表员
					vo.setSbdayyqy(yyqy);
					/*bch-本册号*/
					if(swkj < 50){
						vo.setBch(yyqy + "8999");
					}else if(swkj >= 50){
						vo.setBch(yyqy + "8998");
					}else{
						vo.setBch(yyqy + "9999");
					}
					/*水表类型*/
					//匹配 水表类型 自定义档案项目  by leefirm
//					String sblx = vo.getSblx();
//					if(sblx != null && (("机械水表--螺翼").equalsIgnoreCase(sblx) || ("螺翼").equalsIgnoreCase(sblx))){
//						vo.setSblx("2");
//					}else if(sblx != null && (("机械水表--旋翼").equalsIgnoreCase(sblx) ||("旋翼").equalsIgnoreCase(sblx))){
//						vo.setSblx("1");
//					}else if(sblx !=null && ("超声波水表").equalsIgnoreCase(sblx) ){
//						vo.setSblx("3");
//					}else if(sblx !=null && ("远传电磁水表").equalsIgnoreCase(sblx)  ){
//						vo.setSblx("4");
//					}else if(sblx !=null && ("IC卡智能水表").equalsIgnoreCase(sblx) ){
//						vo.setSblx("5");
//					}
//					else{
//						vo.setSblx(sblx);
//					}
					
					Map<String,String> map = new HashMap<String,String>();
					map = (HashMap<String, String>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("select docname,doccode from bd_jobbasfil where pk_jobbasfil='0001G41000000006HJ9C'"
							, new MapProcessor() );
					for(Map.Entry<String, String> entry : map.entrySet()){
						if(vo.getSblx().equals(entry.getKey())){
							vo.setSblx(entry.getValue());	
						}else {
							vo.setSblx(vo.getSblx());
						}
					}
					
					
					
					//用水性质
					if(vo.getZysxz()==null){
						vo.setZysxz("1101");
					}
					
					//开户银行
					String yhflc = vo.getYhflc();
					vo.setYhflc(MiddleDataUtil.yhflcMap.get(yhflc));
					//付款方式
					/**
					 * 现金	XJ	若“付款方式”=NULL，则SFLX="现金" 代扣	DK	 托收	TS	 转账	ZZ	
					 */
					String sflx = vo.getSflx();
					if(sflx == null) {
						vo.setSflx("现金");
						sflx=vo.getSflx();
					}
				    if(sflx.indexOf("现金") != -1) {
						vo.setSflx("XJ");
					}
					else if(sflx.indexOf("代扣") != -1) {
						vo.setSflx("DK");
					}
					else if(sflx.indexOf("托收") != -1) {
						vo.setSflx("TS");
					}
					else if(sflx.indexOf("转账") != -1) {
						vo.setSflx("ZZ");
					}
					
					
				    System.out.println("sblx长度；"+vo.getSblx().length()+",");
				}
				//往中间表插入数据
				System.out.println("插入的midvo条数："+midMap.size());
				System.out.println("插入中间表的条数："+vos.length);
				
				db.insertVOs(vos, false);
				message = "插入中间表数据!";
			}
		}catch (Exception e) {
	        e.printStackTrace();
	        message = e.getMessage();
	    }
		return message;
	}	

	/**
	 * 验收合格用户，封装中间表vo
	 * @return
	 * @throws BusinessException
	 */
	private LcswMiddleVO[] getLcsw_bzxx() throws BusinessException{
		LcswMiddleVO[] dataVOs = null;
		PersistenceManager sessionManager = null;
		try{
			sessionManager = JdbcPersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			//验证合格的用户sfzbh  ckxd
			StringBuffer strQuery = new StringBuffer();
			/*bzmc-户名,yyqy--营业区域(1：南宁、3：邕宁、4：东盟)*,出库航渡——上期行至/
			strQuery.append("select hm bzmc,ckxd SQXZ,");
			strQuery.append("case when yyqy='南宁' then 1  when yyqy='邕宁' then 3  when yyqy='东盟' then 4  else 3 end yyqy,");
			/*yhdz-安装地址(azdz),zbwz-表位*/
			strQuery.append("nvl(azdz, biaowei) yhdz,nvl(azdz, biaowei) zbwz,");
			/*zysxz-用水性质 TODO (自定义项-用水性质表(主键0001AA1000000000J85H)) */
			strQuery.append("(select doccode from bd_defdoc where pk_defdoclist = '0001AA1000000000J85H' and trim(doccode)=trim(lcsw_bzxx.ysxz)) zysxz,");
			/*ysxzsl,qcy,cbzq,slqy,lxr-联系人,lxdh-联系电话*/
			strQuery.append("1 ysxzsl,1 qcy,1 cbzq,'' slqy,lxr lxr,lxdd lxdh,");
			/*sfzbh-身份证号,zzsh,kj-室外口径,yhzl,jlsfbz,lhbz,yxfjsl,khh-入户编号,khmc-户名,txdz-联系地址*/
			strQuery.append("'' sfzbh,0 zzsh,to_number(swkj) kj,'' yhzl,0 jlsfbz,1 lhbz,0 yxfjsl,rhbh khh,hm khmc, nvl(lxdz, azdz) txdz,sfzh sfzbh,sgfzr azrxm, ");
			/*khxxlxdh-联系电话, sflx- TODO 付款方式*/
			strQuery.append("lxdd khxxlxdh,");
			//panqh
//			strQuery.append("CASE WHEN fkfs = '0001A310000000001BF1' THEN 'XJ' WHEN fkfs = '0001F51000000000ODXI' THEN 'TS'  ");
//			strQuery.append("WHEN fkfs = '0001A310000000001BF2' THEN 'DK' WHEN fkfs = '0001A310000000001BF3' THEN 'ZZ' ELSE 'XJ' END sflx,");
			strQuery.append(" (select balanname from bd_balatype where pk_balatype=fkfs) sflx,");
			/*ysh,yskye,cjfs,khzt,bsh-表码,sbdakj-室外口径 ,cjid,*/
			strQuery.append("0 ysh,0 yskye,'TZD' cjfs,1 khzt,biaoma bsh,to_number(swkj) sbdakj ,1 cjid,");
			/*sblx-水表类型,xjdsbz,zdds,azbz,sbwzbm*/
			strQuery.append("nvl(substr(biaobie,1,8), '1') sblx,0 xjdsbz,0 zdds,1 azbz,0 sbwzbm,");
			/*sbdayyqy-营业区域,zhwzrq-装表日期 ,ljsyts,ljsysl*/
			strQuery.append("null sbdayyqy,nvl(to_date(zbrq,'yyyy-mm-dd'), getdate()) zhwzrq ,0 ljsyts,0 ljsysl,");
			/*yhzt,scrq-装表日期,ztbgrq-装表日期 */
			strQuery.append("'ZC' yhzt,nvl(to_date(zbrq,'yyyy-mm-dd'), getdate()) scrq,nvl(to_date(zbrq,'yyyy-mm-dd'), getdate()) ztbgrq,");
			/*tqsl,pjsl,yszt,ysztms*/
			strQuery.append("0 tqsl,0 pjsl,'00' yszt,'0正常' ysztms,");
			/*,qsl1,qsl2,qsl3,cbsxh,ljsl,ljqxz,bz1,rwdh*/
			strQuery.append("0 qsl1,0 qsl2,0 qsl3,1 cbsxh,0 ljsl,0 ljqxz,0 bz1,'' rwdh,");
			/*gshth-协议编号 ,ygid,drsj,checkflag,errinfo,yhflc-开户行*/
			strQuery.append("xybh gshth,2 ygid,getdate() drsj,nvl('0', null) checkflag, nvl('', null) errinfo,");
			//panqh
//			strQuery.append(" CASE WHEN rhbh = '000' THEN '1090' WHEN rhbh = 'AAA' THEN '100'  WHEN rhbh = 'BBB' THEN '250' ");
//			strQuery.append(" WHEN rhbh = 'CCC' THEN '804' WHEN rhbh = 'DDD' THEN '600' WHEN rhbh = 'EEE' THEN '900' WHEN rhbh = 'FFF' THEN '095' ELSE rhbh END yhflc ,");
			strQuery.append(" (select banktypename from bd_banktype where pk_banktype=khyh) yhflc ,");
			/*yjdz-联系地址,lhyf-入户月份*/
			strQuery.append("isnull(lxdz, azdz) yjdz,convert(datetime,convert(char(10), to_char(getdate(),'yyyy-mm-dd'),120)) lhyf,");
			/*zbrq-装表日期,yhlx,ljqcbr,ljqcby,*/
			strQuery.append("nvl(to_date(zbrq,'yyyy-mm-dd'), getdate()) zbrq,'01' yhlx,nvl(to_date(zbrq,'yyyy-mm-dd'), getdate()) ljqcbr,");
			strQuery.append("convert(datetime,convert(char(10),nvl(zbrq, to_char(getdate(),'yyyy-mm-dd')),120)) ljqcby,");
			/*sjhm-联系电话,ms1-安装地址,ms2-工程性质,ms3-组织结构代码 */
			strQuery.append("lxdd sjhm,isnull(biaowei, azdz) ms1,gcxz ms2,");
			/*khxxsjhm-联系电话,zh-账号 */
			strQuery.append("lxdd khxxsjhm,yhzh zh,");
			/*azgcbh-报装编号,byms1-验收行度,byms2-验收员,byms3-验收联系电话*/
			strQuery.append("to_number(substr(bzbh,3,length(bzbh))) azgcbh,convert(varchar(8),isnull(ysxd,0)) byms1,ysy byms2,ysdh byms3,");
			/*tsrq,bzbh-报装编号*/
			strQuery.append("nvl(to_date(zbrq,'yyyy-mm-dd'),getdate()) tsrq,to_number(substr(bzbh,3,length(bzbh))) bzbh ");
			strQuery.append("from lcsw_bzxx where "+getCheckpassSql());
			
			List result = (List) session.executeQuery(strQuery.toString(),
					new BeanListProcessor(LcswMiddleVO.class));
			if(result != null && result.size() > 0){
				dataVOs = (LcswMiddleVO[])result.toArray(new LcswMiddleVO[result.size()]);
			}
		}catch (DbException ex) {
			Logger.error(ex.getMessage(), ex);
			throw new BusinessException(ex.getMessage());
		} catch (Exception ex){
			Logger.error(ex.getMessage(),ex);
			throw new BusinessException(ex.getMessage());
		}finally {
			sessionManager.release();
		}
		
		return dataVOs;
	}
	
	/**
	 * 获取验证通过的报装用户
	 * <已扫描过的且验证合格的用户不需重复扫描>
	 * @return
	 */
	private String getCheckpassSql() {
		//报装信息主表lcsw_bzxx  报装状态为7-验收合格
		String strWhere = "nvl(dr,0)=0 and bzzt=7  and exists (select 1 from lcsw_bzxx)";
		//避免重复扫描验证合格用户,暂时使用resserve1代替
		String repeateCode = " and (reserve1 is null)";
		return strWhere + repeateCode;
	}

}
