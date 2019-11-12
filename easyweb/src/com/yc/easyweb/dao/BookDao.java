package com.yc.easyweb.dao;

import java.util.List;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.util.DbHelper;

/**
 * ����notice���dao��
 * @author psq
 *
 */
public class BookDao {
	DbHelper db = new DbHelper();
	//��ѯ����
	public List<Book> selectAll(Book book) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(" select bid,bname,buniversity,bucollege,bumajor,bclass, "
				+ " bcontent,bimg,bprice,bstate,btid,btemp,btemp1"
				+ " from book where 1=1 ");
		if(book != null){
			if(book.getBname() != null){
				sb.append(" and bname like '%"+book.getBname()+"%'");
			}
			if(book.getBuniversity() != null){
				sb.append(" and buniversity like '%"+book.getBuniversity()+"%'");
			}
			
			if(book.getBucollege() != null){
				sb.append(" and bucollege like '%"+book.getBucollege()+"%'");
			}
			
			if(book.getBumajor() != null){
				sb.append(" and bumajor like '%"+book.getBumajor()+"%'");
			}
			
			if(book.getBclass() != null){
				sb.append(" and bclass like '%"+book.getBclass()+"%'");
			}
		}
		sb.append(" order by  bid asc");
		List<Book> list = db.selectAll(sb.toString(), null, Book.class);
		return list;
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
