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
	//��ѯ
	public void  query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
	}
	//���
	public void  add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SmartUploadException {
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
				// smartupload �������������˷�װ
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
		if(!paramMap.get("buniversity").equals("��???��") && paramMap.get("buniversity")!=null){
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
					result = Result.success("��ӳɹ�!!!");
				}else{
					result = Result.failure("���ʧ��!!!");
				}
				Gson gson = new Gson();
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			} catch (BizException e) {
				e.printStackTrace();
			}
	}
	//ɾ��
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
	//����
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
	//�ϴ�ͼƬ
	public void uploadImage(HttpServletRequest request, HttpServletResponse response)throws Exception{
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
				//�ж��Ƿ����ϴ��ļ�
				String webPath = null;
				String filename = null;//�ļ���
				String diskPath = null;//����·��
				if(su.getFiles().getSize() > 0){
					//Ϊ��ֹ�ϴ����ļ����ظ������ļ����޸�ΪΨһ
					//��ȡϵͳ��ǰʱ��
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
					Date date = new Date();
					filename = df.format(date);
					Files files = su.getFiles();
					// ʹ��application��Ӧ�������Ķ��� web·�� ת���� ����·��
					// getServletContext���� === application
					diskPath = getServletContext().getRealPath("/back/upload");
					files.getFile(0).saveAs(diskPath + "/" + filename);
					webPath = "back/upload/" + filename;
				}
				Result result;
				if(webPath !=null || !webPath.isEmpty()){
					result = Result.success("�ϴ�ͼƬ�ɹ���",webPath);
				}else{
					result = Result.failure("�ϴ�ͼƬʧ�ܣ�");
				}
				Gson gson = new Gson();
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
	}
}
