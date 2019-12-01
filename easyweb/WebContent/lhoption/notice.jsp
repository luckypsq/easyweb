<%@page import="java.util.List"%>
<%@page import= "com.yc.easyweb.bean.Notice" %>
<%@page import= "com.yc.easyweb.biz.NoticeBiz" %>
<%@page import="com.yc.easyweb.biz.NoticeBiz"%>
<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@page import="com.yc.easyweb.bean.PageNotice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="<%=application.getContextPath()%>/css/index.css"/>
	<script src="js/main.js"></script>
	<title>Document</title>
	<script type="text/javascript" src="<%=application.getContextPath()%>/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=application.getContextPath()%>/ckeditor/config.js"></script>
</head>
<body >

<jsp:include page="common/header.jsp"></jsp:include>

<div class="mainbody" style="background: #FFF url(images/bodybg.png) repeat-x;">
	<div class="container clearfix" style="background-color: white">
		<div class="mainbody_topbg"></div>
		<div class="bread">当前位置：
			<a href="<%=application.getContextPath()%>/lhoption/index.jsp">首页</a> >
			<a href="<%=application.getContextPath()%>/lhoption/notice.jsp">公告</a>
		</div>
		<div class="maincontent fl">
		<%
			String paramNumber = request.getParameter("page"); 
			NoticeBiz biz = new NoticeBiz();
			Notice notice = new Notice();
			
			int iPage = paramNumber == null ? 1 : Integer.parseInt(paramNumber);
			PageNotice pPage = DbHelper.selectPageForMysql(iPage, 5, notice);
		%>
		<%for(Notice t : pPage.getData()){%>
			<div class="post">
				<h2><a href="<%=application.getContextPath()%>/notice-detail.jsp?nid=">华南地区因暴雨天气部分订单推迟配送</a></h2>
				<div class="postdata">
					<div class="date"><%=t.getNtime() %></div>
					<div class="cate">发表于 <a href="notice-detail.html">公告</a> | </div>
					<div class="cate">浏览量: <span><%=t.getNnumber() %></span>次</div>
				</div>
				<div class="post">
				<%-- <%=t.getNcontent() %> --%>
				<textarea rows="10px" cols="10px" name="ckeditor<%=t.getNid() %>" ><%=t.getNcontent() %></textarea>
				<script type="text/javascript">CKEDITOR.replace('ckeditor<%=t.getNid()%>');</script> 
				<p style="text-align: right;"><%=t.getNauthor() %><br>
						<%=t.getNtime() %>
					</p>
				</div>
			</div>
			<%} %>
			
			
			<div id="ball_footer" class="ball_footer">
					
					<a class="firstPage" href="<%=application.getContextPath()%>/lhoption/notice.jsp?page=1">首页</a>
					<a class="previousPage" href="<%=application.getContextPath()%>/lhoption/notice.jsp?page=<%=pPage.getPreviousPage()%>">上一页</a>
					<a class="nextPage" href="<%=application.getContextPath()%>/lhoption/notice.jsp?page=<%=pPage.getNextPage()%>">下一页</a>
					<a class="lastPage" href="<%=application.getContextPath()%>/lhoption/notice.jsp?page=<%=pPage.getLastPage()%>">尾页</a>
					第<%=pPage.getPage()%>/<%=pPage.getLastPage()%>
				
			</div>	
		</div>
		<div class="sidebar fr">
			<ul>
				<li>
					<h2>最新公告</h2>
					<ul>
					<%
					NoticeBiz noticeBiz = new NoticeBiz();
					Notice noticer = new Notice();
					List<Notice> nList = noticeBiz.selectAll(noticer);
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
				%></ul>
				</li>
				<li>
					<h2>公告存档</h2>
					<select  style="width: 130px;margin-top: 10px;">
						<option value="">选择月份</option>
						<option> 2015年八月 </option>
						<option> 2015年七月 </option>
						<option> 2015年六月 </option>
						<option> 2015年五月 </option>
						<option> 2015年四月 </option>
						<option> 2015年三月 </option>
						<option> 2015年二月 </option>
						<option> 2015年一月 </option>
						<option> 2014年十二月 </option>
						<option> 2014年十一月 </option>
						<option> 2014年十月 </option>
						<option> 2014年九月 </option>
						<option> 2014年八月 </option>
						<option> 2014年七月 </option>
						<option> 2014年六月 </option>
						<option> 2014年五月 </option>
						<option> 2014年四月 </option>
						<option> 2014年三月 </option>
						<option> 2014年二月 </option>
						<option> 2014年一月 </option>
						<option> 2013年十二月 </option>
						<option> 2013年十一月 </option>
						<option> 2013年十月 </option>
						<option> 2013年九月 </option>
						<option> 2013年八月 </option>
						<option> 2013年七月 </option>
						<option> 2013年六月 </option>
						<option> 2013年五月 </option>
						<option> 2013年四月 </option>
						<option> 2013年三月 </option>
						<option> 2013年二月 </option>
						<option> 2013年一月 </option>
						<option> 2012年十二月 </option>
						<option> 2012年十一月 </option>
						<option> 2012年十月 </option>
						<option> 2012年九月 </option>
						<option> 2012年八月 </option>
						<option> 2011年九月 </option>
						<option> 2011年八月 </option>
						<option> 2011年七月 </option>
						<option> 2011年六月 </option>
						<option> 2011年四月 </option>
						<option> 2011年三月 </option>
						<option> 2011年二月 </option>
						<option> 2011年一月 </option>
						<option> 2010年十二月 </option>
						<option> 2010年十月 </option>
						<option> 2010年九月 </option>
						<option> 2010年八月 </option>
						<option> 2010年七月 </option>
						<option> 2010年六月 </option>
						<option> 2010年五月 </option>
					</select>
				</li>
			</ul>
			<div class="custom_ads"></div>
		</div>
		<div class="mainbody_bottombg"></div>
	</div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>