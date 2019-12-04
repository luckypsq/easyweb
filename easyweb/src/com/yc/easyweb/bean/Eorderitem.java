package com.yc.easyweb.bean;

public class Eorderitem {
	// 订单详情表
	private String itemid;// 订单详情id
	private int count;// 数量
	private long bid;// 书id
	private String eoid;// 订单号
	private double total;// 总价
	private String eitemp;// 预备字段
	private long uid;// 用户名
	private int cartstate;// 状态
	private String carttime;
	
	

	public String getCarttime() {
		return carttime;
	}

	public void setCarttime(String carttime) {
		this.carttime = carttime;
	}

	public int getCartstate() {
		return cartstate;
	}

	public void setCartstate(int cartstate) {
		this.cartstate = cartstate;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

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

	public String getEoid() {
		return eoid;
	}

	public void setEoid(String eiod) {
		this.eoid = eiod;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getEitemp() {
		return eitemp;
	}

	public void setEitemp(String eitemp) {
		this.eitemp = eitemp;
	}

	@Override
	public String toString() {
		return "Eorderitem [itemid=" + itemid + ", count=" + count + ", bid=" + bid + ", eoid=" + eoid + ", total="
				+ total + ", eitemp=" + eitemp + "]";
	}

}
