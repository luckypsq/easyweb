<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="com.jspsmart.upload.Files"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		//创建文件上传对象
		SmartUpload su = new SmartUpload();
		//初始化，传入页面上下文对象
		su.initialize(pageContext);
		//设置上传配置信息
		//限定文件名后缀
		su.setAllowedFilesList("jpg");
		//限定大小
		su.setMaxFileSize(1024 * 1024 * 10);
		//执行上传
		su.upload();
		Files files = su.getFiles();
		List<String> fileList = new ArrayList<String>();
		String filename = null;//文件名
		String diskPath = null;//磁盘路径
		String realName = null;//文件路径
		for (int i = 0; i < files.getCount(); i++) {
			//为防止上传的文件名重复，将文件名修改为唯一
			//获取系统当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date date = new Date();
			filename = df.format(date);
			//使用application web路径转换成磁盘路径
			diskPath = application.getRealPath("/upload");
			files.getFile(i).saveAs(diskPath + "/" + filename);
			realName = diskPath + "/" + filename;
			//将文件真实路径添加到list集合中
			fileList.add(realName);
		}
		String json = "{code:'1"
				+"', path:'"+realName
				+"', msg:'上传成功！'}";   // 扩张一个结果描述信息
		out.print(json);
	%>
</body>
</html>