<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="${path}/css/index.css"/>
	<script src="${path}/js/main.js"></script>
	<title>个人信息</title>
</head>
<style>
	.help-main p {
		line-height: 50px;
	}
</style>
<body >
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="${path}/back/lhoption/index.jsp">首页</a> >
			<a href="${path}/back/lywoption/member.jsp">个人中心</a> >
			<a href="${path}/back/lywoption/member.jsp">个人信息</a>
		</div>
		<jsp:include page="${path}/common/middle.jsp"></jsp:include>
		<div class="help-r fr">
			<div class="help-item-title">个人信息</div>
			<div class="help-main">
					<form >
						<p><span class="nice">用户名：</span><span>${editShowUser['uname']}</span></p>
						<p>
							<span class="nice">昵称：</span><input type="text" id="uminname" name="uminname" value="${editShowUser['uminname']}">
							<span class="nice">联系电话：</span><input type="text" id="uphone" name="uphone" value="${editShowUser['uphone']}">
							<span class="nice" id="phonetishi"></span>
						</p>
						<p>
							<span class="nice">性别：</span>
								<select name="usex" style="width: 100px" id="usex">
									<option value="${editShowUser['usex']}">${userSex[editShowUser['usex']]}</option>
									<option value="0">保密</option>
									<option value="1">男</option>
									<option value="2">女</option>
								</select>
							<span class="nice">年龄：</span>
								<c:if test="${editShowUser['uage'] ==  0}" var="flag" scope="session">
									<input type="text" id="uage" name="uage" value="保密">						
								</c:if>
								<c:if test="${not flag}">	
									<input type="text" id="uage" name="uage" value="${editShowUser['uage']}">
								</c:if>
							<span class="nice" id="agetishi"></span>
						</p>
						<p>
							<span class="nice">学校：</span>
							<select name="university" id="university" >
								<option value="${editShowUser['university']}">${editShowUser['university']}</option>
								<c:forEach items="${userUni}" var="uni">
									<option >${uni }</option>
								</c:forEach>
							</select>
							<span class="nice">学院：</span>
							<select name="ucollege" id="ucollege" >
								<option value="${editShowUser['ucollege']}">${editShowUser['ucollege']}</option>
								<c:forEach items="${userUcol}" var="ucol">
									<option >${ucol }</option>
								</c:forEach>
							</select>
						</p>
						
						<p>
							<span class="nice">专业：</span>
								<select name="umajor" id="umajor" >
									<option value="${editShowUser['umajor']}">${editShowUser['umajor']}</option>
									<c:forEach items="${userUmar}" var="umar">
										<option >${umar }</option>
									</c:forEach>
								</select>
							<span class="nice">年级：</span>
								<select name="uclass" id="uclass" >
									<option value="${editShowUser['uclass']}">${editShowUser['uclass']}</option>
									<option >大一</option>
									<option >大二</option>
									<option >大三</option>
									<option >大四</option>
								</select>
					</p>
					<input class="save" type="submit" value="修改"/>
				</form>		   	
			</div>
		</div>
	</div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
<script type="text/javascript">
	window.location = function(){
		var msg = "${param.msg}";
		if(msg == -1){
			alert("更新失败！！！");
		}else if(msg == 1){
			alert("更新成功");
		}else{
			alert("信息输入有误,请重新输入！！！");
		}
	}
    $(function(){  
        show();
    }); 
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
	function show(){
		if(xmlhttp!=null){
			var url ="user.s?op=showUser";
			xmlhttp.open("POST",url,true);
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					// 替换空格
					var msg = xmlhttp.responseText.replace(/\s/gi,"");
					if(msg == -1){
						alert("暂无信息");
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		}else{
			alert("不能创建XMLHttpRequest对象实例")
		}
	}
	
	
	//检验年龄
	function checkAge(){
		//校验电话号码格式
	    // 获取用户填写的电话号码
		var age = document.getElementById("uage").value;
	    age = age.replace(/\s/gi,"");
	    if(xmlhttp!=null){
			var url ="user.s?op=checkUage&uage="+age;
			xmlhttp.open("POST",url,true);
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var msg = xmlhttp.responseText.replace(/\s/gi,"");
					if(msg == -1){
						$("#agetishi").text("请输入年龄！").css("color", 'red');
					}else if(msg == -2){
						$("#agetishi").text("该输入不合法！").css("color", 'red');
					}else if(msg == 1){
						$("#agetishi").text("输入正确！！").css("color", 'green'); 
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
		var phone = document.getElementById("uphone").value;
	    phone = phone.replace(/\s/gi,"");
	    if(xmlhttp!=null){
			var url ="user.s?op=checkPhone&uphone="+phone;
			xmlhttp.open("POST",url,true);
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var msg = xmlhttp.responseText.replace(/\s/gi,"");
					if(msg == -1){
						$("#phonetishi").text("请输入电话号码！").css("color", 'red');
					}else if(msg == -2){
						$("#phonetishi").text("该电话号码不合法！").css("color", 'red');
					}else if(msg == -3){
						$("#phonetishi").text("该电话号码已被注册！").css("color", 'red');
					}else if(msg == 1){
						$("#phonetishi").text("该电话号码可以使用！！").css("color", 'green'); 
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		}else{
			alert("不能创建XMLHttpRequest对象实例")
		}
	}
	$('form').submit(function() {
		  var param = $(this).serialize();
		  $.ajax({
	            type: "post",
	            url: "user.s?op=updateUser",
	            data: param,
	            async:true, // 异步请求
	            cache:false, // 设置为 false 将不缓存此页面
	            dataType: 'json', // 返回对象
	            success: function(result) {
	                if(result.code == 1){
	                	alert(result.msg);
	                }else if(result.code == 0){
	                	alert(result.msg);
	                }else if(result.code == -1){
	                	var mess = result.msg.split("/");
	                	if(mess[2] == "-1"){
	                		$("#agetishi").text("年龄不合法或未输入").css("color", 'red');
	                	}
						if(mess[1] == "-1"){
							$("#phonetishi").text("电话号码不合法或已存在！").css("color", 'red');
	                	}
	                }
	            }
	        });
	});
</script>
</body>
</html>