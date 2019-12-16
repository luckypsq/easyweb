package com.yc.easyweb.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.easyweb.bean.*;
import com.yc.easyweb.biz.*;

public class JoinServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBiz();
	private NoticeBiz noticeBiz = new NoticeBiz();
	private BookBiz bookBiz = new BookBiz();
	private BookTypeBiz btBiz = new BookTypeBiz();
	private PayTypeBiz payTypeBiz = new PayTypeBiz();
	private EorderitemBiz eorderitemBiz = new EorderitemBiz();
	private EorderBiz eorderBiz = new EorderBiz();
	private Gson gson = new Gson();
	private Result result;

	public void join(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		/*
		 * // 获取验证码 String vcode01 = (String) session.getAttribute("vcode");
		 * String vcode02 = request.getParameter("vcode"); if(vcode01 != null &&
		 * !vcode01.isEmpty() && !vcode02.isEmpty() && vcode02 != null){ if
		 * (!vcode01.equalsIgnoreCase(vcode02)) { out.print(-2);//验证码错误 return;
		 * } }
		 */

		// 接收 用户名 和 密码
		String username = request.getParameter("uname").trim();
		String password = request.getParameter("upassword").trim();
		String loginkeeping = request.getParameter("loginkeeping").trim();// 验证码
		session.setAttribute("loginUname", username);
		User user = new User();
		try {
			// 添加用户名条件
			if (username != null && !username.isEmpty()) {
				user.setUname(username);
			} else {
				result = Result.failure("用户名为空！！！", username);
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// 添加密码条件
			if (password != null && !password.isEmpty()) {
				user.setUpassword(password);
			} else {
				result = Result.failure("密码为空！！！");
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			User userShow = userBiz.selectSingle(user);// 保存用户信息
			if (userShow.getUid() == 0) {
				// 用户名不存在
				result = Result.failure("用户名不存在！！！", username);
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			if (userShow.getUstate() != 1) {
				result = Result.failure("您已被冻结或账号被删除！！！", username);
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			session.setAttribute("loginedUser", userShow);

			String adminType = null;
			if (userShow.getUtype() == 1) {
				adminType = "超级管理员";
			} else {
				adminType = "普通管理员";
			}
			String path = this.getServletContext().getContextPath();
			// 将项目名字放入会话中
			session.setAttribute("path", path);
			request.getSession().setAttribute("adminType", adminType);
			JoinServlet joinServlet = new JoinServlet();
			joinServlet.init(request, response);
			// 保存登录
			/**
			 * Cookie cookie = new Cookie("loginedUsername",
			 * URLEncoder.encode(username, "GBK")); // 默认为临时Cookie,MaxAge<0 //
			 * 创建一个cookie对象 Cookie cookie01 = new Cookie("loginedPassword",
			 * password); // 解决Cookie存中文的乱码问题 cookie.setMaxAge(60);// 设置有效时间
			 * 1分钟f cookie01.setMaxAge(60); response.addCookie(cookie);//
			 * 将cookie添加到响应对象中 response.addCookie(cookie01);
			 * //从请求对象中获取浏览器发送回服务器的cookie数据 Cookie[]
			 * cookies=request.getCookies(); //Cookie loginedUserCookie=null;
			 * Cookie loginedPasswordCookie=null; if(cookies!=null){ for(Cookie
			 * cookie:cookies){
			 * if(cookie.getName().equalsIgnoreCase("loginedUsername")) {
			 * //解决读取中文乱码问题
			 * request.setAttribute("username",URLDecoder.decode(cookie.getValue
			 * (),"GBK")); } if("loginedPassword".equals(cookie.getName())){
			 * loginedPasswordCookie=cookie; } } }
			 */

			if (userShow.getUtype() != 1 && userShow.getUtype() != 5) {
				result = Result.success("用户登录成功！！！", username);
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			} else {
				UsercontrolBiz ucBiz = new UsercontrolBiz();
				Usercontrol usercontrolOld = new Usercontrol();
				usercontrolOld.setUid(userShow.getUid());
				List<Usercontrol> controlList = ucBiz.selectAll(usercontrolOld);

				List<Long> conList = new ArrayList<Long>();
				if (controlList != null) {
					for (Usercontrol ucon : controlList) {
						conList.add(ucon.getConid());
					}
				}
				session.setAttribute("adminControl", conList);
				result = new Result("管理员登录成功！！！", 2);
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
		} catch (BizException e) {
			result = Result.error(e.getMessage());
			String json = gson.toJson(result);
			// 返回json格式数据
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
		} catch (IOException e) {
			result = Result.error("业务繁忙,请您稍等一会儿再操作！！！");
			String json = gson.toJson(result);
			// 返回json格式数据
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		}
	}

	private void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取系统当前时间
		HttpSession session = request.getSession();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
		Date date = new Date();
		String[] dateStr = df.format(date).split("/");
		// 将登录时间放入会话中
		session.setAttribute("date", dateStr);
		// 将各种状态的类型放入会话中
		String[] userType = { "", "", "普通用户", "普通会员", "钻石会员" };
		String[] uType = { "", "", "2-普通用户", "3-普通会员", "4-钻石会员" };
		session.setAttribute("userType", userType);
		session.setAttribute("uType", uType);
		String[] userSex = { "保密", "男", "女" };
		session.setAttribute("userSex", userSex);
		String[] adminState = { "", "已启用", "已冻结", "已删除" };
		session.setAttribute("adminStateC", adminState);// 存储所有存在的管理员信息
		String[] bookState = { "未上架", "已上架", "已下架", "售罄", "审核不通过", "未审核" };
		session.setAttribute("bookState", bookState);
		//管理员订单状态
		String[] eorderState = { "", "待付款", "待发货", "已发货", "待处理", "已退款", "已接收", "退货失败" };
		session.setAttribute("eoderState", eorderState);
		//用户订单状态
		String[] userOrder = {"", "等待支付", "等待发货", "等待接收", "等待处理", "退款成功", "已接收", "退货失败"};
		session.setAttribute("userEorderState", userOrder);
		String[] eorderMessage = { "", "等待支付", "等待发货", "等待揽件", "等待处理", "退款成功", "买家已揽件", "条件不符合" };
		session.setAttribute("eoderMessage", eorderMessage);
		// 初始化大学，学院，专业
		Book book = new Book();
		List<Book> bookList_add = bookBiz.selectAll(book);
		HashSet<String> bookUniver = new HashSet<String>();
		HashSet<String> bookUcollage = new HashSet<String>();
		HashSet<String> bookUmagor = new HashSet<String>();
		for (Book bookSet : bookList_add) {
			if (null != bookSet.getBuniversity() && !bookSet.getBuniversity().isEmpty()) {
				bookUniver.add(bookSet.getBuniversity());
			}
			if (null != bookSet.getBucollege() && !bookSet.getBucollege().isEmpty()) {
				bookUcollage.add(bookSet.getBucollege());
			}
			if (null != bookSet.getBumajor() && !bookSet.getBumajor().isEmpty()) {
				bookUmagor.add(bookSet.getBumajor());
			}
		}
		session.setAttribute("userUni", bookUniver);
		session.setAttribute("userUcol", bookUcollage);
		session.setAttribute("userUmar", bookUmagor);

		BookType bookType = new BookType();
		bookType.setBtstate(1);
		// 初始化书籍类型
		// 书籍类型
		List<BookType> bookTypes = btBiz.selectAll(bookType);
		session.setAttribute("btypes", bookTypes);
		String[] type = new String[1000];
		String btname ;
		for (BookType bt : bookTypes ) {
			if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
				btname = bt.getBtnamethird();
			} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
				btname = bt.getBtnamesecond();
			} else {
				btname = bt.getBtname();
			}
			type[(int) bt.getBtid()] = btname;
		}
		session.setAttribute("btTypeEdit", type);
		// 公告展示初始化公告
		Notice notice = new Notice();
		notice.setNstate(1);
		List<Notice> nList;
		nList = noticeBiz.selectAll(notice);
		List<Notice> nShow = new ArrayList<Notice>();
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
		Page<Notice> pageNotice = noticeBiz.noticePage(1, 3, notice);
		session.setAttribute("noticePage", pageNotice);
		// 初始化支付类型

		PayType payType = new PayType();
		payType.setEopaystate(1);
		List<PayType> payTypeList = payTypeBiz.selectAll(payType);
		session.setAttribute("payType", payTypeList);
		// list页面书籍
		Book bookList = new Book();
		bookList.setBstate(1);
		Page<Book> page2 = bookBiz.bookPage(1, 21, bookList);
		session.setAttribute("listBookPage", page2);
		// 用户index初始数据
		// 书籍展示
		BookChild bookChild = new BookChild();
		bookChild.setBstate(1);
		// 教材区
		bookChild.setBtname("教材区");
		Page<Book> pPage = bookBiz.bookChildPage(1, 12, bookChild);
		session.setAttribute("teachBook", pPage.getData());
		session.setAttribute("teachPage", pPage);

		// 工具书
		bookChild.setBtname("工具书区");
		Page<Book> pPaget = bookBiz.bookChildPage(1, 7, bookChild);
		session.setAttribute("toolBook", pPaget.getData());
		session.setAttribute("toolPage", pPaget);
		// 分享区
		bookChild.setBtid(Long.parseLong("3"));
		Page<Book> pPages = bookBiz.bookPage(1, 7, bookChild);
		session.setAttribute("shareBook", pPages.getData());
		session.setAttribute("sharePage", pPages);
		
		//用户已发布书籍展示
		User userOld = (User) session.getAttribute("loginedUser");
		Book userBook = new Book();
		Page<Book> page;
		if(userOld.getUid() != 0){
			userBook.setUid(userOld.getUid());
		}else{
			userBook.setUid(0);
		}
		page = bookBiz.bookPage(1, 5, userBook);
		session.setAttribute("userBookPage", page);
		
		//查询所有订单订单信息
		Eorder eorder = new Eorder();
		eorder.setUid(userOld.getUid());
		Page<Eorder> Page = eorderBiz.eorderPage(1, 3, eorder);
		session.setAttribute("userOrderPage", Page);
		Bought bought = new Bought();
		List<Bought> listEo = eorderitemBiz.selectAllCart(bought);
		session.setAttribute("userCart", listEo);
		
		//购物车信息显示
		bought.setCartstate(1);
		bought.setUid(userOld.getUid());
		Page<Bought> pageCart = eorderitemBiz.ePage(1, 6, bought);
		session.setAttribute("cartPage", pageCart);
	}

}
