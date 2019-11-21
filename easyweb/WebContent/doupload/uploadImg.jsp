<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="<%=application.getContextPath() %>/back/js/jquery-1.9.1.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XMLHttpRequest上传文件</title>
<script type="text/javascript">
        function UpladFile() {
        	// js 获取文件对象
        	var fileObj = document.getElementById("file").files[0]; // js 获取文件对象
            var form = new FormData(); // FormData 对象
            var path;
            form.append("file", fileObj); // 文件对象
            $.ajax({
                url:'<%=application.getContextPath()%>/book.s?op=uploadImage', 
                type:'post',
                data: form,
                contentType: false,
                processData: false,
                success:function(result){
                	if(result.code == 1){
                		alert(result.msg);
                		path = result.data;
                		alert(path);
        			} else {
        				alert(result.msg);
        			}
                }
            }); 
        }
    </script>
</head>
<body>
	<input type="file" id="file" name="myfile" />
	<input type="button" onclick="UpladFile()" value="上传" />
</body>
</html>