<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.yc.easyweb.bean.*"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
        <link href="<%=application.getContextPath() %>/back//assets/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back//css/style.css"/>       
        <link href="<%=application.getContextPath() %>/back/assets/css/codemirror.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/ace.min.css" />
        <link rel="stylesheet" href="<%=application.getContextPath() %>/back/font/css/font-awesome.min.css" />
        <!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=application.getContextPath() %>/back/assets/css/ace-ie.min.css" />
		<![endif]-->
		<script src="<%=application.getContextPath() %>/back/js/jquery-1.9.1.min.js"></script>
        <script src="<%=application.getContextPath() %>/back/assets/js/bootstrap.min.js"></script>
		<script src="<%=application.getContextPath() %>/back/assets/js/typeahead-bs2.min.js"></script>           	
		<script src="<%=application.getContextPath() %>/back/assets/js/jquery.dataTables.min.js"></script>
		<script src="<%=application.getContextPath() %>/back/assets/js/jquery.dataTables.bootstrap.js"></script>
        <script src="<%=application.getContextPath() %>/back/assets/layer/layer.js" type="text/javascript" ></script>          
        <script src="<%=application.getContextPath() %>/back/assets/laydate/laydate.js" type="text/javascript"></script>
<title>管理权限</title>
</head>

<body>
 <div class="margin clearfix">
   <div class="border clearfix">
       <span class="l_f">
        <a href="<%=application.getContextPath() %>/back/admin/Competence.jsp" id="Competence_add" class="btn btn-warning" title="添加权限"><i class="fa fa-plus"></i> 添加权限</a>
        <a href="javascript:ovid()" class="btn btn-danger"><i class="fa fa-trash"></i> 批量删除</a>
       </span>
       <span class="r_f">共：<b>5</b>类</span>
     </div>
     <div class="compete_list">
       <table id="sample-table-1" class="table table-striped table-bordered table-hover">
		 <thead>
			<tr>
			<%
				UserBiz userBiz = new UserBiz();
				User user = new User();
				user.setUstate(1);
				user.setUtype(4);
				List<User> userList1 = userBiz.selectAll(user);
				user.setUtype(5);
				List<User> userList2 = userBiz.selectAll(user);
			%>
			  <th class="center"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
			  <th>权限名称</th>
			  <th>人数</th>
              <th>用户名称</th>
			  <th class="hidden-480">描述</th>             
			  <th class="hidden-480">操作</th>
             </tr>
		    </thead>
             <tbody>
			  <tr>
				<td class="center"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
				<td>超级管理员</td>
				<td><%=userList1.size() %></td>
				<td class="hidden-480"><%=userList1.get(0).getUname()%></td>
				<td>拥有至高无上的权利,操作系统的所有权限</td>
				<td>
                 <a title="编辑" onclick="Competence_modify('560')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="fa fa-edit bigger-120"></i></a>        
                 <a title="删除" href="javascript:;"  onclick="Competence_del(this,'1')" class="btn btn-xs btn-warning" ><i class="fa fa-trash  bigger-120"></i></a>
				</td>
			   </tr>
			   <tr>
				<td class="center"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
				<td>普通管理员</td>
				<td><%=userList2.size() %></td>
				<%
					String userStr = "";
					for(int i=0;i<userList2.size() ;i++){
						userStr = userStr+userList2.get(i).getUname();
						if(i>5){
							break;
						}
						userStr = userStr + ",";
					}
				 %>
				<td class="hidden-480"><%=userStr%></td>
				<td>拥有网站的系统大部分使用权限，没有权限管理功能。</td>
				<td>
                 <a title="编辑" onclick="Competence_modify('561')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="fa fa-edit bigger-120"></i></a>        
                 <a title="删除" href="javascript:;"  onclick="Competence_del(this,'2')" class="btn btn-xs btn-warning" ><i class="fa fa-trash  bigger-120"></i></a>
				</td>
			   </tr>	
              <!--  <tr>
				<td class="center"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
				<td>编辑管理员</td>
				<td>5</td>
				<td class="hidden-480">admin345,stysty,adminstyle,admin45678,admin123455</td>
				<td>拥有部分权限，主要进行编辑功能，无边界订单功能，权限分配功能。</td>
				<td>
                 <a title="编辑" onclick="Competence_modify('562')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="fa fa-edit bigger-120"></i></a>        
                 <a title="删除" href="javascript:;"  onclick="Competence_del(this,'3')" class="btn btn-xs btn-warning" ><i class="fa fa-trash  bigger-120"></i></a>
				</td>
			   </tr>		 -->										
		      </tbody>
	        </table>
     </div>
 </div>
</body>
</html>
<script type="text/javascript">
 /*权限-删除*/
function Competence_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
/*修改权限*/
function Competence_modify(id){
		window.location.href ="<%=application.getContextPath() %>/back/admin/Competence.jsp?="+id;
};	
/*字数限制*/
function checkLength(which) {
	var maxChars = 200; //
	if(which.value.length > maxChars){
	   layer.open({
	   icon:2,
	   title:'提示框',
	   content:'您出入的字数超多限制!',	
    });
		// 超过限制的字数了就将 文本框中的内容按规定的字数 截取
		which.value = which.value.substring(0,maxChars);
		return false;
	}else{
		var curr = maxChars - which.value.length; //250 减去 当前输入的
		document.getElementById("sy").innerHTML = curr.toString();
		return true;
	}
};
//面包屑返回值
var index = parent.layer.getFrameIndex(window.name);
parent.layer.iframeAuto(index);
$('.Order_form ,#Competence_add').on('click', function(){
	var cname = $(this).attr("title");
	var cnames = parent.$('.Current_page').html();
	var herf = parent.$("#iframe").attr("src");
    parent.$('#parentIframe span').html(cname);
	parent.$('#parentIframe').css("display","inline-block");
    parent.$('.Current_page').attr("name",herf).css({"color":"#4c8fbd","cursor":"pointer"});
	//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+">" + cnames + "</a>");
    parent.layer.close(index);
	
});
</script>