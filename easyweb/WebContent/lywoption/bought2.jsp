<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="java.util.*"%>
<%@page import="com.yc.easyweb.dao.lyw.*"%>
<%@page import="com.yc.easyweb.biz.*"%>
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
	function deleteone(eostate,itemid){
		if(eostate==5 || eostate==7){
			if(xmlhttp!=null){
				// 定义请求地址
				var url ="<%=application.getContextPath()%>/lywoption/dodelete2.jsp?itemid="+itemid;
				xmlhttp.open("POST",url,true);
				xmlhttp.onreadystatechange=function(){
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
						var json = xmlhttp.responseText.replace(/\s/gi,"");
						eval("var b = " + json);
						console.info(b);
						alert(b.msg);
						if(b.code==1){
							location.href="<%=application.getContextPath()%>/lywoption/bought2.jsp";
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
	}else{
		alert("未收到货或为退货无法删除。");
	}
}
	</script>
</head>
<style>
	.help-main p {
		line-height: 50px;
	}
</style>
<body >
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="<%=application.getContextPath()%>/lhoption/index.jsp">首页</a> >
			<a href="<%=application.getContextPath()%>/lywoption/member.jsp">个人中心</a> >
			<a href="<%=application.getContextPath()%>/lywoption/bought2.jsp">已买书籍</a>
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
			<div class="help-item-title">已买书籍</div>
			
			<%
				DbHelper dbHelper =new DbHelper();
				String pageParam = request.getParameter("Page");
				int ipage = pageParam == null ? 1 : Integer.parseInt(pageParam);
				// 每页行数
				int rows = 3;	
				User user =(User)session.getAttribute("loginedUser");
				long uid=user.getUid();
				PageEorder Page = dbHelper.selectPageForMysql2(ipage, rows, uid);
				
				for(Eorder eorder2 : Page.getData()){%>
				<table class="cart1" id="cart1" width="100%" height="250px"
			cellspacing="0" cellpadding="0" bordercolor="#ADD3EF" border="1">
					<tbody>
				<tr height="50px">
					<td class="ct01" id="ct01"><span><bstyle="color: red;">订单号：<%=eorder2.getEoid()%></b>
					</span> </td>

				</tr>
				<tr>
					<td class="ct02" id="ct02">
						<div class="div_table">
							<table class="eo" id="eo" width="100%" height="180px"
								cellspacing="0" cellpadding="1" border="1" bordercolor="#ADD3EF">
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
								<%
								EorderBiz eoBuyBiz = new EorderBiz();
								OrderDetial orderBuy = new OrderDetial();
								if(eorder2.getEoid() != null){
									orderBuy.setEoid(eorder2.getEoid());
								}
								List<OrderDetial> buy_show = eoBuyBiz.selectDetail(orderBuy);
								String state = null;
								for(OrderDetial bought:buy_show){
									switch (bought.getEostate()) {
								  	  case 1:
								  		state="待付款";
									   break;
									  case 2:
									    state="待发货";
									   break;
									  case 3:
										state="已发货";
									   break;
									  case 4:
											state="退款申请中";
										   break;
									  case 5:
											state="退款成功";
										   break;
									  case 6:
											state="订单取消";
										   break;
									  case 7:
											state="已接收";
										   break;
									  }
								
								%>
									<tr style="text-align:center">
										<td style="width:80px;"><%=bought.getBname() %></td>
										<td style="width:100px;"><img alt="" src="<%=bought.getBimg()%>">   </td>	
										<td style="width:80px;"><%=bought.getUname() %></td>	
										<td style="width:80px;"><%=bought.getEotime() %></td>	
										<td style="width:80px;"><%=bought.getCount() %></td>	
										<td style="width:80px;"><%=bought.getTotal() %></td>
										<td style="width:80px;"><%=state == null? "暂无状态":state %></td>	
										<td style="width:180px;">
										<input type="button" value="售后" >
										<input type="button" value="退货" >
										<input type="button" value="删除" onclick="deleteone(<%=bought.getEostate()%>,<%=bought.getEoid()%>)">
										</td>
									</tr>
								<% } %>
							</table>
						</div>
					</td>
				</tr>
				<tr height="50px">
					<td style="text-align:right;">
						<div class="eo1" id="eo1">
							<span>收货地址：<%=eorder2.getEoaddr() %></span><span>配送方式:<%=eorder2.getEotype() %></span>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
				<%}
			%>
				<div class="page clearfix">
					<a href="<%=application.getContextPath()%>/lywoption/bought2.jsp?Page=<%=Page.getFirstPage()%>">首页</a>
					<a href="<%=application.getContextPath()%>/lywoption/bought2.jsp?Page=<%=Page.getPreviousPage()%>">上一页</a>
					<%for(int i=1; i<=Page.getLastPage() ; i++){ %>
					
							<%if(Page.getPage()==i){ %>
							<span class="currentPage"><%=i%></span>
							<%} else { %>
							<a href="<%=application.getContextPath()%>/lywoption/bought2.jsp?Page=<%=i%>"><%=i%></a>
							<%} %>
				
						<%} %>
					<a href="<%=application.getContextPath()%>/lywoption/bought2.jsp?Page=<%=Page.getNextPage()%>">下一页</a>
					<a href="<%=application.getContextPath()%>/lywoption/bought2.jsp?Page=<%=Page.getLastPage()%>">尾页</a>
					第<%=Page.getPage() %>/<%=Page.getLastPage()%>页
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>