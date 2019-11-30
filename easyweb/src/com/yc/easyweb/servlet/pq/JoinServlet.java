package com.yc.easyweb.servlet.pq;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/JoinServlet.s")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        // �������������ַ������룬���Լ������Ĳ�����������������
		 request.setCharacterEncoding("utf-8");  
	 	  response.setContentType("text/html; charset=UTF-8");
                // ���� �û��� �� ����
                String username = request.getParameter("uname");
                String password = request.getParameter("upassword");
                String loginkeeping = request.getParameter("loginkeeping");
                //��ȡ��֤��
                String vcode01=(String) request.getSession().getAttribute("vcode");
                String vcode02=request.getParameter("vcode");
                String sql = "select * from user where uname=? and upassword=?";
                List<Object> params=new ArrayList<Object>();
                params.add(username);
                params.add(password);
				try {
					User user=DbHelper.selectSingle(sql, params, User.class);
					//Ӧ�������Ķ���洢�û���Ϣ
					ServletContext userContext = getServletContext();
					 request.setAttribute("msg", "");
					userContext.setAttribute("user", user);
					if(!vcode01.equalsIgnoreCase(vcode02)){
						PrintWriter out = response.getWriter();
						out.print("��֤�����!");
						return ;
					}
					else if (user.getUid() != 0) {
                        if(loginkeeping!=null){
							//����һ��cookie����
                        	Cookie cookie=new Cookie("loginedUsername",URLEncoder.encode (username,"GBK")); //Ĭ��Ϊ��ʱCookie,MaxAge<0 
                        	 //���Cookie�����ĵ���������
                        	Cookie cookie01=new Cookie("loginedPassword", password);
                        	//������Чʱ��  1����f  
                        	cookie.setMaxAge(60);
                        	cookie01.setMaxAge(60);
                        	//��cookie��ӵ���Ӧ������
                        	response.addCookie(cookie);
                        	response.addCookie(cookie01);
                        	 request.getSession().setAttribute("loginedUser", user);
     						if(user.getUtype() != 1 && user.getUtype()!= 5){
     							PrintWriter out = response.getWriter();
     							out.print("�û���¼�ɹ�!");
     						}else{
     							PrintWriter out = response.getWriter();
     							out.print("����Ա��¼�ɹ�!");
     						}
						}
					} else if(user.getUid()==0) {
						PrintWriter out = response.getWriter();
						out.print("�û������������!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
                

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
