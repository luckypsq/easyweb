package com.yc.easyweb.bean;

import java.io.Serializable;

public class Eorder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String eoid;//����id
	private long uid;//�û�id
	private int eostate;//״̬1.������2.������3.�ѷ���4.�˻�������5.�˿�ɹ�6.�ѽ���7.�˻�ʧ��
	private String eotime;//�µ�ʱ��
	private String eotemp;//�����ֶ�
	private String uname;//�û���
	private String eoaddr;//��ַ
	private String eotype;//���ͷ�ʽ(����֧�� ���������� �����ȡ)
	private String eoespress;//�������
	private long eopaytypeid;//֧������
	public String getEoespress() {
		return eoespress;
	}
	public void setEoespress(String eoespress) {
		this.eoespress = eoespress;
	}
	public long getEopaytypeid() {
		return eopaytypeid;
	}
	public void setEopaytypeid(long eopaytypeid) {
		this.eopaytypeid = eopaytypeid;
	}
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
				+ eotemp + ", uname=" + uname + ", eoaddr=" + eoaddr + ", eotype=" + eotype + ", eoespress=" + eoespress
				+ ", eopaytypeid=" + eopaytypeid + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eoaddr == null) ? 0 : eoaddr.hashCode());
		result = prime * result + ((eoid == null) ? 0 : eoid.hashCode());
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
		Eorder other = (Eorder) obj;
		if (eoaddr == null) {
			if (other.eoaddr != null)
				return false;
		} else if (!eoaddr.equals(other.eoaddr))
			return false;
		if (eoid == null) {
			if (other.eoid != null)
				return false;
		} else if (!eoid.equals(other.eoid))
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}
	
}
