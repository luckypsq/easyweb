<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="com.yc.easyweb.dao.*"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="com.yc.easyweb.common.DbHelper" %>
<%@ page import = "java.lang.*"%>
<%@ page import ="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="<%=application.getContextPath() %>/css/index.css"/>
	<link rel="stylesheet" href="<%=application.getContextPath() %>/css/swiper3.07.min.css"/>
	<script src="<%=application.getContextPath() %>/js/jquery-1.11.2.min.js"></script>
	<script src="<%=application.getContextPath() %>/js/main.js"></script>
	<script src="<%=application.getContextPath() %>/js/koala.min.1.5.js"></script>
	<style>
		.swiper-container {
			width: 1100px;
			height: 300px;
			margin: 0 auto;
		}
	</style>
	<title>易书网</title>
</head>
<body>
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="banner container">
	<img src="<%=application.getContextPath() %>/images/notice.png" alt="" style="width: 1200px;height: auto;"/>
	<div class="clearfix">
		<div class="about fl">
			<h1>易书网</h1>
			<img src="<%=application.getContextPath() %>/images/logo9.png" alt=""/>
			<p><span>易书网</span>是一个网上书商城。力求打造网上最大的中文图书借换系统二手书交换系统力求打造是是网上最大的中文图书借换系统二手书交换系统。易书网来了,让爱书的你花极小的支出(1到2元)就可以读到你喜欢的书且没有后顾之忧哦！</p>
		</div>
		<div id="fsD1" class="focus fl">
			<div id="D1pic1" class="fPic">
				<div class="fcon">
					<a href="javascript:void();"><img src="<%=application.getContextPath() %>/images/focus1.jpg" /></a>
					<span class="shadow"><a href="detail.html">便宜出售一本好书</a></span>
				</div>
				<div class="fcon">
					<a href="javascript:void();"><img src="<%=application.getContextPath() %>/images/focus2.jpg" /></a>
					<span class="shadow"><a href="detail.html">便宜出售一本好书</a></span>
				</div>
				<div class="fcon">
					<a href="javascript:void();"><img src="<%=application.getContextPath() %>/images/focus3.jpg" /></a>
					<span class="shadow"><a href="detail.html">便宜出售一本好书</a></span>
				</div>
				<div class="fcon">
					<a href="javascript:void();"><img src="<%=application.getContextPath() %>/images/focus4.jpg" /></a>
					<span class="shadow"><a href="detail.html">便宜出售一本好书</a></span>
				</div>
				<div class="fcon">
					<a href="javascript:void();"><img src="<%=application.getContextPath() %>/images/focus5.jpg" /></a>
					<span class="shadow"><a href="detail.html">便宜出售一本好书</a></span>
				</div>
			</div>
			<div class="fbg">
				<div class="D1fBt" id="D1fBt">
					<a href="javascript:void(0)" class="current"><i>1</i></a>
					<a href="javascript:void(0)"><i>2</i></a>
					<a href="javascript:void(0)"><i>3</i></a>
					<a href="javascript:void(0)"><i>4</i></a>
					<a href="javascript:void(0)"><i>5</i></a>
				</div>
			</div>
		</div>
		<div class="help fr">
			<h2>最新公告</h2>
			<ul>
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
				<li><a href="<%=application.getContextPath() %>/notice-detail.jsp?nid=<%=nList.get(i).getNid()%>"><%=nList.get(i).getNtitle() %></a></li>
				<% 
						}
					}else{
				%>
				<li>暂无新公告</li>
				<%
					}
				%>
			</ul>
			<h2>新手帮助</h2>
			<ul>
				<li><a href="<%=application.getContextPath() %>/lhoption/help.jsp">如何买书</a></li>
			</ul>
		</div>
	</div>
	<div class="item clearfix" id="item1">
		<h1>教材区<span></span>
		</h1>
		<% 
		
		//教材区书籍类型分类
		
		BookType bookType = new BookType();
		BookTypeBiz biz = new BookTypeBiz();
		List<BookType> list = biz.selectAll(bookType);
		for(int i = 0;i<list.size();i++){
			if(list.get(i).getBtid()<=3 ||list.get(i).getBtid() >= 12 ){
				list.remove(list.get(i));
				i--;
			}
			
		}
		
		//工具书区书籍类型分类
		BookTypeBiz biz1 = new BookTypeBiz();
		List<BookType> list2 = biz.selectAll(bookType);
		for(int i = 0;i<list2.size();i++){
			if(list2.get(i).getBtid()<=11 ){
				list2.remove(list2.get(i));
				i--;
			}
			
		}
		String paramNumber = request.getParameter("page");
		String btid = request.getParameter("btid");
		long id;
		if(btid !=null && Integer.parseInt(btid)>3 && Integer.parseInt(btid)<12){
			id = Integer.parseInt(btid);
		}else{
			id=4;
		}
		Book book = new Book();
		book.setBtid(id);
		
		int iPage = paramNumber == null ? 1 : Integer.parseInt(paramNumber);
		PageBook pPage = DbHelper.selectPageForMysql(iPage, 12, book);
		//教材区书籍展示
		%>
		<div class="list fl">
			<ul class="one">
			<%for(BookType t :list ){ %>
				<li><a href="<%=application.getContextPath() %>/lhoption/index.jsp?btid=<%=t.getBtid() %>"><%=t.getBtnamesecond() %></a>
					<ul class="two">
						<li><a href="<%=application.getContextPath() %>/lywoption/list.jsp"><%=t.getBtnamethird() %></a></li>
					
					</ul>
				</li>
				<%} %>
			</ul>
			
		</div>

		<div class="book-wrap fr">
		
			<div class="book clearfix">
			<% for(Book s : pPage.getData()){%>
			
				<dl>
					<dt><a href="<%=application.getContextPath() %>/detail.jsp?bid=<%=s.getBid()%>"><img src="<%=s.getBimg() %>" alt=""/></a></dt>
					<dd>
						<p ><a href="<%=application.getContextPath() %>/detail.jsp?bid=<%=s.getBid()%>"><%=s.getBname() %></a></p>
						<p>数量：<%=s.getBnum() %></p>
						<p><s>价格：￥<%=s.getBprice() %></s> ￥<%=s.getBprice() %> </p>
					</dd>
				</dl>
				<%} %>
			
			</div>
			<div id="ball_footer" class="ball_footer">
				 <%String condition = "btid=" + btid ; %>
					<a class="firstPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page=1">首页</a>
					<a class="previousPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page=<%=pPage.getPreviousPage()%>">上一页</a>
					<a class="nextPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page=<%=pPage.getNextPage()%>">下一页</a>
					<a class="lastPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page=<%=pPage.getLastPage()%>">尾页</a>
					第<%=pPage.getPage()%>/<%=pPage.getLastPage()%>
				
			</div>	
		</div>
	</div>	
	<div class="item clearfix" id="item2">
		<h1>工具书区<span></span></h1>
		<ul class="tab clearfix">
		<%
			String paramNumber1 = request.getParameter("page1");
			/*String btid1 = request.getParameter("btid"); */
			long id1=14;
			if(btid !=null && Integer.parseInt(btid)>11 ){
				id1 = Integer.parseInt(btid);
			}
			Book book1 = new Book();
			book1.setBtid(id1);
			int iPage1 = paramNumber1 == null ? 1 : Integer.parseInt(paramNumber1);
			
			
			PageBook pPage1 = DbHelper.selectPageForMysql(iPage1, 7, book1);
			
		%>
		
		<%for(BookType  a : list2){ %>
			<li><a class="on" href="<%=application.getContextPath() %>/lhoption/index.jsp?btid=<%=a.getBtid()%>"><%=a.getBtnamesecond() %> </a></li>
		<%} %>
		</ul>
		
		<div class="tab0 tabs clearfix">
			<div class="book clearfix">
			<%for(Book b : pPage1.getData() ){ %>
				<dl>
					<dt><a href="<%=application.getContextPath() %>/detail.jsp?bid=<%=b.getBid() %>>"><img src="<%=b.getBimg() %>" alt=""/></a></dt>
					<dd>
						<p><a href="<%=application.getContextPath() %>/detail.jsp?bid=<%=b.getBid() %>"><%=b.getBname() %></a></p>
						<p>数量：<%=b.getBnum() %></p>
						<p><s>价格：￥<%=b.getBprice() %></s> ￥<%=b.getBprice() %></p>
					</dd>
				</dl>
				<%} %>
				
			
			</div>
			<div id="ball_footer" class="ball_footer">
					
					<a class="firstPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page1=1">首页</a>
					<a class="previousPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page1=<%=pPage1.getPreviousPage()%>">上一页</a>
					<a class="nextPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page1=<%=pPage1.getNextPage()%>">下一页</a>
					<a class="lastPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page1=<%=pPage1.getLastPage()%>">尾页</a>
					第<%=pPage1.getPage()%>/<%=pPage1.getLastPage()%>
				
			</div>
		</div>
		
		
		
		
	<div class="item clearfix" id="item3">
		<h1>分享区<span></span></h1>
		<%
			String paramNumber2 = request.getParameter("page2");
			Book book2 = new Book();
			book2.setBtid(Long.parseLong("3"));
			int iPage2 = paramNumber2 == null ? 1 : Integer.parseInt(paramNumber2);
			PageBook pPage2 = DbHelper.selectPageForMysql(iPage1, 7, book2);
		%>
		
		<div class="tabs book clearfix">
		<% for(Book c:pPage2.getData()){%>
			<dl>
				<dt><a href="<%=application.getContextPath() %>/detail.jsp?bid=<%=c.getBid() %>"><img src="<%=c.getBimg() %>" alt=""/></a></dt>
				<dd>
					<p ><a href="<%=application.getContextPath() %>/detail.jsp?bid=<%=c.getBid() %>"><%=c.getBname() %></a></p>
					<p>数量：<%=c.getBnum() %></p>
					<p><s>价格：￥<%=c.getBprice() %></s> ￥<%=c.getBprice() %></p>
				</dd>

			</dl>
			<%} %>
			
		</div>
		<div id="ball_footer" class="ball_footer">
			<a class="firstPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page2=1">首页</a>
			<a class="previousPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page2=<%=pPage2.getPreviousPage()%>">上一页</a>
			<a class="nextPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page2=<%=pPage2.getNextPage()%>">下一页</a>
			<a class="lastPage" href="<%=application.getContextPath() %>/lhoption/index.jsp?<%=condition %>&page2=<%=pPage2.getLastPage()%>">尾页</a>
			第<%=pPage2.getPage()%>/<%=pPage2.getLastPage()%>
				
		</div>
	</div>

</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
<script type="text/javascript">
	Qfast.add('widgets', { path: "<%=application.getContextPath() %>/js/terminator2.2.min.js", type: "js", requires: ['fx'] });
	Qfast(false, 'widgets', function () {
		K.tabs({
			id: 'fsD1',   //焦点图包裹id
			conId: "D1pic1",  //** 大图域包裹id
			tabId:"D1fBt",
			tabTn:"a",
			conCn: '.fcon', //** 大图域配置class
			auto: 1,   //自动播放 1或0
			effect: 'fade',   //效果配置
			eType: 'click', //** 鼠标事件
			pageBt:true,//是否有按钮切换页码
			bns: ['.prev', '.next'],//** 前后按钮配置class
			interval: 2750  //** 停顿时间
		})
	})
</script>
</body>
</html>