<%@page import="com.yc.easyweb.common.DbHelper"%>
<%@page import="com.yc.easyweb.util.PageBook"%>
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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" /> 
        <link href="<%=application.getContextPath() %>/back/assets/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/css/style.css"/>       
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/ace.min.css" />
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/font-awesome.min.css" />
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/Widget/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
        <link href="<%=application.getContextPath() %>/back/Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />   
		<!--[if IE 7]>
		  <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
        <!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/ace-ie.min.css" />
		<![endif]-->
	    <script src="<%=application.getContextPath() %>/back/js/jquery-1.9.1.min.js"></script>   
        <script src="<%=application.getContextPath() %>/back/assets/js/bootstrap.min.js"></script>
        <script src="<%=application.getContextPath() %>/back/assets/js/typeahead-bs2.min.js"></script>
		<!-- page specific plugin scripts -->
		<script src="<%=application.getContextPath() %>/back/assets/js/jquery.dataTables.min.js"></script>
		<script src="<%=application.getContextPath() %>/back/assets/js/jquery.dataTables.bootstrap.js"></script>
        <script type="text/javascript" src="<%=application.getContextPath() %>/back/js/H-ui.js"></script> 
        <script type="text/javascript" src="<%=application.getContextPath() %>/back/js/H-ui.admin.js"></script> 
        <script src="<%=application.getContextPath() %>/back/assets/layer/layer.js" type="text/javascript" ></script>
        <script src="<%=application.getContextPath() %>/back/assets/laydate/laydate.js" type="text/javascript"></script>
        <script type="text/javascript" src="<%=application.getContextPath() %>/back/Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script> 
        <script src="<%=application.getContextPath() %>/back/js/lrtk.js" type="text/javascript" ></script>
<script type="text/javascript">
	//查询
	function select(){
		
	}

</script>


<title>产品列表</title>
</head>
<body>
<%
	BookBiz bookBiz = new BookBiz();
	Book book = new Book();
	book.setBstate(1);
	List<Book> bookList = bookBiz.selectAll(book);
	pageContext.setAttribute("bookList", bookList);
%>
<div class=" page-content clearfix">
 <div id="products_style">
    <div class="search_style">
      <ul class="search_content clearfix">
       <li><label class="l_f">书名</label><input name="" type="text"  class="text_add" placeholder="输入书名"  style=" width:250px"/></li>
       <li><label class="l_f">添加时间</label><input class="inline laydate-icon" id="start" style=" margin-left:10px;"></li>
       <li style="width:90px;"><button type="button" class="btn_search"><i class="icon-search" onclick="select()"></i>查询</button></li>
      </ul>
    </div>
     <div class="border clearfix">
       <span class="l_f">
        <a href="<%=application.getContextPath() %>/back/picture-add.html" title="添加书籍" class="btn btn-warning Order_form"><i class="icon-plus"></i>添加书籍</a>
        <a href="<%=application.getContextPath() %>/back/javascript:ovid()" class="btn btn-danger"><i class="icon-trash"></i>批量删除</a>
       </span>
       <span class="r_f">共：<b>${bookList.size() }</b>本书</span>
     </div>
     <!--书籍列表展示-->
     <div class="h_products_list clearfix" id="products_list">
       <div id="scrollsidebar" class="left_Treeview">
        <div class="show_btn" id="rightArrow"><span></span></div>
        <div class="widget-box side_content" >
         <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
         <div class="side_list"><div class="widget-header header-color-green2"><h4 class="lighter smaller">书籍类型列表</h4></div>
         <div class="widget-body">
          <div class="widget-main padding-8"><div id="treeDemo" class="ztree"></div></div>
        </div>
       </div>
      </div>  
     </div>
         <div class="table_menu_list" id="testIframe">
       <table class="table table-striped table-bordered table-hover" id="sample-table">
		<thead>
		 <tr>
				<th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
				<th width="80px">书籍编号</th>
				<th width="250px">书名</th>
				<th width="100px">价格</th>
				<th width="100px">所属系列</th>
                <th width="100px">作者</th>				
				<th width="180px">库存</th>
                <th width="80px">审核状态</th>
				<th width="70px">状态</th>                
				<th width="200px">操作</th>
			</tr>
		</thead>
	<tbody>
	<%
		String checkState = null;
		String state = null;
		for(Book bookShow : bookList){
			pageContext.setAttribute("bookShow",bookShow);
			if(bookShow.getBstate() == 4){
				checkState = "通过";
			}else if(bookShow.getBstate() ==3 ){
				state = "售罄";
			}else if(bookShow.getBstate() ==2 ){
				state = "已下架";
			}else if(bookShow.getBstate() ==1 ){
				state = "已上架";
			}else if(bookShow.getBstate() ==5 ){
				checkState = "未审核";
			}else{
				checkState = "不通过";
			}
	%>
     <tr>
        <td width="25px"><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
        <td width="80px">${bookShow.bid }</td>               
        <td width="250px"><u style="cursor:pointer" class="text-primary" onclick="">${bookShow.bname }</u></td>
        <td width="100px">${bookShow.bprice }</td>
        <td width="100px">${bookShow.btemp == null ? "":bookShow.btemp }</td> 
        <td width="100px">${bookShow.bauthor }</td>         
        <td width="180px">${bookShow.bnum}</td>
        <td class="text-l"><%=checkState == null ? "":checkState%></td>
        <td class="td-status"><span class="label label-success radius"><%=state == null ? "":state%></span></td>
        <td class="td-manage">
        <a onClick="member_stop(this,'10001')"  href="javascript:;" title="下架"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a> 
        <a title="编辑" onclick="member_edit('编辑','<%=application.getContextPath() %>/back/book/picture-add.jsp','4','','510')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a> 
        <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
       </td>
	  </tr>
	  <%} %>
    </tbody>
    </table>
    </div>     
  </div>
 </div>
