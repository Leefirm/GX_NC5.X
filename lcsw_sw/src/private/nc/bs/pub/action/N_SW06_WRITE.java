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
 * 备注：单据:<b><code>SW05</code></b> 的保存 <tt>WRITE</tt> 动态执行类。
 * 
 * @since 创建日期：2014-08-19 16:11:14
 * @version UAP 5.x
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW06_WRITE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW05_WRITE 构造子注解。
	 */
	public N_SW06_WRITE() {
		super();
	}

	/*
	 * 备注：平台编写规则类 接口执行类
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			// ****本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值****
			Object retObj = null;
			// ****重要说明：生成的业务组件方法尽量不要进行修改****
			// 方法说明:公共保存方法
			//如果已经有下游单据，不允许修改[是否移交安装]和[移交安装日期]
			FbyjazVO head = (FbyjazVO) getVo().getParentVO();
			if(head.getSfyjaz() == null || !head.getSfyjaz().booleanValue() || head.getYjazrq() == null) {
				String sql = "select pk_jobbasfil from lcsw_azxxfk_h where nvl(dr,0)=0 and pk_jobbasfil='"+head.getPk_jobbasfil()+"'";
				String pk_jobbasfil= (String) new BaseDAO().executeQuery(sql, new ColumnProcessor());
				if(pk_jobbasfil != null) {
					throw new BusinessException("已经生成安装反馈，不允许修改[是否移交安装]和[移交安装日期]字段！");
				}
			}
			
			
			
			// 生成单据号
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
	 * 备注：平台编写原始脚本
	 */
	public String getCodeRemark() {
		return "	//****本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值****\n	Object retObj  =null;\n	//****重要说明：生成的业务组件方法尽量不要进行修改****\n	//方法说明:公共保存方法\n	// 生成单据号\n	nc.bs.pub.billcodemanage.BillcodeGenerater gene  =\n	new nc.bs.pub.billcodemanage.BillcodeGenerater ();\n	if ( nc.vo.jcom.lang.StringUtil.isEmpty ( ( (String)vo.m_preValueVo.getParentVO ().getAttributeValue ( \"vbillno\")))) {\n		String billno  = gene.getBillCode (vo.m_billType,vo.m_coId,null,null);\n		vo.m_preValueVo.getParentVO ().setAttributeValue ( \"vbillno\",billno);\n	}\n	retObj  =runClassCom@ \"nc.bs.trade.comsave.BillSave\", \"saveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@;\n	//*************************************************\n	return retObj;\n";
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
