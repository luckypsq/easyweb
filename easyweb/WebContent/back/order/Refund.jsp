<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.easyweb.bean.OrderDetial"%>
<%@page import="com.yc.easyweb.dao.EorderDao"%>
<%@page import="com.yc.easyweb.biz.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
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
	href="<%=application.getContextPath() %>/back/font/css/font-awesome.min.css" />
<!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/ace-ie.min.css" />
		<![endif]-->
<script
	src="<%=application.getContextPath() %>/back/js/jquery-1.9.1.min.js"></script>
<script src="<%=application.getContextPath() %>/back/js/H-ui.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/bootstrap.min.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/typeahead-bs2.min.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/jquery.dataTables.min.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/jquery.dataTables.bootstrap.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/layer/layer.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/laydate/laydate.js"
	type="text/javascript"></script>

<script src="<%=application.getContextPath() %>/back/js/lrtk.js"
	type="text/javascript"></script>
<title>退款管理</title>
</head>

<body>
	<div class="margin clearfix">
		<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html;charset=utf-8");
	EorderBiz eoBiz = new EorderBiz();
	OrderDetial orderDetial = new OrderDetial();
	String eotime =null;
	String eoid = null;
	String eostate = null;
	//获取查询条件
	if(request.getParameter("eotime") != null && !request.getParameter("eotime").isEmpty()){
		 eotime =request.getParameter("eotime");	
		orderDetial.setEotime(eotime);
	}
	if(request.getParameter("eoid") != null && !request.getParameter("eoid").isEmpty()){
		eoid =request.getParameter("eoid");	
		orderDetial.setEoid(eoid);
	}
	if(request.getParameter("eostate") != null && !request.getParameter("eostate").isEmpty()){
		eostate = request.getParameter("eostate");
		orderDetial.setEostate(Integer.parseInt(eostate));
	}
	List<OrderDetial> order_show = eoBiz.selectDetail(orderDetial);
	List<OrderDetial> reSuccess = new ArrayList<OrderDetial>();//退货成功
	List<OrderDetial> reFaile = new ArrayList<OrderDetial>();//退货失败
	List<OrderDetial>  reWait = new ArrayList<OrderDetial>();//待退货
	for(OrderDetial order1 :  order_show){
		if(order1.getEostate() == 4){
			reWait.add(order1);
		}else if(order1.getEostate() == 5){
			reSuccess.add(order1);
		}else if(order1.getEostate() == 7){
			reFaile.add(order1);
		}
	}
	int orderCount = 0;
	orderCount = reFaile.size() + reWait.size() + reSuccess.size();
%>
		<div id="refund_style">
			<div class="search_style">

				<ul class="search_content clearfix">
					<li><label class="l_f">订单编号</label><input id="eoid_show"
						value="<%=eoid == null? "" :eoid %>" type="text" class="text_add"
						placeholder="输入订单编号" style="width: 250px" /></li>
					<li><label class="l_f">退款时间</label><input
						value="<%=eotime == null? "" :eotime %>"class="inline laydate-icon" id="start" style="margin-left: 10px;"></li>
					<li style="width: 90px;"><button type="button"
							class="btn_search" onClick="selectReDate()">
							<i class="fa fa-search"></i>查询
						</button></li>
				</ul>
			</div>
			<div class="border clearfix">
				<span class="l_f"> <a href="<%=application.getContextPath() %>/back/order/Refund.jsp?eostate=5"
					class="btn btn-success Order_form"><i
						class="fa fa-check-square-o"></i>&nbsp;已退款订单</a> <a
					href="<%=application.getContextPath() %>/back/order/Refund.jsp?eostate=4" class="btn btn-warning Order_form"><i
						class="fa fa-close"></i>&nbsp;未退款订单</a> <a onclick="selectOrderDelete();"
					class="btn btn-danger"><i class="fa fa-trash"></i>&nbsp;批量删除</a>
				</span> <span class="r_f">共：<b><%=orderCount %></b>笔
				</span>
			</div>
			<!--退款列表-->
			<div class="refund_list">
				<table class="table table-striped table-bordered table-hover"
					id="sample-table">
					<thead>
						<tr>
							<th width="25px"><label><input type="checkbox"
									class="ace"><span class="lbl"></span></label></th>
							<th width="120px">订单编号</th>
							<th width="250px">书籍名称</th>
							<th width="80px">交易金额</th>
							<th width="100px">交易时间</th>
							<th width="180px">配送地区</th>
							<th width="100px">联系电话</th>
							<th width="100px">买家姓名</th>
							<th width="80px">数量</th>
							<th width="70px">状态</th>
							<th width="100px">说明</th>
							<th width="200px">操作</th>
						</tr>
					</thead>
					<tbody>
					<tr>
					<%
				   		for(OrderDetial order :  order_show){
				   			String showState = null;
								String message = null;
					   		 if(order.getEostate() == 4 || order.getEostate() == 5 || order.getEostate() == 7){
						   			pageContext.setAttribute("orderReShow", order);
					   				if(order.getEostate() == 4){
					   					showState = "待退款";
					   					message = "等待退款";
					   				}else if(order.getEostate() == 5){
					   					showState = "已退款";
					   					message = "退款成功";
					   				}else if(order.getEostate() == 7){
					   					showState = "退款失败";
					   					message = "条件不符合";
					   				}
								
   						%>
								<td><label><input type="checkbox" class="ace"><span
										class="lbl"></span></label></td>
								<td>${orderReShow.getEoid() }</td>
								<td class="order_product_name"><a
									href="<%=application.getContextPath() %>/detail.jsp?bid=${orderReShow.getBid()}"
									class="product_Display">${orderReShow.getBname() }</a></td>
								<td>${orderReShow.getTotal() }</td>
								<td>${orderReShow.getEotime() }</td>
								<td>${orderReShow.getEoaddr() }</td>
								<td>${orderReShow.getUphone() }</td>
								<td>${orderReShow.getUname() }</td>
								<td>${orderReShow.getCount() }</td>
								<td class="td-status">
									<% if(order.getEostate() != 4) {%> <span
									class="label label-defaunt radius"><%=showState ==null ? "" :showState %></span>
									<%}else{ %> <span class="label label-success radius"><%=showState ==null ? "" :showState %></span>
									<% }%>
								</td>
								<td><%=message ==null ? "" :message %></td>
								<td>
								<% if(order.getEostate() == 4) {%>
										<a onClick="Delivery_Refund(this,${orderReShow.getEoid() })"
										title="退款" class="btn btn-xs btn-success">退款</a>
								<%}%> 
							   <a
								title="退款订单详细"
								href="<%=application.getContextPath() %>/back/order/Refund_detailed.jsp?eoid=${orderReShow.getEoid() }"
								class="btn btn-xs btn-info Refund_detailed">详细</a>
								 <a
								title="删除"
								href="javascript:;"
								onclick="Order_form_del(this,${orderReShow.getEoid() })"
								class="btn btn-xs btn-warning">删除</a>
								</td>
							</tr>
							<%
					   		 	}
					   		 } %>
					</tbody>
				</table>

			</div>
		</div>
	</div>
