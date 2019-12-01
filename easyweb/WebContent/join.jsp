<%@page import="com.yc.easyweb.bean.BookType"%>
<%@page import="com.yc.easyweb.biz.BookTypeBiz"%>
<%@ page import = "java.lang.*"%>
<%@ page import ="java.util.*" %>
<%@page import="com.yc.easyweb.biz.BookBiz"%>
<%@page import="com.yc.easyweb.bean.Book"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>易书网</title>
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/index.css" />
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/animate-custom.css" />
</head>
<body style="background: #fff url(images/bg.jpg) repeat top left;">
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
		var url ="RegServlet.s?op=checkName&username="+name;
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
                // alert(msg);
				// 允许注册使用绿色字体， 否则红色字体
				var color = msg == '该用户名可以注册！' ? 'green' : 'red'; 
				//alert(msg);
				$("#span1").text(msg).css("color", color); 
				// 输入框的后面一个元素是用于展示信息的 span 元素
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

		// 定义请求地址

		var url ="RegServlet.s?op=checkPhone&uphone="+phone;
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
                // alert(msg);
				// 允许注册使用绿色字体， 否则红色字体
				var color = msg == '该电话号码可以注册！' ? 'green' : 'red'; 
				//alert(msg);
				$("#span2").text(msg).css("color", color); 
				// 输入框的后面一个元素是用于展示信息的 span 元素
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
		var url ="RegServlet.s?op=checkEmail&uemail="+uemail;
		xmlhttp.open("POST",url,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				// 替换空格
				var msg = xmlhttp.responseText.replace(/\s/gi,"");
				var color = msg == '该邮箱可以注册!' ? 'green' : 'red'; 
				//alert(msg);
				$("#span01").text(msg).css("color", color); 
				// 输入框的后面一个元素是用于展示信息的 span 元素
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
	var url ="RegServlet.s?op=checkPassword&upassword="+password;
	xmlhttp.open("POST",url,true);
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			// 替换空格
			var msg = xmlhttp.responseText.replace(/\s/gi,"");
			var color = msg == '该密码可以使用！' ? 'green' : 'red'; 
			$("#span3").text(msg).css("color", color); 
			// 输入框的后面一个元素是用于展示信息的 span 元素
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
		// 定义请求地址
		var url ="RegServlet.s?op=checkPassword01&passwordsignup_confirm="+passwordsignup_confirm+"&upassword="+upassword;
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
                // alert(msg);
				// 允许注册使用绿色字体， 否则红色字体
				var color = msg == '密码一致！' ? 'green' : 'red'; 
				//alert(msg);
				$("#span4").text(msg).css("color", color); 
				// 输入框的后面一个元素是用于展示信息的 span 元素
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
    var uname = document.getElementById("usernamesignup").value;
	var uphone = document.getElementById("tel").value;
	var university = document.getElementById("college").value;
	var ucollege = document.getElementById("academy").value;
	var umajor = document.getElementById("special").value;
	var upassword = document.getElementById("passwordsignup").value;
	var uemail= document.getElementById("uemail").value;
    if(xmlhttp!=null){
		var url ="RegServlet.s?op=checkReg&uname="+uname+"&uphone="+uphone+"&university="+university+"&ucollege="+ucollege+"&umajor="+umajor+"&upassword="+upassword+"&uemail="+uemail;
		xmlhttp.open("POST",url,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				// 替换空格
				var msg = xmlhttp.responseText.replace(/\s/gi,"");
				if(msg=='注册成功!'){
					alert(msg);
					location.href="join.jsp";
				}else{
                alert(msg);
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
		var url ="JoinServlet.s?uname="+uname+"&loginkeeping="+loginkeeping+"&upassword="+upassword+"&vcode="+vcode;
		xmlhttp.open("POST",url,true);
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				// 替换空格
				var msg = xmlhttp.responseText.replace(/\s/gi,"");
				if(msg=='用户登录成功!'){
					alert(msg);
					location.href="<%=application.getContextPath()%>/lhoption/index.jsp";
				}else if(msg=='管理员登录成功!'){
					alert(msg);
					location.href="<%=application.getContextPath()%>/back/main/index.jsp";
				}else if(msg=='用户名或密码错误!'){
					alert(msg);
				}else if(msg=='验证码错误!'){
					alert(msg);
				}else{
					alert(msg);
				}
			}
		};
		xmlhttp.send(null);
	}else{
		alert("不能创建XMLHttpRequest对象实例")
	}
}
</script>
<%
     //从请求对象中获取浏览器发送回服务器的cookie数据
     Cookie[] cookies=request.getCookies();
     //Cookie loginedUserCookie=null;
     Cookie loginedPasswordCookie=null;
     if(cookies!=null){
    	 for(Cookie cookie:cookies){
    		 if(cookie.getName().equalsIgnoreCase("loginedUsername")) {
    			 //解决读取中文乱码问题
    			 request.setAttribute("username",URLDecoder.decode(cookie.getValue(),"GBK"));
    		 }
    		 if("loginedPassword".equals(cookie.getName())){
    			 loginedPasswordCookie=cookie;
    		 }
     }
     }
