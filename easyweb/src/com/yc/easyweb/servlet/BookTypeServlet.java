package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.easyweb.bean.*;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookTypeBiz;

public class BookTypeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookTypeBiz bookTypeBiz = new BookTypeBiz();

	// ��ѯ
	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, BizException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		BookType bookType = new BookType();
		BookTypeBiz btBiz = new BookTypeBiz();
		List<BookType> btList = btBiz.selectAll(bookType);
		HashSet<String> btType = new HashSet<String>();
		for(BookType bt : btList){
			if(bt.getBtnamethird() != null && !bt.getBtnamethird() .isEmpty()){
				btType.add(bt.getBtid()+"-"+bt.getBtname()+"-"+bt.getBtnamesecond()+"-"+bt.getBtnamethird()+"-"+bt.getBtstate());
			}else if(bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()){
				btType.add(bt.getBtid()+"-"+bt.getBtname()+"-"+bt.getBtnamesecond()+"-"+bt.getBtstate());
			}else{
				btType.add(bt.getBtid()+"-"+bt.getBtname()+"-"+bt.getBtstate());
			}
		}
		session.setAttribute("bookTypeShow", btType);
		if(btType.size() ==0 ){
			out.print(0);
		}
	}

	// ���
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, BizException {
		BookType bookType = new BookType();
		PrintWriter out = response.getWriter();
		String realType = null;
		if (request.getParameter("namefrist") != null && !request.getParameter("namefrist").isEmpty()) {
			bookType.setBtname(request.getParameter("namefrist"));
			realType = request.getParameter("namefrist");
		}
		if (request.getParameter("namesecond") != null && !request.getParameter("namesecond").isEmpty()) {
			bookType.setBtnamesecond(request.getParameter("namesecond"));
			realType = request.getParameter("namesecond");
		}
		if (request.getParameter("namethird") != null && !request.getParameter("namethird").isEmpty()) {
			bookType.setBtnamethird(request.getParameter("namethird"));
			realType = request.getParameter("namethird");
		}
		// ��ѯ���ݿ������
		BookType bookType1 = new BookType();
		bookType1.setBtstate(1);
		List<BookType> btList = bookTypeBiz.selectAll(bookType1);
		HashSet<String> btType = new HashSet<String>();
		for (BookType bt : btList) {
			if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
				btType.add(bt.getBtnamethird());
			} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
				btType.add(bt.getBtnamesecond());
			} else {
				btType.add(bt.getBtname());
			}
		}
		int lengthOld = btType.size();
		btType.add(realType);
		try {
			if (lengthOld != btType.size()) {
				int code = bookTypeBiz.insert(bookType);
				if (code > 0) {
					out.print("��ӳɹ�������");
				} else {
					out.print("���ʧ�ܣ�����");
				}
			} else {
				out.print("�Ѵ��ڸ����ͣ�����");
			}
		} catch (BizException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ɾ��
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookType bookTypeOld = new BookType();
		PrintWriter out = response.getWriter();
		if (request.getParameter("btid") != null && !request.getParameter("btid").isEmpty()) {
			bookTypeOld.setBtid(Long.parseLong(request.getParameter("btid")));
		} else {
			out.print("��ѡ��ɾ����������������");
			return;
		}
		try {
			int code = bookTypeBiz.delete(bookTypeOld);
			if (code > 0) {
				out.print("ɾ���ɹ�������");
			} else {
				out.print("ɾ��ʧ�ܣ�����");
			}
		} catch (BizException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ����
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, BizException {
		BookType bookType = new BookType();
		BookType bookTypeOld = new BookType();
		PrintWriter out = response.getWriter();
		String realType = null;
		if (request.getParameter("btid") != null && !request.getParameter("btid").isEmpty()) {
			bookTypeOld.setBtid(Long.parseLong(request.getParameter("btid")));
		} else {
			out.print("��ѡ���޸ĵ�������������");
			return;
		}
		if (request.getParameter("namefrist") != null && !request.getParameter("namefrist").isEmpty()) {
			bookType.setBtname(request.getParameter("namefrist"));
			realType = request.getParameter("namefrist");
		}
		if (request.getParameter("state") != null && !request.getParameter("state").isEmpty()) {
			bookType.setBtstate(Integer.parseInt(request.getParameter("state")));;
		}
		if (request.getParameter("namesecond") != null && !request.getParameter("namesecond").isEmpty()) {
			bookType.setBtnamesecond(request.getParameter("namesecond"));
			realType = request.getParameter("namesecond");
		}
		if (request.getParameter("namethird") != null && !request.getParameter("namethird").isEmpty()) {
			bookType.setBtnamethird(request.getParameter("namethird"));
			realType = request.getParameter("namethird");
		}
		// ��ѯ���ݿ������
		BookType bookType1 = new BookType();
		bookType1.setBtstate(1);
		List<BookType> btList = bookTypeBiz.selectAll(bookType1);
		HashSet<String> btType = new HashSet<String>();
		for (BookType bt : btList) {
			if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
				btType.add(bt.getBtnamethird());
			} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
				btType.add(bt.getBtnamesecond());
			} else {
				btType.add(bt.getBtname());
			}
		}
		int lengthOld = btType.size();
		btType.add(realType);
		try {
			if (lengthOld != btType.size()) {
				int code = bookTypeBiz.update(bookType, bookTypeOld);
				if (code > 0) {
					out.print("���³ɹ�������");
				} else {
					out.print("����ʧ�ܣ�����");
				}
			} else {
				bookType.setBtstate(1);
				int code = bookTypeBiz.update(bookType, bookTypeOld);
				if (code > 0) {
					out.print("���³ɹ�������");
				} else {
					out.print("����ʧ�ܣ�����");
				}
			}
		} catch (BizException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
