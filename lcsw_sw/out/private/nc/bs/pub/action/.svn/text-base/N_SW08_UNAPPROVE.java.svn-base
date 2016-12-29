/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('UNAPPROVE', 'N_SW08_UNAPPROVE', 0, 'N', 'SW08', '1408211129298162Z4N6',NULL, '0001', '2014-08-21 11:29:29')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('弃审', NULL, NULL, 'UNAPPROVE', 'N', 'Y', 0, 'Y', '140821112929916RTJUL', 'SW08', NULL, '2014-08-21 11:29:29')
//go

package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.trade.business.HYPubBO;
import nc.uif.pub.exception.UifException;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw08.YsfksjBVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：单据:<b><code>SW08</code></b> 的弃审  <tt>UNAPPROVE</tt> 动态执行类。
 * 
 * @since 创建日期：2014-08-21 11:29:29
 * @version UAP 5.x 
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW08_UNAPPROVE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW08_UNAPPROVE 构造子注解。
	 */
	public N_SW08_UNAPPROVE() {
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
			Object retObj = runClass("nc.bs.trade.comstatus.BillUnApprove",
					"unApproveBill", "nc.vo.pub.AggregatedValueObject:01", vo,
					m_keyHas, m_methodReturnHas);
			
			writeBackBzzt();
			
			return retObj;
		} catch (Exception ex) {
			if (ex instanceof BusinessException)
				throw (BusinessException) ex;
			else
				throw new PFBusinessException(ex.getMessage(), ex);
		}
	}

	/**
	 * 回写报装状态
	 * @throws UifException
	 */
	private void writeBackBzzt() throws UifException {
		YsfksjBVO[] ysxxfkBVO=(YsfksjBVO[]) getVo().getChildrenVO(); //获取验收信息反馈子表数据
		if(ysxxfkBVO!=null && ysxxfkBVO.length>0){
			HYPubBO bo = new HYPubBO();
			LcswBzxxHVO[] bzxxhvos=new LcswBzxxHVO[ysxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				LcswBzxxHVO bzxxhvo= (LcswBzxxHVO) bo.queryByPrimaryKey(LcswBzxxHVO.class, ysxxfkBVO[i].getPk_bzxx());
//				bzxxhvo.setBzzt(EmunBzzt.YSBHG);//验收不合格
				bzxxhvos[i]=bzxxhvo;
			}
			bo.updateAry(bzxxhvos);
			
			//回写 报装申请 [报装状态]
			BzsqVO[] bzsqVOs=new BzsqVO[ysxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				BzsqVO bzsqVO=(BzsqVO) bo.queryByPrimaryKey(BzsqVO.class,ysxxfkBVO[i].getPk_bzsq());
//				bzsqVO.setBzzt(EmunBzzt.YSBHG);//验收不合格
				bzsqVOs[i]=bzsqVO;
			}
			bo.updateAry(bzsqVOs);
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