</body>
</html>
<script>
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
var sbox = "";
 //订单列表
jQuery(function($) {
		var oTable1 = $('#sample-table').dataTable( {
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,2,3,4,5,6,8,9]}// 制定列不参与排序
		] } );
                 //全选操作
				$('table th input:checkbox').on('click' , function(){
					if(sbox == "/"){
						sbox = "";
					}else{
						sbox = "/";
					}
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
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
			});
function Delivery_Refund(obj,id){
	layer.confirm('是否退款当前商品价格！',function(index){
		if(xmlhttp!=null){
			// 定义请求地址
			var url ="<%=application.getContextPath()%>/eorder.s?op=update&eostate=5&eoid="+id;
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
						$(obj).parents("tr").find(".td-manage").prepend('<a style=" display:none" class="btn btn-xs btn-success" onClick="member_stop(this,${orderReShow.getEoid() })" href="javascript:;" title="已退款">退款</a>');
						$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt  radius">退货失败</span>');
						$(obj).remove();
						layer.msg('退货失败!', {
							icon : 5,
							time : 1000
							});
					}else{
						$(obj).parents("tr").find(".td-manage").prepend('<a style=" display:none" class="btn btn-xs btn-success" onClick="member_stop(this,${orderReShow.getEoid() })" href="javascript:;" title="已退款">退款</a>');
						$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt  radius">已退款</span>');
						$(obj).remove();
						layer.msg('已退款!', {
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
/*订单-删除*/
function Order_form_del(obj, id) {
	layer.confirm('确认要删除吗？', function(index) {
		if(xmlhttp!=null){
			// 定义请求地址
			var url ="<%=application.getContextPath()%>/eorder.s?op=delete&eoid="+id;
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
/*订单查询*/
	function selectReDate(){
		  var eoid = document.getElementById("eoid_show").value.trim();
		  var eotime = document.getElementById("start").value.trim();
		  window.location.href="<%=application.getContextPath() %>/back/order/Refund.jsp?eoid="+eoid+"&eotime="+eotime;
	}
	
	
	/*订单-批量删除*/
	function selectOrderDelete(){
		layer.confirm('确认要删除吗？',function(index){
			if(sbox != "/"){
				var table = $('#sample-table').dataTable();//获取表格
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
				layer.msg("请选择要删除的订单！！！", {
					icon : 7,
					time : 1000
					});
				return ;
			}
			 if (xmlhttp != null) {
				// 定义请求地址
				var url = "<%=application.getContextPath()%>/eorder.s?op=delete&eoid="+sbox;
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
							window.location.href='<%=application.getContextPath()%>/back/order/Refund.jsp';
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
	//面包屑返回值
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.iframeAuto(index);
	$('.Refund_detailed').on('click', function() {
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
		//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
		parent.layer.close(index);

	});
</script>