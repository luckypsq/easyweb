<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<link
	href="${path}/back/assets/css/codemirror.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${path}/back/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="${path}/back/Widget/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet"
	href="${path}/back/assets/css/font-awesome.min.css" />
<link
	href="${path}/back/Widget/icheck/icheck.css"
	rel="stylesheet" type="text/css" />
<link
	href="${path}/back/Widget/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<link href="${path}/back/css/button.css"
	rel="stylesheet" type="text/css" />
<script
	src="${path}/back/assets/layer/layer.js"
	type="text/javascript"></script>
<script
	src="${path}/back/assets/laydate/laydate.js"
	type="text/javascript"></script>
<script
	src="${path}/back/js/jquery-1.9.1.min.js"></script>
<script
	src="${path}/back/assets/js/bootstrap.min.js"></script>
<script
	src="${path}/back/assets/js/typeahead-bs2.min.js"></script>
<script type="text/javascript"
	src="${path}/back/Widget/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${path}/back/Widget/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript"
	src="${path}/back/Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript"
	src="${path}/back/Widget/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript"
	src="${path}/back/Widget/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript"
	src="${path}/back/Widget/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript"
	src="${path}/back/Widget/ueditor/1.4.3/ueditor.all.min.js">
</script>
<script type="text/javascript"
	src="${path}/back/Widget/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script src="${path}/back/js/lrtk.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${path}/back/js/H-ui.js"></script>
<script type="text/javascript"
	src="${path}/back/js/H-ui.admin.js"></script>
<style type="text/css">
</style>
<title>填写订单</title>
</head>
<body onload="show()">
	<div class="page_right_style">
		<div class="type_title">填写订单</div>
		<form action="${path}/eorder.s?op=add" method="post" class="form form-horizontal"
			id="form-article-add">
			<div class="clearfix cl">
				<label class="form-label col-2"><span class="c-red">*</span>地&nbsp;&nbsp;&nbsp;&nbsp;址：</label>
				<div class="formControls col-10">
					<input type="text" class="input-text"
						placeholder="请输入地址" id="eoaddr" name="eoaddr" value="${eorderMessage['eoaddr'] }"
						style="margin-left: 40px;">
				</div>
			</div>
			
			<div class=" clearfix cl">
				<div class="clearfix cl" id ="showleft">
					<img id="imghead" name="imghead"border=0 src="${eorderBook.bimg}" 
										style="width:300px;height:200px; margin-left:100px;"/>
				</div>
				<div id ="showRight">
					<div class="Add_p_s">
						<label class="form-label col-2"><span class="c-red">*</span>收&nbsp;件&nbsp;人:</label>
						<div class="formControls col-2">
							<input type="text" class="input-text"
								placeholder="请输入汉字或字母" id="uname" name="uname" value="${eorderMessage['uname'] }">
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2"><span class="c-red">*</span>电&nbsp;&nbsp;&nbsp;&nbsp;话:</label>
						<div class="formControls col-2">
							<input type="text" class="input-text"
								placeholder="请输入11位数字" id="uphone" name="uphone" value="${eorderMessage['uphone'] }">
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">数&nbsp;&nbsp;&nbsp;&nbsp;量:</label>
						<div class="formControls col-2">
							<input type="number" class="input-text"
								id="count" name="count" value="${eorderMessage['count'] }" onchange="updatePrice();">本
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">*送货类别:</label>
						<div class="formControls col-2">
							<span class="select-box"> <select class="select"
							id="payType" name="payType">
								<option>请选择</option>
								<option value="货到付款">货到付款</option>
								<option value="在线支付">在线支付</option>
								<option value="店面接取">店面接取</option>
						</select>
						</span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">*支付方式:</label>
						<div class="formControls col-2">
							<span class="select-box"> <select class="select"
							id="payOption" name="payOption">
								<option>请选择</option>
								<c:if test="${payType.size()> 0}" var="flag" scope="session">						
									<c:forEach items="${payType}" var="pt">
										<option value="${pt.eopaytypeid}">${pt.eopayname}</option>
									</c:forEach>
								</c:if>
								<c:if test="${not flag}">	
									<option value="1">支付宝</option>
									<option value="2">线下支付</option>				   	
								</c:if>
						</select>
						</span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">书&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
						<div class="formControls col-2">
							<span class="input-text" id="bname" >${eorderBook.bname}</span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">价&nbsp;&nbsp;&nbsp;&nbsp;格:</label>
						<div class="formControls col-2">
							<span class="input-text" id="bprice" >${eorderBook.bprice}</span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">总&nbsp;&nbsp;&nbsp;&nbsp;价:</label>
						<div class="formControls col-2">
							<input class="input-text" id="total" name ="total"readonly="readonly" value="${totalBook}">
						</div>
					</div>
				</div>
			</div>
			<div style="display:none;">
					<input type="text" id="bid" name="bid" value="${eorderMessage['bid'] }">
			</div>
			<div style="display:none;">
					<input type="text" id="itemid" name="itemid" value="${eorderMessage['itemid'] }">
			</div>
			<div >
				<span id="tishi" style="margin-left: 80px; font-size: 18px; width: 300px;"></span>
			</div>
			<div class="clearfix cl">
				<div class="Button_operation">
					<input class="btn btn-primary radius" type="submit"
						id="btn btn-primary radius" value="提交">
					<button onClick="window.location.href='${path}/back/lhoption/index.jsp';" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
