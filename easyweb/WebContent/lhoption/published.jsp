<%@page import="com.yc.easyweb.bean.Page"%>
<%@page import="com.yc.easyweb.bean.User"%>
<%@ page import="com.yc.easyweb.biz.BookBiz"%>
<%@ page import="com.yc.easyweb.bean.Book"%>
<%@ page import ="com.yc.easyweb.dao.BookDao"%>
<%@ page import ="com.yc.easyweb.common.DbHelper" %>
<%@ page import = "java.lang.*"%>
<%@ page import ="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="<%=application.getContextPath()%>/css/index.css"/>
	<link rel="stylesheet" href="<%=application.getContextPath()%>/css/font-awesome.min.css"/>
	<script src="<%=application.getContextPath()%>/js/main.js"></script>
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
			<a href="<%=application.getContextPath()%>/lhoption/index.jsp">首页</a> >
			<a href="<%=application.getContextPath()%>/lywoption/member.jsp">个人中心</a> >
			<a href="<%=application.getContextPath()%>/lhoption/published.jsp">已发布</a>
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
				Book book = new Book();
				User user = (User)session.getAttribute("loginedUser");
				if(user != null){
					if(user.getUid() != 0){
						book.setUid(user.getUid());
					}
				}
				if(book.getUid() == 0){
				%>
					<div class="pro">
					<div class="product-attr">
							<span>暂无已发布书籍</span>
					</div>
				</div>
				<% 
				}else{
				// 第几页
				int iPage = paramNumber == null ? 1 : Integer.parseInt(paramNumber);
				BookBiz bookBiz4 = new BookBiz();
				Page<Book> pPage = bookBiz4.bookPage(iPage, 5,book);
				// 查询总行数
				for(Book s : pPage.getData()){ 
				// 将 商品 map 添加到页面上下文中， 就是用 EL 表达式输出值
				pageContext.setAttribute("t", s);
				%>
				<div class="pro">
					<div class="product-attr">
						<div class="product-name fl">
							<div class="pic-thumb fl"><a href="detail.html"  ><img class="middle" src="${t.bimg }"></a></div>
							<div class="product-title fl">
								<a href="<%=application.getContextPath()%>/detail.jsp?bid=${t.bid}" class="ellipsis">${t.bname }</a><br>
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
								<a href="<%=application.getContextPath()%>/lhoption/publish.jsp?bid=${t.bid }">
									<i class="icon-edit"></i>
									<p>编辑</p>
								</a>
							</span>
									<span class="line"></span>
							<span>
								<a onclick = "deleteBook(${t.bid });">
									<i class="icon-trash"></i>
									<p >删除</p>
								</a>
							</span>
								</li>
							</ul>
						</div>
					</div>
				</div>	
				<%
				} %>
				
				
			<div id="ball_footer" class="ball_footer">
			
				<a class="firstPage" href="<%=application.getContextPath()%>/published.jsp?page=1">首页</a>
				<a class="previousPage" href="<%=application.getContextPath()%>/published.jsp?page=<%=pPage.getPreviousPage()%>">上一页</a>
				<a class="nextPage" href="<%=application.getContextPath()%>/published.jsp?page=<%=pPage.getNextPage()%>">下一页</a>
				<a class="lastPage" href="<%=application.getContextPath()%>/published.jsp?page=<%=pPage.getLastPage()%>">尾页</a>
				第<%=pPage.getPage()%>/<%=pPage.getLastPage()%>
			</div>
		</div>
				<%} %>
			</div>
		</div>
	</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
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
function deleteBook(id){
	if(confirm("确定删除本书？？？")){
		if (xmlhttp != null) {
			// 定义请求地址
			var url = "<%=application.getContextPath()%>/book.s?op=delete&bid="+id;
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST", url, true);
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					var msg = xmlhttp.responseText.replace(/\s/gi, "");
					if(msg == "删除成功！"){
						alert("删除成功！");
						window.location.href='<%=application.getContextPath()%>/lhoption/published.jsp';
					}else if(msg == 0){
						alert("不能进行此操作！！！");
					}else{
						alert(msg);
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		} else {
			alert("不能创建XMLHttpRequest对象实例");
		}
	}
}

</script>
</body>
</html>