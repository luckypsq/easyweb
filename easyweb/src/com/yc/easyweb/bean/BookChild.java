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
	@Override
	public String toString() {
		return "BookChild [btname=" + btname + ", btnamesecond=" + btnamesecond + ", btnamethird=" + btnamethird
				+ ", bid=" + bid + ", bname=" + bname + ", buniversity=" + buniversity + ", bucollege=" + bucollege
				+ ", bumajor=" + bumajor + ", bclass=" + bclass + ", bcontent=" + bcontent + ", bimg=" + bimg
				+ ", bprice=" + bprice + ", bstate=" + bstate + ", btid=" + btid + ", btemp=" + btemp + ", btemp1="
				+ btemp1 + ", bnum=" + bnum + ", bauthor=" + bauthor + ", bdate=" + bdate + ", uid=" + uid + "]";
	}
	
}
