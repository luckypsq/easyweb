<%@page import="com.yc.easyweb.bean.User"%>
<%@page import="com.yc.easyweb.biz.BookBiz"%>
<%@page import="com.yc.easyweb.bean.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.jspsmart.upload.Files"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html;charset=utf-8");
String path = this.getServletContext().getContextPath();
String url = null;
Book book = new Book();
		String buniversity = request.getParameter("buniversity");	
		String bucollege =request.getParameter("bucollege");
		String bumajor = request.getParameter("bumajor");		
		String bclass =  request.getParameter("bclass");	
		String btemp =  request.getParameter("btemp");
		long btid =0  ;
		String bcontent = request.getParameter("bcontent");
		String bname = request.getParameter("bname");
		String bimg = request.getParameter("img_path");
		User user = (User)session.getAttribute("loginedUser");
		Book bookOld = new Book();
		if(buniversity  != null && !buniversity.isEmpty()){
			book.setBuniversity(buniversity);
		}
		if(bucollege  != null && !bucollege.isEmpty()){
			book.setBucollege(bucollege);
		}
		if(bumajor  != null && !bumajor.isEmpty()){
			book.setBumajor(bumajor);
		}
		if(bclass  != null && !bclass.isEmpty()){
			book.setBclass(bclass);
		}
		if(btemp  != null && !btemp.isEmpty()){
			book.setBtemp(btemp);
		}
		if(request.getParameter("btid")  != null && !request.getParameter("btid").isEmpty()){
			btid = Integer.parseInt(request.getParameter("btid"));
			book.setBtid(btid);
		}
		if(request.getParameter("bprice")  != null && !request.getParameter("bprice").isEmpty()){
			book.setBprice(Double.parseDouble(request.getParameter("bprice")));
		}
		if(bcontent  != null && !bcontent.isEmpty()){
			book.setBcontent(bcontent);
		}
		if(bname  != null && !bname.isEmpty()){
			book.setBname(bname);
		}
		if(bimg != null && !bimg.isEmpty()){
			book.setBimg(bimg); 
		}
		if(user.getUid() != 0){
			book.setUid(user.getUid());
		}
		if(request.getParameter("bid")  != null && !request.getParameter("bid").isEmpty()){
			bookOld.setBid(Long.parseLong(request.getParameter("bid")));
		}
		BookBiz biz = new BookBiz();
		if(bookOld.getBid() != 0){
			int j = biz.update(book, bookOld);
			if (j > 0) {
				url = path + "/lhoption/publish.jsp?msg=" +2;
			} else {
				url = path + "/lhoption/publish.jsp?msg=" + 3;
			}
			response.sendRedirect(url);
		}else{
			int i = biz.insert(book);
				if (i > 0) {
					url = path + "/lhoption/publish.jsp?msg=" + 1;
				} else {
					url = path + "/lhoption/publish.jsp?msg=" + 0;
				}
				response.sendRedirect(url);
		}
%>
