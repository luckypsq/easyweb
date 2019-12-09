<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link
	href="${path}/back/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${path}/back/css/style.css" />
<link rel="stylesheet"
	href="${path}/back/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="${path}/back/assets/css/font-awesome.min.css" />
<link
	href="${path}/back/Widget/icheck/icheck.css"
	rel="stylesheet" type="text/css" />
<script
	src="${path}/back/js/jquery-1.9.1.min.js"></script>
<script
	src="${path}/back/assets/js/bootstrap.min.js"></script>
<script type="text/javascript"
		src="${path}/back/Widget/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="${path}/back/Widget/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript"
		src="${path}/back/assets/layer/layer.js"></script>
	<script type="text/javascript"
		src="${path}/back/js/H-ui.js"></script>
	<script type="text/javascript"
		src="${path}/back/js/H-ui.admin.js"></script>
	<script type="text/javascript">

	$(function() {
		$('.skin-minimal input').iCheck({
			checkboxClass : 'icheckbox-blue',
			radioClass : 'iradio-blue',
			increaseArea : '20%'
		});

		$("#form-user-add").Validform({
			tiptype : 2,
			callback : function(form) {
				form[0].submit();
				var index = parent.layer.getFrameIndex(window.name);
				parent.$('.btn-refresh').click();
				parent.layer.close(index);
			}
		});
	});
	function gradeChange(){
		document.getElementById("namefrist").value = "";
    	document.getElementById("namesecond").value = "";
    	document.getElementById("namethird").value = "";
		var objS = document.getElementById("btype").value; 
        var  btype  = objS.split("-");
        document.getElementById("nameid").value =btype[0];
        if(btype[btype.length-1] == 1){
        	 document.getElementById("type_state").value ="已启用";
        }else{
        	document.getElementById("type_state").value ="未启用";
        	 document.getElementById("showState").style.background="lightgrey";
        }
        if(btype.length == 5){
        	document.getElementById("namefrist").value = btype[1];
        	document.getElementById("namesecond").value = btype[2];
        	document.getElementById("namethird").value = btype[3];
        }else if(btype.length == 4){
        	document.getElementById("namefrist").value = btype[1];
        	document.getElementById("namesecond").value = btype[2];
        }else if(btype.length == 3){
        	document.getElementById("namefrist").value = btype[1];
        }
	};
	function updateState(){
		var state =  document.getElementById("type_state").value;
		if(state == "已启用"){
			state = 1;
		}else{
			state = 0;
		}
		var btid = document.getElementById("nameid").value;
		if(btid == null ){
			layer.msg('请选择更新的类型名!!!', {
				icon : 7,
				time : 1000
				});
			return;
		}
		if (xmlhttp != null) {
			// 定义请求地址
			var url = "${path}/bookType.s?op=update&btid="+btid+"&state="+state;
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST", url, true);
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					// 替换空格
					var msg = xmlhttp.responseText.replace(/\s/gi, "");
					if(msg == "更新成功！！！"){
						layer.msg(msg, {
							icon : 6,
							time : 1000
							});
						window.location.href='${path}/back/book/product-category-add.jsp';
					}else{
						layer.msg(msg, {
							icon : 5,
							time : 1000
							});
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		} else {
			layer.msg("不能创建XMLHttpRequest对象实例", {
				icon : 5,
				time : 1000
				});
		}
		
	}
	var xmlhttp;
	// ajax 验证用户名是否存在
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
	function add(){
		var btname = document.getElementById("namefrist").value;
		var btname1 = document.getElementById("namesecond").value;
		var btname2 = document.getElementById("namethird").value;
		if(btname == null ){
			layer.msg('请选择您要添加的类型名!!!', {
				icon : 7,
				time : 1000
				});
			return;
		}
		if (xmlhttp != null) {
			// 定义请求地址
			var url = "${path}/bookType.s?op=add&namefrist="+btname+"&namesecond="+btname1+"&namethird="+btname2 ;
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST", url, true);
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					// 替换空格
					var msg = xmlhttp.responseText.replace(/\s/gi, "");
					if(msg == "添加成功！！！"){
						layer.msg(msg, {
							icon : 6,
							time : 1000
							});
						window.location.href='${path}/back/book/product-category-add.jsp';
					}else{
						layer.msg(msg, {
							icon : 5,
							time : 1000
							});
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		} else {
			layer.msg("不能创建XMLHttpRequest对象实例", {
				icon : 2,
				time : 1000
				});
		}
	};
	function update(){
		var btid = document.getElementById("nameid").value;
		var btname = document.getElementById("namefrist").value;
		var btname1 = document.getElementById("namesecond").value;
		var btname2 = document.getElementById("namethird").value;
		if(btid == null ){
			layer.msg('请选择您要更新的类型名!!!', {
				icon : 7,
				time : 1000
				});
			return;
		}
		if (xmlhttp != null) {
			// 定义请求地址
			var url = "${path}/bookType.s?op=update&btid="+btid+"&namefrist="+btname+"&namesecond="+btname1+"&namethird="+btname2 ;
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST", url, true);
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					// 替换空格
					var msg = xmlhttp.responseText.replace(/\s/gi, "");
					if(msg == "更新成功！！！"){
						layer.msg(msg, {
							icon : 6,
							time : 1000
							});
						window.location.href='${path}/back/book/product-category-add.jsp';
					}else{
						layer.msg(msg, {
							icon : 5,
							time : 1000
							});
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		} else {
			layer.msg("不能创建XMLHttpRequest对象实例", {
				icon : 2,
				time : 1000
				});
		}
	};
	function deleteType(){
		var btid = document.getElementById("nameid").value;
		if(btid == null ){
			layer.msg("请选择您要删除的类型名!!!", {
				icon : 7,
				time : 1000
				});
			return;
		}
		if (xmlhttp != null) {
			// 定义请求地址
			var url = "${path}/bookType.s?op=delete&btid="+btid;
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST", url, true);
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					// 替换空格
					var msg = xmlhttp.responseText.replace(/\s/gi, "");
					if(msg == "删除成功！！！"){
						layer.msg(msg, {
							icon : 6,
							time : 1000
							});
						window.location.href='${path}/back/book/product-category-add.jsp';
					}else{
						layer.msg(msg, {
							icon : 5,
							time : 1000
							});
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		} else {
			layer.msg("不能创建XMLHttpRequest对象实例", {
				icon : 2,
				time : 1000
				});
		}
	};
	function show(){
		if (xmlhttp != null) {
			// 定义请求地址
			var url = "${path}/bookType.s?op=query";
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST", url, true);
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					// 替换空格
					var msg = xmlhttp.responseText.replace(/\s/gi, "");
					if(msg == "0"){
						layer.msg("暂无数据", {
							icon : 5,
							time : 1000
							});
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		} else {
			layer.msg("不能创建XMLHttpRequest对象实例", {
				icon : 2,
				time : 1000
				});
		}
	}
</script>
<title>添加书籍分类</title>
</head>
<body onload="show()">
<div class="type_style">
 <div class="type_title">书籍类型信息</div>
  <div class="type_content">
  <form action="" method="post" class="form form-horizontal" id="form-user-add">
  	<div class="Operate_cont clearfix">
      <label class="form-label" ><span class="c-red"></span>已有分类名:</label>
      <div class="formControls ">
      		<select class="select"id="btype" name="btype" onblur="gradeChange()">
					<option>请选择</option>
					<c:forEach items="${bookTypeShow}" var="bt">
						<option value="${bt }">${bt }</option>
					</c:forEach>
		</select>
      </div>
    </div>
    <div class="Operate_cont clearfix" id = "showState">
      <label class="form-label" ><span class="c-red"></span>状&nbsp;&nbsp;&nbsp;&nbsp;态:</label>
      <div class="formControls ">
        	<div class="btn  btn-success" style="margin-left:10px;">
				<input type="button"onclick="updateState()" id="type_state" style="background:none;outline:none;border:0px;width:100px;height:20px;color:white;" value="已启用">
			</div>
      </div>
    </div>
    <div class="Operate_cont clearfix" style="display:none;">
        <input type="text" class="input-text" value="" id="nameid" name="nameid">
    </div>
    <div class="Operate_cont clearfix">
      <label class="form-label" ><span class="c-red">*</span>一级分类名:</label>
      <div class="formControls ">
        <input type="text" class="input-text" value="" placeholder="请输入最多10个汉字" id="namefrist" name="namefrist">
      </div>
    </div>
    <div class="Operate_cont clearfix">
      <label class="form-label"><span class="c-red"></span>二级分类名:</label>
      <div class="formControls ">
        <input type="text" class="input-text" value="" placeholder="请输入最多10个汉字" id="namesecond" name="namesecond">
      </div>
    </div>
    <div class="Operate_cont clearfix">
      <label class="form-label"><span class="c-red"></span>三级分类名:</label>
      <div class="formControls ">
        <input type="text" class="input-text" value="" placeholder="请输入最多10个汉字" id="namethird" name="namethird">
      </div>
      
    </div>
    
    <div class="Operate_cont clearfix">
		  	<div class="btn  btn-warning" id="add">
		  	 		<i class="icon-edit align-top bigger-125"></i>
					<input onclick = "add()" id="type_add" name="type_add" type="button" class="type-add" style="background:none;outline:none;border:0px;color: white;"value="新增子类型"/>
			</div>
			<div class="btn  btn-success" id="update">
					<i class="icon-ok align-top bigger-125"></i>
					<input onclick = "update()" id="type_update" name="type_update" type="button" class="type-add" style="background:none;outline:none;border:0px;color: white;"value="修改子类型"/>
			</div>
		  <div class="btn  btn-danger" id="delete">
		  	 		<i class="icon-trash   align-top bigger-125"></i>
					<input   id="type_delete" name="type_delete" type="button" class="type-add" style="background:none;outline:none;border:0px;color: white;"value="删除子类型" onclick = "deleteType()"/>
			</div>
 	 </div>
  </form>
 
  </div>
  </div>
	
</body>
</html>