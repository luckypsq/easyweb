package com.yc.easyweb.bean;

import java.io.Serializable;

public class Cart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//购物车
	private long cid;//购物车id
	private String eoid;//订单号
	private String ctemp;//备用字段
	private long uid;//用户id
	
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public String getEoid() {
		return eoid;
	}
	public void setEoid(String eoid) {
		this.eoid = eoid;
	}
	public long getUid() {
		return uid;
	}
	public String getCtemp() {
		return ctemp;
	}
	public void setCtemp(String ctemp) {
		this.ctemp = ctemp;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Cart [cid=" + cid + ", eoid=" + eoid + ", ctemp=" + ctemp + ", uid=" + uid + "]";
	}
	
}
