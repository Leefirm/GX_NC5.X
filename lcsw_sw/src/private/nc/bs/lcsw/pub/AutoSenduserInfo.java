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
 * ��֤�ϸ��û���Ϣ
 * ��NCϵͳɸѡ�ϸ��û�����Ӫ��ϵͳ���ٷ�д�뻧��ź��뻧���ڵ�NCϵͳ
 * ��̨Ԥ������
 * @author crf
 *
 */
@SuppressWarnings("unchecked")
public class AutoSenduserInfo implements IBackgroundWorkPlugin{
	//�м������
	private HashMap<String, LcswMiddleVO> midMap = new HashMap<String, LcswMiddleVO>();
	private String message = "";
	private HYPubBO bo;

	public HYPubBO getHYPubBo() {
		if(bo == null)
			bo = new HYPubBO();
		return bo;
	}
	
	// ������
	public String executeTask(BgWorkingContext bgwc) throws BusinessException {
		String reMsg = null;
		try {
			Logger.debug("------------------У����֤�ϸ��û�---------------------");
			reMsg = checkPass();
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			reMsg = e.getMessage();
		}
		return reMsg;
	}
	
	/**
	 * У�����ͨ���û�
	 * @return
	 * @throws BusinessException 
	 * @throws DbException 
	 * @throws Exception 
	 */
	private String checkPass() throws Exception {
		String source = "lcsw";//����Դ(������Ӫ��ϵͳ������Դ)insert
		/* �õ���֤�ϸ��û� */
		LcswBzxxHVO[] bzxx = (LcswBzxxHVO[]) getHYPubBo().queryByCondition(LcswBzxxHVO.class, getCheckpassSql());
		if(bzxx == null || bzxx.length ==0){
			return "���������պϸ��û�!!!";
		}else{
			//���ڣ������м��zz5_da_bzdrzjb��������
			Logger.debug("------------------���м�����ϸ��û��뻧����---------------------");
			
			message = splitInsertVos(source);
			//��������պϸ��û�����Զ�̵��ýӿڣ������û���Ϣ��Ӫ��ϵͳ
			Logger.debug("------------------��ȡӪ��ϵͳ�뻧����---------------------");
			//���±�װϵͳ�뻧����
			readSysMiddle(source);
			Logger.debug("------------------������û��뻧---------------------");
			
		}
		return message;
	}
		
	/**
	 * ����Ӫ��ϵͳ�Լ���װϵͳ���û��뻧���ݣ������±�װϵͳ�뻧����
	 * @throws BusinessException insert
	 * @throws Exception 
	 */
	private void readSysMiddle(String source) throws Exception {
		if (!midMap.isEmpty()) {
			String strWhere = getClauseByMap4mid(midMap);//�õ����뻧�û���ѯ����
			if (strWhere != null && strWhere.length() > 0) {
//				// ��װ��Ϣ
//				LcswBzxxHVO[] bzxx = (LcswBzxxHVO[]) getHYPubBo().queryByCondition(
//						LcswBzxxHVO.class, getPass_user(strWhere, true)); //��װ��� ���������� bz20160001232  2016090090 ��ȡ�������
				
				LcswBzxxHVO[] bzxx = (LcswBzxxHVO[]) getHYPubBo().queryByCondition(LcswBzxxHVO.class, getCheckpassSql());
				// Ӫ��ϵͳ��Ϣ
				strWhere = getClauseByMap4mid(midMap);//�õ����뻧�û���ѯ����
				LcswMiddleVO[] midvos = retrieveByClause(source, strWhere);
				//�������߹�ϵ��ʵ�����ݸ���
				resolveInfo(bzxx, midvos);
			}
		}
	}
	
	/**
	 * ������װϵͳ��Ӫ��ϵͳ���û��뻧����
	 * ��д��װϵͳ�뻧��Ϣ��
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
		
		spliteBzxx(bzxx);//��װ��װϵͳ�뻧��Ϣ
		
		if (midvos != null && midvos.length > 0) {
			for (LcswMiddleVO midvo : midvos) {
				Integer key = midvo.getBzbh();// ��װ���
				Integer rhbh = midvo.getHh();// �뻧���
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
							strkey="20"+strkey;                 //�����8λǰ��+20 15000969
						}
						if (mapkey.substring(2,mapkey.length()).equals(strkey.substring(2, strkey.length()))) {
							
							LcswBzxxHVO vo = lcsw_bzxx.get(mapkey);
							/* ***������֤�ϸ��û�����д���뻧����**** */
							vo.setRhbh(String.valueOf(rhbh));
							//��������
							
						
							//��������
							vo.setBzzt(9);
							
