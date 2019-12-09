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

import com.yc.easyweb.bean.*;
import com.yc.easyweb.biz.*;

public class ShowServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	// homeҳ�����Ϣչʾ��ѯ
	public void queryHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// ��ȡ�û���Ϣ
		UserBiz userBiz = new UserBiz();
		User user = new User();
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
		BookBiz bookBiz = new BookBiz();
		Book book = new Book();
		List<Book> bookAll = bookBiz.selectAll(book);
		book.setBstate(1);
		List<Book> bookExit = bookBiz.selectAll(book);
		session.setAttribute("bookExit", bookExit);// �洢�����ϼܵ��鼮
		session.setAttribute("bookAll", bookAll);// �洢�����鼮

		// ��ȡȫ��������
		EorderBiz eorderBiz = new EorderBiz();
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
				bookCount[0] = bookCount[0]+ 1;
			} else if (book1.getBstate() == 1) {
				bookCount[1] = bookCount[1]+ 1;
			} else if (book1.getBstate() == 2) {
				bookCount[2] = bookCount[2]+ 1;
			}
		}
		session.setAttribute("bookCount", bookCount);// �洢ÿһ״̬��ֵ

		// ����չʾ
		NoticeBiz noticeBiz = new NoticeBiz();
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
	}
}
