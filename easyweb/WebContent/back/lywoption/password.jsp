<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="${path}/css/index.css"/>
	<script type="text/javascript" src="${path}/js/jquery-1.7.2.min.js"></script>
	<script src="${path}/js/main.js"></script>
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
			var url ="${path}/user.s?op=checkPwd&oldpassword="+oldpassword ;
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST",url,true);
			
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					// 替换空格
					var msg = xmlhttp.responseText.replace(/\s/gi,"");
					var color = msg == '1' ? 'green' : 'red'; 
					// 输入框的后面一个元素是用于展示信息的 span 元素
					document.getElementById("span1").style.color=color;
					document.getElementById("span1").innerText = "原密码正确";
					if(msg == "1"){
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
		var newpassword = document.getElementById("newpassword").value.trim();
		var repassword = document.getElementById("repassword").value.trim();
		if(newpassword == repassword){
			document.getElementById("span2").style.color='green';
			document.getElementById("span2").innerText = "密码一致";
			re=1;
		}else{
			document.getElementById("span2").style.color='red';
			document.getElementById("span2").innerText = "密码不一致";
			re=0;
		}
	}
	function password(){
		var newpassword = document.getElementById("newpassword").value;
		if(xmlhttp!=null){
				if(re==1 && old==1 && ne==1){
				// 定义请求地址
				var url ="${path}/user.s?op=updatePwd&newpassword=" + newpassword;
				xmlhttp.open("POST",url,true);
				xmlhttp.onreadystatechange=function(){
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
						var json = xmlhttp.responseText.replace(/\s/gi,"");
						eval("var user = " + json);
						if(user.code==1){
							alert(user.msg);
							//成功跳转到登录页面
							location.href="${path}/join.jsp";
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
<jsp:include page="${path }/common/header.jsp"></jsp:include>
<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="${path}/bcak/lhoption/index.jsp">首页</a> >
			<a href="${path}/back/lywoption/member.jsp">个人中心</a> >
			<a href="${path}/back/lywoption/password.jsp">修改密码</a>
		</div>
		<jsp:include page="${path}/common/middle.jsp"></jsp:include>
		<div class="help-r fr">
			<div class="help-item-title">个人信息</div>
			<div class="help-main">
				<form >
					<p><span class="nice">原始密码：</span><input type="password" id="oldpassword" name="oldpassword" onblur="checkPassWord()"><span id="span1"></span></p>
					<p><span class="nice">新密码：</span><input type="password" id="newpassword" name="newpassword" onblur="newPassword()"><span id="span3"></span></p>
					<p><span class="nice">确认新密码：</span><input type="password" id="repassword" name="repassword" onblur="ynPassWord()"><span id="span2"></span></p>
					<input class="save" type="button" onclick="password()" value="确认修改"/>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="${path }/common/footer.jsp"></jsp:include>
</body>
</html>