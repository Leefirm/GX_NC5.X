/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('UNAPPROVE', 'N_SW08_UNAPPROVE', 0, 'N', 'SW08', '1408211129298162Z4N6',NULL, '0001', '2014-08-21 11:29:29')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('����', NULL, NULL, 'UNAPPROVE', 'N', 'Y', 0, 'Y', '140821112929916RTJUL', 'SW08', NULL, '2014-08-21 11:29:29')
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
 * ��ע������:<b><code>SW08</code></b> ������  <tt>UNAPPROVE</tt> ��ִ̬���ࡣ
 * 
 * @since �������ڣ�2014-08-21 11:29:29
 * @version UAP 5.x 
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW08_UNAPPROVE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW08_UNAPPROVE ������ע�⡣
	 */
	public N_SW08_UNAPPROVE() {
		super();
	}

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			// ****���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ****
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
	 * ��д��װ״̬
	 * @throws UifException
	 */
	private void writeBackBzzt() throws UifException {
		YsfksjBVO[] ysxxfkBVO=(YsfksjBVO[]) getVo().getChildrenVO(); //��ȡ������Ϣ�����ӱ�����
		if(ysxxfkBVO!=null && ysxxfkBVO.length>0){
			HYPubBO bo = new HYPubBO();
			LcswBzxxHVO[] bzxxhvos=new LcswBzxxHVO[ysxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				LcswBzxxHVO bzxxhvo= (LcswBzxxHVO) bo.queryByPrimaryKey(LcswBzxxHVO.class, ysxxfkBVO[i].getPk_bzxx());
//				bzxxhvo.setBzzt(EmunBzzt.YSBHG);//���ղ��ϸ�
				bzxxhvos[i]=bzxxhvo;
			}
			bo.updateAry(bzxxhvos);
			
			//��д ��װ���� [��װ״̬]
			BzsqVO[] bzsqVOs=new BzsqVO[ysxxfkBVO.length];
			for (int i = 0; i < bzxxhvos.length; i++) {
				BzsqVO bzsqVO=(BzsqVO) bo.queryByPrimaryKey(BzsqVO.class,ysxxfkBVO[i].getPk_bzsq());
//				bzsqVO.setBzzt(EmunBzzt.YSBHG);//���ղ��ϸ�
				bzsqVOs[i]=bzsqVO;
			}
			bo.updateAry(bzsqVOs);
		}
	}

	/*
	 * ��ע��ƽ̨��дԭʼ�ű�
	 */
	public String getCodeRemark() {
		return "	//****���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ****\n	procUnApproveFlow (vo); \n	Object retObj  =runClassCom@ \"nc.bs.trade.comstatus.BillUnApprove\", \"unApproveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@;\n	return retObj;\n";
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
}
