package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.dao.BookDao;

/**
 * ����book���������
 * @author psq
 *
 */
public class BookBiz {

	private BookDao dao = new BookDao();
	
	//��ѯ����
		public List<Book> selectAll(Book book) throws BizException{
			if(book ==null ){
				throw new BizException("����д�鼮��Ϣ��");
			}
			try {
				return dao.selectAll(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		//���
		public int insert(Book book) throws BizException{
			if(book ==null ){
				throw new BizException("����д�鼮��Ϣ��");
			}
			try {
				return dao.insert(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		//ɾ��
		public int delete(Book book) throws BizException{
			if(book ==null ){
				throw new BizException("����д�鼮��Ϣ��");
			}
			try {
				return dao.delete(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
			
		}
		//����
		public  int update (Book book) {
			return 0;
			
		}
		//����
}
