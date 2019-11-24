package com.yc.easyweb.dao;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Cart;
import com.yc.easyweb.common.DbHelper;

/**
 * 操作Cart表的dao类
 * 
 * @author psq
 *
 */
public class CartDao {
	// 查询所有
	public List<Cart> selectAll(Cart cart) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select cid,eoid,uid,ctemp from book where 1=1 ");
		if (cart != null) {
			if (cart.getEoid() != null) {
				sb.append(" and eoid like '%" + cart.getEoid() + "%'");
			}
			if (cart.getCid() != 0) {
				sb.append(" and cid = " + cart.getCid());
			}
			if (cart.getUid() != 0) {
				sb.append(" and uid =" + cart.getUid());
			}
		}
		sb.append(" order by  cid desc");
		List<Cart> list = DbHelper.selectAll(sb.toString(), null, Cart.class);
		return list;
	}

	// 添加
	public int insert(Cart cart) throws Exception {
		String sql = "insert into cart(cid,eoid,uid,ctemp) " + " values(null,?,?,?);";
		return DbHelper.update(sql, cart.getEoid(), cart.getUid(), cart.getCtemp());
	}
	// 删除
	public int delete(Cart cart) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (cart == null) {
			return 0;
		}
		sb.append("delete from cart where 1=1 ");
		if (cart.getEoid() != null) {
			sb.append(" and eoid ='" + cart.getEoid() + "'");
		}
		if (cart.getCid() != 0) {
			sb.append(" and cid = " + cart.getCid());
		}
		if (cart.getUid() != 0) {
			sb.append(" and uid = " + cart.getUid());
		}
		return DbHelper.update(sb.toString(), null);

	}
	
	//删除多条
	public int delete(List<Cart> list) throws Exception {
		StringBuffer sb = null;
		if (list.size() == 0) {
			return 0;
		}
		List<String> sqList = new ArrayList<String>();
		for (Cart cart : list) {
			sb = new StringBuffer();
			sb.append("delete from cart where 1=1 ");
			if (cart.getEoid() != null) {
				sb.append(" and eoid ='" + cart.getEoid() + "'");
			}
			if (cart.getCid() != 0) {
				sb.append(" and cid = " + cart.getCid());
			}
			if (cart.getUid() != 0) {
				sb.append(" and uid = " + cart.getUid());
			}
			sqList.add(sb.toString());
		}
		return DbHelper.update(sqList);
	}

	// 更新
	public int update(Cart cartNew,Cart cartOld) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (cartNew== null || cartOld== null) {
			return 0;
		}
		sb.append("update cart set ctemp='' ");
		if (cartNew.getEoid() != null) {
			sb.append(" , eoid ='" + cartNew.getEoid() + "'");
		}
		if (cartNew.getCid() != 0) {
			sb.append(" ,cid = " + cartNew.getCid());
		}
		if (cartNew.getUid() != 0) {
			sb.append(" , uid = " + cartNew.getUid());
		}
		sb.append(" where 1=1 ");
		if (cartOld.getEoid() != null) {
			sb.append(" and eoid ='" + cartOld.getEoid() + "'");
		}
		if (cartOld.getCid() != 0) {
			sb.append(" and cid = " + cartOld.getCid());
		}
		if (cartOld.getUid() != 0) {
			sb.append(" and uid = " + cartOld.getUid());
		}
		return DbHelper.update(sb.toString(), null);

	}
	// 其他
}
