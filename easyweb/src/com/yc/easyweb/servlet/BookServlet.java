package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;

import sun.util.resources.no.LocaleNames_no_NO_NY;

public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static BookBiz bookBiz = new BookBiz();

	// ��ѯ
	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	}

	// ���
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
		if (request.getParameter("buniversity") != null && !request.getParameter("buniversity").equals("��ѡ��")) {
			bookNew.setBuniversity(request.getParameter("buniversity"));
		}
		if (request.getParameter("bcollege") != null && !request.getParameter("bcollege").equals("��ѡ��")) {
			bookNew.setBucollege(request.getParameter("bcollege"));
		}
		if (request.getParameter("bmajor") != null && !request.getParameter("bmajor").equals("��ѡ��")) {
			bookNew.setBumajor(request.getParameter("bmajor"));
		}
		if (request.getParameter("bclass") != null && !request.getParameter("bclass").equals("��ѡ��")) {
			bookNew.setBclass(request.getParameter("bclass"));
		}

		if (request.getParameter("btemp") != null && !request.getParameter("btemp").isEmpty()) {
			bookNew.setBtemp(request.getParameter("btemp"));
		}
		// ���
		if (request.getParameter("btype") != null && !request.getParameter("btype").equals("��ѡ��")) {
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

	// ɾ��
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
			out.print("ɾ��ʧ�ܣ�");
			return;
		}
		try {
			if (list.size() != 0) {
				i = bookBiz.deleteMore(list);
				if (i > 0) {
					out.print("ɾ���ɹ���");
				} else {
					out.print("ɾ��ʧ�ܣ�");
				}
			} else {
				out.print("ɾ��ʧ�ܣ�");
			}
		} catch (BizException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ����״̬
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

	// �ϴ�ͼƬ
	public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// �����ļ��ϴ�����
		SmartUpload su = new SmartUpload();
		// ��ʼ��������ҳ�������Ķ���
		su.initialize(getServletConfig(), request, response);
		// �����ϴ���������Ϣ
		// �޶��ļ�����׺
		su.setAllowedFilesList("jpg,png,gif,bmp");
		// �޶���С
		su.setMaxFileSize(1024 * 1024 * 10);
		// ִ���ϴ�
		su.upload();
		// �ж��Ƿ����ϴ��ļ�
		String webPath = null;
		String filename = null;// �ļ���
		String diskPath = null;// ����·��
		if (su.getFiles().getSize() > 0) {
			// Ϊ��ֹ�ϴ����ļ����ظ������ļ����޸�ΪΨһ
			// ��ȡϵͳ��ǰʱ��
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date date = new Date();
			Files files = su.getFiles();
			filename = df.format(date) + files.getFile(0).getFileName();
			String path = this.getServletContext().getContextPath();
			// ʹ��application��Ӧ�������Ķ��� web·�� ת���� ����·��
			// getServletContext���� === application
			diskPath = getServletContext().getRealPath("/back/upload");
			files.getFile(0).saveAs(diskPath + "/" + filename);
			webPath = path + "/back/upload/" + filename;
		}
		Result result;
		if (webPath != null && !webPath.isEmpty()) {
			result = Result.success("�ϴ�ͼƬ�ɹ���", webPath);
		} else {
			result = Result.failure("�ϴ�ͼƬʧ�ܣ�");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		// ����json��ʽ����
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(json);
	}

	// �����鱾��Ϣ
	public void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String path = this.getServletContext().getContextPath();
		Book bookNew = new Book();
		Book bookOld = new Book();
		// ����Ҫ�޸��鼮��id
		if (request.getParameter("bid") != null && !request.getParameter("bid").isEmpty()) {
			bookOld.setBid(Long.parseLong(request.getParameter("bid").toString()));
		} else {
			String url = path + "/back/book/bookEdit.jsp?bid=" + bookOld.getBid() + "&msg=" + 2;
			response.sendRedirect(url);
		}
		// �޸ĵ�ֵ
		if (request.getParameter("bname") != null && !request.getParameter("bname").isEmpty()) {
			bookNew.setBname(request.getParameter("bname"));
		}
		if (request.getParameter("buniversity") != null && !request.getParameter("buniversity").equals("��ѡ��")) {
			bookNew.setBuniversity(request.getParameter("buniversity"));
		}
		if (request.getParameter("bcollege") != null && !request.getParameter("bcollege").equals("��ѡ��")) {
			bookNew.setBucollege(request.getParameter("bcollege"));
		}
		if (request.getParameter("bmajor") != null && !request.getParameter("bmajor").equals("��ѡ��")) {
			bookNew.setBumajor(request.getParameter("bmajor"));
		}
		if (request.getParameter("bclass") != null && !request.getParameter("bclass").equals("��ѡ��")) {
			bookNew.setBclass(request.getParameter("bclass"));
		}

		if (request.getParameter("btemp") != null && !request.getParameter("btemp").isEmpty()) {
			bookNew.setBtemp(request.getParameter("btemp"));
		}
		// ���
		if (request.getParameter("btype") != null && !request.getParameter("btype").equals("��ѡ��")) {
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

}
