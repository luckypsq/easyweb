package com.yc.easyweb.bean;

public class BookType {
	private long btid;//类别id
	private String btname;//类别名一
	private String btnamesecond;//类别名二
	private String btnamethird;//类别名三
	private String bttemp;// 备用字段
	public long getBtid() {
		return btid;
	}
	public void setBtid(long btid) {
		this.btid = btid;
	}
	public String getBtname() {
		return btname;
	}
	public void setBtname(String btname) {
		this.btname = btname;
	}
	public String getBtnamesecond() {
		return btnamesecond;
	}
	public void setBtnamesecond(String btnamesecond) {
		this.btnamesecond = btnamesecond;
	}
	public String getBtnamethird() {
		return btnamethird;
	}
	public void setBtnamethird(String btnamethird) {
		this.btnamethird = btnamethird;
	}
	public String getBttemp() {
		return bttemp;
	}
	public void setBttemp(String bttemp) {
		this.bttemp = bttemp;
	}
}
