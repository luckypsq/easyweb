<%@page import="com.yc.easyweb.dao.lyw.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	CartDao dao =new CartDao();
	String itemid = null;
	String count=null;
	if(request.getParameter("itemid") != null && !request.getParameter("itemid").isEmpty()){
		itemid = request.getParameter("itemid");
	}
	if(request.getParameter("count") != null && !request.getParameter("count").isEmpty()){
		count=request.getParameter("count");
	}
	if(count == null || itemid == null){
		out.print(0);
		return;
	}
	try{
		int code = dao.updatebj(count,itemid);
		if(code > 0){
			out.print(1);
		}else{
			out.print(0);
		}
	}catch (Exception e){
		
	}
%>