<%@page import="java.util.*"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE htmlparamMap.get("bmajor")>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
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
	href="<%=application.getContextPath() %>/back/Widget/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=application.getContextPath() %>/back/assets/css/font-awesome.min.css" />
<link
	href="<%=application.getContextPath() %>/back/Widget/icheck/icheck.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath() %>/back/Widget/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath() %>/back/css/button.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=application.getContextPath() %>/back/assets/layer/layer.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/laydate/laydate.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath() %>/back/js/jquery-1.9.1.min.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/bootstrap.min.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/typeahead-bs2.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/Widget/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/Widget/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/Widget/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/Widget/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/Widget/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/Widget/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/Widget/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script src="<%=application.getContextPath() %>/back/js/lrtk.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/js/H-ui.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/js/H-ui.admin.js"></script>
<title>新增书籍</title>
</head>
<body>
	<div class="clearfix" id="add_picture">
		<div id="scrollsidebar" class="left_Treeview">
			<div class="show_btn" id="rightArrow">
				<span></span>
			</div>
			<div class="widget-box side_content">
				<div class="side_title">
					<a title="隐藏" class="close_btn"><span></span></a>
				</div>
				<div class="side_list">
					<div class="widget-header header-color-green2">
						<h4 class="lighter smaller">选择书籍类型</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main padding-8">
							<div id="treeDemo" class="ztree"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="page_right_style">
			<div class="type_title">添加书籍</div>
			<form action="" method="post" class="form form-horizontal"
				id="form-article-add">
				<div class="clearfix cl">
					<label class="form-label col-2"><span class="c-red">*</span>书名：</label>
					<div class="formControls col-10">
						<input type="text" class="input-text"
							placeholder="请输入至少两个字符至多五十的汉字" id="bname" name="bname"
							style="margin-left: 40px;">
					</div>
				</div>
				<!-- 	<div class=" clearfix cl">
         <label class="form-label col-2">：</label> 
	     <div class="formControls col-10"><input type="text" class="input-text" value="" placeholder="" id="" name=""></div>
		</div> -->
				<div class=" clearfix cl">
					<%
		//从数据库中查询所有的大学，专业等信息
			Book book = new Book();
			BookBiz bizBook = new BookBiz();
			List<Book> bookList_add = bizBook.selectAll(book);
			HashSet<String> bookUniver = new HashSet<String>();
			HashSet<String> bookUcollage = new HashSet<String>();
			HashSet<String> bookUmagor = new HashSet<String>();
			for(Book bookSet : bookList_add){
				if(null != bookSet.getBuniversity() && !bookSet.getBuniversity().isEmpty()){
						bookUniver.add(bookSet.getBuniversity());
				}
				if(null != bookSet.getBucollege() && !bookSet.getBucollege().isEmpty()){
						bookUcollage.add(bookSet.getBucollege());
				}
				if(null != bookSet.getBumajor() && !bookSet.getBumajor().isEmpty() ){
						bookUmagor.add(bookSet.getBumajor());
				}
			}
		%>
					<div class="Add_p_s">
						<label class="form-label col-2">所属大学:</label>
						<div class="formControls col-2">
							<span class="select-box"> <select class="select"
								id="buniversity" name="buniversity">
									<option>请选择</option>
									<%
							for(String str : bookUniver){
						%>
									<option value="<%=str %>"><%=str %></option>
									<%} %>
							</select>
							</span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">所属学院:</label>
						<div class="formControls col-2">
							<span class="select-box"> <select class="select"
								id="bcollege" name="bcollege">

									<option>请选择</option>
									<%
							for(String str : bookUcollage){
						%>
									<option value="<%=str %>"><%=str %></option>
									<%} %>
							</select>
							</span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">所属专业:</label>
						<div class="formControls col-2">
							<span class="select-box"> <select class="select"
								name="bmajor" id="bmajor">
									<option>请选择</option>
									<%
							for(String str : bookUmagor){
						%>
									<option value="<%=str %>"><%=str %></option>
									<%} %>
							</select>
							</span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">所属年级:</label>
						<div class="formControls col-2">
							<span class="select-box"> <select class="select"
								id="bclass" name="bclass">
									<option>请选择</option>
									<option value="大一">大一</option>
									<option value="大二">大二</option>
									<option value="大三">大三</option>
									<option value="大四">大四</option>
							</select>
							</span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">所属系列:</label>
						<div class="formControls col-2">
							<input type="text" class="input-text" value=""
								placeholder="请输入字符或汉字" id="btemp" name="btemp">
						</div>
					</div>

					<div class="Add_p_s">
						<label class="form-label col-2">作&nbsp;&nbsp;&nbsp;&nbsp;者：</label>
						<div class="formControls col-2">
							<input type="text" class="input-text" value=""
								placeholder="请输入字符或汉字" id="bauthor" name="bauthor">
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">*价&nbsp;&nbsp;&nbsp;&nbsp;格:</label>
						<div class="formControls col-2">
							<input type="text" class="input-text" value=""
								placeholder="请输入数字" id="bprice" name="bprice">元
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">库&nbsp;&nbsp;&nbsp;&nbsp;存:</label>
						<div class="formControls col-2">
							<input type="number" class="input-text" value="" placeholder=""
								id="bnum" name="num">本
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">上传时间:</label>
						<div class="formControls col-2">
							<input class="inline laydate-icon" id="bdate" name="bdate"
								type="date" style="width: 150px;">
						</div>
					</div>
					<div>
						<span id="tishi"
							style="margin-left: 80px; font-size: 20px; width: 230px;"></span>
					</div>
				</div>

				<!-- <div class="clearfix cl">
			<label class="form-label col-2">关键词：</label>
			<div class="formControls col-10">
				<input type="text" class="input-text" value="" placeholder="" id="" name="">
			</div>
		</div>
		<div class="clearfix cl">
			<label class="form-label col-2">内容摘要：</label>
			<div class="formControls col-10">
				<textarea name="" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="textarealength(this,200)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
		</div> -->

				<div class="clearfix cl">
					<label class="form-label col-2">图片上传：</label>
					<div class="formControls col-10">
						<div class="uploader-list-container" >
							<div class="queueList">
								<div id="dndArea" class="placeholder">
									<span id="img_path"></span>
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
						<script id="editor" type="text/plain"
							style="width:100%;height:400px;"></script>
					</div>
				</div>
				<!-- <div class="clearfix cl">
         <label class="form-label col-2">允许评论：</label>
			<div class="formControls col-2 skin-minimal">
			 <div class="check-box" style=" margin-top:9px"><input type="checkbox" id="checkbox-1"><label for="checkbox-1">&nbsp;</label></div>
             </div>
        </div> -->
				<div class="clearfix cl">
					<div class="Button_operation">
						<button onClick="article_save_submit();"
							class="btn btn-primary radius" type="button" id="btn btn-primary radius">
							<i class="icon-save "></i>保存并提交审核
						</button>
						<!-- <button onClick="article_save();"
							class="btn btn-secondary  btn-warning" type="button">
							<i class="icon-save"></i>保存草稿
						</button> -->
						<button onClick="window.location.href='<%=application.getContextPath() %>/back/book/Products_List.jsp';" class="btn btn-default radius"
							type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script>
