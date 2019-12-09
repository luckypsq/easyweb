<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
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
	href="${path}/back/font/css/font-awesome.min.css" />
<script
	src="${path}/back/js/jquery-1.9.1.min.js"></script>
<script
	src="${path}/back/assets/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${path}/back/Widget/Validform/5.3.2/Validform.min.js"></script>
<script
	src="${path}/back/assets/js/typeahead-bs2.min.js"></script>
<script
	src="${path}/back/assets/js/jquery.dataTables.min.js"></script>
<script
	src="${path}/back/assets/js/jquery.dataTables.bootstrap.js"></script>
<script
	src="${path}/back/assets/layer/layer.js"
	type="text/javascript"></script>
<script src="${path}/back/js/lrtk.js"
	type="text/javascript"></script>
<script
	src="${path}/back/assets/layer/layer.js"
	type="text/javascript"></script>
<script
	src="${path}/back/assets/laydate/laydate.js"
	type="text/javascript"></script>
<title>管理员</title>
</head>

<body onload="selectAdmin()">
	<div class="page-content clearfix">
		<div class="administrator">
				<div class="d_Confirm_Order_style">
				<div class="search_style">
					<ul class="search_content clearfix">
						<li><label class="l_f">管理员名称</label><input name="adminName"id="adminName" value="${userName}"
							type="text" class="text_add"  style="width: 400px" /></li>
						<li><label class="l_f">管理员电话</label><input style=" margin-left:10px;" name="adminType"id="adminType"
							type="text" class="text_add"  style="width: 400px" value="${userPhone }"/></li>
						<li style="width: 90px;"><button type="button" onclick="selectAdmin();"
								class="btn_search">
								<i class="fa fa-search"></i>查询
							</button></li>
					</ul>
				</div>
				<!--操作-->
				<div class="border clearfix">
					<span class="l_f"><a href="${path}/back/admin/userAdd.jsp" id="administrator_add" class="btn btn-warning"><i class="fa fa-plus"></i> 添加管理员</a>
				 <a onclick="selectDelete();"
						class="btn btn-danger"><i class="fa fa-trash"></i> 批量删除</a>
					</span> <span class="r_f">共：<b>${adminAllExit.size() }</b>人
					</span>
				</div>
				<!--管理员列表-->
				<div class="clearfix administrator_style" id="administrator">
					<div class="left_style">
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
										<h4 class="lighter smaller">管理员分类列表</h4>
									</div>
									<div class="widget-body">
										<ul class="b_P_Sort_list">
											<li><i class="fa fa-users green"></i> <a href="${path}/back/admin/administrator.jsp">全部管理员(${numAdmin[2] })
											</a></li>
											<li><i class="fa fa-users orange"></i> <a href="${path}/back/admin/administrator.jsp?type=1">超级管理员(${numAdmin[0] })
											</a></li>
											<li><i class="fa fa-users orange"></i> <a href="${path}/back/admin/administrator.jsp?type=5">普通管理员(${numAdmin[1] })
											</a></li>
									</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="table_menu_list" id="testIframe">
						<table class="table table-striped table-bordered table-hover"
							id="sample_table">
							<thead>
								<tr>
									<th width="25px"><label><input type="checkbox"
											class="ace"><span class="lbl"></span></label></th>
									<th width="80px">编号</th>
									<th width="250px">登录名</th>
									<th width="100px">手机</th>
									<th width="100px">邮箱</th>
									<th width="100px">角色</th>
									<th width="180px">加入时间</th>
									<th width="70px">状态</th>
									<th width="200px">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${adminAllExit}" var="admin">
									<tr>
										<td><label><input type="checkbox" class="ace"><span
												class="lbl"></span></label></td>
										<td>${admin.uid }</td>
										<td>${admin.uname }</td>
										<td>${admin.uphone }</td>
										<td>${admin.uemail }</td>
										<td>${admin.utype == 5 ? "普通管理员" :"超级管理员" }</td>
										<td>${admin.utime }</td>
										<c:if test="${admin.ustate == 1}" var="flag" scope="session">
											<td class="td-status">
												<span class="label label-success radius">已启用</span>
											</td>
										</c:if>
										
										<c:if test="${not flag}">
											<td class="td-status">
											<span class="label label-defaunt radius">${adminStateC[admin.ustate] }</span>
										</td>
										</c:if>
									
										<td class="td-manage">
										
											<c:if test="${admin.ustate == 1}" var="flag" scope="session">
													<a
													onClick="member_stop(this,'${admin.uid }')"
													href="javascript:;"
													title="停用" class="btn btn-xs btn-success"><i
													class="fa fa-check  bigger-120"></i></a>
											</c:if>
											
											<c:if test="${not flag}">
												<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,${admin.uid })" href="javascript:;" title="启用"><i class="fa fa-close bigger-120"></i></a>
											</c:if>
											<a title="编辑"
											href="${path}/back/admin/userAdd.jsp?uid=${admin.uid }"
											class="btn btn-xs btn-info"><i
												class="fa fa-edit bigger-120"></i></a> <a title="删除"
											href="javascript:;" onclick="member_del(this,${admin.uid })"
											class="btn btn-xs btn-warning"><i
												class="fa fa-trash  bigger-120"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
	<script type="text/javascript">
	var xmlhttp;
	//ajax
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
	var sbox = "";
	jQuery(function($) {
		var oTable1 = $('#sample_table').dataTable({
			"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
			"bStateSave" : true,//状态保存
			"aoColumnDefs" : [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{
				"orderable" : false,
				"aTargets" : [ 0, 2, 3, 4, 5, 7, 8, ]
			} // 制定列不参与排序
			]
		});

		$('table th input:checkbox').on(
				'click',
				function() {
					if(sbox == "/"){
						sbox = "";
					}else{
						sbox = "/";
					}
					var that = this;
					$(this).closest('table').find(
							'tr > td:first-child input:checkbox').each(
							function() {
								this.checked = that.checked;
								$(this).closest('tr').toggleClass('selected');
							});

				});
		//设置单选
		$('table tr input:checkbox').on(
		'click',
		function() {
			var that = this;
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
		$('[data-rel="tooltip"]').tooltip({
			placement : tooltip_placement
		});
		
		function tooltip_placement(context, source) {
			var $source = $(source);
			var $parent = $source.closest('table')
			var off1 = $parent.offset();
			var w1 = $parent.width();

			var off2 = $source.offset();
			var w2 = $source.width();

			if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2))
				return 'right';
			return 'left';
		}
	});
	$(function() {
		$("#administrator").fix({
			float : 'left',
			//minStatue : true,
			skin : 'green',
			durationTime : false,
			spacingw : 50,//设置隐藏时的距离
			spacingh : 270,//设置显示时间距
		});
	});
	
	
	//字数限制
	function checkLength(which) {
		var maxChars = 100; //
		if (which.value.length > maxChars) {
			layer.open({
				icon : 2,
				title : '提示框',
				content : '您输入的字数超过限制!',
			});
			// 超过限制的字数了就将 文本框中的内容按规定的字数 截取
			which.value = which.value.substring(0, maxChars);
			return false;
		} else {
			var curr = maxChars - which.value.length; //250 减去 当前输入的
			document.getElementById("sy").innerHTML = curr.toString();
			return true;
		}
	};
	//初始化宽度、高度  
	$(".widget-box").height($(window).height() - 215);
	$(".table_menu_list").width($(window).width() - 260);
	$(".table_menu_list").height($(window).height() - 215);
	//当文档窗口发生改变时 触发  
	$(window).resize(function() {
		$(".widget-box").height($(window).height() - 215);
		$(".table_menu_list").width($(window).width() - 260);
		$(".table_menu_list").height($(window).height() - 215);
	})
	laydate({
		elem : '#start',
		event : 'focus'
	});
	
	
	function selectAdmin(){
		var adminName = document.getElementById("adminName").value.trim();
		var adminType = document.getElementById("adminType").value.trim();
		var reg = /^[0-9]{11}$/;
		if(!reg.test(adminType) && adminType != ""){
			layer.msg('请输入合法的电话！！!', {
				icon : 2,
				time : 1000
			});
			return ;
		}
		if(xmlhttp!=null){
			var  url;
			if(adminName == "" && adminType == ""){
				url="${path}/control.s?op=queryAdmin";
			}else{
				url="${path}/control.s?op=queryAdmin&name="+adminName+"&phone="+adminType;
			}
			xmlhttp.open("POST",url,true);
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					// 替换空格
					var msg = xmlhttp.responseText.replace(/\s/gi,"");
					if(msg == 1){
						layer.msg('暂无数据', {
							icon : 5,
							time : 1000
							});
					}
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
	
	
	/*用户-停用*/
	function member_stop(obj, id){
		layer.confirm('确认要停用吗？',function(index){
			if(xmlhttp!=null){
				// 定义请求地址
				var url ="${path}/user.s?op=updateState&ustate=2&uid="+id;
				// 以 POST 方式 开启连接
				// POST 请求 更安全（编码）  提交的数据大小没有限制
				xmlhttp.open("POST",url,true);
				// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
				// 每次的状态改变都会调用该方法
				xmlhttp.onreadystatechange=function(){
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
						// 替换空格
						var msg = xmlhttp.responseText.replace(/\s/gi,"");
						if(msg == 0){
							layer.msg('停用失败!', {
								icon : 5,
								time : 1000
								});
						}else if(msg == 2){
							layer.msg('信息填写不完整!!!', {
								icon : 2,
								time : 1000
								});
						}else{
							$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,${admin.uid })" href="javascript:;" title="启用"><i class="fa fa-close bigger-120"></i></a>');
							$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
							$(obj).remove();
							layer.msg('已停用!', {
								icon : 1,
								time : 1000
							});
						}
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
		});
	}
	/*用户-启用*/
	function member_start(obj, id) {
		layer.confirm('确认要启用吗？',function(index) {
			if(xmlhttp!=null){
				// 定义请求地址
				var url ="${path}/user.s?op=updateState&ustate=1&uid="+id;
				// 以 POST 方式 开启连接
				// POST 请求 更安全（编码）  提交的数据大小没有限制
				xmlhttp.open("POST",url,true);
				// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
				// 每次的状态改变都会调用该方法
				xmlhttp.onreadystatechange=function(){
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
						// 替换空格
						var msg = xmlhttp.responseText.replace(/\s/gi,"");
						if(msg == 0){
							layer.msg('修改失败!', {
								icon : 5,
								time : 1000
								});
						}else if(msg == 2){
							layer.msg('信息填写不完整!!!', {
								icon : 2,
								time : 1000
								});
						}else{
							$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,${admin.uid })" href="javascript:;" title="停用"><i class="fa fa-check  bigger-120"></i></a>');
							$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
							$(obj).remove();
							layer.msg('已启用!', {
								icon : 6,
								time : 1000
							});
								}
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
		});
	}
	/*产品-编辑*/
	function member_edit(title, url, id, w, h) {
		layer_show(title, url, w, h);
	}

	/*产品-删除*/
	function member_del(obj, id) {
		layer.confirm('确认要删除吗？', function(index) {
			if(xmlhttp!=null){
				// 定义请求地址
				var url ="${path}/user.s?op=delete&uid="+id;
				// 以 POST 方式 开启连接
				// POST 请求 更安全（编码）  提交的数据大小没有限制
				xmlhttp.open("POST",url,true);
				// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
				// 每次的状态改变都会调用该方法
				xmlhttp.onreadystatechange=function(){
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
						// 替换空格
						var msg = xmlhttp.responseText.replace(/\s/gi,"");
						if(msg == 0){
							layer.msg('删除失败!', {
								icon : 5,
								time : 1000
								});
						}else if(msg == 2){
							layer.msg('不能进行删除操作!!!', {
								icon : 2,
								time : 1000
								});
						}else{
							$(obj).parents("tr").remove();
							layer.msg('已删除!', {
								icon : 1,
								time : 1000
							});
						}
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
		});
	}
	/*用户-批量删除*/
	function selectDelete(){
		layer.confirm('确认要删除吗？',function(index){
			if(sbox != "/"){
				var table = $('#sample_table').dataTable();//获取表格
				var nTrs = table.fnGetNodes();//fnGetNodes获取表格所有行，nTrs[i]表示第i行tr对象
				for (var i = 0; i < nTrs.length; i++) {
					if ($(nTrs[i]).hasClass('selected')) {
						var bid = table.fnGetData(nTrs[i]);//fnGetData获取一行的数据
						 sbox =sbox +"/"+ bid[1];
					}
				}
			}else{
				layer.msg("不能进行该操作！！！", {
					icon : 2,
					time : 1000
					});
				return ;
			}
			if(sbox == ""){
				layer.msg("请选择要删除的用户！！！", {
					icon : 7,
					time : 1000
					});
				return ;
			}
			if (xmlhttp != null) {
				// 定义请求地址
				var url = "${path}/user.s?op=delete&uid="+sbox;
				// 以 POST 方式 开启连接
				// POST 请求 更安全（编码）  提交的数据大小没有限制
				xmlhttp.open("POST", url, true);
				// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
				// 每次的状态改变都会调用该方法
				xmlhttp.onreadystatechange = function() {
					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						var msg = xmlhttp.responseText.replace(/\s/gi, "");
						if(msg == 1){
							sbox = "";
							layer.msg("删除成功！！！", {
								icon : 6,
								time : 1000
								});
							window.location.href='${path}/back/admin/administrator.jsp';
						}else if(msg == 2){
							sbox = "";
							layer.msg("不能进行此操作！！！", {
								icon : 2,
								time : 1000
								});
						}else{
							sbox = "";
							layer.msg("删除失败!!!", {
								icon : 5,
								time : 1000
								});
						}
					}
				};
				// 发送请求
				xmlhttp.send(null);
			} else {
				sbox = "";
				layer.msg("不能创建XMLHttpRequest对象实例", {
					icon : 2,
					time : 1000
					});
			}
		});
	}
</script>