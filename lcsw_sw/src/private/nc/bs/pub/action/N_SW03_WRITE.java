/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('WRITE', 'N_SW03_WRITE', 0, 'N', 'SW03', '140807173102430FUKB5', NULL, '0001', '2014-08-07 17:31:02')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('����', NULL, NULL, 'WRITE', 'N', 'Y', 0, 'Y', '14080717310253261I54', 'SW03', NULL, '2014-08-07 17:31:02')
//go
package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.dao.BaseDAO;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.lcsw.sw03.KcpgAggVO;
import nc.vo.lcsw.sw03.KcpgHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע������:<b><code>SW03</code></b> �ı��� <tt>WRITE</tt> ��ִ̬���ࡣ
 * 
 * @since �������ڣ�2014-08-07 17:31:02
 * @version UAP 5.x
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW03_WRITE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW03_WRITE ������ע�⡣
	 */
	public N_SW03_WRITE() {
		super();
	}

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			// ****���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ****
			Object retObj = null;
			
//			//panqhУ��
//			/*
//			 * �����߼���ϵ���������ȵģ�������Ʒѵ�[��������]<=[�����ɹ�����]<=[��ǰ����]��
// 			�����ȵģ���װ�����[�������]<=[�����ɹ�����]<=[��ǰ����]��
//			 */
//			KcpgAggVO aggvo = (KcpgAggVO) vo.m_preValueVo;
//			KcpgHVO kcvo = (KcpgHVO) aggvo.getParentVO();
//			String sql = "select sfsyx,pk_bzsq from lcsw_bzsq where nvl(dr,0)=0 and pk_jobbasfil='" + kcvo.getPk_jobbasfil()+ "'";
//			Object[] objs = (Object[]) new BaseDAO().executeQuery(sql, new ArrayProcessor());
//			String issy = (String) objs[0];
////				��ǰ����
//			UFDate dmakedate = kcvo.getDmakedate();
//			//�����ɹ�����
//			UFDate kcpgrq = kcvo.getKcpgrq();
//			//[�����ɹ�����]>[��ǰ����]
//			if(dmakedate.compareTo(kcpgrq)<0) {
//				throw new BusinessException("��ǰ����:" + dmakedate + "������ڵ��ڿ����ɹ�����:" + kcpgrq);
//			}
//			if("Y".equals(issy) || "y".equals(issy)) {
//				//�����ȵ����
//			}
//			else {//������:������Ʒѵ�[��������]<=[�����ɹ�����]<=[��ǰ����]��
//				String pk_bzsq = (String) objs[1];
//				String xzrqSql = "select xzrq from lcsw_bzfy_b where pk_costsubj='0001AA1000000000KDSS' and pk_bzfy_h in (select pk_bzfy_h from lcsw_bzfy_h where pk_bzsq='"+pk_bzsq+"' and nvl(dr,0)=0)";
//				
//				String xzrqstr = (String) new BaseDAO().executeQuery(xzrqSql, new ColumnProcessor());
//				
//				//��������
//				UFDate xzrq = new UFDate(xzrqstr);
////				throw new BusinessException(dmakedate + ":" +kcpgrq+ ":"+xzrq);
//				//[��������]>[�����ɹ�����]
//				if(kcpgrq.compareTo(xzrq)<0) {
//					throw new BusinessException("�������ȿ����ɹ�����:" + kcpgrq + "������ڵ�����������:" + xzrq);
//				}
//				
//			}
			
			
			// ****��Ҫ˵�������ɵ�ҵ���������������Ҫ�����޸�****
			// ����˵��:�������淽��
			// ���ɵ��ݺ�
			nc.bs.pub.billcodemanage.BillcodeGenerater gene = new nc.bs.pub.billcodemanage.BillcodeGenerater();
			if (nc.vo.jcom.lang.StringUtil.isEmpty(((String) vo.m_preValueVo.getParentVO().getAttributeValue("vbillcode")))) {
				String billno = gene.getBillCode(vo.m_billType, vo.m_coId, null, null);
				vo.m_preValueVo.getParentVO().setAttributeValue("vbillcode", billno);
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
	 * ��ע��ƽ̨��дԭʼ�ű�
	 */
	public String getCodeRemark() {
		return "	//****���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ****\n	Object retObj  =null;\n	//****��Ҫ˵�������ɵ�ҵ���������������Ҫ�����޸�****\n	//����˵��:�������淽��\n	// ���ɵ��ݺ�\n	nc.bs.pub.billcodemanage.BillcodeGenerater gene  =\n	new nc.bs.pub.billcodemanage.BillcodeGenerater ();\n	if ( nc.vo.jcom.lang.StringUtil.isEmpty ( ( (String)vo.m_preValueVo.getParentVO ().getAttributeValue ( \"vbillno\")))) {\n		String billno  = gene.getBillCode (vo.m_billType,vo.m_coId,null,null);\n		vo.m_preValueVo.getParentVO ().setAttributeValue ( \"vbillno\",billno);\n	}\n	retObj  =runClassCom@ \"nc.bs.trade.comsave.BillSave\", \"saveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@;\n	//*************************************************\n	return retObj;\n";
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