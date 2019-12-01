<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	#itemid{
		display:none;
	}
	
</style>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑</title>
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
		var itemid = document.getElementById("itemid").value;
		if(xmlhttp!=null){
			// 定义请求地址
			var url ="<%=application.getContextPath()%>/lywoption/doboughtupdate.jsp?itemid="+itemid
					+ "&count=" + count;
			xmlhttp.open("POST",url,true);
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var json = xmlhttp.responseText.replace(/\s/gi,"");
					if(json==1){
						alert("修改成功！！！");
						location.href="<%=application.getContextPath()%>/lywoption/bought.jsp";
					}else{
						alert("修改失败！！！");  // msg 时扩展的 属性	
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
		String itemid=request.getParameter("itemid");
	%>
	<input id="itemid" value="<%=itemid%>" ></input>
	<div >
	数量：<input id="count" name="count" >
	<input type="button" value="修改" onclick="boughtupdate()">

	</div>
	
</body>
</html>