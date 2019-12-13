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
	function deleteone(obj,eoid){
		if(confirm("确实要删除吗？")){
			if(xmlhttp!=null){
				// 定义请求地址
				var url ="${path}/eorder.s?op=delete&eoid="+eoid;
				xmlhttp.open("POST",url,true);
				xmlhttp.onreadystatechange=function(){
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
						var json = xmlhttp.responseText.replace(/\s/gi,"");
						if(json == 1){
							$(obj).parents("tr").remove();
							alert("删除成功！！！");
						}else if(json == 0){
							alert("删除失败！！！");
						}else{
							alert("条件不满足无法删除！！！");
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
				 url ="${path}/eorder.s?op=queryUserOrder&Page="+page;
			}else{
				 url ="${path}/eorder.s?op=queryUserOrder";
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
	}
	</script>
</head>
<style>
	.help-main p {
		line-height: 50px;
	}
</style>
<body onload="show(-1)">
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="${path}/back/lhoption/index.jsp">首页</a> >
			<a href="${path}/back/lywoption/member.jsp">个人中心</a> >
			<a href="${path}/back/lywoption/bought2.jsp">已买书籍</a>
		</div>
		<jsp:include page="../common/middle.jsp"></jsp:include>
		<div class="help-r fr">
		<div class="help-item-title">已买书籍</div>
		<c:forEach items="${userOrder}" var="userOrder">
			<table class="cart1" id="cart1" width="100%" height="250px" cellspacing="0" cellpadding="0" bordercolor="#ADD3EF" border="1">
				<tbody>
					<tr height="50px">
						<td class="ct01" id="ct01"><span><b style="color: red;">订单号：${userOrder.eoid}</b>
						</span> </td>
					</tr>
					<tr>
						<td class="ct02" id="ct02">
							<div class="div_table">
								<table class="eo" id="eo" width="100%" height="180px" cellspacing="0" cellpadding="1" border="1" bordercolor="#ADD3EF">
									<tr height="30px">
										<th style="width:80px;">书名</th>
										<th style="width:100px;">图片</th>
										<th style="width:80px;">收货人</th>	
										<th style="width:80px;">下单时间</th>	
										<th style="width:80px;">总价</th>	
										<th style="width:80px;">数量</th>
										<th style="width:80px;">状态</th>	
										<th style="width:180px;">操作</th>	
									</tr>
									<c:forEach items="${allOrder}" var="allOrder">
										<c:if test="${allOrder.eoid.equals(userOrder.eoid)}" var="flag" scope="session">						
											<tr style="text-align:center">
												<td style="width:80px;">${allOrder.bname}</td>
												<td style="width:100px;"><img alt="" src="${allOrder.bimg}">   </td>	
												<td style="width:80px;">${allOrder.uname}</td>	
												<td style="width:80px;">${allOrder.eotime}</td>	
												<td style="width:80px;">${allOrder.count}</td>	
												<td style="width:80px;">${allOrder.total}</td>
												<td style="width:80px;">${eorderState[allOrder.eostate]}</td>	
												<td style="width:180px;">
													<input type="button" value="售后" >
													<input type="button" value="退货" >
													<input type="button" value="删除" onclick="deleteone(this,${allOrder.eoid})">
												</td>
											</tr>
										</c:if>
									</c:forEach>
							</table>
						</div>
					</td>
				</tr>
				<tr height="50px">
					<td >
						<div class="eo1" id="eo1">
							<span>收货地址：${userOrder.eoaddr}</span><span style="text-align:right;">配送方式:${userOrder.eotype}</span>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		</c:forEach>
				<div class="page clearfix">
					<a onclick = "show(${userOrderPage.getFirstPage()})">首页</a>
					<a onclick = "show(${userOrderPage.getPreviousPage()})">上一页</a>
					<c:forEach  var="i" begin="1" end="${userOrderPage.getLastPage()}">
						<c:if test="${userOrderPage.getPage() == ${i}" var="flag" scope="session">						
							<span class="currentPage">${i}</span>
						</c:if>
						<c:if test="${not flag}">
							<a onclick = "show(${i})">${i}</a>
						</c:if>			
					</c:forEach>
					<a onclick = "show(${userOrderPage.getNextPage()})" >下一页</a>
					<a onclick = "show(${userOrderPage.getLastPage()})">尾页</a>
					第${userOrderPage.getPage()}/${userOrderPage.getLastPage()}页
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>