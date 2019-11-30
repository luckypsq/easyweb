<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.easyweb.bean.User"%>
<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%

	String oldpassword = request.getParameter("oldpassword");
	String sql = "select upassword from user where uid=? ";
	User user =(User)session.getAttribute("loginedUser");
	System.out.print(user.getUid());
	Object password = user.getUpassword();
	System.out.print(password);
	if(oldpassword.equals(password)){
		// 在 jsp 脚本中输出信息   ， out 对象用于在 jsp 脚本中属性信息
		out.print("原密码正确！");
		System.out.print("原密码正确！");
	} else {
		out.print("原密码错误！");
		
		System.out.print("原密码错误！");
	}
%>