var imgPath=  null;//图片上传路径
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
$(function(){
	var ue = UE.getEditor('editor');
});
/******树状图********/
var setting = {
	view: {
		dblClickExpand: false,
		showLine: false,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		//点击事件
		beforeClick: function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				bookType = treeNode.btid;
				return true;
			}
		}
	}
};
/******书籍数据保存数据库********/

function upImg(){
	// js 获取文件对象
	var fileObj = document.getElementById("upload-file").files[0]; // js 获取文件对象
	var form = new FormData(); // FormData 对象
    form.append("file", fileObj); // 文件对象
	$.ajax({
        url:'<%=application.getContextPath()%>/book.s?op=uploadImage', 
        type:'post',
        data: form,
        contentType: false,
        processData: false,
        success:function(result){
        	if(result.code == 1){
        		imgPath = result.data;
        		document.getElementById("img_path").innerText = imgPath;
			}
        }
    });
}
function article_save_submit(){
	var bnameText = $("#bname").val().replace(/\ +/g,"");
	var bpriceText = $("#bprice").val().replace(/\ +/g,"");
	document.getElementById("tishi").style.color = "red";
	if(bpriceText ==''){
	    document.getElementById("tishi").innerText = "请输入价格！！！";
	}else if(bnameText ==''){
		document.getElementById("tishi").innerText = "请输入书名！！！";
	}else if(bookType == 0){
		document.getElementById("tishi").innerText = "请点击书籍类型！！！";
	}else if(imgPath == null){
		document.getElementById("tishi").innerText = "请选择图片！！！";
	}else{
		document.getElementById("tishi").innerText = "";
		// js 获取文件对象
		var form = new FormData($('#form-article-add')[0]); // FormData 对象
		form.append("bookType",bookType);
		form.append("imgPath",imgPath);
	    $.ajax({
	        url:'<%=application.getContextPath()%>/book.s?op=add', 
	        type:'post',
	        data: form,
	        contentType: false,
	        processData: false,
	        success:function(result){
	        	if(result.code == 1){
	        		document.getElementById("tishi").style.color = "green";
	        		document.getElementById("tishi").innerText = result.msg;
				} else {
					document.getElementById("tishi").innerText = result.msg;
				}
	        }
	    });
	}
}
<%-- function getType(){
	var zNodes = null;
	$.ajax({
        url:'<%=application.getContextPath()%>/bookType.s?op=query', 
        type:'post',
        contentType: false,
        processData: false,
        success:function(result){
        	zNodes=result;
        	alert(result);
        }
    });
	if(zNodes != null){
		return zNodes;
	}
	return null; 
} --%>
var zNodes =[
	{ id:1, pId:0, name:"书籍分类列表",btid:0, open:true},
	{ id:11, pId:1, name:"教材区",btid:1},
	{ id:111, pId:11, name:"成功励志",btid:4},
	{ id:112, pId:11, name:"法律",btid:5},
	{ id:113, pId:11, name:"管理",btid:6},
	{ id:114, pId:11, name:"计算机与网络",btid:7},
	{ id:115, pId:11, name:"教育考试",btid:8},
	{ id:116, pId:11, name:"科技工程",btid:9},
	{ id:117, pId:11, name:"生活时尚",btid:10},
	{ id:118, pId:11, name:"文化历史",btid:11},
	{ id:12, pId:1, name:"工具书区",btid:2},
	{ id:121, pId:12, name:"英语四六级 ",btid:12},
	{ id:122, pId:12, name:"公务员资料 ",btid:13},
	{ id:123, pId:12, name:"考研资料 ",btid:14},
	{ id:124, pId:12, name:"雅思托福 ",btid:15},
	{ id:125, pId:12, name:"其他 ",btid:16},
	{ id:13, pId:1, name:"分享区",btid:3},
	{ id:131, pId:13, name:"分享区",btid:3}
];		
var code;
		
