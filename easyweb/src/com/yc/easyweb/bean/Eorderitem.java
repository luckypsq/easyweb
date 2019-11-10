package com.yc.easyweb.bean;

public class Eorderitem {
	//订单详情表
	private String itemid;//订单详情id
	private int count;//数量
	private long bid;//书id
	private String eiod;//订单号
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public String getEiod() {
		return eiod;
	}
	public void setEiod(String eiod) {
		this.eiod = eiod;
	}
}
