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

public class YsfksjBVO extends SuperVO {
	private String biaobie;
	private String reserve10;
	private String reserve11;
	private UFDateTime ts;
	private UFDate zbrq;
	private String reserve12;
	private String remark;
	private String reserve13;
	private String reserve14;
	private String yqxgr;
	private UFDouble cchd;
	private UFDate yjysrq;
	private UFDate yqxgrq;
	private UFBoolean yshgbz;
	private String pk_ysfksjhid;
	private Integer wgyqts;
	private Integer dr;
	private String reserve20;
	private UFDouble reserve7;
	private UFDouble reserve6;
	private UFDouble reserve5;
	private String bianma;
	private String reserve4;
	private String ysy;
	private String reserve9;
	private String pk_ysfksjbid;
	private UFDouble reserve8;
	private String pk_bzxx;
	private String yslxdh;
	private String reserve3;
	private String pk_jobbasfil;
	private String reserve2;
	private String reserve1;
	private String chandi;
	private String biaowei;
	private String biaoma;
	private UFDate yjzgrq;
	private String bhgyy;
	private UFDouble ysxd;
	private String reserve18;
	private String reserve17;
	private String reserve16;
	private String reserve15;
	private UFDate ysrq;
	private String reserve19;

	public static final String BIAOBIE = "biaobie";
	public static final String RESERVE10 = "reserve10";
	public static final String RESERVE11 = "reserve11";
	public static final String TS = "ts";
	public static final String ZBRQ = "zbrq";
	public static final String RESERVE12 = "reserve12";
	public static final String REMARK = "remark";
	public static final String RESERVE13 = "reserve13";
	public static final String RESERVE14 = "reserve14";
	public static final String YQXGR = "yqxgr";
	public static final String CCHD = "cchd";
	public static final String YJYSRQ = "yjysrq";
	public static final String YQXGRQ = "yqxgrq";
	public static final String YSHGBZ = "yshgbz";
	public static final String PK_YSFKSJHID = "pk_ysfksjhid";
	public static final String WGYQTS = "wgyqts";
	public static final String DR = "dr";
	public static final String RESERVE20 = "reserve20";
	public static final String RESERVE7 = "reserve7";
	public static final String RESERVE6 = "reserve6";
	public static final String RESERVE5 = "reserve5";
	public static final String BIANMA = "bianma";
	public static final String RESERVE4 = "reserve4";
	public static final String YSY = "ysy";
	public static final String RESERVE9 = "reserve9";
	public static final String PK_YSFKSJBID = "pk_ysfksjbid";
	public static final String RESERVE8 = "reserve8";
	public static final String PK_BZXX = "pk_bzxx";
	public static final String YSLXDH = "yslxdh";
	public static final String RESERVE3 = "reserve3";
	public static final String PK_JOBBASFIL = "pk_jobbasfil";
	public static final String RESERVE2 = "reserve2";
	public static final String RESERVE1 = "reserve1";
	public static final String CHANDI = "chandi";
	public static final String BIAOWEI = "biaowei";
	public static final String BIAOMA = "biaoma";
	public static final String YJZGRQ = "yjzgrq";
	public static final String BHGYY = "bhgyy";
	public static final String YSXD = "ysxd";
	public static final String RESERVE18 = "reserve18";
	public static final String RESERVE17 = "reserve17";
	public static final String RESERVE16 = "reserve16";
	public static final String RESERVE15 = "reserve15";
	public static final String YSRQ = "ysrq";
	public static final String RESERVE19 = "reserve19";
	
	private String pk_bzsq;
	
	public static final String PK_BZSQ="pk_bzsq";
	/**
	 */
	public String getPk_bzsq() {
		return pk_bzsq;
	}

	/**
	 */
	public void setPk_bzsq(String pk_bzsq) {
		this.pk_bzsq = pk_bzsq;
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
	 * ����<�����ж�>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDouble getCchd(){
		 return cchd;
	}
	/** 
	 * 
	 * ����<�����ж�>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setCchd(UFDouble newCchd){
		 this.cchd = newCchd;
	}
	/** 
	 * 
	 * ����<�ƽ���������>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public UFDate getYjysrq(){
		 return yjysrq;
	}
	/** 
	 * 
	 * ����<�ƽ���������>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setYjysrq(UFDate newYjysrq){
		 this.yjysrq = newYjysrq;
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
	 * ����<��ͷ����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getPk_ysfksjhid(){
		 return pk_ysfksjhid;
	}
	/** 
	 * 
	 * ����<��ͷ����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setPk_ysfksjhid(String newPk_ysfksjhid){
		 this.pk_ysfksjhid = newPk_ysfksjhid;
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
	public UFDouble getReserve5(){
		 return reserve5;
	}
	/** 
	 * 
	 * ����<Ԥ���ֶ�5>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setReserve5(UFDouble newReserve5){
		 this.reserve5 = newReserve5;
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
	 * ����<����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getPk_ysfksjbid(){
		 return pk_ysfksjbid;
	}
	/** 
	 * 
	 * ����<����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setPk_ysfksjbid(String newPk_ysfksjbid){
		 this.pk_ysfksjbid = newPk_ysfksjbid;
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
	 * ����<��װ����>��Getter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public String getPk_bzxx(){
		 return pk_bzxx;
	}
	/** 
	 * 
	 * ����<��װ����>��Setter����
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	 * 
	 */ 
	public void setPk_bzxx(String newPk_bzxx){
		 this.pk_bzxx = newPk_bzxx;
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
	 * ȡ�ø�VO�����ֶ�
	 * 
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	*/ 
	public java.lang.String getParentPKFieldName() {
		return "pk_ysfksjhid";
	}
	/** 
	 * ȡ�ñ�����
	 * 
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	*/ 
	public java.lang.String getPKFieldName() {
		return "pk_ysfksjbid";
	}
	/** 
	 * ���ر�����
	 * 
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	*/ 
	public java.lang.String getTableName() {
		return "lcsw_ysfksj_b";
	}
	/** 
	 * ����Ĭ�Ϸ�ʽ����������
	 * 
	 * <b>����ʱ��   2014-08-21 11:18:55</b>
	*/ 
	public YsfksjBVO() {
		super();
	}
}
