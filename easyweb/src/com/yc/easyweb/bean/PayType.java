package com.yc.easyweb.bean;

public class PayType {
	private long eopaytypeid;//id
	private String eopayname;//Ö§¸¶Ãû×Ö
	private int eopaystate;//×´Ì¬
	private String eopaytemp;
	public long getEopaytypeid() {
		return eopaytypeid;
	}
	public void setEopaytypeid(long eopaytypeid) {
		this.eopaytypeid = eopaytypeid;
	}
	public String getEopayname() {
		return eopayname;
	}
	public void setEopayname(String eopayname) {
		this.eopayname = eopayname;
	}
	public int getEopaystate() {
		return eopaystate;
	}
	public void setEopaystate(int eopaystate) {
		this.eopaystate = eopaystate;
	}
	public String getEopaytemp() {
		return eopaytemp;
	}
	public void setEopaytemp(String eopaytemp) {
		this.eopaytemp = eopaytemp;
	}
	@Override
	public String toString() {
		return "PayType [eopaytypeid=" + eopaytypeid + ", eopayname=" + eopayname + ", eopaystate=" + eopaystate
				+ ", eopaytemp=" + eopaytemp + "]";
	}
	
}
