package com.yc.easyweb.bean;

public class Eorder {
	private String eoid;//����id
	private long uid;//�û�id
	private int eostate;//״̬1.������2.������3.�ѷ���4.�˻�������5.�˿�ɹ�6.����ȡ��7.�ѽ���
	private String eotime;//�µ�ʱ��
	private String eotemp;//�����ֶ�
	private String uname;//�û���
	private String eoaddr;//��ַ
	private String eotype;//���ͷ�ʽ(1.�ͻ�����2.��ȡ)
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEoaddr() {
		return eoaddr;
	}
	public void setEoaddr(String eoaddr) {
		this.eoaddr = eoaddr;
	}
	public String getEotype() {
		return eotype;
	}
	public void setEotype(String eotype) {
		this.eotype = eotype;
	}
	public String getEoid() {
		return eoid;
	}
	public void setEoid(String eoid) {
		this.eoid = eoid;
	}
	public String getEotime() {
		return eotime;
	}
	public void setEotime(String eotime) {
		this.eotime = eotime;
	}
	public String getEotemp() {
		return eotemp;
	}
	public void setEotemp(String eotemp) {
		this.eotemp = eotemp;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public int getEostate() {
		return eostate;
	}
	public void setEostate(int eostate) {
		this.eostate = eostate;
	}
	@Override
	public String toString() {
		return "Eorder [eoid=" + eoid + ", uid=" + uid + ", eostate=" + eostate + ", eotime=" + eotime + ", eotemp="
				+ eotemp + ", uname=" + uname + ", eoaddr=" + eoaddr + ", eotype=" + eotype + "]";
	}
	
}
