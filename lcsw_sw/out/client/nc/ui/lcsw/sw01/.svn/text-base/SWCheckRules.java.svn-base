package nc.ui.lcsw.sw01;

import nc.vo.trade.checkrule.CheckRule;
import nc.vo.trade.checkrule.ICheckRule;
import nc.vo.trade.checkrule.ICheckRules;
import nc.vo.trade.checkrule.ICheckRules2;
import nc.vo.trade.checkrule.ICompareRule;
import nc.vo.trade.checkrule.ISpecialChecker;
import nc.vo.trade.checkrule.IUniqueRule;
import nc.vo.trade.checkrule.IUniqueRules;
import nc.vo.trade.checkrule.UniqueRule;


public class SWCheckRules implements ICheckRules, ICheckRules2, IUniqueRules {

	/*
	 * 对表头的不为空校验
	 * @see nc.vo.trade.checkrule.ICheckRules#getHeadCheckRules()
	 */
	public ICheckRule[] getHeadCheckRules() {
		return new CheckRule[]{
		//参考以下方法
//			/*
//			 * 所属单位不可为空	
//			 */
//			new CheckRule("公司","pk_corp",false,null,null)
		};
	}

	public ICompareRule[] getHeadCompareRules() {
		return null;
	}

	public String[] getHeadIntegerField() {
		return null;
	}

	public String[] getHeadUFDoubleField() {
		return null;
	}
	/*
	 * 对表体不为空的校验
	 * @see nc.vo.trade.checkrule.ICheckRules#getItemCheckRules(java.lang.String)
	 */
	public ICheckRule[] getItemCheckRules(String tablecode) {
		return null;
	
	}

	public ICompareRule[] getItemCompareRules(String arg0) {
		return null;
	}

	public String[] getItemIntegerField(String arg0) {
		return null;
	}

	public String[] getItemUFDoubleField(String arg0) {
		return null;
	}

	public ISpecialChecker getSpecialChecker() {
		return null;
	}
	
	/*
	 *表体不可为空
	 */
	public boolean isAllowEmptyBody(String arg0) {
		return false;
	}
	
	/**
	 * 表头唯一性校验
	 * @return
	 */
	public IUniqueRule[] getHeadUniqueRules() {
		return null;
	}

	/**
	 * 表体不重复校验
	 * @param arg0
	 * @return
	 */
	public IUniqueRule[] getItemUniqueRules(String arg0) {
		return null;
		//参考以下方法
//		return new UniqueRule[]{
//				new UniqueRule("表体“参会人员”存在重复记录",new String[]{"joinpeople"})	
//		};
	}

	
}
