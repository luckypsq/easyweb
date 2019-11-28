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
	href="<%=application.getContextPath()%>/back/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/back/css/style.css" />
<link
	href="<%=application.getContextPath()%>/back/assets/css/codemirror.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/back/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/back/assets/css/font-awesome.min.css" />
<script
	src="<%=application.getContextPath()%>/back/assets/js/jquery.min.js"></script>
<title>用户查看</title>
</head>
<body>
	<div class="member_show">
	<%
				request.setCharacterEncoding("utf-8");
				//新建条件对象
				User user = new User();
				UserBiz userBiz = new UserBiz();
				if(request.getParameter("uid") != null && !request.getParameter("uid").isEmpty()){
					user.setUid(Long.parseLong(request.getParameter("uid")));
				}
				String utype = null;
				User userShow  = userBiz.selectSingle(user);
				if(userShow.getUtype() == 2){
					utype = "用户";
				}else if(userShow.getUtype() == 3){
					utype = "会员";
				}else if(userShow.getUtype() == 4){
					utype = "钻石会员";
				}
			%>
		<div class="member_jbxx clearfix">
			<img class="img"
				src="<%=application.getContextPath()%>/back/images/user.png">
			<dl class="right_xxln">
				<dt>
					<span class=""><%=userShow.getUname() == null? "":userShow.getUname() %></span> <span class="">余额：0</span>
				</dt>
				<dd class="" style="margin-left: 0">这家伙很懒，什么也没有留下</dd>
			</dl>
		</div>
		<div class="member_content">
			<ul>
				<li><label class="label_name">性别：</label><span class="name"><%=userShow.getUsex() == 1? "男":"女" %></span></li>
				<li><label class="label_name">年龄：</label><span class="name"><%=userShow.getUage() == 0? "":userShow.getUage() %></span></li>
				<li><label class="label_name">电话：</label><span class="name"><%=userShow.getUphone() == null? "":userShow.getUphone() %></span></li>
				<li><label class="label_name">邮箱：</label><span class="name"><%=userShow.getUemail() == null? "":userShow.getUemail() %></span></li>
				<li><label class="label_name">级别：</label><span class="name"><%=utype == null? "":utype %></span></li>
				<li><label class="label_name">积分：</label><span class="name">0</span></li>
				<li><label class="label_name">注册时间：</label><span class="name"><%=userShow.getUtime() == null? "":userShow.getUtime() %></span></li>
				<li><label class="label_name">所在大学：</label><span class="name"><%=userShow.getUniversity() == null? "":userShow.getUniversity() %></span></li>
				<li><label class="label_name">所在学院：</label><span class="name"><%=userShow.getUcollege() == null? "":userShow.getUcollege() %></span></li>
				<li><label class="label_name">所在专业：</label><span class="name"><%=userShow.getUmajor() == null? "":userShow.getUmajor()%></span></li>
				<li><label class="label_name">所在年级：</label><span class="name"><%=userShow.getUclass() == null? "":userShow.getUclass() %></span></li>
			</ul>
		</div>
	</div>
</body>
</html>
