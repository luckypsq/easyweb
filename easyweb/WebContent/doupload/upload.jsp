<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
<form action="<%=application.getContextPath() %>/doupload/doupload.jsp" method="post" enctype="multipart/form-data">
<label>上传单个</label><input type="file" name="file1" ><br>
<input type="submit" value="上传">
</form>
</body>
</html>