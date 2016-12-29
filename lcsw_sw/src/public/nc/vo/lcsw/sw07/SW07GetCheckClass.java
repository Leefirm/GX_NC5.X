package nc.vo.lcsw.sw07;

import java.io.Serializable;

import nc.vo.trade.pub.IBDGetCheckClass2;

/**
 * <b>安装反馈数据获得校验类路径</b>
 * @version 1.0
 * @since 1.0
 * @author 梁展轩
 * @time 2014-09-03 17:20:11
 */
public class SW07GetCheckClass implements Serializable,IBDGetCheckClass2{

	private static final long serialVersionUID = 1L;

	public SW07GetCheckClass() {
	}
	/*
	 * 获得前台校验类的全路径
	 * @see nc.vo.trade.pub.IBDGetCheckClass2#getUICheckClass()
	 */
	public String getUICheckClass() {
		return "nc.ui.lcsw.sw07.SW07CardCheck";
	}
	
	/*
	 * 获得后台校验类的全路径
	 * @see nc.vo.trade.pub.IBDGetCheckClass#getCheckClass()
	 */
	public String getCheckClass() {
		return "nc.bs.lcsw.sw07.SW07BusiCheck";
	}

}
