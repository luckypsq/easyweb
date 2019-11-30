<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="java.util.*"%>
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
	function deleteone(eostate,itemid){
		if(eostate==5 || eostate==7){
			if(xmlhttp!=null){
				// 定义请求地址
				var url ="dodelete2.jsp?itemid="+itemid;
				xmlhttp.open("POST",url,true);
				xmlhttp.onreadystatechange=function(){
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
						var json = xmlhttp.responseText.replace(/\s/gi,"");
						eval("var b = " + json);
						console.info(b);
						alert(b.msg);
						if(b.code==1){
							location.href="bought2.jsp";
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
						<li><a href="publish.jsp">发布书籍</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="help-r fr">
			<div class="help-item-title">已买书籍</div>
			<table class="cart1" id="cart1" width="100%" height="250px"
			cellspacing="0" cellpadding="0" bordercolor="#ADD3EF" border="1">
			<%
				DbHelper dbHelper =new DbHelper();
				String pageParam = request.getParameter("Page");
				int ipage = pageParam == null ? 1 : Integer.parseInt(pageParam);
				// 每页行数
				int rows = 12;	
				User user =(User)session.getAttribute("loginedUser");
				long uid=user.getUid();
				PageEorder Page = dbHelper.selectPageForMysql2(ipage, rows, uid);
				
				for(Eorder eorder2 : Page.getData()){%>
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
									<th>书名</th>
									<th>用户名</th>	
									<th>下单时间</th>	
									<th>单价</th>	
									<th>数量</th>	
									<th>总价</th>
									<th>状态</th>	
									<th>操作</th>	
								</tr>
								<%
								String sql1 ="select bname,bprice,eotime,eostate,bimg,itemid,e.eoid,count,total,eoaddr,eotype,uname from book b,eorderitem eo,eorder e  where eo.bid=b.bid and e.uid=? and e.eoid=? and eo.eoid=?;";
								List<Object> params1 =new ArrayList();
								String eoid=eorder2.getEoid();
								params1.add(uid);
								params1.add(eoid);
								params1.add(eoid);
								List<Bought> list =dbHelper.selectAll(sql1, params1, Bought.class);
								System.out.println(list);
								for(Bought bought:list){%>
									<tr>
									<th><%=bought.getBname() %></th>
									<th><%=bought.getUname() %></th>	
									<th><%=bought.getEotime() %></th>	
									<th><%=bought.getBprice() %></th>	
									<th><%=bought.getCount() %></th>	
									<th><%=bought.getTotal() %></th>
									<th><%=bought.getEostate() %></th>	
									<th>
									<input type="submit" value="售后" >
									<input type="submit" value="退货" >
									<%String itemid1=bought.getItemid(); %>
									<%int eostate1=bought.getEostate(); %>
									<input type="submit" value="删除" onclick="deleteone(<%=eostate1%>,<%=itemid1%>)">
									</th>
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
					<a href="bought2.jsp?Page=<%=Page.getFirstPage()%>">首页</a>
					<a href="bought2.jsp?Page=<%=Page.getPreviousPage()%>">上一页</a>
					<%for(int i=1; i<=Page.getLastPage() ; i++){ %>
					
							<%if(Page.getPage()==i){ %>
							<span class="currentPage"><%=i%></span>
							<%} else { %>
							<a href="bought2.jsp?Page=<%=i%>"><%=i%></a>
							<%} %>
				
						<%} %>
					<a href="bought2.jsp?Page=<%=Page.getNextPage()%>">下一页</a>
					<a href="bought2.jsp?Page=<%=Page.getLastPage()%>">尾页</a>
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