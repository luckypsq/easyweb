<%@page import="com.yc.easyweb.dao.lyw.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	CartDao dao =new CartDao();
	String itemid = request.getParameter("itemid");
	System.out.println(itemid);
	try{
		int code = dao.updateall(itemid);
		// json 格式字符串
		// 定义json 返回字符串,  用于返回复杂的数据
		String json = "{itemid:'"+itemid
				+"', code:'"+code
				+"', msg:'删除成功！'}";   // 扩张一个结果描述信息
		System.out.print(json);
		out.print(json);
	}	catch (Exception e){
		String json = "{msg:'删除失败！'}";   // 扩张一个结果描述信息
		out.print(json);
	}
%>


