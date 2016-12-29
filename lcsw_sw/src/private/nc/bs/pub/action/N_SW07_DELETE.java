package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：安装反馈数据的作废 单据动作执行中的动态执行类的动态执行类。
 * 
 * 创建日期：2014-09-03 17:20:11
 * 
 * @author 梁展轩
 */
public class N_SW07_DELETE extends AbstractCompiler2 {
	private Hashtable<String, Object> m_methodReturnHas = new Hashtable<String, Object>();
	private Hashtable<String, Object> m_keyHas = null;

	/**
	 * N_SW07_DELETE 构造子注解。
	 */
	public N_SW07_DELETE() {
		super();
	}

	/*
	 * 备注：平台编写规则类 接口执行类
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			// ####本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值####
			Object retObj = null;
			// 方法说明:行业公共删除
			retObj = runClass("nc.bs.trade.comdelete.BillDelete", "deleteBill", "nc.vo.pub.AggregatedValueObject:01", vo, m_keyHas, m_methodReturnHas);
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
	 * 备注：平台编写原始脚本
	 */
	public String getCodeRemark() {
		return "	//####本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值####\n	Object retObj  =null;\n	//方法说明:行业公共删除\n	retObj  =runClassCom@ \"nc.bs.trade.comdelete.BillDelete\", \"deleteBill\", \"nc.vo.pub.AggregatedValueObject:01\"@;\n	//##################################################\n	return retObj;\n";
	}
}
