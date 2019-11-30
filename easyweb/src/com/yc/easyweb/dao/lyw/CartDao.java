package com.yc.easyweb.dao.lyw;

import java.util.List;
import com.yc.easyweb.bean.Bought;
import com.yc.easyweb.common.DbHelper;

public class CartDao {

	public List<Bought> selectbAll(List<Object> params) throws Exception {
		String sql = "select bucollege,bumajor,bclass,bname,bprice,eotime,eostate,bimg,itemid,e.eoid,count,total from book b,eorderitem eo,eorder e  where eo.eoid=e.eoid and eo.bid=b.bid and e.uid=?";
		List<Bought> list = DbHelper.selectAll(sql,params,Bought.class);
		System.out.println(list);
		return  list;
	}
	public int update(int eostate,String itemid) throws Exception {
		String sql = "update eorderitem set eostate=? where itemid =?";
		return DbHelper.update(sql,eostate,itemid);
	}
	public int updateall(String itemid) throws Exception {
		String sql = "delete from eorderitem where itemid  =?";
		System.out.println(sql);
		return DbHelper.update(sql, itemid);
	}
	public int updatebj(String count,String total,String itemid) throws Exception {
		String sql = "update eorderitem set count=?,total=? where itemid =?";
		return DbHelper.update(sql,count,total,itemid);
	}
}
