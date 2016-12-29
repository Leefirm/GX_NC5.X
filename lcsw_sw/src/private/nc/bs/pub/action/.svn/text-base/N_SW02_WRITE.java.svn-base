/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('WRITE', 'N_SW02_WRITE', 0, 'N', 'SW02', '140804113943935U2IPI', NULL, '0001', '2014-08-04 11:39:43')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('保存', NULL, NULL, 'WRITE', 'N', 'Y', 0, 'Y', '140804113944035AQ5SL', 'SW02', NULL, '2014-08-04 11:39:44')
//go
package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.dao.BaseDAO;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.hr.utils.PubEnv;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：单据:<b><code>SW02</code></b> 的保存 <tt>WRITE</tt> 动态执行类。
 * 
 * @since 创建日期：2014-08-04 11:39:44
 * @version UAP 5.x
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW02_WRITE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW02_WRITE 构造子注解。
	 */
	public N_SW02_WRITE() {
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
			// 生成单据号
			nc.bs.pub.billcodemanage.BillcodeGenerater gene = new nc.bs.pub.billcodemanage.BillcodeGenerater();
			if (nc.vo.jcom.lang.StringUtil.isEmpty(((String) vo.m_preValueVo.getParentVO().getAttributeValue("bzbh")))) {
				String billno = gene.getBillCode(vo.m_billType, vo.m_coId, null, null);
				if (vo.m_preValueVo.getParentVO().getAttributeValue("sfgz").equals("1"))
					billno = "20" + billno.substring(0, 2) + "4" + billno.substring(2, billno.length());
				else
					billno = "20" + billno.substring(0, 2) + "0" + billno.substring(2, billno.length());
				vo.m_preValueVo.getParentVO().setAttributeValue("bzbh", billno);
			}

			if (vo.m_preValueVo.getParentVO().getAttributeValue("slbh") == null || vo.m_preValueVo.getParentVO().getAttributeValue("slbh").equals("")) {
				String year = PubEnv.getServerDate().toString().substring(0, 4);
				BaseDAO dao = new BaseDAO();
				String temp = (String) dao.executeQuery("select lpad(max(slbh)+1,6,0) from lcsw_bzsq where nvl(dr,0)=0 and subStr(bzrq,0,4)='" + year + "' ",
						new ColumnProcessor());
				if (temp == null || temp.equals("")) {
					temp = "000000";
				}
				vo.m_preValueVo.getParentVO().setAttributeValue("slbh", temp);
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
