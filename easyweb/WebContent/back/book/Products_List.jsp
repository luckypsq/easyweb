<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link
	href="<%=application.getContextPath()%>/back/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/back/css/style.css" />
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/back/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/back/assets/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/back/Widget/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link
	href="<%=application.getContextPath()%>/back/Widget/icheck/icheck.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=application.getContextPath()%>/back/js/jquery-1.9.1.min.js"></script>
<script
	src="<%=application.getContextPath()%>/back/assets/js/bootstrap.min.js"></script>
<script
	src="<%=application.getContextPath()%>/back/assets/js/typeahead-bs2.min.js"></script>
<script
	src="<%=application.getContextPath()%>/back/assets/js/jquery.dataTables.min.js"></script>
<script
	src="<%=application.getContextPath()%>/back/assets/js/jquery.dataTables.bootstrap.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/js/H-ui.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/js/H-ui.admin.js"></script>
<script
	src="<%=application.getContextPath()%>/back/assets/layer/layer.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/back/assets/laydate/laydate.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="<%=application.getContextPath()%>/back/js/lrtk.js"
	type="text/javascript"></script>
<title>书籍列表</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bauthor =null;
		String btime =null;
		String btid = null;
		BookBiz bookBiz = new BookBiz();
		Book book = new Book();
		if(request.getParameter("bname") != null && !request.getParameter("bname").isEmpty()){
			bauthor =request.getParameter("bname");	
			book.setBauthor(bauthor);
		}
		if(request.getParameter("btime") != null && !request.getParameter("btime").isEmpty() ){
			btime =request.getParameter("btime");
			book.setBdate(btime);
		}
		if (request.getParameter("btid") != null && !request.getParameter("btid").isEmpty()) {
			btid = request.getParameter("btid");
			book.setBtid(Long.parseLong(request.getParameter("btid")));
		}
		//查询类别
		BookType bookType = new BookType();
		bookType.setBtstate(1);
		BookTypeBiz btBiz = new BookTypeBiz();
		List<BookType> btList = btBiz.selectAll(bookType);
		Map<Long,String> btType = new HashMap<Long,String>();
		for(BookType bt : btList){
			if(bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()){
				btType.put(bt.getBtid(),bt.getBtnamethird());
				
			}else if(bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()){
				btType.put(bt.getBtid(),bt.getBtnamesecond());
			}else{
				btType.put(bt.getBtid(),bt.getBtname());
			}
		}
		List<Book> bookList = bookBiz.selectAll(book);
	%>
	<div class=" page-content clearfix">
		<div id="products_style">
			<div class="search_style">
				<ul class="search_content clearfix">
					<li><label class="l_f">作者</label><input id ="queryName" name="" type="text"
						class="text_add" placeholder="输入作者" value="<%=bauthor == null ? "":bauthor %>" style="width: 250px"  /></li>
					<li><label class="l_f">添加时间</label><input
						class="inline laydate-icon" id="start" value="<%=btime == null ? "":btime %>" style="margin-left: 10px;"></li>
					<li style="width: 90px;"><button type="button"
							class="btn_search" onclick="query();">
							<i class="icon-search" ></i>查询
						</button></li>
				</ul>
			</div>
			<div class="border clearfix">
				<span class="l_f"> <a
					href="<%=application.getContextPath()%>/back/book/picture-add.jsp"
					title="添加书籍" class="btn btn-warning Order_form"><i
						class="icon-plus"></i>添加书籍</a> <a onclick="selectDelete();"
					class="btn btn-danger"><i class="icon-trash"></i>批量删除</a>
				</span> <span class="r_f">共：<b>${bookList.size() }</b>本书
				</span>
			</div>
			<!--书籍列表展示-->
			<div class="h_products_list clearfix" id="products_list">
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
								<h4 class="lighter smaller" onclick="book()">书籍类型列表</h4>
							</div>
							<div class="widget-body">
								<div class="widget-main padding-8">
									<div id="treeDemo" class="ztree"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="table_menu_list" id="testIframe">
					<table class="table table-striped table-bordered table-hover"
						id="sample-table">
						<thead>
							<tr>
								<th width="25px"><label><input type="checkbox"
										class="ace"><span class="lbl"></span></label></th>
								<th width="80px">书籍编号</th>
								<th width="250px">书名</th>
								<th width="100px">价格</th>
								<th width="100px">所属类别</th>
								<th width="100px">所属系列</th>
								<th width="100px">作者</th>
								<th width="180px">库存</th>
								<th width="80px">审核状态</th>
								<th width="70px">状态</th>
								<th width="200px">操作</th>
							</tr>
						</thead>
						<tbody >
							<%
								String checkState = null;
								String state = null;
								String type= null;
								for (Book bookShow : bookList) {
									pageContext.setAttribute("bookShow", bookShow);
									if (bookShow.getBstate() == 3) {
										state = "售罄";
										checkState = "通过";
									} else if (bookShow.getBstate() == 2) {
										state = "已下架";
										checkState = "通过";
									} else if (bookShow.getBstate() == 1) {
										state = "已上架";
										checkState = "通过";
									} else if (bookShow.getBstate() == 5) {
										checkState = "未审核";
									} else if (bookShow.getBstate() == 4) {
										checkState = "审核不通过";
									}
									type = btType.get(bookShow.getBtid());
							%>
							<tr>
								<td width="25px"><label><input type="checkbox"
										class="ace"><span class="lbl"></span></label></td>
								<td width="80px">${bookShow.bid }</td>
								<td width="250px"><u style="cursor: pointer"
									class="text-primary" onclick="">${bookShow.bname }</u></td>
								<td width="100px">${bookShow.bprice }</td>
								<td width="100px"><%=type == null? "":type %></td>
								<td width="100px">${bookShow.btemp == null ? "":bookShow.btemp }</td>
								<td width="100px">${bookShow.bauthor }</td>
								<td width="180px">${bookShow.bnum}</td>
								<td class="text-l"><%=checkState == null ? "" : checkState%></td>
								<td class="td-status">
									<% if(state.equals("已上架")) {%>
									<span class="label label-success radius"><%=state%></span>
									<%}else{ %>
									<span class="label label-defaunt radius"><%=state == null ? "已下架":state%></span>
									<% }%>
								</td>
								<td class="td-manage">
								<% if(state.equals("已上架")) {%>
										<a onClick="member_stop(this,${bookShow.bid })" 
										title="下架" class="btn btn-xs btn-success">
										<i class="icon-ok bigger-120"></i></a> 
									<%}else{ %>
										<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,${bookShow.bid })" href="javascript:;" title="上架">
										<i class="icon-ok bigger-120"></i></a>
									<% }%>
									<a title="编辑"
									onclick="member_edit('编辑','<%=application.getContextPath()%>/back/book/bookEdit.jsp?bid=${bookShow.bid }','','300')"
									 class="btn btn-xs btn-info"><i
										class="icon-edit bigger-120"></i></a> <a title="删除"
									href="javascript:;" onclick="member_del(this,${bookShow.bid })"
									class="btn btn-xs btn-warning"><i
										class="icon-trash  bigger-120"></i></a></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