function showCode(str) {
	if (!code) code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
}
$(document).ready(function(){
	var t = $("#treeDemo");
	t = $.fn.zTree.init(t, setting, zNodes);
	demoIframe = $("#testIframe");
	demoIframe.bind("load", loadReady);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(zTree.getNodeByParam("id",'11'));
});			
</script>

<!-- 	<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$list = $("#fileList"),
	$btn = $("#btn-star"),
	state = "pending",
	uploader;

	/* var uploader = WebUploader.create({
		auto: true,
		swf: 'lib/webuploader/0.1.5/Uploader.swf',
		// 文件接收服务端。
		server: 'http://lib.h-ui.net/webuploader/0.1.5/server/fileupload.php',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: '#filePicker',
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize: false,
		// 只允许选择图片文件。
		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/*'
		}
	});
	uploader.on( 'fileQueued', function( file ) {
		var $li = $(
			'<div id="' + file.id + '" class="item">' +
				'<div class="pic-box"><img></div>'+
				'<div class="info">' + file.name + '</div>' +
				'<p class="state">等待上传...</p>'+
			'</div>'
		),
		$img = $li.find('img');
		$list.append( $li );
	
		// 创建缩略图
		// 如果为非图片文件，可以不用调用此方法。
		// thumbnailWidth x thumbnailHeight 为 100 x 100
		uploader.makeThumb( file, function( error, src ) {
			if ( error ) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}
	
			$img.attr( 'src', src );
		}, thumbnailWidth, thumbnailHeight );
	});
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
		var $li = $( '#'+file.id ),
			$percent = $li.find('.progress-box .sr-only');
	
		// 避免重复创建
		if ( !$percent.length ) {
			$percent = $('<div class="progress-box"><span class="progress-bar radius"><span class="sr-only" style="width:0%"></span></span></div>').appendTo( $li ).find('.sr-only');
		}
		$li.find(".state").text("上传中");
		$percent.css( 'width', percentage * 100 + '%' );
	});
	
	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on( 'uploadSuccess', function( file ) {
		$( '#'+file.id ).addClass('upload-state-success').find(".state").text("已上传");
	});
	
	// 文件上传失败，显示上传出错。
	uploader.on( 'uploadError', function( file ) {
		$( '#'+file.id ).addClass('upload-state-error').find(".state").text("上传出错");
	});
	
	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on( 'uploadComplete', function( file ) {
		$( '#'+file.id ).find('.progress-box').fadeOut();
	});
	uploader.on('all', function (type) {
        if (type === 'startUpload') {
            state = 'uploading';
        } else if (type === 'stopUpload') {
            state = 'paused';
        } else if (type === 'uploadFinished') {
            state = 'done';
        }

        if (state === 'uploading') {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });
    $btn.on('click', function () {
        if (state === 'uploading') {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });

});
 */
