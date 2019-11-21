package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;

public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static  BookBiz bookBiz = new BookBiz();
	//查询
	public void  query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
	}
	//添加
	public void  add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SmartUploadException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 创建文件上传对象
				SmartUpload su = new SmartUpload();
				// 初始化，传入页面上下文对象
				su.initialize(getServletConfig(), request, response);
				// 设置上传的配置信息
				// 限定文件名后缀
				su.setAllowedFilesList("jpg,png,gif,bmp");
				// 限定大小
				su.setMaxFileSize(1024 * 1024 * 10);
				// 执行上传
				su.upload();
				// smartupload 对请求对象进行了封装
				HashMap<String, String> paramMap = new HashMap<String, String>();
				@SuppressWarnings("unchecked")
				Enumeration<String> es = su.getRequest().getParameterNames();
				while (es.hasMoreElements()) {
					String paramName = es.nextElement();
					String paramValue = su.getRequest().getParameter(paramName);
					//
					paramMap.put(paramName, new String(paramValue.getBytes("gbk"),"utf-8"));
				}
		 Result result;
		 Book book = new Book();
		 System.out.println(paramMap);
		 if(!paramMap.get("bauthor").equals("") && paramMap.get("bauthor")!=null){
			 book.setBauthor(paramMap.get("bauthor"));
		 }
		 if(!paramMap.get("bdate").equals("") && paramMap.get("bdate")!=null){
			 book.setBdate(paramMap.get("bdate"));
		 }
		if(!paramMap.get("buniversity").equals("请???择") && paramMap.get("buniversity")!=null){
			book.setBuniversity(paramMap.get("buniversity"));
		}
		if(!paramMap.get("bname").equals("") && paramMap.get("bname")!=null){
			book.setBname(paramMap.get("bname"));
		}
		if(!paramMap.get("bclass").equals("") && paramMap.get("bclass")!=null){
			 book.setBclass(paramMap.get("bclass"));
		}
		if(!paramMap.get("num").equals("") && paramMap.get("num")!=null){
			 book.setBnum(Long.parseLong(paramMap.get("num")));
		}
		if(!paramMap.get("imgPath").equals("") && paramMap.get("imgPath")!=null){
			 book.setBimg("<%=application.getContextPath()%>/"+paramMap.get("imgPath"));
		}
		if(!paramMap.get("btemp").equals("") && paramMap.get("btemp")!=null){
			 book.setBtemp(paramMap.get("btemp"));
		}
		if(!paramMap.get("bprice").equals("") && paramMap.get("bprice")!=null){
			 book.setBprice(Double.parseDouble(paramMap.get("bprice")));
		}
		if(!paramMap.get("bcollege").equals("") && paramMap.get("bcollege")!=null){
			 book.setBucollege(paramMap.get("bcollege"));
		}
		if(!paramMap.get("bookType").equals("") && paramMap.get("bookType")!=null){
			book.setBtid(Integer.parseInt(paramMap.get("bookType")));
		}
		if(!paramMap.get("bmajor").equals("") && paramMap.get("bmajor")!=null){
			book.setBumajor(paramMap.get("bmajor"));
		}
		if(paramMap.get("editorValue")!= null && !paramMap.get("bmajor").equals("") ){
			book.setBcontent(paramMap.get("editorValue"));
		}
			try {
				int count = bookBiz.insert(book);
				if(count > 0){
					result = Result.success("添加成功!!!");
				}else{
					result = Result.failure("添加失败!!!");
				}
				Gson gson = new Gson();
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			} catch (BizException e) {
				e.printStackTrace();
			}
	}
	//删除
	public void  delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Book book = new Book();
		PrintWriter out = response.getWriter();
		if(request.getParameter("bid")!= null && !request.getParameter("bid").isEmpty()){
			book.setBid(Long.parseLong(request.getParameter("bid").toLowerCase()));
		}
		try {
			int i = bookBiz.delete(book);
			String json = "{code:'"+i+"'}";
			out.print(json);
		} catch (BizException e) {
			e.printStackTrace();
		}
	}
	//更新
	public void  update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Book bookNew = new Book();
		Book bookOld = new Book();
		PrintWriter out = response.getWriter();
		if(request.getParameter("bid")!= null && !request.getParameter("bid").isEmpty()){
			bookOld.setBid(Long.parseLong(request.getParameter("bid").toLowerCase()));
		}
		if(request.getParameter("bstate")!= null && !request.getParameter("bstate").isEmpty()){
			bookNew.setBstate(Integer.parseInt(request.getParameter("bstate").toString()));
		}
		try {
			Book book = bookBiz.selectSingle(bookOld);
			if(book.getBstate() ==1 ||book.getBstate() ==2){
				int code = bookBiz.update(bookNew,bookOld);
				String json = "{code:'"+code+"'}";
				out.print(json);
			}
			else{
				String json = "{code:'-1'}";
				out.print(json);
			}
		} catch (BizException e) {
			e.printStackTrace();
		}
	}
	//上传图片
	public void uploadImage(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 创建文件上传对象
				SmartUpload su = new SmartUpload();
				// 初始化，传入页面上下文对象
				su.initialize(getServletConfig(), request, response);
				// 设置上传的配置信息
				// 限定文件名后缀
				su.setAllowedFilesList("jpg,png,gif,bmp");
				// 限定大小
				su.setMaxFileSize(1024 * 1024 * 10);
				// 执行上传
				su.upload();
				//判断是否有上传文件
				String webPath = null;
				String filename = null;//文件名
				String diskPath = null;//磁盘路径
				if(su.getFiles().getSize() > 0){
					//为防止上传的文件名重复，将文件名修改为唯一
					//获取系统当前时间
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
					Date date = new Date();
					filename = df.format(date);
					Files files = su.getFiles();
					// 使用application（应用上下文对象） web路径 转换成 磁盘路径
					// getServletContext（） === application
					diskPath = getServletContext().getRealPath("/back/upload");
					files.getFile(0).saveAs(diskPath + "/" + filename);
					webPath = "back/upload/" + filename;
				}
				Result result;
				if(webPath !=null || !webPath.isEmpty()){
					result = Result.success("上传图片成功！",webPath);
				}else{
					result = Result.failure("上传图片失败！");
				}
				Gson gson = new Gson();
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
	}
}
