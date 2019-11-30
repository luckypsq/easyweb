<%@page import="com.yc.easyweb.dao.lyw.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	CartDao dao =new CartDao();
	String itemid = request.getParameter("itemid");
	String count=request.getParameter("count");
	String total=request.getParameter("total");
	
	try{
		System.out.print(true);
		int code = dao.updatebj(count, total, itemid);
		// json 格式字符串
		// 定义json 返回字符串,  用于返回复杂的数据
		String json = "{itemid:'"+itemid
				+"', count:'"+count
				+"', total:'"+total
				+"', code:'"+code
				+"', msg:'修改成功！'}";   // 扩张一个结果描述信息
		System.out.print(json);
		out.print(json);
	}	catch (Exception e){
		String json = "{msg:'修改失败！'}";   // 扩张一个结果描述信息
		out.print(json);
	}







%>