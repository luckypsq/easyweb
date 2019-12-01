<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String count=request.getParameter("count");
	String total=request.getParameter("total");
	String itemid=request.getParameter("itemid");
	String eoaddr=request.getParameter("eoaddr");
	String eotype=request.getParameter("eotype");
	
	%>
	<div>
	数量：<input id="count" name="count" value="<%=count%>" >
	总价：<input id="total" name="total" value="<%=total%>">
	地址：<input id="eoaddr" name="eoaddr" value="<%=eoaddr%>">
	配送方式：<input id="eotype" name="eotype" value="<%=eotype%>">
	<input type="button" value="付款" >
	</div>
</body>
</html>