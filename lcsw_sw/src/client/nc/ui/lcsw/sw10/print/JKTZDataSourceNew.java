package nc.ui.lcsw.sw10.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComponent;



import nc.bs.framework.common.NCLocator;
import nc.bs.framework.systool.Systool;
import nc.ui.lcsw.sw10.ClientUI;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.print.IDataSource;
import nc.vo.lcsw.sw10.JKTZPrintVO;
import nc.vo.lcsw.sw10.itf.JKTZPrintService;

public class JKTZDataSourceNew implements IDataSource {

	private static final long serialVersionUID = 1L;

	private String pk_bzsq;
	private JKTZPrintVO vo = null;

	public JKTZDataSourceNew() {
	}

	public JKTZDataSourceNew(String pk_bzsq) throws Exception {
		this.pk_bzsq = pk_bzsq;
		vo =(JKTZPrintVO) NCLocator.getInstance().lookup(JKTZPrintService.class).queryJKTZPrint(pk_bzsq);
		
	}

	public String[] getItemValuesByExpress(String itemExpress) {
		if (vo == null) {
			return new String[] {};
		}
		if("pk_bzsq".equals(itemExpress)) {
			return new String[]{pk_bzsq};
		}
		return new String[] { (String) vo.getAttributeValue(itemExpress).toString() };
	}

	public boolean isNumber(String itemExpress) {
		// TODO Auto-generated method stub
		return false;
	}

	public String[] getDependentItemExpressByExpress(String itemExpress) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getAllDataItemExpress() {
		return new String[] { "userName", "noticeDate", "bzbh", "azdz",
				"newRhgj", "oldRhgj", "kcsjf", "payDate1", "fullName1",
				"afterPayKcsjf", "payDate2", "openBankAccount1",
				"newProjectPay", "payDate3", "fullName2", "openBankAccount2",
				"hbgzProjectPay", "payDate4", "fullName3", "openBankAccount3","pk_bzsq" };
	}

	public String[] getAllDataItemNames() {
		return new String[] { "用户名称", "通知日期", "报装编号", "安装地址", "新装入户管径",
				"原有入户管径", "勘测设计费", "交款日期", "全称", "补交勘测设计费", "交款日期", "开户银行、账户",
				"新装工程款", "交款日期", "全称", "开户银行、账户", "户表改造工程款", "交款日期", "全称",
				"开户银行、账户" ,"报装申请主键"};
	}

	public String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}

}
