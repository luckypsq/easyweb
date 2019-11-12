package com.yc.easyweb.bean;

public class Eorder {
	private String eoid;//订单id
	private long uid;//用户id
	private int eostate;//状态1.待付款2.已发货3.退款申请中4.退款成功5，5.订单取消
	private String eotime;//下单时间
	private String eotemp;//备用字段
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
	@Override
	public String toString() {
		return "Eorder [eoid=" + eoid + ", uid=" + uid + ", eostate=" + eostate + ", eotime=" + eotime + ", eotemp="
				+ eotemp + ", uname=" + uname + ", eoaddr=" + eoaddr + ", eotype=" + eotype + "]";
	}
	
}
