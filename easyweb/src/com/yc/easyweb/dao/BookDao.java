package com.yc.easyweb.dao;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.binding.SelectBinding.AsBoolean;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;

/**
 * 操作notice表的dao类
 * 
 * @author psq
 *
 */
public class BookDao {
	DbHelper db = new DbHelper();

	// 查询所有
	public List<Book> selectAll(Book book) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select bid,bname,buniversity,bucollege,bumajor,bclass, "
				+ " bcontent,bimg,bprice,bstate,btid,btemp,btemp1,bnum,bauthor,bdate" + " from book where 1=1 ");
		if (book != null) {
			if (book.getBname() != null && !book.getBname().isEmpty()) {
				sb.append(" and bname like '%" + book.getBname() + "%'");
			}
			if (book.getBuniversity() != null && !book.getBuniversity().isEmpty()) {
				sb.append(" and buniversity like '%" + book.getBuniversity() + "%'");
			}
			if (book.getBucollege() != null && !book.getBucollege().isEmpty()) {
				sb.append(" and bucollege like '%" + book.getBucollege() + "%'");
			}
			if (book.getBumajor() != null && !book.getBumajor().isEmpty()) {
				sb.append(" and bumajor like '%" + book.getBumajor() + "%'");
			}
			if (book.getBclass() != null && !book.getBclass().isEmpty()) {
				sb.append(" and bclass like '%" + book.getBclass() + "%'");
			}
			
			if(book.getBnum() != null && !book.getBnum().toString().isEmpty()){
				sb.append(" and bnum =" + book.getBnum());
			}
			if (book.getBauthor() != null && !book.getBauthor().isEmpty()) {
				sb.append(" and bauthor like '%" + book.getBauthor() + "%'");
			}
			if (book.getBtid() != null) {
				sb.append(" and btid = " + book.getBtid());
			}
			if(book.getBprice() != 0){
				sb.append(" and bprice = " + book.getBprice());
			}
			if (book.getBstate() != 0) {
				sb.append(" and bstate = " + book.getBstate());
			}
			if (book.getBtemp() != null && !book.getBtemp().isEmpty()) {
				sb.append(" and btemp like '%" + book.getBtemp() + "%'");
			}
			if (book.getBid() != 0) {
				sb.append(" and bid = " + book.getBid());
			}
			if (book.getBdate() != null && !book.getBdate().isEmpty()) {
				sb.append(" and bdate like '%" + book.getBdate() + "%'");
			}
		}
		sb.append(" order by  bid desc");
		//System.out.println(sb.toString());
		List<Book> list = db.selectAll(sb.toString(), null, Book.class);
		return list;
	}
	//查询单条记录
	public Book selectSingle(Book book) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select bid,bname,buniversity,bucollege,bumajor,bclass, "
				+ " bcontent,bimg,bprice,bstate,btid,btemp,btemp1,bnum,bauthor,bdate" + " from book where 1=1 ");
		if (book != null) {
			if (book.getBname() != null && !book.getBname().isEmpty()) {
				sb.append(" and bname like '%" + book.getBname() + "%'");
			}
			if (book.getBuniversity() != null && !book.getBuniversity().isEmpty()) {
				sb.append(" and buniversity like '%" + book.getBuniversity() + "%'");
			}
			if (book.getBucollege() != null && !book.getBucollege().isEmpty()) {
				sb.append(" and bucollege like '%" + book.getBucollege() + "%'");
			}
			if (book.getBumajor() != null && !book.getBumajor().isEmpty()) {
				sb.append(" and bumajor like '%" + book.getBumajor() + "%'");
			}
			if (book.getBclass() != null && !book.getBclass().isEmpty()) {
				sb.append(" and bclass like '%" + book.getBclass() + "%'");
			}
			
			if(book.getBnum() != null && !book.getBnum().toString().isEmpty()){
				sb.append(" and bnum =" + book.getBnum());
			}
			if (book.getBauthor() != null && !book.getBauthor().isEmpty()) {
				sb.append(" and bauthor like '%" + book.getBauthor() + "%'");
			}
			if (book.getBtid() != null) {
				sb.append(" and btid = " + book.getBtid());
			}
			if(book.getBprice() != 0){
				sb.append(" and bprice = " + book.getBprice());
			}
			if (book.getBstate() != 0) {
				sb.append(" and bstate = " + book.getBstate());
			}
			if (book.getBtemp() != null && !book.getBtemp().isEmpty()) {
				sb.append(" and btemp like '%" + book.getBtemp() + "%'");
			}
			if (book.getBid() != 0) {
				sb.append(" and bid = " + book.getBid());
			}
			if (book.getBdate() != null && !book.getBdate().isEmpty()) {
				sb.append(" and bdate like '%" + book.getBdate() + "%'");
			}
		}
		sb.append(" order by  bid desc");
		//System.out.println(sb.toString());
		return db.selectSingle(sb.toString(), null, Book.class);	
	}
	
	// 添加
	public int insert(Book book) throws Exception {
		String sql = "insert into book(bid,bname,buniversity,bucollege," + "bumajor,bclass,bcontent,bimg,bprice,btid,"
				+ "btemp,bnum,bauthor,bdate) " + " values(null,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		return DbHelper.update(sql, book.getBname(), book.getBuniversity(), book.getBucollege(), book.getBumajor(),
				book.getBclass(), book.getBcontent(), book.getBimg(), book.getBprice(), book.getBtid(), book.getBtemp(),
				book.getBnum(), book.getBauthor(), book.getBdate());
	}

	// 删除
	public int delete(Book book) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from book where 1=1 ");
		if (book != null) {
			if (book.getBname() != null && !book.getBname().isEmpty()) {
				sb.append(" and bname ='" + book.getBname() + "'");
			}
			if (book.getBuniversity() != null && !book.getBuniversity().isEmpty()) {
				sb.append(" and buniversity ='"  + book.getBuniversity() + "'");
			}
			if (book.getBucollege() != null && !book.getBucollege().isEmpty()) {
				sb.append(" and bucollege ='"  + book.getBucollege() + "'");
			}
			if (book.getBumajor() != null && !book.getBumajor().isEmpty()) {
				sb.append(" and bumajor ='"  + book.getBumajor() + "'");
			}
			if (book.getBclass() != null && !book.getBclass().isEmpty()) {
				sb.append(" and bclass ='"  + book.getBclass() + "'");
			}
			
			if(book.getBnum() != null && !book.getBnum().toString().isEmpty()){
				sb.append(" and bnum =" + book.getBnum());
			}
			if (book.getBauthor() != null && !book.getBauthor().isEmpty()) {
				sb.append(" and bauthor ='"  + book.getBauthor() + "'");
			}
			if (book.getBtid() != null) {
				sb.append(" and btid = " + book.getBtid());
			}
			if(book.getBprice() != 0){
				sb.append(" and bprice = " + book.getBprice());
			}
			if (book.getBstate() != 0) {
				sb.append(" and bstate = " + book.getBstate());
			}
			if (book.getBtemp() != null && !book.getBtemp().isEmpty()) {
				sb.append(" and btemp ='"  + book.getBtemp() + "'");
			}
			if (book.getBid() != 0) {
				sb.append(" and bid = " + book.getBid());
			}
			if (book.getBdate() != null && !book.getBdate().isEmpty()) {
				sb.append(" and bdate ='"  + book.getBdate() + "'");
			}
		}
		return db.update(sb.toString(), null);
	}

	// 删除多条数据
	public int delete(List<Book> list) throws Exception {
		StringBuffer sb = null;
		if (list.size() == 0) {
			return 0;
		}
		// 保存所有的数据库语句
		List<String> sqList = new ArrayList<String>();
		for (Book book : list) {
			sb = new StringBuffer();
			sb.append("delete from book where 1=1 ");
			if (book != null) {
				if (book.getBid() != 0) {
					sb.append(" and bid = " + book.getBid());
				}
				if (book.getBdate() != null && !book.getBdate().isEmpty()) {
					sb.append(" and bdate ='"  + book.getBdate() + "'");
				}
				sqList.add(sb.toString());
			}
		}
		return db.update(sqList);
	}

	// 更新
	public int update(Book bookNew, Book bookOld) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (bookNew == null || bookOld == null) {
			return 0;
		}
		sb.append("update book set btemp1='' ");
		if (bookNew.getBname() != null && !bookNew.getBname().isEmpty()) {
			sb.append(" ,bname = '" + bookNew.getBname() + "'");
		}
		if (bookNew.getBuniversity() != null && !bookNew.getBuniversity().isEmpty()) {
			sb.append(" ,buniversity =  '" + bookNew.getBuniversity() + "'");
		}
		if (bookNew.getBucollege() != null && !bookNew.getBucollege().isEmpty()) {
			sb.append(" ,bucollege =  '" + bookNew.getBucollege() + "'");
		}
		if (bookNew.getBumajor() != null && !bookNew.getBumajor().isEmpty()) {
			sb.append(" ,bumajor =  '" + bookNew.getBumajor() + "'");
		}
		if (bookNew.getBclass() != null && !bookNew.getBclass().isEmpty()) {
			sb.append(" ,bclass =  '" + bookNew.getBclass() + "'");
		}

		if (bookNew.getBtid() != null && bookNew.getBtid() != 0 ) {
			sb.append(" ,btid = " + bookNew.getBtid());
		}
		if (bookNew.getBtemp() != null && !bookNew.getBtemp().isEmpty()) {
			sb.append(" ,btemp = '" + bookNew.getBtemp() + "'");
		}
		if (bookNew.getBauthor() != null && !bookNew.getBauthor().isEmpty()) {
			sb.append(" ,bauthor =  '" + bookNew.getBauthor() + "'");
		}
		//价格
		if(bookNew.getBprice()!=0){
			sb.append(" ,bprice = " + bookNew.getBprice());
		}
		//库存
		if(bookNew.getBnum()!=null && !bookNew.getBnum().toString().isEmpty()){
			sb.append(" ,bnum = " + bookNew.getBnum());
		}
		//图片
		if(bookNew.getBimg()!=null && !bookNew.getBimg().isEmpty()){
			sb.append(" ,bimg = '" + bookNew.getBimg()+"'");
		}
		//内容
		if(bookNew.getBcontent()!=null && !bookNew.getBcontent().isEmpty()){
			sb.append(" ,bcontent = '" + bookNew.getBcontent()+"'");
		}
		if (bookNew.getBstate() != 0) {
			sb.append(" ,bstate = " + bookNew.getBstate());
		}
		if (bookNew.getBdate() != null && !bookNew.getBdate().isEmpty()) {
			sb.append(" ,bdate = '" + bookNew.getBdate()+"'");
		}
		
		sb.append(" where 1=1 ");
		
		if (bookOld.getBname() != null && !bookOld.getBname().isEmpty()) {
			sb.append(" and bname =  '" + bookOld.getBname() + "'");
		}
		if (bookOld.getBauthor() != null && !bookOld.getBauthor().isEmpty()) {
			sb.append(" and bauthor =  '" + bookOld.getBauthor() + "'");
		}
		if (bookOld.getBtid() != null) {
			sb.append(" and btid = " + bookOld.getBtid());
		}
		if (bookOld.getBstate() != 0) {
			sb.append(" and bstate = " + bookOld.getBstate());
		}
		if (bookOld.getBid() != 0) {
			sb.append(" and bid = " + bookOld.getBid());
		}
		return db.update(sb.toString(), null);

	}
	//更新多条
	// 其他
}
