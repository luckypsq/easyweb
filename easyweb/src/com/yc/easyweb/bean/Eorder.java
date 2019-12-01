package com.yc.easyweb.bean;

public class Eorder {
	private String eoid;//订单id
	private long uid;//用户id
	private int eostate;//状态1.待付款2.待发货3.已发货4.退货申请中5.退款成功6.已接收7.退货失败
	private String eotime;//下单时间
	private String eotemp;//备用字段
	private String uname;//用户名
	private String eoaddr;//地址
	private String eotype;//配送方式(在线支付 ，货到付款 店面接取)
	private String eoespress;//快递名字
	private long eopaytypeid;//支付类型
	private String carttime;//加入购物车时间
	
	
	public String getCarttime() {
		return carttime;
	}
	public void setCarttime(String carttime) {
		this.carttime = carttime;
	}
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
				+ eotemp + ", uname=" + uname + ", eoaddr=" + eoaddr + ", eotype=" + eotype + "]";
	}
	
}
