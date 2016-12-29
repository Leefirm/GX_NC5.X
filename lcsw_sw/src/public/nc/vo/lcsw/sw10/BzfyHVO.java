package nc.vo.lcsw.sw10;
/** 
 * <b> 报装费用主表  </b>
 * <p>
 *     在此处添加此类的描述信息 
 * <p>
 * @author xuns
 * <b>创建时间: 2014-08-12 16:22:09</b>
 * 
 * 
*/ 
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.pub.lang.*;
import nc.vo.pub.*;

public class BzfyHVO extends SuperVO implements Cloneable{
	
	public Object clone() {
		BzfyHVO o = null;
		try {
			o = (BzfyHVO) super.clone();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return o;
	}
	private String vlastbillid;
	private UFDouble jsgck;
	private String yhzh;
	private String reserve10;
	private UFDateTime ts;
	private String reserve11;
	private String remark;
	private String reserve12;
	private String reserve13;
	private UFDouble bjkcf;
	private UFDate dmakedate;
	private String reserve14;
	private String vsourcebillno;
	private String pk_billtype;
	private String reserve20;
	private UFDouble reserve7;
	private UFDouble kcsjf;
	private UFDouble reserve6;
	private UFDouble reserve5;
	private String reserve4;
	private String reserve9;
	private UFDouble reserve8;
	private String reserve3;
	private String reserve2;
	private String reserve1;
	private String modifier;
	private String reserve18;
	private UFDate dbilldate;
	private String fkfs;
	private String reserve17;
	private String reserve16;
	private String reserve15;
	private String reserve19;
	private String vlastbilltype;
	private Integer jyskqs;
	private String voperatorid;
	private String vapprovenote;
	private UFDouble ysgck3;
	private UFDouble ysgck1;
	private UFDouble ysgck2;
	private UFDate dmodify;
	private String pk_bzsq;
	private String vapproveid;
	private String khyh;
	private String pk_busitype;
	private Integer dr;
	private String pk_corp;
	private String jmlx;
	private String pk_bzfy_h;
	private String vsourcebilltype;
	private String vbillcode;
	private Integer vbillstatus;
	private UFDate dapprovedate;
	private String vsourcebillid;
	private String slbh;//受理编号
	private String gcmc;
	
	//add by crf 2015-06-17 增加[是否共同出资]、[是否三优先]、[用水户数] 
	private UFBoolean sfsyx;
	private UFBoolean gtcz;
	private Integer yshs;
	
	
	public UFBoolean getSfsyx() {
		return sfsyx;
	}
	public void setSfsyx(UFBoolean sfsyx) {
		this.sfsyx = sfsyx;
	}
	public UFBoolean getGtcz() {
		return gtcz;
	}
	public void setGtcz(UFBoolean gtcz) {
		this.gtcz = gtcz;
	}
	public Integer getYshs() {
		return yshs;
	}
	public void setYshs(Integer yshs) {
		this.yshs = yshs;
	}
	
	//------------end 2015-06-17-------------
	
	public String getGcmc() {
		return gcmc;
	}
	public void setGcmc(String gcmc) {
		this.gcmc = gcmc;
	}
	public String getSlbh() {
		return slbh;
	}
	public void setSlbh(String slbh) {
		this.slbh = slbh;
	}
	
	public static final String GCMC = "gcmc";
	public static final String SLBH = "slbh";
	public static final String VLASTBILLID = "vlastbillid";
	public static final String JSGCK = "jsgck";
	public static final String YHZH = "yhzh";
	public static final String RESERVE10 = "reserve10";
	public static final String TS = "ts";
	public static final String RESERVE11 = "reserve11";
	public static final String REMARK = "remark";
	public static final String RESERVE12 = "reserve12";
	public static final String RESERVE13 = "reserve13";
	public static final String BJKCF = "bjkcf";
	public static final String DMAKEDATE = "dmakedate";
	public static final String RESERVE14 = "reserve14";
	public static final String VSOURCEBILLNO = "vsourcebillno";
	public static final String PK_BILLTYPE = "pk_billtype";
	public static final String RESERVE20 = "reserve20";
	public static final String RESERVE7 = "reserve7";
	public static final String KCSJF = "kcsjf";
	public static final String RESERVE6 = "reserve6";
	public static final String RESERVE5 = "reserve5";
	public static final String RESERVE4 = "reserve4";
	public static final String RESERVE9 = "reserve9";
	public static final String RESERVE8 = "reserve8";
	public static final String RESERVE3 = "reserve3";
	public static final String RESERVE2 = "reserve2";
	public static final String RESERVE1 = "reserve1";
	public static final String MODIFIER = "modifier";
	public static final String RESERVE18 = "reserve18";
	public static final String DBILLDATE = "dbilldate";
	public static final String FKFS = "fkfs";
	public static final String RESERVE17 = "reserve17";
	public static final String RESERVE16 = "reserve16";
	public static final String RESERVE15 = "reserve15";
	public static final String RESERVE19 = "reserve19";
	public static final String VLASTBILLTYPE = "vlastbilltype";
	public static final String JYSKQS = "jyskqs";
	public static final String VOPERATORID = "voperatorid";
	public static final String VAPPROVENOTE = "vapprovenote";
	public static final String YSGCK3 = "ysgck3";
	public static final String YSGCK1 = "ysgck1";
	public static final String YSGCK2 = "ysgck2";
	public static final String DMODIFY = "dmodify";
	public static final String PK_BZSQ = "pk_bzsq";
	public static final String VAPPROVEID = "vapproveid";
	public static final String KHYH = "khyh";
	public static final String PK_BUSITYPE = "pk_busitype";
	public static final String DR = "dr";
	public static final String PK_CORP = "pk_corp";
	public static final String JMLX = "jmlx";
	public static final String PK_BZFY_H = "pk_bzfy_h";
	public static final String VSOURCEBILLTYPE = "vsourcebilltype";
	public static final String VBILLCODE = "vbillcode";
	public static final String VBILLSTATUS = "vbillstatus";
	public static final String DAPPROVEDATE = "dapprovedate";
	public static final String VSOURCEBILLID = "vsourcebillid";

	/** 
	 * 
	 * 属性<下游单据ID>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getVlastbillid(){
		 return vlastbillid;
	}
	/** 
	 * 
	 * 属性<下游单据ID>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setVlastbillid(String newVlastbillid){
		 this.vlastbillid = newVlastbillid;
	}
	/** 
	 * 
	 * 属性<决算工程款>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDouble getJsgck(){
		 return jsgck;
	}
	/** 
	 * 
	 * 属性<决算工程款>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setJsgck(UFDouble newJsgck){
		 this.jsgck = newJsgck;
	}
	/** 
	 * 
	 * 属性<银行账号>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getYhzh(){
		 return yhzh;
	}
	/** 
	 * 
	 * 属性<银行账号>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setYhzh(String newYhzh){
		 this.yhzh = newYhzh;
	}
	/** 
	 * 
	 * 属性<预留字段10>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve10(){
		 return reserve10;
	}
	/** 
	 * 
	 * 属性<预留字段10>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve10(String newReserve10){
		 this.reserve10 = newReserve10;
	}
	/** 
	 * 
	 * 属性<时戳>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDateTime getTs(){
		 return ts;
	}
	/** 
	 * 
	 * 属性<时戳>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setTs(UFDateTime newTs){
		 this.ts = newTs;
	}
	/** 
	 * 
	 * 属性<预留字段11>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve11(){
		 return reserve11;
	}
	/** 
	 * 
	 * 属性<预留字段11>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve11(String newReserve11){
		 this.reserve11 = newReserve11;
	}
	/** 
	 * 
	 * 属性<备注>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getRemark(){
		 return remark;
	}
	/** 
	 * 
	 * 属性<备注>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setRemark(String newRemark){
		 this.remark = newRemark;
	}
	/** 
	 * 
	 * 属性<预留字段12>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve12(){
		 return reserve12;
	}
	/** 
	 * 
	 * 属性<预留字段12>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve12(String newReserve12){
		 this.reserve12 = newReserve12;
	}
	/** 
	 * 
	 * 属性<预留字段13>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve13(){
		 return reserve13;
	}
	/** 
	 * 
	 * 属性<预留字段13>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve13(String newReserve13){
		 this.reserve13 = newReserve13;
	}
	/** 
	 * 
	 * 属性<补交勘查费>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDouble getBjkcf(){
		 return bjkcf;
	}
	/** 
	 * 
	 * 属性<补交勘查费>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setBjkcf(UFDouble newBjkcf){
		 this.bjkcf = newBjkcf;
	}
	/** 
	 * 
	 * 属性<制单日期>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDate getDmakedate(){
		 return dmakedate;
	}
	/** 
	 * 
	 * 属性<制单日期>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setDmakedate(UFDate newDmakedate){
		 this.dmakedate = newDmakedate;
	}
	/** 
	 * 
	 * 属性<预留字段14>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve14(){
		 return reserve14;
	}
	/** 
	 * 
	 * 属性<预留字段14>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve14(String newReserve14){
		 this.reserve14 = newReserve14;
	}
	/** 
	 * 
	 * 属性<上游单据号>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getVsourcebillno(){
		 return vsourcebillno;
	}
	/** 
	 * 
	 * 属性<上游单据号>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setVsourcebillno(String newVsourcebillno){
		 this.vsourcebillno = newVsourcebillno;
	}
	/** 
	 * 
	 * 属性<单据类型>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getPk_billtype(){
		 return pk_billtype;
	}
	/** 
	 * 
	 * 属性<单据类型>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setPk_billtype(String newPk_billtype){
		 this.pk_billtype = newPk_billtype;
	}
	/** 
	 * 
	 * 属性<预留字段20>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve20(){
		 return reserve20;
	}
	/** 
	 * 
	 * 属性<预留字段20>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve20(String newReserve20){
		 this.reserve20 = newReserve20;
	}
	/** 
	 * 
	 * 属性<预留字段7>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDouble getReserve7(){
		 return reserve7;
	}
	/** 
	 * 
	 * 属性<预留字段7>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve7(UFDouble newReserve7){
		 this.reserve7 = newReserve7;
	}
	/** 
	 * 
	 * 属性<勘查设计费>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDouble getKcsjf(){
		 return kcsjf;
	}
	/** 
	 * 
	 * 属性<勘查设计费>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setKcsjf(UFDouble newKcsjf){
		 this.kcsjf = newKcsjf;
	}
	/** 
	 * 
	 * 属性<预留字段6>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDouble getReserve6(){
		 return reserve6;
	}
	/** 
	 * 
	 * 属性<预留字段6>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve6(UFDouble newReserve6){
		 this.reserve6 = newReserve6;
	}
	/** 
	 * 
	 * 属性<预留字段5>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDouble getReserve5(){
		 return reserve5;
	}
	/** 
	 * 
	 * 属性<预留字段5>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve5(UFDouble newReserve5){
		 this.reserve5 = newReserve5;
	}
	/** 
	 * 
	 * 属性<预留字段4>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve4(){
		 return reserve4;
	}
	/** 
	 * 
	 * 属性<预留字段4>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve4(String newReserve4){
		 this.reserve4 = newReserve4;
	}
	/** 
	 * 
	 * 属性<预留字段9>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve9(){
		 return reserve9;
	}
	/** 
	 * 
	 * 属性<预留字段9>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve9(String newReserve9){
		 this.reserve9 = newReserve9;
	}
	/** 
	 * 
	 * 属性<预留字段8>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDouble getReserve8(){
		 return reserve8;
	}
	/** 
	 * 
	 * 属性<预留字段8>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve8(UFDouble newReserve8){
		 this.reserve8 = newReserve8;
	}
	/** 
	 * 
	 * 属性<预留字段3>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve3(){
		 return reserve3;
	}
	/** 
	 * 
	 * 属性<预留字段3>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve3(String newReserve3){
		 this.reserve3 = newReserve3;
	}
	/** 
	 * 
	 * 属性<预留字段2>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve2(){
		 return reserve2;
	}
	/** 
	 * 
	 * 属性<预留字段2>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve2(String newReserve2){
		 this.reserve2 = newReserve2;
	}
	/** 
	 * 
	 * 属性<预留字段1>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve1(){
		 return reserve1;
	}
	/** 
	 * 
	 * 属性<预留字段1>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve1(String newReserve1){
		 this.reserve1 = newReserve1;
	}
	/** 
	 * 
	 * 属性<修改人>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getModifier(){
		 return modifier;
	}
	/** 
	 * 
	 * 属性<修改人>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setModifier(String newModifier){
		 this.modifier = newModifier;
	}
	/** 
	 * 
	 * 属性<预留字段18>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve18(){
		 return reserve18;
	}
	/** 
	 * 
	 * 属性<预留字段18>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve18(String newReserve18){
		 this.reserve18 = newReserve18;
	}
	/** 
	 * 
	 * 属性<业务日期>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDate getDbilldate(){
		 return dbilldate;
	}
	/** 
	 * 
	 * 属性<业务日期>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setDbilldate(UFDate newDbilldate){
		 this.dbilldate = newDbilldate;
	}
	/** 
	 * 
	 * 属性<付款方式>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getFkfs(){
		 return fkfs;
	}
	/** 
	 * 
	 * 属性<付款方式>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setFkfs(String newFkfs){
		 this.fkfs = newFkfs;
	}
	/** 
	 * 
	 * 属性<预留字段17>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve17(){
		 return reserve17;
	}
	/** 
	 * 
	 * 属性<预留字段17>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve17(String newReserve17){
		 this.reserve17 = newReserve17;
	}
	/** 
	 * 
	 * 属性<预留字段16>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve16(){
		 return reserve16;
	}
	/** 
	 * 
	 * 属性<预留字段16>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve16(String newReserve16){
		 this.reserve16 = newReserve16;
	}
	/** 
	 * 
	 * 属性<预留字段15>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve15(){
		 return reserve15;
	}
	/** 
	 * 
	 * 属性<预留字段15>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve15(String newReserve15){
		 this.reserve15 = newReserve15;
	}
	/** 
	 * 
	 * 属性<预留字段19>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getReserve19(){
		 return reserve19;
	}
	/** 
	 * 
	 * 属性<预留字段19>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setReserve19(String newReserve19){
		 this.reserve19 = newReserve19;
	}
	/** 
	 * 
	 * 属性<下游单据类型>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getVlastbilltype(){
		 return vlastbilltype;
	}
	/** 
	 * 
	 * 属性<下游单据类型>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setVlastbilltype(String newVlastbilltype){
		 this.vlastbilltype = newVlastbilltype;
	}
	/** 
	 * 
	 * 属性<教预算款期数>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public Integer getJyskqs(){
		 return jyskqs;
	}
	/** 
	 * 
	 * 属性<教预算款期数>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setJyskqs(Integer newJyskqs){
		 this.jyskqs = newJyskqs;
	}
	/** 
	 * 
	 * 属性<制单人>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getVoperatorid(){
		 return voperatorid;
	}
	/** 
	 * 
	 * 属性<制单人>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setVoperatorid(String newVoperatorid){
		 this.voperatorid = newVoperatorid;
	}
	/** 
	 * 
	 * 属性<审核批语>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getVapprovenote(){
		 return vapprovenote;
	}
	/** 
	 * 
	 * 属性<审核批语>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setVapprovenote(String newVapprovenote){
		 this.vapprovenote = newVapprovenote;
	}
	/** 
	 * 
	 * 属性<预算工程款3期>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDouble getYsgck3(){
		 return ysgck3;
	}
	/** 
	 * 
	 * 属性<预算工程款3期>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setYsgck3(UFDouble newYsgck3){
		 this.ysgck3 = newYsgck3;
	}
	/** 
	 * 
	 * 属性<预算工程款1期>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDouble getYsgck1(){
		 return ysgck1;
	}
	/** 
	 * 
	 * 属性<预算工程款1期>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setYsgck1(UFDouble newYsgck1){
		 this.ysgck1 = newYsgck1;
	}
	/** 
	 * 
	 * 属性<预算工程款2期>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDouble getYsgck2(){
		 return ysgck2;
	}
	/** 
	 * 
	 * 属性<预算工程款2期>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setYsgck2(UFDouble newYsgck2){
		 this.ysgck2 = newYsgck2;
	}
	/** 
	 * 
	 * 属性<修改日期>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDate getDmodify(){
		 return dmodify;
	}
	/** 
	 * 
	 * 属性<修改日期>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setDmodify(UFDate newDmodify){
		 this.dmodify = newDmodify;
	}
	/** 
	 * 
	 * 属性<报装申请号>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getPk_bzsq(){
		 return pk_bzsq;
	}
	/** 
	 * 
	 * 属性<报装申请号>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setPk_bzsq(String newPk_bzsq){
		 this.pk_bzsq = newPk_bzsq;
	}
	/** 
	 * 
	 * 属性<审核人>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getVapproveid(){
		 return vapproveid;
	}
	/** 
	 * 
	 * 属性<审核人>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setVapproveid(String newVapproveid){
		 this.vapproveid = newVapproveid;
	}
	/** 
	 * 
	 * 属性<开户银行>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getKhyh(){
		 return khyh;
	}
	/** 
	 * 
	 * 属性<开户银行>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setKhyh(String newKhyh){
		 this.khyh = newKhyh;
	}
	/** 
	 * 
	 * 属性<业务类型>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getPk_busitype(){
		 return pk_busitype;
	}
	/** 
	 * 
	 * 属性<业务类型>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setPk_busitype(String newPk_busitype){
		 this.pk_busitype = newPk_busitype;
	}
	/** 
	 * 
	 * 属性<删除>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public Integer getDr(){
		 return dr;
	}
	/** 
	 * 
	 * 属性<删除>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setDr(Integer newDr){
		 this.dr = newDr;
	}
	/** 
	 * 
	 * 属性<公司ID>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getPk_corp(){
		 return pk_corp;
	}
	/** 
	 * 
	 * 属性<公司ID>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setPk_corp(String newPk_corp){
		 this.pk_corp = newPk_corp;
	}
	/** 
	 * 
	 * 属性<减免类型>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getJmlx(){
		 return jmlx;
	}
	/** 
	 * 
	 * 属性<减免类型>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setJmlx(String newJmlx){
		 this.jmlx = newJmlx;
	}
	/** 
	 * 
	 * 属性<主键>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getPk_bzfy_h(){
		 return pk_bzfy_h;
	}
	/** 
	 * 
	 * 属性<主键>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setPk_bzfy_h(String newPk_bzfy_h){
		 this.pk_bzfy_h = newPk_bzfy_h;
	}
	/** 
	 * 
	 * 属性<来源单据类型>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getVsourcebilltype(){
		 return vsourcebilltype;
	}
	/** 
	 * 
	 * 属性<来源单据类型>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setVsourcebilltype(String newVsourcebilltype){
		 this.vsourcebilltype = newVsourcebilltype;
	}
	/** 
	 * 
	 * 属性<单据号>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getVbillcode(){
		 return vbillcode;
	}
	/** 
	 * 
	 * 属性<单据号>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setVbillcode(String newVbillcode){
		 this.vbillcode = newVbillcode;
	}
	/** 
	 * 
	 * 属性<单据状态>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public Integer getVbillstatus(){
		 return vbillstatus;
	}
	/** 
	 * 
	 * 属性<单据状态>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setVbillstatus(Integer newVbillstatus){
		 this.vbillstatus = newVbillstatus;
	}
	/** 
	 * 
	 * 属性<审核日期>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public UFDate getDapprovedate(){
		 return dapprovedate;
	}
	/** 
	 * 
	 * 属性<审核日期>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setDapprovedate(UFDate newDapprovedate){
		 this.dapprovedate = newDapprovedate;
	}
	/** 
	 * 
	 * 属性<来源单据ID>的Getter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public String getVsourcebillid(){
		 return vsourcebillid;
	}
	/** 
	 * 
	 * 属性<来源单据ID>的Setter方法
	 * <b>创建时间   2014-08-13 15:49:37</b>
	 * 
	 */ 
	public void setVsourcebillid(String newVsourcebillid){
		 this.vsourcebillid = newVsourcebillid;
	}



	

	/** 
	 * 取得父VO主键字段
	 * 
	 * <b>创建时间   2014-08-13 15:49:37</b>
	*/ 
	public java.lang.String getParentPKFieldName() {
		return null;
	}
	/** 
	 * 取得表主键
	 * 
	 * <b>创建时间   2014-08-13 15:49:37</b>
	*/ 
	public java.lang.String getPKFieldName() {
		return "pk_bzfy_h";
	}
	/** 
	 * 返回表名称
	 * 
	 * <b>创建时间   2014-08-13 15:49:37</b>
	*/ 
	public java.lang.String getTableName() {
		return "lcsw_bzfy_h";
	}
	/** 
	 * 按照默认方式创建构造子
	 * 
	 * <b>创建时间   2014-08-13 15:49:37</b>
	*/ 
	public BzfyHVO() {
		super();
	}
}
