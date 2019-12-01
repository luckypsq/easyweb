<%@ page import="com.yc.easyweb.biz.*"%>
<%@ page import="com.yc.easyweb.bean.*"%>
<%@ page import ="com.yc.easyweb.dao.*"%>
<%@ page import ="com.yc.easyweb.common.DbHelper" %>
<%@ page import = "java.lang.*"%>
<%@ page import ="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script
	src="<%=application.getContextPath()%>/back/js/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="<%=application.getContextPath()%>/css/index.css"/>
	<link rel="stylesheet" href="<%=application.getContextPath()%>/css/font-awesome.min.css"/>
	<script src="<%=application.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
	<script src="<%=application.getContextPath()%>/js/main.js"></script>
	<style type="text/css">
			#img_path{
				display:none;
			}
			#notice{
				display:none;
			}
	</style>
	<script type="text/javascript">
		//图片上传预览    IE是用了滤镜。
		function previewImage(file)
		{
			var fileObj = document.getElementById("img").files[0]; // js 获取文件对象
			var form = new FormData(); // FormData 对象
		    form.append("file", fileObj); // 文件对象
			$.ajax({
		        url:'<%=application.getContextPath()%>/book.s?op=uploadImage', 
		        type:'post',
		        data: form,
		        contentType: false,
		        processData: false,
		        success:function(result){
		        	if(result.code == 1){
		        		document.getElementById("img_path").value =result.data;
					}
		        }
		    });
			var MAXWIDTH  = 260;
			var MAXHEIGHT = 180;
			var div = document.getElementById('preview');
			if (file.files && file.files[0])
			{
				
				div.innerHTML ='<img id=imghead>';
				var img = document.getElementById('imghead');
				img.onload = function(){
					var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
					img.width  =  rect.width;
					img.height =  rect.height;
					img.style.marginTop = rect.top+'px';
				}
				var reader = new FileReader();
				reader.onload = function(evt){img.src = evt.target.result;}
				reader.readAsDataURL(file.files[0]);
			}
			else //兼容IE
			{
				var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
				file.select();
				var src = document.selection.createRange().text;
				div.innerHTML = '<img id=imghead>';
				var img = document.getElementById('imghead');
				img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
				status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
				div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
			}
		}
		function clacImgZoomParam( maxWidth, maxHeight, width, height ){
			var param = {top:0, left:0, width:width, height:height};
			if( width>maxWidth || height>maxHeight )
			{
				rateWidth = width / maxWidth;
				rateHeight = height / maxHeight;

				if( rateWidth > rateHeight )
				{
					param.width =  maxWidth;
					param.height = Math.round(height / rateWidth);
				}else
				{
					param.width = Math.round(width / rateHeight);
					param.height = maxHeight;
				}
			}

			param.left = Math.round((maxWidth - param.width) / 2);
			param.top = Math.round((maxHeight - param.height) / 2);
			return param;
		}
		function upload(){
			location.href='upload.jsp';
		}
		//监听input框的变化
		window.onload = function()
		{
			 var msg =  document.getElementById("notice").value;
			 
			if(msg == 0) {
				alert("添加失败！！！");
		     }else if(msg == 1){
		    	 alert("添加成功！！！");
			}else if(msg == 2){
				alert("修改成功！！！");
			}else if(msg == 3){
				alert("修改失败！！！");
			}
		}
	</script>
	<title>易书网</title>
</head>
<style>
	.help-main {
		padding: 0;
	}
	select{
		padding: 3px 10px;
	}
</style>
<body>
<jsp:include page="/common/header.jsp"></jsp:include>