<script type="text/javascript">
//全选
var sbox = -1;
	//操作table表格
	jQuery(function($) {
		var oTable1 = $('#sample-table').dataTable({
			"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
			"bStateSave" : true,//状态保存
			"aoColumnDefs" : [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{
				"orderable" : false,
				"aTargets" : [ 0, 2, 3, 4, 5, 8, 9 ]
			} // 制定列不参与排序
			]
		});
		//设置全选
		$('table th input:checkbox').on(
				'click',
				function() {
					sbox=1;
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox').each(
							function() {
								this.checked = that.checked;
								$(this).closest('tr').toggleClass('selected');
							});
				});
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
	laydate({
		elem : '#start',
		event : 'focus'
	});
	$(function() {
		$("#products_style").fix({
			float : 'left',
			//minStatue : true,
			skin : 'green',
			durationTime : false,
			spacingw : 30,//设置隐藏时的距离
			spacingh : 260,//设置显示时间距
		});
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
function selectDelete(){
	if(sbox != 1){
		var table = $('#sample-table').dataTable();//获取表格
		var nTrs = table.fnGetNodes();//fnGetNodes获取表格所有行，nTrs[i]表示第i行tr对象
		for (var i = 0; i < nTrs.length; i++) {
			if ($(nTrs[i]).hasClass('selected')) {
				var bid = table.fnGetData(nTrs[i]);//fnGetData获取一行的数据
				 sbox =sbox +"/"+ bid[1];
			}
		}
	}
	if(sbox == -1){
		layer.msg("请选择要删除的书籍！！！", {
			icon : 7,
			time : 1000
			});
	}
	if (xmlhttp != null) {
		// 定义请求地址
		var url = "<%=application.getContextPath()%>/book.s?op=delete&bid="+sbox;
		// 以 POST 方式 开启连接
		// POST 请求 更安全（编码）  提交的数据大小没有限制
		xmlhttp.open("POST", url, true);
		// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
		// 每次的状态改变都会调用该方法
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var msg = xmlhttp.responseText.replace(/\s/gi, "");
				if(msg == "删除成功！"){
					layer.msg(msg, {
						icon : 6,
						time : 1000
						});
					window.location.href='<%=application.getContextPath()%>/back/book/Products_List.jsp';
					
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
		
}
</script>
<script type="text/javascript">
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

	/*******树状图*******/
	var bookType = null;
	var setting = {
		view : {
			dblClickExpand : false,
			showLine : false,
			selectedMulti : false
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : ""
			}
		},
		callback : {
			//点击事件
			beforeClick : function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					window.location.href="<%=application.getContextPath()%>/back/book/Products_List.jsp?btid="+treeNode.btid;
					return true;
				}
			}
		}
	};

	var zNodes = [ {
		id : 1,
		pId : 0,
		name : "书籍分类列表",
		btid : 0,
		open : true
	}, {
		id : 11,
		pId : 1,
		name : "教材区",
		btid : 1
	}, {
		id : 111,
		pId : 11,
		name : "成功励志",
		btid : 4
	}, {
		id : 112,
		pId : 11,
		name : "法律",
		btid : 5
	}, {
		id : 113,
		pId : 11,
		name : "管理",
		btid : 6
	}, {
		id : 114,
		pId : 11,
		name : "计算机与网络",
		btid : 7
	}, {
		id : 115,
		pId : 11,
		name : "教育考试",
		btid : 8
	}, {
		id : 116,
		pId : 11,
		name : "科技工程",
		btid : 9
	}, {
		id : 117,
		pId : 11,
		name : "生活时尚",
		btid : 10
	}, {
		id : 118,
		pId : 11,
		name : "文化历史",
		btid : 11
	}, {
		id : 12,
		pId : 1,
		name : "工具书区",
		btid : 2
	}, {
		id : 121,
		pId : 12,
		name : "英语四六级 ",
		btid : 12
	}, {
		id : 122,
		pId : 12,
		name : "公务员资料 ",
		btid : 13
	}, {
		id : 123,
		pId : 12,
		name : "考研资料 ",
		btid : 14
	}, {
		id : 124,
		pId : 12,
		name : "雅思托福 ",
		btid : 15
	}, {
		id : 125,
		pId : 12,
		name : "其他 ",
		btid : 16
	}, {
		id : 13,
		pId : 1,
		name : "分享区",
		btid : 3
	}, {
		id : 131,
		pId : 13,
		name : "分享区",
		btid : 3
	} ];
	var code;

	function showCode(str) {
		if (!code)
			code = $("#code");
		code.empty();
		code.append("<li>" + str + "</li>");
	}
	$(document).ready(function() {
		var t = $("#treeDemo");
		t = $.fn.zTree.init(t, setting, zNodes);
		demoIframe = $("#testIframe");
		demoIframe.bind("load", loadReady);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.selectNode(zTree.getNodeByParam("id", '11'));
	});
	function query(){
		var bname = $("#queryName").val().replace(/\ +/g,"");
		var booktime = $("#start").val().replace(/\ +/g,"");
		window.location.href="<%=application.getContextPath()%>/back/book/Products_List.jsp?bname="+bname+"&btime="+booktime;
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
	/*产品-下架*/
	function member_stop(obj, id) {
		var soldBook = null;
		layer.confirm(
			'确认要下架吗？',
			function(index) {
				if(xmlhttp!=null){
					// 定义请求地址
					var url ="<%=application.getContextPath()%>/book.s?op=update&bstate=2&bid="+id;
					// 以 POST 方式 开启连接
					// POST 请求 更安全（编码）  提交的数据大小没有限制
					xmlhttp.open("POST",url,true);
					// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
					// 每次的状态改变都会调用该方法
					xmlhttp.onreadystatechange=function(){
						if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
							// 替换空格
							var msg = xmlhttp.responseText.replace(/\s/gi,"");
							if(msg == '0'){
								layer.msg('修改失败!', {
									icon : 5,
									time : 1000
									});
							}else if(msg == '-1'){
								layer.msg('该条数据不能修改!', {
									icon : 2,
									time : 1000
									});
							}else{
								$(obj)
								.parents("tr")
								.find(".td-manage")
								.prepend(
								'<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,${bookShow.bid })" href="javascript:;" title="上架"><i class="icon-ok bigger-120"></i></a>');
								$(obj)
								.parents("tr")
								.find(".td-status")
								.html(
								'<span class="label label-defaunt radius">已下架</span>');
								$(obj).remove();
								layer.msg('已下架!', {
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

	/*产品-上架*/
	function member_start(obj, id) {
		layer.confirm('确认要上架吗？',function(index) {
			if(xmlhttp!=null){
				// 定义请求地址
				var url ="<%=application.getContextPath()%>/book.s?op=update&bstate=1&bid="+id;
				// 以 POST 方式 开启连接
				// POST 请求 更安全（编码）  提交的数据大小没有限制
				xmlhttp.open("POST",url,true);
				// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
				// 每次的状态改变都会调用该方法
				xmlhttp.onreadystatechange=function(){
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
						// 替换空格
						var msg = xmlhttp.responseText.replace(/\s/gi,"");
						if(msg == '0'){
							layer.msg('修改失败!', {
								icon : 5,
								time : 1000
								});
						} else if(msg == '-1'){
							layer.msg('该条数据不能修改!', {
								icon : 2,
								time : 1000
								});
						}else{
							$(obj)
							.parents("tr")
							.find(".td-manage")
							.prepend(
									'<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,${bookShow.bid })" javascript:;" title="下架"><i class="icon-ok bigger-120"></i></a>');
							$(obj)
									.parents("tr")
									.find(".td-status")
									.html(
											'<span class="label label-success radius">已上架</span>');
							$(obj).remove();
							layer.msg('已上架!', {
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
	/*产品-编辑*/
	function member_edit(title, url, w, h) {
		layer_show(title, url, w, h);
	}

	/*产品-删除*/
	function member_del(obj, id) {
		layer.confirm('确认要删除吗？', function(index) {
			if(xmlhttp!=null){
				// 定义请求地址
				var url ="<%=application.getContextPath()%>/book.s?op=delete&bid="+id;
				// 以 POST 方式 开启连接
				// POST 请求 更安全（编码）  提交的数据大小没有限制
				xmlhttp.open("POST",url,true);
				// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
				// 每次的状态改变都会调用该方法
				xmlhttp.onreadystatechange=function(){
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
						// 替换空格
						var msg = xmlhttp.responseText.replace(/\s/gi,"");
						if(msg == '0'){
							layer.msg('删除失败!', {
								icon : 5,
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
					icon :2,
					time : 1000
					});
			} 
		});
	}
	//面包屑返回值
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.iframeAuto(index);
	$('.Order_form').on('click', function() {
		var cname = $(this).attr("title");
		var chref = $(this).attr("href");
		var cnames = parent.$('.Current_page').html();
		var herf = parent.$("#iframe").attr("src");
		parent.$('#parentIframe').html(cname);
		parent.$('#iframe').attr("src", chref).ready();
		;
		parent.$('#parentIframe').css("display", "inline-block");
		parent.$('.Current_page').attr({
			"name" : herf,
			"href" : "javascript:void(0)"
		}).css({
			"color" : "#4c8fbd",
			"cursor" : "pointer"
		});
		parent.layer.close(index);

	});
</script>