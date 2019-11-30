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
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet.s")
public class RegServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
   
	public  void checkName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");  
   	  response.setContentType("text/html; charset=UTF-8");
   	String username=request.getParameter("username");
   	   String sql="select * from user where uname=?";
   	   List<Object> params=new ArrayList<Object>();
          params.add(username);
          User user;
		try {
			user = DbHelper.selectSingle(sql, params, User.class);
	    	   if(user.getUid() != 0){ //说明用户名已经被使用
	    		   PrintWriter out = response.getWriter();
	    		   out.print("该用户名已经被注册");
	    		   
	    	   }else{
	    		   PrintWriter out = response.getWriter();
	    		   out.print("该用户名可以注册！");
	    	   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	   

	}
	
	
public void checkPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 request.setCharacterEncoding("utf-8");  
 	  response.setContentType("text/html; charset=UTF-8");
 	  String uphone =request.getParameter("uphone");
 	 String sql="select * from user where uphone=?";
	 List<Object> params=new ArrayList<Object>();
    params.add(uphone);
    
    try {
		User user = DbHelper.selectSingle(sql, params, User.class);
		 String regphone="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		    if(!uphone.matches(regphone)){
		      PrintWriter out = response.getWriter();
			  out.print("电话号码不合法！");
		    }else if(user.getUid() != 0){
			  PrintWriter out = response.getWriter();
			  out.print("该电话号码已被注册！");
		   } else{
			   PrintWriter out = response.getWriter();
			   out.print("该电话号码可以注册！");
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


public void checkReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");  
 	  response.setContentType("text/html; charset=UTF-8");
	String uname=request.getParameter("uname");
	  String uphone=request.getParameter("uphone");
	  String university=request.getParameter("university");
	  String ucollege=request.getParameter("ucollege");
	  String umajor=request.getParameter("umajor");
	  String upassword=request.getParameter("upassword");
	  String uemail=request.getParameter("uemail");
	  String sql="insert into user "+
			  "values(null,?,?,?,?,?,null,?,null,null,null,?,null,null,null,null)";
	  try {
		int code =  DbHelper.update(sql,uname,uphone,university,ucollege,umajor,upassword,uemail);
		  if(code>=1){
		
		PrintWriter out = response.getWriter();
		  out.print("注册成功!");
		  }
		  else{
			  PrintWriter out = response.getWriter();
			  out.print("注册失败!");
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}


public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 request.setCharacterEncoding("utf-8");  
	  response.setContentType("text/html; charset=UTF-8");
	  String uemail =request.getParameter("uemail");
	 String sql="select * from user where uemail=?";
	 List<Object> params=new ArrayList<Object>();
   params.add(uemail);
   
   try {
		User user = DbHelper.selectSingle(sql, params, User.class);
		
		 String regemail="^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		    if(!uemail.matches(regemail)){
		      
		      PrintWriter out = response.getWriter();
			  out.print("邮箱格式不合法！");
			   
		    }else if(user.getUid() != 0){
			  PrintWriter out = response.getWriter();
			  out.print("该邮箱已被注册！");
		   } else{
			   
			   PrintWriter out = response.getWriter();
			   out.print("该邮箱可以注册!");
		   }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
   
	}




	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
