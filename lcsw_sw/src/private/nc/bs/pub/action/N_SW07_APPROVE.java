package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.trade.business.HYPubBO;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw07.AzxxfkBVO;
import nc.vo.lcsw.sw07.AzxxfkHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;
/**
 * 
 * <p>
 * <b>安装反馈数据审批动作脚本</b>
 * 
 * <p>
 * <p>
 * @since 1.0
 * @author 梁展轩
 * @time 2014-09-03 17:20:11
 */
public final class N_SW07_APPROVE extends AbstractCompiler2 {
	private Hashtable<String, Object> m_methodReturnHas = new Hashtable<String, Object>();
	private Hashtable<String, Object> m_keyHas = null;
	
	/**
	 * N_SW07_APPROVE 构造子注解。
	 */
	public N_SW07_APPROVE() {
		super();
	}
	
	/*
	 * 备注：平台编写规则类 接口执行类
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			// ####该组件为单动作工作流处理开始...不能进行修改####
			Object m_sysflowObj = procActionFlow(vo);
			if (m_sysflowObj != null) {
				return m_sysflowObj;
			}
			
			
			writeBack();
			
			// ####该组件为单动作工作流处理结束...不能进行修改####
			Object retObj = runClass("nc.bs.trade.comstatus.BillApprove", "approveBill", "nc.vo.pub.AggregatedValueObject:01", vo, m_keyHas, m_methodReturnHas);
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
		return "	//####该组件为单动作工作流处理开始...不能进行修改####\n	procActionFlow@@;\n	//####该组件为单动作工作流处理结束...不能进行修改####\n	Object retObj  =runClassCom@ \"nc.bs.trade.comstatus.BillApprove\", \"approveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@; \n	return retObj;\n";
	}
	
	
	/**
	 * 回写安装信息到报装信息,并更新报装状态   added by 梁展轩
	 * @throws BusinessException
	 */
	private void  writeBack() throws BusinessException {
		AzxxfkBVO[] azxxfkBVO=(AzxxfkBVO[]) getVo().getChildrenVO(); //获取安装信息反馈子表数据
		AzxxfkHVO headvo = (AzxxfkHVO) getVo().getParentVO();
		if(azxxfkBVO!=null && azxxfkBVO.length>0){
			HYPubBO bo = new HYPubBO();
			LcswBzxxHVO[] bzxxhvos=new LcswBzxxHVO[azxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				LcswBzxxHVO bzxxhvo= (LcswBzxxHVO) bo.queryByPrimaryKey(LcswBzxxHVO.class, azxxfkBVO[i].getPk_bzxx());
				bzxxhvo.setAzwgyqts(azxxfkBVO[i].getWgyqts());//完工延期天数
				bzxxhvo.setAzrq(azxxfkBVO[i].getAzrq());	//安装日期
				bzxxhvo.setAzfkrq(azxxfkBVO[i].getAzfkrq());//安装反馈日期
				bzxxhvo.setYjazrq(azxxfkBVO[i].getYjazrq());//移交安装日期
				bzxxhvo.setYsry(azxxfkBVO[i].getYsry());	//验收人员
				bzxxhvo.setJgrq(azxxfkBVO[i].getJgrq());	//竣工日期
				bzxxhvo.setYsdh(azxxfkBVO[i].getYsdh());	//验收电话
				bzxxhvo.setHth(azxxfkBVO[i].getHth());		//合同号
				bzxxhvo.setZlfkrq(azxxfkBVO[i].getZlfkrq());//资料反馈日期
				
				bzxxhvo.setKgrq(azxxfkBVO[i].getKgrq());//开工日期
				bzxxhvo.setYjbwysrq(headvo.getYjbwysrq());//移交表位验收日期
				bzxxhvo.setAzbj(azxxfkBVO[i].getAzbj());//安装表径
				bzxxhvo.setBiaobie(azxxfkBVO[i].getBiaobie());//水表类型
				bzxxhvo.setCkxd(azxxfkBVO[i].getCkxd());//出库行度
				
				bzxxhvo.setBiaoma(azxxfkBVO[i].getBiaoma());//表码
				bzxxhvo.setBianma(azxxfkBVO[i].getBianma());//编码
				bzxxhvo.setLd(azxxfkBVO[i].getLd());//路段
				bzxxhvo.setLdqd(azxxfkBVO[i].getLdqd());//路段起点
				bzxxhvo.setLdzd(azxxfkBVO[i].getLdzd());//路段终点
				bzxxhvo.setSggcai(azxxfkBVO[i].getSggcai());//施工管材
				bzxxhvo.setSggchang(azxxfkBVO[i].getSggchang());//施工管长
				bzxxhvo.setChandi(azxxfkBVO[i].getChandi());//产地
				bzxxhvo.setBiaowei(azxxfkBVO[i].getBiaowei());//表位
				// 返写施工部门 施工负责人 施工联系电话
				bzxxhvo.setSgbm(azxxfkBVO[i].getSgbm());//施工部门
				bzxxhvo.setSgfzr(azxxfkBVO[i].getSgfzr());//施工负责人
				bzxxhvo.setSglxdh(azxxfkBVO[i].getSglxdh());//施工联系电话
				
				bzxxhvo.setBzzt(EmunBzzt.AZWC);//安装完成
				bzxxhvos[i]=bzxxhvo;
			}
			bo.updateAry(bzxxhvos);
			
			//回写 报装申请 [报装状态]
			BzsqVO[] bzsqVOs=new BzsqVO[azxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				if(azxxfkBVO[i].getPk_bzsq()!=null){
					BzsqVO bzsqVO=(BzsqVO) bo.queryByPrimaryKey(BzsqVO.class,azxxfkBVO[i].getPk_bzsq());
					bzsqVO.setBzzt(EmunBzzt.AZWC);//安装完成
					bzsqVOs[i]=bzsqVO;
				}
			}
			bo.updateAry(bzsqVOs);
		}
		
	}
}
