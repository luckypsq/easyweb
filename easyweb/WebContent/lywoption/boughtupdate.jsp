<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
	var xmlhttp;
	// ajax 验证原密码是否正确
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {
			try {
				xmlhttp = new XMLHttpRequest();
			} catch (e) {
			}
		}
	}
	function boughtupdate(){
		var count = document.getElementById("count").value;
		var total = document.getElementById("total").value;
		var itemid = document.getElementById("itemid").getAttribute("value");
		if(xmlhttp!=null){
			// 定义请求地址
			var url ="doboughtupdate.jsp?itemid="+itemid
					+ "&total=" + total
					+ "&count=" + count;
			xmlhttp.open("POST",url,true);
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var json = xmlhttp.responseText.replace(/\s/gi,"");
					eval("var b = " + json);
					console.info(b);
					if(b.code==1){

						alert(b.msg);
						location.href="bought.jsp";
					}else{
						alert(b.msg);  // msg 时扩展的 属性	
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
	}else{
		alert("不能创建XMLHttpRequest对象实例");
	}
}
	

</script>
</head>
<body>
	<%
	String count=request.getParameter("count");
	String total=request.getParameter("total");
	String itemid=request.getParameter("itemid");
	%>
	<div id="itemid" name="itemid"  value="<%=itemid%>">
	数量：<input id="count" name="count" value="<%=count%>" >
	总价：<input id="total" name="total" value="<%=total%>">
	<input type="button" value="修改" onclick="boughtupdate()">

	</div>
	
</body>
</html>