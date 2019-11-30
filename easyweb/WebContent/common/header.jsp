<%@page import="java.util.*"%>
<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="top" id="item4">
	<div class="container clearfix">
		<ul class="clearfix fr">
		<li>
			<%if(session.getAttribute("loginedUser")==null){ %>
			<a href="<%=application.getContextPath() %>/join.jsp" >登录</a>
			<%}else{%>
			${loginedUser.uname}
			<%} %>
		</li>
			<li><a href="<%=application.getContextPath() %>/join.jsp" >注册</a></li>
			<li><a href="<%=application.getContextPath() %>/lywoption/member.jsp" style="border: none">个人中心</a></li>
		</ul>
	</div>
</div>

<div class="header">
	<div class="container clearfix">
		<div class="logo fl">
			<a href="<%=application.getContextPath() %>/lhoption/index.jsp"><img src="<%=application.getContextPath() %>/images/logo4.png" alt=""/></a>
		</div>
		<div class="seacher fl">
			<form action="" method="post">
				<input type="text" placeholder="小伙伴，你想找什么?"/><input type="submit" value="搜 索"/>
			</form>
			<p>热门搜索：<a href="#">自行车</a> <a href="#">笔记本</a> <a href="#">散热器</a> <a href="#">考研资料</a> <a href="#">摩托车</a> <a href="#">手机</a> <a href="#">轮滑鞋</a> <a href="#">显示器</a> <a href="#">显示器</a> <a href="#">显示器</a> <a href="#">显示器</a></p>
		</div>
		<div class="mm fr clearfix">
			<a href="<%=application.getContextPath() %>/lywoption/list.jsp">我要买</a>
			<a href="<%=application.getContextPath() %>/lhoption/publish.jsp">我要卖</a>
		</div>
	</div>
</div>
