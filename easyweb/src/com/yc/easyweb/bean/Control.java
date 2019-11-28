package com.yc.easyweb.bean;

public class Control {
	private long conid;
	private String coname;
	private String conamesecond;
	private int conadd;
	private int condelete;
	private int coninsert;
	private int conupdate;
	private int concheck;
	private long uid;
	private int constate;
	private String contemp;
	public long getConid() {
		return conid;
	}
	public void setConid(long conid) {
		this.conid = conid;
	}
	public String getConame() {
		return coname;
	}
	public void setConame(String coname) {
		this.coname = coname;
	}
	public String getConamesecond() {
		return conamesecond;
	}
	public void setConamesecond(String conamesecond) {
		this.conamesecond = conamesecond;
	}
	public int getConadd() {
		return conadd;
	}
	public void setConadd(int conadd) {
		this.conadd = conadd;
	}
	public int getCondelete() {
		return condelete;
	}
	public void setCondelete(int condelete) {
		this.condelete = condelete;
	}
	public int getConinsert() {
		return coninsert;
	}
	public void setConinsert(int coninsert) {
		this.coninsert = coninsert;
	}
	public int getConupdate() {
		return conupdate;
	}
	public void setConupdate(int conupdate) {
		this.conupdate = conupdate;
	}
	public int getConcheck() {
		return concheck;
	}
	public void setConcheck(int concheck) {
		this.concheck = concheck;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public int getConstate() {
		return constate;
	}
	public void setConstate(int constate) {
		this.constate = constate;
	}
	public String getContemp() {
		return contemp;
	}
	public void setContemp(String contemp) {
		this.contemp = contemp;
	}
	@Override
	public String toString() {
		return "Control [conid=" + conid + ", coname=" + coname + ", conamesecond=" + conamesecond + ", conadd="
				+ conadd + ", condelete=" + condelete + ", coninsert=" + coninsert + ", conupdate=" + conupdate
				+ ", concheck=" + concheck + ", uid=" + uid + ", constate=" + constate + ", contemp=" + contemp + "]";
	}
	
}
