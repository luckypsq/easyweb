<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="${path}/css/index.css"/>
	<link rel="stylesheet" href="${path}/css/font-awesome.min.css"/>
	<script src="${path}/js/main.js"></script>
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
			<a href="${path}/lhoption/index.jsp">首页</a> >
			<a href="${path}/lywoption/member.jsp">个人中心</a> >
			<a href="${path}/lhoption/published.jsp">已发布</a>
		</div>
		<jsp:include page="${path}/common/middle.jsp"></jsp:include>
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
				<c:if test="${userPublish.size() == 0}" var="flag" scope="session">						
					<div class="pro">
					<div class="product-attr">
							<span>暂无已发布书籍</span>
					</div>
				</div>
				</c:if>
				<c:if test="${not flag}">	
				<c:forEach items="${userPublish}" var="t">
					<div class="pro">
					<div class="product-attr">
						<div class="product-name fl">
							<div class="pic-thumb fl"><a href="${path}/detail.jsp?bid=${t.bid}""  ><img class="middle" src="${t.bimg }"></a></div>
							<div class="product-title fl">
								<a href="${path}/detail.jsp?bid=${t.bid}" class="ellipsis">${t.bname }</a><br>
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
								<a href="${path}/back/lhoption/publish.jsp?bid=${t.bid }">
									<i class="icon-edit"></i>
									<p>编辑</p>
								</a>
							</span>
									<span class="line"></span>
							<span>
								<a onclick = "deleteBook(this,${t.bid });">
									<i class="icon-trash"></i>
									<p >删除</p>
								</a>
							</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
				</c:forEach>
				<div id="ball_footer" class="ball_footer">
					<a onclick = "show(${userBookPage.getFirstPage()})">首页</a>
					<a onclick = "show(${userBookPage.getPreviousPage()})">上一页</a>
					<c:forEach  var="i" begin="1" end="${userBookPage.getLastPage()}">
						<c:if test="${userBookPage.getPage() == ${i}" var="flag" scope="session">						
							<span class="currentPage">${i}</span>
						</c:if>
						<c:if test="${not flag}">
							<a onclick = "show(${i})">${i}</a>
						</c:if>			
					</c:forEach>
					<a onclick = "show(${userBookPage.getNextPage()})" >下一页</a>
					<a onclick = "show(${userBookPage.getLastPage()})">尾页</a>
					第${userBookPage.getPage()}/${userBookPage.getLastPage()}页
				</div>			   	
				</c:if>
			</div>
			</div>
		</div>
	</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
<script type="text/javascript">
$(function(){  
    show(-1);
});
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
function deleteBook(obj,id){
	if(confirm("确定删除本书？？？")){
		if (xmlhttp != null) {
			// 定义请求地址
			var url = "${path}/book.s?op=delete&bid="+id;
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST", url, true);
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					var msg = xmlhttp.responseText.replace(/\s/gi, "");
					if(msg == "删除成功！"){
						$(obj).parents("ul").remove();
						alert("删除成功！");
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
function show(page){
	$.ajax({
        type: "post",
        url: "book.s?op=userPublishedBook",
        data: param,
        async:true, // 异步请求
        cache:false, // 设置为 false 将不缓存此页面
        dataType: 'json', // 返回对象
        success: function(result) {
            if(result.code == 1){
            	alert(result.msg);
            }else if(result.code == 0){
            	alert(result.msg);
            }
        }
    });
}
</script>
</body>
</html>