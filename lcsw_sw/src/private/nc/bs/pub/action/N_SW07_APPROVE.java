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
 * <b>��װ�����������������ű�</b>
 * 
 * <p>
 * <p>
 * @since 1.0
 * @author ��չ��
 * @time 2014-09-03 17:20:11
 */
public final class N_SW07_APPROVE extends AbstractCompiler2 {
	private Hashtable<String, Object> m_methodReturnHas = new Hashtable<String, Object>();
	private Hashtable<String, Object> m_keyHas = null;
	
	/**
	 * N_SW07_APPROVE ������ע�⡣
	 */
	public N_SW07_APPROVE() {
		super();
	}
	
	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			// ####�����Ϊ������������������ʼ...���ܽ����޸�####
			Object m_sysflowObj = procActionFlow(vo);
			if (m_sysflowObj != null) {
				return m_sysflowObj;
			}
			
			
			writeBack();
			
			// ####�����Ϊ��������������������...���ܽ����޸�####
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
	 * ��ע��ƽ̨��дԭʼ�ű�
	 */
	public String getCodeRemark() {
		return "	//####�����Ϊ������������������ʼ...���ܽ����޸�####\n	procActionFlow@@;\n	//####�����Ϊ��������������������...���ܽ����޸�####\n	Object retObj  =runClassCom@ \"nc.bs.trade.comstatus.BillApprove\", \"approveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@; \n	return retObj;\n";
	}
	
	
	/**
	 * ��д��װ��Ϣ����װ��Ϣ,�����±�װ״̬   added by ��չ��
	 * @throws BusinessException
	 */
	private void  writeBack() throws BusinessException {
		AzxxfkBVO[] azxxfkBVO=(AzxxfkBVO[]) getVo().getChildrenVO(); //��ȡ��װ��Ϣ�����ӱ�����
		AzxxfkHVO headvo = (AzxxfkHVO) getVo().getParentVO();
		if(azxxfkBVO!=null && azxxfkBVO.length>0){
			HYPubBO bo = new HYPubBO();
			LcswBzxxHVO[] bzxxhvos=new LcswBzxxHVO[azxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				LcswBzxxHVO bzxxhvo= (LcswBzxxHVO) bo.queryByPrimaryKey(LcswBzxxHVO.class, azxxfkBVO[i].getPk_bzxx());
				bzxxhvo.setAzwgyqts(azxxfkBVO[i].getWgyqts());//�깤��������
				bzxxhvo.setAzrq(azxxfkBVO[i].getAzrq());	//��װ����
				bzxxhvo.setAzfkrq(azxxfkBVO[i].getAzfkrq());//��װ��������
				bzxxhvo.setYjazrq(azxxfkBVO[i].getYjazrq());//�ƽ���װ����
				bzxxhvo.setYsry(azxxfkBVO[i].getYsry());	//������Ա
				bzxxhvo.setJgrq(azxxfkBVO[i].getJgrq());	//��������
				bzxxhvo.setYsdh(azxxfkBVO[i].getYsdh());	//���յ绰
				bzxxhvo.setHth(azxxfkBVO[i].getHth());		//��ͬ��
				bzxxhvo.setZlfkrq(azxxfkBVO[i].getZlfkrq());//���Ϸ�������
				
				bzxxhvo.setKgrq(azxxfkBVO[i].getKgrq());//��������
				bzxxhvo.setYjbwysrq(headvo.getYjbwysrq());//�ƽ���λ��������
				bzxxhvo.setAzbj(azxxfkBVO[i].getAzbj());//��װ����
				bzxxhvo.setBiaobie(azxxfkBVO[i].getBiaobie());//ˮ������
				bzxxhvo.setCkxd(azxxfkBVO[i].getCkxd());//�����ж�
				
				bzxxhvo.setBiaoma(azxxfkBVO[i].getBiaoma());//����
				bzxxhvo.setBianma(azxxfkBVO[i].getBianma());//����
				bzxxhvo.setLd(azxxfkBVO[i].getLd());//·��
				bzxxhvo.setLdqd(azxxfkBVO[i].getLdqd());//·�����
				bzxxhvo.setLdzd(azxxfkBVO[i].getLdzd());//·���յ�
				bzxxhvo.setSggcai(azxxfkBVO[i].getSggcai());//ʩ���ܲ�
				bzxxhvo.setSggchang(azxxfkBVO[i].getSggchang());//ʩ���ܳ�
				bzxxhvo.setChandi(azxxfkBVO[i].getChandi());//����
				bzxxhvo.setBiaowei(azxxfkBVO[i].getBiaowei());//��λ
				// ��дʩ������ ʩ�������� ʩ����ϵ�绰
				bzxxhvo.setSgbm(azxxfkBVO[i].getSgbm());//ʩ������
				bzxxhvo.setSgfzr(azxxfkBVO[i].getSgfzr());//ʩ��������
				bzxxhvo.setSglxdh(azxxfkBVO[i].getSglxdh());//ʩ����ϵ�绰
				
				bzxxhvo.setBzzt(EmunBzzt.AZWC);//��װ���
				bzxxhvos[i]=bzxxhvo;
			}
			bo.updateAry(bzxxhvos);
			
			//��д ��װ���� [��װ״̬]
			BzsqVO[] bzsqVOs=new BzsqVO[azxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				if(azxxfkBVO[i].getPk_bzsq()!=null){
					BzsqVO bzsqVO=(BzsqVO) bo.queryByPrimaryKey(BzsqVO.class,azxxfkBVO[i].getPk_bzsq());
					bzsqVO.setBzzt(EmunBzzt.AZWC);//��װ���
					bzsqVOs[i]=bzsqVO;
				}
			}
			bo.updateAry(bzsqVOs);
		}
		
	}
}