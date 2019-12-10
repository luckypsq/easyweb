<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>易书网</title>
<link rel="stylesheet" type="text/css" href="${path}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/animate-custom.css" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
var xmlhttp;
// ajax 验证用户名是否存在//是否为空//
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
function checkUserName(){
	//校验用户名是否存在//是否为空
	// 获取用户填写的用户名
	var name = document.getElementById("usernamesignup").value;
    name = name.replace(/\s/gi,"");
    if(name == ''){
		alert("请填写用户名！");
		return;
	}
    if(xmlhttp!=null){
		// 定义请求地址
		var url ="reg.s?op=checkName&username="+name;
        // 以 POST 方式 开启连接
		// POST 请求 更安全（编码）  提交的数据大小没有限制
		xmlhttp.open("POST",url,true);
        // 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
        // 每次的状态改变都会调用该方法
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				// 替换空格
				var msg = xmlhttp.responseText.replace(/\s/gi,"");
				if(msg == -1){
					$("#span1").text("请输入用户名！").css("color", 'red');
				}else if(msg == -2){
					$("#span1").text("该用户名已被注册！").css("color", 'red');
				}else if(msg == -3){
					$("#span1").text("该用户名不合法！").css("color", 'red');
				}else if(msg == 1){
					$("#span1").text("该用户名可以注册！").css("color", 'green'); 
				}
			}
		};
		// 发送请求
		xmlhttp.send(null);
	}else{
		alert("不能创建XMLHttpRequest对象实例")
	}
}
//检验电话号码格式
function checkPhone(){
	//校验电话号码格式
    // 获取用户填写的电话号码
    var phone = document.getElementById("tel").value;
    phone = phone.replace(/\s/gi,"");
    if(xmlhttp!=null){
		var url ="reg.s?op=checkPhone&uphone="+phone;
		xmlhttp.open("POST",url,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				var msg = xmlhttp.responseText.replace(/\s/gi,"");
				if(msg == -1){
					$("#span2").text("请输入电话号码！").css("color", 'red');
				}else if(msg == -2){
					$("#span2").text("该电话号码不合法！").css("color", 'red');
				}else if(msg == -3){
					$("#span2").text("该电话号码已被注册！").css("color", 'red');
				}else if(msg == 1){
					$("#span2").text("该电话号码可以注册！！").css("color", 'green'); 
				}
			}
		};
		// 发送请求
		xmlhttp.send(null);
	}else{
		alert("不能创建XMLHttpRequest对象实例")
	}
}
//检验电子邮箱
function checkEmail(){
	//校验电子邮箱
    var uemail = document.getElementById("uemail").value;
    uemail = uemail.replace(/\s/gi,"");
    if(xmlhttp!=null){
		// 定义请求地址
		var url ="reg.s?op=checkEmail&uemail="+uemail;
		xmlhttp.open("POST",url,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				if(msg == -1){
					$("#span01").text("请输入邮箱！").css("color", 'red');
				}else if(msg == -3){
					$("#span01").text("该邮箱不合法！").css("color", 'red');
				}else if(msg == -2){
					$("#span01").text("该邮箱已被注册！").css("color", 'red');
				}else if(msg == 1){
					$("#span01").text("该邮箱可以注册!").css("color", 'green'); 
				}
			}
		};
		xmlhttp.send(null);
	}else{
		alert("不能创建XMLHttpRequest对象实例")
	}
}
//校验密码
function checkPassword(){
		var password = document.getElementById("passwordsignup").value;
		password = password.replace(/\s/gi,"");
		if(xmlhttp!=null){
			var url ="reg.s?op=checkPassword&upassword="+password;
			xmlhttp.open("POST",url,true);
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var msg = xmlhttp.responseText.replace(/\s/gi,"");
					if(msg == -1){
						$("#span3").text("请输入密码！").css("color", 'red');
					}else if(msg == -2){
						$("#span3").text("该密码不合法！").css("color", 'red');
					}else if(msg == 1){
						$("#span3").text("该密码可以使用!").css("color", 'green'); 
					}
			}
		};
		// 发送请求
		xmlhttp.send(null);
	}else{
		alert("不能创建XMLHttpRequest对象实例")
	}
}
//检验确认密码
function checkPasswordsignup_confirm(){
	//校验确认密码
    var upassword = document.getElementById("passwordsignup").value;
    var passwordsignup_confirm = document.getElementById("passwordsignup_confirm").value;
    	passwordsignup_confirm = passwordsignup_confirm.replace(/\s/gi,"");
    if(xmlhttp!=null){
		var url ="reg.s?op=checkPassword01&passwordsignup_confirm="+passwordsignup_confirm+"&upassword="+upassword;
		xmlhttp.open("POST",url,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				var msg = xmlhttp.responseText.replace(/\s/gi,"");
				if(msg == -1){
					$("#span4").text("请输入密码！").css("color", 'red');
				}else if(msg == -2){
					$("#span4").text("请确认密码！").css("color", 'red');
				}else if(msg == -3){
					$("#span4").text("密码不一致！").css("color", 'red');
				}else if(msg == 1){
					$("#span4").text("密码一致!").css("color", 'green'); 
				}
			}
		};
		// 发送请求
		xmlhttp.send(null);
	}else{
		alert("不能创建XMLHttpRequest对象实例")
	}
}
// 检验注册
function checkReg(){
	//校验电话号码格式
    // 获取用户填写的电话号码
	var university = document.getElementById("college").value;
	var ucollege = document.getElementById("academy").value;
	var umajor = document.getElementById("special").value;
    if(xmlhttp!=null){
		var url ="reg.s?op=checkReg&university="+university+"&ucollege="+ucollege+"&umajor="+umajor;
		xmlhttp.open("POST",url,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				// 替换空格
				var msg = xmlhttp.responseText.replace(/\s/gi,"");
				var m = msg.split("/");
				if(m[1] == "-1"){
					$("#span1").text("用户名未输入或不合法！").css("color", 'red');
				}else if(m[2] == "-1"){
					$("#span2").text("电话未输入或不合法！").css("color", 'red');
				}else if(m[3] == "-1"){
					$("#span01").text("密码未输入或不合法！").css("color", 'red');
				}else if(m[4] == "-1"){
					$("#span3").text("邮箱未输入或不合法！").css("color", 'red');
				}else if(msg == -5){
					document.getElementById('regMsg').innerText = "注册失败！！！";
				}else if(msg == 1){
					alert("注册成功！！！");
					location.href="join.jsp";
				}
			}
		};
		xmlhttp.send(null);
	}else{
		alert("不能创建XMLHttpRequest对象实例")
	}
}
//检验登录
function checkJoin(){
    var uname = document.getElementById("username").value;
	var loginkeeping = document.getElementById("loginkeeping").value;
	var upassword = document.getElementById("password").value;
	var vcode = document.getElementById("vcode").value;
    if(xmlhttp!=null){
		var url ="join.s?op=join&uname="+uname+"&loginkeeping="+loginkeeping+"&upassword="+upassword+"&vcode="+vcode;
		xmlhttp.open("POST",url,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				// 替换空格
				var msg = xmlhttp.responseText.replace(/\s/gi,"");
				if(msg==1){
					alert("用户登录成功！！！");
					location.href="${path}/bcak/lhoption/index.jsp";
				}else if(msg==2){
					alert("管理员登录成功！！！");
					location.href="${path}/back/main/index.jsp";
				}else if(msg==-3){
					document.getElementById('msg').innerText = "用户名或密码错误！！！";
				}else if(msg==-2){
					document.getElementById('msg').innerText = "验证码错误！！！";
				}else if(msg == -1){
					document.getElementById('msg').innerText = "用户名不存在！！！";
				}else if(msg == -4){
					document.getElementById('msg').innerText = "您的账户已被冻结或删除！！！";
				}	
			}
		};
		xmlhttp.send(null);
	}else{
		alert("不能创建XMLHttpRequest对象实例")
	}
}
function show(){
	if(xmlhttp!=null){
		var url ="reg.s?op=showUser";
		xmlhttp.open("POST",url,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				// 替换空格
				var msg = xmlhttp.responseText.replace(/\s/gi,"");
				if(msg == -1){
					alert("暂无数据！！！");
				}
			}
		};
		xmlhttp.send(null);
	}else{
		alert("不能创建XMLHttpRequest对象实例")
	}
}
</script>
</head>
<body style="background: #fff url(images/bg.jpg) repeat top left;" onload="show()">
<div id="container_demo" >
	<a class="hiddenanchor" id="toregister"></a>
	<a class="hiddenanchor" id="tofind"></a>
	<a class="hiddenanchor" id="tologin"></a>
	<div id="wrapper">
		<div id="login" class="animate form">
			<form  action=""  method="post" >
				<h1>登录</h1> 
				<p> 
					<label for="username" class="uname" data-icon="u" >您的用户名</label>
					<input id="username" name="username" required="required" type="text" 
					value="${loginUname }"
					placeholder="请输入用户名"/>
				</p>
				<p> 
					<label for="password" class="youpasswd" data-icon="p">你的密码</label>
					<input id="password" name="password" required="required" type="password" 
					placeholder="请输入密码" />
				</p>
				<p> 
					<label for="vcode" class="vcode" data-icon="p">请输入验证码</label>
					<input id="vcode" name="vcode" required="required" type="text" 
					placeholder="请输入验证码" />
					<img alt="" src="${codeImg }" style="width:200px; height:80px;" onclick="show()">
				</p>
				<p class="keeplogin"> 
					<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> 
					<label for="loginkeeping">保持登录状态</label>
				</p>
				<div class="title" style="text-align: center;">
							<font color="red" id="msg"></font>
				</div>
				<p class="login button"> 
					<input type="button" value="登录" onclick="checkJoin()" /> 
				</p>				
				
				<p class="change_link">
				          忘记密码?<a href="find.jsp" >找回密码</a>
					不是成员?<a href="#toregister" class="to_register">加入我们</a>
				</p>
			</form>
		</div>

		<div id="register" class="animate form">
			<form   action="" 
			method="post" >
				<h1>注册</h1> 
				<p> 
					<label for="usernamesignup" class="uname" data-icon="u">用户名</label>
					
					<span id="span1" style="font-weight:700;font-size:15px"></span>
					<input id="usernamesignup" name="usernamesignup" required="required" type="text" placeholder="用户名" 
					onblur="checkUserName()" />
					
				</p>
				<p> 
					<label for="tel" class="youmail">联系电话</label>
					<span id="span2" style="font-weight:700;font-size:15px"></span>
					<input id="tel" name="emailsignup" required="required" type="tel" placeholder="联系电话"
					onblur="checkPhone()"/>
					
				</p>
				
				<p> 
					<label for="uemail" class="youemail">电子邮箱</label>
					<span id="span01" style="font-weight:700;font-size:15px"></span>
					<input id="uemail" name="uemail" required="required" type="text" placeholder="电子邮箱"
					onblur="checkEmail()"/>
					
				</p>
				
				<p>
					<label>你的大学</label>
					<select name="college" id="college" >
						<option >请选择你的大学</option>
						<c:forEach items="${userUni}" var="univ">
							<option >${univ }</option>
						</c:forEach>
					</select>
					<span id="span01" style="font-weight:700;font-size:15px"></span>
				</p>
				<p>
					<label>你的学院</label>
					<select name="academy" id="academy" >
						<option value="">请选择你的学院</option>
						<c:forEach items="${userUcol}" var="ucol">
							<option >${ucol }</option>
						</c:forEach>
					</select>
					<span id="span02" style="font-weight:700;font-size:15px"></span>
				</p>
				<p>
					<label>你的专业</label>
					<select name="special" id="special" >
						<option value="">请选择你的专业</option>
						<c:forEach items="${userUmar}" var="umar">
							<option >${umar }</option>
						</c:forEach>
					</select>
					<span id="span03" style="font-weight:700;font-size:15px"></span>
				</p>
				<p> 
					<label for="passwordsignup" class="youpasswd" data-icon="p">密码（由6-21位字母和数字组成）</label>
					<span id="span3" style="font-weight:700;font-size:15px"></span>
					<input id="passwordsignup" name="passwordsignup" required="required" type="password" placeholder="密码"
					onblur="checkPassword()"/>
				</p>
				<p> 
					<label for="passwordsignup_confirm" class="youpasswd" data-icon="p">确认密码</label>
					<span id="span4" style="font-weight:700;font-size:15px"></span>
					<input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required" type="password" placeholder="确认密码"
					oninput="checkPasswordsignup_confirm()"/>
				</p>
				<div class="title" style="text-align: center;">
					<font color="red" id="regMsg"></font>
				</div>
				<p class="signin button"> 
					<input type="button" value="注册"  onclick="checkReg()"/> 
				</p>
				<p class="change_link">  
					已经是成员?<a href="#tologin" class="to_register"> 去登录 </a>
				</p>
			</form>
		</div>
	</div>
</div>
</body>
</html>