package com.yc.easyweb.bean;

import java.io.Serializable;

public class BookType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long btid;//���id
	private String btname;//�����һ
	private String btnamesecond;//�������
	private String btnamethird;//�������
	private String bttemp;// �����ֶ�
	private int btstate;//״̬(1.����ʹ��2.δʹ��3.��ɾ��)
	public int getBtstate() {
		return btstate;
	}
	public void setBtstate(int btstate) {
		this.btstate = btstate;
	}
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
	@Override
	public String toString() {
		return "BookType [btid=" + btid + ", btname=" + btname + ", btnamesecond=" + btnamesecond + ", btnamethird="
				+ btnamethird + ", bttemp=" + bttemp + "]";
	}
	
}
