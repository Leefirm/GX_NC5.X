package nc.vo.lcsw.sw07;
/** 
 * <b> �ڴ˴���Ҫ��������Ĺ���  </b>
 * <p>
 *     �ڴ˴���Ӵ����������Ϣ 
 * <p>
 * @author ��չ��
 * <b>����ʱ��: 2014-09-03 17:11:19</b>
 * 
 * 
*/ 
import nc.vo.pub.lang.*;
import nc.vo.pub.*;

@SuppressWarnings("serial")
public class AzxxfkHVOC extends SuperVO {
	private String ysdh;
	private String vlastbillid;
	private String reserve10;
	private String reserve11;
	private String remark;
	private String reserve12;
	private String reserve13;
	private String reserve14;
	private UFDate dmakedate;
	private String vsourcebillno;
	private String vlastbillrowid;
	private UFDate yqxgrq;
	private UFDate jgrq;
	private String pk_billtype;
	private String reserve20;
	private UFDate yjazrq;
	private String hth;
	private UFDouble reserve7;
	private UFDouble reserve6;
	private UFDouble reserve5;
	private String reserve4;
	private String reserve9;
	private UFDouble reserve8;
	private String reserve3;
	private String reserve2;
	private UFDate azrq;
	private UFDate zlfkrq;
	private String reserve1;
	private String modifier;
	private String reserve18;
	private UFDate dbilldate;
	private String reserve17;
	private String reserve16;
	private String reserve15;
	private String reserve19;
	private String vlastbilltype;
	private String ysry;
	private String yqxgr;
	private String voperatorid;
	private String vapprovenote;
	private UFDate dmodify;
	private String vapproveid;
	private String pk_busitype;
	private Integer wgyqts;
	private String pk_corp;
	private UFDate azfkrq;
	private String vsourcebilltype;
	private String pk_jobbasfil;
	private String vbillcode;
	private Integer vbillstatus;
	private UFDate dapprovedate;
	private String vsourcebillrowid;
	private String vsourcebillid;
	private String pk_azxxfkhid;
	private UFDateTime ts;
	private Integer dr;
	
	private UFDate kgrq;
	private UFDate zbrq;
	private UFDate yjbwysrq;
	private String sgbm;
	private String sgfzr;
	private String sglxdh;
	private String azbj;
	private String ckxd;
	private String ld;
	private String ldqd;
	private String ldzd;
	private String sggcai;
	private UFDouble sggchang;
	private String biaoma;
	private String chandi;
	private String bianma;
	private String biaobie;
	private String biaowei;
	private UFBoolean sfyjsy;
	private String sjth;
	
	//add by crf 2015-07-08 [�ƻ���װ����]��[�ƻ���װ�������]��[ʵ�ʰ�װ����]
	private Integer jhazgq;
	private UFDate jhazwcrq;
	private Integer sjazgq;
	public Integer getJhazgq() {
		return jhazgq;
	}
	public void setJhazgq(Integer jhazgq) {
		this.jhazgq = jhazgq;
	}
	public UFDate getJhazwcrq() {
		return jhazwcrq;
	}
	public void setJhazwcrq(UFDate jhazwcrq) {
		this.jhazwcrq = jhazwcrq;
	}
	public Integer getSjazgq() {
		return sjazgq;
	}
	public void setSjazgq(Integer sjazgq) {
		this.sjazgq = sjazgq;
	}
	//end
	public UFBoolean getSfyjsy() {
		return sfyjsy;
	}
	public void setSfyjsy(UFBoolean sfyjsy) {
		this.sfyjsy = sfyjsy;
	}
	
	public String getSjth() {
		return sjth;
	}
	public void setSjth(String sjth) {
		this.sjth = sjth;
	}

