package nc.bs.lcsw.sw11;

import nc.bs.trade.business.IBDBusiCheck;
import nc.bs.trade.comcheckunique.BillIsUnique;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.trade.pub.IBDACTION;

/**
 * <b>��Ʒ������ݺ�̨У����</b>
 * @version 1.0
 * @since 1.0
 * @author ��չ��
 * @time 2014-08-14 15:16:25
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
