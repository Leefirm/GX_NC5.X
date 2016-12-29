package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.lcsw.sw07.AzxxfkBVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：安装反馈数据的提交 单据动作执行中的动态执行类的动态执行类。
 * 
 * 创建日期：2014-09-03 17:20:11
 * 
 * @author 梁展轩
 */
public class N_SW07_WRITE extends AbstractCompiler2 {
	private Hashtable<String, Object> m_methodReturnHas = new Hashtable<String, Object>();
	private Hashtable<String, Object> m_keyHas = null;

	/**
	 * N_SW07_WRITE 构造子注解。
	 */
	public N_SW07_WRITE() {
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
			// ####重要说明：生成的业务组件方法尽量不要进行修改####
			// 方法说明:公共保存方法
			
			AzxxfkBVO[] ysxxfkBVO=(AzxxfkBVO[]) getVo().getChildrenVO(); //获取验收信息反馈子表数据
			if(ysxxfkBVO != null) {
				for (AzxxfkBVO ysfksjBVO : ysxxfkBVO) {
					if(ysfksjBVO.getPk_bzxx() == null) {
						throw new BusinessException("表体不允许新增行！");
					}
				}
			}
			
			// 生成单据号
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
	 * 备注：平台编写原始脚本
	 */
	public String getCodeRemark() {
		return "	//####本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值####\n	Object retObj =null;\n	//####重要说明：生成的业务组件方法尽量不要进行修改####\n	//方法说明:公共保存方法\n	retObj =runClassCom@ \"nc.bs.trade.comsave.BillSave\", \"saveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@;\n	//##################################################\n	return retObj;\n";
	}
}
