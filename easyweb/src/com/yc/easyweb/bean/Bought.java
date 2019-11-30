package com.yc.easyweb.bean;

public class Bought {
	private String itemid;
	private long cid;//购物车id
	private String eoid;//订单号
	private String ctemp;//备用字段
	private long uid;//用户id
	private int eostate;//状态1.待付款2.待发货3.已发货4.退货申请中5.退款成功6.订单取消7.已接收
	private String eotime;//下单时间
	private String bucollege;//书籍分类学院
	private String bumajor;//书籍分类专业
	private String bclass;//书籍分类年级
	private String bname;//书名
	private double bprice;//价格
	private String bimg;//图片
	private int count;//数量
	private double total;//总价
	private String eoaddr;//地址
	private String eotype;//配送方式(在线支付 ，货到付款 店面接取)
	private String uname;//用户名
	
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	public double getBprice() {
		return bprice;
	}
	public void setBprice(double bprice) {
		this.bprice = bprice;
	}
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public String getEoid() {
		return eoid;
	}
	public void setEoid(String eoid) {
		this.eoid = eoid;
	}
	public String getCtemp() {
		return ctemp;
	}
	public void setCtemp(String ctemp) {
		this.ctemp = ctemp;
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
	public String getEotime() {
		return eotime;
	}
	public void setEotime(String eotime) {
		this.eotime = eotime;
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
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
}