							vo.setLhrq(new UFDate(new Date()));
							
							//Ԥ���ֶ�һ���������ظ�ɨ��
							vo.setReserve1("Y");
							bzxx_list.add(vo);
						}
					}
				}
			}
			if (bzxx_list != null && bzxx_list.size() > 0) {
				dao.updateVOList(bzxx_list);//���±�װϵͳ�뻧��Ϣ
			}
			message = "������û��뻧����" + bzxx_list.size();
		}
	
	}
	/**
	 * ��װ��װϵͳ�뻧��Ϣ
	 * @param bzxx
	 */
	private void spliteBzxx(LcswBzxxHVO[] bzxx) {
		if (bzxx != null && bzxx.length > 0){
			for (LcswBzxxHVO vo : bzxx) {
				String key = vo.getBzbh();// ��װ���
				if (!lcsw_bzxx.containsKey(key)) {
					lcsw_bzxx.put(key, vo);
				}
			}
		}
	}
	/**
	 * ��װ��ѯ����
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
				key="20"+key;                 //�����8λǰ��+20 15000969
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
	 * ��װ��ѯ����
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
	 * ��ȡ�������û��뻧��Ϣ
	 * @param source
	 * @return
	 * @throws Exception
	 */
	private LcswMiddleVO[] retrieveByClause(String source,String con) throws Exception {
		/* ����Ӫ��ϵͳ���ݿ� */
		DBManager db = new DBManager(source);
		
		Collection col = (Collection) db.executeQuery("select * from NNMIS.ZZ5_DA_BZDRZJB where " + getPass_user(con,false), new BeanListProcessor(LcswMiddleVO.class));
		
//		Collection col = db.retrieveByClause(LcswMiddleVO.class, getPass_user(con,false));
		if (col == null || col.size() <= 0)
			return null;
		return 
		((LcswMiddleVO[]) col.toArray(new LcswMiddleVO[0]));
	}
	/**
	 * ��װ��Ų�ѯ����
	 * @param con
	 * @return
	 */
	private String getPass_user(String con,boolean islocal) {
		String strWhere = "";
		if(islocal) {
			//��װϵͳ
			if(con.contains("bz")){
				strWhere = "substr(bzbh,3,length(bzbh)) in (" + con + ") and substr(bzbh,0,2) in ('bz')";
			}else{
				
			
			strWhere = "substr(bzbh,3,length(bzbh)) in (" + con + ") ";//baozhuangbianhao 2015 000818
			}
		}
			else{
			//Ӫ��ϵͳ
			strWhere = "bzbh in (" + con + ")";
			System.out.println("select * from NNMIS.ZZ5_DA_BZDRZJB where " +strWhere);
		}
		return strWhere;
	}
	
	/**
	 * �������ݣ����·�װ�м��vo��Ϣ
	 * @param bzxx
	 * @throws DbException 
	 * @throws DbException 
	 */
	private String splitInsertVos(String source) throws DAOException, DbException {
		/* ����Ӫ��ϵͳ���ݿ� */
		DBManager db = new DBManager(source);
		midMap.clear();
		try{
			//�ӱ�װ��Ϣ�л�ȡ��֤ͨ���û���Ϣ
			LcswMiddleVO[] vos = getLcsw_bzxx();
			if(vos != null && vos.length > 0){
				for (LcswMiddleVO vo : vos) {
					String key = vo.getAzgcbh();//��װ���
					if(!midMap.containsKey(key))
						midMap.put(key, vo);
					String sfzbh=vo.getSfzbh(); 
					if(sfzbh !=null){
					vo.setSfzbh(sfzbh);//���֤���
					}
					String sgfzr = vo.getAzrxm();
					if (sgfzr !=null){
					vo.setAzrxm(sgfzr);//ʩ��������
					}
					Integer yyqy = vo.getYyqy();//Ӫҵ����
					Integer swkj = vo.getKj();//����ھ�
					Integer rhbh = vo.getRhbh();//�뻧���
					String cby = vo.getCby();//�״γ���Ա
					vo.setSbdayyqy(yyqy);
					/*bch-�����*/
					if(swkj < 50){
						vo.setBch(yyqy + "8999");
					}else if(swkj >= 50){
						vo.setBch(yyqy + "8998");
					}else{
						vo.setBch(yyqy + "9999");
					}
					/*ˮ������*/
					//ƥ�� ˮ������ �Զ��嵵����Ŀ  by leefirm
//					String sblx = vo.getSblx();
//					if(sblx != null && (("��еˮ��--����").equalsIgnoreCase(sblx) || ("����").equalsIgnoreCase(sblx))){
//						vo.setSblx("2");
//					}else if(sblx != null && (("��еˮ��--����").equalsIgnoreCase(sblx) ||("����").equalsIgnoreCase(sblx))){
//						vo.setSblx("1");
//					}else if(sblx !=null && ("������ˮ��").equalsIgnoreCase(sblx) ){
//						vo.setSblx("3");
//					}else if(sblx !=null && ("Զ�����ˮ��").equalsIgnoreCase(sblx)  ){
//						vo.setSblx("4");
//					}else if(sblx !=null && ("IC������ˮ��").equalsIgnoreCase(sblx) ){
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
					
					
					
					//��ˮ����
					if(vo.getZysxz()==null){
						vo.setZysxz("1101");
					}
					
					//��������
					String yhflc = vo.getYhflc();
					vo.setYhflc(MiddleDataUtil.yhflcMap.get(yhflc));
					//���ʽ
					/**
					 * �ֽ�	XJ	�������ʽ��=NULL����SFLX="�ֽ�" ����	DK	 ����	TS	 ת��	ZZ	
					 */
					String sflx = vo.getSflx();
					if(sflx == null) {
						vo.setSflx("�ֽ�");
						sflx=vo.getSflx();
					}
				    if(sflx.indexOf("�ֽ�") != -1) {
						vo.setSflx("XJ");
					}
					else if(sflx.indexOf("����") != -1) {
						vo.setSflx("DK");
					}
					else if(sflx.indexOf("����") != -1) {
						vo.setSflx("TS");
					}
					else if(sflx.indexOf("ת��") != -1) {
						vo.setSflx("ZZ");
					}
					
					
				    System.out.println("sblx���ȣ�"+vo.getSblx().length()+",");
				}
				//���м���������
				System.out.println("�����midvo������"+midMap.size());
				System.out.println("�����м���������"+vos.length);
				
				db.insertVOs(vos, false);
				message = "�����м������!";
			}
		}catch (Exception e) {
	        e.printStackTrace();
	        message = e.getMessage();
	    }
		return message;
	}	

	/**
	 * ���պϸ��û�����װ�м��vo
	 * @return
	 * @throws BusinessException
	 */
	private LcswMiddleVO[] getLcsw_bzxx() throws BusinessException{
		LcswMiddleVO[] dataVOs = null;
		PersistenceManager sessionManager = null;
		try{
			sessionManager = JdbcPersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			//��֤�ϸ���û�sfzbh  ckxd
			StringBuffer strQuery = new StringBuffer();
			/*bzmc-����,yyqy--Ӫҵ����(1��������3��������4������)*,���⺽�ɡ�����������/
			strQuery.append("select hm bzmc,ckxd SQXZ,");
			strQuery.append("case when yyqy='����' then 1  when yyqy='����' then 3  when yyqy='����' then 4  else 3 end yyqy,");
			/*yhdz-��װ��ַ(azdz),zbwz-��λ*/
			strQuery.append("nvl(azdz, biaowei) yhdz,nvl(azdz, biaowei) zbwz,");
			/*zysxz-��ˮ���� TODO (�Զ�����-��ˮ���ʱ�(����0001AA1000000000J85H)) */
			strQuery.append("(select doccode from bd_defdoc where pk_defdoclist = '0001AA1000000000J85H' and trim(doccode)=trim(lcsw_bzxx.ysxz)) zysxz,");
			/*ysxzsl,qcy,cbzq,slqy,lxr-��ϵ��,lxdh-��ϵ�绰*/
			strQuery.append("1 ysxzsl,1 qcy,1 cbzq,'' slqy,lxr lxr,lxdd lxdh,");
			/*sfzbh-���֤��,zzsh,kj-����ھ�,yhzl,jlsfbz,lhbz,yxfjsl,khh-�뻧���,khmc-����,txdz-��ϵ��ַ*/
			strQuery.append("'' sfzbh,0 zzsh,to_number(swkj) kj,'' yhzl,0 jlsfbz,1 lhbz,0 yxfjsl,rhbh khh,hm khmc, nvl(lxdz, azdz) txdz,sfzh sfzbh,sgfzr azrxm, ");
			/*khxxlxdh-��ϵ�绰, sflx- TODO ���ʽ*/
			strQuery.append("lxdd khxxlxdh,");
			//panqh
//			strQuery.append("CASE WHEN fkfs = '0001A310000000001BF1' THEN 'XJ' WHEN fkfs = '0001F51000000000ODXI' THEN 'TS'  ");
//			strQuery.append("WHEN fkfs = '0001A310000000001BF2' THEN 'DK' WHEN fkfs = '0001A310000000001BF3' THEN 'ZZ' ELSE 'XJ' END sflx,");
			strQuery.append(" (select balanname from bd_balatype where pk_balatype=fkfs) sflx,");
			/*ysh,yskye,cjfs,khzt,bsh-����,sbdakj-����ھ� ,cjid,*/
			strQuery.append("0 ysh,0 yskye,'TZD' cjfs,1 khzt,biaoma bsh,to_number(swkj) sbdakj ,1 cjid,");
			/*sblx-ˮ������,xjdsbz,zdds,azbz,sbwzbm*/
			strQuery.append("nvl(substr(biaobie,1,8), '1') sblx,0 xjdsbz,0 zdds,1 azbz,0 sbwzbm,");
			/*sbdayyqy-Ӫҵ����,zhwzrq-װ������ ,ljsyts,ljsysl*/
			strQuery.append("null sbdayyqy,nvl(to_date(zbrq,'yyyy-mm-dd'), getdate()) zhwzrq ,0 ljsyts,0 ljsysl,");
			/*yhzt,scrq-װ������,ztbgrq-װ������ */
			strQuery.append("'ZC' yhzt,nvl(to_date(zbrq,'yyyy-mm-dd'), getdate()) scrq,nvl(to_date(zbrq,'yyyy-mm-dd'), getdate()) ztbgrq,");
			/*tqsl,pjsl,yszt,ysztms*/
			strQuery.append("0 tqsl,0 pjsl,'00' yszt,'0����' ysztms,");
			/*,qsl1,qsl2,qsl3,cbsxh,ljsl,ljqxz,bz1,rwdh*/
			strQuery.append("0 qsl1,0 qsl2,0 qsl3,1 cbsxh,0 ljsl,0 ljqxz,0 bz1,'' rwdh,");
			/*gshth-Э���� ,ygid,drsj,checkflag,errinfo,yhflc-������*/
			strQuery.append("xybh gshth,2 ygid,getdate() drsj,nvl('0', null) checkflag, nvl('', null) errinfo,");
			//panqh
//			strQuery.append(" CASE WHEN rhbh = '000' THEN '1090' WHEN rhbh = 'AAA' THEN '100'  WHEN rhbh = 'BBB' THEN '250' ");
//			strQuery.append(" WHEN rhbh = 'CCC' THEN '804' WHEN rhbh = 'DDD' THEN '600' WHEN rhbh = 'EEE' THEN '900' WHEN rhbh = 'FFF' THEN '095' ELSE rhbh END yhflc ,");
			strQuery.append(" (select banktypename from bd_banktype where pk_banktype=khyh) yhflc ,");
			/*yjdz-��ϵ��ַ,lhyf-�뻧�·�*/
			strQuery.append("isnull(lxdz, azdz) yjdz,convert(datetime,convert(char(10), to_char(getdate(),'yyyy-mm-dd'),120)) lhyf,");
			/*zbrq-װ������,yhlx,ljqcbr,ljqcby,*/
			strQuery.append("nvl(to_date(zbrq,'yyyy-mm-dd'), getdate()) zbrq,'01' yhlx,nvl(to_date(zbrq,'yyyy-mm-dd'), getdate()) ljqcbr,");
			strQuery.append("convert(datetime,convert(char(10),nvl(zbrq, to_char(getdate(),'yyyy-mm-dd')),120)) ljqcby,");
			/*sjhm-��ϵ�绰,ms1-��װ��ַ,ms2-��������,ms3-��֯�ṹ���� */
			strQuery.append("lxdd sjhm,isnull(biaowei, azdz) ms1,gcxz ms2,");
			/*khxxsjhm-��ϵ�绰,zh-�˺� */
			strQuery.append("lxdd khxxsjhm,yhzh zh,");
			/*azgcbh-��װ���,byms1-�����ж�,byms2-����Ա,byms3-������ϵ�绰*/
			strQuery.append("to_number(substr(bzbh,3,length(bzbh))) azgcbh,convert(varchar(8),isnull(ysxd,0)) byms1,ysy byms2,ysdh byms3,");
			/*tsrq,bzbh-��װ���*/
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
	 * ��ȡ��֤ͨ���ı�װ�û�
	 * <��ɨ���������֤�ϸ���û������ظ�ɨ��>
	 * @return
	 */
	private String getCheckpassSql() {
		//��װ��Ϣ����lcsw_bzxx  ��װ״̬Ϊ7-���պϸ�
		String strWhere = "nvl(dr,0)=0 and bzzt=7  and exists (select 1 from lcsw_bzxx)";
		//�����ظ�ɨ����֤�ϸ��û�,��ʱʹ��resserve1����
		String repeateCode = " and (reserve1 is null)";
		return strWhere + repeateCode;
	}

}
