package com.yc.easyweb.servlet.pq;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/JoinServlet.s")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        // 设置请求对象的字符集编码，可以兼容中文参数，避免乱码问题
		 request.setCharacterEncoding("utf-8");  
	 	  response.setContentType("text/html; charset=UTF-8");
                // 接收 用户名 和 密码
                String username = request.getParameter("uname");
                String password = request.getParameter("upassword");
                String loginkeeping = request.getParameter("loginkeeping");
                //获取验证码
                String vcode01=(String) request.getSession().getAttribute("vcode");
                String vcode02=request.getParameter("vcode");
                String sql = "select * from user where uname=? and upassword=?";
                List<Object> params=new ArrayList<Object>();
                params.add(username);
                params.add(password);
				try {
					User user=DbHelper.selectSingle(sql, params, User.class);
					//应用上下文对象存储用户信息
					ServletContext userContext = getServletContext();
					 request.setAttribute("msg", "");
					userContext.setAttribute("user", user);
					if(!vcode01.equalsIgnoreCase(vcode02)){
						PrintWriter out = response.getWriter();
						out.print("验证码错误!");
						return ;
					}
					else if (user.getUid() != 0) {
                        if(loginkeeping!=null){
							//创建一个cookie对象
                        	Cookie cookie=new Cookie("loginedUsername",URLEncoder.encode (username,"GBK")); //默认为临时Cookie,MaxAge<0 
                        	 //解决Cookie存中文的乱码问题
                        	Cookie cookie01=new Cookie("loginedPassword", password);
                        	//设置有效时间  1分钟f  
                        	cookie.setMaxAge(60);
                        	cookie01.setMaxAge(60);
                        	//将cookie添加到响应对象中
                        	response.addCookie(cookie);
                        	response.addCookie(cookie01);
                        	 request.getSession().setAttribute("loginedUser", user);
     						if(user.getUtype() != 1 && user.getUtype()!= 5){
     							PrintWriter out = response.getWriter();
     							out.print("用户登录成功!");
     						}else{
     							PrintWriter out = response.getWriter();
     							out.print("管理员登录成功!");
     						}
						}
					} else if(user.getUid()==0) {
						PrintWriter out = response.getWriter();
						out.print("用户名或密码错误!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
                

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
