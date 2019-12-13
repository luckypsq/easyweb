<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
	<link rel="stylesheet" href="${path}/css/index.css"/>
	<script src="${path}/js/jquery-1.7.2.min.js"></script>
	<script src="${path}/js/main.js"></script>
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
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="list-main">
	<div class="container">
		<div class="bread" style="margin-bottom: 0;">当前位置：
			<a href="${path}/back/lhoption/index.jsp">首页</a> >
			<a href="${path}/back/lywoption/list.jsp">列表</a>
		</div>
		
		<ul class="select">
			<li class="select-list">
				<dl id="select1">
					<dt>学校：</dt>
					<dd class="select-all selected"><a href="#">全部</a></dd>
					<dd><a href="#">深圳大学</a></dd>
					<dd><a href="#">深圳技师学院</a></dd>
					<dd><a href="#">深圳广播电视大学</a></dd>
					<dd><a href="#">深圳香港中文大学</a></dd>
					<dd><a href="#">深圳市华强职业技术学校</a></dd>
					<dd><a href="#">深圳大学医学院</a></dd>
					<dd><a href="#">深圳华中科技大学研究院</a></dd>
					<dd><a href="#">深圳南方科技大学</a></dd>
				</dl>
			</li>
			<li class="select-list">
				<dl id="select2">
					<dt>学院：</dt>
					<dd class="select-all selected"><a href="#">全部</a></dd>
					<dd><a href="#">传播学院</a></dd>
					<dd><a href="#">文学院</a></dd>
					<dd><a href="#">外国语学院</a></dd>
					<dd><a href="#">软件学院</a></dd>
					<dd><a href="#">音乐学院</a></dd>
				</dl>
			</li>
			<li class="select-list">
				<dl id="select3">
					<dt>专业：</dt>
					<dd class="select-all selected"><a href="#">全部</a></dd>
					<dd><a href="#">教育技术学</a></dd>
					<dd><a href="#">传播学</a></dd>
					<dd><a href="#">广告学</a></dd>
					<dd><a href="#">不知道了</a></dd>
				</dl>
			</li>
			<li class="select-list">
				<dl id="select4">
					<dt>年级：</dt>
					<dd class="select-all selected"><a href="#">全部</a></dd>
					<dd><a href="#">大一</a></dd>
					<dd><a href="#">大二</a></dd>
					<dd><a href="#">大三</a></dd>
					<dd><a href="#">大四</a></dd>
				</dl>
			</li>
			<li class="select-result">
				<dl>
					<dt>已选条件：</dt>
					<dd class="select-no">暂时没有选择过滤条件</dd>
				</dl>
			</li>
		</ul>
		<div class="tabs book clearfix">
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>
			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>

			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>
			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>
			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>
			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>
			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>
			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>
			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>
			</dl>
			<dl>
				<dt><a href="detail.jsp"><img src="images/book.jpg" alt=""/></a></dt>
				<dd>
					<p><a href="detail.jsp">福尔摩斯探案全集</a></p>
					<p>数量：99</p>
					<p><s>价格：￥25</s> ￥7</p>
				</dd>
			</dl>
			<div class="clearfix"></div>
			<div class="page clearfix">
				<a href="#">首页</a>
				<a href="#">上一页</a>
				<a class="bg-blue" href="#">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<a href="#">下一页</a>
				<a href="#">尾页</a>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>