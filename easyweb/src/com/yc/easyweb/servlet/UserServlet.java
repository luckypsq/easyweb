package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.UserBiz;
import com.yc.easyweb.dao.lyw.UserDaoLyw;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	UserBiz userBiz = new UserBiz();
	private UserDaoLyw dao =new UserDaoLyw();
	// 添加
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InvocationTargetException {
		User user = new User();
		String name = request.getParameter("username").trim();
		String phone = request.getParameter("uphone").trim();
		String email = request.getParameter("uemail").trim();
		String uid = request.getParameter("uid").trim();
		String url = "/back/user/userAdd.jsp?name=" + name + "&phone=" + phone + "&email=" + email;
		// 验证用户名 1.是否为空 2. 是否合法 3.是否存在
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
				}  catch (BizException e) {
					// TODO Auto-generated catch block
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
		if(request.getParameter("bate")!=null && !request.getParameter("bate").isEmpty()){
			user.setUtime(request.getParameter("bate"));
		}
		// 验证电话 1.是否为空 2.是否合法
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
		if (!request.getParameter("utype").equals("请选择")) {
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
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BizException e) {
				// TODO Auto-generated catch block
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
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 删除
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
					}  catch (BizException e) {
						// TODO Auto-generated catch block
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
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 更新状态
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
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 添加管理员
	public void addAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InvocationTargetException {
		String name = request.getParameter("user-name").trim();
		String pwd = request.getParameter("userpassword").trim();
		String sex = request.getParameter("form-field-radio").trim();
		String phone = request.getParameter("user-tel").trim();
		String email = request.getParameter("email").trim();
		String type = request.getParameter("admin-role").trim();
		String uid = request.getParameter("uid").trim();
		User user = new User();
		String url = "/back/admin/userAdd.jsp?name=" + name + "&phone=" + phone + "&email=" + email;
		// 验证用户名 1.是否为空 2. 是否合法 3.是否存在
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
						url = url + "&msg=" + 5;//名字重复
						request.getRequestDispatcher(url).forward(request, response);
						return;
					}
				}  catch (BizException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				url = url + "&msg=" + 4;//名字不合法
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		} else {
			url = url + "&msg=" + 2;//名字为空
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		// 验证电话 1.是否为空 2.是否合法
		if (phone != null && !phone.isEmpty()) {
			String reg = "^[0-9]{11}$";
			if (phone.matches(reg)) {
				user.setUphone(phone);
			} else {
				url = url + "&msg=" + 6;//电话不合法
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		} else {
			url = url + "&msg=" + 3;//电话为空
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		if (email != null && !email.isEmpty()) {
			String reg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
			if (email.matches(reg)) {
				user.setUemail(email);
			} else {
				url = url + "&msg=" + 7;//邮箱不合法
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		}else {
			url = url + "&msg=" + 8;//邮箱为空
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		//密码
		if (pwd!= null && !pwd.isEmpty()) {
			String reg = "^[A-Za-z0-9_.]{6,20}$";
			if (pwd.matches(reg)) {
				user.setUpassword(pwd);
			} else {
				url = url + "&msg=" + 11;//密码不合法
				request.getRequestDispatcher(url).forward(request, response);
				return;
			}
		}else {
			url = url + "&msg=" + 12;//密码为空
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		//性别
		if(sex!= null && !sex.isEmpty()){
			user.setUsex(Integer.parseInt(sex));
		}
		//级别
		if(type!= null && !type.isEmpty()){
			user.setUtype(Integer.parseInt(type));
		}else{
			user.setUtype(5);
		}
		if(request.getParameter("bate")!=null && !request.getParameter("bate").isEmpty()){
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BizException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			user.setUstate(1);
			int i = userBiz.insert(user);
			if (i > 0) {
				url = url + "&msg=" + 1;
			} else {
				url = url + "&msg=" + 0;
			}
			request.getRequestDispatcher(url).forward(request, response);
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void remember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user =(User)request.getSession().getAttribute("loginedUser");
		String uminname = request.getParameter("uminname");
		String uphone = request.getParameter("uphone");
		String university = request.getParameter("university");
		String ucollege = request.getParameter("ucollege");
		String umajor = request.getParameter("umajor");
		String uclass = request.getParameter("uclass");
		Long uid = user.getUid();
		String url = "/lywoption/member.jsp";
		try {
			int i=dao.update(uminname,uphone,university,ucollege,umajor,uclass,uid);
			if(i==1){
				request.setAttribute("result","个人信息修改成功");
				request.getRequestDispatcher(url).forward(request, response);
				
            }else{
                request.setAttribute("result","个人信息修改失败");
                request.getRequestDispatcher(url).forward(request, response);
            }
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
