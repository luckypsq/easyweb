package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.UserBiz;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBiz();
	private Gson gson = new Gson();
	private Result result;

	// ���
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InvocationTargetException {
		User user = new User();
		HttpSession session = request.getSession();
		String name = request.getParameter("username");
		String phone = request.getParameter("uphone");
		String email = request.getParameter("uemail");
		String uid = request.getParameter("uid");
		String url = "/back/user/userAdd.jsp?name=" + name + "&phone=" + phone + "&email=" + email;
		if (!uid.equals("-1")) {
			User user2 = new User();
			user2.setUid(Long.parseLong(uid));
			try {
				User userShow = userBiz.selectSingle(user2);
				session.setAttribute("userShowAdd", userShow);
			} catch (BizException e) {
				e.printStackTrace();
			}
		} else {
			Map<String, String> show = new HashMap<String, String>();
			show.put("uname", name);
			show.put("uphone", phone);
			show.put("uemail", email);
			session.setAttribute("userShowAdd", show);
		}
		// ��֤�û��� 1.�Ƿ�Ϊ�� 2. �Ƿ�Ϸ� 3.�Ƿ����
		if (name != null && !name.isEmpty()) {
			String reg = "^[\u4e00-\u9fa5a-zA-Z]{0,20}$";
			if (name.matches(reg)) {
				User user2 = new User();
				user2.setUname(name);
				try {
					User user3 = userBiz.selectSingle(user2);
					if (user3.getUid() == 0) {
						user.setUname(name);
					} else if (user3.getUid() != 0 && uid.equals("-1")) {
						url = url + "&msg=" + 5;
						request.getRequestDispatcher(url).forward(request, response);
						return;
					}
				} catch (BizException e) {

					e.printStackTrace();
				}
			} else {
				url = url + "&msg=" + 4;
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		} else {
			url = url + "&msg=" + 2;
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		if (request.getParameter("bate") != null && !request.getParameter("bate").isEmpty()) {
			user.setUtime(request.getParameter("bate"));
		}
		// ��֤�绰 1.�Ƿ�Ϊ�� 2.�Ƿ�Ϸ�
		if (phone != null && !phone.isEmpty()) {
			String reg = "^[0-9]{11}$";
			if (phone.matches(reg)) {
				user.setUphone(phone);
			} else {
				url = url + "&msg=" + 6;
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		} else {
			url = url + "&msg=" + 3;
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		if (email != null && !email.isEmpty()) {
			String reg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
			if (email.matches(reg)) {
				user.setUemail(email);
			} else {
				url = url + "&msg=" + 7;
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		}
		if (!request.getParameter("utype").equals("��ѡ��")) {
			String[] utype1 = request.getParameter("utype").split("-");
			user.setUtype(Integer.parseInt(utype1[0]));
		} else {
			user.setUtype(2);
		}
		if (!uid.equals("-1")) {
			User userOld = new User();
			userOld.setUid(Long.parseLong(uid));
			try {
				int j = userBiz.update(user, userOld);
				if (j > 0) {
					url = url + "&msg=" + 10;
				} else {
					url = url + "&msg=" + 9;
				}
				request.getRequestDispatcher(url).forward(request, response);
				return;
			} catch (SQLException e) {

				e.printStackTrace();
			} catch (BizException e) {

				e.printStackTrace();
			}
		}
		try {
			user.setUpassword("aaaa8888");
			user.setUstate(1);
			int i = userBiz.insert(user);
			if (i > 0) {
				url = url + "&msg=" + 1;
			} else {
				url = url + "&msg=" + 0;
			}
			request.getRequestDispatcher(url).forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (BizException e) {

			e.printStackTrace();
		}
	}

	// ɾ��
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		User user;
		User user2 = new User();
		List<User> list = new ArrayList<User>();
		int i;
		if (request.getParameter("uid") != null && !request.getParameter("uid").toString().isEmpty()) {
			String[] uid = request.getParameter("uid").split("/");
			if (uid.length == 1) {
				if (!uid[0].isEmpty() && uid[0] != null) {
					user2.setUid(Long.parseLong(uid[0]));
					User user3;
					try {
						user3 = userBiz.selectSingle(user2);
						if (user3.getUstate() == 3) {
							out.print(2);
							return;
						}
					} catch (BizException e) {

						e.printStackTrace();
					}
				}
			}
			for (String string : uid) {
				user = new User();
				if (!string.isEmpty() && string != null) {
					user.setUid(Long.parseLong(string));
					user.setUstate(3);
					list.add(user);
				}
			}
		} else {
			out.print(0);
			return;
		}
		try {
			if (list.size() != 0) {
				i = userBiz.update(list);
				if (i > 0) {
					out.print(1);
				} else {
					out.print(0);
				}
			} else {
				out.print(0);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (BizException e) {

			e.printStackTrace();
		}
	}

	// ����״̬
	public void updateState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User userNew = new User();
		User userOld = new User();
		PrintWriter out = response.getWriter();
		if (request.getParameter("uid") != null && !request.getParameter("uid").isEmpty()) {
			userOld.setUid(Long.parseLong(request.getParameter("uid")));
		} else {
			out.print(2);
			return;
		}
		if (request.getParameter("ustate") != null && !request.getParameter("ustate").isEmpty()) {
			userNew.setUstate(Integer.parseInt(request.getParameter("ustate")));
		}
		if (request.getParameter("upassword") != null && !request.getParameter("upassword").isEmpty()) {
			if (request.getParameter("upassword").equals("1")) {
				userNew.setUpassword("aaaa8888");
			}
		}
		try {
			int code = userBiz.update(userNew, userOld);
			if (code > 0) {
				out.print(1);
			} else {
				out.print(0);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (BizException e) {

			e.printStackTrace();
		}
	}

	// ��ӹ���Ա
	public void addAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InvocationTargetException {
		String name = request.getParameter("user-name");
		String pwd = request.getParameter("userpassword");
		String sex = request.getParameter("form-field-radio");
		String phone = request.getParameter("user-tel");
		String email = request.getParameter("email");
		String type = request.getParameter("admin-role");
		String uid = request.getParameter("uid");
		String utime = request.getParameter("bdate");
		int message = Integer.parseInt(uid);
		HttpSession session = request.getSession();
		session.setAttribute("uidAdd", message);
		if (!uid.equals("-1")) {
			User user2 = new User();
			user2.setUid(Long.parseLong(uid));
			try {
				User userShow = userBiz.selectSingle(user2);
				session.setAttribute("adminShowAdd", userShow);
			} catch (BizException e) {
				e.printStackTrace();
			}
		} else {
			Map<String, String> show = new HashMap<String, String>();
			show.put("uname", name);
			show.put("usex", sex);
			show.put("uphone", phone);
			show.put("uemail", email);
			show.put("utype", type);
			show.put("utime", utime);
			session.setAttribute("adminShowAdd", show);
		}
		User user = new User();
		String url = "/back/admin/userAdd.jsp?name=" + name + "&phone=" + phone + "&email=" + email;
		// ��֤�û��� 1.�Ƿ�Ϊ�� 2. �Ƿ�Ϸ� 3.�Ƿ����
		if (name != null && !name.isEmpty()) {
			String reg = "^[\u4e00-\u9fa5a-zA-Z]{0,20}$";
			if (name.matches(reg)) {
				User user2 = new User();
				user2.setUname(name);
				try {
					User user3 = userBiz.selectSingle(user2);
					if (user3.getUid() == 0) {
						user.setUname(name);
					} else if (user3.getUid() != 0 && uid.equals("-1")) {
						url = url + "&msg=" + 5;// �����ظ�
						request.getRequestDispatcher(url).forward(request, response);
						return;
					}
				} catch (BizException e) {

					e.printStackTrace();
				}
			} else {
				url = url + "&msg=" + 4;// ���ֲ��Ϸ�
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		} else {
			url = url + "&msg=" + 2;// ����Ϊ��
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		// ��֤�绰 1.�Ƿ�Ϊ�� 2.�Ƿ�Ϸ�
		if (phone != null && !phone.isEmpty()) {
			String reg = "^[0-9]{11}$";
			if (phone.matches(reg)) {
				user.setUphone(phone);
			} else {
				url = url + "&msg=" + 6;// �绰���Ϸ�
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		} else {
			url = url + "&msg=" + 3;// �绰Ϊ��
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		if (email != null && !email.isEmpty()) {
			String reg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
			if (email.matches(reg)) {
				user.setUemail(email);
			} else {
				url = url + "&msg=" + 7;// ���䲻�Ϸ�
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		} else {
			url = url + "&msg=" + 8;// ����Ϊ��
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		// ����
		if (pwd != null && !pwd.isEmpty()) {
			String reg = "^[A-Za-z0-9_.]{6,20}$";
			if (pwd.matches(reg)) {
				user.setUpassword(pwd);
			} else {
				url = url + "&msg=" + 11;// ���벻�Ϸ�
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		} else {
			url = url + "&msg=" + 12;// ����Ϊ��
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		// �Ա�
		if (sex != null && !sex.isEmpty()) {
			user.setUsex(Integer.parseInt(sex));
		}
		// ʱ��
		if (utime != null) {
			user.setUtime(utime);
		}
		// ����
		if (type != null && !type.isEmpty()) {
			user.setUtype(Integer.parseInt(type));
		} else {
			user.setUtype(5);
		}
		if (request.getParameter("bate") != null && !request.getParameter("bate").isEmpty()) {
			user.setUtime(request.getParameter("bate"));
		}
		if (!uid.equals("-1")) {
			User userOld = new User();
			userOld.setUid(Long.parseLong(uid));
			try {
				int j = userBiz.update(user, userOld);
				if (j > 0) {
					url = url + "&msg=" + 10;
				} else {
					url = url + "&msg=" + 9;
				}
				request.getRequestDispatcher(url).forward(request, response);
				return;
			} catch (SQLException e) {

				e.printStackTrace();
			} catch (BizException e) {

				e.printStackTrace();
			}
		}
		try {
			user.setUstate(1);
			int i = userBiz.insert(user);
			if (i > 0) {
				url = url + "&msg=" + 1;
			} else {
				url = url + "&msg=" + -1;
			}
			request.getRequestDispatcher(url).forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (BizException e) {

			e.printStackTrace();
		}
	}

	// ������Ϣչʾ
	public void showUserMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		UserBiz userBiz = new UserBiz();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if (request.getParameter("uid") != null && !request.getParameter("uid").isEmpty()) {
			user.setUid(Long.parseLong(request.getParameter("uid")));
		}
		try {
			User userShow = userBiz.selectSingle(user);
			session.setAttribute("userMessage", userShow);
			if (userShow.getUid() == 0) {
				out.print(0);
			}
		} catch (BizException e) {
			e.printStackTrace();
		}
	}

	public void queryUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, BizException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		String uname = "";// ����
		String phone = "";// �绰
		String email = "";// ����
		// ��ѯ���е��û�
		User userShow = new User();// ������ʾ���û���������
		UserBiz userBiz = new UserBiz();// user���������
		// ��ȡ��ѯ����
		if (request.getParameter("username") != null && !request.getParameter("username").isEmpty()) {
			uname = request.getParameter("username");
			userShow.setUname(uname);
		}
		if (request.getParameter("phone") != null && !request.getParameter("phone").isEmpty()) {
			phone = request.getParameter("phone");
			userShow.setUphone(phone);
		}
		if (request.getParameter("email") != null && !request.getParameter("email").isEmpty()) {
			email = request.getParameter("email");
			userShow.setUemail(email);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("uname", uname);
		map.put("uphone", phone);
		map.put("uemail", email);
		session.setAttribute("userQuery", map);
		// ��ѯ����
		List<User> showList = userBiz.selectAll(userShow);// ���ݿ����е��û��Լ�����Ա
		// �޳�����Ա
		for (int i = 0; i < showList.size(); i++) {
			if (showList.get(i).getUtype() == 1 || showList.get(i).getUtype() == 0) {
				showList.remove(i);// ��Ԫ���Ƴ�
				// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
				i--;
			}
		}
		session.setAttribute("userListShow", showList);
		if (showList.size() == 0) {
			out.print(1);
		}
	}
	// TODO Auto-generated catch block

	// �޸ĸ�������
	public void updatePwd(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginedUser");
		User userNew = new User();
		String newpassword = (String) session.getAttribute("regUpwd");
		try {
			if (newpassword != null && !newpassword.isEmpty()) {
				userNew.setUpassword(newpassword);
			} else {
				result = Result.failure("������δ����򲻺Ϸ�");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
			int code = userBiz.update(userNew, user);
			if (code <= 0) {
				result = Result.failure("�޸�ʧ�ܣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.success("�޸ĳɹ�������");
			//����ˢ��
			User user2 = userBiz.selectSingle(user);
			session.setAttribute("longinedUser", user2);
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
		} catch (SQLException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
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
				throw new RuntimeException(e1);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
	}

	// ��������������
	public void checkPwd(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginedUser");
		try {
			String oldpassword = request.getParameter("oldpassword");
			if (oldpassword != null && !oldpassword.isEmpty()) {
				if (oldpassword.equals(user.getUpassword())) {
					result = Result.success("ԭ������ȷ������");
					String json = gson.toJson(result);

					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
				} else {
					result = Result.failure("ԭ����������󣡣���");
					String json = gson.toJson(result);

					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
				}
			} else {
				result = Result.failure("������ԭ���룡����");
				String json = gson.toJson(result);

				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);

			}
			e.printStackTrace();
		}
	}

	// ���¸�����Ϣ
	public void updateUser(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = new User();
		String age = request.getParameter("uage");
		String phone = request.getParameter("uphone");
		String email = request.getParameter("uemail");
		String minname = request.getParameter("uminname");
		String sex = request.getParameter("usex");
		String uni = request.getParameter("university");
		String ucol = request.getParameter("ucollege");
		String umajor = request.getParameter("umajor");
		String uclass = request.getParameter("uclass");
		User userOld = (User) session.getAttribute("loginedUser");
		String check = "1";
		// �绰
		if (phone != null && !phone.isEmpty()) {
			if (!phone.equals(userOld.getUphone())) {
				phone = (String) session.getAttribute("updateUphone");
				if (phone != null && !phone.isEmpty()) {
					user.setUphone(phone);
					check = check + "/1";
				} else {
					check = check + "/-1";
				}
			} else {
				user.setUphone(phone);
				check = check + "/1";
			}
		} else {
			check = check + "/-1";
		}

		// ����
		if (age != null && !age.isEmpty()) {
			if (!age.equals(userOld.getUage())) {
				age = (String) session.getAttribute("updateUage");
				if (age != null && !age.isEmpty()) {
					user.setUage(Integer.parseInt(age));
					check = check + "/1";
				} else {
					check = check + "/-1";
				}
			} else {
				user.setUage(Integer.parseInt(age));
				check = check + "/1";
			}
		} else {
			check = check + "/-1";
		}
		// ����
		if (email != null && !email.isEmpty()) {
			if (!email.equals(userOld.getUemail())) {
				email = (String) session.getAttribute("updateUemail");
				if (email != null && !email.isEmpty()) {
					user.setUemail(email);
					check = check + "/1";
				} else {
					check = check + "/-1";
				}
			} else {
				user.setUemail(email);
				check = check + "/1";
			}
		} else {
			check = check + "/-1";
		}
		try {
			if (!check.equals("1/1/1/1")) {
				result = Result.lack("��Ϣ�����������", check);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// ����Ϸ�
			if (minname != null && !minname.isEmpty()) {
				user.setUminname(minname);
			}
			if (sex != null && !sex.isEmpty()) {
				user.setUsex(Integer.parseInt(sex));
			}
			if (uni != null && !uni.isEmpty()) {
				user.setUniversity(uni);
			}
			if (ucol != null && !ucol.isEmpty()) {
				user.setUcollege(ucol);
			}
			if (!uclass.isEmpty() && uclass != null) {
				user.setUclass(uclass);
			}
			if (umajor != null && !umajor.isEmpty()) {
				user.setUmajor(umajor);
			}

			// ���и��²���

			int code = userBiz.update(user, userOld);
			if (code > 0) {
				//����ˢ��
				User user2 = userBiz.selectSingle(user);
				session.setAttribute("longinedUser", user2);
				result = Result.success("�޸ĳɹ�������");
			} else {
				result = Result.failure("�޸�ʧ��");
			}
			String json = gson.toJson(result);

			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
		} catch (SQLException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
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
				throw new RuntimeException(e1);

			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);

			}
			e.printStackTrace();
		}
	}

	// ���¼�������
	public void checkUage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		String uage = request.getParameter("uage");
		String regage = "^[0-9]{1,2}$";
		try {
			if (uage != null && !uage.isEmpty()) {
				if (!uage.matches(regage)) {
					result = Result.failure("�������벻�Ϸ�������");
					String json = gson.toJson(result);

					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				session.setAttribute("updateUage", uage);
				result = Result.success("������ȷ������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			} else {
				result = Result.failure("���������䣡����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
	}

	/*
	 * ���¼���绰
	 */
	public void checkPhone(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String uphone = request.getParameter("uphone");
		String regphone = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		User userOld = (User) session.getAttribute("loginedUser");
		User user = new User();
		try {
			if (uphone != null && !uphone.isEmpty()) {
				// �绰δ�޸�
				if (uphone.equals(userOld.getUphone())) {
					result = Result.success("", uphone);
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					session.setAttribute("updateUphone", uphone);
					return;
				}
				if (!uphone.matches(regphone)) {
					result = Result.failure("�绰���벻�Ϸ�������", uphone);
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				user.setUphone(uphone);
				User user2 = userBiz.selectSingle(user);
				if (user2.getUid() != 0) {
					result = Result.failure("�绰�����ѱ�ʹ�ã�����", uphone);
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				result = Result.success("�õ绰�������ʹ�ã�����", uphone);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				session.setAttribute("updateUphone", uphone);
			} else {
				result = Result.failure("������绰���룡����", uphone);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
		} catch (BizException e) {
			result = Result.error(e.getMessage(), uphone);
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
			e.printStackTrace();
		}
	}

	// ���¼������
	public void checkEmail(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User userOld = (User) session.getAttribute("loginedUser");
		String uemail = request.getParameter("uemail");
		User user = new User();
		String regemail = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		try {
			if (uemail != null && !uemail.isEmpty()) {
				user.setUemail(uemail);
			} else {
				result = Result.failure("���������䣡����", uemail);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// �����ʽ���Ϸ���
			if (!uemail.matches(regemail)) {
				result = Result.failure("�����ʽ���Ϸ�������", uemail);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// ����δ�޸�
			if (uemail.equals(userOld.getUemail())) {
				result = Result.success("");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				session.setAttribute("updateUemail", uemail);
				return;
			}
			User user2 = userBiz.selectSingle(user);
			// �������ѱ�ע�ᣡ
			if (user2.getUid() != 0) {
				result = Result.failure("�������ѱ�ע�ᣡ����", uemail);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// ���������ע��!
			session.setAttribute("updateUemail", uemail);
			result = Result.success("���������ʹ��", uemail);
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
		} catch (BizException e) {
			result = Result.error(e.getMessage(), uemail);
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
	}

}
