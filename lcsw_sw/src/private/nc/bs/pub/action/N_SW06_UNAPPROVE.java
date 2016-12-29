package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.trade.business.HYPubBO;
import nc.vo.lcsw.sw06.FbyjazBVO;
import nc.vo.lcsw.sw06.FbyjazVO;
import nc.vo.lcsw.sw06.itf.IFbyjaz;
import nc.vo.lcsw.sw07.AzxxfkBVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：单据:<b><code>SW05</code></b> 的弃审 <tt>UNAPPROVE</tt> 动态执行类。
 * 
 * @since 创建日期：2014-08-19 16:11:13
 * @version UAP 5.x
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW06_UNAPPROVE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW05_UNAPPROVE 构造子注解。
	 */
	public N_SW06_UNAPPROVE() {
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

			if (((FbyjazVO) getVo().getParentVO()).getVbillstatus() == 8) {
				// 取得后台EJB组件
				IFbyjaz fbyjazServer = (IFbyjaz) NCLocator.getInstance().lookup(IFbyjaz.class);
				FbyjazBVO[] bvos = (FbyjazBVO[]) getVo().getChildrenVO();
				//add by crf 2015-06-16 下游单据校验
				this.check(bvos);
				fbyjazServer.unProve(bvos, ((FbyjazVO) getVo().getParentVO()).getPk_fbyjaz());
			}

			return retObj;
		} catch (Exception ex) {
			if (ex instanceof BusinessException)
				throw (BusinessException) ex;
			else
				throw new PFBusinessException(ex.getMessage(), ex);
		}
	}
	/**
	 * 下游单据校验
	 * add by crf 
	 * @param bvos
	 * @throws Exception
	 */
	private void check(FbyjazBVO[] bvos) throws Exception {
		int row = bvos.length;

		HYPubBO bo = new HYPubBO();

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

		//发包移交安装
		AzxxfkBVO[] azxxfkBVO = (AzxxfkBVO[]) bo.queryByCondition(AzxxfkBVO.class, " nvl(dr,0)=0 and " + AzxxfkBVO.PK_BZSQ + " in (" + pk_bzsq + ") ");
		if (azxxfkBVO != null && azxxfkBVO.length > 0) {
			throw new PFBusinessException("该单据存在下游单据不能弃审！", null);
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
