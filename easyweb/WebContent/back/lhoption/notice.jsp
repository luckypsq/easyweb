<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="${path}/css/index.css"/>
	<title>网站公告</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$.ajax({
		        type: "post",
		        url: "${path}/notice.s?op=query",
		        data: "",
		        async:true, // 异步请求
		        cache:true, // 设置为 false 将不缓存此页面
		        dataType: 'json', // 返回对象
		        success: function(result) {
		        	if(result.code == -1){
		            	alert(result.msg);
		            }
		        	if(result.code == 0){
		            	alert(result.msg);
		            }
		        	if(result.code == 1){
			        		if(location.href.indexOf('#maincontent fl')==-1){
			   			     location.href=location.href + '#maincontent fl';
			   			     location.reload();
			   		    }
		        	}
		        }
		    });
		});
		function show(page){
			var page1 = "page="+page;
			$.ajax({
		        type: "post",
		        url: "${path}/notice.s?op=query",
		        data: page1,
		        async:true, // 异步请求
		        cache:true, // 设置为 false 将不缓存此页面
		        dataType: 'json', // 返回对象
		        success: function(result) {
		        	if(result.code == -1){
		            	alert(result.msg);
		            }
		        	if(result.code == 0){
		            	alert(result.msg);
		            }
		        	if(result.code == 1){
			        		
		        	}
		        }
		    });
		}
	</script>
</head>
<body >
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="mainbody" style="background: #FFF url(images/bodybg.png) repeat-x;" >
	<div class="container clearfix" style="background-color: white">
		<div class="mainbody_topbg"></div>
		<div class="bread">当前位置：
			<a href="${path}/back/lhoption/index.jsp">首页</a> >
			<a href="${path}/backlhoption/notice.jsp">公告</a>
		</div>
		<div class="maincontent fl" id="maincontent fl">
				<div class="post">
					<c:forEach   items="${allNoticeShow}" var="allNoticeShow">
						<h2><a href="${path}/notice-detail.jsp?nid=${allNoticeShow.nid}">${allNoticeShow.ntitle}</a></h2>
						<div class="postdata">
							<div class="date">${date[0] }年${date[1] }月${date[2] }日</div>
							<div class="cate">发表于 <a href="#">公告</a> | </div>
							<div class="cate">浏览量: <span>${allNoticeShow.nnumber}</span>次</div>
						</div>
						<div class="content">
							<p>${allNoticeShow.ncontent}</p>
							<p style="text-align: right;">${allNoticeShow.nauthor}<br />${allNoticeShow.ntime}</p>
						</div>
					</c:forEach>
				</div>
			<div id="ball_footer" class="ball_footer">
					<a href="javascript:;" class="firstPage" onclick = "show(${noticePage.getFirstPage()})">首页</a>
					<a href="javascript:;" class="previousPage" onclick = "show(${noticePage.getPreviousPage()})">上一页</a>
					<a class="nextPage" href="javascript:;"onclick = "show(${noticePage.getNextPage()})" >下一页</a>
					<a class="lastPage" href="javascript:;" onclick = "show(${noticePage.getLastPage()})">尾页</a>
					第${noticePage.getPage()}/${noticePage.getLastPage()}页
			</div>	
		</div>
		<div class="sidebar fr">
			<ul>
				<li>
					<h2>最新公告</h2>
					<ul>
						<c:if test="${noticeShow.size()> 0}" var="flag" scope="session">
							<c:forEach items="${noticeShow}" var="n">
								<li><i class="icon-bell red"></i><a href="${path}/notice-detail.jsp?nid=${n.nid}">${n.ntitle }</a></li>
							</c:forEach>
						</c:if>
						
						<c:if test="${not flag}">
						   	<li><i class="icon-bell red"></i>暂无新公告</li>
						</c:if>
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
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>