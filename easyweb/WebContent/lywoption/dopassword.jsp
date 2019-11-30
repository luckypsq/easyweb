<%@page import="com.yc.easyweb.bean.User"%>
<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%	

	String oldpassword = request.getParameter("oldpassword");
	String newpassword = request.getParameter("newpassword");
	String repassword = request.getParameter("repassword");
	String sql = "update user set upassword=?  where uid=?";
	try{
		User user =(User)session.getAttribute("loginedUser");
		int code = DbHelper.update(sql,repassword,user.getUid());

		
		// json 格式字符串
		// 定义json 返回字符串,  用于返回复杂的数据
		String json = "{oldpassword:'"+oldpassword
				+"', newpassword:'"+newpassword
				+"', repassword:'"+repassword
				+"', code:'"+code
				+"', msg:'修改密码成功！'}";   // 扩张一个结果描述信息
		System.out.print(json);
		out.print(json);
	} catch (Exception e){
		String json = "{msg:'修改密码成功！'}";   // 扩张一个结果描述信息
		out.print(json);
	}
%>