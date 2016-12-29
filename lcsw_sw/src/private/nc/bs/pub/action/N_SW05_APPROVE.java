/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('APPROVE', 'N_SW05_APPROVE', 0, 'N', 'SW05', '140819161113223G1O5P', NULL, '0001', '2014-08-19 16:11:13')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('����', NULL, NULL, 'APPROVE', 'N', 'Y', 0, 'Y', '1408191611133234WGNB', 'SW05', NULL, '2014-08-19 16:11:13')
//go

package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.lcsw.sw05.GcpgsjBVO;
import nc.vo.lcsw.sw05.GcpgsjHVO;
import nc.vo.lcsw.sw05.itf.IGcpgsj;
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
public class N_SW05_APPROVE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW05_APPROVE ������ע�⡣
	 */
	public N_SW05_APPROVE() {
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

			if (((GcpgsjHVO) getVo().getParentVO()).getVbillstatus() == 1) {
				// ȡ�ú�̨EJB���
				IGcpgsj gcpgsjServer = (IGcpgsj) NCLocator.getInstance().lookup(IGcpgsj.class);
				GcpgsjBVO[] bvos = (GcpgsjBVO[]) getVo().getChildrenVO();
				gcpgsjServer.updateBzxx(bvos, ((GcpgsjHVO) getVo().getParentVO()).getPk_gcpgsjhid());
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
