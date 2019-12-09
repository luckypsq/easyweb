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
 <link href="${path}/back/assets/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${path}/back/css/style.css"/>       
        <link href="${path}/back/assets/css/codemirror.css" rel="stylesheet">
        <link rel="stylesheet" href="${path}/back/assets/css/ace.min.css" />
        <link rel="stylesheet" href="${path}/back/font/css/font-awesome.min.css" />
		<script src="${path}/back/js/jquery-1.9.1.min.js"></script>
        <script src="${path}/back/assets/js/bootstrap.min.js"></script>
		<script src="${path}/back/assets/js/typeahead-bs2.min.js"></script> 
        <script src="${path}/back/js/H-ui.js" type="text/javascript"></script>          	
        <script src="${path}/back/assets/layer/layer.js" type="text/javascript" ></script>          
<title>退款详细</title>
</head>

<body onload="show()">
<div class="margin clearfix">
 <div class="Refund_detailed">
    <div class="Numbering">退款单编号:<b>${orderShow.eoid }</b></div>
    <div class="detailed_style">
     <!--退款信息-->
     <div class="Receiver_style">
     <div class="title_name">退款信息</div>
     <div class="Info_style clearfix">
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款人姓名： </label>
         <div class="o_content">${orderShow.uname }</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款人电话： </label>
         <div class="o_content">${orderShow.uphone }</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款方式：</label>
         <div class="o_content">银联</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款数量：</label>
         <div class="o_content">${orderShow.count }件</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 快递名称：</label>
         <div class="o_content">${orderShow.eoespress }</div>
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
         <div class="o_content">${orderShow.total }元</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 退款日期：</label>
         <div class="o_content">${orderShow.eootime }</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 状态：</label>
         <div class="o_content">${eoderState[orderShow.eostate]}</div>
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
      <a href="${path}/detail.jsp?bid=${orderShow.bid }" class="img_link"><img src="${orderShow.bimg }"></a>
      <span>
      <a href="${path}/detail.jsp?bid=${orderShow.bid}" class="name_link">${orderShow.bname }</a>
      <br>
      <p>数量：${orderShow.getCount() }本</p> 
      <p class="status">${eoderState[orderShow.eostate]}</p>   
      </span>
      </div>
    </div>
    </div>
    </div>
    <div class="Button_operation">
				<button onclick="window.location.href='${path}/back/order/Refund.jsp'" class="btn btn-primary radius" type="button"><i class="icon-save "></i>返回上一步</button>
			<!-- 	<button onclick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
		 -->	</div>
 </div>
</div>
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
function show(){
	var eoid = "${param.eoid}";
	if (xmlhttp != null) {
		// 定义请求地址
		var url = "${path}/eorder.s?op=querySingle&eoid="+eoid;
		// 以 POST 方式 开启连接
		// POST 请求 更安全（编码）  提交的数据大小没有限制
		xmlhttp.open("POST", url, true);
		// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
		// 每次的状态改变都会调用该方法
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				/* var msg = xmlhttp.responseText.replace(/\s/gi, "");
				if(msg == 0){
					alert("暂无数据");
				} */
			}
		};
		// 发送请求
		xmlhttp.send(null);
	} else {
		alert("不能创建XMLHttpRequest对象实例")
	}
}
</script>
</body>
</html>
