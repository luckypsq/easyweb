package com.yc.easyweb.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.UserBiz;
import com.yc.easyweb.util.VerifyCodeUtils;

public class RegServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBiz();

	public void checkName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 User user = new User();
		 PrintWriter out = response.getWriter();
		 HttpSession session = request.getSession();
		String username = request.getParameter("username");
		if(username != null && !username.isEmpty()){
			user.setUname(username);
		}else{
			out.print(-1);//用户名输入为空
			return ;
		}
		String reg = "^[\u4e00-\u9fa5a-zA-Z]{0,20}$";
		if (!username.matches(reg)) {
			out.print(-3);
			return ;
		}
		
		try {
			User user2 = userBiz.selectSingle(user);
			if (user2.getUid() != 0) { // 说明用户名已经被使用
				out.print(-2);
			} else {
				out.print(1);//可以使用
				session.setAttribute("regUname", username);
			}
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkPhone(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 HttpSession session = request.getSession();
		String uphone = request.getParameter("uphone");
		String regphone = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		 User user = new User();
		 try {
			if(uphone != null && !uphone.isEmpty()){
				if (!uphone.matches(regphone)) {
					out.print(-2);//不合法
					return ;
				} 
				User user2 = userBiz.selectSingle(user);
				if(user2.getUid() != 0){
					out.print(-3);//已存在
					return ;
				}
				out.print(1);//可以使用
				session.setAttribute("regUphone", uphone);
			}else{
				out.print(-1);//输入为空
			}
		 } catch (BizException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	public void checkEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uemail = request.getParameter("uemail");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String regemail = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		try {
			if(uemail !=null && !uemail.isEmpty()){
				User user = new User();
				user.setUemail(uemail);
				User user2 = userBiz.selectSingle(user);
				if (!uemail.matches(regemail)) {
					out.print(-3);//邮箱格式不合法！
				} else if (user2.getUid() != 0) {
					out.print(-2);//该邮箱已被注册！
				} else {
					out.print(1);//该邮箱可以注册!
					session.setAttribute("regUemail", uemail);
				}
			}else{
				out.print(-1);//请输入
			}
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checkPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String upassword = request.getParameter("upassword");
		String regpassword = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
		if(upassword != null && !upassword.isEmpty()){
			if (!upassword.matches(regpassword)) {
				out.print(-2);//不合法
			} else {
				out.print(1);//可以使用
				session.setAttribute("regUpwd", upassword);
			}
		}else{
			out.print(-1);//输入为空
		}
		
	}

	public void checkPassword01(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String confirm = request.getParameter("passwordsignup_confirm");
		String upassword = request.getParameter("upassword");
		if(upassword == null ||  upassword.isEmpty()){
			out.print(-1);//请先输入密码！！！
			return ;
		}
		if(confirm == null ||  confirm.isEmpty()){
			out.print(-2);//请确认密码！！！
			return ;
		}
		if (upassword.equals(confirm)) {
			out.print(1);//密码一致
		} else {
			out.print(-3);//密码不一致
		}
	}

	public void checkReg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String uname = (String)session.getAttribute("regUname");
		String uphone = (String)session.getAttribute("regUphone");
		String upassword = (String)session.getAttribute("regUpwd");
		String uemail = (String)session.getAttribute("regUemail");
		
		String check = "1";
		User user = new User();
		if(uname != null && !uname.isEmpty()){
			user.setUname(uname);
			check = check +"/1";
		}else{
			check = check +"/-1";
		}
		
		if(uphone != null && !uphone.isEmpty()){
			user.setUphone(uphone);
			check = check +"/1";
		}else{
			check = check +"/-1";//电话未输入或不合法
		}
		
		if(upassword != null && !upassword.isEmpty()){
			user.setUpassword(upassword);
			check = check +"/1";
		}else{
			check = check +"/-1";//密码未输入或不合法
		}
		
		if(uemail != null && !uemail.isEmpty()){
			user.setUemail(uemail);
			check = check +"/1";
		}else{
			check = check +"/-1";//邮箱
		}
		
		if(!check.equals("1/1/1/1/1")){
			out.print(check);
			return ;
		}
		String university = request.getParameter("university");
		String ucollege = request.getParameter("ucollege");
		String umajor = request.getParameter("umajor");
		
		if(!university.equals("请选择你的大学")){
			user.setUniversity(university);
		}
		if(!ucollege.equals("请选择你的学院")){
			user.setUcollege(ucollege);
		}
		if(!umajor.equals("请选择你的专业")){
			user.setUmajor(umajor);
		}
		
		try {
			int code = userBiz.insert(user);
			if (code >= 1) {
				out.print(1);//成功
			} else {
				out.print(-5);//失败
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String path = this.getServletContext().getContextPath();
		// 随机生成验证码
		String verifyCode = VerifyCodeUtils.outputImage(response);
		// 将验证码添加到会话中，注意：在会话中保存的验证码的名称 vscode
		session.setAttribute("vcode", verifyCode);
		/**
			getOutputStream() has already been called for this response
			在JSP中输出图片，控制台会抛出该异常，必须添加以下代码，解决错误
			out.clear();
			out = pageContext.pushBody();
		*/
		String url = path+"/images";
		// 定义图片目录
		File dir = new File(url);
		// 生成图片文件
		File file = new File(dir, verifyCode + ".jpg");
		String img = verifyCode + ".jpg";
		session.setAttribute("codeImg", img);
	}
}
