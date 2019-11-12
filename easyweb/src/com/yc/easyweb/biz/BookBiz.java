package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.dao.BookDao;

/**
 * 操作book表的事务类
 * @author psq
 *
 */
public class BookBiz {

	private BookDao dao = new BookDao();
	
	//查询所有
		public List<Book> selectAll(Book book) throws Exception{
			return dao.selectAll(book);
		}
		//添加
		public int insert(Book book){
			return 0;
			
		}
		//删除
		public int delete(Book book){
			return 0;
			
		}
		//更新
		public  int update (Book book) {
			return 0;
			
		}
		//其他
}
