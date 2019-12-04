<%@page import="com.yc.easyweb.dao.lyw.CartDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
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
	href="<%=application.getContextPath()%>/back/Widget/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/back/assets/css/font-awesome.min.css" />
<link
	href="<%=application.getContextPath()%>/back/Widget/icheck/icheck.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/back/Widget/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath()%>/back/css/button.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=application.getContextPath()%>/back/assets/layer/layer.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/back/assets/laydate/laydate.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/back/js/jquery-1.9.1.min.js"></script>
<script
	src="<%=application.getContextPath()%>/back/assets/js/bootstrap.min.js"></script>
<script
	src="<%=application.getContextPath()%>/back/assets/js/typeahead-bs2.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/Widget/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/Widget/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/Widget/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/Widget/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/Widget/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/Widget/ueditor/1.4.3/ueditor.all.min.js">
	
</script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/Widget/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script src="<%=application.getContextPath()%>/back/js/lrtk.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/js/H-ui.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/back/js/H-ui.admin.js"></script>
<style type="text/css">
</style>
<title>填写订单</title>
</head>
<body>
	<div class="page_right_style">
		<div class="type_title">填写订单</div>
		<%
					request.setCharacterEncoding("utf-8");
					response.setContentType("text/html;charset=utf-8");
					 //获取提示信息
					int notice = -1;
					 String bid = null;
					 String itemid = null;
					 BookBiz bookBiz = new BookBiz();
					 EorderitemBiz eoBiz1 = new EorderitemBiz();
					 Book book = null;
					 Map<String,Object> map = null;
					if(request.getParameter("msg")!=null && !request.getParameter("msg").toString().isEmpty()){
						notice = Integer.parseInt(request.getParameter("msg").toString());
					} 
					if(request.getParameter("bid")!=null && !request.getParameter("bid").toString().isEmpty()){
						bid = request.getParameter("bid");
						Book book2 =  new Book();
						book2.setBid(Long.parseLong(bid));
						book = bookBiz.selectSingle(book2);
					} 
					if(request.getParameter("itemid")!=null && !request.getParameter("itemid").toString().isEmpty()){
						itemid = request.getParameter("itemid");
						CartDao dao = new CartDao();
						List<Object> params = new ArrayList<Object>();
						params.add(itemid);
						map = dao.selectById(params);
					} 
					String bname = null;
					String img = null;
					double price = 0;
					double total = 0;
					if(book != null){
						if(book.getBname() != null){
							bname = book.getBname();
						}
						if(book.getBimg() != null){
							img = book.getBimg();
						}
						if(book.getBprice() != 0){
							price = book.getBprice();
							total = book.getBprice();
						}
					}else if(map != null){
						if(map.get("bname") != null){
							bname = map.get("bname").toString();
						}
						if(map.get("bimg") != null){
							img = map.get("bimg").toString();
						}
						if(map.get("total")!= null){
							total = Double.parseDouble(map.get("total").toString()) ;
						}
						if(map.get("count")!= null){
							price = Double.parseDouble(map.get("count").toString()) ;
						}
					}
				%>
		<form action="<%=application.getContextPath()%>/eorder.s?op=add" method="post" class="form form-horizontal"
			id="form-article-add">
			<div class="clearfix cl">
				<label class="form-label col-2"><span class="c-red">*</span>地&nbsp;&nbsp;&nbsp;&nbsp;址：</label>
				<div class="formControls col-10">
					<input type="text" class="input-text"
						placeholder="请输入地址" id="eoaddr" name="eoaddr" value=""
						style="margin-left: 40px;">
				</div>
			</div>
			
			<div class=" clearfix cl">
				<div class="clearfix cl" id ="showleft">
					<img id="imghead" name="imghead"border=0 src="<%=img == null ? "":img %>" 
										style="width:300px;height:200px; margin-left:100px;"/>
				</div>
				<div id ="showRight">
					<div class="Add_p_s">
						<label class="form-label col-2"><span class="c-red">*</span>收&nbsp;件&nbsp;人:</label>
						<div class="formControls col-2">
							<input type="text" class="input-text"
								placeholder="请输入汉字或字母" id="uname" name="uname" value="">
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2"><span class="c-red">*</span>电&nbsp;&nbsp;&nbsp;&nbsp;话:</label>
						<div class="formControls col-2">
							<input type="text" class="input-text"
								placeholder="请输入11位数字" id="uphone" name="uphone" value="">
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">数&nbsp;&nbsp;&nbsp;&nbsp;量:</label>
						<div class="formControls col-2">
							<input type="number" class="input-text"
								id="count" name="count" value="" onchange="updatePrice();">本
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
						<%
							PayTypeBiz payTypeBiz = new PayTypeBiz();
							PayType payType = new PayType();
							List<PayType> payTypeList = payTypeBiz.selectAll(payType);
						%>
							<span class="select-box"> <select class="select"
							id="payOption" name="payOption">
								<option>请选择</option>
								<%
									if(payTypeList.size() != 0){
										for(PayType payType2: payTypeList){
								%>
									<option value="<%=payType2.getEopaytypeid()%>"><%=payType2.getEopayname()%></option>
								<%}}else{ %>
									<option value="1">支付宝</option>
									<option value="2">线下支付</option>
								<%} %>
						</select>
						</span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">书&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
						<div class="formControls col-2">
							<span class="input-text" id="bname" ><%=bname == null ? "":bname %></span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">价&nbsp;&nbsp;&nbsp;&nbsp;格:</label>
						<div class="formControls col-2">
							<span class="input-text" id="bprice" ><%=price == 0 ? "":price %></span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">总&nbsp;&nbsp;&nbsp;&nbsp;价:</label>
						<div class="formControls col-2">
							<input class="input-text" id="total" name ="total"readonly="readonly" value="<%=total == 0 ? "":total %>">
						</div>
					</div>
					<div style="display:none;">
						<input type="text" id="notice" name="notice" value="<%=notice %>" >
					</div>
					<div style="display:none;">
						<input type="text" id="bid" name="bid" value="<%=bid == null ?"":bid %>" >
					</div>
					<div style="display:none;">
						<input type="text" id="itemid" name="itemid" value="<%=itemid== null ?"":itemid %>" >
					</div>
				</div>
			</div>
			<div >
						<span id="tishi"
							style="margin-left: 80px; font-size: 18px; width: 300px;"></span>
					</div>
			<div class="clearfix cl">
				<div class="Button_operation">
					<input class="btn btn-primary radius" type="submit"
						id="btn btn-primary radius" value="提交">
					<button onClick="window.location.href='<%=application.getContextPath() %>/lhoption/index.jsp';" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
<script type="text/javascript">
//监听input框的变化
window.onload = function()
{
	 var msg =  document.getElementById("notice").value;
	 if (msg == 0) {
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

//监听input框的变化
window.onload = function()
{
	 var msg =  document.getElementById("notice").value;
	 if (msg == 0) {
		alert("添加失败！！！");
     }else if(msg == 1){
    	 alert("添加成功！！！");
	}else if(msg == 2){
		alert("信息填写错误！！！");
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
function updatePrice(){
	var price =  document.getElementById("price").value;
	var count =  document.getElementById("count").value;
	document.getElementById("total").innerText = price * count;
}

</script>
</body>
</html>