package nc.bs.lcsw.sw08;

import nc.bs.trade.business.IBDBusiCheck;
import nc.bs.trade.comcheckunique.BillIsUnique;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.trade.pub.IBDACTION;

/**
 * <b>���շ�����Ϣ��̨У����</b>
 * @version 1.0
 * @since 1.0
 * @author xuns
 * @time 2014-08-21 11:55:18
 */
public class SWBusiCheck implements IBDACTION, IBDBusiCheck {
	
	public SWBusiCheck() {
		
	}

	public void check(int intBdAction, AggregatedValueObject vo, Object userObj)
			throws Exception {
		//�ο� ���·���
	// �Ա������У��
	//		if(intBdAction == SAVE){
	//			
	//			BillIsUnique billIsUnique = new BillIsUnique();
	//			
	//			ArrayList<String[]> list1 = new ArrayList<String []>();
	//			list1.add(new String[]{"code"});
	//			try{
	//				billIsUnique.checkBillIsUnique(vo, list1);
	//			}catch(BusinessException e){
	//				throw new BusinessException("�á����롱�Ѿ�����!");
	//			}
	//		  }

		}
	

	public void dealAfter(int intBdAction, AggregatedValueObject billVo,
			Object userObj) throws Exception {}
		
}