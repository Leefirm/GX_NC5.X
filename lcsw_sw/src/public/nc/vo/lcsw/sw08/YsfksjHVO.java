package nc.vo.lcsw.sw08;
/** 
 * <b> �ڴ˴���Ҫ��������Ĺ���  </b>
 * <p>
 *     �ڴ˴���Ӵ����������Ϣ 
 * <p>
 * @author xuns
 * <b>����ʱ��: 2014-08-21 11:18:55</b>
 * 
 * 
*/ 
import nc.vo.pub.lang.*;
import nc.vo.pub.*;

public class YsfksjHVO extends SuperVO {
	private String sgbm;
	private String reserve10;
	private String vlastbillid;
	private String reserve11;
	private UFDateTime ts;
	private UFDate zbrq;
	private String reserve12;
	private String remark;
	private String reserve13;
	private String reserve14;
	private UFDate dmakedate;
	private String cchd;
	private String vsourcebillno;
	private String azbj;
	private String vlastbillrowid;
	private String sgfzr;
	private String sglxdh;
	private UFDate yqxgrq;
	private String pk_billtype;
	private String reserve20;
	private UFDouble reserve7;
	private UFDouble reserve6;
	private UFBoolean reserve5;
	private String bianma;
	private String reserve4;
	private String reserve9;
	private UFDouble reserve8;
	private String sggcai;
	private String yslxdh;
	private String reserve3;
	private String reserve2;
	private String reserve1;
	private String biaoma;
	private String modifier;
	private UFDate yjzgrq;
	private String bhgyy;
	private String ld;
	private UFDouble ysxd;
	private String reserve18;
	private UFDate dbilldate;
	private String reserve17;
	private String reserve16;
	private String reserve15;
	private String reserve19;
	private String ckxd;
	private String biaobie;
	private UFDate yjbwysrq;
	private String vlastbilltype;
	private String yqxgr;
	private String voperatorid;
	private String vapprovenote;
	private UFDate dmodify;
	private String vapproveid;
	private UFBoolean yshgbz;
	private String pk_busitype;
	private String pk_ysfksjhid;
	private Integer dr;
	private UFDouble sggchang;
	private Integer wgyqts;
	private String pk_corp;
	private String ysy;
	private String ldzd;
	private String pk_jobbasfil;
	private String vsourcebilltype;
	private String chandi;
	private String biaowei;
	private String vbillcode;
	private Integer vbillstatus;
	private String ldqd;
	private UFDate dapprovedate;
	private String vsourcebillid;
	private String vsourcebillrowid;
	private UFDate ysrq;
	private UFDouble jsgck;
	
	private UFDate zlfkrq;//���Ϸ�������
	private Integer ysjg;//���ս��

	
	public UFDate getZlfkrq() {
		return zlfkrq;
	}
	public void setZlfkrq(UFDate zlfkrq) {
		this.zlfkrq = zlfkrq;
	}
	public Integer getYsjg() {
		return ysjg;
	}
	public void setYsjg(Integer ysjg) {
		this.ysjg = ysjg;
	}
	public static final String ZLFKRQ="zlfkrq";
	public static final String YSJG="ysjg";
	
