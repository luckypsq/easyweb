package com.yc.easyweb.biz;

import java.util.List;

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
		public List<Book> selectAll(Book book) throws Exception{
			return dao.selectAll(book);
		}
		//���
		public int insert(Book book){
			return 0;
			
		}
		//ɾ��
		public int delete(Book book){
			return 0;
			
		}
		//����
		public  int update (Book book) {
			return 0;
			
		}
		//����
}
