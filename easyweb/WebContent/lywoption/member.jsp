<%@page import="java.util.*"%>
<%@page import="com.yc.easyweb.dao.lyw.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="<%=application.getContextPath()%>/css/index.css"/>
	<script src="<%=application.getContextPath()%>/js/main.js"></script>
	<title>Document</title>
	
	<%
		User user =(User)session.getAttribute("loginedUser");
		pageContext.setAttribute("user", user);
		UserDaoLyw dao=new UserDaoLyw();
		List<Object> params=new ArrayList<Object>();
		params.add(user.getUid());
		user=dao.selectAll(params);
		pageContext.setAttribute("user", user);
	%>
</head>
<style>
	.help-main p {
		line-height: 50px;
	}
</style>
<body >
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="<%=application.getContextPath()%>/lhoption/index.jsp">首页</a> >
			<a href="<%=application.getContextPath()%>/lywoption/member.jsp">个人中心</a> >
			<a href="<%=application.getContextPath()%>/lywoption/member.jsp">个人信息</a>
		</div>
		<div class="help-l fl">
			<div class="help-item">
				<div class="help-item-title">个人中心
					<a href="javascript:void(0)" class="abs"></a>
				</div>
				<div class="help-item-list">
					<ul>
						<li><a href="<%=application.getContextPath()%>/lywoption/member.jsp">个人信息</a></li>
						<li><a href="<%=application.getContextPath()%>/lywoption/password.jsp">修改密码</a></li>
					</ul>
				</div>
			</div>
			<div class="help-item">
				<div class="help-item-title">书籍管理
					<a href="javascript:void(0)" class="abs"></a>
				</div>
				<div class="help-item-list">
					<ul>
						<li><a href="<%=application.getContextPath()%>/lhoption/published.jsp">已发布</a></li>
						<li><a href="<%=application.getContextPath()%>/lywoption/bought.jsp">购物车</a></li>
						<li><a href="<%=application.getContextPath()%>/lywoption/bought2.jsp">已买书籍</a></li>
						<li><a href="<%=application.getContextPath()%>/lhoption/publish.jsp">发布书籍</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="help-r fr">
			<div class="help-item-title">个人信息</div>
			<div class="help-main">
				
				<form action="<%=application.getContextPath()%>/user.s?op=remember"
						method="post" novalidate="novalidate" >
					<p><span class="nice">用户名：</span><span>${user.uname}</span></p>
					<p><span class="nice">昵称：</span><input type="text" id="uminname" name="uminname" value="${user.uminname}">
					<span class="nice">联系电话：</span><input type="text" id="uphone" name="uphone" value="${user.uphone}"></p>
					<p><span class="nice">学校：</span><input type="text" id="university" name="university" value="${user.university}">
					<span class="nice">学院：</span><input type="text" id="ucollege" name="ucollege" value="${user.ucollege}"></p>
					<p><span class="nice">专业：</span><input type="text" id="umajor" name="umajor" value="${user.umajor}">
					<span class="nice">年级：</span><input type="text" id="uclass" name="uclass" value="${user.uclass}"></p>
					<input class="save" type="submit" value="修改"/>
				</form>
			</div>
		</div>
	</div>
</div>
<%
     Object result = request.getAttribute("result");
     if(result!=null && !"".equals(result)){
  %>
      <script type="text/javascript">
          alert('<%=result%>');
     </script>
  <%} %>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>