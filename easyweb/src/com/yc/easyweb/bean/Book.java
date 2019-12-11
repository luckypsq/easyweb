package com.yc.easyweb.bean;

import java.io.Serializable;

public class Book implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public double getBprice() {
		return bprice;
	}
	public void setBprice(double bprice) {
		this.bprice = bprice;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public Long getBnum() {
		return bnum;
	}
	public void setBnum(Long bnum) {
		this.bnum = bnum;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	
	public String getBtemp() {
		return btemp;
	}
	public void setBtemp(String btemp) {
		this.btemp = btemp;
	}
	public int getBstate() {
		return bstate;
	}
	public void setBstate(int bstate) {
		this.bstate = bstate;
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public String getBuniversity() {
		return buniversity;
	}
	public void setBuniversity(String buniversity) {
		this.buniversity = buniversity;
	}
	public String getBucollege() {
		return bucollege;
	}
	public void setBucollege(String bucollege) {
		this.bucollege = bucollege;
	}
	public String getBumajor() {
		return bumajor;
	}
	public void setBumajor(String bumajor) {
		this.bumajor = bumajor;
	}
	public String getBclass() {
		return bclass;
	}
	public void setBclass(String bclass) {
		this.bclass = bclass;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	
	public Long getBtid() {
		return btid;
	}
	public void setBtid(Long btid) {
		this.btid = btid;
	}
	public String getBtemp1() {
		return btemp1;
	}
	public void setBtemp1(String btemp1) {
		this.btemp1 = btemp1;
	}
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", buniversity=" + buniversity + ", bucollege=" + bucollege
				+ ", bumajor=" + bumajor + ", bclass=" + bclass + ", bcontent=" + bcontent + ", bimg=" + bimg
				+ ", bprice=" + bprice + ", bstate=" + bstate + ", btid=" + btid + ", btemp=" + btemp + ", btemp1="
				+ btemp1 + ", bnum=" + bnum + ", bauthor=" + bauthor + ", bdate=" + bdate + ", uid=" + uid
				+ ", getUid()=" + getUid() + ", getBprice()=" + getBprice() + ", getBdate()=" + getBdate()
				+ ", getBauthor()=" + getBauthor() + ", getBnum()=" + getBnum() + ", getBname()=" + getBname()
				+ ", getBtemp()=" + getBtemp() + ", getBstate()=" + getBstate() + ", getBid()=" + getBid()
				+ ", getBuniversity()=" + getBuniversity() + ", getBucollege()=" + getBucollege() + ", getBumajor()="
				+ getBumajor() + ", getBclass()=" + getBclass() + ", getBcontent()=" + getBcontent() + ", getBimg()="
				+ getBimg() + ", getBtid()=" + getBtid() + ", getBtemp1()=" + getBtemp1() +
				"]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bauthor == null) ? 0 : bauthor.hashCode());
		result = prime * result + ((bname == null) ? 0 : bname.hashCode());
		result = prime * result + (int) (uid ^ (uid >>> 32));
		return result;
	}
	
	//��дhashCode ��equals���� �ж��鼮�Ƿ���ͬ
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
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
		if (uid != other.uid)
			return false;
		return true;
	}
	
	
	
	
	
}
