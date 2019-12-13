<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script
	src="${path}/back/js/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="${path}/css/index.css"/>
	<link rel="stylesheet" href="${path}/css/font-awesome.min.css"/>
	<script src="${path}/js/jquery-1.11.2.min.js"></script>
	<script src="${path}/js/main.js"></script>
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
		        url:'${path}/book.s?op=uploadImage', 
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
		//上传图片
		function upImg(){
			// js 获取文件对象
			var fileObj = document.getElementById("img").files[0]; // js 获取文件对象
			var form = new FormData(); // FormData 对象
		    form.append("file", fileObj); // 文件对象
			$.ajax({
		        url:'${path}/book.s?op=uploadImage', 
		        type:'post',
		        data: form,
		        contentType: false,
		        processData: false,
		        success:function(result){
		        	if(result.code == 1){
		        		document.getElementById("imghead").src =result.data;
		        		document.getElementById("img_path").value =result.data;
					}
		        }
		    });
		}
		
		
		//发布书籍
		$('form').submit(function() {
			  var param = $(this).serialize();
			  $.ajax({
		            type: "post",
		            url: "book.s?op=userAddBook",
		            data: param,
		            async:true, // 异步请求
		            cache:false, // 设置为 false 将不缓存此页面
		            dataType: 'json', // 返回对象
		            success: function(result) {
		                
		            }
		        });
		});
	//输入数据验证
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
<jsp:include page="../common/header.jsp"></jsp:include>

<div class="help-wrap">
	<div class="container clearfix">
		<div class="bread">当前位置：
			<a href="${path}/back/lhoption/index.jsp">首页</a> >
			<a href="${path}/back/lywoption/member.jsp">个人中心</a> >
			<a href="${path}/back/lywoption/bought.jsp">发布书籍</a>
		</div>
		<jsp:include page="../common/middle.jsp"></jsp:include>
		<div class="help-r fr">
			<div class="help-item-title">发布书籍</div>
			<div class="help-main">
				<form >
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类1：</div>
						</div>
						<div class="product-edit-item-r fl">
						
						<select style="width: 150px" name="buniversity" >
							<option value="图书所属大学">图书所属大学</option>
							<c:forEach items="${userUni}" var="uni">
								<option >${uni }</option>
							</c:forEach>
						</select>
							
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类2：</div>
						</div>
						<div class="product-edit-item-r fl">
							<select name="bucollege" style="width: 150px" >
							<c:forEach items="${userUcol}" var="ucol">
								<option >${ucol }</option>
							</c:forEach>
							</select>
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类3：</div>
						</div>
						<div class="product-edit-item-r fl">
							<select name="bumajor" style="width: 150px">
								<c:forEach items="${userUmar}" var="umar">
										<option >${umar }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl">
							<div class="fr"><i class="middle">*</i>图书分类4：</div>
						</div>
						<div class="product-edit-item-r fl">
							<input type="text" placeholder="请输入类别" name="btemp"style="width: 150px" value="">
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
								<c:forEach items="${btTypeEdit}" var="btType">
										<option >${btType}</option>
								</c:forEach>
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
							<input type="text" name="bprice"style="width: 200px" value="">
						</div>
					</div>
					<div class="product-edit-item clearfix">
						<div class="product-edit-item-l fl clearfixc">
							<div class="fr"><i class="middle">*</i>图书描述：</div>
						</div>
						<div class="product-edit-item-r fl">
							<textarea name="bcontent"  cols="30" rows="10" >
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
								<img id="imghead" border=0 src="${path }/images/book.jpg" />
							</div>
							<input type="text" id ="img_path" name="img_path" style="dispaly:none;" />
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
					<div class="upload"><label><input type="submit" value="点击发布"></label></div>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>