	public static final String SJTH = "sjth";
	public static final String SFYJSY = "sfyjsy";
	public static final String KGRQ = "kgrq";
	public static final String ZBRQ = "zbrq";
	public static final String YJBWYSRQ = "yjbwysrq";
	public static final String SGBM = "sgbm";
	public static final String SGFZR = "sgfzr";
	public static final String SGLXDH = "sglxdh";
	public static final String AZBJ = "azbj";
	public static final String CKXD = "ckxd";
	public static final String LD = "ld";
	public static final String LDQD = "ldqd";
	public static final String LDZD = "ldzd";
	public static final String SGGCAI = "sggcai";
	public static final String SGGCHANG = "sggchang";
	public static final String BIAOMA = "biaoma";
	public static final String CHANDI = "chandi";
	public static final String BIANMA = "bianma";
	public static final String BIAOBIE = "biaobie";
	public static final String BIAOWEI = "biaowei";
	
	
	
	

	public UFDate getKgrq() {
		return kgrq;
	}
	public void setKgrq(UFDate kgrq) {
		this.kgrq = kgrq;
	}
	public UFDate getZbrq() {
		return zbrq;
	}
	public void setZbrq(UFDate zbrq) {
		this.zbrq = zbrq;
	}
	public UFDate getYjbwysrq() {
		return yjbwysrq;
	}
	public void setYjbwysrq(UFDate yjbwysrq) {
		this.yjbwysrq = yjbwysrq;
	}
	public String getSgbm() {
		return sgbm;
	}
	public void setSgbm(String sgbm) {
		this.sgbm = sgbm;
	}
	public String getSgfzr() {
		return sgfzr;
	}
	public void setSgfzr(String sgfzr) {
		this.sgfzr = sgfzr;
	}
	public String getSglxdh() {
		return sglxdh;
	}
	public void setSglxdh(String sglxdh) {
		this.sglxdh = sglxdh;
	}
	public String getAzbj() {
		return azbj;
	}
	public void setAzbj(String azbj) {
		this.azbj = azbj;
	}
	public String getCkxd() {
		return ckxd;
	}
	public void setCkxd(String ckxd) {
		this.ckxd = ckxd;
	}
	public String getLd() {
		return ld;
	}
	public void setLd(String ld) {
		this.ld = ld;
	}
	public String getLdqd() {
		return ldqd;
	}
	public void setLdqd(String ldqd) {
		this.ldqd = ldqd;
	}
	public String getLdzd() {
		return ldzd;
	}
	public void setLdzd(String ldzd) {
		this.ldzd = ldzd;
	}
	public String getSggcai() {
		return sggcai;
	}
	public void setSggcai(String sggcai) {
		this.sggcai = sggcai;
	}
	public UFDouble getSggchang() {
		return sggchang;
	}
	public void setSggchang(UFDouble sggchang) {
		this.sggchang = sggchang;
	}
	public String getBiaoma() {
		return biaoma;
	}
	public void setBiaoma(String biaoma) {
		this.biaoma = biaoma;
	}
	public String getChandi() {
		return chandi;
	}
	public void setChandi(String chandi) {
		this.chandi = chandi;
	}
	public String getBianma() {
		return bianma;
	}
	public void setBianma(String bianma) {
		this.bianma = bianma;
	}
	public String getBiaobie() {
		return biaobie;
	}
	public void setBiaobie(String biaobie) {
		this.biaobie = biaobie;
	}
	public String getBiaowei() {
		return biaowei;
	}
	public void setBiaowei(String biaowei) {
		this.biaowei = biaowei;
	}
	public static final String YSDH = "ysdh";
	public static final String VLASTBILLID = "vlastbillid";
	public static final String RESERVE10 = "reserve10";
	public static final String TS = "ts";
	public static final String RESERVE11 = "reserve11";
	public static final String REMARK = "remark";
	public static final String RESERVE12 = "reserve12";
	public static final String RESERVE13 = "reserve13";
	public static final String RESERVE14 = "reserve14";
	public static final String DMAKEDATE = "dmakedate";
	public static final String VSOURCEBILLNO = "vsourcebillno";
	public static final String VLASTBILLROWID = "vlastbillrowid";
	public static final String YQXGRQ = "yqxgrq";
	public static final String JGRQ = "jgrq";
	public static final String PK_BILLTYPE = "pk_billtype";
	public static final String RESERVE20 = "reserve20";
	public static final String YJAZRQ = "yjazrq";
	public static final String HTH = "hth";
	public static final String RESERVE7 = "reserve7";
	public static final String RESERVE6 = "reserve6";
	public static final String RESERVE5 = "reserve5";
	public static final String RESERVE4 = "reserve4";
	public static final String RESERVE9 = "reserve9";
	public static final String RESERVE8 = "reserve8";
	public static final String RESERVE3 = "reserve3";
	public static final String RESERVE2 = "reserve2";
	public static final String AZRQ = "azrq";
	public static final String ZLFKRQ = "zlfkrq";
	public static final String RESERVE1 = "reserve1";
	public static final String MODIFIER = "modifier";
	public static final String RESERVE18 = "reserve18";
	public static final String DBILLDATE = "dbilldate";
	public static final String RESERVE17 = "reserve17";
	public static final String RESERVE16 = "reserve16";
	public static final String RESERVE15 = "reserve15";
	public static final String RESERVE19 = "reserve19";
	public static final String VLASTBILLTYPE = "vlastbilltype";
	public static final String YSRY = "ysry";
	public static final String YQXGR = "yqxgr";
	public static final String VOPERATORID = "voperatorid";
	public static final String VAPPROVENOTE = "vapprovenote";
	public static final String DMODIFY = "dmodify";
	public static final String VAPPROVEID = "vapproveid";
	public static final String PK_BUSITYPE = "pk_busitype";
	public static final String WGYQTS = "wgyqts";
	public static final String DR = "dr";
	public static final String PK_CORP = "pk_corp";
	public static final String AZFKRQ = "azfkrq";
	public static final String VSOURCEBILLTYPE = "vsourcebilltype";
	public static final String PK_JOBBASFIL = "pk_jobbasfil";
	public static final String VBILLCODE = "vbillcode";
	public static final String VBILLSTATUS = "vbillstatus";
	public static final String DAPPROVEDATE = "dapprovedate";
	public static final String VSOURCEBILLROWID = "vsourcebillrowid";
	public static final String VSOURCEBILLID = "vsourcebillid";
	public static final String PK_AZXXFKHID = "pk_azxxfkhid";

