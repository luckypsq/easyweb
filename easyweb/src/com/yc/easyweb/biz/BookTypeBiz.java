package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
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
		public List<BookType> selectAll(BookType bookType) throws IOException{
				return book.selectAll(bookType);
		}
		//���
		public int insert(BookType bookType) throws BizException, SQLException{
			if(bookType ==null){
				throw new BizException("����д������Ϣ������");
			}
			if(bookType.getBtname() == null && bookType.getBtnamesecond() == null){
				throw new BizException("����д������Ϣ������");
			}
				return book.insert(bookType);
			
		}
		//ɾ��
		public int delete(BookType bookType)throws BizException, SQLException{
			if(bookType ==null){
				throw new BizException("����д��Ҫɾ����������Ϣ������");
			}
			if(bookType.getBtname() == null && bookType.getBtid() == 0 && bookType.getBtnamesecond() == null && bookType.getBtnamethird() == null){
				throw new BizException("��ָ����Ҫɾ�������ͣ�����");
			}
				return book.delete(bookType);
		}
		//����
		public  int update (BookType bookTypeNew,BookType bookTypeOld)throws BizException, SQLException {
			if(bookTypeOld ==null){
				throw new BizException("����д������Ϣ������");
			}
			if(bookTypeOld.getBtname() == null && bookTypeOld.getBtid() == 0 && bookTypeOld.getBtnamesecond() == null && bookTypeOld.getBtnamethird() == null){
				throw new BizException("��ָ����Ҫ�޸ĵ����ͣ�����");
			}
			if(bookTypeNew ==null){
				throw new BizException("����д��Ҫ�޸ĵ���Ϣ������");
			}
			return book.update(bookTypeNew, bookTypeOld);
		}
		//����
}
