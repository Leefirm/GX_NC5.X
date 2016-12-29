package nc.vo.lcsw.sw10;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class JKTZPrintVO extends SuperVO {
	
	public static final String[] attrNames = new String[]{"用户名称","通知日期","报装编号","安装地址","新装入户管径","原有入户管径"
		,"勘测设计费","交款日期","全称","补交勘测设计费","交款日期","开户银行、账户","新装工程款","交款日期","全称","开户银行、账户"
		,"户表改造工程款","交款日期","全称","开户银行、账户"};
	public static final String[] attrs = new String[]{"userName","noticeDate","bzbh","azdz","newRhgj","oldRhgj"
		,"kcsjf","payDate1","fullName1","afterPayKcsjf","payDate2","openBankAccount1","newProjectPay","payDate3","fullName2","openBankAccount2"
		,"hbgzProjectPay","payDate4","fullName3","openBankAccount3"};
	
	
	private String userName;
	 private  String  noticeDate;
	 private String bzbh;
	 private String azdz;
	 private String newRhgj;
	 private String oldRhgj;
	 private String  kcsjf;
	 private  String  payDate1;
	 private String fullName1;
	 private String afterPayKcsjf;
	 private  String  payDate2;
	 private String openBankAccount1;
	 private String newProjectPay;
	 private  String  payDate3;
	 private String fullName2;
	 private String openBankAccount2;
	 private String hbgzProjectPay;
	 private  String  payDate4;
	 private String fullName3;
	 private String openBankAccount3;
	 
	
	 public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public  String  getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate( String  noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getBzbh() {
		return bzbh;
	}

	public void setBzbh(String bzbh) {
		this.bzbh = bzbh;
	}

	public String getAzdz() {
		return azdz;
	}

	public void setAzdz(String azdz) {
		this.azdz = azdz;
	}

	public String getNewRhgj() {
		return newRhgj;
	}

	public void setNewRhgj(String newRhgj) {
		this.newRhgj = newRhgj;
	}

	public String getOldRhgj() {
		return oldRhgj;
	}

	public void setOldRhgj(String oldRhgj) {
		this.oldRhgj = oldRhgj;
	}

	public String getKcsjf() {
		return kcsjf;
	}

	public void setKcsjf(String kcsjf) {
		this.kcsjf = kcsjf;
	}

	public  String  getPayDate1() {
		return payDate1;
	}

	public void setPayDate1( String  payDate1) {
		this.payDate1 = payDate1;
	}

	public String getFullName1() {
		return fullName1;
	}

	public void setFullName1(String fullName1) {
		this.fullName1 = fullName1;
	}

	public String getAfterPayKcsjf() {
		return afterPayKcsjf;
	}

	public void setAfterPayKcsjf(String afterPayKcsjf) {
		this.afterPayKcsjf = afterPayKcsjf;
	}

	public  String  getPayDate2() {
		return payDate2;
	}

	public void setPayDate2( String  payDate2) {
		this.payDate2 = payDate2;
	}

	public String getOpenBankAccount1() {
		return openBankAccount1;
	}

	public void setOpenBankAccount1(String openBankAccount1) {
		this.openBankAccount1 = openBankAccount1;
	}

	public String getNewProjectPay() {
		return newProjectPay;
	}

	public void setNewProjectPay(String newProjectPay) {
		this.newProjectPay = newProjectPay;
	}

	public  String  getPayDate3() {
		return payDate3;
	}

	public void setPayDate3( String  payDate3) {
		this.payDate3 = payDate3;
	}

	public String getFullName2() {
		return fullName2;
	}

	public void setFullName2(String fullName2) {
		this.fullName2 = fullName2;
	}

	public String getOpenBankAccount2() {
		return openBankAccount2;
	}

	public void setOpenBankAccount2(String openBankAccount2) {
		this.openBankAccount2 = openBankAccount2;
	}

	public String getHbgzProjectPay() {
		return hbgzProjectPay;
	}

	public void setHbgzProjectPay(String hbgzProjectPay) {
		this.hbgzProjectPay = hbgzProjectPay;
	}

	public  String  getPayDate4() {
		return payDate4;
	}

	public void setPayDate4( String  payDate4) {
		this.payDate4 = payDate4;
	}

	public String getFullName3() {
		return fullName3;
	}

	public void setFullName3(String fullName3) {
		this.fullName3 = fullName3;
	}

	public String getOpenBankAccount3() {
		return openBankAccount3;
	}

	public void setOpenBankAccount3(String openBankAccount3) {
		this.openBankAccount3 = openBankAccount3;
	}

	public static String[] getAttrnames() {
		return attrNames;
	}

	public static String[] getAttrs() {
		return attrs;
	}

	
     	 
	 
	 
	 
	
	

	@Override
	public String getPKFieldName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParentPKFieldName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

}
