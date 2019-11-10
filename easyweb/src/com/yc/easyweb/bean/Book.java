package com.yc.easyweb.bean;

public class Book {
	private long bid;//id
	private String buniversity;//书籍分类学校
	private String bucollege;//书籍分类学院
	private String bumajor;//书籍分类专业
	private String bclass;//书籍分类年级
	
	private String bcontent;//描述
	private String bimg;//图片
	private String bprice;//价格
	private String bstate;//状态
	private long btid;//类别
	
	private String btemp;//所属系列
	
	public String getBtemp() {
		return btemp;
	}
	public void setBtemp(String btemp) {
		this.btemp = btemp;
	}
	public String getBprice() {
		return bprice;
	}
	public void setBprice(String bprice) {
		this.bprice = bprice;
	}
	public String getBstate() {
		return bstate;
	}
	public void setBstate(String bstate) {
		this.bstate = bstate;
	}
	public long getBtid() {
		return btid;
	}
	public void setBtid(long btid) {
		this.btid = btid;
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public String getBuniversity() {
		return buniversity;
	}
	public void setBuniversity(String buniversity) {
		this.buniversity = buniversity;
	}
	public String getBucollege() {
		return bucollege;
	}
	public void setBucollege(String bucollege) {
		this.bucollege = bucollege;
	}
	public String getBumajor() {
		return bumajor;
	}
	public void setBumajor(String bumajor) {
		this.bumajor = bumajor;
	}
	public String getBclass() {
		return bclass;
	}
	public void setBclass(String bclass) {
		this.bclass = bclass;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	
}
