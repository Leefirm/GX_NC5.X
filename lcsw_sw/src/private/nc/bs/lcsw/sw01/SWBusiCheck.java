package nc.bs.lcsw.sw01;

import nc.bs.trade.business.IBDBusiCheck;
import nc.bs.trade.comcheckunique.BillIsUnique;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.trade.pub.IBDACTION;

/**
 * <b>报装信息后台校验类</b>
 * @version 1.0
 * @since 1.0
 * @author 梁展轩
 * @time 2014-08-04 15:42:26
 */
public class SWBusiCheck implements IBDACTION, IBDBusiCheck {
	
	public SWBusiCheck() {
		
	}

	public void check(int intBdAction, AggregatedValueObject vo, Object userObj)
			throws Exception {
		//参考 以下方法
	// 对保存进行校验
	//		if(intBdAction == SAVE){
	//			
	//			BillIsUnique billIsUnique = new BillIsUnique();
	//			
	//			ArrayList<String[]> list1 = new ArrayList<String []>();
	//			list1.add(new String[]{"code"});
	//			try{
	//				billIsUnique.checkBillIsUnique(vo, list1);
	//			}catch(BusinessException e){
	//				throw new BusinessException("该“编码”已经存在!");
	//			}
	//		  }

		}
	

	public void dealAfter(int intBdAction, AggregatedValueObject billVo,
			Object userObj) throws Exception {}
		
}