%>
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
					<%if(request.getAttribute("username")!=null) {%>  value="<%=request.getAttribute("username") %>"  <%}%> 
					placeholder="请输入用户名"/>
				</p>
				<p> 
					<label for="password" class="youpasswd" data-icon="p">你的密码</label>
					<input id="password" name="password" required="required" type="password" 
					<%if(loginedPasswordCookie!=null) {%>  value="<%=loginedPasswordCookie.getValue() %>"  <%}%>
					placeholder="请输入密码" />
				</p>
				<p> 
					<label for="vcode" class="vcode" data-icon="p">请输入验证码</label>
					<input id="vcode" name="vcode" required="required" type="text" 
					placeholder="请输入验证码" />
					<img alt="" src="<%=application.getContextPath()%>/pqoption/vcode.jsp">
				</p>
				<p class="keeplogin"> 
					<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> 
					<label for="loginkeeping">保持登录状态</label>
				</p>
				<div class="title" style="text-align: center;">
						<%if(request.getAttribute("msg")!=null){ %>
							<font color="red"><%=request.getAttribute("msg")%></font>
						<%}%>
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
				<%
				BookBiz biz = new BookBiz();
				Book book = new Book();
				List<Book> list = biz.selectAll(book);
				HashSet set =  new HashSet();
				for(int i = 0;i<list.size();i++){
					for(Book s : list){
						set.add(s.getBuniversity());
					}
				}
				set.remove(null);
				set.remove("");
				HashSet set1 =  new HashSet();
				for(int i = 0;i<list.size();i++){
					
					for(Book s : list){
						
						set1.add(s.getBucollege());
					}
				}
				set1.remove(null);
				set1.remove("");
				HashSet set2 =  new HashSet();
				for(int i = 0;i<list.size();i++){
					for(Book s : list){
						set2.add(s.getBumajor());
					}
				}
				set2.remove(null);
				set2.remove("");
				
				%>
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
					<select name="college" id="college" ">
						<option value="">请选择你的大学</option>
						<% for(Iterator it = set.iterator();it.hasNext(); ){%>
						<option  ><%=it.next()%></option>
						<%} %>
					</select>
					<span id="span01" style="font-weight:700;font-size:15px"></span>
				</p>
				<p>
					<label>你的学院</label>
					<select name="academy" id="academy" ">
						<option value="">请选择你的学院</option>
						<% for(Iterator it = set1.iterator();it.hasNext(); ){%>
						<option  ><%=it.next()%></option>
						<%} %>
					</select>
					<span id="span02" style="font-weight:700;font-size:15px"></span>
				</p>
				<p>
					<label>你的专业</label>
					<select name="special" id="special" ">
						<option value="">请选择你的专业</option>
						<% for(Iterator it = set2.iterator();it.hasNext(); ){%>
						<option  ><%=it.next()%></option>
						<%} %>
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