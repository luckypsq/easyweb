package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Page;
import com.yc.easyweb.dao.BookDao;

/**
 * ����book���������
 * 
 * @author psq
 *
 */
public class BookBiz {

	private BookDao dao = new BookDao();

	// ��ѯ����
	public List<Book> selectAll(Book book) throws  IOException {
		return dao.selectAll(book);
	}
	// ��ѯ����
	public Book selectSingle(Book book) throws BizException, IOException {
		if (book == null) {
			throw new BizException("����д�鼮��Ϣ��");
		}
		return dao.selectSingle(book);
	}
	// ���
	public int insert(Book book) throws BizException, SQLException {
		if (book == null) {
			throw new BizException("����д�鼮��Ϣ��");
		}
			return dao.insert(book);
	}

	// ɾ��
	public int delete(Book book) throws BizException, SQLException {
		if (book == null) {
			throw new BizException("����д�鼮��Ϣ��");
		}
			return dao.delete(book);
	}

	// ɾ������
	public int deleteMore(List<Book> list) throws BizException, SQLException {
		if (list.size() == 0) {
			throw new BizException("����д�鼮��Ϣ��");
		}
			return dao.delete(list);
	}

	// ����
	public int update(Book newBook, Book oldBook) throws BizException, SQLException {
		if (newBook == null ) {
			throw new BizException("����д��Ҫ�޸ĵ���Ϣ��");
		}
		if (oldBook == null ) {
			throw new BizException("����д��Ҫ�޸ĵ��鼮��");
		}
			return dao.update(newBook, oldBook);
	}
	//��ҳ
	public Page<Book> bookPage(int page, int rows,Book book) throws IOException{
		return dao.bookPage(page, rows, book);
	}
	// ����
}
