package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.trade.business.HYPubBO;
import nc.uif.pub.exception.UifException;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw07.AzxxfkBVO;
import nc.vo.lcsw.sw08.YsfksjBVO;
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
public class N_SW07_UNAPPROVE extends AbstractCompiler2 {
	private Hashtable<String, Object> m_methodReturnHas = new Hashtable<String, Object>();
	private Hashtable<String, Object> m_keyHas = null;

	/**
	 * N_SW07_UNAPPROVE 构造子注解。
	 */
	public N_SW07_UNAPPROVE() {
		super();
	}

	/*
	 * 备注：平台编写规则类 接口执行类
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			// ####本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值####
			procUnApproveFlow(vo);
			Object retObj = runClass("nc.bs.trade.comstatus.BillUnApprove", "unApproveBill", "nc.vo.pub.AggregatedValueObject:01", vo, m_keyHas, m_methodReturnHas);
			
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
	private void writeBackBzzt() throws Exception {
		AzxxfkBVO[] azxxfkBVO=(AzxxfkBVO[]) getVo().getChildrenVO(); //获取安装信息反馈子表数据
		//add by crf 2015-06-16 下游单据校验
		this.check(azxxfkBVO);
		//-----end ------------
		if(azxxfkBVO!=null && azxxfkBVO.length>0){
			HYPubBO bo = new HYPubBO();
			LcswBzxxHVO[] bzxxhvos=new LcswBzxxHVO[azxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				LcswBzxxHVO bzxxhvo= (LcswBzxxHVO) bo.queryByPrimaryKey(LcswBzxxHVO.class, azxxfkBVO[i].getPk_bzxx());
				bzxxhvo.setBzzt(EmunBzzt.FBYJ);//发包移交
				bzxxhvos[i]=bzxxhvo;
			}
			bo.updateAry(bzxxhvos);
		
			//回写 报装申请 [报装状态]
			BzsqVO[] bzsqVOs=new BzsqVO[azxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				BzsqVO bzsqVO=(BzsqVO) bo.queryByPrimaryKey(BzsqVO.class,azxxfkBVO[i].getPk_bzsq());
				bzsqVO.setBzzt(EmunBzzt.FBYJ);//发包移交
				bzsqVOs[i]=bzsqVO;
			}
			bo.updateAry(bzsqVOs);
			
		}
	}
	/**
	 * 下游单据校验
	 * add by crf 
	 * @param bvos
	 * @throws Exception
	 */
	private void check(AzxxfkBVO[] bvos) throws Exception {
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

		//验收反馈数据
		YsfksjBVO[] azxxfkBVO = (YsfksjBVO[]) bo.queryByCondition(YsfksjBVO.class, " nvl(dr,0)=0 and " + YsfksjBVO.PK_BZSQ + " in (" + pk_bzsq + ") ");
		if (azxxfkBVO != null && azxxfkBVO.length > 0) {
			throw new PFBusinessException("该单据存在下游单据不能弃审！", null);
		}
	}
	/*
	 * 备注：平台编写原始脚本
	 */
	public String getCodeRemark() {
		return "	//####本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值####\n	 procUnApproveFlow (vo); \n	Object retObj=runClassCom@ \"nc.bs.trade.comstatus.BillUnApprove\", \"unApproveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@;\n	return retObj;\n";
	}
}
