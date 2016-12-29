package nc.vo.lcsw.pub;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;

public class LcswCYVO extends SuperVO {
	//
	private static final long serialVersionUID = 1L;
	

	private String pk_cyfk;
	public String getPk_cyfk() {
		return pk_cyfk;
	}

	public void setPk_cyfk(String pk_cyfk) {
		this.pk_cyfk = pk_cyfk;
	}

	private UFDate cbrq;
	private String cby;
	private UFDate lhrq;
	private String bch;
	private String rhbh;
	private Integer dr;
	private UFDateTime ts;
	
	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public UFDateTime getTs() {
		return ts;
	}

	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}


	
	public String getRhbh() {
		return rhbh;
	}

	public void setRhbh(String rhbh) {
		this.rhbh = rhbh;
	}

	public UFDate getCbrq() {
		return cbrq;
	}

	public void setCbrq(UFDate cbrq) {
		this.cbrq = cbrq;
	}

	public String getCby() {
		return cby;
	}

	public void setCby(String cby) {
		this.cby = cby;
	}

	public UFDate getLhrq() {
		return lhrq;
	}

	public void setLhrq(UFDate lhrq) {
		this.lhrq = lhrq;
	}

	public String getBch() {
		return bch;
	}

	public void setBch(String bch) {
		this.bch = bch;
	}

	

	@Override
	public String getParentPKFieldName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPKFieldName() {
		// TODO Auto-generated method stub
		return "pk_cyfk";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "LCSW_CYFK";
	}
}
