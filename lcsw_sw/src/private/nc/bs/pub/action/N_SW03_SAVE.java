/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('SAVE', 'N_SW03_SAVE', 0, 'N', 'SW03', '140807173102207KSHS3', NULL, '0001', '2014-08-07 17:31:02')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('�ύ', NULL, NULL, 'SAVE', 'N', 'Y', 0, 'Y', '1408071731023075YL74', 'SW03', NULL, '2014-08-07 17:31:02')
//go

package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע������:<b><code>SW03</code></b> ���ύ <tt>SAVE</tt> ��ִ̬���ࡣ
 * 
 * @since �������ڣ�2014-08-07 17:31:02
 * @version UAP 5.x 
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW03_SAVE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW03_SAVE ������ע�⡣
	 */
	public N_SW03_SAVE() {
		super();
	}

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
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

	/*
	 * ��ע��ƽ̨��дԭʼ�ű�
	 */
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