package com.yc.easyweb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.UserBiz;

public class RegServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBiz();
	private  Gson gson = new Gson();
	private Result result ;
	
	/**
	 * �����û���
	 * @param request
	 * @param response
	 */
	public void checkName(HttpServletRequest request, HttpServletResponse response){
		 User user = new User();
		 HttpSession session = request.getSession();
		String username = request.getParameter("username");
		try {
			if(username != null && !username.isEmpty()){
				user.setUname(username);
			}else{
				//�û�������Ϊ��
				result = Result.failure("�û���Ϊ�գ�����", username);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			String reg = "^[\u4e00-\u9fa5a-zA-Z]{2,20}$";
			if (!username.matches(reg)) {
				result = Result.failure("�û������벻�Ϸ�������",username);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
		
		
			User user2 = userBiz.selectSingle(user);
			if (user2.getUid() != 0) { // ˵���û����Ѿ���ʹ��
				result = Result.failure("�Ѵ��ڸ��û�������",username);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			} 
			
				result = Result.success("���û�������ʹ�ã�����", username);
				String json = gson.toJson(result);
				session.setAttribute("regUname", username);
				
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				
		} catch (BizException e) {
			result = Result.error(e.getMessage(),username);
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
				// TODO Auto-generated catch block
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������",username);
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
				// TODO Auto-generated catch block
			}			
			e.printStackTrace();
		}
	}

	/*
	 * ����绰
	 * @param request
	 * @param response
	 */
	public void checkPhone(HttpServletRequest request, HttpServletResponse response){
		 HttpSession session = request.getSession();
		String uphone = request.getParameter("uphone");
		String regphone = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		 User user = new User();
		 try {
			if(uphone != null && !uphone.isEmpty()){
				if (!uphone.matches(regphone)) {
					result = Result.failure("�绰���벻�Ϸ�������",uphone);
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return ;
				} 
				user.setUphone(uphone);
				User user2 = userBiz.selectSingle(user);
				if(user2.getUid() != 0){
					result = Result.failure("�绰�����ѱ�ʹ�ã�����",uphone);
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return ;
				}
				result = Result.success("�õ绰�������ʹ��", uphone);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				session.setAttribute("regUphone", uphone);
			}else{
				result = Result.failure("������绰���룡����",uphone);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
		 } catch (BizException e) {
			 result = Result.error(e.getMessage(),uphone);
			 String json = gson.toJson(result);
			 response.setContentType("application/json;charset=UTF-8");
			 try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
		} catch (IOException e) {
			 result = Result.error("ҵ��æ,���Եȼ������ٲ���");
			 String json = gson.toJson(result);
			 response.setContentType("application/json;charset=UTF-8");
			 try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//�������
	public void checkEmail(HttpServletRequest request, HttpServletResponse response){
		
		String uemail = request.getParameter("uemail");
		HttpSession session = request.getSession();
		User user = new User();
		String regemail = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		try {
			if(uemail !=null && !uemail.isEmpty()){
				user.setUemail(uemail);
			}else{
				result = Result.failure("���������䣡����",uemail);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			//�����ʽ���Ϸ���
			if (!uemail.matches(regemail)) {
				result = Result.failure("�����ʽ���Ϸ�������",uemail);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			User user2 = userBiz.selectSingle(user);
			//�������ѱ�ע�ᣡ
			if (user2.getUid() != 0) {
				result = Result.failure("�������ѱ�ע�ᣡ����",uemail);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			} 
			//���������ע��!
			session.setAttribute("regUemail", uemail);
			result = Result.success("���������ע��",uemail);
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
		} catch (BizException e) {
			result = Result.error(e.getMessage(),uemail);
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
	}
	
	//�������
	public void checkPassword(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String upassword = request.getParameter("upassword");
		String regpassword = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
		try {
			if(upassword != null && !upassword.isEmpty()){
				//���Ϸ�
				if (!upassword.matches(regpassword)) {
					result = Result.failure("���벻�Ϸ�,���������룡����");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
				} 
				//����ʹ��
				session.setAttribute("regUpwd", upassword);
				result = Result.success("���������ʹ�ã�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}else{
				//����Ϊ��
				result = Result.failure("���������룡����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
	}

	//ȷ������
	public void checkPassword01(HttpServletRequest request, HttpServletResponse response){
		
		String confirm = request.getParameter("passwordsignup_confirm");
		String upassword = request.getParameter("upassword");
		try {
			if(upassword == null ||  upassword.isEmpty()){
				result = Result.failure("���������룡����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			if(confirm == null ||  confirm.isEmpty()){
				result = Result.failure("��ȷ�����룡����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			if ( !upassword.equals(confirm)) {
				result = Result.failure("���벻һ�£�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			} 
			result = Result.success("����һ�£�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
	}

	/*
	 * ע����֤
	 */
	public void checkReg(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		
		String uname = (String)session.getAttribute("regUname");
		String uphone = (String)session.getAttribute("regUphone");
		String upassword = (String)session.getAttribute("regUpwd");
		String uemail = (String)session.getAttribute("regUemail");
		String check = "1";
		User user = new User();
		if(uname != null && !uname.isEmpty()){
			user.setUname(uname);
			check = check +"/1";
		}else{
			check = check +"/-1";
		}
		
		if(uphone != null && !uphone.isEmpty()){
			user.setUphone(uphone);
			check = check +"/1";
		}else{
			check = check +"/-1";//�绰δ����򲻺Ϸ�
		}
		
		if(upassword != null && !upassword.isEmpty()){
			user.setUpassword(upassword);
			check = check +"/1";
		}else{
			check = check +"/-1";//����δ����򲻺Ϸ�
		}
		
		if(uemail != null && !uemail.isEmpty()){
			user.setUemail(uemail);
			check = check +"/1";
		}else{
			check = check +"/-1";//����
		}
		String university = request.getParameter("university");
		String ucollege = request.getParameter("ucollege");
		String umajor = request.getParameter("umajor");
		
		if(!university.equals("��ѡ����Ĵ�ѧ")){
			user.setUniversity(university);
		}
		if(!ucollege.equals("��ѡ�����ѧԺ")){
			user.setUcollege(ucollege);
		}
		if(!umajor.equals("��ѡ�����רҵ")){
			user.setUmajor(umajor);
		}
		try {
			if(!check.equals("1/1/1/1/1")){
				result = Result.lack("δ��ȷ������Ϣ������", check);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			// ��ȡϵͳ��ǰʱ��
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			user.setUtime(df.format(date));
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			String min = uuid.substring(0, 10);
			user.setUminname("�û�"+min);
			user.setUtype(2);
			int code = userBiz.insert(user);
			if (code <= 0) {
				result = Result.failure("ע��ʧ�ܣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
			
			result = Result.success("ע��ɹ�������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			
		} catch (SQLException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		} catch (BizException e) {
			result = Result.error(e.getMessage());
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
	}

	public void showUser(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		BookBiz bookBiz = new BookBiz();
		Book book = new Book();
		HashSet<String> bookUniver = new HashSet<String>();
		HashSet<String> bookUcollage = new HashSet<String>();
		HashSet<String> bookUmagor = new HashSet<String>();
		List<Book> bookList_add;
		try {
			bookList_add = bookBiz.selectAll(book);
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
			session.setAttribute("userUni", bookUniver);
			session.setAttribute("userUcol", bookUcollage);
			session.setAttribute("userUmar", bookUmagor);
			
			if(bookList_add.size() >0){
				result = Result.success("�����Ѽ��أ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			result = Result.failure("����δ����,��ˢ��ҳ�棡����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return ;
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
		/**
			getOutputStream() has already been called for this response
			��JSP�����ͼƬ������̨���׳����쳣������������´��룬�������
			String path = this.getServletContext().getContextPath();
		// ���������֤��
		String verifyCode = VerifyCodeUtils.outputImage(response);
		// ����֤����ӵ��Ự�У�ע�⣺�ڻỰ�б������֤������� vscode
		session.setAttribute("vcode", verifyCode);
		out.clearError();
		out = pageContext.pushBody();
		String url = path+"/images";
		// ����ͼƬĿ¼
		File dir = new File(url);
		// ����ͼƬ�ļ�
		File file = new File(dir, verifyCode + ".jpg");
		String img = verifyCode + ".jpg";
		session.setAttribute("codeImg", img);
		*/
		
	}
}
