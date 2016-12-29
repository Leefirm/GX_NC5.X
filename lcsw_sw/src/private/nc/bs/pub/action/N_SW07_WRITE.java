package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.lcsw.sw07.AzxxfkBVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע����װ�������ݵ��ύ ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ
 * 
 * �������ڣ�2014-09-03 17:20:11
 * 
 * @author ��չ��
 */
public class N_SW07_WRITE extends AbstractCompiler2 {
	private Hashtable<String, Object> m_methodReturnHas = new Hashtable<String, Object>();
	private Hashtable<String, Object> m_keyHas = null;

	/**
	 * N_SW07_WRITE ������ע�⡣
	 */
	public N_SW07_WRITE() {
		super();
	}

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			// ####���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ####
			Object retObj = null;
			// ####��Ҫ˵�������ɵ�ҵ���������������Ҫ�����޸�####
			// ����˵��:�������淽��
			
			AzxxfkBVO[] ysxxfkBVO=(AzxxfkBVO[]) getVo().getChildrenVO(); //��ȡ������Ϣ�����ӱ�����
			if(ysxxfkBVO != null) {
				for (AzxxfkBVO ysfksjBVO : ysxxfkBVO) {
					if(ysfksjBVO.getPk_bzxx() == null) {
						throw new BusinessException("���岻���������У�");
					}
				}
			}
			
			// ���ɵ��ݺ�
			nc.bs.pub.billcodemanage.BillcodeGenerater gene = new nc.bs.pub.billcodemanage.BillcodeGenerater();
			if (nc.vo.jcom.lang.StringUtil.isEmpty(((String) vo.m_preValueVo
					.getParentVO().getAttributeValue("vbillcode")))) {
				String billno = gene.getBillCode(vo.m_billType, vo.m_coId,
						null, null);
				vo.m_preValueVo.getParentVO().setAttributeValue("vbillcode",
						billno);
			}
			
			
			retObj = runClass("nc.bs.trade.comsave.BillSave", "saveBill", "nc.vo.pub.AggregatedValueObject:01", vo, m_keyHas, m_methodReturnHas);
			// ##################################################
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
		return "	//####���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ####\n	Object retObj =null;\n	//####��Ҫ˵�������ɵ�ҵ���������������Ҫ�����޸�####\n	//����˵��:�������淽��\n	retObj =runClassCom@ \"nc.bs.trade.comsave.BillSave\", \"saveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@;\n	//##################################################\n	return retObj;\n";
	}
}