<script type="text/javascript">
var xmlhttp;
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
	var bid = "${param.bid}";
	 var itemid = "${param.itemid}";
	 if(xmlhttp!=null){
			// 定义请求地址
			var url ;
			 if(bid != ''){
				 url ="${path}/eorde.s?op=showEorder&bid="+bid;
			 }else if(itemid != ''){
				 url ="${path}/eorde.s?op=showEorder&itemid="+itemid;
			 }else {
				 alert("条件不足无法显示信息！！！");
				 return;
			 }
			xmlhttp.open("POST",url,true);
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var json = xmlhttp.responseText.replace(/\s/gi,"");
					if(json==-1){
						alert("暂无数据");
					}else{
						alert("条件不足无法查询数据！！！");
					}
				}
			};
			// 发送请求
			xmlhttp.send(null);
		}else{
			alert("不能创建XMLHttpRequest对象实例");
		}
}
//监听input框的变化
window.onload = function()
{
	 var msg =  "${param.msg}";
	 if (msg == -1) {
		alert("添加失败！！！");
     }else if(msg == 1){
    	 alert("添加成功！！！");
	}else if(msg == 3){
		alert("信息填写错误！！！");
	}
}
/********添加订单的输入框失去焦点正则判断********/
//地址
$("#eoaddr").on('input',function(){
	var bnameReg = /^[\u4e00-\u9fa5A-Za-z0-9]{0,50}$/;
	var bnameText = $("#eoaddr").val().replace(/\ +/g,"");
	document.getElementById("tishi").style.color = "red";
   if((bnameText =='')){
       document.getElementById("tishi").innerText = "请输入地址！！！";
   }else if(!bnameReg.test(bnameText)){
       document.getElementById("tishi").innerText = "请输入合法地址(最多50个汉字)！！！";
       
   }else {
	   document.getElementById("tishi").innerText = "";
   }
});
//收件人
$("#uname").on('input',function(){
	var btempReg = /^[\u4e00-\u9fa5a-zA-Z]{0,20}$/;
	var btempText = $("#uname").val().replace(/\ +/g,"");
	document.getElementById("tishi").style.color = "red";
   if(!btempReg.test(btempText)){
	   document.getElementById("tishi").innerText = "请输入合法收件人(输入汉字或字母且最多20个汉字)！！！";
   }else if(btempText=""){
	   document.getElementById("tishi").innerText = "请输入收件人";
   } else {
	   document.getElementById("tishi").innerText = "";
   }
});
//电话
$("#uphone").on('input',function(){
	var bauthorReg = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	var bauthorText = $("#uphone").val().replace(/\ +/g,"");
	document.getElementById("tishi").style.color = "red";
   if(!bauthorReg.test(bauthorText)){
	   document.getElementById("tishi").innerText = "请输入合法电话(只能输入以13，14，15，18开头的11位数字)！！！";
   }else if(bauthorText=""){
	   document.getElementById("tishi").innerText = "请输入电话";
   }else {
	   document.getElementById("tishi").innerText = "";
   }
});

$(function() { 
	$("#add_picture").fix({
		float : 'left',
		skin : 'green',	
		durationTime :false,
		stylewidth:'220',
		spacingw:0,
		spacingh:260,
	});
});
$( document).ready(function(){
//初始化宽度、高度
   $(".widget-box").height($(window).height()); 
   $(".page_right_style").height($(window).height()); 
   $(".page_right_style").width($(window).width()-220); 
  //当文档窗口发生改变时 触发  
    $(window).resize(function(){

	 $(".widget-box").height($(window).height()); 
	 $(".page_right_style").height($(window).height()); 
	 $(".page_right_style").width($(window).width()-220); 
	});	
});
function updatePrice(){
	var price =  document.getElementById("price").value;
	var count =  document.getElementById("count").value;
	document.getElementById("total").innerText = price * count;
}

</script>
</body>
</html>