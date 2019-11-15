<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>易书网后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=application.getContextPath() %>/back/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=application.getContextPath() %>/back/css/style.css" />
<link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/ace.min.css" />
<link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/font-awesome.min.css" />
<link href="<%=application.getContextPath() %>/back/assets/css/codemirror.css" rel="stylesheet">
<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
<script src="<%=application.getContextPath()%>/back/assets/js/ace-extra.min.js"></script>
<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
<!--[if !IE]> -->
<script src="<%=application.getContextPath() %>/back/assets/js/jquery.min.js"></script>
<!-- <![endif]-->
<script src="<%=application.getContextPath() %>/back/assets/dist/echarts.js"></script>
<script src="<%=application.getContextPath() %>/back/assets/js/bootstrap.min.js"></script>
<title></title>
</head>
<body>
	<div class="page-content clearfix">
		<div class="alert alert-block alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<i class="icon-remove"></i>
			</button>
			<%
				//获取系统当前时间
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss/SSS");
				Date date = new Date();
				String[] dateStr = df.format(date).split("/");
				pageContext.setAttribute("date", dateStr);
			%>
			<i class="icon-ok green"></i>
			欢迎使用
			<strong class="green">易书网后台管理系统<small>(v1.1)</small></strong>
			,你本次登录时间为${date[0] }年${date[1] }月${date[2] }日${date[3] }时${date[4] }分
		</div>
		<div class="state-overview clearfix">
			<div class="col-lg-3 col-sm-6">
				<section class="panel"> <a href="<%=application.getContextPath() %>/back/user/user_list.jsp" title="商城会员">
					<div class="symbol terques">
						<i class="icon-user"></i>
					</div>
					<div class="value">
					<%
						UserBiz userBiz = new UserBiz();
						User user = new User();
						user.setUstate(1);
						user.setUtype(1);
						List<User> userList1 = userBiz.selectAll(user);
						user.setUtype(2);
						List<User> userList2 = userBiz.selectAll(user);
						user.setUtype(3);
						List<User> userList3 = userBiz.selectAll(user);
						long userNum = userList1.size()+userList2.size()+userList3.size();
					%>
						<h1><%=userNum %></h1>
						<p>书城用户</p>
					</div>
				</a> </section>
			</div>
			<div class="col-lg-3 col-sm-6">
				<section class="panel"><a href="<%=application.getContextPath() %>/back/book/Products_List.jsp" title="商城订单">
				<div class="symbol red">
					<i class="icon-tags"></i>
				</div>
				<div class="value">
				<%
					BookBiz bookBiz = new BookBiz();
					Book book = new Book();
					book.setBstate(1);
					List<Book> bookList = bookBiz.selectAll(book);
					pageContext.setAttribute("bookList", bookList);
				%>
					<h1>${bookList.size() }</h1>
					<p>书籍数量</p>
				</div>
				</section>
			</div>
			<div class="col-lg-3 col-sm-6">
				<section class="panel"><a href="<%=application.getContextPath() %>/back/order/Orderform.jsp" title="商城订单">
				<div class="symbol yellow">
					<i class="icon-shopping-cart"></i>
				</div>
				<div class="value">
				<%
					//1.查询全部订单数
					EorderBiz eorderBiz = new EorderBiz();
					Eorder eorder = new Eorder();
					//eorder.setEotime(dateStr[0]+"/"+dateStr[1]+"/"+dateStr[2]);
					List<Eorder> eorderList = eorderBiz.selectAll(eorder);
					pageContext.setAttribute("eorderList", eorderList);
				%>
					<h1>${eorderList.size() }</h1>
					<p>书籍订单</p>
				</div>
				</section>
			</div>
			<div class="col-lg-3 col-sm-6">
				<section class="panel"><a href="<%=application.getContextPath() %>/back/order/Amounts.jsp" title="交易记录">
				<div class="symbol blue">
					<i class="icon-bar-chart"></i>
				</div>
				<div class="value">
				<%
					double num = 0.0;
					EorderitemBiz eorderitemBiz = new EorderitemBiz();
					List<Eorderitem> eorderitemList = null;
					Eorderitem  eorderitem ; 
					if(eorderList.size() != 0){
						//根据查询出来的订单存储订单id
						for(Eorder e : eorderList){
							eorderitem = new Eorderitem ();
							eorderitem.setEoid(e.getEoid());
							eorderitemList = eorderitemBiz.selectAll(eorderitem);
							//根据查询出来的订单详情表的数据计算总和
							if(eorderitemList.size() != 0 ){
								for(Eorderitem eo : eorderitemList){
									num = eo.getTotal() + num;
								}
							}
						}
					}
				%>
					<h1>￥<%=num %></h1>
					<p>交易金额</p>
				</div>
				</section>
			</div>
		</div>
		<!--实时交易记录-->
		<div class="clearfix">
			<div class="Order_Statistics ">
				<div class="title_name">订单统计信息</div>
				<table class="table table-bordered">
					<tbody>
					<%
						//eorderList保存着全部订单
						//状态1. 2.未处理3.代发货4.未处理5.交易失败6.交易失败7.已成交
						//配送方式(1.送货上门2.自取)
						long num1 = 0;//未处理订单eostate == 2 ||eostate == 4 && eotype==1
						long num2 = 0;//待发货订单eostate == 3  && eotype==1
						long num3 = 0;//待结算订单eotype==2
						long num4 = 0;//已成交订单数eostate == 7
						long num5 = 0;//交易失败eostate == 5 ||eostate == 6 && eotype==1
						for(Eorder eo : eorderList){
							if(eo.getEotype().equals("店面接取")){
								num3 ++;
							}else if(eo.getEostate() == 7){
								num4++;
							}else if((eo.getEostate() == 2 || eo.getEostate() == 4) && eo.getEotype().equals("网上配送")){
								num1++;
							}else if(eo.getEostate() == 3  && eo.getEotype().equals("网上配送")){
								num2++;
							}else if((eo.getEostate() == 5 || eo.getEostate() ==6) && eo.getEotype().equals("网上配送")){
								num5++;
							}
						}
					%>
						<tr>
							<td class="name">未处理订单：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/order/Order_handling.jsp"><%=num1%></a>&nbsp;个</td>
						</tr>
						<tr>
							<td class="name">待发货订单：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/order/Order_handling.jsp"><%=num2%></a>&nbsp;个</td>
						</tr>
						<tr>
							<td class="name">待结算订单：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/order/Order_handling.jsp"><%=num3 %></a>&nbsp;个</td>
						</tr>
						<tr>
							<td class="name">已成交订单数：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/order/Order_handling.jsp"><%=num4 %></a>&nbsp;个</td>
						</tr>
						<tr>
							<td class="name">交易失败：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/order/Order_handling.jsp"><%=num5 %></a>&nbsp;个</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="Order_Statistics">
				<div class="title_name">书籍统计信息</div>
				<table class="table table-bordered">
					<tbody>
					<%
					//bookList存储所有书籍信息
					// bstate;//状态(1可用，2.删除3.售罄)
					long bnum1 = 0;
					long bnum2 = 0;
					long bnum3 = 0;
					for(Book book1 : bookList){
						if(book1.getBstate() == 3){
							bnum1++;
						}else if(book1.getBstate() == 1){
							bnum2++;
						}else if(book1.getBstate() == 2){
							bnum3++;
						}
					}
					%>
						<tr>
							<td class="name">书籍总数：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/book/Products_List.jsp">${bookList.size() }</a>&nbsp;个</td>
						</tr>
						<tr>
							<td class="name">售罄书籍：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/book/Products_List.jsp"><%=bnum1 %></a>&nbsp;个</td>
						</tr>
						<tr>
							<td class="name">上架书籍：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/book/Products_List.jsp"><%=bnum2 %></a>&nbsp;个</td>
						</tr>
						<tr>
							<td class="name">下架书籍：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/book/Products_List.jsp"><%=bnum3 %></a>&nbsp;个</td>
						</tr>
						<tr>
							<td class="name">商品评论：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/book/#">2176</a>&nbsp;条</td>
						</tr>

					</tbody>
				</table>
			</div>
			<%-- <div class="Order_Statistics">
				<div class="title_name">会员登录统计信息</div>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td class="name">注册会员登录：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/#">3240</a>&nbsp;次</td>
						</tr>
						<tr>
							<td class="name">新浪会员登录：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/#">1130</a>&nbsp;次</td>
						</tr>
						<tr>
							<td class="name">支付宝登录：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/#">1130</a>&nbsp;次</td>
						</tr>
						<tr>
							<td class="name">QQ会员登录：</td>
							<td class="munber"><a href="<%=application.getContextPath() %>/back/#">1130</a>&nbsp;次</td>
						</tr>
					</tbody>
				</table>
			</div> --%>
			<!--<div class="t_Record">
               <div id="main" style="height:300px; overflow:hidden; width:100%; overflow:auto" ></div>     
              </div> -->
			<div class="news_style">
				<div class="title_name">最新公告</div>
				<ul class="list">
				<%
					NoticeBiz noticeBiz = new NoticeBiz();
					Notice notice = new Notice();
					List<Notice> nList = noticeBiz.selectAll(notice);
					if(nList.size() != 0){
						for(int i=0; i<nList.size();i++){
							if(i == 6){
								break ;
							}
				%>
				<li><i class="icon-bell red"></i><a href="<%=application.getContextPath() %>/notice-detail.jsp?nid=<%=nList.get(i).getNid()%>"><%=nList.get(i).getNtitle() %></a></li>
				<% 
						}
					}else{
				%>
				<li><i class="icon-bell red"></i>暂无新公告</li>
				<%
					}
				%>
				</ul>
			</div>
		</div>
		<!--记录-->
		<div class="clearfix">
			<div class="home_btn">
				<div>
					<a href="<%=application.getContextPath() %>/back/book/picture-add.jsp" title="添加书籍"
						class="btn  btn-info btn-sm no-radius"> <i class="bigger-200"><img
							src="<%=application.getContextPath() %>/back/images/icon-addp.png" /></i>
						<h5 class="margin-top">添加书籍</h5>
					</a> <a href="<%=application.getContextPath() %>/back/book/Category_Manage.jsp" title="书籍分类"
						class="btn  btn-primary btn-sm no-radius"> <i
						class="bigger-200"><img src="<%=application.getContextPath() %>/back/images/icon-cpgl.png" /></i>
						<h5 class="margin-top">书籍分类</h5>
					</a> <a href="<%=application.getContextPath() %>/back/admin/admin_info.jsp" title="个人信息"
						class="btn  btn-success btn-sm no-radius"> <i
						class="bigger-200"><img src="<%=application.getContextPath() %>/back/images/icon-grxx.png" /></i>
						<h5 class="margin-top">个人信息</h5>
					</a> <a href="<%=application.getContextPath() %>/back/system/Systems.jsp" title="系统设置"
						class="btn  btn-info btn-sm no-radius"> <i class="bigger-200"><img
							src="<%=application.getContextPath() %>/back/images/xtsz.png" /></i>
						<h5 class="margin-top">系统设置</h5>
					</a> <a href="<%=application.getContextPath() %>/back/order/Order_handling.jsp" title="书籍订单"
						class="btn  btn-purple btn-sm no-radius"> <i
						class="bigger-200"><img src="<%=application.getContextPath() %>/back/images/icon-gwcc.png" /></i>
						<h5 class="margin-top">书籍订单</h5>
					</a> <a href="<%=application.getContextPath() %>/back/book/picture-add.jsp" title="添加广告"
						class="btn  btn-pink btn-sm no-radius"> <i class="bigger-200"><img
							src="<%=application.getContextPath() %>/back/images/icon-ad.png" /></i>
						<h5 class="margin-top">添加广告</h5>
					</a> <a href="<%=application.getContextPath() %>/back/notice/article_add.jsp" title="添加公告"
						class="btn  btn-info btn-sm no-radius"> <i class="bigger-200"><img
							src="<%=application.getContextPath() %>/back/images/icon-addwz.png" /></i>
						<h5 class="margin-top">添加公告</h5>
					</a>
				</div>
			</div>

		</div>

	</div>
</body>
</html>
<script type="text/javascript">
	//面包屑返回值
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.iframeAuto(index);
	$('.no-radius').on('click', function() {
		var cname = $(this).attr("title");
		var chref = $(this).attr("href");
		var cnames = parent.$('.Current_page').html();
		var herf = parent.$("#iframe").attr("src");
		parent.$('#parentIframe').html(cname);
		parent.$('#iframe').attr("src", chref).ready();
		;
		parent.$('#parentIframe').css("display", "inline-block");
		parent.$('.Current_page').attr({
			"name" : herf,
			"href" : "javascript:void(0)"
		}).css({
			"color" : "#4c8fbd",
			"cursor" : "pointer"
		});
		//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
		parent.layer.close(index);

	});
	$(document).ready(function() {

		$(".t_Record").width($(window).width() - 640);
		//当文档窗口发生改变时 触发  
		$(window).resize(function() {
			$(".t_Record").width($(window).width() - 640);
		});
	});
</script>
l>