	/** 
	 * 
	 * ����<���յ绰>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getYsdh(){
		 return ysdh;
	}
	/** 
	 * 
	 * ����<���յ绰>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setYsdh(String newYsdh){
		 this.ysdh = newYsdh;
	}
	/** 
	 * 
	 * ����<���ε���ID>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getVlastbillid(){
		 return vlastbillid;
	}
	/** 
	 * 
	 * ����<���ε���ID>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVlastbillid(String newVlastbillid){
		 this.vlastbillid = newVlastbillid;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�10>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve10(){
		 return reserve10;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�10>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve10(String newReserve10){
		 this.reserve10 = newReserve10;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�11>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve11(){
		 return reserve11;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�11>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve11(String newReserve11){
		 this.reserve11 = newReserve11;
	}
	/** 
	 * 
	 * ����<��ע>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getRemark(){
		 return remark;
	}
	/** 
	 * 
	 * ����<��ע>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setRemark(String newRemark){
		 this.remark = newRemark;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�12>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve12(){
		 return reserve12;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�12>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve12(String newReserve12){
		 this.reserve12 = newReserve12;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�13>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve13(){
		 return reserve13;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�13>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve13(String newReserve13){
		 this.reserve13 = newReserve13;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�14>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve14(){
		 return reserve14;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�14>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve14(String newReserve14){
		 this.reserve14 = newReserve14;
	}
	/** 
	 * 
	 * ����<�Ƶ�����>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDate getDmakedate(){
		 return dmakedate;
	}
	/** 
	 * 
	 * ����<�Ƶ�����>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setDmakedate(UFDate newDmakedate){
		 this.dmakedate = newDmakedate;
	}
	/** 
	 * 
	 * ����<���ε��ݺ�>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getVsourcebillno(){
		 return vsourcebillno;
	}
	/** 
	 * 
	 * ����<���ε��ݺ�>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVsourcebillno(String newVsourcebillno){
		 this.vsourcebillno = newVsourcebillno;
	}
	/** 
	 * 
	 * ����<���ε�����ID>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getVlastbillrowid(){
		 return vlastbillrowid;
	}
	/** 
	 * 
	 * ����<���ε�����ID>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVlastbillrowid(String newVlastbillrowid){
		 this.vlastbillrowid = newVlastbillrowid;
	}
	/** 
	 * 
	 * ����<�����޸�����>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDate getYqxgrq(){
		 return yqxgrq;
	}
	/** 
	 * 
	 * ����<�����޸�����>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setYqxgrq(UFDate newYqxgrq){
		 this.yqxgrq = newYqxgrq;
	}
	/** 
	 * 
	 * ����<��������>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDate getJgrq(){
		 return jgrq;
	}
	/** 
	 * 
	 * ����<��������>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setJgrq(UFDate newJgrq){
		 this.jgrq = newJgrq;
	}
	/** 
	 * 
	 * ����<��������>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getPk_billtype(){
		 return pk_billtype;
	}
	/** 
	 * 
	 * ����<��������>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setPk_billtype(String newPk_billtype){
		 this.pk_billtype = newPk_billtype;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�20>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve20(){
		 return reserve20;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�20>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve20(String newReserve20){
		 this.reserve20 = newReserve20;
	}
	/** 
	 * 
	 * ����<�ƽ���װ����>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDate getYjazrq(){
		 return yjazrq;
	}
	/** 
	 * 
	 * ����<�ƽ���װ����>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setYjazrq(UFDate newYjazrq){
		 this.yjazrq = newYjazrq;
	}
	/** 
	 * 
	 * ����<��ͬ��>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getHth(){
		 return hth;
	}
	/** 
	 * 
	 * ����<��ͬ��>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setHth(String newHth){
		 this.hth = newHth;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�7>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDouble getReserve7(){
		 return reserve7;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�7>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve7(UFDouble newReserve7){
		 this.reserve7 = newReserve7;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�6>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDouble getReserve6(){
		 return reserve6;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�6>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve6(UFDouble newReserve6){
		 this.reserve6 = newReserve6;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�5>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDouble getReserve5(){
		 return reserve5;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�5>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve5(UFDouble newReserve5){
		 this.reserve5 = newReserve5;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�4>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve4(){
		 return reserve4;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�4>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve4(String newReserve4){
		 this.reserve4 = newReserve4;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�9>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve9(){
		 return reserve9;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�9>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve9(String newReserve9){
		 this.reserve9 = newReserve9;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�8>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDouble getReserve8(){
		 return reserve8;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�8>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve8(UFDouble newReserve8){
		 this.reserve8 = newReserve8;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�3>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve3(){
		 return reserve3;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�3>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve3(String newReserve3){
		 this.reserve3 = newReserve3;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�2>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve2(){
		 return reserve2;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�2>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve2(String newReserve2){
		 this.reserve2 = newReserve2;
	}
	/** 
	 * 
	 * ����<��װ����>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDate getAzrq(){
		 return azrq;
	}
	/** 
	 * 
	 * ����<��װ����>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setAzrq(UFDate newAzrq){
		 this.azrq = newAzrq;
	}
	/** 
	 * 
	 * ����<���Ϸ�������>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDate getZlfkrq(){
		 return zlfkrq;
	}
	/** 
	 * 
	 * ����<���Ϸ�������>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setZlfkrq(UFDate newZlfkrq){
		 this.zlfkrq = newZlfkrq;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�1>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve1(){
		 return reserve1;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�1>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve1(String newReserve1){
		 this.reserve1 = newReserve1;
	}
	/** 
	 * 
	 * ����<�޸���>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getModifier(){
		 return modifier;
	}
	/** 
	 * 
	 * ����<�޸���>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setModifier(String newModifier){
		 this.modifier = newModifier;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�18>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve18(){
		 return reserve18;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�18>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve18(String newReserve18){
		 this.reserve18 = newReserve18;
	}
	/** 
	 * 
	 * ����<ҵ������>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDate getDbilldate(){
		 return dbilldate;
	}
	/** 
	 * 
	 * ����<ҵ������>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setDbilldate(UFDate newDbilldate){
		 this.dbilldate = newDbilldate;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�17>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve17(){
		 return reserve17;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�17>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve17(String newReserve17){
		 this.reserve17 = newReserve17;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�16>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve16(){
		 return reserve16;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�16>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve16(String newReserve16){
		 this.reserve16 = newReserve16;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�15>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve15(){
		 return reserve15;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�15>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve15(String newReserve15){
		 this.reserve15 = newReserve15;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�19>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getReserve19(){
		 return reserve19;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�19>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setReserve19(String newReserve19){
		 this.reserve19 = newReserve19;
	}
	/** 
	 * 
	 * ����<���ε�������>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getVlastbilltype(){
		 return vlastbilltype;
	}
	/** 
	 * 
	 * ����<���ε�������>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVlastbilltype(String newVlastbilltype){
		 this.vlastbilltype = newVlastbilltype;
	}
	/** 
	 * 
	 * ����<������Ա>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getYsry(){
		 return ysry;
	}
	/** 
	 * 
	 * ����<������Ա>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setYsry(String newYsry){
		 this.ysry = newYsry;
	}
	/** 
	 * 
	 * ����<�����޸���>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getYqxgr(){
		 return yqxgr;
	}
	/** 
	 * 
	 * ����<�����޸���>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setYqxgr(String newYqxgr){
		 this.yqxgr = newYqxgr;
	}
	/** 
	 * 
	 * ����<�Ƶ���>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getVoperatorid(){
		 return voperatorid;
	}
	/** 
	 * 
	 * ����<�Ƶ���>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVoperatorid(String newVoperatorid){
		 this.voperatorid = newVoperatorid;
	}
	/** 
	 * 
	 * ����<�������>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getVapprovenote(){
		 return vapprovenote;
	}
	/** 
	 * 
	 * ����<�������>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVapprovenote(String newVapprovenote){
		 this.vapprovenote = newVapprovenote;
	}
	/** 
	 * 
	 * ����<�޸�����>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDate getDmodify(){
		 return dmodify;
	}
	/** 
	 * 
	 * ����<�޸�����>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setDmodify(UFDate newDmodify){
		 this.dmodify = newDmodify;
	}
	/** 
	 * 
	 * ����<�����>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getVapproveid(){
		 return vapproveid;
	}
	/** 
	 * 
	 * ����<�����>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVapproveid(String newVapproveid){
		 this.vapproveid = newVapproveid;
	}
	/** 
	 * 
	 * ����<ҵ������>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getPk_busitype(){
		 return pk_busitype;
	}
	/** 
	 * 
	 * ����<ҵ������>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setPk_busitype(String newPk_busitype){
		 this.pk_busitype = newPk_busitype;
	}
	/** 
	 * 
	 * ����<�깤��������>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public Integer getWgyqts(){
		 return wgyqts;
	}
	/** 
	 * 
	 * ����<�깤��������>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setWgyqts(Integer newWgyqts){
		 this.wgyqts = newWgyqts;
	}
	
	/** 
	 * 
	 * ����<��˾ID>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getPk_corp(){
		 return pk_corp;
	}
	/** 
	 * 
	 * ����<��˾ID>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setPk_corp(String newPk_corp){
		 this.pk_corp = newPk_corp;
	}
	/** 
	 * 
	 * ����<��װ��������>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDate getAzfkrq(){
		 return azfkrq;
	}
	/** 
	 * 
	 * ����<��װ��������>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setAzfkrq(UFDate newAzfkrq){
		 this.azfkrq = newAzfkrq;
	}
	/** 
	 * 
	 * ����<��Դ��������>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getVsourcebilltype(){
		 return vsourcebilltype;
	}
	/** 
	 * 
	 * ����<��Դ��������>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVsourcebilltype(String newVsourcebilltype){
		 this.vsourcebilltype = newVsourcebilltype;
	}
	/** 
	 * 
	 * ����<��Ŀ>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getPk_jobbasfil(){
		 return pk_jobbasfil;
	}
	/** 
	 * 
	 * ����<��Ŀ>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setPk_jobbasfil(String newPk_jobbasfil){
		 this.pk_jobbasfil = newPk_jobbasfil;
	}
	/** 
	 * 
	 * ����<���ݺ�>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getVbillcode(){
		 return vbillcode;
	}
	/** 
	 * 
	 * ����<���ݺ�>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVbillcode(String newVbillcode){
		 this.vbillcode = newVbillcode;
	}
	/** 
	 * 
	 * ����<����״̬>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public Integer getVbillstatus(){
		 return vbillstatus;
	}
	/** 
	 * 
	 * ����<����״̬>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVbillstatus(Integer newVbillstatus){
		 this.vbillstatus = newVbillstatus;
	}
	/** 
	 * 
	 * ����<�������>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDate getDapprovedate(){
		 return dapprovedate;
	}
	/** 
	 * 
	 * ����<�������>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setDapprovedate(UFDate newDapprovedate){
		 this.dapprovedate = newDapprovedate;
	}
	/** 
	 * 
	 * ����<��Դ������ID>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getVsourcebillrowid(){
		 return vsourcebillrowid;
	}
	/** 
	 * 
	 * ����<��Դ������ID>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVsourcebillrowid(String newVsourcebillrowid){
		 this.vsourcebillrowid = newVsourcebillrowid;
	}
	/** 
	 * 
	 * ����<��Դ����ID>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getVsourcebillid(){
		 return vsourcebillid;
	}
	/** 
	 * 
	 * ����<��Դ����ID>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setVsourcebillid(String newVsourcebillid){
		 this.vsourcebillid = newVsourcebillid;
	}
	/** 
	 * 
	 * ����<����>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public String getPk_azxxfkhid(){
		 return pk_azxxfkhid;
	}
	/** 
	 * 
	 * ����<����>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setPk_azxxfkhid(String newPk_azxxfkhid){
		 this.pk_azxxfkhid = newPk_azxxfkhid;
	}

	/** 
	 * 
	 *����<ʱ���>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public UFDateTime getTs() {
		return ts;
	}

	/** 
	 * 
	 * ����<ʱ���>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setTs(UFDateTime newTs ) {
		this.ts = newTs;
	}

	/** 
	 * 
	 *����<ɾ����־>��Getter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public Integer getDr() {
		return dr;
	}

	/** 
	 * 
	 * ����<ɾ����־>��Setter����
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	 * 
	 */ 
	public void setDr(Integer newDr ) {
		this.dr = newDr;
	}

	/** 
	 * ȡ�ø�VO�����ֶ�
	 * 
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	*/ 
	public java.lang.String getParentPKFieldName() {
		return null;
	}
	/** 
	 * ȡ�ñ�����
	 * 
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	*/ 
	public java.lang.String getPKFieldName() {
		return "pk_azxxfkhid";
	}
	/** 
	 * ���ر�����
	 * 
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	*/ 
	public java.lang.String getTableName() {
		return "lcsw_azxxfk_hCopy";
	}
	/** 
	 * ����Ĭ�Ϸ�ʽ����������
	 * 
	 * <b>����ʱ��   2014-09-03 17:11:19</b>
	*/ 
	public AzxxfkHVOC() {
		super();
	}
}
