package com.yc.easyweb.servlet.pq;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.bean.Usercontrol;
import com.yc.easyweb.biz.UsercontrolBiz;
import com.yc.easyweb.common.DbHelper;
import com.yc.easyweb.servlet.BaseServlet;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/JoinServlet.s")
public class JoinServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求对象的字符集编码，可以兼容中文参数，避免乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		// 接收 用户名 和 密码
		String username = request.getParameter("uname");
		String password = request.getParameter("upassword");
		String loginkeeping = request.getParameter("loginkeeping");
		// 获取验证码
		String vcode01 = (String) request.getSession().getAttribute("vcode");
		String vcode02 = request.getParameter("vcode");
		String sql = "select * from user where uname=? and upassword=?";
		List<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(password);
		User user = DbHelper.selectSingle(sql, params, User.class);
		// 应用上下文对象存储用户信息
		ServletContext userContext = getServletContext();
		request.setAttribute("msg", "");
		userContext.setAttribute("user", user);
		if (!vcode01.equalsIgnoreCase(vcode02)) {
			PrintWriter out = response.getWriter();
			out.print("验证码错误!");
			return;
		} else if (user.getUid() != 0) {
			if (loginkeeping != null) {
				HttpSession session = request.getSession();
				// 创建一个cookie对象
				Cookie cookie = new Cookie("loginedUsername", URLEncoder.encode(username, "GBK")); // 默认为临时Cookie,MaxAge<0
				// 解决Cookie存中文的乱码问题
				Cookie cookie01 = new Cookie("loginedPassword", password);
				// 设置有效时间 1分钟f
				cookie.setMaxAge(60);
				cookie01.setMaxAge(60);
				// 将cookie添加到响应对象中
				response.addCookie(cookie);
				response.addCookie(cookie01);
				request.getSession().setAttribute("loginedUser", user);
				// 获取系统当前时间
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
				Date date = new Date();
				String[] dateStr = df.format(date).split("/");
				//将登录时间放入会话中
				request.getSession().setAttribute("date", dateStr);
				String path = this.getServletContext().getContextPath();
				//将项目名字放入会话中
				request.getSession().setAttribute("path",path);
				String[] userType = {"","","普通用户","普通会员","钻石会员"};
				String[] uType = {"","","2-普通用户","3-普通会员","4-钻石会员"};
				session.setAttribute("userType", userType);
				session.setAttribute("uType", uType);
				String[] userSex = {"保密","男","女"};
				session.setAttribute("userSex", userSex);
				String[] adminState = {"","已启用","已冻结","已删除"};
				session.setAttribute("adminStateC", adminState);// 存储所有存在的管理员信息
				String[] bookState = {"未上架","已上架","已下架","售罄","审核不通过","未审核"};
				session.setAttribute("bookState", bookState);
				String[] eorderState = {"","待付款","待发货","已发货","待处理","已退款","已接收","退货失败"};
				session.setAttribute("eoderState", eorderState);
				
				String[] eorderMessage = {"","等待支付","等待发货","等待揽件","等待处理","退款成功","买家已揽件","条件不符合"};
				session.setAttribute("eoderMessage", eorderMessage);
				
				if (user.getUtype() != 1 && user.getUtype() != 5) {
					PrintWriter out = response.getWriter();
					out.print("用户登录成功!");
				} else {
					PrintWriter out = response.getWriter();
					String adminType = null;
					if(user.getUtype() == 1){
			 			adminType = "超级管理员";
			 		}else{
			 			adminType = "普通管理员";
			 		}
					//页头展示
					 User loginUser = (User)session.getAttribute("loginedUser");
					 Usercontrol usercontrolOld = new Usercontrol();
					 List<Usercontrol> controlList = null;
					 List<Long> conList = new ArrayList<Long>();
					 UsercontrolBiz usercontrolBiz = new UsercontrolBiz();
				 	if(loginUser != null){
				 		if(loginUser.getUid() != 0){
				 			usercontrolOld.setUid(loginUser.getUid());
				 			controlList = usercontrolBiz.selectAll(usercontrolOld);
				 		}
				 	}
				 	if(controlList != null){
				 		for(Usercontrol ucon : controlList){
				 			conList.add(ucon.getConid());
				 		}
				 	}
					session.setAttribute("adminControl", conList);
					request.getSession().setAttribute("adminType", adminType);
					out.print("管理员登录成功!");
				}
			}
		} else if (user.getUid() == 0) {
			PrintWriter out = response.getWriter();
			out.print("用户名或密码错误!");
		}
	}
}
