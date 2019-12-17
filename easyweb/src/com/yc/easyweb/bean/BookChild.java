package com.yc.easyweb.bean;

import java.io.Serializable;

public class BookChild extends Book implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String btname;//����
	private String btnamesecond;
	private String btnamethird;
	private long bid;//id
	private String bname;//����
	private String buniversity;//�鼮����ѧУ
	private String bucollege;//�鼮����ѧԺ
	private String bumajor;//�鼮����רҵ
	private String bclass;//�鼮�����꼶
	
	private String bcontent;//����
	private String bimg;//ͼƬ
	private double bprice;//�۸�
	private int bstate;//״̬(1���ϼܣ�2.���¼�3.����4.��˲�ͨ��5.δ���6.��ɾ��)
	private Long btid;//���
	private String btemp;//����ϵ��
	private String btemp1;//Ԥ���ֶ�
	private Long bnum;//���
	private String bauthor ;//���߻������
	private String bdate;//�ϴ�ʱ��
	private long uid;//�ϴ���
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
	public String getBtname() {
		return btname;
	}
	public void setBtname(String btname) {
		this.btname = btname;
	}
	//tosing����
	@Override
	public String toString() {
		return "BookChild [btname=" + btname + ", btnamesecond=" + btnamesecond + ", btnamethird=" + btnamethird
				+ ", bid=" + bid + ", bname=" + bname + ", buniversity=" + buniversity + ", bucollege=" + bucollege
				+ ", bumajor=" + bumajor + ", bclass=" + bclass + ", bcontent=" + bcontent + ", bimg=" + bimg
				+ ", bprice=" + bprice + ", bstate=" + bstate + ", btid=" + btid + ", btemp=" + btemp + ", btemp1="
				+ btemp1 + ", bnum=" + bnum + ", bauthor=" + bauthor + ", bdate=" + bdate + ", uid=" + uid + "]";
	}
	//��дhashCode��equals����
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bauthor == null) ? 0 : bauthor.hashCode());
		result = prime * result + ((bname == null) ? 0 : bname.hashCode());
		result = prime * result + ((btemp == null) ? 0 : btemp.hashCode());
		result = prime * result + ((btnamesecond == null) ? 0 : btnamesecond.hashCode());
		result = prime * result + ((btnamethird == null) ? 0 : btnamethird.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookChild other = (BookChild) obj;
		if (bauthor == null) {
			if (other.bauthor != null)
				return false;
		} else if (!bauthor.equals(other.bauthor))
			return false;
		if (bname == null) {
			if (other.bname != null)
				return false;
		} else if (!bname.equals(other.bname))
			return false;
		if (btemp == null) {
			if (other.btemp != null)
				return false;
		} else if (!btemp.equals(other.btemp))
			return false;
		if (btnamesecond == null) {
			if (other.btnamesecond != null)
				return false;
		} else if (!btnamesecond.equals(other.btnamesecond))
			return false;
		if (btnamethird == null) {
			if (other.btnamethird != null)
				return false;
		} else if (!btnamethird.equals(other.btnamethird))
			return false;
		return true;
	}
	
	
}
