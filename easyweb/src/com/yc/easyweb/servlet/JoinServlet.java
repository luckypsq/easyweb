package com.yc.easyweb.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.BookType;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.bean.Usercontrol;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.BookTypeBiz;
import com.yc.easyweb.biz.UserBiz;
import com.yc.easyweb.biz.UsercontrolBiz;

public class JoinServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBiz();
	
	public void join(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		/*// ��ȡ��֤��
		String vcode01 = (String) session.getAttribute("vcode");
		String vcode02 = request.getParameter("vcode");
		if(vcode01 != null && !vcode01.isEmpty() && !vcode02.isEmpty() && vcode02 != null){
			if (!vcode01.equalsIgnoreCase(vcode02)) {
				out.print(-2);//��֤�����
				return;
			} 
		}
		*/
				
		// ���� �û��� �� ����
		String username = request.getParameter("uname");
		String password = request.getParameter("upassword");
		String loginkeeping = request.getParameter("loginkeeping");//��֤��
		session.setAttribute("loginUname", username);
		User user = new User();
		Result result;
		try {
			//����û�������
			if(username != null && !username.isEmpty()){
				user.setUname(username);
			}else{
				result = Result.failure("�û���Ϊ�գ�����", username);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			//�����������
			if(password != null && !password.isEmpty()){
				user.setUpassword(password);
			}else{
				result = Result.failure("����Ϊ�գ�����");
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			
		
			User userShow = userBiz.selectSingle(user);//�����û���Ϣ
			if(userShow.getUid() == 0){
				//�û���������
				result =Result.failure("�û��������ڣ�����", username);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			if(userShow.getUstate() != 1){
				result = Result.failure("���ѱ�������˺ű�ɾ��������", username);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			session.setAttribute("loginedUser", userShow);
			// ��ȡϵͳ��ǰʱ��
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
			Date date = new Date();
			String[] dateStr = df.format(date).split("/");
			//����¼ʱ�����Ự��
			session.setAttribute("date", dateStr);
			String path = this.getServletContext().getContextPath();
			//����Ŀ���ַ���Ự��
			session.setAttribute("path",path);
			//������״̬�����ͷ���Ự��
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
			
			BookBiz bookBiz = new BookBiz();
			Book book = new Book();
			List<Book> bookList_add = bookBiz.selectAll(book);
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
			session.setAttribute("userUni", bookUniver);
			session.setAttribute("userUcol", bookUcollage);
			session.setAttribute("userUmar", bookUmagor);
			
			BookType bookType = new BookType();
			bookType.setBtstate(1);
			BookTypeBiz btBiz = new BookTypeBiz();
			
			List<BookType> btList = btBiz.selectAll(bookType);
			HashSet<String> btType = new HashSet<String>();
			for(BookType bt : btList){
				if(bt.getBtnamethird() != null && !bt.getBtnamethird() .isEmpty()){
					btType.add(bt.getBtid()+"-"+bt.getBtnamethird());
				}else if(bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()){
					btType.add(bt.getBtid()+"-"+bt.getBtnamesecond());
				}else{
					btType.add(bt.getBtid()+"-"+bt.getBtname());
				}
			}
			session.setAttribute("btTypeEdit", btType);
			
			
			String adminType = null;
			if(userShow.getUtype() == 1){
			 	adminType = "��������Ա";
			}else{
			 	adminType = "��ͨ����Ա";
			}
			request.getSession().setAttribute("adminType", adminType);
			
			//�����¼
			/**	 
			  Cookie cookie = new Cookie("loginedUsername", URLEncoder.encode(username, "GBK")); // Ĭ��Ϊ��ʱCookie,MaxAge<0
				// ����һ��cookie����
				Cookie cookie01 = new Cookie("loginedPassword", password);	// ���Cookie�����ĵ���������
				cookie.setMaxAge(60);// ������Чʱ�� 1����f
				cookie01.setMaxAge(60);
				response.addCookie(cookie);// ��cookie��ӵ���Ӧ������
				response.addCookie(cookie01);
				//����������л�ȡ��������ͻط�������cookie����
			     Cookie[] cookies=request.getCookies();
			     //Cookie loginedUserCookie=null;
			     Cookie loginedPasswordCookie=null;
			     if(cookies!=null){
			    	 for(Cookie cookie:cookies){
			    		 if(cookie.getName().equalsIgnoreCase("loginedUsername")) {
			    			 //�����ȡ������������
			    			 request.setAttribute("username",URLDecoder.decode(cookie.getValue(),"GBK"));
			    		 }
			    		 if("loginedPassword".equals(cookie.getName())){
			    			 loginedPasswordCookie=cookie;
			    		 }
			     }
     			}
			*/
			
			if (userShow.getUtype() != 1 && userShow.getUtype() != 5) {
				result = Result.success("�û���¼�ɹ�������", username);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			} else {
				UsercontrolBiz ucBiz = new UsercontrolBiz();
				Usercontrol usercontrolOld = new Usercontrol();
				usercontrolOld.setUid(userShow.getUid());
				List<Usercontrol> controlList = ucBiz.selectAll(usercontrolOld);
				
				List<Long> conList = new ArrayList<Long>();
				if(controlList != null){
					for(Usercontrol ucon : controlList){
					 	conList.add(ucon.getConid());
					}
				}
				session.setAttribute("adminControl", conList);
				result = new Result("����Ա��¼�ɹ�������",2);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
		} catch (BizException e) {
			result = Result.error(e.getMessage());
			String json = gson.toJson(result);
			// ����json��ʽ����
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,�����Ե�һ����ٲ���������");
			String json = gson.toJson(result);
			// ����json��ʽ����
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		}
	}
}