</div>
</body>
</html>


<script>
jQuery(function($) {
		var oTable1 = $('#sample-table').dataTable( {
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,2,3,4,5,8,9]}// 制定列不参与排序
		] } );
				
				
				$('table th input:checkbox').on('click' , function(){
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
						
				});
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			});
 laydate({
    elem: '#start',
    event: 'focus' 
});
$(function() { 
	$("#products_style").fix({
		float : 'left',
		//minStatue : true,
		skin : 'green',	
		durationTime :false,
		spacingw:30,//设置隐藏时的距离
	    spacingh:260,//设置显示时间距
	});
});
<%
	BookType bookType = new BookType();
	BookTypeBiz bookTypeBiz = new BookTypeBiz();
	List<BookType> bookTypes = bookTypeBiz.selectAll(bookType);
%>
</script>
<script type="text/javascript">
var xmlhttp;
// ajax 验证用户名是否存在
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
//初始化宽度、高度  
 $(".widget-box").height($(window).height()-215); 
$(".table_menu_list").width($(window).width()-260);
 $(".table_menu_list").height($(window).height()-215);
  //当文档窗口发生改变时 触发  
    $(window).resize(function(){
	$(".widget-box").height($(window).height()-215);
	 $(".table_menu_list").width($(window).width()-260);
	  $(".table_menu_list").height($(window).height()-215);
	})
 
/*******树状图*******/
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
		beforeClick: function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				demoIframe.attr("src",treeNode.file + ".jsp");
				return true;
			}
		}
	}
};

var zNodes =[
	{ id:1, pId:0, name:"书籍分类列表", open:true},
	{ id:11, pId:1, name:"教材区"},
	{ id:111, pId:11, name:"成功励志"},
	{ id:112, pId:11, name:"法律"},
	{ id:113, pId:11, name:"管理"},
	{ id:114, pId:11, name:"计算机与网络"},
	{ id:115, pId:11, name:"教育考试"},
	{ id:116, pId:11, name:"科技工程"},
	{ id:117, pId:11, name:"生活时尚"},
	{ id:118, pId:11, name:"文化历史"},
	{ id:12, pId:1, name:"工具书区"},
	{ id:121, pId:12, name:"英语四六级 "},
	{ id:122, pId:12, name:"公务员资料 "},
	{ id:123, pId:12, name:"考研资料 "},
	{ id:124, pId:12, name:"雅思托福 "},
	{ id:125, pId:12, name:"其他 "},
	{ id:13, pId:1, name:"分享区"},
	{ id:131, pId:13, name:"分享区"}
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
/*产品-下架*/
function member_stop(obj,id){
	var soldBook = null;
	layer.confirm('确认要下架吗？',function(index){
		/* if (xmlhttp != null) {
			// 定义请求地址
			var url = "book.s?op=query&bid="+ id;
			// 以 POST 方式 开启连接
			// POST 请求 更安全（编码）  提交的数据大小没有限制
			xmlhttp.open("POST", url, true);
			// 设置回调函数   // 当收到服务器的响应时，会触发该函数（回调函数）
			// 每次的状态改变都会调用该方法
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					// 替换空格
					soldBook = xmlhttp.responseText.replace(/\s/gi, "");
				}
			};
			// 发送请求
			xmlhttp.send(null);
		} else {
			soldBook = "不能创建XMLHttpRequest对象实例";
		} */
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,id)" href="javascript:;" title="上架"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已下架!',{icon: 5,time:1000});
	});
}

/*产品-上架*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" javascript:;" title="下架"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
}
/*产品-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}

/*产品-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
//面包屑返回值
var index = parent.layer.getFrameIndex(window.name);
parent.layer.iframeAuto(index);
$('.Order_form').on('click', function(){
	var cname = $(this).attr("title");
	var chref = $(this).attr("href");
	var cnames = parent.$('.Current_page').html();
	var herf = parent.$("#iframe").attr("src");
    parent.$('#parentIframe').html(cname);
    parent.$('#iframe').attr("src",chref).ready();;
	parent.$('#parentIframe').css("display","inline-block");
	parent.$('.Current_page').attr({"name":herf,"href":"javascript:void(0)"}).css({"color":"#4c8fbd","cursor":"pointer"});
	//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
    parent.layer.close(index);
	
});
</script>