package com.yc.easyweb.bean;

public class User {
	private int uid;//�û�id
	private String uname;//�û���
	private String university;//�û����ڴ�ѧ
	private String ucollege;//�û�����ѧԺ
	private String umajor;//�û�����רҵ
	private String upassword;//�û�����
	private int ustate;//�û�״̬
	private String uphone;//�û��绰
	
	private int utype;//����(�û�����Ա����ʯ��Ա����ͨ����Ա����������Ա)
	private String uemail;//����
	private String utime;//ע��ʱ��
	private int usex;//�Ա�(1.��2.Ů)
	private int uage;//����
	public int getUtype() {
		return utype;
	}
	public void setUtype(int utype) {
		this.utype = utype;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUtime() {
		return utime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
	public int getUsex() {
		return usex;
	}
	public void setUsex(int usex) {
		this.usex = usex;
	}
	public int getUage() {
		return uage;
	}
	public void setUage(int uage) {
		this.uage = uage;
	}
	private String utemp;//�����ֶ�
	
	
	public int getUstate() {
		return ustate;
	}
	public void setUstate(int ustate) {
		this.ustate = ustate;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getUcollege() {
		return ucollege;
	}
	public void setUcollege(String ucollege) {
		this.ucollege = ucollege;
	}
	public String getUmajor() {
		return umajor;
	}
	public void setUmajor(String umajor) {
		this.umajor = umajor;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUtemp() {
		return utemp;
	}
	public void setUtemp(String utemp) {
		this.utemp = utemp;
	}
}
