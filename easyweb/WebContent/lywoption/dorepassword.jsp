<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%

	String newpassword = request.getParameter("newpassword");
	String repassword = request.getParameter("repassword");

	if(newpassword.equals(repassword)){
		// 在 jsp 脚本中输出信息   ， out 对象用于在 jsp 脚本中属性信息
		out.print("密码一致！");
	} else {
		out.print("密码输入不一致！");
	}
%>