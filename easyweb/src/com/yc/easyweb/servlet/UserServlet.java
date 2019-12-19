package com.yc.easyweb.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

	// ɾ��
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String uid = request.getParameter("uid");
		User user;
		User user2;
		List<User> list = new ArrayList<User>();
		try {
			// 1.�ж��Ƿ�Ϊ��
			if (uid != null && !uid.isEmpty()) {
				// 2.�ж�ɾ�����û�״̬
				String[] uids = uid.split("/");
				for (String string : uids) {
					user = new User();
					if (!string.isEmpty() && string != null) {
						// a.״̬Ϊ3�����ظ�����
						user.setUid(Long.parseLong(string));
						user2 = userBiz.selectSingle(user);
						if (user2.getUstate() == 3) {
							result = Result.failure("���û��ѱ�ɾ��,�����ظ����д˲���������");
							String json = gson.toJson(result);
							response.setContentType("application/json;charset=UTF-8");
							response.getWriter().append(json);
							return;
						}
						user.setUstate(3);
						list.add(user);
					}
				}
			} else {
				result = Result.failure("��δѡ���κ��û�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			if (list.size() > 0) {
				int code = userBiz.update(list);
				if (code > 0) {
					result = Result.success("ɾ���ɹ�������");
					// ����ˢ��
					User user1 = new User();
					List<User> customerExit = userBiz.selectAll(user1);// �洢�����û���Ϣ
					user1.setUtype(1);
					List<User> adminListAll = userBiz.selectAll(user1);
					user1.setUtype(5);
					List<User> adminList = userBiz.selectAll(user1);
					if (adminList.size() != 0) {
						for (User u : adminList) {
							adminListAll.add(u);
						}
					}
					// �޳�����Ա
					for (int i = 0; i < customerExit.size(); i++) {
						for (int j = 0; j < adminListAll.size(); j++) {
							if (customerExit.get(i).equals(adminListAll.get(j))
									&& customerExit.get(i).hashCode() == adminListAll.get(j).hashCode()) {
								customerExit.remove(i);
								// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
								i--;
							}
						}
					}
					session.setAttribute("customerAll", customerExit);// �洢�����û���Ϣ
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				result = Result.failure("ɾ��ʧ�ܣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("��δѡ���κ��û�������");
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
		} catch (BizException e) {
			result = Result.error(e.getMessage());
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
		}
	}

	// ����Ա�����û�״̬������
	public void updateState(HttpServletRequest request, HttpServletResponse response) {
		User userNew = new User();
		User userOld = new User();
		HttpSession session = request.getSession();
		String uid = request.getParameter("uid");
		String ustate = request.getParameter("ustate");
		String upwd = request.getParameter("upassword");
		try {
			if (uid != null && !uid.isEmpty()) {
				userOld.setUid(Long.parseLong(uid));
			} else {
				result = Result.failure("��ѡ���û�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			if (ustate != null && !ustate.isEmpty()) {
				userNew.setUstate(Integer.parseInt(ustate));
			}else if (upwd != null && !upwd.isEmpty()) {
				if (upwd.equals("1")) {
					userNew.setUpassword("aaaa8888");
				}else {
					result = Result.failure("����ʧ�ܣ�����");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
			}else {
				result = Result.failure("����ʧ�ܣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			int code = userBiz.update(userNew, userOld);
			if (code > 0) {
				result = Result.success("�����ɹ�������");
				// ��������
				User user = new User();
				List<User> customerExit = userBiz.selectAll(user);// �洢�����û���Ϣ
				user.setUtype(1);
				List<User> adminListAll = userBiz.selectAll(user);
				user.setUtype(5);
				List<User> adminList = userBiz.selectAll(user);
				if (adminList.size() != 0) {
					for (User u : adminList) {
						adminListAll.add(u);
					}
				}
				// �޳�����Ա
				for (int i = 0; i < customerExit.size(); i++) {
					for (int j = 0; j < adminListAll.size(); j++) {
						if (customerExit.get(i).equals(adminListAll.get(j))
								&& customerExit.get(i).hashCode() == adminListAll.get(j).hashCode()) {
							customerExit.remove(i);
							// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
							i--;
						}
					}
				}
				session.setAttribute("customerAll", customerExit);// �洢�����û���Ϣ
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("����ʧ�ܣ�����");
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
		} catch (BizException e) {
			result = Result.error(e.getMessage());
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
		}
	}

	// ����Ա�鿴�û�������Ϣ
	public void showUserMessage(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		HttpSession session = request.getSession();
		String uid = request.getParameter("uid");
		if (uid != null && !uid.isEmpty()) {
			user.setUid(Long.parseLong(uid));
		}
		try {
			User userShow = userBiz.selectSingle(user);
			if (userShow.getUid() == 0) {
				result = Result.failure("���޴��ˣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			session.setAttribute("userMessage", userShow);
			result = Result.success("��ѯ�ɹ�������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
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
		} catch (BizException e) {
			result = Result.error(e.getMessage());
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
		}
	}

	// ����Ա�鿴�û��б�
	public void queryUser(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String uname = request.getParameter("uname");// ����
		String uphone = request.getParameter("uphone");// �绰
		String uemail = request.getParameter("uemail");// ����
		String utype = request.getParameter("utype");
		// ��ѯ���е��û�
		User userShow = new User();// ������ʾ���û���������

		// ��ȡ��ѯ����
		if (uname != null && !uname.isEmpty()) {
			userShow.setUname(uname);
		}
		if (uphone != null && !uphone.isEmpty()) {
			userShow.setUphone(uphone);
		}
		if (uemail != null && !uemail.isEmpty()) {
			userShow.setUemail(uemail);
		}
		if(utype != null && !utype.isEmpty()){
			userShow.setUtype(Integer.parseInt(utype));	
		}
		try {
			List<User> customerExit = userBiz.selectAll(userShow);// �洢�����û���Ϣ
			User user = new User();
			user.setUtype(1);
			List<User> adminListAll = userBiz.selectAll(user);
			user.setUtype(5);
			List<User> adminList = userBiz.selectAll(user);
			if (adminList.size() != 0) {
				for (User u : adminList) {
					adminListAll.add(u);
				}
			}
			// �޳�����Ա
			for (int i = 0; i < customerExit.size(); i++) {
				for (int j = 0; j < adminListAll.size(); j++) {
					if (customerExit.get(i).equals(adminListAll.get(j))
							&& customerExit.get(i).hashCode() == adminListAll.get(j).hashCode()) {
						customerExit.remove(i);
						// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
						i--;
					}
				}
			}
			session.setAttribute("customerAll", customerExit);// �洢�����û���Ϣ
			if (customerExit.size() > 0) {
				result = Result.success("��ѯ�ɹ�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("�������ݣ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
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
		} catch (BizException e) {
			result = Result.error(e.getMessage());
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
		}
	}

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
			// ����ˢ��
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
				// ����ˢ��
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

	// ����Ա����û�
	public void add(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("regUname");
		String uphone = (String) session.getAttribute("regUphone");
		String uemail = (String) session.getAttribute("regUemail");
		String utype = request.getParameter("utype");

		if (utype != null && !utype.isEmpty()) {
			user.setUtype(Integer.parseInt(utype));
		} else {
			user.setUtype(2);
		}
		// 1.��֤���ݵĺϷ���
		String check = "1";
		// �û���
		if (uname != null && !uname.isEmpty()) {
			user.setUname(uname);
			check = check + "/1";
		} else {
			check = check + "/-1";
		}
		// �绰
		if (uphone != null && !uphone.isEmpty()) {
			user.setUphone(uphone);
			check = check + "/1";
		} else {
			check = check + "/-1";
		}
		// ����
		if (uemail != null && !uemail.isEmpty()) {
			user.setUemail(uemail);
			check = check + "/1";
		} else {
			check = check + "/-1";
		}
		try {
			if (!check.equals("1/1/1/1")) {
				result = Result.lack("��Ϣ������������", check);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// 2.���
			// ��ȡϵͳ��ǰʱ��
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			user.setUtime(df.format(date));
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			String min = uuid.substring(0, 10);
			user.setUminname("�û�" + min);
			user.setUpassword("aaaa8888");
			user.setUstate(1);
			int code = userBiz.insert(user);
			if (code > 0) {
				result = Result.success("��ӳɹ�������");
				// �Ự��ԭ
				String string = null;
				session.setAttribute("regUname", string);
				session.setAttribute("regUphone", string);
				session.setAttribute("regUpwd", string);
				session.setAttribute("regUemail", string);
				// ����ˢ��
				User user1 = new User();
				List<User> customerExit = userBiz.selectAll(user1);// �洢�����û���Ϣ
				user1.setUtype(1);
				List<User> adminListAll = userBiz.selectAll(user1);
				user1.setUtype(5);
				List<User> adminList = userBiz.selectAll(user1);
				if (adminList.size() != 0) {
					for (User u : adminList) {
						adminListAll.add(u);
					}
				}
				// �޳�����Ա
				for (int i = 0; i < customerExit.size(); i++) {
					for (int j = 0; j < adminListAll.size(); j++) {
						if (customerExit.get(i).equals(adminListAll.get(j))
								&& customerExit.get(i).hashCode() == adminListAll.get(j).hashCode()) {
							customerExit.remove(i);
							// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
							i--;
						}
					}
				}
				session.setAttribute("customerAll", customerExit);// �洢�����û���Ϣ
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("���ʧ�ܣ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
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
			result = Result.error("ҵ��æ,���Եȼ������ٲ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		} catch (SQLException e) {
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

	/*
	 * ����Ա�����û������û���
	 * 
	 * @param request
	 * 
	 * @param response
	 */
	public void adminCheckName(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String uid = request.getParameter("uid");
		try {
			if (username != null && !username.isEmpty()) {
				user.setUname(username);
			} else {
				// �û�������Ϊ��
				result = Result.failure("�û���Ϊ�գ�����", username);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			String reg = "^[\u4e00-\u9fa5a-zA-Z]{2,20}$";
			if (!username.matches(reg)) {
				result = Result.failure("�û������벻�Ϸ�������", username);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			long uid1;
			if (uid != null && !uid.isEmpty()) {
				uid1 = Long.parseLong(uid);
			} else {
				result = Result.failure("δѡ���û�������", username);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			User user2 = userBiz.selectSingle(user);
			if (user2.getUid() != uid1 && user2.getUid() != 0) { // ˵���û����Ѿ���ʹ��
				result = Result.failure("�Ѵ��ڸ��û�������", username);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.success("���û�������ʹ�ã�����");
			String json = gson.toJson(result);
			session.setAttribute("adminUpdateUname", username);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
		} catch (BizException e) {
			result = Result.error(e.getMessage(), username);
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);

			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������", username);
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
	 * ����Ա�����û�����绰
	 */
	public void adminCheckPhone(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String uphone = request.getParameter("uphone");
		String regphone = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		String uid = request.getParameter("uid");
		User user = new User();
		long uid1 = 0;
		try {
			User user2 = new User();
			if (uid != null && !uid.isEmpty()) {
				uid1 = Long.parseLong(uid);
				user2.setUid(uid1);
			} else {
				result = Result.failure("δѡ���û�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
			User userOld = userBiz.selectSingle(user2);
			if (uphone != null && !uphone.isEmpty()) {
				// �绰δ�޸�
				if (uphone.equals(userOld.getUphone())) {
					result = Result.success("", uphone);
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					session.setAttribute("adminUpdateUphone", uphone);
					return;
				}
				if (!uphone.matches(regphone)) {
					result = Result.failure("�绰���벻�Ϸ�������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				user.setUphone(uphone);
				User user3 = userBiz.selectSingle(user);
				if (user3.getUid() != 0 && user3.getUid() != uid1) {
					result = Result.failure("�绰�����ѱ�ʹ�ã�����");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				result = Result.success("�õ绰�������ʹ�ã�����");
				session.setAttribute("adminUpdateUphone", uphone);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
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

	// ����Ա�����û��������
	public void adminCheckEmail(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String uemail = request.getParameter("uemail");
		String uid = request.getParameter("uid");
		User user = new User();
		String regemail = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		try {
			if (uemail != null && !uemail.isEmpty()) {
				user.setUemail(uemail);
			} else {
				result = Result.failure("���������䣡����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// �����ʽ���Ϸ���
			if (!uemail.matches(regemail)) {
				result = Result.failure("�����ʽ���Ϸ�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// ����δ�޸�
			long uid1 = 0;
			if (uid != null && !uid.isEmpty()) {
				uid1 = Long.parseLong(uid);
				user.setUid(uid1);
			} else {
				result = Result.failure("δѡ���û�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			User user2 = new User();
			user2.setUid(uid1);
			User userOld = userBiz.selectSingle(user2);
			if (uemail.equals(userOld.getUemail())) {
				result = Result.success("");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				session.setAttribute("adminUpdateUemail", uemail);
				return;
			}
			User user3 = userBiz.selectSingle(user);
			// �������ѱ�ע�ᣡ
			if (user3.getUid() != 0 && user3.getUid() != uid1) {
				result = Result.failure("�������ѱ�ע�ᣡ����", uemail);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// ���������ע��!
			session.setAttribute("adminUpdateUemail", uemail);
			result = Result.success("���������ʹ��", uemail);
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
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
	

	// ����Ա�����û���Ϣ
	public void adminUpdateUser(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String uid = request.getParameter("uid");
		String utype = request.getParameter("utype");
		User userNew = new User();
		User userOld = new User();

		String uname = (String) session.getAttribute("adminUpdateUname");
		String uemail = (String) session.getAttribute("adminUpdateUemail");
		String uphone = (String) session.getAttribute("adminUpdateUphone");

		// 1.��֤���ݵĺϷ���
		String check = "1";
		// a.����
		if (uname != null && !uname.isEmpty()) {
			userNew.setUname(uname);
			check = check + "/1";
		} else {
			if (request.getParameter("uname") != null && !request.getParameter("uname").isEmpty()) {
				userNew.setUname(uname);
				check = check + "/1";
			} else {
				check = check + "/-1";
			}
		}
		// b.����
		if (uemail != null && !uemail.isEmpty()) {
			userNew.setUemail(uemail);
			check = check + "/1";
		} else {
			if (request.getParameter("uemail") != null && !request.getParameter("uemail").isEmpty()) {
				userNew.setUemail(uemail);
				check = check + "/1";
			} else {
				check = check + "/-1";
			}
		}
		// c.�绰
		if (uphone != null && !uphone.isEmpty()) {
			userNew.setUphone(uphone);
			check = check + "/1";
		} else {
			if (request.getParameter("uphone") != null && !request.getParameter("uphone").isEmpty()) {
				userNew.setUphone(uphone);
				check = check + "/1";
			} else {
				check = check + "/-1";
			}
		}
		try {
			// ���ݲ��Ϸ�
			if (!check.equals("1/1/1/1")) {
				result = Result.lack("���ݲ��Ϸ�������",check);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			//�û�����
			if(uid != null && !uid.isEmpty()){
				userOld.setUid(Long.parseLong(uid));
			}else{
				result = Result.failure("δѡ���û�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			//2.���и��²���
			if(utype != null && !utype.isEmpty()){
				userNew.setUtype(Integer.parseInt(utype));
			}
			int code = userBiz.update(userNew, userOld);
			if(code > 0 ){
				result = Result.success("���³ɹ�������");
				//�Ự��ԭ
				String string = null; 
				session.setAttribute("adminUpdateUname", string);
				session.setAttribute("adminUpdateUemail", string);
				session.setAttribute("adminUpdateUphone", string);
				//���ݸ���
				User user1 = new User();
				List<User> customerExit = userBiz.selectAll(user1);// �洢�����û���Ϣ
				user1.setUtype(1);
				List<User> adminListAll = userBiz.selectAll(user1);
				user1.setUtype(5);
				List<User> adminList = userBiz.selectAll(user1);
				if (adminList.size() != 0) {
					for (User u : adminList) {
						adminListAll.add(u);
					}
				}
				// �޳�����Ա
				for (int i = 0; i < customerExit.size(); i++) {
					for (int j = 0; j < adminListAll.size(); j++) {
						if (customerExit.get(i).equals(adminListAll.get(j))
								&& customerExit.get(i).hashCode() == adminListAll.get(j).hashCode()) {
							customerExit.remove(i);
							// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
							i--;
						}
					}
				}
				session.setAttribute("customerAll", customerExit);// �洢�����û���Ϣ
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("����ʧ�ܣ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
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
		} catch (SQLException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ�����");
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
		}
	}
	// TODO Auto-generated catch block


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

}
