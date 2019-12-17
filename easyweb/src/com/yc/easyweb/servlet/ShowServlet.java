package com.yc.easyweb.servlet;

/**
 * �ۺ���Ϣչʾ��servlet
 */
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.easyweb.bean.*;
import com.yc.easyweb.biz.*;

public class ShowServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private UserBiz userBiz = new UserBiz();
	private BookBiz bookBiz = new BookBiz();
	private EorderBiz eorderBiz = new EorderBiz();
	private NoticeBiz noticeBiz = new NoticeBiz();
	private Result result;

	// homeҳ�����Ϣչʾ��ѯ
	public void queryHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// ��ȡ�û���Ϣ
		User user = new User();
		try {
			// ��ȡuser��������Ϣ�洢�ڻỰ��
			List<User> userListAll = userBiz.selectAll(user);
			session.setAttribute("userAll", userListAll);// �洢��user���������Ϣ

			user.setUstate(1);
			List<User> userListExit = userBiz.selectAll(user);
			List<User> customerExit = userBiz.selectAll(user);// �洢�����û���Ϣ
			session.setAttribute("userExit", userListExit);// �洢user�����д��ڵ��û���Ϣ
			// �洢����Ա������Ϣ
			user.setUtype(1);
			List<User> adminListAll = userBiz.selectAll(user);
			user.setUtype(5);
			List<User> adminList = userBiz.selectAll(user);
			if (adminList.size() != 0) {
				for (User u : adminList) {
					adminListAll.add(u);
				}
			}
			session.setAttribute("adminExit", adminListAll);// �洢���д��ڵĹ���Ա��Ϣ
			// �洢�����û���Ϣ
			for (int i = 0; i < customerExit.size(); i++) {
				for (int j = 0; j < adminListAll.size(); j++) {
					if (customerExit.get(i).equals(adminListAll.get(j))
							&& customerExit.get(i).hashCode() == adminListAll.hashCode()) {
						customerExit.remove(i);
						// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
						i--;
					}
				}
			}
			session.setAttribute("customerExit", customerExit);// �洢���д��ڵ��û���Ϣ

			// ��ȡ�鼮��Ϣ

			Book book = new Book();
			List<Book> bookAll = bookBiz.selectAll(book);
			book.setBstate(1);
			List<Book> bookExit;

			bookExit = bookBiz.selectAll(book);
			session.setAttribute("bookExit", bookExit);// �洢�����ϼܵ��鼮
			session.setAttribute("bookAll", bookAll);// �洢�����鼮

			// ��ȡȫ��������

			Eorder eorder = new Eorder();
			OrderDetial eorder1 = new OrderDetial();
			String[] date = (String[]) session.getAttribute("date");
			eorder.setEotime(date[0]);
			eorder1.setEotime(date[0]);
			List<OrderDetial> eorderList;
			eorderList = eorderBiz.selectAllDetail(eorder1);
			List<Eorder> eorders = eorderBiz.selectAll(eorder);
			session.setAttribute("eorderSuccess", eorderList);// �洢���еĶ�����Ϣ
			session.setAttribute("eorderAll", eorders);// �洢���еĶ�����Ϣ
			double num = 0.0;
			if (eorderList.size() != 0) {
				for (OrderDetial oShow : eorderList) {
					num = num + oShow.getTotal();// �洢�����ܶ�
				}
			}
			session.setAttribute("eorderTotal", num);// �洢ÿһ�������ܼ�
			// eorderList������ȫ������
			// ״̬1.������2.������3.�ѷ���4.�˻�������5.�˿�ɹ�6.�ѽ���7.�˻�ʧ��
			long[] count = { 0, 0, 0, 0, 0 };
			for (Eorder eo : eorders) {
				if (eo.getEostate() == 1) {
					// �����㶩��eotype==1
					count[2] = count[2] + 1;
				} else if (eo.getEostate() == 2) {
					// ����������eostate == 2
					count[1] = count[1] + 1;
				} else if (eo.getEostate() == 4) {
					// δ������eostate == 4
					count[0] = count[0] + 1;
				} else if (eo.getEostate() == 6 || eo.getEostate() == 3) {
					// �ѳɽ�������eostate == 6
					count[3] = count[3] + 1;
				} else if (eo.getEostate() == 5) {
					// ����ʧ��eostate == 5
					count[4] = count[4] + 1;
				}
			}
			session.setAttribute("eorderCount", count);// �洢ÿһ״̬��ֵ
			// bookList�洢�����鼮��Ϣ
			// bstate;//״̬(1���ã�2.ɾ��3.����)
			long[] bookCount = { 0, 0, 0 };
			for (Book book1 : bookAll) {
				if (book1.getBstate() == 3) {
					bookCount[0] = bookCount[0] + 1;
				} else if (book1.getBstate() == 1) {
					bookCount[1] = bookCount[1] + 1;
				} else if (book1.getBstate() == 2) {
					bookCount[2] = bookCount[2] + 1;
				}
			}
			session.setAttribute("bookCount", bookCount);// �洢ÿһ״̬��ֵ

			// ����չʾ
			Notice notice = new Notice();
			List<Notice> nList = noticeBiz.selectAll(notice);
			List<Notice> nShow = new ArrayList<Notice>();
			session.setAttribute("noticeAll", nList);// �洢���й���
			if (nList.size() != 0) {
				for (int i = 0; i < nList.size(); i++) {
					if (i == 6) {
						break;
					}
					nShow.add(nList.get(i));
				}
			}
			// �洢���µ���������չʾ����
			session.setAttribute("noticeShow", nShow);
		} catch (BizException e) {
			result = Result.error(e.getMessage());
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
		}
	}

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
