package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.BookType;
import com.yc.easyweb.dao.BookTypeDao;

/**
 * ����BookType���������
 * @author psq
 *
 */
public class BookTypeBiz {

	private BookTypeDao book = new BookTypeDao();
	
	//��ѯ����
		public List<BookType> selectAll(BookType bookType){
			try {
				return book.selectAll(bookType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		//���
		public int insert(BookType bookType){
			return 0;
			
		}
		//ɾ��
		public int delete(BookType bookType){
			return 0;
			
		}
		//����
		public  int update (BookType bookType) {
			return 0;
			
		}
		//����
}
