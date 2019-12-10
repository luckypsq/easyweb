<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="/css/index.css"/>
	<script src="/js/jquery-1.11.2.min.js"></script>
	<script src="/js/main.js"></script>
	<script type="text/javascript" src="/js/mz-packed.js"></script>
	<title>书籍详情</title>
</head>
<body onload="show()">
<jsp:include page="common/header.jsp"></jsp:include>
<div class="main">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="/lhoption/index.jsp">首页</a> >
			<a href="/lywoption/list.jsp">教材区</a> >
			<a href="/detail.jsp">图书详情</a>
		</div>
		<div class="main-left fl clearfix">
			<div class="zoom-wrap fl">
				<div id="zoom">
					<a href="${path }/images/ps.jpg" title="" class="MagicZoom">
						<img class="bzoom" src="${bookDetail.bimg}" width="300" height="424" />
					</a>
				</div>
			</div>
			<div class="attr fl">
				<p style="width:450px;">书名：<span>${bookDetail.bname}</span></p>
				<p>所属学校：<span>
					<c:if test="${bookDetail.buniversity.isEmpty()}" var="flag" scope="session">						
						<c:out value="暂无信息"></c:out>
					</c:if>
					<c:if test="${not flag}">
						<c:out value="${bookDetail.buniversity}"></c:out>	
					</c:if>
				</span></p>
				<p>所属学院：<span>
					<c:if test="${bookDetail.bucollege.isEmpty()}" var="flag" scope="session">						
						<c:out value="暂无信息"></c:out>
					</c:if>
					<c:if test="${not flag}">
						<c:out value="${bookDetail.bucollege}"></c:out>	
					</c:if>
				</span></p>
				<p>所属专业：<span>
					<c:if test="${bookDetail.bumajor.isEmpty()}" var="flag" scope="session">						
						<c:out value="暂无信息"></c:out>
					</c:if>
					<c:if test="${not flag}">
						<c:out value="${bookDetail.bumajor}"></c:out>	
					</c:if>
				</span></p>
				<p>所属年级：<span>
					<c:if test="${bookDetail.bclass.isEmpty()}" var="flag" scope="session">						
						<c:out value="暂无信息"></c:out>
					</c:if>
					<c:if test="${not flag}">
						<c:out value="${bookDetail.bclass}"></c:out>	
					</c:if>
				</span></p>
				<p>拥有者：<span>
					<c:if test="${bookDetail.uid == 0}" var="flag" scope="session">						
						<c:out value="易书网商家"></c:out>
					</c:if>
					<c:if test="${not flag}">
						<c:out value="用户"></c:out>	
					</c:if>
				</span></p>
				<a  class="pay" href="${path }/back/lywoption/buy.jsp?bid=${bookDetail.bid}">立即购买</a>
				<a  class="pay" onclick="addCart(${bookDetail.bid});">加入购物车</a>
			</div>
			<div class="clearfix"></div>
			<div class="description clearfix">
				<h2>发布者描述</h2>
				<p>${bookDetail.bcontent}</p>
			</div>
		</div>
		<div class="main-right fr">
			<h2>同类推荐</h2>
			<div class="tj">
				<c:forEach items="${similarBook}" var="similar" end="5">
					<ul>
						<li class="fore1" > <div class="p-img">  
						<a href="${path }/detail.jsp?bid=${similar.bid}">  
						<img height="150" width="auto"  src="${similar.bimg}" class="img"></a>                
						</div>                
						<div class="p-name"><a href="${path }/detail.jsp?bid=${similar.bid}">
						${similar.bname}</a></div>                
						<div class="p-price"><strong class="J-p-1701273745">￥${similar.bprice}</strong></div>           
						 </li>
					</ul>
				</c:forEach>
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
	if (xmlhttp != null) {
		var url = "${path}/eorderitem.s?op=add&bid="+id;
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
function show(){
	if (xmlhttp != null) {
		var url = "${path}/book.s?op=bookDetail";
		xmlhttp.open("POST", url, true);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var msg = xmlhttp.responseText.replace(/\s/gi, "");
				if(msg == -1){
					alert("暂无信息！！！");
				}
				if(msg == -2){
					alert("暂无同类书籍！！！");
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