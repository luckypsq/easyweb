<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@page import="com.yc.easyweb.bean.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%	

request.setCharacterEncoding("utf-8");  
  response.setContentType("text/html; charset=UTF-8");
	  //PrintWriter out = response.getWriter();
	  System.out.println("=====");
	  String uname=request.getParameter("uname");
	  String uphone=request.getParameter("uphone");
	  String university=request.getParameter("university");
	  String ucollege=request.getParameter("ucollege");
	  String umajor=request.getParameter("umajor");
	  String upassword=request.getParameter("upassword");
	  String password_confirm=request.getParameter("password_confirm");
	  String sql="insert into user "+
			  "values(null,?,?,?,?,?,null,?,null,null,null,null,null,null,null,null)";
	try{
		User user =(User)session.getAttribute("loginedUser");
		
		int code =  DbHelper.update(sql,uname,uphone,university,ucollege,umajor,upassword);
		
		System.out.println("更新");
		// json 格式字符串
		// 定义json 返回字符串,  用于返回复杂的数据
		String json = "{uname:'"+uname
				+"', uphone:'"+uphone
				+"', university:'"+university
				+"', ucollege:'"+ucollege
				+"', umajor:'"+umajor
				+"', upassword:'"+upassword
				
				+"', msg:'注册成功！'}";   // 扩张一个结果描述信息
		System.out.print(json);
		out.print(json);
	} catch (Exception e){
		String json = "{msg:'注册成功！'}";   // 扩张一个结果描述信息
		out.print(json);
	}
%>