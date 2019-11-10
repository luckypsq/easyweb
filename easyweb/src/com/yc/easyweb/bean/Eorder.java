package com.yc.easyweb.bean;

public class Eorder {
	private String eoid;//订单id
	private String eotime;//下单时间
	private String eotemp;//备用字段
	private long bid;//书籍id
	private long uid;//用户id
	private int eostate;//状态
	public String getEoid() {
		return eoid;
	}
	public void setEoid(String eoid) {
		this.eoid = eoid;
	}
	public String getEotime() {
		return eotime;
	}
	public void setEotime(String eotime) {
		this.eotime = eotime;
	}
	public String getEotemp() {
		return eotemp;
	}
	public void setEotemp(String eotemp) {
		this.eotemp = eotemp;
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public int getEostate() {
		return eostate;
	}
	public void setEostate(int eostate) {
		this.eostate = eostate;
	}
	
}
