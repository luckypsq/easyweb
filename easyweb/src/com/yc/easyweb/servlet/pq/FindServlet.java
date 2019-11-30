package com.yc.easyweb.servlet.pq;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;
import com.yc.easyweb.servlet.BaseServlet;

/**
 * Servlet implementation class FindServlet
 */
@WebServlet("/FindServlet.s")
public class FindServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
  
	public void checkNext(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");  
	   	 response.setContentType("text/html; charset=UTF-8");
	   	String uname=request.getParameter("uname");
	   	String uemail=request.getParameter("uemail");
	   	//获取验证码
	   	String vcode1 =request.getParameter("vcode");
	   	String vcode2 = (String) request.getSession().getAttribute("vcode");
	   	   String sql="select * from user where uname=? and uemail=?";
	   	   List<Object> params=new ArrayList<Object>();
	          params.add(uname);
	          params.add(uemail);
	          User user;
			try {
				user = DbHelper.selectSingle(sql, params, User.class);
				System.out.println(user.toString());
				if(user.getUid()!=0 && vcode1.equals(vcode2)) {
					
						PrintWriter out = response.getWriter();
						out.print("验证成功!");				
				}
				if(!vcode1.equals(vcode2)) {
					PrintWriter out = response.getWriter();
					out.print("验证码错误!");
					return ;
				}else {
					PrintWriter out = response.getWriter();
					out.print("用户名或邮箱地址错误!");
				}	
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   	   

		}
	public void checkPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
		  response.setContentType("text/html; charset=UTF-8");
		  String upassword =request.getParameter("upassword");
		   try {
			    String regpassword="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
			    if(!upassword.matches(regpassword)){
			      
			      PrintWriter out = response.getWriter();
				  out.print("密码不合法！");
				   
			    } else{
				   
				   PrintWriter out = response.getWriter();
				   out.print("该密码可以使用！");
			   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}
	public void checkPassword01(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");  
	  	  response.setContentType("text/html; charset=UTF-8");
	  	
	  	  String passwordsignup_confirm=request.getParameter("passwordsignup_confirm");
	    	String upassword=request.getParameter("upassword");
			try {
				
		    	   if(upassword.equals(passwordsignup_confirm)){ 
		    		   PrintWriter out = response.getWriter();
		    		   out.print("密码一致！");
		    		   
		    	   }else{
		    		   
		    		   PrintWriter out = response.getWriter();
		    		   out.print("密码不一致！");
		    	   }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void checkFinish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8");  
		  response.setContentType("text/html; charset=UTF-8");
		  String upassword=request.getParameter("upassword");
		  String uemail=request.getParameter("uemail");
		  String uname=request.getParameter("uname");
		  System.out.println(upassword);
		  System.out.println(uemail);
		  
		  String sql="update user set upassword=? where uemail=? and uname=?";
		  try {
			int code =  DbHelper.update(sql,upassword,uemail,uname);
			  if(code>=1){
			   
			  PrintWriter out = response.getWriter();
			  out.print("密码重置成功！");}
			  else{
				  PrintWriter out = response.getWriter();
				  out.print("重置失败!");
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	//获取中文参数或英文参数
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
