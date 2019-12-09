package com.yc.easyweb.servlet;

/**
 * 综合信息展示的servlet
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

	// home页面的信息展示查询
	public void queryHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 获取用户信息
		UserBiz userBiz = new UserBiz();
		User user = new User();
		// 获取user表所有信息存储在会话中
		List<User> userListAll = userBiz.selectAll(user);
		session.setAttribute("userAll", userListAll);// 存储有user表的所有信息

		user.setUstate(1);
		List<User> userListExit = userBiz.selectAll(user);
		List<User> customerExit = userBiz.selectAll(user);// 存储所有用户信息
		session.setAttribute("userExit", userListExit);// 存储user表所有存在的用户信息
		// 存储管理员所有信息
		user.setUtype(1);
		List<User> adminListAll = userBiz.selectAll(user);
		user.setUtype(5);
		List<User> adminList = userBiz.selectAll(user);
		if (adminList.size() != 0) {
			for (User u : adminList) {
				adminListAll.add(u);
			}
		}
		session.setAttribute("adminExit", adminListAll);// 存储所有存在的管理员信息
		// 存储所有用户信息
		for (int i = 0; i < customerExit.size(); i++) {
			for (int j = 0; j < adminListAll.size(); j++) {
				if (customerExit.get(i).equals(adminListAll.get(j))
						&& customerExit.get(i).hashCode() == adminListAll.hashCode()) {
					customerExit.remove(i);
					// 此时需注意，因为list会动态变化不像数组会占位，所以当前索引应该后退一位
					i--;
				}
			}
		}
		session.setAttribute("customerExit", customerExit);// 存储所有存在的用户信息

		// 获取书籍信息
		BookBiz bookBiz = new BookBiz();
		Book book = new Book();
		List<Book> bookAll = bookBiz.selectAll(book);
		book.setBstate(1);
		List<Book> bookExit = bookBiz.selectAll(book);
		session.setAttribute("bookExit", bookExit);// 存储所有上架的书籍
		session.setAttribute("bookAll", bookAll);// 存储所有书籍

		// 获取全部订单数
		EorderBiz eorderBiz = new EorderBiz();
		Eorder eorder = new Eorder();
		OrderDetial eorder1 = new OrderDetial();
		String[] date = (String[]) session.getAttribute("date");
		eorder.setEotime(date[0]);
		eorder1.setEotime(date[0]);
		List<OrderDetial> eorderList;
		eorderList = eorderBiz.selectAllDetail(eorder1);
		List<Eorder> eorders = eorderBiz.selectAll(eorder);
		session.setAttribute("eorderSuccess", eorderList);// 存储所有的订单信息
		session.setAttribute("eorderAll", eorders);// 存储所有的订单信息
		double num = 0.0;
		if (eorderList.size() != 0) {
			for (OrderDetial oShow : eorderList) {
				num = num + oShow.getTotal();// 存储销售总额
			}
		}
		session.setAttribute("eorderTotal", num);// 存储每一年销售总价
		// eorderList保存着全部订单
		// 状态1.待付款2.待发货3.已发货4.退货申请中5.退款成功6.已接收7.退货失败
		long[] count = { 0, 0, 0, 0, 0 };
		for (Eorder eo : eorders) {
			if (eo.getEostate() == 1) {
				// 待结算订单eotype==1
				count[2] = count[2] + 1;
			} else if (eo.getEostate() == 2) {
				// 待发货订单eostate == 2
				count[1] = count[1] + 1;
			} else if (eo.getEostate() == 4) {
				// 未处理订单eostate == 4
				count[0] = count[0] + 1;
			} else if (eo.getEostate() == 6 || eo.getEostate() == 3) {
				// 已成交订单数eostate == 6
				count[3] = count[3] + 1;
			} else if (eo.getEostate() == 5) {
				// 交易失败eostate == 5
				count[4] = count[4] + 1;
			}
		}
		session.setAttribute("eorderCount", count);// 存储每一状态数值
		// bookList存储所有书籍信息
		// bstate;//状态(1可用，2.删除3.售罄)
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
		session.setAttribute("bookCount", bookCount);// 存储每一状态数值

		// 公告展示
		NoticeBiz noticeBiz = new NoticeBiz();
		Notice notice = new Notice();
		List<Notice> nList = noticeBiz.selectAll(notice);
		List<Notice> nShow = new ArrayList<Notice>();
		session.setAttribute("noticeAll", nList);// 存储所有公告
		if (nList.size() != 0) {
			for (int i = 0; i < nList.size(); i++) {
				if (i == 6) {
					break;
				}
				nShow.add(nList.get(i));
			}
		}
		// 存储最新的六个公告展示出来
		session.setAttribute("noticeShow", nShow);
	}
}
