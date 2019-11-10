package com.yc.easyweb.bean;

public class Eorder {
	private String eoid;//订单id
	private String eotime;//下单时间
	private String eotemp;//备用字段
	private long uid;//用户id
	private int eostate;//状态
	private String uname;//用户名
	private String eoaddr;//地址
	private String eotype;//配送方式
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEoaddr() {
		return eoaddr;
	}
	public void setEoaddr(String eoaddr) {
		this.eoaddr = eoaddr;
	}
	public String getEotype() {
		return eotype;
	}
	public void setEotype(String eotype) {
		this.eotype = eotype;
	}
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
