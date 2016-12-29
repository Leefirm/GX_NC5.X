package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.dao.BaseDAO;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.lcsw.sw06.FbyjazVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע������:<b><code>SW05</code></b> �ı��� <tt>WRITE</tt> ��ִ̬���ࡣ
 * 
 * @since �������ڣ�2014-08-19 16:11:14
 * @version UAP 5.x
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW06_WRITE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW05_WRITE ������ע�⡣
	 */
	public N_SW06_WRITE() {
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
			//����Ѿ������ε��ݣ��������޸�[�Ƿ��ƽ���װ]��[�ƽ���װ����]
			FbyjazVO head = (FbyjazVO) getVo().getParentVO();
			if(head.getSfyjaz() == null || !head.getSfyjaz().booleanValue() || head.getYjazrq() == null) {
				String sql = "select pk_jobbasfil from lcsw_azxxfk_h where nvl(dr,0)=0 and pk_jobbasfil='"+head.getPk_jobbasfil()+"'";
				String pk_jobbasfil= (String) new BaseDAO().executeQuery(sql, new ColumnProcessor());
				if(pk_jobbasfil != null) {
					throw new BusinessException("�Ѿ����ɰ�װ�������������޸�[�Ƿ��ƽ���װ]��[�ƽ���װ����]�ֶΣ�");
				}
			}
			
			
			
			// ���ɵ��ݺ�
			nc.bs.pub.billcodemanage.BillcodeGenerater gene = new nc.bs.pub.billcodemanage.BillcodeGenerater();
			if (nc.vo.jcom.lang.StringUtil.isEmpty(((String) vo.m_preValueVo.getParentVO().getAttributeValue("vbillcode")))) {
				String billno = gene.getBillCode(vo.m_billType, vo.m_coId, null, null);
				vo.m_preValueVo.getParentVO().setAttributeValue("vbillcode", billno);
			}
			retObj = runClass("nc.bs.trade.comsave.BillSave", "saveBill", "nc.vo.pub.AggregatedValueObject:01", vo, m_keyHas, m_methodReturnHas);
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
