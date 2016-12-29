/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('DELETE', 'N_SW01_DELETE', null, 'N', 'SW01', '14080411394323597HOY', NULL, '0001', '2014-08-04 11:39:43')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('����', NULL, NULL, 'DELETE', 'N', 'Y', 0, 'Y', '1408041139433351OHIB', 'SW01', NULL, '2014-08-04 11:39:43')
//go

package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע������:<b><code>SW01</code></b> 
 * 
 * @since �������ڣ�2014-08-04 11:39:43
 * @version UAP 5.x 
 * @author Administrator
 * @author NC Plugin  by leefirm
 */
public class N_SW01_SAVE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW01_DELETE ������ע�⡣
	 */
	public N_SW01_SAVE() {
		super();
	}

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			// ****�����Ϊ����������������ʼ...���ܽ����޸�****
						Object m_sysflowObj = procActionFlow(vo);
						if (m_sysflowObj != null) {
							return m_sysflowObj;
						}
			
			updateJobName((LcswBzxxHVO) vo.m_preValueVo.getParentVO());
			
			// ****���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ****
						Object retObj = runClass("nc.bs.trade.comstatus.BillCommit",
								"commitBill", "nc.vo.pub.AggregatedValueObject:01", vo,
								m_keyHas, m_methodReturnHas);
						return retObj;
					} catch (Exception ex) {
						if (ex instanceof BusinessException)
							throw (BusinessException) ex;
						else
							throw new PFBusinessException(ex.getMessage(), ex);
					}
	}

	private void updateJobName(LcswBzxxHVO bzxxVo) throws Exception {
		if(bzxxVo.getPk_jobbasfil() != null ){
			String jobbasfil=bzxxVo.getPk_jobbasfil();
			BaseDAO dao = new BaseDAO();
			String sqlJobname= "update bd_jobbasfil set jobname ="+jobbasfil;
			dao.executeUpdate(sqlJobname);
		}
		
	}

	// ��ע��ƽ̨��дԭʼ�ű�
	
	public String getCodeRemark() {
		return "	//****���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ****\n	Object retObj  =runClassCom@ \"nc.bs.trade.comstatus.BillCommit\", \"commitBill\", \"nc.vo.pub.AggregatedValueObject:01\"@;\n	return retObj;\n";
	}
	/*
	 * ��ע�����ýű�������HAS
	 */
	private void setParameter(String key, Object val) {
		if (m_keyHas == null) {
			m_keyHas = new Hashtable();
		}
		if (val != null) {
			m_keyHas.put(key, val);
		}
	}
}
