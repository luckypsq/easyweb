package com.yc.easyweb.servlet;

/**
 * �ۺ���Ϣչʾ��servlet
 */
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.easyweb.bean.*;
import com.yc.easyweb.biz.*;

public class ShowServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private BookBiz bookBiz = new BookBiz();
	private Result result;

	/*
	 * �û�indexҳ����Ϣչʾ
	 */
	public void queryUserIndex(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			String btid1 = request.getParameter("btid1");
			String btid2 = request.getParameter("btid2");
			// �鼮չʾ
			BookChild book = new BookChild();
			book.setBstate(1);
			Book book2 = new Book();
			book2.setBstate(1);
			Page<Book> pPageTeach = new Page<Book>();
			Page<Book> pPageTool = new Page<Book>();
			int j = 1;
			int i = 1;
			// �̲���
			if (btid1 != null && !btid1.isEmpty()) {
				book2.setBtid(Long.parseLong(btid1));
				pPageTeach = bookBiz.bookPage(1, 12, book2);
				session.setAttribute("teachBook", pPageTeach.getData());
				session.setAttribute("teachPage", pPageTeach);
				if (pPageTeach.getData().size() == 0) {
					i = 0;
				}
			}
			// ��������
			if (btid2 != null && !btid2.isEmpty()) {
				book2.setBtid(Long.parseLong(btid2));
				pPageTool = bookBiz.bookPage(1, 7, book2);
				session.setAttribute("toolBook", pPageTool.getData());
				session.setAttribute("toolPage", pPageTool);
				if (pPageTool.getData().size() == 0) {
					j = 0;
				}
			}
			if (i == 0 || j == 0) {
				result = Result.failure("�������ݣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.success("�����Ѽ��أ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);

			}
			e.printStackTrace();
		}
	}

	// �˳�ϵͳ
	public void quit(HttpServletRequest request, HttpServletResponse response) {
		// ���ٻỰ����
		request.getSession().invalidate();
		result = Result.success("");
		String json = gson.toJson(result);
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().append(json);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}

	// �û��ѷ������鼮��ʾ
	public void userPublishedBookShow(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User userOld = (User) session.getAttribute("loginedUser");
		Book userBook = new Book();
		String page1 = request.getParameter("page");
		int ipage;
		Page<Book> page;
		try {
			if (page1 != null && !page1.isEmpty()) {
				ipage = Integer.parseInt(page1);
			} else {
				ipage = 1;
			}
			if (userOld.getUid() != 0) {
				userBook.setUid(userOld.getUid());
				page = bookBiz.bookPage(ipage, 5, userBook);
			} else {
				userBook.setUid(0);
				page = bookBiz.bookPage(ipage, 5, userBook);
			}
			session.setAttribute("userBookPage", page);

			if (page.getData().size() > 0) {
				result = Result.success("��ѯ�ɹ�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("�������ݣ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
	}
}
