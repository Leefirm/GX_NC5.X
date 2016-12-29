package nc.vo.lcsw.sw10;

import java.io.Serializable;

import nc.vo.trade.pub.IBDGetCheckClass2;

/**
 * <b>报装费用获得校验类路径</b>
 * @version 1.0
 * @since 1.0
 * @author xuns
 * @time 2014-08-12 16:09:22
 */
public class SWGetCheckClass implements Serializable,IBDGetCheckClass2{

	private static final long serialVersionUID = 1L;

	public SWGetCheckClass() {
	}
	/*
	 * 获得前台校验类的全路径
	 * @see nc.vo.trade.pub.IBDGetCheckClass2#getUICheckClass()
	 */
	public String getUICheckClass() {
		return "nc.ui.lcsw.sw10.SWCardCheck";
	}
	
	/*
	 * 获得后台校验类的全路径
	 * @see nc.vo.trade.pub.IBDGetCheckClass#getCheckClass()
	 */
	public String getCheckClass() {
		return "nc.bs.lcsw.sw10.SWBusiCheck";
	}

}
