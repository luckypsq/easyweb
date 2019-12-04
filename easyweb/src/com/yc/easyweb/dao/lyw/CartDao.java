package com.yc.easyweb.dao.lyw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.yc.easyweb.bean.Bought;
import com.yc.easyweb.common.DbHelper;

public class CartDao {

	public List<Bought> selectbAll(List<Object> params) throws Exception {
		String sql = "select bucollege,bumajor,bclass,bname,bprice,bimg,itemid,count,eo.bid,eo.eoid,total,eitemp,"
				+ " eo.uid,cartstate,carttime "
				+ " from book b,eorderitem eo,eorder e  where eo.eoid=e.eoid and eo.bid=b.bid and e.uid=?";
		List<Bought> list = DbHelper.selectAll(sql,params,Bought.class);
		return  list;
	}
	//查询单个
	public Bought selectSingle(List<Object> params) throws Exception {
		String sql = "select bucollege,bumajor,bclass,bname,bprice,bimg,itemid,count,eo.bid,eo.eoid,"
				+ " total,eitemp,eo.uid,cartstate,carttime "
				+ " from book b,eorderitem eo,eorder e  where eo.eoid=e.eoid and eo.bid=b.bid and e.uid=?";
		return  DbHelper.selectSingle(sql,params,Bought.class);
	}
	
	//查询单个
		public Map<String, Object> selectById(List<Object> params) throws Exception {
			String sql = "select bname,bprice,bimg,itemid,count,eo.bid,total"
					+ " from book b,eorderitem eo  where eo.bid=b.bid and itemid=?";
			return  DbHelper.selectSingle(sql, params);
		}
	
	public int update(int eostate,String itemid) throws Exception {
		String sql = "update eorderitem set cartstate=? where itemid =?";
		return DbHelper.update(sql,eostate,itemid);
	}
	public int updateall(String itemid) throws Exception {
		String sql = "delete from eorderitem where itemid  =?";
		return DbHelper.update(sql, itemid);
	}
	public int updatebj(String count,String itemid) throws Exception {
		CartDao dao =new CartDao();
		List<Object> params = new ArrayList<Object>();
		params.add(itemid);
		Bought bought = dao.selectSingle(params);
		double price = 0;
		if(bought != null){
			if(bought.getBprice() != 0){
				price = bought.getBprice();
			}
		}
		price = price * Integer.parseInt(count);
		String sql = "update eorderitem set count=?,total=? where itemid =?";
		return DbHelper.update(sql,count,price,itemid);
	}
}
