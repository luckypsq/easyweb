package com.yc.easyweb.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.UserBiz;
import com.yc.easyweb.common.DbHelper;
import com.yc.easyweb.util.StrUtil;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	// 查询
	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	}

	// 添加
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		User user = new User();
		System.out.println("======");
		Map<String, String[]> map = request.getParameterMap();
		for (int i = 0; i < map.size(); i++) {
			System.out.println(map.get(i).toString());
		}
		if(map.get("user-name") != null){
			user.setUname(map.get("user-name")[0]);
		}
		if(map.get("userpassword") != null){
			user.setUpassword(map.get("userpassword")[0]);
		}
		if (map.get("user-tel") != null) {
			user.setUphone(map.get("user-tel")[0]);
		}
		if (map.get("email") != null) {
			user.setUemail(map.get("email")[0]);
		}
		String[] sex = map.get("form-field-radio");
		/*if (sex != null) {
			if(sex.equals("0")){
				user.setUsex(0);
			}else if(sex.equals("1")){
				user.setUsex(1);
			}else{
				user.setUsex(2);
			}
		}*/
		
		/*String type = request.getParameter("admin-role");
		if (type != null) {
			if(type.equals("5")){
				user.setUtype(5);
			}else{
				user.setUtype(4);
			}
		}*/
		Result result;
		UserBiz biz = new UserBiz();
		int i = biz.insert(user);
		if(i > 0){
			result = Result.success("添加成功！！！");
		}else{
			result = Result.failure("添加失败！");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		// 返回json格式数据
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(json);
	}

	// 删除
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	}

	// 更新
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	}
}
