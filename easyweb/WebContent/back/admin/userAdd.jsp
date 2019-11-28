<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.yc.easyweb.biz.UserBiz"%>
<%@page import="com.yc.easyweb.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="<%=application.getContextPath()%>/back/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/back/css/style.css" />
<link
	href="<%=application.getContextPath()%>/back/assets/css/codemirror.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/back/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/back/font/css/font-awesome.min.css" />
<script
	src="<%=application.getContextPath()%>/back/js/jquery-1.9.1.min.js"></script>
	
<title>管理员操作</title>
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
	}
%>
<div id="add_administrator_style" class="add_menber"
			>
			<form action="<%=application.getContextPath()%>/user.s?op=addAdmin" method="post" id="form-admin-add">
				<div class="form-group">
					<label class="form-label"><span class="c-red">*</span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
					<div class="formControls">
						<input type="text" class="input-text" placeholder="请输入1到10个以内的汉字或字符"
							value= "<%=name_insert == null ? "":name_insert%>"id="user-name" name="user-name" datatype="*1-8" oninput="checkName();">
					</div>
					<div class="col-4" >
							<span id="vf_username" style="margin-left: 20px;color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label"><span class="c-red">*</span>初始密码：</label>
					<div class="formControls">
						<input type="password" placeholder="请输入密码(6-20个字符)" name="userpassword" id="userpassword"
							class="input-text" oninput="checkPwd();">
					</div>
					<div class="col-4">
						<span id="vf_pwd" style="margin-left: 20px;color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label "><span class="c-red">*</span>确认密码：</label>
					<div class="formControls ">
						<input type="password" placeholder="确认新密码"
							class="input-text Validform_error" 
							datatype="*"
							id="newpassword2" name="newpassword2" oninput="checkRepwd();">
					</div>
					<div class="col-4">
						<span id="vf_repwd" style="margin-left: 20px;color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label "><span class="c-red">*</span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<div class="formControls  skin-minimal">
						<label><input name="form-field-radio" type="radio"value="0"
							class="ace" checked="checked"><span class="lbl" >保密</span></label>&nbsp;&nbsp;
						<label><input name="form-field-radio" type="radio"value="1"
							class="ace"><span class="lbl"  >男</span></label>&nbsp;&nbsp;
						<label><input name="form-field-radio" type="radio"value="2" 
							class="ace"><span class="lbl" >女</span></label>
					</div>
					<div class="col-4">
						<span id="vf_sex"style="margin-left: 20px;color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label "><span class="c-red">*</span>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</label>
					<div class="formControls ">
						<input type="text" class="input-text" placeholder="请输入十一位手机号"
							value= "<%=phone_insert == null ? "":phone_insert%>"id="user-tel" name="user-tel" oninput="checkPhone();">
					</div>
					<div class="col-4">
						<span id="vf_phone"style="margin-left: 20px;color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label"><span class="c-red">*</span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
					<div class="formControls ">
						<input type="text" class="input-text" placeholder="@" name="email"
							value= "<%=email_insert == null ? "":email_insert%>"id="email" datatype="e" oninput="checkEmail();">
					</div>
					<div class="col-4">
						<span id="vf_email"style="margin-left: 20px;color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
					<div class="formControls ">
						<span class="select-box" style="width: 150px;"> <select
							class="select" name="admin-role" size="1">
								<option value="5">普通管理员</option>
								<option value="1">超级管理员</option>
						</select>
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label">加入时间：</label>
					<div class="formControls ">
						<input class="inline laydate-icon" id="bdate" name="bdate"
							type="date" style="width: 150px;" value="<%=time==null?"":time%>">
					</div>
				</div>
					
				<input type="text" id="notice" style="display:none;" value="<%=notice == null ? -1:notice%>">
				<input type="text" id="uid" style="display:none;" name="uid" value="<%=uuid == null ? -1:uuid%>">
				<input class="btn btn-primary radius" type="submit"
						id="Add_Administrator" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<input class="btn btn-primary radius" type="button" onclick="window.location.href='<%=application.getContextPath()%>/back/admin/administrator.jsp'"
						id="Add_Administrator" value="&nbsp;&nbsp;返回&nbsp;&nbsp;">
			
			</form>
		</div>
		<script>
		//监听
		window.onload = function()
		{
			 var msg =  document.getElementById("notice").value;
			 if (msg == 0) {
				alert("添加失败！！！");
		     }else if(msg == 1){
		    	 alert("添加成功！！！");
			}else if(msg == 2){
				document.getElementById("vf_username").innerText = "请输入用户名！！！";
			}else if(msg == 3){
				document.getElementById("vf_phone").innerText = "请输入电话！！！";
			}else if(msg == 4){
				document.getElementById("vf_username").innerText = "用户名不合法！！！";
			}else if(msg == 5){
				document.getElementById("vf_username").innerText ="用户名已存在！！！";
			}else if(msg == 6){
				document.getElementById("vf_phone").innerText = "电话不合法！！！";
			}else if(msg == 7){
				document.getElementById("vf_email").innerText = "邮箱不合法！！！";
			}else if(msg == 8){
				document.getElementById("vf_email").innerText = "邮箱为空！！！";
			}else if(msg == 11){
				document.getElementById("vf_pwd").innerText = "密码不合法！！！";
			}else if(msg == 12){
				document.getElementById("vf_pwd").innerText = "密码为空！！！";
			}else if(msg == 10){
		    	 alert("更新成功！！！");
			}else if(msg == 9){
		    	 alert("更新失败！！！");
			}
		}
