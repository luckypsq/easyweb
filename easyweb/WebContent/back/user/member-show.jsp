<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link
	href="${path}/back/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${path}/back/css/style.css" />
<link
	href="${path}/back/assets/css/codemirror.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${path}/back/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="${path}/back/assets/css/font-awesome.min.css" />
<script
	src="${path}/back/assets/js/jquery.min.js"></script>
<title>用户查看</title>
</head>
<body onload="show()">
	<div class="member_show">
		<div class="member_jbxx clearfix">
			<img class="img"
				src="${path}/back/images/user.png">
			<dl class="right_xxln">
				<dt>
					<span class="">${userMessage.uname }</span> <span class="">余额：0</span>
				</dt>
				<dd class="" style="margin-left: 0">这家伙很懒，什么也没有留下</dd>
			</dl>
		</div>
		<div class="member_content">
			<ul>
				<li><label class="label_name">性别：</label><span class="name">${ userSex[userMessage.usex] }</span></li>
				<li><label class="label_name">年龄：</label><span class="name">${ userMessage.uage}</span></li>
				<li><label class="label_name">电话：</label><span class="name">${ userMessage.phone}</span></li>
				<li><label class="label_name">邮箱：</label><span class="name">${ userMessage.uemail}</span></li>
				<li><label class="label_name">级别：</label><span class="name">${ userType[userMessage.utype] }</span></li>
				<li><label class="label_name">积分：</label><span class="name">0</span></li>
				<li><label class="label_name">注册时间：</label><span class="name">${ userMessage.utime}</span></li>
				<li><label class="label_name">所在大学：</label><span class="name">${ userMessage.university}</span></li>
				<li><label class="label_name">所在学院：</label><span class="name">${ userMessage.ucollege}</span></li>
				<li><label class="label_name">所在专业：</label><span class="name">${ userMessage.umajor}</span></li>
				<li><label class="label_name">所在年级：</label><span class="name">${ userMessage.uclass}</span></li>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
	//定义xml对象
	var xmlhttp;
	// ajax 
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
		function show(){
			var id = "param.uid";
			if(id == ''){
				alert("未选择用户！！！");
				return ;
			}
			if (xmlhttp != null) {
				// 定义请求地址
				var url = "${path}/user.s?op=showUserMessage&uid="+id;
				// 以 POST 方式 开启连接
				// POST 请求 更安全（编码）  提交的数据大小没有限制
				xmlhttp.open("POST", url, true);
				// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
				// 每次的状态改变都会调用该方法
				xmlhttp.onreadystatechange = function() {
					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						var msg = xmlhttp.responseText.replace(/\s/gi, "");
						if(msg == 0){
							alert("暂无数据");
						}
					}
				};
				// 发送请求
				xmlhttp.send(null);
			} else {
				alert("不能创建XMLHttpRequest对象实例")
			}
		}
	</script>
</body>
</html>
