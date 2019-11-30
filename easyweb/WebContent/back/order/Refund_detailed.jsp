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
 <link href="<%=application.getContextPath() %>/back/assets/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/css/style.css"/>       
        <link href="<%=application.getContextPath() %>/back/assets/css/codemirror.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/ace.min.css" />
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/font/css/font-awesome.min.css" />
        <!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/ace-ie.min.css" />
		<![endif]-->
		<script src="<%=application.getContextPath() %>/back/js/jquery-1.9.1.min.js"></script>
        <script src="<%=application.getContextPath() %>/back/assets/js/bootstrap.min.js"></script>
		<script src="<%=application.getContextPath() %>/back/assets/js/typeahead-bs2.min.js"></script> 
        <script src="<%=application.getContextPath() %>/back/js/H-ui.js" type="text/javascript"></script>          	
        <script src="<%=application.getContextPath() %>/back/assets/layer/layer.js" type="text/javascript" ></script>          
<title>退款详细</title>
</head>

<body>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	EorderBiz eorderBiz = new EorderBiz();
	OrderDetial reorder_Detial = new OrderDetial();
	//获取查询条件
	if(request.getParameter("eoid") != null && !request.getParameter("eoid").isEmpty()){
		reorder_Detial.setEoid(request.getParameter("eoid"));
	}
	List<OrderDetial> reorderShow = eorderBiz.selectDetail(reorder_Detial);
	String type = null;
	if(reorderShow.get(0).getEostate() == 4){
		type = "待退款";
	}else if(reorderShow.get(0).getEostate() == 5){
		type = "已退款";
	}else if(reorderShow.get(0).getEostate() == 7){
		type = "退款失败";
	}
	pageContext.setAttribute("reordershow",reorderShow.get(0) );
%>

<div class="margin clearfix">
 <div class="Refund_detailed">
    <div class="Numbering">退款单编号:<b>${reordershow.getEoid() }</b></div>
    <div class="detailed_style">
     <!--退款信息-->
     <div class="Receiver_style">
     <div class="title_name">退款信息</div>
     <div class="Info_style clearfix">
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款人姓名： </label>
         <div class="o_content">${reordershow.getUname() }</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款人电话： </label>
         <div class="o_content">${reordershow.getUphone() }</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款方式：</label>
         <div class="o_content">银联</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款数量：</label>
         <div class="o_content">${reordershow.getCount() }件</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 快递名称：</label>
         <div class="o_content">${reordershow.getEoespress() }</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 快递单号：</label>
         <div class="o_content">3456789090</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款账户：</label>
         <div class="o_content">招商储蓄卡</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款账号：</label>
         <div class="o_content">345678*****5678</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款金额：</label>
         <div class="o_content">${reordershow.getTotal() }元</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款日期：</label>
         <div class="o_content">${reordershow.getEotime() }</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 状态：</label>
         <div class="o_content"><%=type == null ? "" :type %></div>
        </div>
     </div>
    </div>
    <div class="Receiver_style">
    <div class="title_name">退款说明</div>
    <div class="reund_content">
      买家收到货,需要退货,如何退货呢--淘宝退款流程交易订单的交易状态是卖家已发货,有可能是因为产品问题或者其他原因需要退...  
    </div>
    </div>
    
    <!--产品信息-->
    <div class="product_style">
    <div class="title_name">产品信息</div>
    <div class="Info_style clearfix">
      <div class="product_info clearfix">
      <a href="<%=application.getContextPath() %>/detail.jsp?bid=${reordershow.getBid() }" class="img_link"><img src="${reordershow.getBimg() }"></a>
      <span>
      <a href="<%=application.getContextPath() %>/detail.jsp?bid=${reordershow.getBid() }" class="name_link">${reordershow.getBname() }</a>
      <!-- <b>也称为姬娜果，饱满色艳，个头小</b>
      <p>编号：HY54567</p>
      <p>规格：500g/斤</p>
      <p>价格：<b class="price"><i>￥</i>56</b></p>  -->
      <br>
      <p>数量：${reordershow.getCount() }本</p> 
      <p class="status"><%=type == null ? "" :type %></p>   
      </span>
      </div>
    </div>
    </div>
    </div>
    <div class="Button_operation">
				<button onclick="window.location.href='<%=application.getContextPath() %>/back/order/Refund.jsp'" class="btn btn-primary radius" type="button"><i class="icon-save "></i>返回上一步</button>
			<!-- 	<button onclick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
		 -->	</div>
 </div>
</div>
</body>
</html>
