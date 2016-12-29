package nc.vo.lcsw.sw11;

import nc.vo.pub.SuperVO;

public class ZZSHBPrintVO extends SuperVO{
	
	public static final String[] attrNames = new String[]{"工程名称","项目编码","联系电话","联系人","进户管径","进户表径"
		,"勘测设计费","经办人","勘察人员","出资方式","用水性质","水表类型","水表用途","通知日期"};
	public static final String[] attrs = new String[]{"gcmc","jobcode","lxdh","lxr","intoPipe","intoWatch"
		,"kcsjf","jbr","kcry","czway","ysxz","sblx","sbyt","noticeDate"};
	
	private String gcmc;
	private String jobcode;
	private String lxdh;
	private String lxr;
	private String intoPipe;
	private String intoWatch;
	private String kcsjf;
	private String jbr;
	private String kcry;
	private String czway;
	private String ysxz;
	private String sblx;
	private String sbyt;
	 public String getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}

	private  String  noticeDate;
	
	public String getGcmc() {
		return gcmc;
	}

	public void setGcmc(String gcmc) {
		this.gcmc = gcmc;
	}

	public String getJobcode() {
		return jobcode;
	}

	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getIntoPipe() {
		return intoPipe;
	}

	public void setIntoPipe(String intoPipe) {
		this.intoPipe = intoPipe;
	}

	public String getIntoWatch() {
		return intoWatch;
	}

	public void setIntoWatch(String intoWatch) {
		this.intoWatch = intoWatch;
	}

	public String getKcsjf() {
		return kcsjf;
	}

	public void setKcsjf(String kcsjf) {
		this.kcsjf = kcsjf;
	}

	public String getJbr() {
		return jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	public String getKcry() {
		return kcry;
	}

	public void setKcry(String kcry) {
		this.kcry = kcry;
	}

	public String getCzway() {
		return czway;
	}

	public void setCzway(String czway) {
		this.czway = czway;
	}

	public String getYsxz() {
		return ysxz;
	}

	public void setYsxz(String ysxz) {
		this.ysxz = ysxz;
	}

	public String getSblx() {
		return sblx;
	}

	public void setSblx(String sblx) {
		this.sblx = sblx;
	}

	public String getSbyt() {
		return sbyt;
	}

	public void setSbyt(String sbyt) {
		this.sbyt = sbyt;
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
