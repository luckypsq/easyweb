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
<title>修改书籍信息</title>
</head>
<body onload="show()">
	<div class="page_right_style">
		<div class="type_title">修改书籍信息</div>
		<form action="${path }/book.s?op=updateBook&bid=${showBookEdit.bid}" method="post" class="form form-horizontal"
			id="form-article-add">
			<div class="clearfix cl">
				<label class="form-label col-2"><span class="c-red">*</span>书名：</label>
				<div class="formControls col-10">
					<input type="text" class="input-text"
						placeholder="请输入至少两个字符至多五十个的汉字" id="bname" name="bname" value="${showBookEdit.bname}"
						style="margin-left: 40px;">
				</div>
			</div>
			<div class=" clearfix cl">
				
				<div class="Add_p_s">
					<label class="form-label col-2">所属大学:</label>
					<div class="formControls col-2">
						<span class="select-box"> <select class="select"
							id="buniversity" name="buniversity">
								<c:if test="${showBookEdit.buniversity == null}" var="flag" scope="session">
									<option>请选择</option>
								</c:if>
								<c:if test="${not flag}">
									<option>${showBookEdit.buniversity}</option>
								</c:if>
								<c:forEach items="${bookUniverEdit}" var="uni">
									<option value="${uni }">${uni }</option>
								</c:forEach>
						</select>
						</span>
					</div>
				</div>
				<div class="Add_p_s">
					<label class="form-label col-2">所属学院:</label>
					<div class="formControls col-2">
						<span class="select-box"> <select class="select"
							id="bcollege" name="bcollege">
								<c:if test="${showBookEdit.bucollege == null}" var="flag" scope="session">
									<option>请选择</option>
								</c:if>
								<c:if test="${not flag}">
									<option>${showBookEdit.bucollege}</option>
								</c:if>
								<c:forEach items="${bookUcollageEdit}" var="ucl">
									<option value="${ucl }">${ucl }</option>
								</c:forEach>
						</select>
						</span>
					</div>
				</div>
				<div class="Add_p_s">
					<label class="form-label col-2">所属专业:</label>
					<div class="formControls col-2">
						<span class="select-box"> <select class="select"
							name="bmajor" id="bmajor">
							<c:if test="${showBookEdit.bumajor == null}" var="flag" scope="session">
									<option>请选择</option>
								</c:if>
								<c:if test="${not flag}">
									<option>${showBookEdit.bumajor}</option>
								</c:if>
								<c:forEach items="${bookUmagorEdit}" var="uma">
									<option value="${uma }">${uma }</option>
								</c:forEach>
						</select>
						</span>
					</div>
				</div>
				<div class="Add_p_s">
					<label class="form-label col-2">所属年级:</label>
					<div class="formControls col-2">
						<span class="select-box"> <select class="select"
							id="bclass" name="bclass">
								<option value="${showBookEdit.bclass }">${showBookEdit.bclass }</option>
								<option value="大一">大一</option>
								<option value="大二">大二</option>
								<option value="大三">大三</option>
								<option value="大四">大四</option>
						</select>
						</span>
					</div>
				</div>
				<div class="Add_p_s">
					<label class="form-label col-2">所属类别:</label>
					<div class="formControls col-2">
						<span class="select-box"> <select class="select"
							id="btype" name="btype">
								<c:if test="${showTypeEdit == ''}" var="flag" scope="session">
									<option>请选择</option>
								</c:if>
								<c:if test="${not flag}">
									<option>${showTypeEdit}</option>
								</c:if>
								<c:forEach items="${btTypeEdit}" var="bty">
									<option value="${bty}">${bty }</option>
								</c:forEach>
						</select>
						</span>
					</div>
				</div>
				<div class="Add_p_s">
					<label class="form-label col-2">所属系列:</label>
					<div class="formControls col-2">
						<input type="text" class="input-text"
							placeholder="请输入字符或汉字" id="btemp" name="btemp" value="${showBookEdit.btemp }">
					</div>
				</div>

				<div class="Add_p_s">
					<label class="form-label col-2">作&nbsp;&nbsp;&nbsp;&nbsp;者：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${showBookEdit.bauthor }"
							placeholder="请输入字符或汉字" id="bauthor" name="bauthor">
					</div>
				</div>
				<div class="Add_p_s">
					<label class="form-label col-2">*价&nbsp;&nbsp;&nbsp;&nbsp;格:</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${showBookEdit.bprice }" placeholder="请输入数字"
							id="bprice" name="bprice">元
					</div>
				</div>
				<div class="Add_p_s">
					<label class="form-label col-2">库&nbsp;&nbsp;&nbsp;&nbsp;存:</label>
					<div class="formControls col-2">
						<input type="number" class="input-text"
							id="bnum" name="bnum" value="${showBookEdit.bnum }">本
					</div>
				</div>
				<div class="Add_p_s">
					<label class="form-label col-2">上传时间:</label>
					<div class="formControls col-2">
						<input class="inline laydate-icon" id="bdate" name="bdate"
							type="date" style="width: 150px;" value="${showBookEdit.bdate }">
					</div>
				</div>
				<div style="display:none;">
					<input type="text" id="img_path" name="img_path" >
				</div>
			</div>
			<div >
					<span id="tishi"
						style="margin-left: 80px; font-size: 18px; width: 300px;"></span>
				</div>
			<div class="clearfix cl">
				<label class="form-label col-2">图片上传：</label>
				<div class="formControls col-10">
					<div class="uploader-list-container">
						<div class="queueList">
							<div id="dndArea" class="placeholder">
							<c:if test="${showBookEdit.bimg == null}" var="flag" scope="session">
									<img id="imghead" name="imghead"border=0 src="${path }/images/book.jpg" style="width:300px;height:200px;"/>
								</c:if>
								<c:if test="${not flag}">
									<img id="imghead" name="imghead"border=0 src="${showBookEdit.bimg }" 
									style="width:300px;height:200px;"/>
								</c:if>
								<div class="new-contentarea tc">
									<a href="javascript:void(0)" class="upload-img"><label
										for="upload-file">点击选择图片</label></a> <input type="file" class=""
										name="upload-file" id="upload-file" onblur="upImg();" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="clearfix cl">
				<label class="form-label col-2">描述：</label>
				<div class="formControls col-10">
				<textarea rows="3" cols="30" id="bcontent"name="bcontent">
				${showBookEdit.bcontent}
				</textarea>
				</div>
			</div>
			<div class="clearfix cl">
				<div class="Button_operation">
					<input class="btn btn-primary radius" type="submit"
						id="btn btn-primary radius" value="保存并提交审核">
					<button onClick="window.location.href='${path}/back/book/Products_List.jsp';" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
