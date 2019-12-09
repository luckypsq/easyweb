<%@page import="com.yc.easyweb.biz.EorderitemBiz"%>
<%@page import="java.util.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="<%=application.getContextPath()%>/css/index.css"/>
	<link rel="stylesheet" href="<%=application.getContextPath()%>/css/font-awesome.min.css"/>
	<script src="<%=application.getContextPath()%>/js/main.js"></script>
	<title>易书网</title>
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
	function deleteone(itemid){
		if(xmlhttp!=null){
			// 定义请求地址
			var url ="<%=application.getContextPath()%>/lywoption/dodelete.jsp?itemid="+itemid;
			xmlhttp.open("POST",url,true);
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var json = xmlhttp.responseText.replace(/\s/gi,"");
					eval("var b = " + json);
					console.info(b);
					alert(b.msg);
					if(b.code==1){
						location.href="<%=application.getContextPath()%>/lywoption/bought.jsp";
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
<style>
	.help-main p {
		line-height: 50px;
	}
	#buyA{
		text-decoration:none ;
		out-line: none;
	}
	
</style>
<body >
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="<%=application.getContextPath()%>/lhoption/index.jsp">首页</a> >
			<a href="<%=application.getContextPath()%>/lywoption/member.jsp">个人中心</a> >
			<a href="<%=application.getContextPath()%>/lywoption/bought.jsp">购物车</a>
	
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
			<div class="help-item-title">购物车</div>
			<div class="help-main">
				<div class="product-item clearfix">
					<div class="name fl">
						<span style="margin-left: 150px">书名</span>
					</div>
					<div class="attr fr">
						<ul class="clearfix">
							<li>单价</li>
							<li>加入时间</li>
							<li style="width: 110px">状态</li>
							<li class="edit1" style="width: 50px">	
							</li>
							<li class="edit1" style="width: 50px">
							</li>
							<li class="edit1" style="width: 50px">
							</li>
						</ul>
					</div>
				</div>
				<%		
					DbHelper dbHelper=new DbHelper();
					Bought bought =new Bought();
					String pageParam = request.getParameter("Page");
					int ipage = pageParam == null ? 1 : Integer.parseInt(pageParam);
					// 每页行数
					int rows = 6;	
					User user =(User)session.getAttribute("loginedUser");
					long uid=user.getUid();
					EorderitemBiz eorderitemBiz = new EorderitemBiz();
					Bought bou = new Bought();
					Page<Bought>  Page = eorderitemBiz.ePage(ipage, rows, bou, uid);
						for(Bought bought1 : Page.getData()){
							String state = null;
							 switch (bought1.getCartstate()) {
						  	  case 1:
						  		state="待下单";
							   break;
							  case 2:
							    state="已购买";
							   break;
							  case 3:
								state="已删除";
							   break;
							  }
							 String itemid1=bought1.getItemid();
							 long count1=bought1.getCount();
							 double total1=bought1.getTotal();
					%>
				<div class="pro">
					<div class="product-attr">
						<div class="product-name fl">
							<div class="pic-thumb fl"><a href="<%=application.getContextPath()%>/detail.jsp"  ><img class="middle" src="<%=bought1.getBimg()%>"></a></div>
							<div class="product-title fl">
								<a href="<%=application.getContextPath()%>/detail.jsp?bid=<%=bought1.getBid()%>" class="ellipsis"><%=bought1.getBname() %></a><br>
								<span><%=bought1.getBucollege()== null ?"暂无信息" :bought1.getBucollege()%></span>
								<span><%=bought1.getBumajor()== null ?"暂无信息" :bought1.getBumajor() %></span>
								<span><%=bought1.getBclass()== null ?"暂无信息" : bought1.getBclass()%></span>
							</div>
						</div>
						<div class="product-price fr">
							<ul class="clearfix">
								<li><%=bought1.getBprice() %></li>
								<li><%=bought1.getCarttime()%></li>
								<li class="edit" style="width: 110px">
								<span class="sell"><%=state%></span>
								</li>
								<li  class="edit1" style="width: 50px" >
									<a id="buyA" href="<%=application.getContextPath()%>/lywoption/buy.jsp?itemid=<%=itemid1%>">购买</a>
								</li>
								<li class="edit2" style="width: 50px" >
								<form action="<%=application.getContextPath()%>/lywoption/boughtupdate.jsp?itemid=<%=itemid1%>"
									method="post" novalidate="novalidate"
									>
									<input type="submit" value="编辑" >
								</form>
								</li>
								<li class="edit3" style="width: 50px" >
									<input type="button" value="删除" onclick="deleteone(<%=itemid1%>)">
								</li>
							
							</ul>
						</div>
					</div>
				</div>
				<%} %>
				
				<div class="page clearfix">
					<a href="<%=application.getContextPath()%>/lywoption/bought.jsp?Page=<%=Page.getFirstPage()%>">首页</a>
					<a href="<%=application.getContextPath()%>/lywoption/bought.jsp?Page=<%=Page.getPreviousPage()%>">上一页</a>
					<%
						for(int j=1; j<=Page.getLastPage() ; j++){ 
						if(Page.getPage()==j){ %>
							<span class="currentPage"><%=j%></span>
							<%} else { %>
							<a href="<%=application.getContextPath()%>/lywoption/bought.jsp?Page=<%=j%>"><%=j%></a>
							<%} %>
					<%} %>
					<a href="<%=application.getContextPath()%>/lywoption/bought.jsp?Page=<%=Page.getNextPage()%>">下一页</a>
					<a href="<%=application.getContextPath()%>/lywoption/bought.jsp?Page=<%=Page.getLastPage()%>">尾页</a>
					第<%=Page.getPage() %>/<%=Page.getLastPage()%>页
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>