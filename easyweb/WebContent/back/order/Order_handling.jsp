<%@page import="java.util.List"%>
<%@page import="com.yc.easyweb.bean.OrderDetial"%>
<%@page import="com.yc.easyweb.dao.EorderDao"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<title>订单处理</title>
</head>
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
	int success = 0;//成功的
	int numSend = 0;//待发货
	int numPay= 0;//待付款
	int num = 0;//待收货
	for(OrderDetial order_main :  order_show){
		if(order_main.getEostate() == 1){
			numPay ++;
		}else if(order_main.getEostate() == 2){
			numSend++;	
		}else if(order_main.getEostate() == 3){
			num++;
		}else if(order_main.getEostate() == 6){
			success++;		
		}
	}
%>
<body>
	<div class="clearfix">
		<div class="handling_style" id="order_hand">
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
							<h4 class="lighter smaller">订单操作</h4>
						</div>
						<div class="widget-body">
							<ul class="b_P_Sort_list">
								<li><i class="orange  fa fa-reorder"></i><a
									href="<%=application.getContextPath() %>/back/order/Order_handling.jsp">全部订单(<%=order_show.size() %>)
								</a></li>
								<li><i class="fa fa-sticky-note pink "></i> <a
									href="<%=application.getContextPath() %>/back/order/Order_handling.jsp?eostate=6">已完成(<%=success%>)</a></li>
								<li><i class="fa fa-sticky-note pink "></i> <a
									href="<%=application.getContextPath() %>/back/order/Order_handling.jsp?eostate=1">待付款(<%=numPay%>)</a>
								</li>
								<li><i class="fa fa-sticky-note pink "></i> <a
									href="<%=application.getContextPath() %>/back/order/Order_handling.jsp?eostate=2">待发货(<%=numSend%>)</a></li>
								<li><i class="fa fa-sticky-note pink "></i> <a
									href="<%=application.getContextPath() %>/back/order/Order_handling.jsp?eostate=3">待收货(<%=num%>)</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="order_list_style" id="order_list_style">
				<div class="search_style">
					<ul class="search_content clearfix">
						<li><label class="l_f">订单编号</label><input id="eoid_show"
							value="<%=eoid == null? "" :eoid %>" type="text"  class="text_add" placeholder="输入订单编号" style="width: 250px" /></li>
						<li><label class="l_f">交易时间</label><input
							class="inline laydate-icon" id="start"
							value="<%=eotime == null? "" :eotime %>" style="margin-left:10px;"></li>
						<li style="width: 90px;"><button type="button"
								class="btn_search" onClick="selectDate()">
								<i class="fa fa-search"></i>查询
							</button></li>
					</ul>
				</div>
				<!--交易订单列表-->
				<div class="Orderform_list">
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
								<th width="100px">配送方式</th>
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
		   		 if(order.getEostate() != 4 && order.getEostate() != 5 && order.getEostate() != 7){
		   			pageContext.setAttribute("orderShow", order);
	   				if(order.getEostate() == 1){
	   					showState = "待付款";
	   					message = "等待支付";
	   				}else if(order.getEostate() == 2){
	   					showState = "待发货";
	   					message = "等待发获";
	   				}else if(order.getEostate() == 3){
	   					showState = "已发货";
	   					message = "等待揽件";
	   				}else if(order.getEostate() == 5){
	   					showState = "交易失败";
	   					message = "退款成功";
	   				}else if(order.getEostate() == 6){
	   					showState = "已收货";
	   					message = "已领取包裹";
	   				}
				
   	%>
								<td><label><input type="checkbox" class="ace"><span
										class="lbl"></span></label></td>
								<td>${orderShow.getEoid() }</td>
								<td class="order_product_name"><a
									href="<%=application.getContextPath() %>/detail.jsp?bid=${orderShow.getBid()}"
									class="product_Display">${orderShow.getBname() }</a></td>
								<td>${orderShow.getTotal() }</td>
								<td>${orderShow.getEotime() }</td>
								<td>${orderShow.getEotype() }</td>
								<td>${orderShow.getEoaddr() }</td>
								<td>${orderShow.getUphone() }</td>
								<td>${orderShow.getUname() }</td>
								<td>${orderShow.getCount() }</td>
								<td class="td-status">
									<% if(order.getEostate() == 5) {%> <span
									class="label label-defaunt radius"><%=showState ==null ? "" :showState %></span>
									<%}else{ %> <span class="label label-success radius"><%=showState ==null ? "" :showState %></span>
									<% }%>
								</td>
								<td><%=message ==null ? "" :message %></td>
								<td>
									<% if(order.getEostate() == 2) {%> <a
									onClick="Delivery_stop(this,${orderShow.getEoid() })"
									title="发货" class="btn btn-xs btn-success"><i
										class="fa fa-cubes bigger-120"></i></a> <%}%> 
									<a title="订单详细"
									href="<%=application.getContextPath() %>/back/order/order_detailed.jsp?eoid=${orderShow.getEoid() }"
									class="btn btn-xs btn-info order_detailed"><i
										class="fa fa-list bigger-120"></i></a> 
										
										<a title="删除"
									href="javascript:;"
									onclick="Order_form_del(this,${orderShow.getEoid() })"
									class="btn btn-xs btn-warning"><i
										class="fa fa-trash  bigger-120"></i></a>
								</td>
							</tr>
							<%}} %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!--发货-->
	<div id="Delivery_stop" style="display: none">
		<div class="">
			<div class="content_style">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">快递公司 </label>
					<div class="col-sm-9">
						<select class="form-control" id="form-field-select-1">
							<option value="">--选择快递--</option>
							<option value="天天快递">天天快递</option>
							<option value="圆通快递">圆通快递</option>
							<option value="中通快递">中通快递</option>
							<option value="顺丰快递">顺丰快递</option>
							<option value="申通快递">申通快递</option>
							<option value="邮政EMS">邮政EMS</option>
							<option value="邮政小包">邮政小包</option>
							<option value="韵达快递">韵达快递</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1"> 快递号 </label>
					<div class="col-sm-9">
						<input type="text" id="form-field-1" placeholder="快递号"
							class="col-xs-10 col-sm-5" style="margin-left: 0px;">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">货到付款 </label>
					<div class="col-sm-9">
						<label><input name="checkbox" type="radio" class="ace"
							id="checkbox" value="货到付款"><span class="lbl"></span></label>
					</div>
				</div>
				<div class="form-group">
						<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">在线支付 </label>
					<div class="col-sm-9">
						<label><input name="checkbox" type="radio" class="ace"
							id="checkbox" value="在线支付"><span class="lbl"></span></label>
					</div>
				</div>
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
function selectDate(){
	  var eoid = document.getElementById("eoid_show").value.trim();
	  var eotime = document.getElementById("start").value.trim();
	  window.location.href="<%=application.getContextPath() %>/back/order/Order_handling.jsp?eoid="+eoid+"&eotime="+eotime;
}
$(function() { 
	$("#order_hand").fix({
		float : 'left',
		//minStatue : true,
		skin : 'green',	
		durationTime :false,
		spacingw:30,//设置隐藏时的距离
	    spacingh:250,//设置显示时间距
		table_menu:'.order_list_style',
	});
});
//时间
 laydate({
    elem: '#start',
    event: 'focus' 
});
//初始化宽度、高度  
 $(".widget-box").height($(window).height()); 
