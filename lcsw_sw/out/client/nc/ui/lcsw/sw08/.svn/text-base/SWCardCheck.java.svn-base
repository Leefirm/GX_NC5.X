package nc.ui.lcsw.sw08;

import java.awt.Container;

import nc.ui.trade.check.BeforeActionCHK;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.trade.checkrule.VOChecker;

public class SWCardCheck extends BeforeActionCHK {

	
	public void runBatchClass(Container parent, String billType,
			String actionName, AggregatedValueObject[] vos, Object[] obj)
			throws Exception {
		
	}

	public void runClass(Container parent, String billType, String actionName,
			AggregatedValueObject vo, Object obj) throws Exception {
		if(!VOChecker.check(vo,new SWCheckRules() )){
			throw new BusinessException(VOChecker.getErrorMessage());
			}
		}


}
