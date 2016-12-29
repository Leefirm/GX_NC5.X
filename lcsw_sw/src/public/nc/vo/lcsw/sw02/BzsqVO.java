package nc.vo.lcsw.sw02;

/** 
 * <b> �ڴ˴���Ҫ��������Ĺ���  </b>
 * <p>
 *     �ڴ˴���Ӵ����������Ϣ 
 * <p>
 * @author xuns
 * <b>����ʱ��: 2014-08-04 14:34:44</b>
 * 
 * 
 */
import nc.vo.pub.lang.*;
import nc.vo.pub.*;

public class BzsqVO extends SuperVO implements Cloneable {
	@Override
	public Object clone() {
		BzsqVO 	o = null;
		try {
			o = (BzsqVO) super.clone();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return o;
	}

	private Integer kcwgyqts;
	private String ysdh;
	private String reserve10;
	private String vlastbillid;
	private String reserve11;
	private UFDate zbrq;
	private String reserve12;
	private String reserve13;
	private String reserve14;
	private Integer gc;
	private String gcxz;
	private UFBoolean eqgcbz;
	private String bzjbry;
	private UFDate yjysrq;
	private String swgj;
	private UFDate jhxtrq;
	private String rhbh;
	private UFBoolean gtcz;
	private String reserve20;
	private UFDouble kcsjf;
	private String bianma;
	private UFDate kcrq2;
	private String yslxdh;
	private UFDate azrq;
	private String biaoma;
	private String bhgyy;
	private String reserve18;
	private String reserve17;
	private String reserve16;
	private String reserve15;
	private String reserve19;
	private String vlastbilltype;
	private String swsgdw;
	private Integer gcwgyqts;
	private UFBoolean sjbs;
	private String voperatorid;
	private String vapprovenote;
	private String azdz;
	private String kcsjry;
	private String vapproveid;
	private Integer yshgbz;
	private String pk_busitype;
	private Integer dr;
	private String ysy;
	private String slbh;
	private String kcpgry;
	private Integer sgts;
	private String chandi;
	private String vbillcode;
	private String lxr;
	private UFDate kcpgrq;
	private Integer kcxk;
	private Integer azwgyqts;
	private Integer zgwgyqts;
	private String snkj;
	private String vsourcebillid;
	private UFDate jsqbrq;
	private UFDate yjhhxrq;
	private UFDate yjsjrq;
	private String yhzh;
	private UFDateTime ts;
	private String remark;
	private UFDate dmakedate;
	private String lxdz;
	private UFDouble cchd;
	private String vsourcebillno;
	private UFDate kcrq;
	private String sjdw;
	private String ygj;
	private UFDate jgrq;
	private String kcry;
	private String swkj;
	private UFDouble jzmj;
	private UFDate kcfkrq;
	private String pk_billtype;
	private UFDouble reserve7;
	private UFDate yjazrq;
	private String hth;
	private UFDouble reserve6;
	private UFDouble reserve5;
	private String reserve4;
	private String reserve9;
	private Integer sbgs;
	private UFDouble reserve8;
	private String pk_bzsq;
	private String lxdd;
	private String reserve3;
	private Integer yswgyqts;
	private String reserve2;
	private String ysxz;
	private String reserve1;
	private UFDate zlfkrq;
	private String modifier;
	private Integer yshs;
	private UFDate yjzgrq;
	private String zlxfs;
	private UFDouble ysxd;
	private UFDate dbilldate;
	private String fkfs;
	private String bzbh;
	private Integer zgcs;
	private String biaobie;
	private String ysry;
	private Integer jyskqs;
	private String hm;
	private Integer bzzt;
	private UFDate dmodify;
	private String khyh;
	private UFDate bzrq;
	private String jbry;
	private String pk_corp;
	private UFDate sjpswcrq;
	private Integer sjwgyqts;
	private String jmlx;
	private String snsgdw;
	private UFDate azfkrq;
	private String pk_jobbasfil;
	private String vsourcebilltype;
	private UFDate yjsjpsrq;
	private String biaowei;
	private UFDate zgrq;
	private String xybh;
	private Integer zghgbz;
	private Integer vbillstatus;
	private Integer zdsl;
	private String sngj;
	private UFDate dapprovedate;
	private Integer ysrs;
	private UFDate ysrq;
	private UFBoolean sfsyx;
	private String sfzh;
	private String yyzzh;
	private String yyqy;
	private String sfgz;
	private String xmda;
	private String biaojing;
	

	public static final String KCWGYQTS = "kcwgyqts";
	public static final String YSDH = "ysdh";
	public static final String RESERVE10 = "reserve10";
	public static final String VLASTBILLID = "vlastbillid";
	public static final String RESERVE11 = "reserve11";
	public static final String ZBRQ = "zbrq";
	public static final String RESERVE12 = "reserve12";
	public static final String RESERVE13 = "reserve13";
	public static final String RESERVE14 = "reserve14";
	public static final String GC = "gc";
	public static final String GCXZ = "gcxz";
	public static final String EQGCBZ = "eqgcbz";
	public static final String BZJBRY = "bzjbry";
	public static final String YJYSRQ = "yjysrq";
	public static final String SWGJ = "swgj";
	public static final String JHXTRQ = "jhxtrq";
	public static final String RHBH = "rhbh";
	public static final String GTCZ = "gtcz";
	public static final String RESERVE20 = "reserve20";
	public static final String KCSJF = "kcsjf";
	public static final String BIANMA = "bianma";
	public static final String KCRQ2 = "kcrq2";
	public static final String YSLXDH = "yslxdh";
	public static final String AZRQ = "azrq";
	public static final String BIAOMA = "biaoma";
	public static final String BHGYY = "bhgyy";
	public static final String RESERVE18 = "reserve18";
	public static final String RESERVE17 = "reserve17";
	public static final String RESERVE16 = "reserve16";
	public static final String RESERVE15 = "reserve15";
	public static final String RESERVE19 = "reserve19";
	public static final String VLASTBILLTYPE = "vlastbilltype";
	public static final String SWSGDW = "swsgdw";
	public static final String GCWGYQTS = "gcwgyqts";
	public static final String SJBS = "sjbs";
	public static final String VOPERATORID = "voperatorid";
	public static final String VAPPROVENOTE = "vapprovenote";
	public static final String AZDZ = "azdz";
	public static final String KCSJRY = "kcsjry";
	public static final String VAPPROVEID = "vapproveid";
	public static final String YSHGBZ = "yshgbz";
	public static final String PK_BUSITYPE = "pk_busitype";
	public static final String DR = "dr";
	public static final String YSY = "ysy";
	public static final String SLBH = "slbh";
	public static final String KCPGRY = "kcpgry";
	public static final String SGTS = "sgts";
	public static final String CHANDI = "chandi";
	public static final String VBILLCODE = "vbillcode";
	public static final String LXR = "lxr";
	public static final String KCPGRQ = "kcpgrq";
	public static final String KCXK = "kcxk";
	public static final String AZWGYQTS = "azwgyqts";
	public static final String ZGWGYQTS = "zgwgyqts";
	public static final String SNKJ = "snkj";
	public static final String VSOURCEBILLID = "vsourcebillid";
	public static final String JSQBRQ = "jsqbrq";
	public static final String YJHHXRQ = "yjhhxrq";
	public static final String YJSJRQ = "yjsjrq";
	public static final String YHZH = "yhzh";
	public static final String TS = "ts";
	public static final String REMARK = "remark";
	public static final String DMAKEDATE = "dmakedate";
	public static final String LXDZ = "lxdz";
	public static final String CCHD = "cchd";
	public static final String VSOURCEBILLNO = "vsourcebillno";
	public static final String KCRQ = "kcrq";
	public static final String SJDW = "sjdw";
	public static final String YGJ = "ygj";
	public static final String JGRQ = "jgrq";
	public static final String KCRY = "kcry";
	public static final String SWKJ = "swkj";
	public static final String JZMJ = "jzmj";
	public static final String KCFKRQ = "kcfkrq";
	public static final String PK_BILLTYPE = "pk_billtype";
	public static final String RESERVE7 = "reserve7";
	public static final String YJAZRQ = "yjazrq";
	public static final String HTH = "hth";
	public static final String RESERVE6 = "reserve6";
	public static final String RESERVE5 = "reserve5";
	public static final String RESERVE4 = "reserve4";
	public static final String RESERVE9 = "reserve9";
	public static final String SBGS = "sbgs";
	public static final String RESERVE8 = "reserve8";
	public static final String PK_BZSQ = "pk_bzsq";
	public static final String LXDD = "lxdd";
	public static final String RESERVE3 = "reserve3";
	public static final String YSWGYQTS = "yswgyqts";
	public static final String RESERVE2 = "reserve2";
	public static final String YSXZ = "ysxz";
	public static final String RESERVE1 = "reserve1";
	public static final String ZLFKRQ = "zlfkrq";
	public static final String MODIFIER = "modifier";
	public static final String YSHS = "yshs";
	public static final String YJZGRQ = "yjzgrq";
	public static final String ZLXFS = "zlxfs";
	public static final String YSXD = "ysxd";
	public static final String DBILLDATE = "dbilldate";
	public static final String FKFS = "fkfs";
	public static final String BZBH = "bzbh";
	public static final String ZGCS = "zgcs";
	public static final String BIAOBIE = "biaobie";
	public static final String YSRY = "ysry";
	public static final String JYSKQS = "jyskqs";
	public static final String HM = "hm";
	public static final String BZZT = "bzzt";
	public static final String DMODIFY = "dmodify";
	public static final String KHYH = "khyh";
	public static final String BZRQ = "bzrq";
	public static final String JBRY = "jbry";
	public static final String PK_CORP = "pk_corp";
	public static final String SJPSWCRQ = "sjpswcrq";
	public static final String SJWGYQTS = "sjwgyqts";
	public static final String JMLX = "jmlx";
	public static final String SNSGDW = "snsgdw";
	public static final String AZFKRQ = "azfkrq";
	public static final String PK_JOBBASFIL = "pk_jobbasfil";
	public static final String VSOURCEBILLTYPE = "vsourcebilltype";
	public static final String YJSJPSRQ = "yjsjpsrq";
	public static final String BIAOWEI = "biaowei";
	public static final String ZGRQ = "zgrq";
	public static final String XYBH = "xybh";
	public static final String ZGHGBZ = "zghgbz";
	public static final String VBILLSTATUS = "vbillstatus";
	public static final String ZDSL = "zdsl";
	public static final String SNGJ = "sngj";
	public static final String DAPPROVEDATE = "dapprovedate";
	public static final String YSRS = "ysrs";
	public static final String SFSYX = "sfsyx";
	public static final String SFZH = "sfzh";
	public static final String YYZZH = "yyzzh";
	public static final String YYQY = "yyqy";
	public static final String SFGZ = "sfgz";
	public static final String XMDA = "xmda";
	public static final String BIAOJING = "biaojing";
	

	/**
	 * 
	 * ����<�����깤��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getKcwgyqts() {
		return kcwgyqts;
	}

	/**
	 * 
	 * ����<�����깤��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setKcwgyqts(Integer newKcwgyqts) {
		this.kcwgyqts = newKcwgyqts;
	}

	/**
	 * 
	 * ����<���յ绰>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getYsdh() {
		return ysdh;
	}

	/**
	 * 
	 * ����<���յ绰>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYsdh(String newYsdh) {
		this.ysdh = newYsdh;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�10>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve10() {
		return reserve10;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�10>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve10(String newReserve10) {
		this.reserve10 = newReserve10;
	}

	/**
	 * 
	 * ����<���ε���ID>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getVlastbillid() {
		return vlastbillid;
	}

	/**
	 * 
	 * ����<���ε���ID>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setVlastbillid(String newVlastbillid) {
		this.vlastbillid = newVlastbillid;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�11>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve11() {
		return reserve11;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�11>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve11(String newReserve11) {
		this.reserve11 = newReserve11;
	}

	/**
	 * 
	 * ����<װ������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getZbrq() {
		return zbrq;
	}

	/**
	 * 
	 * ����<װ������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setZbrq(UFDate newZbrq) {
		this.zbrq = newZbrq;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�12>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve12() {
		return reserve12;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�12>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve12(String newReserve12) {
		this.reserve12 = newReserve12;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�13>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve13() {
		return reserve13;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�13>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve13(String newReserve13) {
		this.reserve13 = newReserve13;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�14>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve14() {
		return reserve14;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�14>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve14(String newReserve14) {
		this.reserve14 = newReserve14;
	}

	/**
	 * 
	 * ����<�ܳ�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getGc() {
		return gc;
	}

	/**
	 * 
	 * ����<�ܳ�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setGc(Integer newGc) {
		this.gc = newGc;
	}

	/**
	 * 
	 * ����<��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getGcxz() {
		return gcxz;
	}

	/**
	 * 
	 * ����<��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setGcxz(String newGcxz) {
		this.gcxz = newGcxz;
	}

	/**
	 * 
	 * ����<���ڹ��̱�־>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFBoolean getEqgcbz() {
		return eqgcbz;
	}

	/**
	 * 
	 * ����<���ڹ��̱�־>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setEqgcbz(UFBoolean newEqgcbz) {
		this.eqgcbz = newEqgcbz;
	}

	/**
	 * 
	 * ����<��װ������Ա>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getBzjbry() {
		return bzjbry;
	}

	/**
	 * 
	 * ����<��װ������Ա>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setBzjbry(String newBzjbry) {
		this.bzjbry = newBzjbry;
	}

	/**
	 * 
	 * ����<�ƽ���������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getYjysrq() {
		return yjysrq;
	}

	/**
	 * 
	 * ����<�ƽ���������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYjysrq(UFDate newYjysrq) {
		this.yjysrq = newYjysrq;
	}

	/**
	 * 
	 * ����<����ܾ�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getSwgj() {
		return swgj;
	}

	/**
	 * 
	 * ����<����ܾ�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSwgj(String newSwgj) {
		this.swgj = newSwgj;
	}

	/**
	 * 
	 * ����<�Ӻ���ͼ����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getJhxtrq() {
		return jhxtrq;
	}

	/**
	 * 
	 * ����<�Ӻ���ͼ����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setJhxtrq(UFDate newJhxtrq) {
		this.jhxtrq = newJhxtrq;
	}

	/**
	 * 
	 * ����<�뻧���>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getRhbh() {
		return rhbh;
	}

	/**
	 * 
	 * ����<�뻧���>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setRhbh(String newRhbh) {
		this.rhbh = newRhbh;
	}

	/**
	 * 
	 * ����<�Ƿ�ͬ����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFBoolean getGtcz() {
		return gtcz;
	}

	/**
	 * 
	 * ����<�Ƿ�ͬ����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setGtcz(UFBoolean newGtcz) {
		this.gtcz = newGtcz;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�20>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve20() {
		return reserve20;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�20>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve20(String newReserve20) {
		this.reserve20 = newReserve20;
	}

	/**
	 * 
	 * ����<������Ʒ�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDouble getKcsjf() {
		return kcsjf;
	}

	/**
	 * 
	 * ����<������Ʒ�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setKcsjf(UFDouble newKcsjf) {
		this.kcsjf = newKcsjf;
	}

	/**
	 * 
	 * ����<����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getBianma() {
		return bianma;
	}

	/**
	 * 
	 * ����<����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setBianma(String newBianma) {
		this.bianma = newBianma;
	}

	/**
	 * 
	 * ����<��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getKcrq2() {
		return kcrq2;
	}

	/**
	 * 
	 * ����<��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setKcrq2(UFDate newKcrq2) {
		this.kcrq2 = newKcrq2;
	}

	/**
	 * 
	 * ����<������ϵ�绰>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getYslxdh() {
		return yslxdh;
	}

	/**
	 * 
	 * ����<������ϵ�绰>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYslxdh(String newYslxdh) {
		this.yslxdh = newYslxdh;
	}

	/**
	 * 
	 * ����<��װ����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getAzrq() {
		return azrq;
	}

	/**
	 * 
	 * ����<��װ����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setAzrq(UFDate newAzrq) {
		this.azrq = newAzrq;
	}

	/**
	 * 
	 * ����<����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getBiaoma() {
		return biaoma;
	}

	/**
	 * 
	 * ����<����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setBiaoma(String newBiaoma) {
		this.biaoma = newBiaoma;
	}

	/**
	 * 
	 * ����<���ϸ�ԭ��>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getBhgyy() {
		return bhgyy;
	}

	/**
	 * 
	 * ����<���ϸ�ԭ��>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setBhgyy(String newBhgyy) {
		this.bhgyy = newBhgyy;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�18>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve18() {
		return reserve18;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�18>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve18(String newReserve18) {
		this.reserve18 = newReserve18;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�17>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve17() {
		return reserve17;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�17>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve17(String newReserve17) {
		this.reserve17 = newReserve17;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�16>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve16() {
		return reserve16;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�16>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve16(String newReserve16) {
		this.reserve16 = newReserve16;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�15>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve15() {
		return reserve15;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�15>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve15(String newReserve15) {
		this.reserve15 = newReserve15;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�19>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve19() {
		return reserve19;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�19>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve19(String newReserve19) {
		this.reserve19 = newReserve19;
	}

	/**
	 * 
	 * ����<���ε�������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getVlastbilltype() {
		return vlastbilltype;
	}

	/**
	 * 
	 * ����<���ε�������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setVlastbilltype(String newVlastbilltype) {
		this.vlastbilltype = newVlastbilltype;
	}

	/**
	 * 
	 * ����<����ʩ����λ>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getSwsgdw() {
		return swsgdw;
	}

	/**
	 * 
	 * ����<����ʩ����λ>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSwsgdw(String newSwsgdw) {
		this.swsgdw = newSwsgdw;
	}

	/**
	 * 
	 * ����<�����깤��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getGcwgyqts() {
		return gcwgyqts;
	}

	/**
	 * 
	 * ����<�����깤��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setGcwgyqts(Integer newGcwgyqts) {
		this.gcwgyqts = newGcwgyqts;
	}

	/**
	 * 
	 * ����<��Ʊ�ʶ>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFBoolean getSjbs() {
		return sjbs;
	}

	/**
	 * 
	 * ����<��Ʊ�ʶ>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSjbs(UFBoolean newSjbs) {
		this.sjbs = newSjbs;
	}

	/**
	 * 
	 * ����<�Ƶ���>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getVoperatorid() {
		return voperatorid;
	}

	/**
	 * 
	 * ����<�Ƶ���>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setVoperatorid(String newVoperatorid) {
		this.voperatorid = newVoperatorid;
	}

	/**
	 * 
	 * ����<�������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getVapprovenote() {
		return vapprovenote;
	}

	/**
	 * 
	 * ����<�������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setVapprovenote(String newVapprovenote) {
		this.vapprovenote = newVapprovenote;
	}

	/**
	 * 
	 * ����<��װ��ַ>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getAzdz() {
		return azdz;
	}

	/**
	 * 
	 * ����<��װ��ַ>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setAzdz(String newAzdz) {
		this.azdz = newAzdz;
	}

	/**
	 * 
	 * ����<���������Ա>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getKcsjry() {
		return kcsjry;
	}

	/**
	 * 
	 * ����<���������Ա>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setKcsjry(String newKcsjry) {
		this.kcsjry = newKcsjry;
	}

	/**
	 * 
	 * ����<�����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getVapproveid() {
		return vapproveid;
	}

	/**
	 * 
	 * ����<�����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setVapproveid(String newVapproveid) {
		this.vapproveid = newVapproveid;
	}

	/**
	 * 
	 * ����<���պϸ��־>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getYshgbz() {
		return yshgbz;
	}

	/**
	 * 
	 * ����<���պϸ��־>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYshgbz(Integer newYshgbz) {
		this.yshgbz = newYshgbz;
	}

	/**
	 * 
	 * ����<ҵ������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getPk_busitype() {
		return pk_busitype;
	}

	/**
	 * 
	 * ����<ҵ������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setPk_busitype(String newPk_busitype) {
		this.pk_busitype = newPk_busitype;
	}

	/**
	 * 
	 * ����<ɾ��>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getDr() {
		return dr;
	}

	/**
	 * 
	 * ����<ɾ��>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setDr(Integer newDr) {
		this.dr = newDr;
	}

	/**
	 * 
	 * ����<����Ա>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getYsy() {
		return ysy;
	}

	/**
	 * 
	 * ����<����Ա>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYsy(String newYsy) {
		this.ysy = newYsy;
	}

	/**
	 * 
	 * ����<������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getSlbh() {
		return slbh;
	}

	/**
	 * 
	 * ����<������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSlbh(String newSlbh) {
		this.slbh = newSlbh;
	}

	/**
	 * 
	 * ����<�����ɹ���Ա>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getKcpgry() {
		return kcpgry;
	}

	/**
	 * 
	 * ����<�����ɹ���Ա>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setKcpgry(String newKcpgry) {
		this.kcpgry = newKcpgry;
	}

	/**
	 * 
	 * ����<ʩ������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getSgts() {
		return sgts;
	}

	/**
	 * 
	 * ����<ʩ������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSgts(Integer newSgts) {
		this.sgts = newSgts;
	}

	/**
	 * 
	 * ����<����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getChandi() {
		return chandi;
	}

	/**
	 * 
	 * ����<����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setChandi(String newChandi) {
		this.chandi = newChandi;
	}

	/**
	 * 
	 * ����<���ݺ�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getVbillcode() {
		return vbillcode;
	}

	/**
	 * 
	 * ����<���ݺ�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setVbillcode(String newVbillcode) {
		this.vbillcode = newVbillcode;
	}

	/**
	 * 
	 * ����<��ϵ��>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getLxr() {
		return lxr;
	}

	/**
	 * 
	 * ����<��ϵ��>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setLxr(String newLxr) {
		this.lxr = newLxr;
	}

	/**
	 * 
	 * ����<�����ɹ�����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getKcpgrq() {
		return kcpgrq;
	}

	/**
	 * 
	 * ����<�����ɹ�����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setKcpgrq(UFDate newKcpgrq) {
		this.kcpgrq = newKcpgrq;
	}

	/**
	 * 
	 * ����<�������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getKcxk() {
		return kcxk;
	}

	/**
	 * 
	 * ����<�������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setKcxk(Integer newKcxk) {
		this.kcxk = newKcxk;
	}

	/**
	 * 
	 * ����<��װ�깤��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getAzwgyqts() {
		return azwgyqts;
	}

	/**
	 * 
	 * ����<��װ�깤��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setAzwgyqts(Integer newAzwgyqts) {
		this.azwgyqts = newAzwgyqts;
	}

	/**
	 * 
	 * ����<�����깤��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getZgwgyqts() {
		return zgwgyqts;
	}

	/**
	 * 
	 * ����<�����깤��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setZgwgyqts(Integer newZgwgyqts) {
		this.zgwgyqts = newZgwgyqts;
	}

	/**
	 * 
	 * ����<���ڿھ�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getSnkj() {
		return snkj;
	}

	/**
	 * 
	 * ����<���ڿھ�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSnkj(String newSnkj) {
		this.snkj = newSnkj;
	}

	/**
	 * 
	 * ����<��Դ����ID>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getVsourcebillid() {
		return vsourcebillid;
	}

	/**
	 * 
	 * ����<��Դ����ID>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setVsourcebillid(String newVsourcebillid) {
		this.vsourcebillid = newVsourcebillid;
	}

	/**
	 * 
	 * ����<�����������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getJsqbrq() {
		return jsqbrq;
	}

	/**
	 * 
	 * ����<�����������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setJsqbrq(UFDate newJsqbrq) {
		this.jsqbrq = newJsqbrq;
	}

	/**
	 * 
	 * ����<�ƽ�����������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getYjhhxrq() {
		return yjhhxrq;
	}

	/**
	 * 
	 * ����<�ƽ�����������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYjhhxrq(UFDate newYjhhxrq) {
		this.yjhhxrq = newYjhhxrq;
	}

	/**
	 * 
	 * ����<�ƽ��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getYjsjrq() {
		return yjsjrq;
	}

	/**
	 * 
	 * ����<�ƽ��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYjsjrq(UFDate newYjsjrq) {
		this.yjsjrq = newYjsjrq;
	}

	/**
	 * 
	 * ����<�����˺�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getYhzh() {
		return yhzh;
	}

	/**
	 * 
	 * ����<�����˺�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYhzh(String newYhzh) {
		this.yhzh = newYhzh;
	}

	/**
	 * 
	 * ����<��ע>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 
	 * ����<��ע>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setRemark(String newRemark) {
		this.remark = newRemark;
	}

	/**
	 * 
	 * ����<�Ƶ�����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getDmakedate() {
		return dmakedate;
	}

	/**
	 * 
	 * ����<�Ƶ�����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setDmakedate(UFDate newDmakedate) {
		this.dmakedate = newDmakedate;
	}

	/**
	 * 
	 * ����<��ϵ��ַ>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getLxdz() {
		return lxdz;
	}

	/**
	 * 
	 * ����<��ϵ��ַ>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setLxdz(String newLxdz) {
		this.lxdz = newLxdz;
	}

	/**
	 * 
	 * ����<�����ж�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDouble getCchd() {
		return cchd;
	}

	/**
	 * 
	 * ����<�����ж�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setCchd(UFDouble newCchd) {
		this.cchd = newCchd;
	}

	/**
	 * 
	 * ����<���ε��ݺ�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getVsourcebillno() {
		return vsourcebillno;
	}

	/**
	 * 
	 * ����<���ε��ݺ�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setVsourcebillno(String newVsourcebillno) {
		this.vsourcebillno = newVsourcebillno;
	}

	/**
	 * 
	 * ����<��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getKcrq() {
		return kcrq;
	}

	/**
	 * 
	 * ����<��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setKcrq(UFDate newKcrq) {
		this.kcrq = newKcrq;
	}

	/**
	 * 
	 * ����<��Ƶ�λ>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getSjdw() {
		return sjdw;
	}

	/**
	 * 
	 * ����<��Ƶ�λ>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSjdw(String newSjdw) {
		this.sjdw = newSjdw;
	}

	/**
	 * 
	 * ����<ԭ�ܾ�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getYgj() {
		return ygj;
	}

	/**
	 * 
	 * ����<ԭ�ܾ�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYgj(String newYgj) {
		this.ygj = newYgj;
	}

	/**
	 * 
	 * ����<��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getJgrq() {
		return jgrq;
	}

	/**
	 * 
	 * ����<��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setJgrq(UFDate newJgrq) {
		this.jgrq = newJgrq;
	}

	/**
	 * 
	 * ����<������Ա>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getKcry() {
		return kcry;
	}

	/**
	 * 
	 * ����<������Ա>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setKcry(String newKcry) {
		this.kcry = newKcry;
	}

	/**
	 * 
	 * ����<����ھ�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getSwkj() {
		return swkj;
	}

	/**
	 * 
	 * ����<����ھ�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSwkj(String newSwkj) {
		this.swkj = newSwkj;
	}

	/**
	 * 
	 * ����<�������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDouble getJzmj() {
		return jzmj;
	}

	/**
	 * 
	 * ����<�������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setJzmj(UFDouble newJzmj) {
		this.jzmj = newJzmj;
	}

	/**
	 * 
	 * ����<���췴������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getKcfkrq() {
		return kcfkrq;
	}

	/**
	 * 
	 * ����<���췴������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setKcfkrq(UFDate newKcfkrq) {
		this.kcfkrq = newKcfkrq;
	}

	/**
	 * 
	 * ����<��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getPk_billtype() {
		return pk_billtype;
	}

	/**
	 * 
	 * ����<��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setPk_billtype(String newPk_billtype) {
		this.pk_billtype = newPk_billtype;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�7>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDouble getReserve7() {
		return reserve7;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�7>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve7(UFDouble newReserve7) {
		this.reserve7 = newReserve7;
	}

	/**
	 * 
	 * ����<�ƽ���װ����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getYjazrq() {
		return yjazrq;
	}

	/**
	 * 
	 * ����<�ƽ���װ����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYjazrq(UFDate newYjazrq) {
		this.yjazrq = newYjazrq;
	}

	/**
	 * 
	 * ����<��ͬ��>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getHth() {
		return hth;
	}

	/**
	 * 
	 * ����<��ͬ��>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setHth(String newHth) {
		this.hth = newHth;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�6>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDouble getReserve6() {
		return reserve6;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�6>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve6(UFDouble newReserve6) {
		this.reserve6 = newReserve6;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�5>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDouble getReserve5() {
		return reserve5;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�5>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve5(UFDouble newReserve5) {
		this.reserve5 = newReserve5;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�4>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve4() {
		return reserve4;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�4>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve4(String newReserve4) {
		this.reserve4 = newReserve4;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�9>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve9() {
		return reserve9;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�9>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve9(String newReserve9) {
		this.reserve9 = newReserve9;
	}

	/**
	 * 
	 * ����<ˮ�����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getSbgs() {
		return sbgs;
	}

	/**
	 * 
	 * ����<ˮ�����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSbgs(Integer newSbgs) {
		this.sbgs = newSbgs;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�8>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDouble getReserve8() {
		return reserve8;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�8>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve8(UFDouble newReserve8) {
		this.reserve8 = newReserve8;
	}

	/**
	 * 
	 * ����<����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getPk_bzsq() {
		return pk_bzsq;
	}

	/**
	 * 
	 * ����<����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setPk_bzsq(String newPk_bzsq) {
		this.pk_bzsq = newPk_bzsq;
	}

	/**
	 * 
	 * ����<��ϵ�绰>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getLxdd() {
		return lxdd;
	}

	/**
	 * 
	 * ����<��ϵ�绰>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setLxdd(String newLxdd) {
		this.lxdd = newLxdd;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�3>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve3() {
		return reserve3;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�3>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve3(String newReserve3) {
		this.reserve3 = newReserve3;
	}

	/**
	 * 
	 * ����<�����깤��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getYswgyqts() {
		return yswgyqts;
	}

	/**
	 * 
	 * ����<�����깤��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYswgyqts(Integer newYswgyqts) {
		this.yswgyqts = newYswgyqts;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�2>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve2() {
		return reserve2;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�2>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve2(String newReserve2) {
		this.reserve2 = newReserve2;
	}

	/**
	 * 
	 * ����<��ˮ����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getYsxz() {
		return ysxz;
	}

	/**
	 * 
	 * ����<��ˮ����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYsxz(String newYsxz) {
		this.ysxz = newYsxz;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�1>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getReserve1() {
		return reserve1;
	}

	/**
	 * 
	 * ����<Ԥ���ֶ�1>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setReserve1(String newReserve1) {
		this.reserve1 = newReserve1;
	}

	/**
	 * 
	 * ����<���Ϸ�������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getZlfkrq() {
		return zlfkrq;
	}

	/**
	 * 
	 * ����<���Ϸ�������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setZlfkrq(UFDate newZlfkrq) {
		this.zlfkrq = newZlfkrq;
	}

	/**
	 * 
	 * ����<�޸���>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getModifier() {
		return modifier;
	}

	/**
	 * 
	 * ����<�޸���>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setModifier(String newModifier) {
		this.modifier = newModifier;
	}

	/**
	 * 
	 * ����<��ˮ����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getYshs() {
		return yshs;
	}

	/**
	 * 
	 * ����<��ˮ����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYshs(Integer newYshs) {
		this.yshs = newYshs;
	}

	/**
	 * 
	 * ����<�ƽ���������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getYjzgrq() {
		return yjzgrq;
	}

	/**
	 * 
	 * ����<�ƽ���������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYjzgrq(UFDate newYjzgrq) {
		this.yjzgrq = newYjzgrq;
	}

	/**
	 * 
	 * ����<����ϵ��ʽ>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getZlxfs() {
		return zlxfs;
	}

	/**
	 * 
	 * ����<����ϵ��ʽ>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setZlxfs(String newZlxfs) {
		this.zlxfs = newZlxfs;
	}

	/**
	 * 
	 * ����<�����ж�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDouble getYsxd() {
		return ysxd;
	}

	/**
	 * 
	 * ����<�����ж�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYsxd(UFDouble newYsxd) {
		this.ysxd = newYsxd;
	}

	/**
	 * 
	 * ����<ҵ������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getDbilldate() {
		return dbilldate;
	}

	/**
	 * 
	 * ����<ҵ������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setDbilldate(UFDate newDbilldate) {
		this.dbilldate = newDbilldate;
	}

	/**
	 * 
	 * ����<���ʽ>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getFkfs() {
		return fkfs;
	}

	/**
	 * 
	 * ����<���ʽ>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setFkfs(String newFkfs) {
		this.fkfs = newFkfs;
	}

	/**
	 * 
	 * ����<��װ���>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getBzbh() {
		return bzbh;
	}

	/**
	 * 
	 * ����<��װ���>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setBzbh(String newBzbh) {
		this.bzbh = newBzbh;
	}

	/**
	 * 
	 * ����<��߲���>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getZgcs() {
		return zgcs;
	}

	/**
	 * 
	 * ����<��߲���>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setZgcs(Integer newZgcs) {
		this.zgcs = newZgcs;
	}

	/**
	 * 
	 * ����<���>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getBiaobie() {
		return biaobie;
	}

	/**
	 * 
	 * ����<���>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setBiaobie(String newBiaobie) {
		this.biaobie = newBiaobie;
	}

	/**
	 * 
	 * ����<������Ա>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getYsry() {
		return ysry;
	}

	/**
	 * 
	 * ����<������Ա>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYsry(String newYsry) {
		this.ysry = newYsry;
	}

	/**
	 * 
	 * ����<��Ԥ�������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getJyskqs() {
		return jyskqs;
	}

	/**
	 * 
	 * ����<��Ԥ�������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setJyskqs(Integer newJyskqs) {
		this.jyskqs = newJyskqs;
	}

	/**
	 * 
	 * ����<����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getHm() {
		return hm;
	}

	/**
	 * 
	 * ����<����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setHm(String newHm) {
		this.hm = newHm;
	}

	/**
	 * 
	 * ����<��װ״̬>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getBzzt() {
		return bzzt;
	}

	/**
	 * 
	 * ����<��װ״̬>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setBzzt(Integer newBzzt) {
		this.bzzt = newBzzt;
	}

	/**
	 * 
	 * ����<�޸�����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getDmodify() {
		return dmodify;
	}

	/**
	 * 
	 * ����<�޸�����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setDmodify(UFDate newDmodify) {
		this.dmodify = newDmodify;
	}

	/**
	 * 
	 * ����<��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getKhyh() {
		return khyh;
	}

	/**
	 * 
	 * ����<��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setKhyh(String newKhyh) {
		this.khyh = newKhyh;
	}

	/**
	 * 
	 * ����<��װ����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getBzrq() {
		return bzrq;
	}

	/**
	 * 
	 * ����<��װ����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setBzrq(UFDate newBzrq) {
		this.bzrq = newBzrq;
	}

	/**
	 * 
	 * ����<������Ա>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getJbry() {
		return jbry;
	}

	/**
	 * 
	 * ����<������Ա>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setJbry(String newJbry) {
		this.jbry = newJbry;
	}

	/**
	 * 
	 * ����<��˾ID>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getPk_corp() {
		return pk_corp;
	}

	/**
	 * 
	 * ����<��˾ID>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setPk_corp(String newPk_corp) {
		this.pk_corp = newPk_corp;
	}

	/**
	 * 
	 * ����<��������������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getSjpswcrq() {
		return sjpswcrq;
	}

	/**
	 * 
	 * ����<��������������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSjpswcrq(UFDate newSjpswcrq) {
		this.sjpswcrq = newSjpswcrq;
	}

	/**
	 * 
	 * ����<����깤��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getSjwgyqts() {
		return sjwgyqts;
	}

	/**
	 * 
	 * ����<����깤��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSjwgyqts(Integer newSjwgyqts) {
		this.sjwgyqts = newSjwgyqts;
	}

	/**
	 * 
	 * ����<��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getJmlx() {
		return jmlx;
	}

	/**
	 * 
	 * ����<��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setJmlx(String newJmlx) {
		this.jmlx = newJmlx;
	}

	/**
	 * 
	 * ����<����ʩ����λ>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getSnsgdw() {
		return snsgdw;
	}

	/**
	 * 
	 * ����<����ʩ����λ>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSnsgdw(String newSnsgdw) {
		this.snsgdw = newSnsgdw;
	}

	/**
	 * 
	 * ����<��װ��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getAzfkrq() {
		return azfkrq;
	}

	/**
	 * 
	 * ����<��װ��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setAzfkrq(UFDate newAzfkrq) {
		this.azfkrq = newAzfkrq;
	}

	/**
	 * 
	 * ����<��Ŀ��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getPk_jobbasfil() {
		return pk_jobbasfil;
	}

	/**
	 * 
	 * ����<��Ŀ��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setPk_jobbasfil(String newPk_jobbasfil) {
		this.pk_jobbasfil = newPk_jobbasfil;
	}

	/**
	 * 
	 * ����<��Դ��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getVsourcebilltype() {
		return vsourcebilltype;
	}

	/**
	 * 
	 * ����<��Դ��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setVsourcebilltype(String newVsourcebilltype) {
		this.vsourcebilltype = newVsourcebilltype;
	}

	/**
	 * 
	 * ����<�ƽ������������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getYjsjpsrq() {
		return yjsjpsrq;
	}

	/**
	 * 
	 * ����<�ƽ������������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYjsjpsrq(UFDate newYjsjpsrq) {
		this.yjsjpsrq = newYjsjpsrq;
	}

	/**
	 * 
	 * ����<��λ>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getBiaowei() {
		return biaowei;
	}

	/**
	 * 
	 * ����<��λ>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setBiaowei(String newBiaowei) {
		this.biaowei = newBiaowei;
	}

	/**
	 * 
	 * ����<��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getZgrq() {
		return zgrq;
	}

	/**
	 * 
	 * ����<��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setZgrq(UFDate newZgrq) {
		this.zgrq = newZgrq;
	}

	/**
	 * 
	 * ����<Э����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getXybh() {
		return xybh;
	}

	/**
	 * 
	 * ����<Э����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setXybh(String newXybh) {
		this.xybh = newXybh;
	}

	/**
	 * 
	 * ����<���ĺϸ��־>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getZghgbz() {
		return zghgbz;
	}

	/**
	 * 
	 * ����<���ĺϸ��־>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setZghgbz(Integer newZghgbz) {
		this.zghgbz = newZghgbz;
	}

	/**
	 * 
	 * ����<����״̬>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getVbillstatus() {
		return vbillstatus;
	}

	/**
	 * 
	 * ����<����״̬>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setVbillstatus(Integer newVbillstatus) {
		this.vbillstatus = newVbillstatus;
	}

	/**
	 * 
	 * ����<���ˮ��>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getZdsl() {
		return zdsl;
	}

	/**
	 * 
	 * ����<���ˮ��>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setZdsl(Integer newZdsl) {
		this.zdsl = newZdsl;
	}

	/**
	 * 
	 * ����<���ڹܾ�>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public String getSngj() {
		return sngj;
	}

	/**
	 * 
	 * ����<���ڹܾ�>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setSngj(String newSngj) {
		this.sngj = newSngj;
	}

	/**
	 * 
	 * ����<�������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getDapprovedate() {
		return dapprovedate;
	}

	/**
	 * 
	 * ����<�������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setDapprovedate(UFDate newDapprovedate) {
		this.dapprovedate = newDapprovedate;
	}

	/**
	 * 
	 * ����<��ˮ����>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public Integer getYsrs() {
		return ysrs;
	}

	/**
	 * 
	 * ����<��ˮ����>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYsrs(Integer newYsrs) {
		this.ysrs = newYsrs;
	}

	/**
	 * 
	 * ����<��������>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDate getYsrq() {
		return ysrq;
	}

	/**
	 * 
	 * ����<��������>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setYsrq(UFDate newYsrq) {
		this.ysrq = newYsrq;
	}

	/**
	 * 
	 * ����<ʱ���>��Getter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public UFDateTime getTs() {
		return ts;
	}

	/**
	 * 
	 * ����<ʱ���>��Setter���� <b>����ʱ�� 2014-08-04 14:34:44</b>
	 * 
	 */
	public void setTs(UFDateTime newTs) {
		this.ts = newTs;
	}

	public UFBoolean getSfsyx() {
		return sfsyx;
	}

	public void setSfsyx(UFBoolean sfsyx) {
		this.sfsyx = sfsyx;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getYyzzh() {
		return yyzzh;
	}

	public void setYyzzh(String yyzzh) {
		this.yyzzh = yyzzh;
	}

	public String getYyqy() {
		return yyqy;
	}

	public void setYyqy(String yyqy) {
		this.yyqy = yyqy;
	}

	public String getSfgz() {
		return sfgz;
	}

	public void setSfgz(String sfgz) {
		this.sfgz = sfgz;
	}

	public String getXmda() {
		return xmda;
	}

	public void setXmda(String xmda) {
		this.xmda = xmda;
	}

	public String getBiaojing() {
		return biaojing;
	}

	public void setBiaojing(String biaojing) {
		this.biaojing = biaojing;
	}

	/**
	 * ȡ�ø�VO�����ֶ�
	 * 
	 * <b>����ʱ�� 2014-08-04 14:34:44</b>
	 */
	public java.lang.String getParentPKFieldName() {
		return null;
	}

	/**
	 * ȡ�ñ�����
	 * 
	 * <b>����ʱ�� 2014-08-04 14:34:44</b>
	 */
	public java.lang.String getPKFieldName() {
		return "pk_bzsq";
	}

	/**
	 * ���ر�����
	 * 
	 * <b>����ʱ�� 2014-08-04 14:34:44</b>
	 */
	public java.lang.String getTableName() {
		return "lcsw_bzsq";
	}

	/**
	 * ����Ĭ�Ϸ�ʽ����������
	 * 
	 * <b>����ʱ�� 2014-08-04 14:34:44</b>
	 */
	public BzsqVO() {
		super();
	}

	public void clone(BzsqVO distictGc) {
		// TODO Auto-generated method stub
		
	}
}
