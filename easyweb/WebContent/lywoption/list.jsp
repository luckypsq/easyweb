<%@page import="java.util.*"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="com.yc.easyweb.dao.lyw.*"%>
<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="css/index.css"/>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/main.js"></script>
	<title>Document</title>
	<script type="text/javascript">
		$(function(){
			$(".selected").each(function(){
					$("#selectedDd").append($(this).clone()[0])
							
			})
			
		})
		
	</script>
</head>
<body >

<div class="top" id="item4">
	<div class="container clearfix">
		<ul class="clearfix fr">
			<li><a href="join.html#tologin" >登录</a></li>
			<li><a href="join.html#toregister" >注册</a></li>
			<li><a href="member.html" style="border: none">个人中心</a></li>
		</ul>
	</div>
</div>

<div class="header">
	<div class="container clearfix">
		<div class="logo fl">
			<a href="index.html"><img src="images/logo4.png" alt=""/></a>
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

<div class="list-main">
<% 
					 			String buniversity =request.getParameter("buniversity");
								String bucollege =request.getParameter("bucollege");
								String bumajor =request.getParameter("bumajor");
								String bclass =request.getParameter("bclass");
								
								String condition = null ;
					 			String condition1 = null ;
					 			String condition2 = null ;
					 			String condition3 = null ;
								if(buniversity != null && !buniversity.isEmpty()){
									 condition="buniversity=" + buniversity;
								}else {
									 condition="buniversity=";
								}
								if(bucollege != null && !bucollege.isEmpty()){
									 condition1="bucollege=" + bucollege;
								}else {
									 condition1="bucollege=";
								}
								if(bumajor != null && !bumajor.isEmpty()){
									 condition2="bumajor=" + bumajor;
								}else {
									 condition2="bumajor=";
								}
								if(bclass != null && !bclass.isEmpty()){
									 condition3="bclass=" + bclass;
								}else {
									 condition3="bclass=";
								}
								
					 			
					%>
	<div class="container">
		<div class="bread" style="margin-bottom: 0;">当前位置：
			<a href="index.html">首页</a> >
			<a href="list.html">列表</a>
		</div>
		<ul class="select">
			<li class="select-list">
				<dl id="select1">
					<dt>学校：</dt>
					<%if(buniversity != null && !buniversity.isEmpty()){ %>
					<dd class="select-all "><a href="list.jsp?<%=condition1%>&<%=condition2%>&<%=condition3%>">全部</a></dd>
					<% }else {%>
					<dd class="select-all selected"><a href="list.jsp?<%=condition1%>&<%=condition2%>&<%=condition3%>">全部</a></dd>
					<%} %>

					<%	
						Book book =new Book();
						PageBookDao dao =new PageBookDao();
						HashSet<String> set =new HashSet<String>();
						List<Book> list =dao.selectbuniversity();
						for(Book s  : list){
							set.add(s.getBuniversity())	;	
						}
						%>
					 <% for(String s : set){
					 		if(s!=null && !s.isEmpty()){
					 			String cls = s.equals(request.getParameter("buniversity"))? "selected": "";
					 		%>
					 			
					 			<dd class="<%=cls%>"><a  href="list.jsp?buniversity=<%=s%>&<%=condition1%>&<%=condition2%>&<%=condition2%>"><%=s%></a></dd>
					 	<% 	}
					 		
					   }%>   
				</dl>
			</li>
			<li class="select-list">
				<dl id="select2">
					<dt>学院：</dt>
					<%if(bucollege != null && !bucollege.isEmpty()){ %>
					<dd class="select-all "><a href="list.jsp?<%=condition%>&<%=condition2%>&<%=condition3%>">全部</a></dd>
					<% }else {%>
					<dd class="select-all selected"><a href="list.jsp?<%=condition%>&<%=condition2%>&<%=condition3%>">全部</a></dd>
					<%} %>
					<%	

						HashSet<String> set1 =new HashSet<String>();
						List<Book> list1 =dao.selectbucollege();
						for(Book s  : list1){
							set1.add(s.getBucollege());	
							
						}
						%>
					 <% for(String s : set1){
					 		if(s!=null && !s.isEmpty()){
					 			String cls = s.equals(request.getParameter("bucollege"))? "selected": "";
					 			
					 		%>
					 			
					 			<dd class="<%=cls%>"><a href="list.jsp?<%=condition %>&bucollege=<%=s%>&<%=condition2%>&<%=condition3%>"><%=s%></a></dd>
					 	<% 	}
					 		
					   }%> 
				</dl>
			</li>
			<li class="select-list">
				<dl id="select3">
					<dt>专业：</dt>
					<%if(bumajor != null && !bumajor.isEmpty()){ %>
					<dd class="select-all "><a href="list.jsp?<%=condition%>&<%=condition1%>&<%=condition3%>">全部</a></dd>
					<% }else {%>
					<dd class="select-all selected"><a href="list.jsp?<%=condition%>&<%=condition1%>&<%=condition3%>">全部</a></dd>
					<%} %>
					
					<%	

						HashSet<String> set2 =new HashSet<String>();
						List<Book> list2 =dao.selectbumajor();
						for(Book s  : list2){
							set2.add(s.getBumajor());	
							
						}
						%>
					 <% for(String s : set2){
					 		if(s!=null && !s.isEmpty()){
					 			String cls = s.equals(request.getParameter("bumajor"))? "selected": "";
					 			
					 		%>
					 			<dd class="<%=cls%>"><a href="list.jsp?<%=condition %>&<%=condition1 %>&bumajor=<%=s%>&<%=condition3%>"><%=s%></a></dd>
					 	<% 	}
					 		
					   }%>
				</dl>
			</li>
			<li class="select-list">
				<dl id="select4">
					<dt>年级：</dt>
					<%if(bclass != null && !bclass.isEmpty()){ %>
					<dd class="select-all "><a href="list.jsp?<%=condition%>&<%=condition1%>&<%=condition2%>">全部</a></dd>
					<% }else {%>
					
					<dd class="select-all selected"><a href="list.jsp?<%=condition%>&<%=condition1%>&<%=condition2%>">全部</a></dd>
					<%} %>
					<%	

						HashSet<String> set3 =new HashSet<String>();
						List<Book> list3 =dao.selectbclass();
						for(Book s  : list3){
							set3.add(s.getBclass());	
							
						}
						%>
					 <% for(String s : set3){
					 		if(s!=null && !s.isEmpty()){
					 			String cls = s.equals(request.getParameter("bclass"))? "selected": "";
					 			
					 		%>
					 			<dd class="<%=cls%>"><a href="list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&bclass=<%=s%>"><%=s%></a></dd>
					 	<% 	}
					 		
					   }%>
				</dl>
			</li>
			<li class="select-result">
				<dl id="selectedDd">
					<dt>已选条件：</dt>
					
				</dl>
			</li>
		</ul>
		<div class="tabs book clearfix">
			
				<%			
							DbHelper dbHelper =new DbHelper();
							if(buniversity != null && !buniversity.isEmpty()){
								book.setBuniversity(buniversity);
							}
							if(bucollege != null && !bucollege.isEmpty()){
								book.setBucollege(bucollege);
							}
							if(bumajor != null && !bumajor.isEmpty()){
								book.setBumajor(bumajor);
							}
							if(bclass != null && !bclass.isEmpty()){
								book.setBclass(bclass);
							}
							// 第几页
							String pageParam = request.getParameter("Page");
							int ipage = pageParam == null ? 1 : Integer.parseInt(pageParam);
							// 每页行数
							int rows = 12;			
							PageBook Page = dbHelper.selectPageForMysql(ipage, rows, book);
							System.out.print(Page.getData().size());

							for(Book book2 : Page.getData()){
						%>
							<dl>
								<dt><a href="detail.jsp?bid=<%=book2.getBid()%>"><img src="<%=book2.getBimg()%>" alt=""/></a></dt>
								<dd>
									<p><a href="detail.jsp?bid=<%=book2.getBid()%>"><%=book2.getBname()%></a></p>
									<p>数量：<%=book2.getBnum()%></p>
									<p>价格：<%=book2.getBprice()%></p> 	
								</dd>	
							</dl>
						<%}%>
					<dl>
				

			</dl>
			<div class="clearfix"></div>
			<div class="page clearfix">
				<a href="list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&<%=condition3 %>&Page=<%=Page.getFirstPage()%>">首页</a>
				<a href="list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&<%=condition3 %>&Page=<%=Page.getPreviousPage()%>">上一页</a>
				<%for(int i=1; i<=Page.getLastPage() ; i++){ %>
					
						<%if(Page.getPage()==i){ %>
							<span class="currentPage"><%=i%></span>
						<%} else { %>
							<a href="list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&<%=condition3 %>&Page=<%=i%>"><%=i%></a>
						<%} %>
				
					<%} %>
				<a href="list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&<%=condition3 %>&Page=<%=Page.getNextPage()%>">下一页</a>
				<a href="list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&<%=condition3 %>&Page=<%=Page.getLastPage()%>">尾页</a>
				第<%=Page.getPage() %>/<%=Page.getLastPage()%>页
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