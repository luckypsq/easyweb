package com.yc.easyweb.bean;


public class Notice {
	private long nid;//����id
	private String ntime;//����ʱ��
	private String nnumber;//�����
	private String nauthor;//������
	private String ncontent;//����
	private String ntemp;//�����ֶ�
	private int nstate;//״̬
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
