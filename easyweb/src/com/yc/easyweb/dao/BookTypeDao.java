package com.yc.easyweb.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.easyweb.bean.BookType;
import com.yc.easyweb.common.DbHelper;

/**
 * ����BookType���dao��
 * @author psq
 *
 */
public class BookTypeDao {
	DbHelper db = new DbHelper();
	//��ѯ����
	@SuppressWarnings("static-access")
	public List<BookType> selectAll(BookType bookType) throws IOException{
		StringBuffer sb = new StringBuffer();
		sb.append(" select btid,btname,btnamesecond,btnamethird,btstate "
				+ " from booktype where 1=1 ");
		if(bookType != null){
			if(bookType.getBtname() != null && !bookType.getBtname().isEmpty()){
				sb.append(" and btname ='"+bookType.getBtname()+"'");
			}
			if(bookType.getBtnamesecond() != null && !bookType.getBtnamesecond().isEmpty()){
				sb.append(" and btnamesecond ='"+bookType.getBtnamesecond()+"'");
			}
			if(bookType.getBtnamethird() != null && !bookType.getBtnamethird().isEmpty()){
				sb.append(" and btnamethird ='"+bookType.getBtnamethird()+"'");
			}
			if(bookType.getBtid() != 0){
				sb.append(" and btid = "+bookType.getBtid() );
			}
			if(bookType.getBtstate() != 0){
				sb.append(" and btstate = "+bookType.getBtstate() );
			}
		}
		sb.append(" order by  btid asc");
		List<BookType> list = db.selectAll(sb.toString(), null, BookType.class);
		return list;
	}
	//���
	public int insert(BookType bookType) throws SQLException {
		String sql = "insert into booktype(btid,btname,btnamesecond,btnamethird) " 
					+ " values(null,?,?,?);";
		return DbHelper.update(sql, bookType.getBtname(),bookType.getBtnamesecond(),bookType.getBtnamethird());
	}
	//ɾ��
	public int delete(BookType bookType) throws SQLException {
		StringBuffer sb = new StringBuffer();
		if (bookType == null) {
			return 0;
		}
		sb.append("delete from booktype where 1=1 ");
		if(bookType.getBtname() != null && !bookType.getBtname().isEmpty()){
			sb.append(" and btname ='"+bookType.getBtname()+"'");
		}
		if(bookType.getBtnamesecond() != null && !bookType.getBtnamesecond().isEmpty()){
			sb.append(" and btnamesecond ='"+bookType.getBtnamesecond()+"'");
		}
		if(bookType.getBtnamethird() != null && !bookType.getBtnamethird().isEmpty()){
			sb.append(" and btnamethird ='"+bookType.getBtnamethird()+"'");
		}
		if(bookType.getBtid() != 0){
			sb.append(" and btid = "+bookType.getBtid() );
		}
		if(bookType.getBtstate() != 0){
			sb.append(" and btstate = "+bookType.getBtstate() );
		}
		return db.update(sb.toString(), null);
	}
	
	//ɾ����������
	@SuppressWarnings("static-access")
	public int delete(List<BookType> list) throws SQLException {
		StringBuffer sb = null;
		if (list.size() == 0) {
			return 0;
		}
		List<String> sqList = new ArrayList<String>();
		for (BookType bookType : list) {
			sb = new StringBuffer();
			sb.append("delete from booktype where 1=1 ");
			if(bookType.getBtname() != null && !bookType.getBtname().isEmpty()){
				sb.append(" and btname ='"+bookType.getBtname()+"'");
			}
			if(bookType.getBtnamesecond() != null && !bookType.getBtnamesecond().isEmpty()){
				sb.append(" and btnamesecond ='"+bookType.getBtnamesecond()+"'");
			}
			if(bookType.getBtnamethird() != null && !bookType.getBtnamethird().isEmpty()){
				sb.append(" and btnamethird ='"+bookType.getBtnamethird()+"'");
			}
			if(bookType.getBtid() != 0){
				sb.append(" and btid = "+bookType.getBtid() );
			}
			if(bookType.getBtstate() != 0){
				sb.append(" and btstate = "+bookType.getBtstate() );
			}
			sqList.add(sb.toString());
		}
		return db.update(sqList);
	}
	//����
	public  int update (BookType bookTypeNew,BookType bookTypeOld) throws SQLException  {
		StringBuffer sb = new StringBuffer();
		if (bookTypeNew == null || bookTypeOld == null) {
			return 0;
		}
		sb.append("update booktype set bttemp='' ");
		if(bookTypeNew.getBtname() != null && !bookTypeNew.getBtname().isEmpty()){
			sb.append(" ,btname ='"+bookTypeNew.getBtname()+"'");
		}
		if(bookTypeNew.getBtnamesecond() != null && !bookTypeNew.getBtnamesecond().isEmpty()){
			sb.append(" ,btnamesecond ='"+bookTypeNew.getBtnamesecond()+"'");
		}
		if(bookTypeNew.getBtnamethird() != null && !bookTypeNew.getBtnamethird().isEmpty()){
			sb.append(" ,btnamethird ='"+bookTypeNew.getBtnamethird()+"'");
		}
		if(bookTypeNew.getBtstate() != 0){
			sb.append(" , btstate = "+bookTypeNew.getBtstate() );
		}
		sb.append(" where 1=1 ");
		if(bookTypeOld.getBtname() != null && !bookTypeOld.getBtname().isEmpty()){
			sb.append(" and btname ='"+bookTypeOld.getBtname()+"'");
		}
		if(bookTypeOld.getBtnamesecond() != null && !bookTypeOld.getBtnamesecond().isEmpty()){
			sb.append(" and btnamesecond ='"+bookTypeOld.getBtnamesecond()+"'");
		}
		if(bookTypeOld.getBtnamethird() != null && !bookTypeOld.getBtnamethird().isEmpty()){
			sb.append(" and btnamethird ='"+bookTypeOld.getBtnamethird()+"'");
		}
		if(bookTypeOld.getBtid() != 0){
			sb.append(" and btid = "+bookTypeOld.getBtid() );
		}
		if(bookTypeOld.getBtstate() != 0){
			sb.append(" and btstate = "+bookTypeOld.getBtstate() );
		}
		return db.update(sb.toString(), null);
	}
	//����
}
