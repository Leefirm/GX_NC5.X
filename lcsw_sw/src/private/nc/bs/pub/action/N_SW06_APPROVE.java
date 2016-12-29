package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.trade.business.HYPubBO;
import nc.ui.trade.business.HYPubBO_Client;
import nc.uif.pub.exception.UifException;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw06.FbyjazBVO;
import nc.vo.lcsw.sw06.FbyjazVO;
import nc.vo.lcsw.sw06.itf.IFbyjaz;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע������:<b><code>SW05</code></b> ������ <tt>APPROVE</tt> ��ִ̬���ࡣ
 * 
 * @since �������ڣ�2014-08-19 16:11:13
 * @version UAP 5.x
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW06_APPROVE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW05_APPROVE ������ע�⡣
	 */
	public N_SW06_APPROVE() {
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

			if (((FbyjazVO) getVo().getParentVO()).getVbillstatus() == 1) {
				// ȡ�ú�̨EJB���
				IFbyjaz fbyjazServer = (IFbyjaz) NCLocator.getInstance().lookup(IFbyjaz.class);
				FbyjazBVO[] bvos = (FbyjazBVO[]) getVo().getChildrenVO();
				fbyjazServer.updateBzxx(bvos, ((FbyjazVO) getVo().getParentVO()).getPk_fbyjaz());
			}

			if (m_sysflowObj != null) {
				return m_sysflowObj;
			}
			// ****�����Ϊ�������������������...���ܽ����޸�****
			Object retObj = runClass("nc.bs.trade.comstatus.BillApprove", "approveBill", "nc.vo.pub.AggregatedValueObject:01", vo, m_keyHas, m_methodReturnHas);
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
		return "	//****�����Ϊ����������������ʼ...���ܽ����޸�****\n	procActionFlow@@;\n	//****�����Ϊ�������������������...���ܽ����޸�****\n	Object retObj  =runClassCom@ \"nc.bs.trade.comstatus.BillApprove\", \"approveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@; \n	return retObj;\n";
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