function checkName(){
		var bpriceReg = /^[\u4e00-\u9fa5a-zA-Z]{0,15}$/;
		var bpriceText = document.getElementById("user-name").value.trim();
		document.getElementById("vf_username").style.color = "red";
	   if(bpriceText ==""){
	       document.getElementById("vf_username").innerText = "请输入姓名！！！";
	   }else if(!bpriceReg.test(bpriceText)){
	       document.getElementById("vf_username").innerText = "请输入合法姓名(1-10个字母汉字)！！！";
	   }else {
		   document.getElementById("vf_username").innerText = "";
	   }
}
function checkPwd(){
	var bpriceReg = /^[A-Za-z0-9_.]{6,20}$/;
	var bpriceText = document.getElementById("userpassword").value.trim();
	document.getElementById("vf_pwd").style.color = "red";
   if(bpriceText ==""){
       document.getElementById("vf_pwd").innerText = "请输入初始密码！！！";
   }else if(!bpriceReg.test(bpriceText)){
       document.getElementById("vf_pwd").innerText = "请输入合法密码(6-20个字符)！！！";
   }else {
	   document.getElementById("vf_pwd").innerText = "";
   }
}
function checkRepwd(){
	var rePwd = document.getElementById("newpassword2").value.trim();
	var pwd = document.getElementById("userpassword").value.trim();
	document.getElementById("vf_repwd").style.color = "red";
   if(rePwd ==""){
       document.getElementById("vf_repwd").innerText = "请输入确认密码！！！";
   }else if(pwd ==""){
       document.getElementById("vf_repwd").innerText = "请输入密码(6-20个字符)！！！";
   }else if(pwd != rePwd){
       document.getElementById("vf_repwd").innerText = "密码不一致！！！";
   }else  {
	   document.getElementById("vf_repwd").innerText = "";
   }
}
function checkPhone(){
	var bpriceReg = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	var bpriceText = document.getElementById("user-tel").value.trim();
	document.getElementById("vf_phone").style.color = "red";
   if(bpriceText ==""){
       document.getElementById("vf_phone").innerText = "请输入电话！！！";
   }else if(!bpriceReg.test(bpriceText)){
       document.getElementById("vf_phone").innerText = "请输入合法电话(11个数字以13,14,15,18开头)！！！";
   }else {
	   document.getElementById("vf_phone").innerText = "";
   }
}
function checkEmail(){
	var bpriceReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var bpriceText = document.getElementById("email").value.trim();
	document.getElementById("vf_email").style.color = "red";
   if(bpriceText ==""){
       document.getElementById("vf_email").innerText = "请输入邮箱！！！";
   }else if(!bpriceReg.test(bpriceText)){
       document.getElementById("vf_email").innerText = "请输入合法邮箱(如：1139386771@qq.com)";
   }else {
	   document.getElementById("vf_email").innerText = "";
   }
}
</script>
</body>
</html>
