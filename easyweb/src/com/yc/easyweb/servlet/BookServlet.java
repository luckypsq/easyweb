package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.BookType;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.BookTypeBiz;

import sun.invoke.util.BytecodeName;
import sun.util.resources.no.LocaleNames_no_NO_NY;

public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static BookBiz bookBiz = new BookBiz();

	// 查询
	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String bauthor =null;
		String btime =null;
		String btid = null;
		BookBiz bookBiz = new BookBiz();
		Book book = new Book();
		//获取查询条件
		if(request.getParameter("bauthor") != null && !request.getParameter("bauthor").isEmpty()){
			bauthor =request.getParameter("bauthor");	
			book.setBauthor(bauthor);
		}
		if(request.getParameter("btime") != null && !request.getParameter("btime").isEmpty() ){
			btime =request.getParameter("btime");
			book.setBdate(btime);
		}
		if (request.getParameter("btid") != null && !request.getParameter("btid").isEmpty()) {
			btid = request.getParameter("btid");
			book.setBtid(Long.parseLong(request.getParameter("btid")));
		}
		//查询类别
		BookType bookType = new BookType();
		bookType.setBtstate(1);
		BookTypeBiz btBiz = new BookTypeBiz();
		List<BookType> btList = btBiz.selectAll(bookType);
		Map<Long,String> btType = new HashMap<Long,String>();
		for(BookType bt : btList){
			if(bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()){
				btType.put(bt.getBtid(),bt.getBtnamethird());
			}else if(bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()){
				btType.put(bt.getBtid(),bt.getBtnamesecond());
			}else{
				btType.put(bt.getBtid(),bt.getBtname());
			}
		}
		List<Book> bookList = bookBiz.selectAll(book);
		session.setAttribute("bookList", bookList);//保存所有书籍信息
		session.setAttribute("bookType", btType);//保存所有书籍类型信息
		
		if(bauthor != null){
			session.setAttribute("bauthor", bauthor);
		}else{
			session.setAttribute("bauthor", "");
		}
		if(btime != null){
			session.setAttribute("btime", btime);
		}else{
			session.setAttribute("btime", "");
		}
		if(bookList.size() <= 0){
			out.print(0);
		}
	}

	// 添加
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SmartUploadException {
		Book bookNew = new Book();
		String path = this.getServletContext().getContextPath();
		if (request.getParameter("bname") != null && !request.getParameter("bname").isEmpty()) {
			bookNew.setBname(request.getParameter("bname"));
		} else {
			String url = path + "/back/book/picture-add.jsp?msg=" + 2;
			response.sendRedirect(url);
		}
		if (request.getParameter("buniversity") != null && !request.getParameter("buniversity").equals("请选择")) {
			bookNew.setBuniversity(request.getParameter("buniversity"));
		}
		if (request.getParameter("bcollege") != null && !request.getParameter("bcollege").equals("请选择")) {
			bookNew.setBucollege(request.getParameter("bcollege"));
		}
		if (request.getParameter("bmajor") != null && !request.getParameter("bmajor").equals("请选择")) {
			bookNew.setBumajor(request.getParameter("bmajor"));
		}
		if (request.getParameter("bclass") != null && !request.getParameter("bclass").equals("请选择")) {
			bookNew.setBclass(request.getParameter("bclass"));
		}

		if (request.getParameter("btemp") != null && !request.getParameter("btemp").isEmpty()) {
			bookNew.setBtemp(request.getParameter("btemp"));
		}
		// 类别
		if (request.getParameter("btype") != null && !request.getParameter("btype").equals("请选择")) {
			String btid = request.getParameter("btype").toString().split("-")[0];
			bookNew.setBtid(Long.parseLong(btid));
		}
		if (request.getParameter("bauthor") != null && !request.getParameter("bauthor").isEmpty()) {
			bookNew.setBauthor(request.getParameter("bauthor"));
		}
		if (request.getParameter("bprice") != null && !request.getParameter("bprice").isEmpty()) {
			bookNew.setBprice(Double.parseDouble(request.getParameter("bprice")));
		} else {
			String url = path + "/back/book/picture-add.jsp?msg=" + 3;
			response.sendRedirect(url);
		}
		if (request.getParameter("bnum") != null && !request.getParameter("bnum").isEmpty()) {
			bookNew.setBnum(Long.parseLong(request.getParameter("bnum")));
		}
		if (request.getParameter("bdate") != null && !request.getParameter("bdate").isEmpty()) {
			bookNew.setBdate(request.getParameter("bdate"));
		}
		if (request.getParameter("bcontent") != null && !request.getParameter("bcontent").isEmpty()) {
			bookNew.setBcontent(request.getParameter("bcontent").trim());
		}
		if (request.getParameter("img_path") != null && !request.getParameter("img_path").isEmpty()) {
			bookNew.setBimg(request.getParameter("img_path"));
		}
		try {
			int code;
			try {
				code = bookBiz.insert(bookNew);
				String url = null;
				if (code > 0) {
					url = path + "/back/book/picture-add.jsp?msg=" + 1;
				} else {
					url = path + "/back/book/picture-add.jsp?msg=" + 0;
				}
				response.sendRedirect(url);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (BizException e) {
			e.printStackTrace();
		}
	}

	// 删除
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Book book2;
		List<Book> list = new ArrayList<Book>();
		int i;
		if (request.getParameter("bid") != null && !request.getParameter("bid").toString().isEmpty()) {
			if(request.getParameter("bid").equals("all")){
				out.print(0);
				return;
			}else{
				String[] bid = request.getParameter("bid").split("/");
				for (String string : bid) {
					book2 = new Book();
					if (!string.equals("-1") && !string.isEmpty() && string != null) {
						book2.setBid(Long.parseLong(string));
						list.add(book2);
					}
				}
			}
		} else {
			out.print("删除失败！");
			return;
		}
		try {
			if (list.size() != 0) {
				i = bookBiz.deleteMore(list);
				if (i > 0) {
					out.print("删除成功！");
				} else {
					out.print("删除失败！");
				}
			} else {
				out.print("删除失败！");
			}
		} catch (BizException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 更新状态
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book bookNew = new Book();
		Book bookOld = new Book();
		PrintWriter out = response.getWriter();
		if (request.getParameter("bid") != null && !request.getParameter("bid").isEmpty()) {
			bookOld.setBid(Long.parseLong(request.getParameter("bid").toString()));
		}
		if (request.getParameter("bstate") != null && !request.getParameter("bstate").isEmpty()) {
			bookNew.setBstate(Integer.parseInt(request.getParameter("bstate").toString()));
		}
		try {
			Book book = bookBiz.selectSingle(bookOld);
			if (book.getBstate() == 1 || book.getBstate() == 2) {
				int code = bookBiz.update(bookNew, bookOld);
				String json = "{code:'" + code + "'}";
				out.print(json);
			} else {
				String json = "{code:'-1'}";
				out.print(json);
			}
		} catch (BizException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// 上传图片
	public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		// 判断是否有上传文件
		String webPath = null;
		String filename = null;// 文件名
		String diskPath = null;// 磁盘路径
		if (su.getFiles().getSize() > 0) {
			// 为防止上传的文件名重复，将文件名修改为唯一
			// 获取系统当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date date = new Date();
			Files files = su.getFiles();
			filename = df.format(date) + files.getFile(0).getFileName();
			String path = this.getServletContext().getContextPath();
			// 使用application（应用上下文对象） web路径 转换成 磁盘路径
			// getServletContext（） === application
			diskPath = getServletContext().getRealPath("/back/upload");
			files.getFile(0).saveAs(diskPath + "/" + filename);
			webPath = path + "/back/upload/" + filename;
		}
		Result result;
		if (webPath != null && !webPath.isEmpty()) {
			result = Result.success("上传图片成功！", webPath);
		} else {
			result = Result.failure("上传图片失败！");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		// 返回json格式数据
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(json);
	}

	// 更新书本信息
	public void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = this.getServletContext().getContextPath();
		Book bookNew = new Book();
		Book bookOld = new Book();
		// 赋需要修改书籍的id
		if (request.getParameter("bid") != null && !request.getParameter("bid").isEmpty()) {
			bookOld.setBid(Long.parseLong(request.getParameter("bid").toString()));
		} else {
			String url = path + "/back/book/bookEdit.jsp?bid=" + bookOld.getBid() + "&msg=" + 2;
			response.sendRedirect(url);
		}
		// 修改的值
		if (request.getParameter("bname") != null && !request.getParameter("bname").isEmpty()) {
			bookNew.setBname(request.getParameter("bname"));
		}
		if (request.getParameter("buniversity") != null && !request.getParameter("buniversity").equals("请选择")) {
			bookNew.setBuniversity(request.getParameter("buniversity"));
		}
		if (request.getParameter("bcollege") != null && !request.getParameter("bcollege").equals("请选择")) {
			bookNew.setBucollege(request.getParameter("bcollege"));
		}
		if (request.getParameter("bmajor") != null && !request.getParameter("bmajor").equals("请选择")) {
			bookNew.setBumajor(request.getParameter("bmajor"));
		}
		if (request.getParameter("bclass") != null && !request.getParameter("bclass").equals("请选择")) {
			bookNew.setBclass(request.getParameter("bclass"));
		}

		if (request.getParameter("btemp") != null && !request.getParameter("btemp").isEmpty()) {
			bookNew.setBtemp(request.getParameter("btemp"));
		}
		// 类别
		if (request.getParameter("btype") != null && !request.getParameter("btype").equals("请选择")) {
			String btid = request.getParameter("btype").toString().split("-")[0];
			bookNew.setBtid(Long.parseLong(btid));
		}
		if (request.getParameter("bauthor") != null && !request.getParameter("bauthor").isEmpty()) {
			bookNew.setBauthor(request.getParameter("bauthor"));
		}
		if (request.getParameter("bprice") != null && !request.getParameter("bprice").isEmpty()) {
			bookNew.setBprice(Double.parseDouble(request.getParameter("bprice")));
		}
		if (request.getParameter("bnum") != null && !request.getParameter("bnum").isEmpty()) {
			bookNew.setBnum(Long.parseLong(request.getParameter("bnum")));
		}
		if (request.getParameter("bdate") != null && !request.getParameter("bdate").isEmpty()) {
			bookNew.setBdate(request.getParameter("bdate"));
		}
		if (request.getParameter("bcontent") != null && !request.getParameter("bcontent").isEmpty()) {
			bookNew.setBcontent(request.getParameter("bcontent").trim());
		}
		if (request.getParameter("img_path") != null && !request.getParameter("img_path").isEmpty()) {
			bookNew.setBimg(request.getParameter("img_path"));
		}
		try {
			int code = bookBiz.update(bookNew, bookOld);
			String url = null;
			if (code > 0) {
				url = path + "/back/book/bookEdit.jsp?bid=" + bookOld.getBid() + "&msg=" + 1;
			} else {
				url = path + "/back/book/bookEdit.jsp?bid=" + bookOld.getBid() + "&msg=" + 0;
			}
			response.sendRedirect(url);

		} catch (BizException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 更新书本信息页面显示(bookEdit.jsp)
		public void editShow(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();
			 //获取提示信息
			BookBiz bizBook = new BookBiz();
			//获取id
			Book showBook = new Book();
			Book book2 = new Book();
			Long bid=null ;
			if(request.getParameter("bid")!=null && !request.getParameter("bid").isEmpty()){
				bid=Long.parseLong(request.getParameter("bid").toString());
				book2.setBid(bid);
			}
			try {
				showBook = bizBook.selectSingle(book2);
				session.setAttribute("showBookEdit",showBook);
				//展示的数据
				String showType = null;
				//从数据库中查询所有的大学，专业等信息
				Book book = new Book();
				List<Book> bookList_add = bizBook.selectAll(book);
				HashSet<String> bookUniver = new HashSet<String>();
				HashSet<String> bookUcollage = new HashSet<String>();
				HashSet<String> bookUmagor = new HashSet<String>();
				for (Book bookSet : bookList_add) {
					if (null != bookSet.getBuniversity() && !bookSet.getBuniversity().isEmpty()) {
						bookUniver.add(bookSet.getBuniversity());
					}
					if (null != bookSet.getBucollege() && !bookSet.getBucollege().isEmpty()) {
						bookUcollage.add(bookSet.getBucollege());
					}
					if (null != bookSet.getBumajor() && !bookSet.getBumajor().isEmpty()) {
						bookUmagor.add(bookSet.getBumajor());
					}
				}
				BookType bookType = new BookType();
				bookType.setBtstate(1);
				BookTypeBiz btBiz = new BookTypeBiz();
				List<BookType> btList = btBiz.selectAll(bookType);
				HashSet<String> btType = new HashSet<String>();
				session.setAttribute("bookUniverEdit", bookUniver);
				session.setAttribute("bookUcollageEdit", bookUcollage);
				session.setAttribute("bookUmagorEdit", bookUmagor);
				for(BookType bt : btList){
					if(showBook.getBtid() == bt.getBtid()){
						if(bt.getBtnamethird() != null && !bt.getBtnamethird() .isEmpty()){
							showType =bt.getBtid()+"-"+bt.getBtnamethird();
						}else if(bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()){
							showType =bt.getBtid()+"-"+bt.getBtnamesecond();
						}else{
							showType =bt.getBtid()+"-"+bt.getBtname();
						}
					}
					if(bt.getBtnamethird() != null && !bt.getBtnamethird() .isEmpty()){
						btType.add(bt.getBtid()+"-"+bt.getBtnamethird());
					}else if(bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()){
						btType.add(bt.getBtid()+"-"+bt.getBtnamesecond());
					}else{
						btType.add(bt.getBtid()+"-"+bt.getBtname());
					}
				}
				session.setAttribute("showTypeEdit", showType);
				session.setAttribute("btTypeEdit", btType);
			} catch (BizException e) {
				e.printStackTrace();
			}
		}
}
