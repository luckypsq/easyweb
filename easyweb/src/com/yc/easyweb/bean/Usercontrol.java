package com.yc.easyweb.bean;

public class Usercontrol {
	private long ucon;
	private long uid;
	private long conid;
	public long getUcon() {
		return ucon;
	}
	public void setUcon(long ucon) {
		this.ucon = ucon;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getConid() {
		return conid;
	}
	public void setConid(long conid) {
		this.conid = conid;
	}
	
	@Override
	public String toString() {
		return "Usercontrol [ucon=" + ucon + ", uid=" + uid + ", conid=" + conid + "]";
	}
	
}