<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="<%=application.getContextPath()%>/lhoption/index.jsp">首页</a> >
			<a href="<%=application.getContextPath()%>/lywoption/member.jsp">个人中心</a> >
			<a href="<%=application.getContextPath()%>/lywoption/bought.jsp">发布书籍</a>
		</div>
		<div class="help-l fl">
			<div class="help-item">
				<div class="help-item-title">个人中心
					<a href="javascript:void(0)" class="abs"></a>
				</div>
				<div class="help-item-list">
					<ul>
							<li><a href="<%=application.getContextPath()%>/lywoption/member.jsp">个人信息</a></li>
						<li><a href="<%=application.getContextPath()%>/lywoption/password.jsp">修改密码</a></li>
				</ul>
				</div>
			</div>
			<div class="help-item">
				<div class="help-item-title">书籍管理
					<a href="javascript:void(0)" class="abs"></a>
				</div>
				<div class="help-item-list">
					<ul>
						<li><a href="<%=application.getContextPath()%>/lhoption/published.jsp">已发布</a></li>
						<li><a href="<%=application.getContextPath()%>/lywoption/bought.jsp">购物车</a></li>
						<li><a href="<%=application.getContextPath()%>/lywoption/bought2.jsp">已买书籍</a></li>
						<li><a href="<%=application.getContextPath()%>/lhoption/publish.jsp">发布书籍</a></li>
				</ul>
				</div>
			</div>
		</div>
		<div class="help-r fr">
			<div class="help-item-title">发布书籍</div>
			<div class="help-main">
				<form action="<%=application.getContextPath()%>/lhoption/doupload.jsp" method="post">
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类1：</div>
						</div>
						<div class="product-edit-item-r fl">
						<% 
						String notice = null;
						if(request.getParameter("msg") != null && !request.getParameter("msg").isEmpty()){
							notice = request.getParameter("msg");
						}
						BookBiz biz = new BookBiz();
						Book book = new Book();
						List<Book> list = biz.selectAll(book);
						HashSet set =  new HashSet();
						for(int i = 0;i<list.size();i++){
							for(Book s : list){
								set.add(s.getBuniversity());
							}
						}
						set.remove(null);
						set.remove("");
						HashSet set1 =  new HashSet();
						for(int i = 0;i<list.size();i++){
							
							for(Book s : list){
								
								set1.add(s.getBucollege());
							}
						}
						set1.remove(null);
						set1.remove("");
						HashSet set2 =  new HashSet();
						for(int i = 0;i<list.size();i++){
							for(Book s : list){
								set2.add(s.getBumajor());
							}
						}
						set2.remove(null);
						set2.remove("");
						BookTypeBiz biz1 = new BookTypeBiz();
						BookType type = null;
						List<BookType> list1 = biz1.selectAll(type);
						for(int j =0;j<list1.size();j++){
							if(list1.get(j).getBtnamesecond()==null){
								list1.remove(j);
								j--;
							}
						}
						Book bookOld = new Book();
						if(request.getParameter("bid")!= null && !request.getParameter("bid").isEmpty()){
							bookOld.setBid(Long.parseLong(request.getParameter("bid")));
						}else{
							bookOld.setBid(-1);
						}
						Book bookShowOld = biz.selectSingle(bookOld);
						%>
						<select style="width: 150px" name="buniversity" >
							<option><%=bookShowOld.getBuniversity() == null?"图书所属学校": bookShowOld.getBuniversity()%></option>
							<% for(Iterator it = set.iterator();it.hasNext(); ){%>
							<option  ><%=it.next()%></option>
							<%} %>
						</select>
							
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类2：</div>
						</div>
						<div class="product-edit-item-r fl">
							<select name="bucollege" style="width: 150px" >
							<option><%=bookShowOld.getBucollege() == null?"图书所属学院": bookShowOld.getBucollege()%></option>
							<% for(Iterator it = set1.iterator();it.hasNext(); ){%>
							<option ><%=it.next()%></option>
							<%} %>
							</select>
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类3：</div>
						</div>
						<div class="product-edit-item-r fl">
							<select name="bumajor" style="width: 150px">
							<option><%=bookShowOld.getBumajor() == null?"图书所属专业": bookShowOld.getBumajor()%></option>
							<% for(Iterator it = set2.iterator();it.hasNext(); ){%>
							<option ><%=it.next()%></option>
							<%} %>
							</select>
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类4：</div>
						</div>
						<div class="product-edit-item-r fl">
							<input type="text" placeholder="请输入类别" name="btemp"style="width: 150px" value="<%=bookShowOld.getBtemp() == null?"": bookShowOld.getBtemp()%>">
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类5：</div>
						</div>
						<div class="product-edit-item-r fl">
							<select name="bclass" style="width: 150px" >
								<option ><%=bookShowOld.getBclass()== null?"图书所属年级": bookShowOld.getBclass()%></option>
								<option >大一</option>
								<option >大二</option>
								<option >大三</option>
								<option >大四</option>
							</select>
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类6：</div>
						</div>
						<div class="product-edit-item-r fl">
							<select name="btid" style="width: 150px" >
								<option value="">图书所属类型</option>
								<option value="3">分享</option>
								<%for(BookType t : list1){ %>
								<option value="<%=t.getBtid()%>"><%=t.getBtnamesecond() %></option>
								<%} %>
								
							</select>
						</div>
					</div>
					
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl clearfixc">
							<div class="fr"><i class="middle">*</i>图书名称：</div>
						</div>
						<div class="product-edit-item-r fl">
							<input type="text" value="<%=bookShowOld.getBname()== null?"": bookShowOld.getBname()%>" name="bname"style="width: 200px">
							<p >图书标题名称长度至少3个字符，最长50个汉字</p>
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl clearfixc">
							<div class="fr"><i class="middle">*</i>图书价格：</div>
						</div>
						<div class="product-edit-item-r fl">
							<input type="text" name="bprice"style="width: 200px" value="<%=bookShowOld.getBprice()== 0?"": bookShowOld.getBprice()%>">
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl clearfixc">
							<div class="fr"><i class="middle">*</i>图书描述：</div>
						</div>
						<div class="product-edit-item-r fl">
							<textarea name="bcontent"  cols="30" rows="10" >
							<%=bookShowOld.getBcontent()== null?"": bookShowOld.getBcontent()%>
							</textarea>
							<p >请如实描述你所发布书籍的详细情况，以方便其他会员购买！</p>
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl clearfixc">
							<div class="fr"><i class="middle">*</i>上传封面：</div>
						</div>
						<div class="product-edit-item-r fl">
							<div id="preview">
								<img id="imghead" border=0 src="<%=bookShowOld.getBimg()== null?"": bookShowOld.getBimg()%>" />
							</div>
							<input type="text" id ="img_path" name="img_path" style="dispaly:none;" />
							<input type="text" id ="notice"  style="dispaly:none;"value="<%=notice==null?-1:notice %>" />
							<input type="file" name="img" id ="img"onchange="previewImage(this)" />
							<p >请上传图书封面，尽量保持图片清晰</p>
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl clearfixc" style="border: none;border-top:1px solid #E6E6E6">
						</div>
						<div class="product-edit-item-r fl" style="border: none;border-top:1px solid #E6E6E6">
						</div>
					</div>
					<div class="upload"><label><input type="submit" value="点击发布""></label></div>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>