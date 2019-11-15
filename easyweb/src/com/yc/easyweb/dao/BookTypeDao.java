package com.yc.easyweb.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.BookType;
import com.yc.easyweb.common.DbHelper;

/**
 * 操作BookType表的dao类
 * @author psq
 *
 */
public class BookTypeDao {
	DbHelper db = new DbHelper();
	//查询所有
	public List<BookType> selectAll(BookType bookType) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(" select btid,btname,btnamesecond,btnamethird "
				+ " from booktype where 1=1 ");
		if(bookType != null){
			if(bookType.getBtname() != null){
				sb.append(" and btname like '%"+bookType.getBtname()+"%'");
			}
			if(bookType.getBtnamesecond() != null){
				sb.append(" and btnamesecond like '%"+bookType.getBtnamesecond()+"%'");
			}
			if(bookType.getBtnamethird() != null){
				sb.append(" and btnamethird like '%"+bookType.getBtnamethird()+"%'");
			}
			if(bookType.getBtid() != 0){
				sb.append(" and btid = "+bookType.getBtid() );
			}
		}
		sb.append(" order by  btid desc");
		List<BookType> list = db.selectAll(sb.toString(), null, BookType.class);
		return list;
	}
	//添加
	public int insert(BookType bookType) throws Exception{
		String sql = "insert into booktype(btid,btname,btnamesecond,btnamethird) " 
					+ " values(null,?,?,?);";

		return DbHelper.update(sql, bookType.getBtname(),bookType.getBtnamesecond(),bookType.getBtnamethird());
	}
	//删除
	public int delete(BookType bookType) throws Exception{
		StringBuffer sb = new StringBuffer();
		if (bookType == null) {
			return 0;
		}
		sb.append("delete from booktype where 1=1 ");
		if(bookType.getBtname() != null){
			sb.append(" and btname ='"+bookType.getBtname()+"'");
		}
		if(bookType.getBtnamesecond() != null){
			sb.append(" and btnamesecond ='"+bookType.getBtnamesecond()+"'");
		}
		if(bookType.getBtnamethird() != null){
			sb.append(" and btnamethird ='"+bookType.getBtnamethird()+"'");
		}
		if(bookType.getBtid() != 0){
			sb.append(" and btid = "+bookType.getBtid() );
		}
		return db.update(sb.toString(), null);
	}
	
	//删除多条数据
	public int delete(List<BookType> list) throws Exception{
		StringBuffer sb = null;
		if (list.size() == 0) {
			return 0;
		}
		List<String> sqList = new ArrayList<String>();
		for (BookType b : list) {
			sb = new StringBuffer();
			sb.append("delete from booktype where 1=1 ");
			if(b.getBtname() != null){
				sb.append(" and btname ='"+b.getBtname()+"'");
			}
			if(b.getBtnamesecond() != null){
				sb.append(" and btnamesecond ='"+b.getBtnamesecond()+"'");
			}
			if(b.getBtnamethird() != null){
				sb.append(" and btnamethird ='"+b.getBtnamethird()+"'");
			}
			if(b.getBtid() != 0){
				sb.append(" and btid = "+b.getBtid() );
			}
			sqList.add(sb.toString());
		}
		return db.update(sqList, null);
	}
	//更新
	public  int update (BookType bookTypeNew,BookType bookTypeOld) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (bookTypeNew == null || bookTypeOld == null) {
			return 0;
		}
		sb.append("update booktype set bttemp='' ");
		if(bookTypeNew.getBtname() != null){
			sb.append(" ,btname ='"+bookTypeNew.getBtname()+"'");
		}
		if(bookTypeNew.getBtnamesecond() != null){
			sb.append(" ,btnamesecond ='"+bookTypeNew.getBtnamesecond()+"'");
		}
		if(bookTypeNew.getBtnamethird() != null){
			sb.append(" ,btnamethird ='"+bookTypeNew.getBtnamethird()+"'");
		}
		if(bookTypeNew.getBtid() != 0){
			sb.append(" ,btid = "+bookTypeNew.getBtid() );
		}
		sb.append(" where 1=1 ");
		if(bookTypeOld.getBtname() != null){
			sb.append(" and btname ='"+bookTypeOld.getBtname()+"'");
		}
		if(bookTypeOld.getBtnamesecond() != null){
			sb.append(" and btnamesecond ='"+bookTypeOld.getBtnamesecond()+"'");
		}
		if(bookTypeOld.getBtnamethird() != null){
			sb.append(" and btnamethird ='"+bookTypeOld.getBtnamethird()+"'");
		}
		if(bookTypeOld.getBtid() != 0){
			sb.append(" and btid = "+bookTypeOld.getBtid() );
		}
		return db.update(sb.toString(), null);
	}
	//其他
}
