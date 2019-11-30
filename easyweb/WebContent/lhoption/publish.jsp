<%@page import="com.yc.easyweb.biz.BookTypeBiz"%>
<%@page import="com.yc.easyweb.bean.BookType"%>
<%@ page import="com.yc.easyweb.biz.BookBiz"%>
<%@ page import="com.yc.easyweb.bean.Book"%>
<%@ page import ="com.yc.easyweb.dao.BookDao"%>
<%@ page import ="com.yc.easyweb.common.DbHelper" %>
<%@ page import = "java.lang.*"%>
<%@ page import ="java.util.*" %>
<%@ page import ="com.yc.easyweb.servlet.BookServlet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="css/index.css"/>
	<link rel="stylesheet" href="css/font-awesome.min.css"/>
	<script src="js/jquery-1.11.2.min.js"></script>
	<script src="js/main.js"></script>
	<script type="text/javascript">
		//图片上传预览    IE是用了滤镜。
		function previewImage(file)
		{
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
//                 img.style.marginLeft = rect.left+'px';
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
			<a href="index.jsp">首页</a> >
			<a href="member.jsp">个人中心</a> >
			<a href="bought.jsp">发布书籍</a>
		</div>
		<div class="help-l fl">
			<div class="help-item">
				<div class="help-item-title">个人中心
					<a href="javascript:void(0)" class="abs"></a>
				</div>
				<div class="help-item-list">
					<ul>
						<li><a href="member.jsp">个人信息</a></li>
						<li><a href="password.jsp">修改密码</a></li>
					</ul>
				</div>
			</div>
			<div class="help-item">
				<div class="help-item-title">书籍管理
					<a href="javascript:void(0)" class="abs"></a>
				</div>
				<div class="help-item-list">
					<ul>
						<li><a href="published.jsp">已发布</a></li>
						<li><a href="bought.jsp">已买书籍</a></li>
						<li><a href="publish.jsp">发布书籍</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="help-r fr">
			<div class="help-item-title">发布书籍</div>
			<div class="help-main">
				<form action="doupload.jsp" method="post" enctype="multipart/form-data">
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类1：</div>
						</div>
						<div class="product-edit-item-r fl">
						<% 
						
						BookBiz biz = new BookBiz();
						Book book = null;
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
						HashSet set3 =  new HashSet();
						for(int i = 0;i<list.size();i++){
							for(Book s : list){
								
								set3.add(s.getBtemp());
								
							}
						}
						set3.remove(null);
						set3.remove("");
						BookTypeBiz biz1 = new BookTypeBiz();
						BookType type = null;
						List<BookType> list1 = biz1.selectAll(type);
						System.out.println(list1.size());
						for(int j =0;j<list1.size();j++){
							if(list1.get(j).getBtnamesecond()==null){
								list1.remove(j);
								j--;
							}
						}
						
						%>
						<select style="width: 150px" name="buniversity" >
							<option>图书所属学校</option>
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
							<option>图书所属学院</option>
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
							<option>图书所属专业</option>
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
							<select name="btemp" style="width: 150px" >
							<option value="">图书所属系列</option>
							<% for(Iterator it = set3.iterator();it.hasNext(); ){%>
							<option ><%=it.next()%></option>
							<%} %>
							</select>
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类5：</div>
						</div>
						<div class="product-edit-item-r fl">
							<select name="bclass" style="width: 150px" >
								<option >图书所属年级</option>
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
							<input type="text" value="" name="bname"style="width: 200px">
							<p >图书标题名称长度至少3个字符，最长50个汉字</p>
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl clearfixc">
							<div class="fr"><i class="middle">*</i>图书价格：</div>
						</div>
						<div class="product-edit-item-r fl">
							<input type="text" value="" name="bprice"style="width: 200px">
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl clearfixc">
							<div class="fr"><i class="middle">*</i>图书描述：</div>
						</div>
						<div class="product-edit-item-r fl">
							<textarea name="bcontent"  cols="30" rows="10"></textarea>
							<p >请如实描述你所发布书籍的详细情况，以方便其他会员购买！</p>
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl clearfixc">
							<div class="fr"><i class="middle">*</i>上传封面：</div>
						</div>
						<div class="product-edit-item-r fl">
							<div id="preview">
								<img id="imghead" border=0 src="" />
							</div>
							<input type="file" name="img" onchange="previewImage(this)" />
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