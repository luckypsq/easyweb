<%@page import="java.util.*"%>
<%@page import="com.yc.easyweb.dao.lyw.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="css/index.css"/>
	<script src="js/main.js"></script>
	<title>Document</title>
	
	<%
	
	User user =(User)session.getAttribute("loginedUser");
	pageContext.setAttribute("user", user);
	System.out.print(user);
	UserDaoLyw dao=new UserDaoLyw();
	List<Object> params=new ArrayList<Object>();
	System.out.print(user);
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

<div class="top" id="item4">
	<div class="container clearfix">
		<ul class="clearfix fr">
			<li><a href="join.jsp#tologin" >登录</a></li>
			<li><a href="join.jsp#toregister" >注册</a></li>
			<li><a href="member.jsp" style="border: none">个人中心</a></li>
		</ul>
	</div>
</div>

<div class="header">
	<div class="container clearfix">
		<div class="logo fl">
			<a href="index.html"><img src="images/logo4.png" alt=""/></a>
		</div>
		<div class="seacher fl">
			<form action="" method="post">
				<input type="text" placeholder="小伙伴，你想找什么?"/><input type="submit" value="搜 索"/>
			</form>
			<p>热门搜索：<a href="#">自行车</a> <a href="#">笔记本</a> <a href="#">散热器</a> <a href="#">考研资料</a> <a href="#">摩托车</a> <a href="#">手机</a> <a href="#">轮滑鞋</a> <a href="#">显示器</a> <a href="#">显示器</a> <a href="#">显示器</a> <a href="#">显示器</a></p>
		</div>
		<div class="mm fr clearfix">
			<a href="list.html">我要买</a>
			<a href="publish.html">我要卖</a>
		</div>
	</div>
</div>

<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="index.html">首页</a> >
			<a href="member.jsp">个人中心</a> >
			<a href="member.jsp">个人信息</a>
		</div>
		<div class="help-l fl">
			<div class="help-item">
				<div class="help-item-title">个人中心
					<a href="javascript:void(0)" class="abs"></a>
				</div>
				<div class="help-item-list">
					<ul>
						<li><a href="member.jsp">个人信息</a></li>
						<li><a href="password.jsp">修改密码</a></li>
					</ul>
				</div>
			</div>
			<div class="help-item">
				<div class="help-item-title">书籍管理
					<a href="javascript:void(0)" class="abs"></a>
				</div>
				<div class="help-item-list">
					<ul>
						<li><a href="published.html">已发布</a></li>
						<li><a href="bought.jsp">已买书籍</a></li>
						<li><a href="publish.html">发布书籍</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="help-r fr">
			<div class="help-item-title">个人信息</div>
			<div class="help-main">
				
				<form action="user.s?op=remember"
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



<div class="foot">
	<div class="container">
		<div class="zhinan">
			<ul class="clearfix">
				<li class="item-li">关于我们
					<ul>
						<li><a href="help.html">自我介绍</a></li>
						<li><a href="help.html">联系我们</a></li>
						<li><a href="help.html">网站公告</a></li>
					</ul>
				</li>
				<li class="item-li">新手指南
					<ul>
						<li><a href="help.html">如何买书</a></li>
						<li><a href="help.html">如何卖书</a></li>
						<li><a href="help.html">修改密码</a></li>
					</ul>
				</li>
				<li class="item-li">配送方式
					<ul>
						<li><a href="help.html">配送范围</a></li>
						<li><a href="help.html">配送时间</a></li>
					</ul>
				</li>
				<li class="item-li">售后服务
					<ul>
						<li><a href="help.html">退款申请</a></li>
						<li><a href="help.html">退换货处理</a></li>
						<li><a href="help.html">退换货政策</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="line"></div>

		<div class="bottom">
			<p>友情链接：<a href="#">安工在线</a>&nbsp;&nbsp;<a href="#">万林强-前端在线简历</a></p>
			<p>本站所有信息均为用户自由发布，本站不对信息的真实性负任何责任，交易时请注意识别信息的真假如有网站内容侵害了您的权益请联系我们删除，举报电话：15068718875</p>
			<p>技术支持：万林强 &nbsp;&nbsp;商务QQ:584845663 &nbsp;&nbsp;邮箱：584845663@qq.com</p>
		</div>
	</div>
</div>

</body>
</html>