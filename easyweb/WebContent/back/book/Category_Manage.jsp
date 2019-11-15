<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="<%=application.getContextPath() %>/back/assets/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/css/style.css"/>       
        <link href="<%=application.getContextPath() %>/back/assets/css/codemirror.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/ace.min.css" />
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/Widget/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/font-awesome.min.css" />
        
		<!--[if IE 7]>
		  <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
        <!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/ace-ie.min.css" />
		<![endif]-->
			<script src="<%=application.getContextPath() %>/back/assets/js/jquery.min.js"></script>
		<!-- <![endif]-->
		<!--[if IE]>
       <script src="<%=application.getContextPath() %>/back/http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <![endif]-->
		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
		<!-- <![endif]-->
		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
        <script src="<%=application.getContextPath() %>/back/assets/js/ace-elements.min.js"></script>
		<script src="<%=application.getContextPath() %>/back/assets/js/ace.min.js"></script>
        <script src="<%=application.getContextPath() %>/back/assets/js/bootstrap.min.js"></script>
		<script src="<%=application.getContextPath() %>/back/assets/js/typeahead-bs2.min.js"></script>
        <script type="text/javascript" src="<%=application.getContextPath() %>/back/Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script> 
        <script src="<%=application.getContextPath() %>/back/js/lrtk.js" type="text/javascript" ></script>
<title>分类管理</title>
</head>

<body>
<div class=" clearfix">
 <div id="category">
    <div id="scrollsidebar" class="left_Treeview">
    <div class="show_btn" id="rightArrow"><span></span></div>
    <div class="widget-box side_content" >
    <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
     <div class="side_list">
      <div class="widget-header header-color-green2">
          <h4 class="lighter smaller">书籍类型列表</h4>
      </div>
      <div class="widget-body">
          <div class="widget-main padding-8">
              <div  id="treeDemo" class="ztree"></div>
          </div>
  </div>
  </div>
  </div>  
  </div>
<!---->
 <iframe ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO  src="<%=application.getContextPath() %>/back/book/product-category-add.jsp" class="page_right_style"></iframe>
 </div>
</div>
</body>
</html>
<script type="text/javascript"> 
$(function() { 
	$("#category").fix({
		float : 'left',
		//minStatue : true,
		skin : 'green',	
		durationTime :false
	});
});
</script>
<script type="text/javascript">
//初始化宽度、高度  
 $(".widget-box").height($(window).height()); 
 $(".page_right_style").width($(window).width()-220);
  //当文档窗口发生改变时 触发  
    $(window).resize(function(){
	$(".widget-box").height($(window).height());
	 $(".page_right_style").width($(window).width()-220);
	})
 
/**************/
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
</script>