	public static final String JSGCK="jsgck";
	public static final String SGBM = "sgbm";
	public static final String RESERVE10 = "reserve10";
	public static final String VLASTBILLID = "vlastbillid";
	public static final String RESERVE11 = "reserve11";
	public static final String TS = "ts";
	public static final String ZBRQ = "zbrq";
	public static final String RESERVE12 = "reserve12";
	public static final String REMARK = "remark";
	public static final String RESERVE13 = "reserve13";
	public static final String RESERVE14 = "reserve14";
	public static final String DMAKEDATE = "dmakedate";
	public static final String CCHD = "cchd";
	public static final String VSOURCEBILLNO = "vsourcebillno";
	public static final String AZBJ = "azbj";
	public static final String VLASTBILLROWID = "vlastbillrowid";
	public static final String SGFZR = "sgfzr";
	public static final String SGLXDH = "sglxdh";
	public static final String YQXGRQ = "yqxgrq";
	public static final String PK_BILLTYPE = "pk_billtype";
	public static final String RESERVE20 = "reserve20";
	public static final String RESERVE7 = "reserve7";
	public static final String RESERVE6 = "reserve6";
	public static final String RESERVE5 = "reserve5";
	public static final String BIANMA = "bianma";
	public static final String RESERVE4 = "reserve4";
	public static final String RESERVE9 = "reserve9";
	public static final String RESERVE8 = "reserve8";
	public static final String SGGCAI = "sggcai";
	public static final String YSLXDH = "yslxdh";
	public static final String RESERVE3 = "reserve3";
	public static final String RESERVE2 = "reserve2";
	public static final String RESERVE1 = "reserve1";
	public static final String BIAOMA = "biaoma";
	public static final String MODIFIER = "modifier";
	public static final String YJZGRQ = "yjzgrq";
	public static final String BHGYY = "bhgyy";
	public static final String LD = "ld";
	public static final String YSXD = "ysxd";
	public static final String RESERVE18 = "reserve18";
	public static final String DBILLDATE = "dbilldate";
	public static final String RESERVE17 = "reserve17";
	public static final String RESERVE16 = "reserve16";
	public static final String RESERVE15 = "reserve15";
	public static final String RESERVE19 = "reserve19";
	public static final String CKXD = "ckxd";
	public static final String BIAOBIE = "biaobie";
	public static final String YJBWYSRQ = "yjbwysrq";
	public static final String VLASTBILLTYPE = "vlastbilltype";
	public static final String YQXGR = "yqxgr";
	public static final String VOPERATORID = "voperatorid";
	public static final String VAPPROVENOTE = "vapprovenote";
	public static final String DMODIFY = "dmodify";
	public static final String VAPPROVEID = "vapproveid";
	public static final String YSHGBZ = "yshgbz";
	public static final String PK_BUSITYPE = "pk_busitype";
	public static final String PK_YSFKSJHID = "pk_ysfksjhid";
	public static final String DR = "dr";
	public static final String SGGCHANG = "sggchang";
	public static final String WGYQTS = "wgyqts";
	public static final String PK_CORP = "pk_corp";
	public static final String YSY = "ysy";
	public static final String LDZD = "ldzd";
	public static final String PK_JOBBASFIL = "pk_jobbasfil";
	public static final String VSOURCEBILLTYPE = "vsourcebilltype";
	public static final String CHANDI = "chandi";
	public static final String BIAOWEI = "biaowei";
	public static final String VBILLCODE = "vbillcode";
	public static final String VBILLSTATUS = "vbillstatus";
	public static final String LDQD = "ldqd";
	public static final String DAPPROVEDATE = "dapprovedate";
	public static final String VSOURCEBILLID = "vsourcebillid";
	public static final String VSOURCEBILLROWID = "vsourcebillrowid";
	public static final String YSRQ = "ysrq";

	
	
	
	public UFDouble getJsgck() {
		return jsgck;
	}
	public void setJsgck(UFDouble jsgck) {
		this.jsgck = jsgck;
	}
	/** 
	 * 
	 * ����<ʩ������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getSgbm(){
		 return sgbm;
	}
	/** 
	 * 
	 * ����<ʩ������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setSgbm(String newSgbm){
		 this.sgbm = newSgbm;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�10>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve10(){
		 return reserve10;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�10>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve10(String newReserve10){
		 this.reserve10 = newReserve10;
	}
	/** 
	 * 
	 * ����<���ε���ID>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getVlastbillid(){
		 return vlastbillid;
	}
	/** 
	 * 
	 * ����<���ε���ID>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVlastbillid(String newVlastbillid){
		 this.vlastbillid = newVlastbillid;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�11>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve11(){
		 return reserve11;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�11>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve11(String newReserve11){
		 this.reserve11 = newReserve11;
	}
	/** 
	 * 
	 * ����<ʱ��>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDateTime getTs(){
		 return ts;
	}
	/** 
	 * 
	 * ����<ʱ��>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setTs(UFDateTime newTs){
		 this.ts = newTs;
	}
	/** 
	 * 
	 * ����<װ������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDate getZbrq(){
		 return zbrq;
	}
	/** 
	 * 
	 * ����<װ������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setZbrq(UFDate newZbrq){
		 this.zbrq = newZbrq;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�12>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve12(){
		 return reserve12;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�12>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve12(String newReserve12){
		 this.reserve12 = newReserve12;
	}
	/** 
	 * 
	 * ����<��ע>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getRemark(){
		 return remark;
	}
	/** 
	 * 
	 * ����<��ע>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setRemark(String newRemark){
		 this.remark = newRemark;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�13>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve13(){
		 return reserve13;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�13>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve13(String newReserve13){
		 this.reserve13 = newReserve13;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�14>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve14(){
		 return reserve14;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�14>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve14(String newReserve14){
		 this.reserve14 = newReserve14;
	}
	/** 
	 * 
	 * ����<�Ƶ�����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDate getDmakedate(){
		 return dmakedate;
	}
	/** 
	 * 
	 * ����<�Ƶ�����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setDmakedate(UFDate newDmakedate){
		 this.dmakedate = newDmakedate;
	}
	/** 
	 * 
	 * ����<�����ж�>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getCchd(){
		 return cchd;
	}
	/** 
	 * 
	 * ����<�����ж�>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setCchd(String newCchd){
		 this.cchd = newCchd;
	}
	/** 
	 * 
	 * ����<���ε��ݺ�>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getVsourcebillno(){
		 return vsourcebillno;
	}
	/** 
	 * 
	 * ����<���ε��ݺ�>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVsourcebillno(String newVsourcebillno){
		 this.vsourcebillno = newVsourcebillno;
	}
	/** 
	 * 
	 * ����<��װ��>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getAzbj(){
		 return azbj;
	}
	/** 
	 * 
	 * ����<��װ��>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setAzbj(String newAzbj){
		 this.azbj = newAzbj;
	}
	/** 
	 * 
	 * ����<���ε�����ID>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getVlastbillrowid(){
		 return vlastbillrowid;
	}
	/** 
	 * 
	 * ����<���ε�����ID>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVlastbillrowid(String newVlastbillrowid){
		 this.vlastbillrowid = newVlastbillrowid;
	}
	/** 
	 * 
	 * ����<ʩ��������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getSgfzr(){
		 return sgfzr;
	}
	/** 
	 * 
	 * ����<ʩ��������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setSgfzr(String newSgfzr){
		 this.sgfzr = newSgfzr;
	}
	/** 
	 * 
	 * ����<ʩ����ϵ�绰>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getSglxdh(){
		 return sglxdh;
	}
	/** 
	 * 
	 * ����<ʩ����ϵ�绰>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setSglxdh(String newSglxdh){
		 this.sglxdh = newSglxdh;
	}
	/** 
	 * 
	 * ����<�����޸�����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDate getYqxgrq(){
		 return yqxgrq;
	}
	/** 
	 * 
	 * ����<�����޸�����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setYqxgrq(UFDate newYqxgrq){
		 this.yqxgrq = newYqxgrq;
	}
	/** 
	 * 
	 * ����<��������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getPk_billtype(){
		 return pk_billtype;
	}
	/** 
	 * 
	 * ����<��������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setPk_billtype(String newPk_billtype){
		 this.pk_billtype = newPk_billtype;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�20>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve20(){
		 return reserve20;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�20>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve20(String newReserve20){
		 this.reserve20 = newReserve20;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�7>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDouble getReserve7(){
		 return reserve7;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�7>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve7(UFDouble newReserve7){
		 this.reserve7 = newReserve7;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�6>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDouble getReserve6(){
		 return reserve6;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�6>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve6(UFDouble newReserve6){
		 this.reserve6 = newReserve6;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�5>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFBoolean getReserve5() {
		return reserve5;
	}
	public void setReserve5(UFBoolean reserve5) {
		this.reserve5 = reserve5;
	}
	/** 
	 * 
	 * ����<����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getBianma(){
		 return bianma;
	}
	/** 
	 * 
	 * ����<����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setBianma(String newBianma){
		 this.bianma = newBianma;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�4>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve4(){
		 return reserve4;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�4>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve4(String newReserve4){
		 this.reserve4 = newReserve4;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�9>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve9(){
		 return reserve9;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�9>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve9(String newReserve9){
		 this.reserve9 = newReserve9;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�8>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDouble getReserve8(){
		 return reserve8;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�8>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve8(UFDouble newReserve8){
		 this.reserve8 = newReserve8;
	}
	/** 
	 * 
	 * ����<ʩ���ܲ�>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getSggcai(){
		 return sggcai;
	}
	/** 
	 * 
	 * ����<ʩ���ܲ�>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setSggcai(String newSggcai){
		 this.sggcai = newSggcai;
	}
	/** 
	 * 
	 * ����<������ϵ�绰>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getYslxdh(){
		 return yslxdh;
	}
	/** 
	 * 
	 * ����<������ϵ�绰>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setYslxdh(String newYslxdh){
		 this.yslxdh = newYslxdh;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�3>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve3(){
		 return reserve3;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�3>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve3(String newReserve3){
		 this.reserve3 = newReserve3;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�2>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve2(){
		 return reserve2;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�2>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve2(String newReserve2){
		 this.reserve2 = newReserve2;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�1>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve1(){
		 return reserve1;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�1>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve1(String newReserve1){
		 this.reserve1 = newReserve1;
	}
	/** 
	 * 
	 * ����<����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getBiaoma(){
		 return biaoma;
	}
	/** 
	 * 
	 * ����<����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setBiaoma(String newBiaoma){
		 this.biaoma = newBiaoma;
	}
	/** 
	 * 
	 * ����<�޸���>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getModifier(){
		 return modifier;
	}
	/** 
	 * 
	 * ����<�޸���>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setModifier(String newModifier){
		 this.modifier = newModifier;
	}
	/** 
	 * 
	 * ����<�ƽ���������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDate getYjzgrq(){
		 return yjzgrq;
	}
	/** 
	 * 
	 * ����<�ƽ���������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setYjzgrq(UFDate newYjzgrq){
		 this.yjzgrq = newYjzgrq;
	}
	/** 
	 * 
	 * ����<���ϸ�ԭ��>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getBhgyy(){
		 return bhgyy;
	}
	/** 
	 * 
	 * ����<���ϸ�ԭ��>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setBhgyy(String newBhgyy){
		 this.bhgyy = newBhgyy;
	}
	/** 
	 * 
	 * ����<·��>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getLd(){
		 return ld;
	}
	/** 
	 * 
	 * ����<·��>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setLd(String newLd){
		 this.ld = newLd;
	}
	/** 
	 * 
	 * ����<�����ж�>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDouble getYsxd(){
		 return ysxd;
	}
	/** 
	 * 
	 * ����<�����ж�>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setYsxd(UFDouble newYsxd){
		 this.ysxd = newYsxd;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�18>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve18(){
		 return reserve18;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�18>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve18(String newReserve18){
		 this.reserve18 = newReserve18;
	}
	/** 
	 * 
	 * ����<ҵ������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDate getDbilldate(){
		 return dbilldate;
	}
	/** 
	 * 
	 * ����<ҵ������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setDbilldate(UFDate newDbilldate){
		 this.dbilldate = newDbilldate;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�17>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve17(){
		 return reserve17;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�17>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve17(String newReserve17){
		 this.reserve17 = newReserve17;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�16>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve16(){
		 return reserve16;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�16>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve16(String newReserve16){
		 this.reserve16 = newReserve16;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�15>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve15(){
		 return reserve15;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�15>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve15(String newReserve15){
		 this.reserve15 = newReserve15;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�19>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getReserve19(){
		 return reserve19;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�19>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve19(String newReserve19){
		 this.reserve19 = newReserve19;
	}
	/** 
	 * 
	 * ����<�����ж�>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getCkxd(){
		 return ckxd;
	}
	/** 
	 * 
	 * ����<�����ж�>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setCkxd(String newCkxd){
		 this.ckxd = newCkxd;
	}
	/** 
	 * 
	 * ����<���>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getBiaobie(){
		 return biaobie;
	}
	/** 
	 * 
	 * ����<���>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setBiaobie(String newBiaobie){
		 this.biaobie = newBiaobie;
	}
	/** 
	 * 
	 * ����<�ƽ���λ��������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDate getYjbwysrq(){
		 return yjbwysrq;
	}
	/** 
	 * 
	 * ����<�ƽ���λ��������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setYjbwysrq(UFDate newYjbwysrq){
		 this.yjbwysrq = newYjbwysrq;
	}
	/** 
	 * 
	 * ����<���ε�������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getVlastbilltype(){
		 return vlastbilltype;
	}
	/** 
	 * 
	 * ����<���ε�������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVlastbilltype(String newVlastbilltype){
		 this.vlastbilltype = newVlastbilltype;
	}
	/** 
	 * 
	 * ����<�����޸���>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getYqxgr(){
		 return yqxgr;
	}
	/** 
	 * 
	 * ����<�����޸���>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setYqxgr(String newYqxgr){
		 this.yqxgr = newYqxgr;
	}
	/** 
	 * 
	 * ����<�Ƶ���>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getVoperatorid(){
		 return voperatorid;
	}
	/** 
	 * 
	 * ����<�Ƶ���>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVoperatorid(String newVoperatorid){
		 this.voperatorid = newVoperatorid;
	}
	/** 
	 * 
	 * ����<�������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getVapprovenote(){
		 return vapprovenote;
	}
	/** 
	 * 
	 * ����<�������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVapprovenote(String newVapprovenote){
		 this.vapprovenote = newVapprovenote;
	}
	/** 
	 * 
	 * ����<�޸�����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDate getDmodify(){
		 return dmodify;
	}
	/** 
	 * 
	 * ����<�޸�����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setDmodify(UFDate newDmodify){
		 this.dmodify = newDmodify;
	}
	/** 
	 * 
	 * ����<�����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getVapproveid(){
		 return vapproveid;
	}
	/** 
	 * 
	 * ����<�����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVapproveid(String newVapproveid){
		 this.vapproveid = newVapproveid;
	}
	/** 
	 * 
	 * ����<���պϸ��־>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFBoolean getYshgbz(){
		 return yshgbz;
	}
	/** 
	 * 
	 * ����<���պϸ��־>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setYshgbz(UFBoolean newYshgbz){
		 this.yshgbz = newYshgbz;
	}
	/** 
	 * 
	 * ����<ҵ������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getPk_busitype(){
		 return pk_busitype;
	}
	/** 
	 * 
	 * ����<ҵ������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setPk_busitype(String newPk_busitype){
		 this.pk_busitype = newPk_busitype;
	}
	/** 
	 * 
	 * ����<����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getPk_ysfksjhid(){
		 return pk_ysfksjhid;
	}
	/** 
	 * 
	 * ����<����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setPk_ysfksjhid(String newPk_ysfksjhid){
		 this.pk_ysfksjhid = newPk_ysfksjhid;
	}
	/** 
	 * 
	 * ����<ɾ��>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public Integer getDr(){
		 return dr;
	}
	/** 
	 * 
	 * ����<ɾ��>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setDr(Integer newDr){
		 this.dr = newDr;
	}
	/** 
	 * 
	 * ����<ʩ���ܳ�>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDouble getSggchang(){
		 return sggchang;
	}
	/** 
	 * 
	 * ����<ʩ���ܳ�>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setSggchang(UFDouble newSggchang){
		 this.sggchang = newSggchang;
	}
	/** 
	 * 
	 * ����<�깤��������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public Integer getWgyqts(){
		 return wgyqts;
	}
	/** 
	 * 
	 * ����<�깤��������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setWgyqts(Integer newWgyqts){
		 this.wgyqts = newWgyqts;
	}
	/** 
	 * 
	 * ����<��˾ID>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getPk_corp(){
		 return pk_corp;
	}
	/** 
	 * 
	 * ����<��˾ID>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setPk_corp(String newPk_corp){
		 this.pk_corp = newPk_corp;
	}
	/** 
	 * 
	 * ����<����Ա>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getYsy(){
		 return ysy;
	}
	/** 
	 * 
	 * ����<����Ա>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setYsy(String newYsy){
		 this.ysy = newYsy;
	}
	/** 
	 * 
	 * ����<·���յ�>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getLdzd(){
		 return ldzd;
	}
	/** 
	 * 
	 * ����<·���յ�>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setLdzd(String newLdzd){
		 this.ldzd = newLdzd;
	}
	/** 
	 * 
	 * ����<��Ŀ>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getPk_jobbasfil(){
		 return pk_jobbasfil;
	}
	/** 
	 * 
	 * ����<��Ŀ>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setPk_jobbasfil(String newPk_jobbasfil){
		 this.pk_jobbasfil = newPk_jobbasfil;
	}
	/** 
	 * 
	 * ����<��Դ��������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getVsourcebilltype(){
		 return vsourcebilltype;
	}
	/** 
	 * 
	 * ����<��Դ��������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVsourcebilltype(String newVsourcebilltype){
		 this.vsourcebilltype = newVsourcebilltype;
	}
	/** 
	 * 
	 * ����<����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getChandi(){
		 return chandi;
	}
	/** 
	 * 
	 * ����<����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setChandi(String newChandi){
		 this.chandi = newChandi;
	}
	/** 
	 * 
	 * ����<��λ>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getBiaowei(){
		 return biaowei;
	}
	/** 
	 * 
	 * ����<��λ>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setBiaowei(String newBiaowei){
		 this.biaowei = newBiaowei;
	}
	/** 
	 * 
	 * ����<���ݺ�>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getVbillcode(){
		 return vbillcode;
	}
	/** 
	 * 
	 * ����<���ݺ�>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVbillcode(String newVbillcode){
		 this.vbillcode = newVbillcode;
	}
	/** 
	 * 
	 * ����<����״̬>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public Integer getVbillstatus(){
		 return vbillstatus;
	}
	/** 
	 * 
	 * ����<����״̬>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVbillstatus(Integer newVbillstatus){
		 this.vbillstatus = newVbillstatus;
	}
	/** 
	 * 
	 * ����<·�����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getLdqd(){
		 return ldqd;
	}
	/** 
	 * 
	 * ����<·�����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setLdqd(String newLdqd){
		 this.ldqd = newLdqd;
	}
	/** 
	 * 
	 * ����<�������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDate getDapprovedate(){
		 return dapprovedate;
	}
	/** 
	 * 
	 * ����<�������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setDapprovedate(UFDate newDapprovedate){
		 this.dapprovedate = newDapprovedate;
	}
	/** 
	 * 
	 * ����<��Դ����ID>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getVsourcebillid(){
		 return vsourcebillid;
	}
	/** 
	 * 
	 * ����<��Դ����ID>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVsourcebillid(String newVsourcebillid){
		 this.vsourcebillid = newVsourcebillid;
	}
	/** 
	 * 
	 * ����<��Դ������ID>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getVsourcebillrowid(){
		 return vsourcebillrowid;
	}
	/** 
	 * 
	 * ����<��Դ������ID>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setVsourcebillrowid(String newVsourcebillrowid){
		 this.vsourcebillrowid = newVsourcebillrowid;
	}
	/** 
	 * 
	 * ����<��������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDate getYsrq(){
		 return ysrq;
	}
	/** 
	 * 
	 * ����<��������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setYsrq(UFDate newYsrq){
		 this.ysrq = newYsrq;
	}

	/** 
	 * ȡ�ø�VO�����ֶ�
	 * 
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	*/ 
	public java.lang.String getParentPKFieldName() {
		return null;
	}
	/** 
	 * ȡ�ñ�����
	 * 
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	*/ 
	public java.lang.String getPKFieldName() {
		return "pk_ysfksjhid";
	}
	/** 
	 * ���ر�����
	 * 
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	*/ 
	public java.lang.String getTableName() {
		return "lcsw_ysfksj_h";
	}
	/** 
	 * ����Ĭ�Ϸ�ʽ����������
	 * 
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	*/ 
	public YsfksjHVO() {
		super();
	}
}
