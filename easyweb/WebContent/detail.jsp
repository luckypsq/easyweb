<%@page import="com.yc.easyweb.biz.BookBiz"%>
<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@page import="com.yc.easyweb.bean.Book"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="<%=application.getContextPath()%>/css/index.css"/>
	<script src="<%=application.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
	<script src="<%=application.getContextPath()%>/js/main.js"></script>
	<script type="text/javascript" src="<%=application.getContextPath()%>/js/mz-packed.js"></script>
	<title>书籍详情</title>
</head>
<body>
<%
			  request.setCharacterEncoding("utf-8");
			  response.setContentType("text/html;charset=utf-8");
			  BookBiz biz = new BookBiz();
			  Book book1 = new Book();
			  if(request.getParameter("bid") != null && !request.getParameter("bid").isEmpty()){
				  book1.setBid(Long.parseLong(request.getParameter("bid")));
			  }
			  Book book = biz.selectSingle(book1);
			%>
<jsp:include page="common/header.jsp"></jsp:include>
<div class="main">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="<%=application.getContextPath()%>/lhoption/index.jsp">首页</a> >
			<a href="<%=application.getContextPath()%>/lywoption/list.jsp">教材区</a> >
			<a href="<%=application.getContextPath()%>/detail.jsp">图书详情</a>
		</div>
		<div class="main-left fl clearfix">
			<div class="zoom-wrap fl">
				<div id="zoom">
					<a href="<%=application.getContextPath() %>/images/ps.jpg" title="" class="MagicZoom">
						<img class="bzoom" src="<%=book.getBimg() %>" width="300" height="424" />
					</a>
				</div>
			</div>
			<div class="attr fl">
				<p style="width:450px;">书名：<span><%=book.getBname() %></span></p>
				<p>所属学校：<span><%=book.getBuniversity()== null? "暂无信息" : book.getBuniversity()%></span></p>
				<p>所属学院：<span><%=book.getBucollege()== null? "暂无信息" : book.getBucollege()%></span></p>
				<p>所属专业：<span><%=book.getBumajor()== null? "暂无信息" : book.getBumajor()%></span></p>
				<p>所属年级：<span><%=book.getBclass()== null? "暂无信息" : book.getBclass()%></span></p>
				<a  class="pay" href="<%=application.getContextPath()%>/lywoption/buy.jsp?bid=<%=book.getBid()%>">立即购买</a>
				<a  class="pay" onclick="addCart(<%=book.getBid() %>);">加入购物车</a>
			</div>
			<div class="clearfix"></div>
			<div class="description clearfix">
				<h2>发布者描述</h2>
				<p><%=book.getBcontent() %></p>
			</div>
		</div>
		<div class="main-right fr">
			<h2>同类推荐</h2>
			<div class="tj">
				<ul>
				 <%
			       long btid= book.getBtid();
			       String sql01="select * from book where btid=? ";
			       List<Object> params01=new ArrayList<Object>();
                   params01.add(btid);
                   List<Book> list=DbHelper.selectAll(sql01, params01, Book.class);
                   int i=0;
			       for(Book btype:list){
			%>
					<li class="fore1" > <div class="p-img">  
					<a href="<%=application.getContextPath()%>/detail.jsp?bid=<%=btype.getBid()%>">  
					<img height="150" width="auto"  src="<%=btype.getBimg() %>" class="img"></a>                
					</div>                
					<div class="p-name"><a href="<%=application.getContextPath()%>/detail.jsp?bid=<%=btype.getBid()%>">
					<%=btype.getBcontent() %></a></div>                
					<div class="p-price"><strong class="J-p-1701273745">￥<%=btype.getBprice() %></strong></div>           
					 </li>
		  <%   i++;  if(i>=5) break;} %>
				</ul>
			</div>
		</div>

	</div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>

<!-- <div class="full hide">
	<div class="select-book">
		<div class="clearfix">
			<button class="select">&nbsp;</button>
			<h1>点击图片选择</h1>
			<button class="send " data-counter="0">&#10004;</button>
		</div>
		<div style="text-align:center;clear:both">
		</div>
		<ul class="clearfix">
			<li><img src="images/book.jpg"/><h3>福尔摩斯探案全集1</h3></li>
			<li><img src="images/book.jpg" /><h3>福尔摩斯探案全集2</h3></li>
			<li><img src="images/book.jpg" /><h3>福尔摩斯探案全集3</h3></li>
			<li><img src="images/book.jpg" /><h3>福尔摩斯探案全集4</h3></li>
			<li><img src="images/book.jpg" /><h3>福尔摩斯探案全集5</h3></li>
			<li><img src="images/book.jpg" /><h3>福尔摩斯探案全集6</h3></li>
			<li><img src="images/book.jpg" /><h3>福尔摩斯探案全集7</h3></li>
			<li><img src="images/book.jpg" /><h3>福尔摩斯探案全集8</h3></li>
			<li><img src="images/book.jpg" /><h3>福尔摩斯探案全集9</h3></li>
			<li><img src="images/book.jpg" /><h3>福尔摩斯探案全集10</h3></li>
			<li><img src="images/book.jpg" /><h3>福尔摩斯探案全集11</h3></li>
			<li><img src="images/book.jpg" /><h3>福尔摩斯探案全集12</h3></li>
		</ul>
		<div class="confirm">
			<a href="#" id="confirm">确认</a>
			<a href="#">取消</a>
		</div>
	</div>
</div> -->
<script type="text/javascript">
var xmlhttp;
// ajax 验证用户名是否存在//是否为空//
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
function addCart(id){
	alert(id);
	if (xmlhttp != null) {
		var url = "<%=application.getContextPath()%>/eorderitem.s?op=add&bid="+id;
		xmlhttp.open("POST", url, true);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var msg = xmlhttp.responseText.replace(/\s/gi, "");
				if(msg == 1){
					alert("添加成功！！！");
				}else if(msg = 2){
					alert("信息不足无法添加！！！");
				}else{
					alert("添加失败!!!");
				}
			}
		};
		xmlhttp.send(null);
	} else {
		alert("不能创建XMLHttpRequest对象实例");
	}
}
</script>
</body>
</html>