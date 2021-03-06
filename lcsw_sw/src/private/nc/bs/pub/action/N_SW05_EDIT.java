/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('EDIT', 'N_SW05_EDIT', 0, 'N', 'SW05', '140819161114267EIQXG', NULL, '0001', '2014-08-19 16:11:14')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('修改', NULL, NULL, 'EDIT', 'N', 'Y', 0, 'Y', '1408191611143673MU5B', 'SW05', NULL, '2014-08-19 16:11:14')
//go

package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：单据:<b><code>SW05</code></b> 的修改 <tt>EDIT</tt> 动态执行类。
 * 
 * @since 创建日期：2014-08-19 16:11:14
 * @version UAP5.x 
 * @author 平台脚本生成 编辑脚本
 */
public class N_SW05_EDIT extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW05_EDIT 构造子注解。
	 */
	public N_SW05_EDIT() {
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
			if (true)
				return null;
			throw new BusinessException("");
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
		return "	//****本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值****\n	Object retObj  =null;\n	if ( true)\n	return null;\n	throw new BusinessException ( \"\");\n";
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
