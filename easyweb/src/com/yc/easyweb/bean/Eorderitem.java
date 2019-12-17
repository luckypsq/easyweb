package com.yc.easyweb.bean;

import java.io.Serializable;

public class Eorderitem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ���������
	private String itemid;// ��������id
	private int count;// ����
	private long bid;// ��id
	private String eoid;// ������
	private double total;// �ܼ�
	private String eitemp;// Ԥ���ֶ�
	private long uid;// �û���
	private int cartstate;// ״̬
	private String carttime;// ����ʱ��

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
				+ total + ", eitemp=" + eitemp + ", uid=" + uid + ", cartstate=" + cartstate + ", carttime=" + carttime
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bid ^ (bid >>> 32));
		result = prime * result + ((itemid == null) ? 0 : itemid.hashCode());
		result = prime * result + (int) (uid ^ (uid >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eorderitem other = (Eorderitem) obj;
		if (bid != other.bid)
			return false;
		if (itemid == null) {
			if (other.itemid != null)
				return false;
		} else if (!itemid.equals(other.itemid))
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}

}
