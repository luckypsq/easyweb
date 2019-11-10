package com.yc.easyweb.bean;


public class Notice {
	private long nid;//公告id
	private String ntime;//发布时间
	private String nnumber;//浏览量
	private String nauthor;//发布者
	private String ncontent;//内容
	private String ntemp;//备用字段
	private int nstate;//状态
	public long getNid() {
		return nid;
	}
	public void setNid(long nid) {
		this.nid = nid;
	}
	public String getNtime() {
		return ntime;
	}
	public void setNtime(String ntime) {
		this.ntime = ntime;
	}
	public String getNnumber() {
		return nnumber;
	}
	public void setNnumber(String nnumber) {
		this.nnumber = nnumber;
	}
	public String getNauthor() {
		return nauthor;
	}
	public void setNauthor(String nauthor) {
		this.nauthor = nauthor;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNtemp() {
		return ntemp;
	}
	public void setNtemp(String ntemp) {
		this.ntemp = ntemp;
	}
	public int getNstate() {
		return nstate;
	}
	public void setNstate(int nstate) {
		this.nstate = nstate;
	}
}
