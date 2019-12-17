package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.BookChild;
import com.yc.easyweb.bean.OrderDetial;
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
	public List<Book> selectAll(Book book) throws  IOException, BizException {
		if (book == null) {
			throw new BizException("����д�鼮��Ϣ������");
		}
		return dao.selectAll(book);
	}
	// ��ѯ����
	public Book selectSingle(Book book) throws BizException, IOException {
		if (book == null) {
			throw new BizException("����д�鼮��Ϣ������");
		}
		if(book.getBid() == 0  && book.getBname() == null ){
			throw new BizException("δָ���鼮������");
		}
		return dao.selectSingle(book);
	}
	// ���
	public int insert(Book book) throws BizException, SQLException {
		if (book == null) {
			throw new BizException("����д�鼮��Ϣ������");
		}
		if(book.getBname() == null ){
			throw new BizException("����д����������");
		}else if(book.getBname().isEmpty()){
			throw new BizException("����д����������");
		}
		if(book.getBprice() == 0){
			throw new BizException("����д�鼮�۸񣡣���");
		}
		return dao.insert(book);
	}

	// ɾ��
	public int delete(Book book) throws BizException, SQLException, IOException {
		if (book == null) {
			throw new BizException("����д�鼮��Ϣ������");
		}
		if(book.getBid() == 0 && book.getUid() ==0 && book.getBname() == null){
			throw new BizException("δָ��ɾ�����鼮������");
		}
		EorderBiz biz = new EorderBiz();
		OrderDetial eo = new OrderDetial();
		eo.setBid(book.getBid());
		List<OrderDetial> list = biz.selectAllDetail(eo);
		if(list.size() != 0 ){
			for(OrderDetial o : list){
				if(o.getEostate() != 5 && o.getEostate() != 6){
					throw new BizException("���ж���δ�����겻��ɾ��������");
				}
			}
		}
		return dao.delete(book);
	}

	// ɾ������
	public int deleteMore(List<Book> list) throws BizException, SQLException, IOException {
		if (list.size() == 0) {
			throw new BizException("����д�鼮��Ϣ������");
		}
		for(Book book : list){
			if(book.getBid() == 0 && book.getUid() ==0 && book.getBname() == null){
				throw new BizException("δָ��ɾ�����鼮������");
			}
			EorderBiz biz = new EorderBiz();
			OrderDetial eo = new OrderDetial();
			eo.setBid(book.getBid());
			List<OrderDetial> list1 = biz.selectAllDetail(eo);
			if(list1.size() != 0 ){
				for(OrderDetial o : list1){
					if(o.getEostate() != 5 && o.getEostate() != 6){
						throw new BizException("���ж���δ�����겻��ɾ��������");
					}
				}
			}
		}
			return dao.delete(list);
	}

	// ����
	public int update(Book newBook, Book oldBook) throws BizException, SQLException {
		if (newBook == null ) {
			throw new BizException("����д��Ҫ�޸ĵ���Ϣ������");
		}
		if (oldBook == null ) {
			throw new BizException("����д��Ҫ�޸ĵ��鼮������");
		}
		if(oldBook.getBid() == 0 && oldBook.getBname() == null){
			throw new BizException("����д��Ҫ�޸ĵ��鼮��Ϣ������");
		}
		return dao.update(newBook, oldBook);
	}
	//��ҳ
	public Page<Book> bookPage(int page, int rows,Book book) throws IOException{
		return dao.bookPage(page, rows, book);
	}
	public Page<Book> bookChildPage(int page, int rows,BookChild book) throws IOException{
		return dao.bookChildPage(page, rows, book);
	}
	// ����
}
