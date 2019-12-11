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
	UserBiz userBiz = new UserBiz();
	// 添加
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InvocationTargetException {
		User user = new User();
		HttpSession session = request.getSession();
		String name = request.getParameter("username").trim();
		String phone = request.getParameter("uphone").trim();
		String email = request.getParameter("uemail").trim();
		String uid = request.getParameter("uid").trim();
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
		}else{
			Map<String,String> show = new HashMap<String, String>();
			show.put("uname", name);
			show.put("uphone", phone);
			show.put("uemail", email);
			session.setAttribute("userShowAdd", show);
		}
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
		String utime = request.getParameter("bdate").trim();
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
		}else{
			Map<String,String> show = new HashMap<String, String>();
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
		//时间
		if(utime != null){
			user.setUtime(utime);
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
				url = url + "&msg=" + -1;
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
	
	//个人信息展示
	public void showUserMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		UserBiz userBiz = new UserBiz();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if(request.getParameter("uid") != null && !request.getParameter("uid").isEmpty()){
			user.setUid(Long.parseLong(request.getParameter("uid")));
		}
		try {
			User userShow  = userBiz.selectSingle(user);
			session.setAttribute("userMessage", userShow);
			if(userShow.getUid() == 0){
				out.print(0);
			}
		} catch (BizException e) {
			e.printStackTrace();
		}
	}
	public void queryUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
	
		String uname = "";//姓名
		String phone = "";//电话
		String email = "";//邮箱
		//查询所有的用户
		User userShow = new User();//定义显示的用户条件对象
		UserBiz userBiz = new UserBiz();//user事务操作类
		//获取查询条件
		if(request.getParameter("username") != null && !request.getParameter("username").isEmpty()){
			uname = request.getParameter("username");
			userShow.setUname(uname);
		}
		if(request.getParameter("phone") != null && !request.getParameter("phone").isEmpty()){
			phone = request.getParameter("phone");
			userShow.setUphone(phone);
		}
		if(request.getParameter("email") != null && !request.getParameter("email").isEmpty()){
			email = request.getParameter("email");
			userShow.setUemail(email);
		}
		Map<String, String> map  = new HashMap<String, String>();
		map.put("uname", uname);
		map.put("uphone", phone);
		map.put("uemail", email);
		session.setAttribute("userQuery", map);
		//查询数据
		List<User> showList = userBiz.selectAll(userShow);//数据库所有的用户以及管理员
		//剔除管理员
		for(int i =0;i<showList.size();i++){
			if(showList.get(i).getUtype() ==1 || showList.get(i).getUtype() ==0 ){
				showList.remove(i);//将元素移出
				//此时需注意，因为list会动态变化不像数组会占位，所以当前索引应该后退一位
				i--;
			}
		}
		session.setAttribute("userListShow", showList);
		if(showList.size() == 0){
			out.print(1);
		}
	}
	
	//检查个人密码输入
	public void checkPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user =(User)session.getAttribute("loginedUser");
		
		String oldpassword = request.getParameter("oldpassword");
		if(oldpassword != null && !oldpassword.isEmpty()){
			if(oldpassword.equals(user.getUpassword())){
				out.print(1);
			}else{
				out.print(-1);
			}
		}else{
			out.print(-1);
		}
	}
	//修改个人密码
	public void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user =(User)session.getAttribute("loginedUser");
		User userNew = new User();
		String newpassword = request.getParameter("newpassword");
		if(newpassword != null && !newpassword.isEmpty()){
			userNew.setUpassword(newpassword);
		}
			int code;
			try {
				code = userBiz.update(userNew, user);
				String json = "{code:'"+code
						+"', msg:'修改密码成功！'}";   // 扩张一个结果描述信息
				out.print(json);
			} catch (SQLException e) {
				String json = "{msg:'修改密码失败！'}";   // 扩张一个结果描述信息
				out.print(json);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BizException e) {
				String json = "{msg:'修改密码失败！'}";   // 扩张一个结果描述信息
				out.print(json);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	//显示需要修改的信息
	public void showUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User userOld =(User)session.getAttribute("loginedUser");
		
		if(userOld.getUid() == 0){
			out.print(-1);
			return ;
		}
		//定义展示的map
		Map<String, String> map = new HashMap<String, String>();
		map.put("uname", userOld.getUname());
		map.put("uphone", userOld.getUphone());
		map.put("university", userOld.getUniversity());
		map.put("ucollege", userOld.getUcollege());
		map.put("umajor", userOld.getUmajor());
		map.put("uclass", userOld.getUclass());
		map.put("uemail", userOld.getUemail());
		map.put("utime", userOld.getUtime());
		map.put("usex", userOld.getUtime()+"");
		map.put("uage", userOld.getUage()+"");
		map.put("uminname", userOld.getUminname());
		session.setAttribute("editShowUser", map);
	}
	//检验电话
	public void checkPhone(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 HttpSession session = request.getSession();
		String uphone = request.getParameter("uphone");
		User userOld =(User)session.getAttribute("loginedUser");
		String regphone = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		 User user = new User();
		 try {
			if(uphone != null && !uphone.isEmpty()){
				if (!uphone.matches(regphone)) {
					out.print(-2);//不合法
					return ;
				} 
				if( !userOld.getUphone().equals(uphone)){
					User user2 = userBiz.selectSingle(user);
					if(user2.getUid() != 0){
						out.print(-3);//已存在
						return ;
					}
				}
				out.print(1);//可以使用
				session.setAttribute("updateUphone", uphone);
			}else{
				out.print(-1);//输入为空
			}
		 } catch (BizException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	//检验年龄
		public void checkUage(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			 PrintWriter out = response.getWriter();
			 HttpSession session = request.getSession();
			 
			String uage = request.getParameter("uage");
			String regage = "^[0-9]{1,3}$";
			
			if(uage != null && !uage.isEmpty()){
				if (!uage.matches(regage)) {
					out.print(-2);//不合法
					return ;
				}
				session.setAttribute("updateAge", uage);
				out.print(1);
			}else{
				out.print(-1);
			}
			 
		}
	//更新个人信息
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		Result result ;
		User user = new User();
		String phone = (String) session.getAttribute("updateAge");
		String age = (String) session.getAttribute("updateUphone");
		String minname = request.getParameter("uminname").trim();
		String sex = request.getParameter("usex").trim();
		String uni = request.getParameter("university").trim();
		String ucol = request.getParameter("ucollege").trim();
		String umajor = request.getParameter("umajor").trim();
		String uclass = request.getParameter("uclass").trim();
		User userOld =(User)session.getAttribute("loginedUser");
		//定义展示的map
		Map<String, String> map = new HashMap<String, String>();
		map.put("uname", userOld.getUname());
		map.put("uphone", phone);
		map.put("university", uni);
		map.put("ucollege", ucol);
		map.put("umajor", umajor);
		map.put("uclass", uclass);
		map.put("uemail", userOld.getUemail());
		map.put("utime", userOld.getUtime());
		map.put("usex", sex);
		map.put("uage", age);
		map.put("uminname", minname);
		session.setAttribute("editShowUser", map);
		
		String check = "1";
		if(phone != null && !phone.isEmpty()){
			user.setUphone(phone);
			check = check + "/1";
		}else{
			check = check + "/1";
		}
		if(age != null && !age.isEmpty()){
			user.setUage(Integer.parseInt(age));
			check = check + "/-1";
		}else{
			check = check + "/-1";
		}
		if(!check.equals("1/1/1")){
			result= Result.failure(check);
			String json = gson.toJson(result);
			// 返回json格式数据
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return ;
		}
		//输入合法
		if(minname != null && !minname.isEmpty()){
			user.setUminname(minname);
		}
		if(sex != null && !sex.isEmpty()){
			user.setUsex(Integer.parseInt(sex));
		}
		if(uni != null && !uni.isEmpty()){
			user.setUniversity(uni);
		}
		if(ucol != null && !ucol.isEmpty()){
			user.setUcollege(ucol);
		}
		if(!uclass.isEmpty() && uclass != null){
			user.setUclass(uclass);
		}
		if(umajor != null && !umajor.isEmpty()){
			user.setUmajor(umajor);
		}
		
		//进行更新操作
		try {
			int code = userBiz.update(user, userOld);
			if(code > 0){
				result = Result.success("修改成功！！！");
			}else{
				result = Result.failure("修改失败");
			}
			String json = gson.toJson(result);
			// 返回json格式数据
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
