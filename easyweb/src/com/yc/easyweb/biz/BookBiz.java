package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.bean.Book;
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
	public List<Book> selectAll(Book book) throws BizException {
		if (book == null) {
			throw new BizException("����д�鼮��Ϣ��");
		}
		try {
			return dao.selectAll(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ��ѯ����
	public Book selectSingle(Book book) throws BizException {
		if (book == null) {
			throw new BizException("����д�鼮��Ϣ��");
		}
		try {
			return dao.selectSingle(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ���
	public int insert(Book book) throws BizException {
		if (book == null) {
			throw new BizException("����д�鼮��Ϣ��");
		}
		try {
			return dao.insert(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// ɾ��
	public int delete(Book book) throws BizException {
		if (book == null) {
			throw new BizException("����д�鼮��Ϣ��");
		}
		try {
			return dao.delete(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	// ɾ������
	public int deleteMore(List<Book> list) throws BizException {
		if (list.size() == 0) {
			throw new BizException("����д�鼮��Ϣ��");
		}
		try {
			return dao.delete(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// ����
	public int update(Book newBook, Book oldBook) throws BizException {
		if (newBook == null || oldBook == null) {
			throw new BizException("����д��Ҫ��ĵ��鼮��Ϣ��");
		}
		try {
			return dao.update(newBook, oldBook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}
	// ����
}
