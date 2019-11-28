<%@page import="com.yc.easyweb.biz.UserBiz"%>
<%@page import="com.yc.easyweb.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link
	href="<%=application.getContextPath() %>/back/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=application.getContextPath() %>/back/css/style.css" />
<link
	href="<%=application.getContextPath() %>/back/assets/css/codemirror.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=application.getContextPath() %>/back/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="<%=application.getContextPath() %>/back/assets/css/font-awesome.min.css" />
<script
	src="<%=application.getContextPath() %>/back/assets/js/jquery.min.js"></script>
<script type="text/javascript">
		window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='<%=application.getContextPath() %>/back/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/bootstrap.min.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/typeahead-bs2.min.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/jquery.dataTables.min.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/jquery.dataTables.bootstrap.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/js/H-ui.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/js/H-ui.admin.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/layer/layer.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/laydate/laydate.js"
	type="text/javascript"></script>
	
<script type="text/javascript">
	//监听input框的变化
	window.onload = function()
	{
		 var msg =  document.getElementById("notice").value;
		 if (msg == 0) {
			alert("添加失败！！！");
	     }else if(msg == 1){
	    	 alert("添加成功！！！");
		}else if(msg == 2){
			document.getElementById("tishi").innerText = "请输入用户名！！！";
		}else if(msg == 3){
			document.getElementById("tishi").innerText = "请输入电话！！！";
		}else if(msg == 4){
			document.getElementById("tishi").innerText = "用户名不合法！！！";
		}else if(msg == 5){
			document.getElementById("tishi").innerText ="用户名已存在！！！";
		}else if(msg == 6){
			document.getElementById("tishi").innerText = "电话不合法！！！";
		}else if(msg == 7){
			document.getElementById("tishi").innerText = "邮箱不合法！！！";
		}else if(msg == 10){
	    	 alert("更新成功！！！");
		}else if(msg == 9){
	    	 alert("更新失败！！！");
		}
	}
	var xmlhttp;
	// ajax
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
	function doupdate(id){
		if(id == -1){
			alert("不能进行次操作！！！");
			return;
		}
		if(xmlhttp!=null){
			// 定义请求地址
			var url ="<%=application.getContextPath()%>/user.s?op=updateState&upassword=1&uid="+id;
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST",url,true);
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					// 替换空格
					var msg = xmlhttp.responseText.replace(/\s/gi,"");
					if(msg == 1){
						alert("重置成功！！！");
					}else{
						alert("重置失败！！！");
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		}else{
			alert('不能创建XMLHttpRequest对象实例');
		} 
	}
	</script>
<title>用户添加</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html;charset=utf-8");
	String notice = null;
	String name_insert = null;
	String email_insert = null;
	String phone_insert = null;
	String uuid = null;
	String utype_insert=null;
	String time = null;
	if(request.getParameter("msg") != null && !request.getParameter("msg").toString().isEmpty()){
		notice = request.getParameter("msg") ;
	}
	if(request.getParameter("name") != null && !request.getParameter("name").toString().isEmpty()){
		name_insert = request.getParameter("name") ;
	}
	if(request.getParameter("email") != null && !request.getParameter("email").toString().isEmpty()){
		email_insert = request.getParameter("email") ;
	}
	if(request.getParameter("phone") != null && !request.getParameter("phone").toString().isEmpty()){
		phone_insert= request.getParameter("phone") ;
	}
	if(request.getParameter("uid") != null && !request.getParameter("uid").toString().isEmpty()){
		uuid= request.getParameter("uid") ;
	}
	if(uuid !=null){
		User user = new User();
		UserBiz uBiz = new UserBiz();
		user.setUid(Integer.parseInt(uuid));
		User user2 = uBiz.selectSingle(user);
		if(user2.getUphone() != null && !user2.getUphone().isEmpty()){
			phone_insert = user2.getUphone();
		}
		if(user2.getUemail() != null && !user2.getUemail().isEmpty()){
			email_insert = user2.getUemail();
		}
		if(user2.getUname() != null && !user2.getUname().isEmpty()){
			name_insert = user2.getUname();
		}
		if(user2.getUtime()!= null && !user2.getUtime().isEmpty()){
			time = user2.getUtime();
		}
		if(user2.getUtype() == 2){
			utype_insert = 2+"-"+"用户";
		}else if(user2.getUtype() == 3){
			utype_insert = 3+"-"+"会员";
		}else if(user2.getUtype() == 4){
			utype_insert = 4+"-"+"钻石会员";
		}
	}
%>
	<form action="<%=application.getContextPath()%>/user.s?op=add" method="post" >
	<div class="add_menber" id="add_menber_style" >
		<ul class=" page-content">
			<li><label class="label_name">用&nbsp;&nbsp;户 &nbsp;名：</label><span
				class="add_name">
				<input placeholder="请输入10个以内字母,汉字" id ="username_add"type="text"class="text_add" name="username" value="<%=name_insert==null?"":name_insert%>"/></span>
				<div class="prompt r_f"></div></li>
				<li><label class="label_name">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</label><span
				class="add_name"><input placeholder="请输入11位数字" name="uphone" id ="uphone" type="text"
					class="text_add" value="<%=phone_insert==null?"":phone_insert%>"/></span>
				<div class="prompt r_f"></div></li>
				<li><label class="label_name">电子邮箱：</label><span
				class="add_name"><input placeholder="请输入电子邮箱" name="uemail" id ="uemail"type="text"
					class="text_add" value="<%=email_insert==null?"":email_insert%>"/></span>
				<div class="prompt r_f"></div></li>
				
				<li><label class="label_name">加入时间：</label><span
				class="add_name"><input class="inline laydate-icon" id="bdate" name="bdate"
							type="date" style="width: 150px;" value="<%=time==null?"":time%>">
					</span>
				<div class="prompt r_f"></div></li>
				
				
						
				<li ><label class="label_name">级&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label><span
				class="add_name">
				<select style="width:100px;margin-left:10px;" class="text_add"name="utype">
					<option><%=utype_insert ==null ?"请选择" :utype_insert %></option>
					<option>2-用户</option>
					<option>3-会员</option>
					<option>4-钻石会员</option>
				</select>
				</span>
				<div class="prompt r_f"></div></li>
				<li>
				<input type="text" id="notice" style="display:none;" value="<%=notice == null ? -1:notice%>">
				<input type="text" id="uid" style="display:none;" name="uid" value="<%=uuid == null ? -1:uuid%>">
				<input type="submit" value="提交" style="width:80px;">
				<input type="button" value="重置密码" style="width:80px;"onclick="doupdate(<%=notice == null ? -1:notice%>)" >
				<input type="button" value="返回" style="width:80px;" onclick="window.location.href='<%=application.getContextPath()%>/back/user/user_list.jsp'">
				</li>
				<li><label id="tishi" style="color: red;font-size:18px; margin-left:20px;padding-bottom: 20px;"></label>
				</li>
		</ul>
	</div>
	</form>
</body>
</html>