<script type="text/javascript">
var booType = 0;//书籍类型
/********添加书籍的输入框失去焦点正则判断********/
$("#bname").on('input',function(){
	var bnameReg = /^[\w\u4e00-\u9fa5]{2,50}$/;
	var bnameText = $("#bname").val().replace(/\ +/g,"");
	document.getElementById("tishi").style.color = "red";
   if((bnameText =='')){
       document.getElementById("tishi").innerText = "请输入书名！！！";
       
   }else if(!bnameReg.test(bnameText)){
       document.getElementById("tishi").innerText = "请输入合法书名(至少两个字符或50个汉字)！！！";
       
   }else {
	   document.getElementById("tishi").innerText = "";
   }
});
$("#btemp").on('input',function(){
	var btempReg = /^[\u4e00-\u9fa5]{0,20}$/;
	var btempText = $("#btemp").val().replace(/\ +/g,"");
	document.getElementById("tishi").style.color = "red";
   if(!btempReg.test(btempText)){
	   document.getElementById("tishi").innerText = "请输入合法系列名(只能输入汉字且最多20个汉字)！！！";
   }else if(btempText=""){
	   document.getElementById("tishi").innerText = "";
   } else {
	   document.getElementById("tishi").innerText = "";
   }
});
$("#bauthor").on('input',function(){
	var bauthorReg = /^[\u4e00-\u9fa5A-Za-z\w]{1,20}$/;
	var bauthorText = $("#bauthor").val().replace(/\ +/g,"");
	document.getElementById("tishi").style.color = "red";
   if(!bauthorReg.test(bauthorText)){
	   document.getElementById("tishi").innerText = "请输入合法作者名(只能输入汉字或字母且最多20个汉字)！！！";
   }else if(bauthorText=""){
	   document.getElementById("tishi").innerText = "";
   }else {
	   document.getElementById("tishi").innerText = "";
   }
});
$("#bprice").on('input',function(){
	var bpriceReg = /^[0-9]+(.[0-9]{0,2})?$/;
	var bpriceText = $("#bprice").val().replace(/\ +/g,"");
	
	document.getElementById("tishi").style.color = "red";
   if(bpriceText ==''){
       document.getElementById("tishi").innerText = "请输入价格！！！";
   }else if(!bpriceReg.test(bpriceText)){
       document.getElementById("tishi").innerText = "请输入合法价格(小数点后最多有两位，数值最大不超过十位)！！！";
   }else {
	   document.getElementById("tishi").innerText = "";
   }
});
$("#bname").on('blur',function(){
	var bnameReg = /^[\w\u4e00-\u9fa5]{2,50}$/;
	var bnameText = $("#bname").val().replace(/\ +/g,"");
	document.getElementById("tishi").style.color = "red";
   if((bnameText =='')){
       document.getElementById("tishi").innerText = "请输入书名！！！";
       
   }else if(!bnameReg.test(bnameText)){
       document.getElementById("tishi").innerText = "请输入合法书名(至少两个字符或50个汉字)！！！";
       
   }else {
	   document.getElementById("tishi").innerText = "";
   }
});
$("#btemp").on('blur',function(){
	var btempReg = /^[\u4e00-\u9fa5]{0,20}$/;
	var btempText = $("#btemp").val().replace(/\ +/g,"");
	document.getElementById("tishi").style.color = "red";
   if(!btempReg.test(btempText)){
	   document.getElementById("tishi").innerText = "请输入合法系列名(只能输入汉字且最多20个汉字)！！！";
   }else if(btempText=""){
	   document.getElementById("tishi").innerText = "";
   } else {
	   document.getElementById("tishi").innerText = "";
   }
});
$("#bauthor").on('blur',function(){
	var bauthorReg = /^[\u4e00-\u9fa5A-Za-z\w]{1,20}$/;
	var bauthorText = $("#bauthor").val().replace(/\ +/g,"");
	document.getElementById("tishi").style.color = "red";
   if(!bauthorReg.test(bauthorText)){
	   document.getElementById("tishi").innerText = "请输入合法作者名(只能输入汉字或字母且最多20个汉字)！！！";
   }else if(bauthorText=""){
	   document.getElementById("tishi").innerText = "";
   }else {
	   document.getElementById("tishi").innerText = "";
   }
});
$("#bprice").on('blur',function(){
	var bpriceReg = /^[0-9]+(.[0-9]{0,2})?$/;
	var bpriceText = $("#bprice").val().replace(/\ +/g,"");
	
	document.getElementById("tishi").style.color = "red";
   if(bpriceText ==''){
       document.getElementById("tishi").innerText = "请输入价格！！！";
   }else if(!bpriceReg.test(bpriceText)){
       document.getElementById("tishi").innerText = "请输入合法价格(小数点后最多有两位，数值最大不超过十位)！！！";
   }else {
	   document.getElementById("tishi").innerText = "";
   }
});
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
function show(){
	var id='${param.bid}';
	if(xmlhttp!=null){
		// 定义请求地址
		var url ="${path}/book.s?op=editShow&bid="+id;
		// 以 POST 方式 开启连接
		// POST 请求 更安全（编码）  提交的数据大小没有限制
		xmlhttp.open("POST",url,true);
		// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
		// 每次的状态改变都会调用该方法
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			}
		};
		// 发送请求
		xmlhttp.send(null);
	}else{
		layer.msg('不能创建XMLHttpRequest对象实例', {
			icon : 2,
			time : 1000
			});
	} 
}

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
/* //初始化UEditor
$(function(){
	var ue = UE.getEditor('editor');
	ue.ready(function() {
	    //设置默认值
	    ue.setContent('默认值....');
	});
}); */

/******书籍数据保存数据库********/

function upImg(){
	// js 获取文件对象
	var fileObj = document.getElementById("upload-file").files[0]; // js 获取文件对象
	var form = new FormData(); // FormData 对象
    form.append("file", fileObj); // 文件对象
	$.ajax({
        url:'${path}/book.s?op=uploadImage', 
        type:'post',
        data: form,
        contentType: false,
        processData: false,
        success:function(result){
        	if(result.code == 1){
        		document.getElementById("imghead").src =result.data;
        		document.getElementById("img_path").value =result.data;
			}
        }
    });
}
</script>
</body>
</html>