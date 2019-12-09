package com.yc.easyweb.dao;

import java.io.IOException;
import java.util.List;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.common.DbHelper;

public class PageBookDao {
	public List<Book> selectbuniversity() throws IOException  {
		String sql = "select buniversity from book ";
		List<Book> list = DbHelper.selectAll(sql,null,Book.class);
		return  list;
	}
	public List<Book> selectbucollege() throws IOException  {
		String sql = "select bucollege from book ";
	
		List<Book> list = DbHelper.selectAll(sql,null,Book.class);
		return  list;
	}
	public List<Book> selectbumajor() throws IOException  {
		String sql = "select bumajor from book ";
	
		List<Book> list = DbHelper.selectAll(sql,null,Book.class);
		return  list;
	}
	public List<Book> selectbclass() throws IOException  {
		String sql = "select bclass from book ";
		List<Book> list = DbHelper.selectAll(sql,null,Book.class);
		return  list;
	}
}
