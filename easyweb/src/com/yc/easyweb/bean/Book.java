package com.yc.easyweb.bean;


public class Book {
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
	private int btid;//���
	private String btemp;//����ϵ��
	private String btemp1;//Ԥ���ֶ�
	private Long bnum;//���
	private String bauthor ;//���߻������
	private String bdate;//�ϴ�ʱ��
	
	
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
	public int getBtid() {
		return btid;
	}
	public void setBtid(int btid) {
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
				+ btemp1 + "]";
	}
	
	
}
