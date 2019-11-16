package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.biz.BizException;
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
		public List<Book> selectAll(Book book) throws BizException{
			if(book ==null ){
				throw new BizException("请填写书籍信息！");
			}
			try {
				return dao.selectAll(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		//添加
		public int insert(Book book) throws BizException{
			if(book ==null ){
				throw new BizException("请填写书籍信息！");
			}
			try {
				return dao.insert(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		//删除
		public int delete(Book book) throws BizException{
			if(book ==null ){
				throw new BizException("请填写书籍信息！");
			}
			try {
				return dao.delete(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
			
		}
		//更新
		public  int update (Book book) {
			return 0;
			
		}
		//其他
}
