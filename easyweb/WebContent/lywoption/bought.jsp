<%@page import="java.util.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="com.yc.easyweb.dao.lyw.*"%>
<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="css/index.css"/>
	<link rel="stylesheet" href="css/font-awesome.min.css"/>
	<script src="js/main.js"></script>
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
			var url ="dodelete.jsp?itemid="+itemid;
			xmlhttp.open("POST",url,true);
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var json = xmlhttp.responseText.replace(/\s/gi,"");
					eval("var b = " + json);
					console.info(b);
					alert(b.msg);
					if(b.code==1){
						location.href="bought.jsp";
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
			<a href="index.jsp"><img src="images/logo4.png" alt=""/></a>
		</div>
		<div class="seacher fl">
			<form action="" method="post">
				<input type="text" placeholder="小伙伴，你想找什么?"/><input type="submit" value="搜 索"/>
			</form>
			<p>热门搜索：<a href="#">自行车</a> <a href="#">笔记本</a> <a href="#">散热器</a> <a href="#">考研资料</a> <a href="#">摩托车</a> <a href="#">手机</a> <a href="#">轮滑鞋</a> <a href="#">显示器</a> <a href="#">显示器</a> <a href="#">显示器</a> <a href="#">显示器</a></p>
		</div>
		<div class="mm fr clearfix">
			<a href="list.jsp">我要买</a>
			<a href="publish.jsp">我要卖</a>
		</div>
	</div>
</div>

<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="index.jsp">首页</a> >
			<a href="member.jsp">个人中心</a> >
			<a href="bought.jsp">已买书籍</a>
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
						<li><a href="published.jsp">已发布</a></li>
						<li><a href="bought.jsp">已买书籍</a></li>
						<li><a href="bought2.jsp">购物车</a></li>
						<li><a href="publish.jsp">发布书籍</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="help-r fr">
			<div class="help-item-title">已买书籍</div>
			<div class="help-main">
				<div class="product-item clearfix">
					<div class="name fl">
						<span style="margin-left: 150px">书名</span>
					</div>
					<div class="attr fr">
						<ul class="clearfix">
							<li>总价</li>
							<li>买入时间</li>
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
					int rows = 12;	
					User user =(User)session.getAttribute("loginedUser");
					long uid=user.getUid();
					PageCart Page = dbHelper.selectPageForMysql(ipage, rows,uid);
						for(Bought bought1 : Page.getData()){
					%>
				<div class="pro">
					<div class="product-attr">
						<div class="product-name fl">
							<div class="pic-thumb fl"><a href="detail.jsp"  ><img class="middle" src="<%=bought1.getBimg()%>"></a></div>
							<div class="product-title fl">
								<a href="detail.jsp" class="ellipsis"><%=bought1.getBname() %></a><br>
								<span><%=bought1.getBucollege() %></span>
								<span><%=bought1.getBumajor() %></span>
								<span><%=bought1.getBclass() %></span>
							</div>
						</div>
						<div class="product-price fr">
							<ul class="clearfix">
								<li><%=bought1.getTotal() %></li>
								<li><%=bought1.getEotime() %></li>
								<li class="edit" style="width: 110px">
								<%
								String state = null;
								 switch (bought1.getEostate()) {
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
									state="退货申请中";
								   break;
								  case 5:
									state="退款成功";
								  case 6:
									state="订单取消";
								   break;
								  default:
									state="已接收";
								   break;
								  }
								 String itemid1=bought1.getItemid();
								 int count1=bought1.getCount();
								 double total1=bought1.getTotal(); 
								 String eoaddr1 =bought1.getEoaddr();
								 String eotype1 =bought1.getEotype();
								 
								%>
									<span class="sell"><%=state%></span>
								</li>
								<li  class="edit1" style="width: 50px" >
								<form action="buy.jsp?itemid=<%=itemid1%>&count=<%=count1%>&total=<%=total1%>&eoaddr=<%=eoaddr1%>&eotype=<%=eotype1%>"
									method="post" novalidate="novalidate"
									>
									<input type="submit" value="购买" >
								</form>
								</li>
								<li class="edit2" style="width: 50px" >
								<form action="boughtupdate.jsp?itemid=<%=itemid1%>&count=<%=count1%>&total=<%=total1%>"
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
					<a href="bought.jsp?Page=<%=Page.getFirstPage()%>">首页</a>
					<a href="bought.jsp?Page=<%=Page.getPreviousPage()%>">上一页</a>
					<%
						for(int j=1; j<=Page.getLastPage() ; j++){ 
						if(Page.getPage()==j){ %>
							<span class="currentPage"><%=j%></span>
							<%} else { %>
							<a href="bought.jsp?Page=<%=j%>"><%=j%></a>
							<%} %>
				
					<%} %>
					<a href="bought.jsp?Page=<%=Page.getNextPage()%>">下一页</a>
					<a href="bought.jsp?Page=<%=Page.getLastPage()%>">尾页</a>
					第<%=Page.getPage() %>/<%=Page.getLastPage()%>页
				</div>
			</div>
		</div>
	</div>
</div>




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