<%-- (function( $ ){
    // 当domReady的时候开始初始化
    $(function() {
        var $wrap = $('.uploader-list-container'),

            // 图片容器
            $queue = $( '<ul class="filelist"></ul>' )
                .appendTo( $wrap.find( '.queueList' ) ),

            // 状态栏，包括进度和控制按钮
            $statusBar = $wrap.find( '.statusBar' ),

            // 文件总体选择信息。
            $info = $statusBar.find( '.info' ),

            // 上传按钮
            $upload = $wrap.find( '.uploadBtn' ),

            // 没选择文件之前的内容。
            $placeHolder = $wrap.find( '.placeholder' ),

            $progress = $statusBar.find( '.progress' ).hide(),

            // 添加的文件数量
            fileCount = 0,

            // 添加的文件总大小
            fileSize = 0,

            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,

            // 缩略图大小
            thumbnailWidth = 110 * ratio,
            thumbnailHeight = 110 * ratio,

            // 可能有pedding, ready, uploading, confirm, done.
            state = 'pedding',

            // 所有文件的进度信息，key为file id
            percentages = {},
            // 判断浏览器是否支持图片的base64
            isSupportBase64 = ( function() {
                var data = new Image();
                var support = true;
                data.onload = data.onerror = function() {
                    if( this.width != 1 || this.height != 1 ) {
                        support = false;
                    }
                }
                data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                return support;
            } )(),

            // 检测是否已经安装flash，检测flash的版本
            flashVersion = ( function() {
                var version;

                try {
                    version = navigator.plugins[ 'Shockwave Flash' ];
                    version = version.description;
                } catch ( ex ) {
                    try {
                        version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                                .GetVariable('$version');
                    } catch ( ex2 ) {
                        version = '0.0';
                    }
                }
                version = version.match( /\d+/g );
                return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
            } )(),

            supportTransition = (function(){
                var s = document.createElement('p').style,
                    r = 'transition' in s ||
                            'WebkitTransition' in s ||
                            'MozTransition' in s ||
                            'msTransition' in s ||
                            'OTransition' in s;
                s = null;
                return r;
            })(),

            // WebUploader实例
            uploader;

        if ( !WebUploader.Uploader.support('flash') && WebUploader.browser.ie ) {

            // flash 安装了但是版本过低。
            if (flashVersion) {
                (function(container) {
                    window['expressinstallcallback'] = function( state ) {
                        switch(state) {
                            case 'Download.Cancelled':
                                alert('您取消了更新！')
                                break;

                            case 'Download.Failed':
                                alert('安装失败')
                                break;

                            default:
                                alert('安装已成功，请刷新！');
                                break;
                        }
                        delete window['expressinstallcallback'];
                    };

                    var swf = 'expressInstall.swf';
                    // insert flash object
                    var html = '<object type="application/' +
                            'x-shockwave-flash" data="' +  swf + '" ';

                    if (WebUploader.browser.ie) {
                        html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                    }

                    html += 'width="100%" height="100%" style="outline:0">'  +
                        '<param name="movie" value="' + swf + '" />' +
                        '<param name="wmode" value="transparent" />' +
                        '<param name="allowscriptaccess" value="always" />' +
                    '</object>';

                    container.html(html);

                })($wrap);

            // 压根就没有安转。
            } else {
                $wrap.html('<a href="<%=application.getContextPath() %>/back/http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="<%=application.getContextPath() %>/back/http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
            }

            return;
        } else if (!WebUploader.Uploader.support()) {
            alert( 'Web Uploader 不支持您的浏览器！');
            return;
        }

        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker-2',
                label: '点击选择图片'
            },
            formData: {
                uid: 123
            },
            dnd: '#dndArea',
            paste: '#uploader',
            swf: 'lib/webuploader/0.1.5/Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            server: 'http://lib.h-ui.net/webuploader/0.1.5/server/fileupload.php',
            // runtimeOrder: 'flash',

            // accept: {
            //     title: 'Images',
            //     extensions: 'gif,jpg,jpeg,bmp,png',
            //     mimeTypes: 'image/*'
            // },

            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 300,
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
        });

        // 拖拽时不接受 js, txt 文件。
        uploader.on( 'dndAccept', function( items ) {
            var denied = false,
                len = items.length,
                i = 0,
                // 修改js类型
                unAllowed = 'text/plain;application/javascript ';

            for ( ; i < len; i++ ) {
                // 如果在列表里面
                if ( ~unAllowed.indexOf( items[ i ].type ) ) {
                    denied = true;
                    break;
                }
            }

            return !denied;
        });

        uploader.on('dialogOpen', function() {
            console.log('here');
        });

       
        // 添加“添加文件”的按钮，
        uploader.addButton({
            id: '#filePicker2',
            label: '继续添加'
        });

        uploader.on('ready', function() {
            window.uploader = uploader;
        });

        // 当有文件添加进来时执行，负责view的创建
        function addFile( file ) {
            var $li = $( '<li id="' + file.id + '">' +
                    '<p class="title">' + file.name + '</p>' +
                    '<p class="imgWrap"></p>'+
                    '<p class="progress"><span></span></p>' +
                    '</li>' ),

                $btns = $('<div class="file-panel">' +
                    '<span class="cancel">删除</span>' +
                    '<span class="rotateRight">向右旋转</span>' +
                    '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
                $prgress = $li.find('p.progress span'),
                $wrap = $li.find( 'p.imgWrap' ),
                $info = $('<p class="error"></p>'),

                showError = function( code ) {
                    switch( code ) {
                        case 'exceed_size':
                            text = '文件大小超出';
                            break;

                        case 'interrupt':
                            text = '上传暂停';
                            break;

                        default:
                            text = '上传失败，请重试';
                            break;
                    }

                    $info.text( text ).appendTo( $li );
                };

            if ( file.getStatus() === 'invalid' ) {
                showError( file.statusText );
            } else {
                // @todo lazyload
                $wrap.text( '预览中' );
                uploader.makeThumb( file, function( error, src ) {
                    var img;

                    if ( error ) {
                        $wrap.text( '不能预览' );
                        return;
                    }

                    if( isSupportBase64 ) {
                        img = $('<img src="<%=application.getContextPath() %>/back/'+src+'">');
                        $wrap.empty().append( img );
                    } else {
                        $.ajax('lib/webuploader/0.1.5/server/preview.php', {
                            method: 'POST',
                            data: src,
                            dataType:'json'
                        }).done(function( response ) {
                            if (response.result) {
                                img = $('<img src="'+response.result+'">');
                                $wrap.empty().append( img );
                            } else {
                                $wrap.text("预览出错");
                            }
                        });
                    }
                }, thumbnailWidth, thumbnailHeight );

                percentages[ file.id ] = [ file.size, 0 ];
                file.rotation = 0;
            }

            file.on('statuschange', function( cur, prev ) {
                if ( prev === 'progress' ) {
                    $prgress.hide().width(0);
                } else if ( prev === 'queued' ) {
                    $li.off( 'mouseenter mouseleave' );
                    $btns.remove();
                }

                // 成功
                if ( cur === 'error' || cur === 'invalid' ) {
                    console.log( file.statusText );
                    showError( file.statusText );
                    percentages[ file.id ][ 1 ] = 1;
                } else if ( cur === 'interrupt' ) {
                    showError( 'interrupt' );
                } else if ( cur === 'queued' ) {
                    percentages[ file.id ][ 1 ] = 0;
                } else if ( cur === 'progress' ) {
                    $info.remove();
                    $prgress.css('display', 'block');
                } else if ( cur === 'complete' ) {
                    $li.append( '<span class="success"></span>' );
                }

                $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
            });

            $li.on( 'mouseenter', function() {
                $btns.stop().animate({height: 30});
            });

            $li.on( 'mouseleave', function() {
                $btns.stop().animate({height: 0});
            });

            $btns.on( 'click', 'span', function() {
                var index = $(this).index(),
                    deg;

                switch ( index ) {
                    case 0:
                        uploader.removeFile( file );
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if ( supportTransition ) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                }
            });

            $li.appendTo( $queue );
        }

        // 负责view的销毁
        function removeFile( file ) {
            var $li = $('#'+file.id);

            delete percentages[ file.id ];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        function updateTotalProgress() {
            var loaded = 0,
                total = 0,
                spans = $progress.children(),
                percent;

            $.each( percentages, function( k, v ) {
                total += v[ 0 ];
                loaded += v[ 0 ] * v[ 1 ];
            } );
            percent = total ? loaded / total : 0;
            spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
            spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
            updateStatus();
        }

        function updateStatus() {
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize( fileSize ) + '。';
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                        stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
						}
					} else {
						stats = uploader.getStats();
						text = '共' + fileCount + '张('
								+ WebUploader.formatSize(fileSize) + ')，已上传'
								+ stats.successNum + '张';

						if (stats.uploadFailNum) {
							text += '，失败' + stats.uploadFailNum + '张';
						}
					}
					$info.html(text);
				}

				function setState(val) {
					var file, stats;

					if (val === state) {
						return;
					}

					$upload.removeClass('state-' + state);
					$upload.addClass('state-' + val);
					state = val;

					switch (state) {
					case 'pedding':
						$placeHolder.removeClass('element-invisible');
						$queue.hide();
						$statusBar.addClass('element-invisible');
						uploader.refresh();
						break;

					case 'ready':
						$placeHolder.addClass('element-invisible');
						$('#filePicker2').removeClass('element-invisible');
						$queue.show();
						$statusBar.removeClass('element-invisible');
						uploader.refresh();
						break;

					case 'uploading':
						$('#filePicker2').addClass('element-invisible');
						$progress.show();
						$upload.text('暂停上传');
						break;

					case 'paused':
						$progress.show();
						$upload.text('继续上传');
						break;

					case 'confirm':
						$progress.hide();
						$('#filePicker2').removeClass('element-invisible');
						$upload.text('开始上传');

						stats = uploader.getStats();
						if (stats.successNum && !stats.uploadFailNum) {
							setState('finish');
							return;
						}
						break;
					case 'finish':
						stats = uploader.getStats();
						if (stats.successNum) {
							alert('上传成功');
						} else {
							// 没有成功的图片，重设
							state = 'done';
							location.reload();
						}
						break;
					}

					updateStatus();
				}

				uploader.onUploadProgress = function(file, percentage) {
					var $li = $('#' + file.id), $percent = $li
							.find('.progress span');

					$percent.css('width', percentage * 100 + '%');
					percentages[file.id][1] = percentage;
					updateTotalProgress();
				};

				uploader.onFileQueued = function(file) {
					fileCount++;
					fileSize += file.size;

					if (fileCount === 1) {
						$placeHolder.addClass('element-invisible');
						$statusBar.show();
					}

					addFile(file);
					setState('ready');
					updateTotalProgress();
				};

				uploader.onFileDequeued = function(file) {
					fileCount--;
					fileSize -= file.size;

					if (!fileCount) {
						setState('pedding');
					}

					removeFile(file);
					updateTotalProgress();

				};

				uploader.on('all', function(type) {
					var stats;
					switch (type) {
					case 'uploadFinished':
						setState('confirm');
						break;

					case 'startUpload':
						setState('uploading');
						break;

					case 'stopUpload':
						setState('paused');
						break;

					}
				});
				uploader.onError = function(code) {
					alert('Eroor: ' + code);
				};

				$upload.on('click', function() {
					if ($(this).hasClass('disabled')) {
						return false;
					}

					if (state === 'ready') {
						uploader.upload();
					} else if (state === 'paused') {
						uploader.upload();
					} else if (state === 'uploading') {
						uploader.stop();
					}
				});

				$info.on('click', '.retry', function() {
					uploader.retry();
				});

				$info.on('click', '.ignore', function() {
					alert('todo');
				});

				$upload.addClass('state-' + state);
				updateTotalProgress();
			});

		})(jQuery); --%>
	</script>
	 -->
</body>
</html>