<%@ page import="com.yc.easyweb.biz.BookBiz"%>
<%@ page import="com.yc.easyweb.bean.Book"%>
<%@ page import ="com.yc.easyweb.dao.BookDao"%>
<%@ page import ="com.yc.easyweb.common.DbHelper" %>
<%@ page import = "java.lang.*"%>
<%@ page import ="java.util.*" %>
<%@ page import = "com.yc.easyweb.bean.PageBook" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="css/index.css"/>
	<link rel="stylesheet" href="css/font-awesome.min.css"/>
	<script src="js/main.js"></script>
	<title>易书网</title>
</head>
<style>
	.help-main p {
		line-height: 50px;
	}
</style>
<body>
<jsp:include page="/common/header.jsp"></jsp:include>

<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="index.html">首页</a> >
			<a href="member.html">个人中心</a> >
			<a href="published.html">已发布</a>
		</div>
		<div class="help-l fl">
			<div class="help-item">
				<div class="help-item-title">个人中心
					<a href="javascript:void(0)" class="abs"></a>
				</div>
				<div class="help-item-list">
					<ul>
						<li><a href="member.html">个人信息</a></li>
						<li><a href="password.html">修改密码</a></li>
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
						<li><a href="bought.html">已买书籍</a></li>
						<li><a href="publish.html">发布书籍</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="help-r fr">
			<div class="help-item-title">已发布书籍</div>
			<div class="help-main">
				<div class="product-item clearfix">
					<div class="name fl">
						<span style="margin-left: 150px">书名</span>
					</div>
					<div class="attr fr">
						<ul class="clearfix">
							<li>价格</li>
							<li>当前数量</li>
							<li style="width: 110px">状态</li>
						</ul>
					</div>
				</div>
				<%
				String paramNumber = request.getParameter("page");
				
				Book book = null;
				// 第几页
				int iPage = paramNumber == null ? 1 : Integer.parseInt(paramNumber);
				// 查询总行数
				PageBook pPage =  DbHelper.selectPageForMysql(iPage, 8,book );
				
				%>
				
				<% for(Book s : pPage.getData()){ %>
				<% 
				// 将 商品 map 添加到页面上下文中， 就是用 EL 表达式输出值
				pageContext.setAttribute("t", s);
				%>
				<div class="pro">
					<div class="product-attr">
						<div class="product-name fl">
							<div class="pic-thumb fl"><a href="detail.html"  ><img class="middle" src="${t.bimg }"></a></div>
							<div class="product-title fl">
								<a href="detail.html" class="ellipsis">${t.bname }</a><br>
								<span>${t.bucollege }</span>
								<span>${t.bumajor }</span>
								<span>${t.bclass }</span>
							</div>
						</div>
						<div class="product-price fr">
							<ul class="clearfix">
								<li>${t.bprice }</li>
								<li>${t.bnum }</li>
								<li class="edit" style="width: 110px">
							<span>
								<a href="edit.html">
									<i class="icon-edit"></i>
									<p>编辑</p>
								</a>
							</span>
									<span class="line"></span>
							<span>
								<a href="#">
									<i class="icon-trash"></i>
									<p>删除</p>
								</a>
							</span>
								</li>
							</ul>
						</div>
					</div>
				</div>	
				<%} %>
				
				
			<div id="ball_footer" class="ball_footer">
			
				<a class="firstPage" href="published.jsp?page=1">首页</a>
				<a class="previousPage" href="published.jsp?page=<%=pPage.getPreviousPage()%>">上一页</a>
				<a class="nextPage" href="published.jsp?page=<%=pPage.getNextPage()%>">下一页</a>
				<a class="lastPage" href="published.jsp?page=<%=pPage.getLastPage()%>">尾页</a>
				第<%=pPage.getPage()%>/<%=pPage.getLastPage()%>
			</div>
		</div>
				
			</div>
		</div>
	</div>
</div>



<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>