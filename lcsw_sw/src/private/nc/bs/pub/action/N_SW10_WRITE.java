/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('WRITE', 'N_SW10_WRITE', 0, 'N', 'SW10', '1408121633279644OB4M', NULL, '0001', '2014-08-12 16:33:27')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('����', NULL, NULL, 'WRITE', 'N', 'Y', 0, 'Y', '140812163328064VVUIJ', 'SW10', NULL, '2014-08-12 16:33:28')
//go
package nc.bs.pub.action;

import java.util.Hashtable;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע������:<b><code>SW10</code></b> �ı��� <tt>WRITE</tt> ��ִ̬���ࡣ
 * 
 * @since �������ڣ�2014-08-12 16:33:28
 * @version UAP 5.x 
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW10_WRITE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW10_WRITE ������ע�⡣
	 */
	public N_SW10_WRITE() {
		super();
	}

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			// ****���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ****
			Object retObj = null;
			// ****��Ҫ˵�������ɵ�ҵ���������������Ҫ�����޸�****
			// ����˵��:�������淽��
			// ���ɵ��ݺ�
			nc.bs.pub.billcodemanage.BillcodeGenerater gene = new nc.bs.pub.billcodemanage.BillcodeGenerater();
			if (nc.vo.jcom.lang.StringUtil.isEmpty(((String) vo.m_preValueVo
					.getParentVO().getAttributeValue("vbillcode")))) {
				String billno = gene.getBillCode(vo.m_billType, vo.m_coId,
						null, null);
				vo.m_preValueVo.getParentVO().setAttributeValue("vbillcode",
						billno);
			}
			retObj = runClass("nc.bs.trade.comsave.BillSave", "saveBill",
					"nc.vo.pub.AggregatedValueObject:01", vo, m_keyHas,
					m_methodReturnHas);
			// *************************************************
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
		return "	//****���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ****\n	Object retObj  =null;\n	//****��Ҫ˵�������ɵ�ҵ���������������Ҫ�����޸�****\n	//����˵��:�������淽��\n	// ���ɵ��ݺ�\n	nc.bs.pub.billcodemanage.BillcodeGenerater gene  =\n	new nc.bs.pub.billcodemanage.BillcodeGenerater ();\n	if ( nc.vo.jcom.lang.StringUtil.isEmpty ( ( (String)vo.m_preValueVo.getParentVO ().getAttributeValue ( \"vbillno\")))) {\n		String billno  = gene.getBillCode (vo.m_billType,vo.m_coId,null,null);\n		vo.m_preValueVo.getParentVO ().setAttributeValue ( \"vbillno\",billno);\n	}\n	retObj  =runClassCom@ \"nc.bs.trade.comsave.BillSave\", \"saveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@;\n	//*************************************************\n	return retObj;\n";
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
