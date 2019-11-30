package com.yc.easyweb.dao.lyw;

import java.util.List;
import java.util.Map;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.PageBook;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;

public class PageBookDao {
	public List<Book> selectbuniversity() throws Exception {
		String sql = "select buniversity from book ";
	
		List<Book> list = DbHelper.selectAll(sql,null,Book.class);
		return  list;
	}
	public List<Book> selectbucollege() throws Exception {
		String sql = "select bucollege from book ";
	
		List<Book> list = DbHelper.selectAll(sql,null,Book.class);
		return  list;
	}
	public List<Book> selectbumajor() throws Exception {
		String sql = "select bumajor from book ";
	
		List<Book> list = DbHelper.selectAll(sql,null,Book.class);
		return  list;
	}
	public List<Book> selectbclass() throws Exception {
		String sql = "select bclass from book ";
		List<Book> list = DbHelper.selectAll(sql,null,Book.class);
		return  list;
	}
}
