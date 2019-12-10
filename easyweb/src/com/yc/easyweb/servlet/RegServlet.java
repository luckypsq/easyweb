package com.yc.easyweb.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.UserBiz;
import com.yc.easyweb.util.VerifyCodeUtils;

public class RegServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBiz();

	public void checkName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 User user = new User();
		 PrintWriter out = response.getWriter();
		 HttpSession session = request.getSession();
		String username = request.getParameter("username");
		if(username != null && !username.isEmpty()){
			user.setUname(username);
		}else{
			out.print(-1);//�û�������Ϊ��
			return ;
		}
		String reg = "^[\u4e00-\u9fa5a-zA-Z]{0,20}$";
		if (!username.matches(reg)) {
			out.print(-3);
			return ;
		}
		
		try {
			User user2 = userBiz.selectSingle(user);
			if (user2.getUid() != 0) { // ˵���û����Ѿ���ʹ��
				out.print(-2);
			} else {
				out.print(1);//����ʹ��
				session.setAttribute("regUname", username);
			}
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkPhone(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 HttpSession session = request.getSession();
		String uphone = request.getParameter("uphone");
		String regphone = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		 User user = new User();
		 try {
			if(uphone != null && !uphone.isEmpty()){
				if (!uphone.matches(regphone)) {
					out.print(-2);//���Ϸ�
					return ;
				} 
				User user2 = userBiz.selectSingle(user);
				if(user2.getUid() != 0){
					out.print(-3);//�Ѵ���
					return ;
				}
				out.print(1);//����ʹ��
				session.setAttribute("regUphone", uphone);
			}else{
				out.print(-1);//����Ϊ��
			}
		 } catch (BizException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	public void checkEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uemail = request.getParameter("uemail");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String regemail = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		try {
			if(uemail !=null && !uemail.isEmpty()){
				User user = new User();
				user.setUemail(uemail);
				User user2 = userBiz.selectSingle(user);
				if (!uemail.matches(regemail)) {
					out.print(-3);//�����ʽ���Ϸ���
				} else if (user2.getUid() != 0) {
					out.print(-2);//�������ѱ�ע�ᣡ
				} else {
					out.print(1);//���������ע��!
					session.setAttribute("regUemail", uemail);
				}
			}else{
				out.print(-1);//������
			}
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checkPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String upassword = request.getParameter("upassword");
		String regpassword = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
		if(upassword != null && !upassword.isEmpty()){
			if (!upassword.matches(regpassword)) {
				out.print(-2);//���Ϸ�
			} else {
				out.print(1);//����ʹ��
				session.setAttribute("regUpwd", upassword);
			}
		}else{
			out.print(-1);//����Ϊ��
		}
		
	}

	public void checkPassword01(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String confirm = request.getParameter("passwordsignup_confirm");
		String upassword = request.getParameter("upassword");
		if(upassword == null ||  upassword.isEmpty()){
			out.print(-1);//�����������룡����
			return ;
		}
		if(confirm == null ||  confirm.isEmpty()){
			out.print(-2);//��ȷ�����룡����
			return ;
		}
		if (upassword.equals(confirm)) {
			out.print(1);//����һ��
		} else {
			out.print(-3);//���벻һ��
		}
	}

	public void checkReg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
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
		
		if(!check.equals("1/1/1/1/1")){
			out.print(check);
			return ;
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
			int code = userBiz.insert(user);
			if (code >= 1) {
				out.print(1);//�ɹ�
			} else {
				out.print(-5);//ʧ��
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String path = this.getServletContext().getContextPath();
		// ���������֤��
		String verifyCode = VerifyCodeUtils.outputImage(response);
		// ����֤����ӵ��Ự�У�ע�⣺�ڻỰ�б������֤������� vscode
		session.setAttribute("vcode", verifyCode);
		/**
			getOutputStream() has already been called for this response
			��JSP�����ͼƬ������̨���׳����쳣������������´��룬�������
			out.clear();
			out = pageContext.pushBody();
		*/
		String url = path+"/images";
		// ����ͼƬĿ¼
		File dir = new File(url);
		// ����ͼƬ�ļ�
		File file = new File(dir, verifyCode + ".jpg");
		String img = verifyCode + ".jpg";
		session.setAttribute("codeImg", img);
	}
}
