package com.yc.easyweb.servlet.pq;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.bean.Usercontrol;
import com.yc.easyweb.biz.UsercontrolBiz;
import com.yc.easyweb.common.DbHelper;
import com.yc.easyweb.servlet.BaseServlet;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/JoinServlet.s")
public class JoinServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �������������ַ������룬���Լ������Ĳ�����������������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		// ���� �û��� �� ����
		String username = request.getParameter("uname");
		String password = request.getParameter("upassword");
		String loginkeeping = request.getParameter("loginkeeping");
		// ��ȡ��֤��
		String vcode01 = (String) request.getSession().getAttribute("vcode");
		String vcode02 = request.getParameter("vcode");
		String sql = "select * from user where uname=? and upassword=?";
		List<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(password);
		User user = DbHelper.selectSingle(sql, params, User.class);
		// Ӧ�������Ķ���洢�û���Ϣ
		ServletContext userContext = getServletContext();
		request.setAttribute("msg", "");
		userContext.setAttribute("user", user);
		if (!vcode01.equalsIgnoreCase(vcode02)) {
			PrintWriter out = response.getWriter();
			out.print("��֤�����!");
			return;
		} else if (user.getUid() != 0) {
			if (loginkeeping != null) {
				HttpSession session = request.getSession();
				// ����һ��cookie����
				Cookie cookie = new Cookie("loginedUsername", URLEncoder.encode(username, "GBK")); // Ĭ��Ϊ��ʱCookie,MaxAge<0
				// ���Cookie�����ĵ���������
				Cookie cookie01 = new Cookie("loginedPassword", password);
				// ������Чʱ�� 1����f
				cookie.setMaxAge(60);
				cookie01.setMaxAge(60);
				// ��cookie��ӵ���Ӧ������
				response.addCookie(cookie);
				response.addCookie(cookie01);
				request.getSession().setAttribute("loginedUser", user);
				// ��ȡϵͳ��ǰʱ��
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
				Date date = new Date();
				String[] dateStr = df.format(date).split("/");
				//����¼ʱ�����Ự��
				request.getSession().setAttribute("date", dateStr);
				String path = this.getServletContext().getContextPath();
				//����Ŀ���ַ���Ự��
				request.getSession().setAttribute("path",path);
				String[] userType = {"","","��ͨ�û�","��ͨ��Ա","��ʯ��Ա"};
				String[] uType = {"","","2-��ͨ�û�","3-��ͨ��Ա","4-��ʯ��Ա"};
				session.setAttribute("userType", userType);
				session.setAttribute("uType", uType);
				String[] userSex = {"����","��","Ů"};
				session.setAttribute("userSex", userSex);
				String[] adminState = {"","������","�Ѷ���","��ɾ��"};
				session.setAttribute("adminStateC", adminState);// �洢���д��ڵĹ���Ա��Ϣ
				String[] bookState = {"δ�ϼ�","���ϼ�","���¼�","����","��˲�ͨ��","δ���"};
				session.setAttribute("bookState", bookState);
				String[] eorderState = {"","������","������","�ѷ���","������","���˿�","�ѽ���","�˻�ʧ��"};
				session.setAttribute("eoderState", eorderState);
				
				String[] eorderMessage = {"","�ȴ�֧��","�ȴ�����","�ȴ�����","�ȴ�����","�˿�ɹ�","���������","����������"};
				session.setAttribute("eoderMessage", eorderMessage);
				
				if (user.getUtype() != 1 && user.getUtype() != 5) {
					PrintWriter out = response.getWriter();
					out.print("�û���¼�ɹ�!");
				} else {
					PrintWriter out = response.getWriter();
					String adminType = null;
					if(user.getUtype() == 1){
			 			adminType = "��������Ա";
			 		}else{
			 			adminType = "��ͨ����Ա";
			 		}
					//ҳͷչʾ
					 User loginUser = (User)session.getAttribute("loginedUser");
					 Usercontrol usercontrolOld = new Usercontrol();
					 List<Usercontrol> controlList = null;
					 List<Long> conList = new ArrayList<Long>();
					 UsercontrolBiz usercontrolBiz = new UsercontrolBiz();
				 	if(loginUser != null){
				 		if(loginUser.getUid() != 0){
				 			usercontrolOld.setUid(loginUser.getUid());
				 			controlList = usercontrolBiz.selectAll(usercontrolOld);
				 		}
				 	}
				 	if(controlList != null){
				 		for(Usercontrol ucon : controlList){
				 			conList.add(ucon.getConid());
				 		}
				 	}
					session.setAttribute("adminControl", conList);
					request.getSession().setAttribute("adminType", adminType);
					out.print("����Ա��¼�ɹ�!");
				}
			}
		} else if (user.getUid() == 0) {
			PrintWriter out = response.getWriter();
			out.print("�û������������!");
		}
	}
}
