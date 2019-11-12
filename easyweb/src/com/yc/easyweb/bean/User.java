package com.yc.easyweb.bean;

public class User {
	private int uid;//用户id
	private String uname;//用户名
	private String university;//用户所在大学
	private String ucollege;//用户所在学院
	private String umajor;//用户所在专业
	private String upassword;//用户密码
	private int ustate;//用户状态
	private String uphone;//用户电话
	
	private int utype;//级别(用户，会员，钻石会员，普通管理员，超级管理员)
	private String uemail;//邮箱
	private String utime;//注册时间
	private int usex;//性别(1.男2.女)
	private int uage;//年龄
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
	private String utemp;//备用字段
	
	
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
