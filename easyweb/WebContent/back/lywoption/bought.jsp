<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="${path}/css/index.css"/>
	<link rel="stylesheet" href="${path}/css/font-awesome.min.css"/>
	<script src="${path}/js/main.js"></script>
	<title>易书网</title>
	<script type="text/javascript">
	var xmlhttp;
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
	function deleteone(obj,itemid){
		if(confirm("确实要删除吗？")){
			if(xmlhttp!=null){
				// 定义请求地址
				if(itemid == ""){
					alert("请选择商品！！！");
					return;
				}
				var url ="${path}/eorderitem.s?op=delete&itemid="+itemid;
				xmlhttp.open("POST",url,true);
				xmlhttp.onreadystatechange=function(){
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
						var json = xmlhttp.responseText.replace(/\s/gi,"");
						if(json==1){
							$(obj).parents("ul").remove();
							alert("删除成功！！！");
						}else{
							alert("删除失败！！！");  
						}
					}
				};
				// 发送请求
				xmlhttp.send(null);
			}else{
				alert("不能创建XMLHttpRequest对象实例");
			}
		}
	}
	
function show(page){
		if(xmlhttp!=null){
			var url;
			// 定义请求地址
			if(page != -1){
				 url ="${path}/eorderitem.s?op=query&Page="+page;
			}else{
				 url ="${path}/eorderitem.s?op=query";
			}
			xmlhttp.open("POST",url,true);
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var json = xmlhttp.responseText.replace(/\s/gi,"");
					if(json == -1){
						alert("暂无数据");
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
	}else{
		alert("不能创建XMLHttpRequest对象实例");
	}
	$(function(){  
	     show(-1);
	});
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
<jsp:include page="${path}/common/header.jsp"></jsp:include>
<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="${path}/back/lhoption/index.jsp">首页</a> >
			<a href="${path}/back/lywoption/member.jsp">个人中心</a> >
			<a href="${path}/back/lywoption/bought.jsp">购物车</a>
	
		</div>
		<jsp:include page="${path}/common/middle.jsp"></jsp:include>
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
							<li class="edit1" style="width: 50px">	
							</li>
							<li class="edit1" style="width: 50px">
							</li>
							<li class="edit1" style="width: 50px">
							</li>
						</ul>
					</div>
				</div>
				<c:forEach items="${cartDate}" var="cart">
					<div class="pro">
						<div class="product-attr">
							<div class="product-name fl">
								<div class="pic-thumb fl"><a href="${path}/detail.jsp?bid=${cart.bid}"  ><img class="middle" src="${cart.bimg}"></a></div>
								<div class="product-title fl">
									<a href="${path}/detail.jsp?bid=${cart.bid}" class="ellipsis">${cart.bname}</a><br>
									<span>
										<c:if test="${cart.bucollege == ''}" var="flag" scope="session">						
											<c:out value="暂无信息"></c:out>
										</c:if>
										<c:if test="${not flag}">	
												${cart.bucollege}			   	
										</c:if>
									</span>
									<span>
										<c:if test="${cart.bumajor == ''}" var="flag" scope="session">						
											<c:out value="暂无信息"></c:out>
										</c:if>
										<c:if test="${not flag}">	
												${cart.bumajor}		   	
										</c:if>
									</span>
									<span>
										<c:if test="${cart.bclass == ''}" var="flag" scope="session">						
											<c:out value="暂无信息"></c:out>
										</c:if>
										<c:if test="${not flag}">	
												${cart.bclass}	   	
										</c:if>
									</span>
								</div>
							</div>
							<div class="product-price fr">
								<ul class="clearfix">
									<li>${cart.bprice}	 </li>
									<li>${cart.carttime} </li>
									<li class="edit" style="width: 110px">
									</li>
									<li  class="edit1" style="width: 50px" >
										<a id="buyA" href="${path}/back/lywoption/buy.jsp?itemid=${cart.itemid}">购买</a>
									</li>
									<li class="edit2" style="width: 50px" >
										<a id="buyA" href="${path}/back/lywoption/boughtupdate.jsp?itemid=${cart.itemid}"></a>
									</li>
									<li class="edit3" style="width: 50px" >
										<a id="buyA" onclick="deleteone(this,${cart.itemid})"></a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="page clearfix">
					<a onclick = "show(${cartPage.getFirstPage()})">首页</a>
					<a onclick = "show(${cartPage.getPreviousPage()})">上一页</a>
					<c:forEach  var="i" begin="1" end="${cartPage.getLastPage()}">
						<c:if test="${cartPage.getPage() == ${i}" var="flag" scope="session">						
							<span class="currentPage">${i}</span>
						</c:if>
						<c:if test="${not flag}">
							<a onclick = "show(${i})">${i}</a>
						</c:if>			
					</c:forEach>
					<a onclick = "show(${cartPage.getNextPage()})" >下一页</a>
					<a onclick = "show(${cartPage.getLastPage()})">尾页</a>
					第${cartPage.getPage()}/${cartPage.getLastPage()}页
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="${path}/common/footer.jsp"></jsp:include>
</body>
</html>