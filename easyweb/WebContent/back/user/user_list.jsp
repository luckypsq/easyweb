<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="com.yc.easyweb.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
	href="<%=application.getContextPath() %>/back/assets/css/font-awesome.min.css" />
<script
	src="<%=application.getContextPath() %>/back/assets/js/jquery.min.js"></script>
<script type="text/javascript">
		window.jQuery || document.write("<script src='<%=application.getContextPath() %>/back/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='<%=application.getContextPath() %>/back/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/bootstrap.min.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/typeahead-bs2.min.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/jquery.dataTables.min.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/js/jquery.dataTables.bootstrap.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/js/H-ui.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath() %>/back/js/H-ui.admin.js"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/layer/layer.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath() %>/back/assets/laydate/laydate.js"
	type="text/javascript"></script>
	
<title>用户列表</title>
</head>

<body>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String uname = null;//姓名
	String phone = null;//电话
	String email = null;//邮箱
	//查询所有的用户
	User userShow = new User();//定义显示的用户条件对象
	UserBiz userBiz = new UserBiz();//user事务操作类
	//获取查询条件
	if(request.getParameter("username") != null && !request.getParameter("username").isEmpty()){
		uname = request.getParameter("username");
		userShow.setUname(uname);
	}
	if(request.getParameter("phone") != null && !request.getParameter("phone").isEmpty()){
		phone = request.getParameter("phone");
		userShow.setUphone(phone);
	}
	if(request.getParameter("email") != null && !request.getParameter("email").isEmpty()){
		email = request.getParameter("email");
		userShow.setUemail(email);
	}
	//查询数据
	List<User> showList = userBiz.selectAll(userShow);//数据库所有的用户以及管理员
	//剔除管理员
	for(int i =0;i<showList.size();i++){
		if(showList.get(i).getUtype() ==1 || showList.get(i).getUtype() ==0 ){
			showList.remove(i);//将元素移出
			//此时需注意，因为list会动态变化不像数组会占位，所以当前索引应该后退一位
			i--;
		}
	}
%>
	<div class="page-content clearfix">
		<div id="Member_Ratings">
			<div class="d_Confirm_Order_style">
				<div class="search_style">
					<ul class="search_content clearfix">
						<li><label class="l_f">用户名</label><input name="username" id="username" type="text"value="<%=uname ==null ?"":uname %>"
							class="text_add" placeholder="请输入用户名" style="width: 200px" /></li>
						<li><label class="l_f">电话</label><input name="phone" id="phone"type="text"value="<%=phone ==null ?"":phone %>"
							class="text_add" placeholder="请输入电话" style="width: 200px" /></li>
						<li><label class="l_f">邮箱</label><input name="email" id="email"type="text" value="<%=email ==null ?"":email %>"
							class="text_add" placeholder="请输入邮箱" style="width: 200px" /></li>
						<li style="width: 90px;"><button type="button" onclick="query();"
								class="btn_search">
								<i class="icon-search"></i>查询 
							</button></li>
					</ul>
				</div>
				<div class="border clearfix">
					<span class="l_f"> <a
						href="javascript:ovid()"
						id="member_add" class="btn btn-warning"><i class="icon-plus"></i>添加用户</a>
						<a onclick="selectDelete();" class="btn btn-danger"><i class="icon-trash"></i>批量删除</a>
					</span> <span class="r_f">共：<b><%=showList.size() %></b>条
					</span>
				</div>
				<div class="table_menu_list">
					<table class="table table-striped table-bordered table-hover"
							id="sample-table">
						<thead>
							<tr>
								<th width="25"><label><input type="checkbox"
										class="ace"><span class="lbl"></span></label></th>
								<th width="80">ID</th>
								<th width="100">用户名</th>
								<th width="120">手机</th>
								<th width="120">所在大学</th>
								<th width="100">等级</th>
								<th width="150">邮箱</th>
								<th width="80">性别</th>
								<th width="80">年龄</th>
								<th width="100">昵称</th>
								<th width="180">加入时间</th>
								<th width="100">状态</th>
								<th width="250">操作</th>
							</tr>
						</thead>
						<tbody>
						<%
							String utype = null;
							for(User u : showList){
								pageContext.setAttribute("u",u);
								if(u.getUtype() == 2){
									utype = "用户";
								}else if(u.getUtype() == 3){
									utype = "会员";
								}else if(u.getUtype() == 4){
									utype = "钻石会员";
								}
						%>
							<tr>
								<td><label><input type="checkbox" class="ace"><span
										class="lbl"></span></label></td>
								<td>${u.getUid() }</td>
								<td><u style="cursor: pointer" class="text-primary"
									onclick="member_show('个人信息','<%=application.getContextPath()%>/back/user/member-show.jsp','${u.getUid() }','500','400')">${u.getUname() }</u></td>
								<td>${u.getUphone() }</td>
								<td>${u.getUniversity() }</td>
								<td><%=utype == null? "用户":utype %></td>
								<td>${u.getUemail() }</td>
								<td><%=u.getUsex() == 1? "男":"女"%></td>
								<td>${u.getUage() }</td>
								<td>${u.getUminname() }</td>
								<td>${u.getUtime() }</td>
								<%
									if(u.getUstate() == 1){
								%>
								<td class="td-status">
									<span class="label label-success radius">已启用</span>
								</td>
								<%}else if(u.getUstate() == 2){%>
									<td class="td-status">
										<span class="label label-defaunt radius">已冻结</span>
									</td>
								<% }else if(u.getUstate() == 3){%>
									<td class="td-status">
									<span class="label label-defaunt radius">已删除</span>
									</td>
								<%}%>
								<td class="td-manage">
								<%
									if(u.getUstate() == 1){
								%>
								<a
									onClick="member_stop(this,'${u.getUid() }')"
									href="javascript:;"
									title="停用" class="btn btn-xs btn-success"><i
									class="icon-ok bigger-120"></i></a>
								<%}else{%>
									<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,${u.getUid() })" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>
								<%}%>
									 <a title="编辑"
									href='<%=application.getContextPath()%>/back/user/userAdd.jsp?uid=${u.getUid() }'
									class="btn btn-xs btn-info"><i class="icon-edit bigger-120"></i></a>
									<a title="删除"
									href="javascript:;"
									onclick="member_del(this,'${u.getUid() }')" class="btn btn-xs btn-warning"><i
									class="icon-trash  bigger-120"></i></a></td>
							</tr>
							<%} %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="add_menber" id="add_menber_style" style="display:none;">
		<%@ include file='/back/user/userAdd.jsp'%>
	</div>
