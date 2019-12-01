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
    <title>主页</title>
	<link rel="stylesheet" href="<%=application.getContextPath()%>/css/index.css"/>
	<script src="<%=application.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
	<script src="<%=application.getContextPath()%>/js/main.js"></script>
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
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="list-main">

<% 
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
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
			<a href="<%=application.getContextPath()%>/lhoption/index.jsp">首页</a> >
			<a href="<%=application.getContextPath()%>/lywoption/list.jsp">列表</a>
		</div>
		<ul class="select">
			<li class="select-list">
				<dl id="select1">
					<dt>学校：</dt>
					<%if(buniversity != null && !buniversity.isEmpty()){ %>
					<dd class="select-all "><a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition1%>&<%=condition2%>&<%=condition3%>">全部</a></dd>
					<% }else {%>
					<dd class="select-all selected"><a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition1%>&<%=condition2%>&<%=condition3%>">全部</a></dd>
					<%} %>

					<%	
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
					 			
					 			<dd class="<%=cls%>"><a  href="<%=application.getContextPath()%>/lywoption/list.jsp?buniversity=<%=s%>&<%=condition1%>&<%=condition2%>&<%=condition2%>"><%=s%></a></dd>
					 	<% 	}
					 		
					   }%>   
				</dl>
			</li>
			<li class="select-list">
				<dl id="select2">
					<dt>学院：</dt>
					<%if(bucollege != null && !bucollege.isEmpty()){ %>
					<dd class="select-all "><a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition%>&<%=condition2%>&<%=condition3%>">全部</a></dd>
					<% }else {%>
					<dd class="select-all selected"><a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition%>&<%=condition2%>&<%=condition3%>">全部</a></dd>
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
					 			
					 			<dd class="<%=cls%>"><a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition %>&bucollege=<%=s%>&<%=condition2%>&<%=condition3%>"><%=s%></a></dd>
					 	<% 	}
					 		
					   }%> 
				</dl>
			</li>
			<li class="select-list">
				<dl id="select3">
					<dt>专业：</dt>
					<%if(bumajor != null && !bumajor.isEmpty()){ %>
					<dd class="select-all "><a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition%>&<%=condition1%>&<%=condition3%>">全部</a></dd>
					<% }else {%>
					<dd class="select-all selected"><a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition%>&<%=condition1%>&<%=condition3%>">全部</a></dd>
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
					 			<dd class="<%=cls%>"><a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition %>&<%=condition1 %>&bumajor=<%=s%>&<%=condition3%>"><%=s%></a></dd>
					 	<% 	}
					 		
					   }%>
				</dl>
			</li>
			<li class="select-list">
				<dl id="select4">
					<dt>年级：</dt>
					<%if(bclass != null && !bclass.isEmpty()){ %>
					<dd class="select-all "><a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition%>&<%=condition1%>&<%=condition2%>">全部</a></dd>
					<% }else {%>
					
					<dd class="select-all selected"><a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition%>&<%=condition1%>&<%=condition2%>">全部</a></dd>
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
					 			<dd class="<%=cls%>"><a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&bclass=<%=s%>"><%=s%></a></dd>
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
							
							Book book =new Book();
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
							String pageParam = null; 
							if(request.getParameter("Page") != null && !request.getParameter("Page").isEmpty()){
								pageParam = request.getParameter("Page");
							}
							int ipage = pageParam == null ? 1 : Integer.parseInt(pageParam);
							// 每页行数
							int rows = 12;		
							PageBook Page = dbHelper.selectPageForMysql(ipage, rows, book);
							for(Book book2 : Page.getData()){
						%>
							<dl>
								<dt><a href="<%=application.getContextPath()%>/detail.jsp?bid=<%=book2.getBid()%>"><img src="<%=book2.getBimg()%>" alt=""/></a></dt>
								<dd>
									<p><a href="<%=application.getContextPath()%>/detail.jsp?bid=<%=book2.getBid()%>"><%=book2.getBname()%></a></p>
									<p>数量：<%=book2.getBnum()%></p>
									<p>价格：<%=book2.getBprice()%></p> 	
								</dd>	
							</dl>
						<%}%>
					<dl>
			</dl>
			<div class="clearfix"></div>
			<div class="page clearfix">
				<a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&<%=condition3 %>&Page=<%=Page.getFirstPage()%>">首页</a>
				<a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&<%=condition3 %>&Page=<%=Page.getPreviousPage()%>">上一页</a>
				<%
					int countPage = 0;
					int i=Page.getPage();
				for(; i<=Page.getLastPage() ; i++){ 
					if(countPage > 8){
						countPage= 0;
						break;
					}
					countPage++;
					if(Page.getPage()==i){ %>
							<span class="currentPage"><%=i%></span>
						<%} else { %>
							<a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&<%=condition3 %>&Page=<%=i%>"><%=i%></a>
						<%} %>
				
					<%} %>
				<a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&<%=condition3 %>&Page=<%=Page.getNextPage()%>">下一页</a>
				<a href="<%=application.getContextPath()%>/lywoption/list.jsp?<%=condition %>&<%=condition1 %>&<%=condition2 %>&<%=condition3 %>&Page=<%=Page.getLastPage()%>">尾页</a>
				第<%=Page.getPage() %>/<%=Page.getLastPage()%>页
			</div>
		</div>

	</div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>