/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('APPROVE', 'N_SW08_APPROVE', 0, 'N', 'SW08', '140821112929363E6HHR', NULL, '0001', '2014-08-21 11:29:29')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('����', NULL, NULL, 'APPROVE', 'N', 'Y', 0, 'Y', '1408211129294634AMQL', 'SW08', NULL, '2014-08-21 11:29:29')
//go

package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.trade.business.HYPubBO;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw08.YsfksjBVO;
import nc.vo.lcsw.sw08.YsfksjHVO;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.lcsw.sw10.BzfyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע������:<b><code>SW08</code></b> ������ <tt>APPROVE</tt> ��ִ̬���ࡣ
 * 
 * @since �������ڣ�2014-08-21 11:29:29
 * @version UAP 5.x 
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW08_APPROVE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW08_APPROVE ������ע�⡣
	 */
	public N_SW08_APPROVE() {
		super();
	}

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {

			super.m_tmpVo = vo;
			// ****�����Ϊ����������������ʼ...���ܽ����޸�****
			Object m_sysflowObj = procActionFlow(vo);
			if (m_sysflowObj != null) {
				return m_sysflowObj;
			}
			
			writeBack();
			
			// ****�����Ϊ�������������������...���ܽ����޸�****
			Object retObj = runClass("nc.bs.trade.comstatus.BillApprove",
					"approveBill", "nc.vo.pub.AggregatedValueObject:01", vo,
					m_keyHas, m_methodReturnHas);
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
		return "	//****�����Ϊ����������������ʼ...���ܽ����޸�****\n	procActionFlow@@;\n	//****�����Ϊ�������������������...���ܽ����޸�****\n	Object retObj  =runClassCom@ \"nc.bs.trade.comstatus.BillApprove\", \"approveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@; \n	return retObj;\n";
	}

	/*
	 * ��ע�����ýű�������HAS
	 */
	private void setParameter(String key, Object val) {
		if (m_keyHas == null) {
			m_keyHas = new Hashtable();
		}
		if (val != null) {
			m_keyHas.put(key, val);
		}
	}
	
	/**
	 * ��д������Ϣ����װ��Ϣ,�����±�װ״̬   added by ��չ��
	 * @throws BusinessException
	 */
	private void  writeBack() throws BusinessException {
		YsfksjHVO ysxxfkHVO= (YsfksjHVO) getVo().getParentVO();
		YsfksjBVO[] ysxxfkBVO=(YsfksjBVO[]) getVo().getChildrenVO(); //��ȡ������Ϣ�����ӱ�����
		if(ysxxfkBVO!=null && ysxxfkBVO.length>0){
			HYPubBO bo = new HYPubBO();
			LcswBzxxHVO[] bzxxhvos=new LcswBzxxHVO[ysxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				LcswBzxxHVO bzxxhvo= (LcswBzxxHVO) bo.queryByPrimaryKey(LcswBzxxHVO.class, ysxxfkBVO[i].getPk_bzxx());
				bzxxhvo.setBianma(ysxxfkBVO[i].getBianma());//����
				bzxxhvo.setBhgyy(ysxxfkBVO[i].getBhgyy());	//������ԭ��
				bzxxhvo.setBiaobie(ysxxfkBVO[i].getBiaobie());//���
				bzxxhvo.setBiaoma(ysxxfkBVO[i].getBiaoma());//����
				bzxxhvo.setBiaowei(ysxxfkBVO[i].getBiaowei());	//��λ
				bzxxhvo.setYshgbz(ysxxfkBVO[i].getYshgbz());	//���պϸ��־
				bzxxhvo.setYswgyqts(ysxxfkBVO[i].getWgyqts());	//�����깤��������
				bzxxhvo.setYsy(ysxxfkBVO[i].getYsy());		//����Ա
				bzxxhvo.setYsxd(ysxxfkBVO[i].getYsxd());//�����ж�
				bzxxhvo.setYsrq(ysxxfkBVO[i].getYsrq());//��������
				bzxxhvo.setZbrq(ysxxfkBVO[i].getZbrq());//װ������
				bzxxhvo.setYjysrq(ysxxfkBVO[i].getYjysrq());//�ƽ���������
				bzxxhvo.setYjzgrq(ysxxfkBVO[i].getYjzgrq());//�ƽ���������
				bzxxhvo.setCchd(ysxxfkBVO[i].getCchd());//�����ж�
				bzxxhvo.setChandi(ysxxfkBVO[i].getChandi());//����
				
				if(ysxxfkBVO[i].getYshgbz() == null || !ysxxfkBVO[i].getYshgbz().booleanValue()) {
					bzxxhvo.setBzzt(EmunBzzt.YSBHG);//���ղ��ϸ�
				}
				else {
					bzxxhvo.setBzzt(EmunBzzt.YCHG);//���պϸ�
				}
				//panqh
				//���յ绰
				bzxxhvo.setYsdh(ysxxfkHVO.getYslxdh());
				//���Ϸ�������
				bzxxhvo.setZlfkrq(ysxxfkHVO.getZlfkrq());
				
				
				bzxxhvos[i]=bzxxhvo;
			}
			bo.updateAry(bzxxhvos);
			
			//��д ��װ���� [��װ״̬]
			BzsqVO[] bzsqVOs=new BzsqVO[ysxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				if(ysxxfkBVO[i].getPk_bzsq()!=null){
					BzsqVO bzsqVO=(BzsqVO) bo.queryByPrimaryKey(BzsqVO.class,ysxxfkBVO[i].getPk_bzsq());
					
					if(ysxxfkBVO[i].getYshgbz() == null || !ysxxfkBVO[i].getYshgbz().booleanValue()) {
						bzsqVO.setBzzt(EmunBzzt.YSBHG);//���ղ��ϸ�
					}
					else {
						bzsqVO.setBzzt(EmunBzzt.YCHG);//���պϸ�
					}
					
					bzsqVOs[i]=bzsqVO;
				}
			}
			bo.updateAry(bzsqVOs);
			
			//��д��װ���ñ�ͷ�ֶ�  <���㹤�̿�>
//			BzfyBVO bzfyBVO = (BzfyBVO) bo.queryByPrimaryKey(BzfyBVO.class,bzxxhvos[0].getPk_bzxx());
			BzfyBVO bzfyBVO[] = (BzfyBVO[]) bo.queryByCondition(BzfyBVO.class, " pk_bzxx='" + bzxxhvos[0].getPk_bzxx()+"'");
			
			if(bzfyBVO!=null && bzfyBVO.length > 0){
				BzfyHVO bzfyHVO = (BzfyHVO) bo.queryByPrimaryKey(BzfyHVO.class,bzfyBVO[0].getPk_bzfy_h());
				bzfyHVO.setJsgck(ysxxfkHVO.getJsgck());//���㹤�̿�
				bo.update(bzfyHVO);
			}
		}
	
	}
}