</body>
</html>
<script>
//全选
var sbox = "";
jQuery(function($) {
				var oTable1 = $('#sample-table').dataTable( {
				"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
		] } );
				//设置全选				
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
			})
/*用户-添加*/
 $('#member_add').on('click', function(){
    layer.open({
        type: 1,
        title: '添加用户',
		maxmin: true, 
		shadeClose: true, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_menber_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".add_menber input[type$='text']").each(function(n){
          if($(this).val()=="")
          {
			   layer.alert(str+=""+$(this).attr("name")+"不能为空！\r\n",{
                title: '提示框',				
				icon:0,								
          }); 
		    num++;
            return false;            
          } 
		 });
		  if(num>0){  return false;}	 	
          else{
			  layer.alert('添加成功！',{
               title: '提示框',				
			icon:1,		
			  });
			   layer.close(index);	
		  }		  		     				
		}
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
/*用户查询*/
function query(){
	var username =  document.getElementById("username").value.replace(/\ +/g,"");
	var phone= document.getElementById("phone").value.replace(/\ +/g,"");
	var email = document.getElementById("email").value.replace(/\ +/g,"");
	window.location.href="<%=application.getContextPath()%>/back/user/user_list.jsp?username="+username+"&phone="+phone+"&email="+email;
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url+'?uid='+id,w,h);
}
/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		if(xmlhttp!=null){
			// 定义请求地址
			var url ="<%=application.getContextPath()%>/user.s?op=updateState&ustate=2&uid="+id;
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
						$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,${u.getUid() })" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
						$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
						$(obj).remove();
						layer.msg('已停用!',{icon: 1,time:1000});
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
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		if(xmlhttp!=null){
			// 定义请求地址
			var url ="<%=application.getContextPath()%>/user.s?op=updateState&ustate=1&uid="+id;
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
						$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,${u.getUid() })" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
						$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
						$(obj).remove();
						layer.msg('已启用!',{icon: 6,time:1000});
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
/*用户-编辑*/
function member_edit(id){
	  layer.open({
        type: 1,
        title: '修改用户信息',
		maxmin: true, 
		shadeClose:false, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_menber_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".add_menber input[type$='text']").each(function(n){
          if($(this).val()=="")
          {
			   layer.alert(str+=""+$(this).attr("name")+"不能为空！\r\n",{
                title: '提示框',				
				icon:0,								
          }); 
		    num++;
            return false;            
          } 
		 });
		  if(num>0){  return false;}	 	
          else{
			  layer.alert('添加成功！',{
               title: '提示框',				
			icon:1,		
			  });
			   layer.close(index);	
		  }		  		     				
		}
    });
}
/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		if(xmlhttp!=null){
			// 定义请求地址
			var url ="<%=application.getContextPath()%>/user.s?op=delete&uid="+id;
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
						layer.msg('已删除!',{icon:1,time:1000});
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
			layer.msg("请选择要删除的用户！！！", {
				icon : 7,
				time : 1000
				});
			return ;
		}
		if (xmlhttp != null) {
			// 定义请求地址
			var url = "<%=application.getContextPath()%>/user.s?op=delete&uid="+sbox;
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
						window.location.href='<%=application.getContextPath()%>/back/user/user_list.jsp';
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
laydate({
    elem: '#start',
    event: 'focus' 
});

</script>