$(".order_list_style").width($(window).width()-220);
 $(".order_list_style").height($(window).height()-30);
  //当文档窗口发生改变时 触发  
    $(window).resize(function(){
	$(".widget-box").height($(window).height());
	 $(".order_list_style").width($(window).width()-234);
	  $(".order_list_style").height($(window).height()-30);
});
/**发货**/
function Delivery_stop(obj,id){
	layer.open({
        type: 1,
        title: '发货',
		maxmin: true, 
		shadeClose:false,
        area : ['500px' , ''],
        content:$('#Delivery_stop'),
		btn:['确定','取消'],
		yes: function(index, layero){		
		if($('#form-field-1').val()==""){
			layer.alert('快递号不能为空！',{
               title: '提示框',				
			  icon:0,		
			  }) 
			}else{			
			 layer.confirm('提交成功！',function(index){
				 var express = document.getElementById("form-field-select-1").value.trim();
				 var eotype =  $("input[type='radio']:checked").val();
				 if(xmlhttp!=null){
						// 定义请求地址
						var url ="<%=application.getContextPath()%>/eorder.s?op=update&eostate=3&eoid="+id+"&eopress="+express+"&type="+eotype;
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
									layer.msg('发货失败!', {
										icon : 5,
										time : 1000
										});
									 layer.close(index);  
								}else{
									$(obj).parents("tr").find(".td-manage").prepend('<a style=" display:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" href="javascript:;" title="已发货"><i class="fa fa-cubes bigger-120"></i></a>');
									$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发货</span>');
									$(obj).remove();
									layer.msg('已发货!',{icon: 6,time:1000});
								}
								 layer.close(index);  
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
		}
	})
};
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
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
						
				});
			});
</script>
