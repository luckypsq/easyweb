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
	<link rel="stylesheet" href="css/index.css"/>
	<script src="js/main.js"></script>
	<title>Document</title>
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="ckeditor/config.js"></script>
</head>
<body >

<jsp:include page="common/header.jsp"></jsp:include>

<div class="mainbody" style="background: #FFF url(images/bodybg.png) repeat-x;">
	<div class="container clearfix" style="background-color: white">
		<div class="mainbody_topbg"></div>
		<div class="bread">当前位置：
			<a href="index.html">首页</a> >
			<a href="notice.html">公告</a>
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
				<h2><a href="notice-detail.jsp?nid=">华南地区因暴雨天气部分订单推迟配送</a></h2>
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
					
					<a class="firstPage" href="notice.jsp?page=1">首页</a>
					<a class="previousPage" href="notice.jsp?page=<%=pPage.getPreviousPage()%>">上一页</a>
					<a class="nextPage" href="notice.jsp?page=<%=pPage.getNextPage()%>">下一页</a>
					<a class="lastPage" href="notice.jsp?page=<%=pPage.getLastPage()%>">尾页</a>
					第<%=pPage.getPage()%>/<%=pPage.getLastPage()%>
				
			</div>	
		</div>
		<div class="sidebar fr">
			<ul>
				<li>
					<h2>最新公告</h2>
					<ul>
						<li><a href="notice-detail.html">部分地区订单推迟配送 </a></li>
						<li><a href="notice-detail.html">购爱星期三，平安信用卡支付选银联，购减二重礼 </a></li>
						<li><a href="notice-detail.html">自营家居约惠七夕满300减150，满200减80 </a></li>
						<li><a href="notice-detail.html">北京银行暂停业务通知 </a></li>
						<li><a href="notice-detail.html">部分地区订单推迟配送 </a></li>
						<li><a href="notice-detail.html">关于北京地区快件实行实名收寄的公告 </a></li>
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