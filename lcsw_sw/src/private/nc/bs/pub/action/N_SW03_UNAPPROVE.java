/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('UNAPPROVE', 'N_SW03_UNAPPROVE', 0, 'N', 'SW03', '140807173101964EV48D',NULL, '0001', '2014-08-07 17:31:01')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('弃审', NULL, NULL, 'UNAPPROVE', 'N', 'Y', 0, 'Y', '1408071731020640BDLV', 'SW03', NULL, '2014-08-07 17:31:02')
//go

package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.trade.business.HYPubBO;
import nc.ui.trade.business.HYPubBO_Client;
import nc.uif.pub.exception.UifException;
import nc.vo.lcsw.sw03.KcpgBVO;
import nc.vo.lcsw.sw03.KcpgHVO;
import nc.vo.lcsw.sw03.itf.IKcpg;
import nc.vo.lcsw.sw04.KcfksjBVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：单据:<b><code>SW03</code></b> 的弃审 <tt>UNAPPROVE</tt> 动态执行类。
 * 
 * @since 创建日期：2014-08-07 17:31:02
 * @version UAP 5.x
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW03_UNAPPROVE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW03_UNAPPROVE 构造子注解。
	 */
	public N_SW03_UNAPPROVE() {
		super();
	}

	/*
	 * 备注：平台编写规则类 接口执行类
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			// ****本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值****
			procUnApproveFlow(vo);
			Object retObj = runClass("nc.bs.trade.comstatus.BillUnApprove", "unApproveBill", "nc.vo.pub.AggregatedValueObject:01", vo, m_keyHas,
					m_methodReturnHas);

			if (((KcpgHVO) getVo().getParentVO()).getVbillstatus() == 8) {
				// 取得后台EJB组件
				IKcpg kcpgServer = (IKcpg) NCLocator.getInstance().lookup(IKcpg.class);
				KcpgBVO[] bvos = (KcpgBVO[]) getVo().getChildrenVO();
				this.check(bvos);
				kcpgServer.updateUnprove(bvos, ((KcpgHVO) getVo().getParentVO()).getPk_kcpghid());
			}

			return retObj;
		} catch (Exception ex) {
			if (ex instanceof BusinessException)
				throw (BusinessException) ex;
			else
				throw new PFBusinessException(ex.getMessage(), ex);
		}
	}

	private void check(KcpgBVO[] bvos) throws Exception {
		HYPubBO bo = new HYPubBO();

		int row = bvos.length;

		String pk_bzsq = "";
		for (int i = 0; i < row; i++) {
			Object obj = bvos[i].getPk_bzsq();
			if (i == 0) {
				pk_bzsq = " '" + obj.toString() + "' ";
			}
			obj = "'" + obj.toString() + "'";
			if (i != 0 && pk_bzsq.indexOf(obj.toString()) < 1) {
				pk_bzsq = pk_bzsq + " , " + obj.toString() + " ";
			}
		}

		KcfksjBVO[] kcfksjbvo = (KcfksjBVO[]) bo.queryByCondition(KcfksjBVO.class, " nvl(dr,0)=0 and " + KcfksjBVO.PK_BZSQ + " in (" + pk_bzsq + ") ");
		if (kcfksjbvo != null && kcfksjbvo.length > 0) {
			throw new BusinessException("该单据存在下游单据不能弃审！");
		}
	}

	/*
	 * 备注：平台编写原始脚本
	 */
	public String getCodeRemark() {
		return "	//****本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值****\n	procUnApproveFlow (vo); \n	Object retObj  =runClassCom@ \"nc.bs.trade.comstatus.BillUnApprove\", \"unApproveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@;\n	return retObj;\n";
	}

	/*
	 * 备注：设置脚本变量的HAS
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
