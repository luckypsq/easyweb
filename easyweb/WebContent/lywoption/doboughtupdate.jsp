<%@page import="com.yc.easyweb.biz.EorderitemBiz"%>
<%@page import="com.yc.easyweb.bean.Eorderitem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	EorderitemBiz biz =new EorderitemBiz();
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
	Eorderitem eo = new Eorderitem();
	Eorderitem eoOld = new Eorderitem();
	eo.setCount(Integer.parseInt(count));
	eoOld.setItemid(itemid);
	try{
		int code = biz.update(eo, eoOld);
		if(code > 0){
			out.print(1);
		}else{
			out.print(0);
		}
	}catch (Exception e){
		
	}
%>