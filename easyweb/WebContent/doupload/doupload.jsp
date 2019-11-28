<%@page import="com.yc.easyweb.bean.*"%>
<%@page import="com.yc.easyweb.biz.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="com.jspsmart.upload.Files"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处理上传请求</title>
</head>
<body>
	<%
		//创建文件上传对象
		SmartUpload su = new SmartUpload();
		//初始化，传入页面上下文对象
		su.initialize(pageContext);
		//设置上传配置信息
		//限定文件名后缀
		su.setAllowedFilesList("txt");
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
		File file = null;
		BufferedReader br;
		InputStreamReader reader;
		String s = null;//读取txt文件的数据并保存到该字符串中
		List<String[]> listS = new ArrayList<String[]>(); //将存的元素的数组添加到集合中
		String[] params = null;//存储一条数据中的所有元素
		for (String f : fileList) {
			file = new File(f);//创建文件对象
			try {
				//注意txt的文本格式 ，如果为utf—8则需要修改文本格式
				reader = new InputStreamReader(new FileInputStream(file));
				br = new BufferedReader(reader);
				//从文件中读取数据并存入数据库中
				//从文本中一行一行的读取
				while ((s = br.readLine()) != null) {
					params = s.split("\\s+");
					listS.add(params);
				}
				//关闭流
				br.close();
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//listS中存储所有文件的所有数据
		String msg = null;//提示信息
		Book book = null;
		List<Book> list = new ArrayList<Book>();
		System.out.println(listS.size());
		//遍历集合将数据从数组中取出存入Book实体类对象中，并存入集合中
		for (String[] string : listS) {
			/* //数组长度等于13
			if(string.length == 13){
				book = new Book();
				book.setBtid(Integer.parseInt(string[0]));
				book.setBimg(string[1]);
				book.setBname(string[2]+" "+string[4]);
				book.setBauthor(string[3]);
				book.setBcontent(string[5]+" "+string[6]);
				book.setBnum(Long.parseLong(string[7]));
				book.setBprice(Double.parseDouble(string[8]));
				book.setBuniversity(string[9]);
				book.setBucollege(string[10]);
				book.setBumajor(string[11]);
				book.setBclass(string[12]);
				list.add(book);
			} */
			if(string.length == 11){
				book = new Book();
				book.setBtid(Long.parseLong(string[0]));
				book.setBimg(string[2]);
				book.setBname(string[3]);
				book.setBuniversity(string[4]);
				book.setBucollege(string[5]);
				book.setBumajor(string[6]);
				book.setBclass(string[7]);
				book.setBcontent(string[3]);
				book.setBauthor(string[8]);
				book.setBnum(Long.parseLong(string[9]));
				book.setBprice(Double.parseDouble(string[10]));
				list.add(book);
			} else if(string.length == 8){
				book = new Book();
				book.setBtid(Long.parseLong(string[0]));
				book.setBimg(string[2]);
				book.setBname(string[3]);
				book.setBcontent(string[3]+" "+string[4]);
				book.setBauthor(string[5]);
				book.setBnum(Long.parseLong(string[6]));
				book.setBprice(Double.parseDouble(string[7]));
				list.add(book);
			}
		}
		//遍历集合将数据存储进数据库
		BookBiz biz = null;//操作Book表的事件数组
		long num = 0;
		if(list.size() != 0){
			for (int i = 0; i < list.size(); i++) {
				biz = new BookBiz();
				num = biz.insert(list.get(i));
				if (num < 0) {
					msg = "文件上传失败！！！";
					pageContext.setAttribute("msg", msg);
					return;
				}
			}
			msg = "文件上传成功！！！";
			pageContext.setAttribute("msg", msg);
		}else{
			msg = "文件上传失败！！！";
			pageContext.setAttribute("msg", msg);
		}
	%>
	<b>${msg}</b>
	<br>
	<input type="button" value="返回"
		onclick="window.location.href = '<%=application.getContextPath() %>/doupload/upload.jsp'" />
</body>
</html>