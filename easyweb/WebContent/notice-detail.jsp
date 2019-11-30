<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css"/>
	<script src="js/main.js"></script>
	<title>Document</title>
</head>
<body >
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="mainbody" style="background: #FFF url(images/bodybg.png) repeat-x;">
	<div class="container clearfix" style="background-color: white">
		<div class="mainbody_topbg"></div>
		<div class="bread">当前位置：
			<a href="<%=application.getContextPath() %>/lhoption/index.jsp">首页</a> >
			<a href="<%=application.getContextPath() %>/lhoption/notice.jsp">公告</a> >
			<a href="<%=application.getContextPath() %>/notice-detail.jsp">公告详情</a>
		</div>
		<div class="maincontent fl">
			<div class="post">
				<h2><a href="notice-detail.html">华南地区因暴雨天气部分订单推迟配送</a></h2>
				<div class="postdata">
					<div class="date">六月 30th, 2014</div>
					<div class="cate">发表于 <a href="#">公告</a> | </div>
					<div class="cate">浏览量: <span>98</span>次</div>
				</div>
				<div class="content">
					<p>亲爱的易书网顾客，您好：</p>
					<p style="text-indent: 2em;"><strong>天津：</strong>受天津地区突发事件的影响，发往滨海新区（塘沽）的订单会有延迟配送的情况，如果您订购的商品未能及时送达，请先耐心等待！感谢大家的理解，惟愿平安！</p>
					<p style="text-indent: 2em;"><strong>福建：</strong>受暴雨天气影响，8月19日易书网在福建地区的配送服务将无法正常进行，受影响的订单预计推迟1天配送，如果您订购的商品未能及时送达，请先耐心等待。</p>
					<p style="text-indent: 2em;"><strong>江苏：</strong>受暴雨天气影响，8月19日至8月20日期间，易书网在江苏地区的配送服务将无法正常进行，受影响的订单预计推迟1天配送，如果您订购的商品未能及时送达，请先耐心等待。</p>
					<p style="text-indent: 2em;"><strong>湖北、湖南、广西：</strong>受暴雨天气影响，8月18日至8月19日期间，易书网在湖北、湖南、广西部分地区的配送服务将无法正常进行，受影响的订单预计于暴雨过后推迟1天配送,如果您订购的商品未能及时送达，千万别着急，暴雨过后，我们一定会快马加鞭，马不停蹄地为您配。</p>
					<p style="text-indent: 2em;"><strong>云南、贵州、四川：</strong>受暴雨天气影响，8月20日至8月21日期间，易书网在云南、贵州、四川部分地区的配送服务将无法正常进行，受影响的订单预计于暴雨过后推迟1天配送,如果您订购的商品未能及时送达，千万别着急，暴雨过后，我们一定会快马加鞭，马不停蹄地为您配。</p>
					<p style="text-indent: 2em;">给亲爱的造成的不便，请谅解！祝您生活愉快！</p>
					<p style="text-align: right;">易书网客服中心<br />2015年8月19日</p>
				</div>
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
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>