package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.trade.business.HYPubBO;
//import nc.tb.app.adjustbill.ui.MessageShowDlg;
import nc.ui.pub.beans.MessageDialog;
import nc.uif.pub.exception.UifException;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.lcsw.sw11.SjfksjBVO;
import nc.vo.lcsw.sw11.SjfksjHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValuepkUtils;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：设计反馈数据的提交 单据动作执行中的动态执行类的动态执行类。
 * 
 * 创建日期：2014-08-14 15:16:25
 * 
 * @author 梁展轩
 */
public class N_SW11_UNAPPROVE extends AbstractCompiler2 {
	private Hashtable<String, Object> m_methodReturnHas = new Hashtable<String, Object>();
	private Hashtable<String, Object> m_keyHas = null;

	/**
	 * N_SW11_UNAPPROVE 构造子注解。
	 */
	public N_SW11_UNAPPROVE() {
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
			
			SjfksjHVO sjfksjhvo=(SjfksjHVO) getVo().getParentVO();
			SjfksjBVO[] sjfksjbvo=  (SjfksjBVO[]) getVo().getChildrenVO(); //获取设计子表数据
			if(sjfksjbvo!=null && sjfksjbvo.length > 0){
				HYPubBO bo = new HYPubBO();
				LcswBzxxHVO[] bzxxhvos=new LcswBzxxHVO[sjfksjbvo.length];
				for (int i = 0; i < bzxxhvos.length; i++) {
					BzfyBVO[] bzfyBVO=(BzfyBVO[]) bo.queryByCondition(BzfyBVO.class, " pk_bzxx='"+ sjfksjbvo[i].getPk_bzxx()+"' and nvl(dr,0)=0");  //获取报装信息VO
					if(bzfyBVO!=null && bzfyBVO.length>0){
						for (int j = 0; j < bzfyBVO.length; j++) {
							if(sjfksjhvo.getBjkcf()!=null && sjfksjhvo.getBjkcf().doubleValue()!=0){
								if(bzfyBVO[j].getPk_costsubj().equals(ValuepkUtils.BJKCF)){									
									throw  new BusinessException("请先在<报装费用>手动删除表体的补交勘察费");
								}
							}
							if(sjfksjhvo.getYsgck()!=null && sjfksjhvo.getYsgck().doubleValue()!=0){
								if(bzfyBVO[j].getPk_costsubj().equals(ValuepkUtils.YSGCK1Q)){									
									throw  new BusinessException("请先在<报装费用>手动删除表体的预算工程费1期");
								}
							}
						}
					}
				}
			}
			
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
		SjfksjBVO[] sjfksjbvo=  (SjfksjBVO[]) getVo().getChildrenVO(); //获取设计子表数据
		if(sjfksjbvo!=null && sjfksjbvo.length > 0){
			HYPubBO bo = new HYPubBO();
			LcswBzxxHVO[] bzxxhvos=new LcswBzxxHVO[sjfksjbvo.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				LcswBzxxHVO bzxxhvo=(LcswBzxxHVO) bo.queryByPrimaryKey(LcswBzxxHVO.class, sjfksjbvo[i].getPk_bzxx());  //获取报装信息VO
				bzxxhvo.setBzzt(EmunBzzt.KCCG);//报装状态 : 勘察成功
				bzxxhvos[i]=bzxxhvo;
			}
			bo.updateAry(bzxxhvos);  
			
			
			//回写 报装申请 [报装状态]
			BzsqVO[] bzsqVOs=new BzsqVO[sjfksjbvo.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				BzsqVO bzsqVO=(BzsqVO) bo.queryByPrimaryKey(BzsqVO.class,sjfksjbvo[i].getPk_bzsq());
				bzsqVO.setBzzt(EmunBzzt.KCCG);//报装状态 : 勘察成功
				bzsqVOs[i]=bzsqVO;
			}
			bo.updateAry(bzsqVOs);
		}
		
	}

	/*
	 * 备注：平台编写原始脚本
	 */
	public String getCodeRemark() {
		return "	//####本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值####\n	 procUnApproveFlow (vo); \n	Object retObj=runClassCom@ \"nc.bs.trade.comstatus.BillUnApprove\", \"unApproveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@;\n	return retObj;\n";
	}
}
