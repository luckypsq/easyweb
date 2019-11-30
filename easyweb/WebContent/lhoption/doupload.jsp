<%@page import="com.yc.easyweb.biz.BookBiz"%>
<%@page import="com.yc.easyweb.bean.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.jspsmart.upload.Files"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%
	
	//创建文件上传对象
	SmartUpload su = new SmartUpload();
	//初始化，传入页面上下文对象
	su.initialize(pageContext);
	//设置上传的配置信息
	//限定文件后缀
	su.setAllowedFilesList("jpg,png,gif,bmp");
	//限定大小
	su.setMaxFileSize(1024 * 1024 * 10);
	//执行上传
	
	su.upload();
	
	
	Files files = su.getFiles();
	su.getRequest().getParameter("bname");
	String filename = null;//文件名
	String diskPath = null;//磁盘路径
	String realName = null;//文件路径
	String	path = null;
	
	ArrayList<String> fileList = new ArrayList<String>();
	
	for(int i =0;i<files.getCount();i++){
		
		filename = files.getFile(i).getFileName();
		//使用application(应用上下文对象) web路径转换成磁盘路径
		//upload ==>d:dev/tomcat/webapps/damai/upload
		
		diskPath = application.getRealPath("/upload");
	
		
		files.getFile(i).saveAs(diskPath + "/" + filename);
		
		realName = diskPath + "/" + filename;
		//将文件真实路径添加到list集合中
		fileList.add(realName);
	} 
		String buniversity = su.getRequest().getParameter("buniversity");	
		String bucollege =su.getRequest().getParameter("bucollege");
		String bumajor = su.getRequest().getParameter("bumajor");		
		String bclass =  su.getRequest().getParameter("bclass");	
		String btemp =  su.getRequest().getParameter("btemp");
		long btid =Integer.parseInt(su.getRequest().getParameter("btid")) ;
		String bcontent = su.getRequest().getParameter("bcontent");
		String bname = su.getRequest().getParameter("bname");
		String bimg = realName; 
		
		
		
		Book book = new Book();
		book.setBuniversity(buniversity);
		book.setBucollege(bucollege);
		book.setBumajor(bumajor);
		book.setBclass(bclass);
		book.setBtemp(btemp);
		book.setBtid(btid);
		book.setBcontent(bcontent);
		book.setBname(bname);
		book.setBimg(bimg); 
		
		BookBiz biz = new BookBiz();
		int i = biz.insert(book);
		
		
%>
<%if(i>0){ %>
	 图书上传成功!!!
<%}else{ %>
	图书上传失败!!!
<%} %>
<input type="button" value="返回"
		onclick="window.location.href = 'publish.jsp'" />