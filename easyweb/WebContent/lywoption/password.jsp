<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="css/index.css"/>
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script src="js/main.js"></script>
	<title>Document</title>
	<script type="text/javascript">
	var xmlhttp;
	var old =0;
	var re =0;
	var ne =0;
	// ajax 验证原密码是否正确
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {
			try {
				xmlhttp = new XMLHttpRequest();
			} catch (e) {
			}
		}
	}
	//校验原密码是否存在
	function checkPassWord(){
		
		// 获取用户填写的原密码
		var oldpassword = document.getElementById("oldpassword").value;
		oldpassword = oldpassword.replace(/\s/gi,"");
		if(oldpassword == ''){
			alert("请填写原密码！");
			return;
		}
		if(xmlhttp!=null){
			// 定义请求地址
			var url ="dooldpassword.jsp?oldpassword="+oldpassword ;
					
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST",url,true);
			
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					// 替换空格
					var msg = xmlhttp.responseText.replace(/\s/gi,"");
					// 使用 span 展示错误信息， 替代 alert(msg); 提升用户体验
					// 原密码正确使用绿色字体， 否则红色字体
					var color = msg == '原密码正确！' ? 'green' : 'red'; 
					// 输入框的后面一个元素是用于展示信息的 span 元素
					document.getElementById("span1").innerText = msg;
					document.getElementById("span1").style.color=color;
					
					if(msg == "原密码正确！"){
						old=1;
					
					}else {
						old=0;
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		}else{
			alert("不能创建XMLHttpRequest对象实例")
		}
	}
	function newPassword(){
		var newpassword = document.getElementById("newpassword").value;
		var reg=/^[a-zA-Z0-9]{6,21}$/;    
		    if(reg.test(newpassword)==false){
		    	document.getElementById("span3").innerText = '密码不能含有非法字符，长度在6-21之间';
				document.getElementById("span3").style.color='red'; 
				ne=0;
		      }else{
		    	document.getElementById("span3").innerText = '密码格式正确';
				document.getElementById("span3").style.color='green'; 
		      	ne=1;
				}
	}	
	function ynPassWord(){
		
		
		// 获取用户填写的新 密码和确认密码
		var newpassword = document.getElementById("newpassword").value;
		var repassword = document.getElementById("repassword").value;

		if(xmlhttp!=null){
			// 定义请求地址
			var url ="dorepassword.jsp?newpassword="+newpassword 
					+ "&repassword=" + repassword;
					
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST",url,true);
			
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					// 替换空格
					var msg = xmlhttp.responseText.replace(/\s/gi,"");
					// 使用 span 展示错误信息， 替代 alert(msg); 提升用户体验
					// 原密码正确使用绿色字体， 否则红色字体
					var color = msg == '密码一致！' ? 'green' : 'red'; 
					// 输入框的后面一个元素是用于展示信息的 span 元素
					document.getElementById("span2").innerText = msg;
					document.getElementById("span2").style.color=color; 
					if(msg == "密码一致！"){
						re=1;
						
					}else {
						re=0;
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		}else{
			alert("不能创建XMLHttpRequest对象实例")
		}
	}
	function password(){
		var oldpassword = document.getElementById("oldpassword").value;
		var newpassword = document.getElementById("newpassword").value;
		var repassword = document.getElementById("repassword").value;
		if(xmlhttp!=null){
				
				if(re==1 && old==1 && ne==1){
				// 定义请求地址
				var url ="dopassword.jsp?oldpassword="+oldpassword
						+ "&newpassword=" + newpassword
						+ "&repassword=" + repassword;
				// 以 POST 方式 开启连接
				// POST 请求 更安全（编码）  提交的数据大小没有限制
				xmlhttp.open("POST",url,true);
			
			
				// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
				// 每次的状态改变都会调用该方法
				xmlhttp.onreadystatechange=function(){
				
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				
						var json = xmlhttp.responseText.replace(/\s/gi,"");
						eval("var user = " + json);
						console.info(user);
						alert(user.msg);
						if(user.code==1){
							//成功跳转到登录页面
							location.href="join.jsp";
						}else{
							alert(user.msg);  // msg 时扩展的 属性	
						}
					}
				};
				// 发送请求

				xmlhttp.send(null);
			}else{
				alert("修改密码失败");
			}
		}else{
			alert("不能创建XMLHttpRequest对象实例");
		}
	
		
}
	</script>
	
</head>
<style>
	.help-main p {
		line-height: 50px;
	}
</style>
<body >

<div class="top" id="item4">
	<div class="container clearfix">
		<ul class="clearfix fr">
			<li><a href="join.jsp#tologin" >登录</a></li>
			<li><a href="join.jsp#toregister" >注册</a></li>
			<li><a href="member.jsp" style="border: none">个人中心</a></li>
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
			<a href="list.html">我要买</a>
			<a href="publish.html">我要卖</a>
		</div>
	</div>
</div>

<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="index.html">首页</a> >
			<a href="member.jsp">个人中心</a> >
			<a href="password.html">修改密码</a>
		</div>
		<div class="help-l fl">
			<div class="help-item">
				<div class="help-item-title">个人中心
					<a href="javascript:void(0)" class="abs"></a>
				</div>
				<div class="help-item-list">
					<ul>
						<li><a href="member.jsp">个人信息</a></li>
						<li><a href="password.html">修改密码</a></li>
					</ul>
				</div>
			</div>
			<div class="help-item">
				<div class="help-item-title">书籍管理
					<a href="javascript:void(0)" class="abs"></a>
				</div>
				<div class="help-item-list">
					<ul>
						<li><a href="published.html">已发布</a></li>
						<li><a href="bought.html">已买书籍</a></li>
						<li><a href="publish.html">发布书籍</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="help-r fr">
			<div class="help-item-title">个人信息</div>
			<div class="help-main">
				<form action="password.jsp"
						method="post" novalidate="novalidate"
						onsubmit="password(); return false">
					<p><span class="nice">原始密码：</span><input type="password" id="oldpassword" name="oldpassword" onblur="checkPassWord()"><span id="span1"></span></p>
					<p><span class="nice">新密码：</span><input type="password" id="newpassword" name="newpassword" onblur="newPassword()"><span id="span3"></span></p>
					<p><span class="nice">确认新密码：</span><input type="password" id="repassword" name="repassword" onblur="ynPassWord()"><span id="span2"></span></p>
					<input class="save" type="submit" value="确认修改"/>
				</form>
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