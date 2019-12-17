package com.yc.easyweb.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.BookChild;
import com.yc.easyweb.bean.Page;
import com.yc.easyweb.common.DbHelper;

/**
 * ����notice���dao��
 * 
 * @author psq
 *
 */
public class BookDao {
	DbHelper db = new DbHelper();

	// ��ѯ����
	@SuppressWarnings("static-access")
	public List<Book> selectAll(Book book) throws IOException  {
		StringBuffer sb = new StringBuffer();
		sb.append(" select bid,bname,buniversity,bucollege,bumajor,bclass, "
				+ " bcontent,bimg,bprice,bstate,btid,btemp,btemp1,bnum,bauthor,bdate,uid" + " from book where 1=1 ");
		if (book != null) {
			//����
			if (book.getBname() != null && !book.getBname().isEmpty()) {
				sb.append(" and bname like '%" + book.getBname() + "%'");
			}
			//��ѧ
			if (book.getBuniversity() != null && !book.getBuniversity().isEmpty()) {
				sb.append(" and buniversity like '%" + book.getBuniversity() + "%'");
			}
			//ѧԺ
			if (book.getBucollege() != null && !book.getBucollege().isEmpty()) {
				sb.append(" and bucollege like '%" + book.getBucollege() + "%'");
			}
			//רҵ
			if (book.getBumajor() != null && !book.getBumajor().isEmpty()) {
				sb.append(" and bumajor like '%" + book.getBumajor() + "%'");
			}
			//�꼶
			if (book.getBclass() != null && !book.getBclass().isEmpty()) {
				sb.append(" and bclass like '%" + book.getBclass() + "%'");
			}
			//���
			if(book.getBnum() != 0 ){
				sb.append(" and bnum =" + book.getBnum());
			}
			//����
			if (book.getBauthor() != null && !book.getBauthor().isEmpty()) {
				sb.append(" and bauthor like '%" + book.getBauthor() + "%'");
			}
			//���
			if (book.getBtid() != 0) {
				sb.append(" and btid = " + book.getBtid());
			}
			//�۸�
			if(book.getBprice() != 0){
				sb.append(" and bprice = " + book.getBprice());
			}
			//������
			if(book.getUid() != 0){
				sb.append(" and uid = " + book.getUid());
			}
			//״̬
			if (book.getBstate() != 0) {
				sb.append(" and bstate = " + book.getBstate());
			}
			//ϵ��
			if (book.getBtemp() != null && !book.getBtemp().isEmpty()) {
				sb.append(" and btemp like '%" + book.getBtemp() + "%'");
			}
			//��id
			if (book.getBid() != 0) {
				sb.append(" and bid = " + book.getBid());
			}
			//��������
			if (book.getBdate() != null && !book.getBdate().isEmpty()) {
				sb.append(" and bdate like '%" + book.getBdate() + "%'");
			}
		}
		sb.append(" order by  bid desc");
		List<Book> list = db.selectAll(sb.toString(), null, Book.class);
		return list;
	}
	//��ѯ������¼
	@SuppressWarnings("static-access")
	public Book selectSingle(Book book) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(" select bid,bname,buniversity,bucollege,bumajor,bclass, "
				+ " bcontent,bimg,bprice,bstate,btid,btemp,uid,btemp1,bnum,bauthor,bdate" + " from book where 1=1 ");
		if (book != null) {
			//����
			if (book.getBname() != null && !book.getBname().isEmpty()) {
				sb.append(" and bname = '" + book.getBname() + "'");
			}
			//��ѧ
			if (book.getBuniversity() != null && !book.getBuniversity().isEmpty()) {
				sb.append(" and buniversity = '" + book.getBuniversity() + "'");
			}
			//ѧԺ
			if (book.getBucollege() != null && !book.getBucollege().isEmpty()) {
				sb.append(" and bucollege = '" + book.getBucollege() + "'");
			}
			//רҵ
			if (book.getBumajor() != null && !book.getBumajor().isEmpty()) {
				sb.append(" and bumajor = '" + book.getBumajor() + "'");
			}
			//�꼶
			if (book.getBclass() != null && !book.getBclass().isEmpty()) {
				sb.append(" and bclass = '" + book.getBclass() + "'");
			}
			//������
			if(book.getUid() != 0){
				sb.append(" and uid = " + book.getUid());
			}
			//����
			if (book.getBauthor() != null && !book.getBauthor().isEmpty()) {
				sb.append(" and bauthor = '" + book.getBauthor() + "'");
			}
			//���
			if (book.getBtid() !=0) {
				sb.append(" and btid = " + book.getBtid());
			}
			//�۸�
			if(book.getBprice() != 0){
				sb.append(" and bprice = " + book.getBprice());
			}
			//״̬
			if (book.getBstate() != 0) {
				sb.append(" and bstate = " + book.getBstate());
			}
			//ϵ��
			if (book.getBtemp() != null && !book.getBtemp().isEmpty()) {
				sb.append(" and btemp = '" + book.getBtemp() + "'");
			}
			//id
			if (book.getBid() != 0) {
				sb.append(" and bid = " + book.getBid());
			}
			//����ʱ��
			if (book.getBdate() != null && !book.getBdate().isEmpty()) {
				sb.append(" and bdate = '" + book.getBdate() + "'");
			}
		}
		sb.append(" order by  bid desc");
		return db.selectSingle(sb.toString(), null, Book.class);	
	}
	
	// ���
	public int insert(Book book) throws SQLException  {
		String sql = "insert into book(bid,bname,buniversity,bucollege," + "bumajor,bclass,bcontent,bimg,bprice,btid,"
				+ "btemp,bnum,bauthor,bdate,uid,null,btemp1) " + " values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		return DbHelper.update(sql, book.getBname(), book.getBuniversity(), book.getBucollege(), book.getBumajor(),
				book.getBclass(), book.getBcontent(), book.getBimg(), book.getBprice(), book.getBtid(), book.getBtemp(),
				book.getBnum(), book.getBauthor(), book.getBdate(),book.getUid(),book.getBtemp1());
	}

	// ɾ��
	public int delete(Book book) throws  SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from book where 1=1 ");
		if (book != null) {
			//����
			if (book.getBname() != null && !book.getBname().isEmpty()) {
				sb.append(" and bname ='" + book.getBname() + "'");
			}
			//��ѧ
			if (book.getBuniversity() != null && !book.getBuniversity().isEmpty()) {
				sb.append(" and buniversity ='"  + book.getBuniversity() + "'");
			}
			//ѧԺ
			if (book.getBucollege() != null && !book.getBucollege().isEmpty()) {
				sb.append(" and bucollege ='"  + book.getBucollege() + "'");
			}
			//רҵ
			if (book.getBumajor() != null && !book.getBumajor().isEmpty()) {
				sb.append(" and bumajor ='"  + book.getBumajor() + "'");
			}
			//�꼶
			if (book.getBclass() != null && !book.getBclass().isEmpty()) {
				sb.append(" and bclass ='"  + book.getBclass() + "'");
			}
			//���
			if(book.getBnum() != 0){
				sb.append(" and bnum =" + book.getBnum());
			}
			//����
			if (book.getBauthor() != null && !book.getBauthor().isEmpty()) {
				sb.append(" and bauthor ='"  + book.getBauthor() + "'");
			}
			//���
			if (book.getBtid() != 0) {
				sb.append(" and btid = " + book.getBtid());
			}
			//������
			if (book.getUid() != 0) {
				sb.append(" and uid = " + book.getUid());
			}
			//�۸�
			if(book.getBprice() != 0){
				sb.append(" and bprice = " + book.getBprice());
			}
			//״̬
			if (book.getBstate() != 0) {
				sb.append(" and bstate = " + book.getBstate());
			}
			//ϵ��
			if (book.getBtemp() != null && !book.getBtemp().isEmpty()) {
				sb.append(" and btemp ='"  + book.getBtemp() + "'");
			}
			//��id
			if (book.getBid() != 0) {
				sb.append(" and bid = " + book.getBid());
			}
			//����ʱ��
			if (book.getBdate() != null && !book.getBdate().isEmpty()) {
				sb.append(" and bdate ='"  + book.getBdate() + "'");
			}
		}
		return db.update(sb.toString(), null);
	}

	// ɾ����������
	@SuppressWarnings("static-access")
	public int delete(List<Book> list) throws SQLException  {
		StringBuffer sb = null;
		if (list.size() == 0) {
			return 0;
		}
		// �������е����ݿ����
		List<String> sqList = new ArrayList<String>();
		for (Book book : list) {
			sb = new StringBuffer();
			sb.append("delete from book where 1=1 ");
			if (book != null) {
				if (book.getBid() != 0) {
					sb.append(" and bid = " + book.getBid());
				}
				if (book.getBdate() != null && !book.getBdate().isEmpty()) {
					sb.append(" and bdate ='"  + book.getBdate() + "'");
				}
				sqList.add(sb.toString());
			}
		}
		return db.update(sqList);
	}

	// ����
	public int update(Book bookNew, Book bookOld) throws SQLException  {
		StringBuffer sb = new StringBuffer();
		if (bookNew == null || bookOld == null) {
			return 0;
		}
		sb.append("update book set btemp1='' ");
		//����
		if (bookNew.getBname() != null && !bookNew.getBname().isEmpty()) {
			sb.append(" ,bname = '" + bookNew.getBname() + "'");
		}
		//��ѧ
		if (bookNew.getBuniversity() != null && !bookNew.getBuniversity().isEmpty()) {
			sb.append(" ,buniversity =  '" + bookNew.getBuniversity() + "'");
		}
		//ѧԺ
		if (bookNew.getBucollege() != null && !bookNew.getBucollege().isEmpty()) {
			sb.append(" ,bucollege =  '" + bookNew.getBucollege() + "'");
		}
		//רҵ
		if (bookNew.getBumajor() != null && !bookNew.getBumajor().isEmpty()) {
			sb.append(" ,bumajor =  '" + bookNew.getBumajor() + "'");
		}
		//�꼶
		if (bookNew.getBclass() != null && !bookNew.getBclass().isEmpty()) {
			sb.append(" ,bclass =  '" + bookNew.getBclass() + "'");
		}
		//�����
		if (bookNew.getBtid() != 0 ) {
			sb.append(" ,btid = " + bookNew.getBtid());
		}
		//ϵ��
		if (bookNew.getBtemp() != null && !bookNew.getBtemp().isEmpty()) {
			sb.append(" ,btemp = '" + bookNew.getBtemp() + "'");
		}
		//����
		if (bookNew.getBauthor() != null && !bookNew.getBauthor().isEmpty()) {
			sb.append(" ,bauthor =  '" + bookNew.getBauthor() + "'");
		}
		//�۸�
		if(bookNew.getBprice()!=0){
			sb.append(" ,bprice = " + bookNew.getBprice());
		}
		//���
		if(bookNew.getBnum()!=0){
			sb.append(" ,bnum = " + bookNew.getBnum());
		}
		//ͼƬ
		if(bookNew.getBimg()!=null && !bookNew.getBimg().isEmpty()){
			sb.append(" ,bimg = '" + bookNew.getBimg()+"'");
		}
		//����
		if(bookNew.getBcontent()!=null && !bookNew.getBcontent().isEmpty()){
			sb.append(" ,bcontent = '" + bookNew.getBcontent()+"'");
		}
		//״̬
		if (bookNew.getBstate() != 0) {
			sb.append(" ,bstate = " + bookNew.getBstate());
		}
		//��������
		if (bookNew.getBdate() != null && !bookNew.getBdate().isEmpty()) {
			sb.append(" ,bdate = '" + bookNew.getBdate()+"'");
		}
		//�����ֶ�
		if(bookNew.getBtemp1() != null && !bookNew.getBtemp1().isEmpty()){
			sb.append(" ,btemp1 = '" + bookNew.getBtemp1()+"'");
		}
		//����
		if(bookNew.getBcontent() != null && !bookNew.getBcontent().isEmpty()){
			sb.append(" ,bcontent = '" + bookNew.getBcontent()+"'");
		}
		sb.append(" where 1=1 ");
		
		if (bookOld.getBname() != null && !bookOld.getBname().isEmpty()) {
			sb.append(" and bname =  '" + bookOld.getBname() + "'");
		}
		if (bookOld.getBauthor() != null && !bookOld.getBauthor().isEmpty()) {
			sb.append(" and bauthor =  '" + bookOld.getBauthor() + "'");
		}
		if (bookOld.getBtid() !=0) {
			sb.append(" and btid = " + bookOld.getBtid());
		}
		if (bookOld.getBstate() != 0) {
			sb.append(" and bstate = " + bookOld.getBstate());
		}
		if (bookOld.getBid() != 0) {
			sb.append(" and bid = " + bookOld.getBid());
		}
		if(bookOld.getUid() != 0){
			sb.append(" and uid = " + bookOld.getUid());
		}
		return db.update(sb.toString(), null);
		
	}
	//book��ҳ
	@SuppressWarnings({ "static-access", "unchecked" })
	public Page<Book> bookPage(int page, int rows,Book book) throws IOException {
		StringBuffer sb = new StringBuffer();
    	sb.append("select * from book where 1=1 ");
    	if(book != null){
    		//����
			if (book.getBname() != null && !book.getBname().isEmpty()) {
				sb.append(" and bname ='" + book.getBname() + "'");
			}
			//��ѧ
			if (book.getBuniversity() != null && !book.getBuniversity().isEmpty()) {
				sb.append(" and buniversity ='"  + book.getBuniversity() + "'");
			}
			//ѧԺ
			if (book.getBucollege() != null && !book.getBucollege().isEmpty()) {
				sb.append(" and bucollege ='"  + book.getBucollege() + "'");
			}
			//רҵ
			if (book.getBumajor() != null && !book.getBumajor().isEmpty()) {
				sb.append(" and bumajor ='"  + book.getBumajor() + "'");
			}
			//�꼶
			if (book.getBclass() != null && !book.getBclass().isEmpty()) {
				sb.append(" and bclass ='"  + book.getBclass() + "'");
			}
			//����
			if (book.getBauthor() != null && !book.getBauthor().isEmpty()) {
				sb.append(" and bauthor ='"  + book.getBauthor() + "'");
			}
			//���
			if (book.getBtid() != 0) {
				sb.append(" and btid = " + book.getBtid());
			}
			//������
			if (book.getUid() != 0) {
				sb.append(" and uid = " + book.getUid());
			}
		}
    	sb.append(" order by  bid asc");
    	return  db.selectPageForMysql(page, rows, Book.class, sb.toString());
	}
	//book��ҳ
		@SuppressWarnings({ "static-access", "unchecked" })
		public Page<Book> bookChildPage(int page, int rows,BookChild book) throws IOException {
			StringBuffer sb = new StringBuffer();
			sb.append(" select bid,bname,buniversity,bucollege,bumajor,bclass, "
					+ " bcontent,bimg,bprice,bstate,b.btid,btemp,btemp1,bnum,bauthor,bdate,uid,btname,btnamesecond,btnamethird" + " from book b,booktype bt where 1=1 and b.btid = bt.btid ");
	    	if(book != null){
	    		//�����
				if(book.getBtname() != null && !book.getBtname().isEmpty()){
					sb.append(" and btname = '"+book.getBtname()+"'");
				}
				//���id
				if(book.getBtid() != 0){
					sb.append(" and b.btid = "+book.getBtid());
				}
				//��ѧ
				if (book.getBuniversity() != null && !book.getBuniversity().isEmpty()) {
					sb.append(" and buniversity = '" + book.getBuniversity() + "'");
				}
				//ѧԺ
				if (book.getBucollege() != null && !book.getBucollege().isEmpty()) {
					sb.append(" and bucollege = '" + book.getBucollege() + "'");
				}
				//רҵ
				if (book.getBumajor() != null && !book.getBumajor().isEmpty()) {
					sb.append(" and bumajor = '" + book.getBumajor() + "'");
				}
				//�꼶
				if (book.getBclass() != null && !book.getBclass().isEmpty()) {
					sb.append(" and bclass = '" + book.getBclass() + "'");
				}
				//������
				if(book.getUid() != 0){
					sb.append(" and uid = "+book.getUid());
				}
			}
	    	sb.append(" order by  bid asc");
	    	return  db.selectPageForMysql(page, rows, Book.class, sb.toString());
		}
	// ����
}
