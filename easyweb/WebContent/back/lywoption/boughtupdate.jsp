<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<script type="text/javascript">
	var xmlhttp;
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
		var itemid = "${param.itemid}";
		if(itemid == '' || count == ''){
			alert("信息不足无法进行此操作！！！");
			return;
		}
		if(xmlhttp!=null){
			// 定义请求地址
			var url ="${path}/eorderitem.s?op=update&itemid="+itemid
					+ "&count=" + count;
			xmlhttp.open("POST",url,true);
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var json = xmlhttp.responseText.replace(/\s/gi,"");
					if(json == 1){
						alert("修改成功！！！");
						window.location.href='${path}/back/lywoption/bought.jsp';
					}else if(json == -1){
						alert("修改失败！！！");  
					}else{
						alert("信息不足操作失败！！！");
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
	<div >
	数量：<input id="count" name="count" value="${cartCount }">
	<br>
		<input type="button" value="修改" onclick="boughtupdate()">
		<input type="button" value="返回" onclick="window.location.href='${path}/back/lywoption/bought.jsp'">
	</div>
</body>
</html>