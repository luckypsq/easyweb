package com.yc.easyweb.bean;

public class User {
	private int uid;//用户id
	private String uname;//用户名
	private String university;//用户所在大学
	private String ucollege;//用户所在学院
	private String umajor;//用户所在专业
	private String upassword;//用户密码
	private String utemp;//备用字段
	private int ustate;//用户状态
	private String uphone;//用户电话
	
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
