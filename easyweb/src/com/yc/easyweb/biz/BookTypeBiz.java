package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.BookType;
import com.yc.easyweb.dao.BookTypeDao;

/**
 * 操作BookType表的事务类
 * @author psq
 *
 */
public class BookTypeBiz {

	private BookTypeDao book = new BookTypeDao();
	
	//查询所有
		public List<BookType> selectAll(BookType bookType){
			try {
				return book.selectAll(bookType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		//添加
		public int insert(BookType bookType) throws BizException{
			if(bookType ==null){
				throw new BizException("请填写类型信息！！！");
			}
			try {
				return book.insert(bookType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
			
		}
		//删除
		public int delete(BookType bookType)throws BizException{
			if(bookType ==null){
				throw new BizException("请填写类型信息！！！");
			}
			try {
				return book.delete(bookType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
			
		}
		//更新
		public  int update (BookType bookTypeNew,BookType bookTypeOld)throws BizException {
			if(bookTypeOld ==null){
				throw new BizException("请填写类型信息！！！");
			}
			try {
				return book.update(bookTypeNew, bookTypeOld);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		//其他
}
