package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Page;
import com.yc.easyweb.dao.BookDao;

/**
 * 操作book表的事务类
 * 
 * @author psq
 *
 */
public class BookBiz {

	private BookDao dao = new BookDao();

	// 查询所有
	public List<Book> selectAll(Book book) throws  IOException {
		return dao.selectAll(book);
	}
	// 查询单条
	public Book selectSingle(Book book) throws BizException, IOException {
		if (book == null) {
			throw new BizException("请填写书籍信息！");
		}
		return dao.selectSingle(book);
	}
	// 添加
	public int insert(Book book) throws BizException, SQLException {
		if (book == null) {
			throw new BizException("请填写书籍信息！");
		}
			return dao.insert(book);
	}

	// 删除
	public int delete(Book book) throws BizException, SQLException {
		if (book == null) {
			throw new BizException("请填写书籍信息！");
		}
			return dao.delete(book);
	}

	// 删除多条
	public int deleteMore(List<Book> list) throws BizException, SQLException {
		if (list.size() == 0) {
			throw new BizException("请填写书籍信息！");
		}
			return dao.delete(list);
	}

	// 更新
	public int update(Book newBook, Book oldBook) throws BizException, SQLException {
		if (newBook == null ) {
			throw new BizException("请填写需要修改的信息！");
		}
		if (oldBook == null ) {
			throw new BizException("请填写需要修改的书籍！");
		}
			return dao.update(newBook, oldBook);
	}
	//分页
	public Page<Book> bookPage(int page, int rows,Book book) throws IOException{
		return dao.bookPage(page, rows, book);
	}
	// 其他
}
