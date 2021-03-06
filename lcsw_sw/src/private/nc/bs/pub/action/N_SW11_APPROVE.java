package nc.bs.pub.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.trade.business.HYPubBO;
import nc.hr.utils.PubEnv;
import nc.ui.lcsw.sw01.ref.BzxxRefModel;
import nc.uif.pub.exception.UifException;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxBVO;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.lcsw.sw10.BzfyHVO;
import nc.vo.lcsw.sw11.SjfksjBVO;
import nc.vo.lcsw.sw11.SjfksjHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValuepkUtils;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.uap.pf.PFBusinessException;
/**
 * 
 * <p>
 * <b>设计反馈数据审批动作脚本</b>
 * 
 * <p>
 * <p>
 * @since 1.0
 * @author 梁展轩
 * @time 2014-08-14 15:16:25
 */
public final class N_SW11_APPROVE extends AbstractCompiler2 {
	private static final Object[] SjfksjBVO = null;
	private Hashtable<String, Object> m_methodReturnHas = new Hashtable<String, Object>();
	private Hashtable<String, Object> m_keyHas = null;
	
	/**
	 * N_SW11_APPROVE 构造子注解。
	 */
	public N_SW11_APPROVE() {
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
			
			writeBackSjxx();
			
			
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
	 * 回写设计信息到报装信息,更新报装状态并更新报装费用   added by 梁展轩
	 * @throws BusinessException
	 */
	private void  writeBackSjxx() throws BusinessException {
		SjfksjHVO sjfksjhvo=(SjfksjHVO) getVo().getParentVO();
		SjfksjBVO[] sjfksjbvo=  (SjfksjBVO[]) getVo().getChildrenVO(); //获取设计子表数据
		if(sjfksjbvo!=null && sjfksjbvo.length > 0){
			HYPubBO bo = new HYPubBO();
			LcswBzxxHVO[] bzxxhvos=new LcswBzxxHVO[sjfksjbvo.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				LcswBzxxHVO bzxxhvo=(LcswBzxxHVO) bo.queryByPrimaryKey(LcswBzxxHVO.class, sjfksjbvo[i].getPk_bzxx());  //获取报装信息VO
				bzxxhvo.setSjjbry(sjfksjbvo[i].getJbry()); //经办人员
				bzxxhvo.setSjry(sjfksjbvo[i].getSjry());	//设计人员
				bzxxhvo.setSjth(sjfksjbvo[i].getSjth());	//设计图号
				bzxxhvo.setSjqsrq(sjfksjbvo[i].getQsrq());	//签收日期
				bzxxhvo.setSjfdrq(sjfksjbvo[i].getFdrq());	//发单日期
				bzxxhvo.setSjatrq(sjfksjbvo[i].getAtrq());	//按图日期
				bzxxhvo.setSjwgyqts(sjfksjbvo[i].getWgyqts());	//完工延期天数
				bzxxhvo.setSjyqxgr(sjfksjbvo[i].getYqxgr());	//延期修改人
				bzxxhvo.setSjyqxgrq(sjfksjbvo[i].getYqxgrq());	//延期修改日期
//				bzxxhvo.setPk_bzxx(sjfksjbvo[i].getPk_bzxx());
				bzxxhvo.setBzzt(EmunBzzt.SJCG);//报装状态 : 设计成功
			
				bzxxhvos[i]=bzxxhvo;
			}
			bo.updateAry(bzxxhvos);  
			
			//回写 报装申请 [报装状态]
			BzsqVO[] bzsqVOs=new BzsqVO[sjfksjbvo.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				if(sjfksjbvo[i].getPk_bzsq()!=null){
					BzsqVO bzsqVO=(BzsqVO) bo.queryByPrimaryKey(BzsqVO.class,sjfksjbvo[i].getPk_bzsq());
					bzsqVO.setBzzt(EmunBzzt.SJCG);//验收合格
					bzsqVOs[i]=bzsqVO;
				}
			}
			bo.updateAry(bzsqVOs);
			
			
			
			//回写报装费用表头字段  <补交勘查费> <预算工程款1期>
//			BzfyBVO bzfyBVO = (BzfyBVO) bo.queryByPrimaryKey(BzfyBVO.class,bzxxhvos[0].getPk_bzxx());
			BzfyBVO bzfyBVO[] = (BzfyBVO[]) bo.queryByCondition(BzfyBVO.class, " pk_bzxx='" + bzxxhvos[0].getPk_bzxx()+"'");
			
			if(bzfyBVO!=null && bzfyBVO.length > 0){
				BzfyHVO bzfyHVO = (BzfyHVO) bo.queryByPrimaryKey(BzfyHVO.class,bzfyBVO[0].getPk_bzfy_h());
				bzfyHVO.setBjkcf(sjfksjhvo.getBjkcf());//补交勘查费
				bzfyHVO.setYsgck1(sjfksjhvo.getYsgck());//预算工程款1期
			
				bo.update(bzfyHVO);
				
			//生成报装费用 表体
				List<BzfyBVO> list = new ArrayList<BzfyBVO>();	
				
			if(bzfyHVO.getBjkcf()!=null && bzfyHVO.getBjkcf().doubleValue()!=0 ){
				// 金额平均
				Double yushu = bzfyHVO.getBjkcf().getDouble() % bzxxhvos.length;
				Double pingjun = (bzfyHVO.getBjkcf().getDouble() - yushu) / bzxxhvos.length;
				for (int i = 0; i < bzxxhvos.length; i++) {
					BzfyBVO fybvo = new BzfyBVO();
					fybvo.setPk_bzxx(bzxxhvos[i].getPk_bzxx());
					fybvo.setPk_bzfy_h(bzfyBVO[0].getPk_bzfy_h());
					fybvo.setPk_costsubj(ValuepkUtils.BJKCF);//补交勘察费
					fybvo.setTfrq(PubEnv.getServerDate());
					fybvo.setPk_balatype(bzxxhvos[i].getFkfs());
					
					if (i == bzxxhvos.length-1)
						fybvo.setAmount(new UFDouble(pingjun + yushu));
					else
						fybvo.setAmount(new UFDouble(pingjun));
					
					list.add(fybvo);
				}
			}
			
			if(bzfyHVO.getYsgck1()!=null && bzfyHVO.getYsgck1().doubleValue()!=0 ){
				// 金额平均
				Double yushu = bzfyHVO.getYsgck1().getDouble() % bzxxhvos.length;
				Double pingjun = (bzfyHVO.getYsgck1().getDouble() - yushu) / bzxxhvos.length;
				for (int i = 0; i < bzxxhvos.length; i++) {
					BzfyBVO fybvo = new BzfyBVO();
					fybvo.setPk_bzxx(bzxxhvos[i].getPk_bzxx());
					fybvo.setPk_bzfy_h(bzfyBVO[0].getPk_bzfy_h());
					fybvo.setPk_costsubj(ValuepkUtils.YSGCK1Q);//预算工程款
					fybvo.setTfrq(PubEnv.getServerDate());
					fybvo.setPk_balatype(bzxxhvos[i].getFkfs());
					
					if (i == bzxxhvos.length-1)
						fybvo.setAmount(new UFDouble(pingjun + yushu));
					else
						fybvo.setAmount(new UFDouble(pingjun));
					
					list.add(fybvo);
				}
			}
			
			bo.insertAry(list.toArray(new BzfyBVO[list.size()]));
		}
			
		}
	}
	
}
