package com.yc.easyweb.bean;
//������������
public class OrderDetial {
	private String eoid;//�������
	private String bname;//����
	private double total;//�ܼ�
	private String eotime;//ʱ��
	private String eotype;//��������
	private String eoaddr;//��ַ
	private String uphone;//�绰
	private String uname;//�û���
	private long uid;//�û�id
	private long bid;//��id
	private long count;//����
	private int eostate;//״̬
	private String eoespress;//�������
	private String eopayname;//֧������
	private String bimg;//ͼƬ
	
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	public String getEoespress() {
		return eoespress;
	}
	public void setEoespress(String eoespress) {
		this.eoespress = eoespress;
	}
	public String getEopayname() {
		return eopayname;
	}
	public void setEopayname(String eopayname) {
		this.eopayname = eopayname;
	}
	public String getEoid() {
		return eoid;
	}
	public void setEoid(String eoid) {
		this.eoid = eoid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getEotime() {
		return eotime;
	}
	public void setEotime(String eotime) {
		this.eotime = eotime;
	}
	public String getEotype() {
		return eotype;
	}
	public void setEotype(String eotype) {
		this.eotype = eotype;
	}
	public String getEoaddr() {
		return eoaddr;
	}
	public void setEoaddr(String eoaddr) {
		this.eoaddr = eoaddr;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public int getEostate() {
		return eostate;
	}
	public void setEostate(int eostate) {
		this.eostate = eostate;
	}
	@Override
	public String toString() {
		return "OrderDetial [eoid=" + eoid + ", bname=" + bname + ", total=" + total + ", eotime=" + eotime
				+ ", eotype=" + eotype + ", eoaddr=" + eoaddr + ", uphone=" + uphone + ", uname=" + uname + ", uid="
				+ uid + ", bid=" + bid + ", count=" + count + ", eostate=" + eostate + "]";
	}
	
	
}