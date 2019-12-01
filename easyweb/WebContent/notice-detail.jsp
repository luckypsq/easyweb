<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.yc.easyweb.bean.*"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=application.getContextPath() %>/css/index.css"/>
	<script src="<%=application.getContextPath() %>/js/main.js"></script>
	<title>公告详情</title>
</head>
<body >
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="mainbody" style="background: #FFF url(images/bodybg.png) repeat-x;">
<%
		NoticeBiz noticeBiz = new NoticeBiz();
		Notice notice = new Notice();
		List<Notice> nList = noticeBiz.selectAll(notice);
		if(request.getParameter("nid") != null && !request.getParameter("nid").isEmpty()){
			notice.setNid(Long.parseLong(request.getParameter("nid")));
		}
		Notice noticeShow = noticeBiz.selectSingle(notice);
%>
	<div class="container clearfix" style="background-color: white">
		<div class="mainbody_topbg"></div>
		<div class="bread">当前位置：
			<a href="<%=application.getContextPath() %>/lhoption/index.jsp">首页</a> >
			<a href="<%=application.getContextPath() %>/lhoption/notice.jsp">公告</a> >
			<a href="<%=application.getContextPath() %>/notice-detail.jsp?nid=<%=noticeShow.getNid()%>">公告详情</a>
		</div>
		<div class="maincontent fl">
			<div class="post">
				<h2><a href="<%=application.getContextPath() %>/notice-detail.jsp?nid=<%=noticeShow.getNid()%>"">华南地区因暴雨天气部分订单推迟配送</a></h2>
				<div class="postdata">
					<div class="date"><%=noticeShow.getNtime()%></div>
					<div class="cate">发表于 <a href="#">公告</a> | </div>
					<div class="cate">浏览量: <span><%=noticeShow.getNnumber()%></span>次</div>
				</div>
				<div class="content">
					<p><%=noticeShow.getNcontent()%></p>
					<p style="text-align: right;"><%=noticeShow.getNauthor()%><br /><%=noticeShow.getNtime()%></p>
				</div>
			</div>
		</div>
		<div class="sidebar fr">
			<ul>
				<li>
					<h2>最新公告</h2>
					<ul>
					<%
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