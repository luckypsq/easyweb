package com.yc.easyweb.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的Servlet基类
 */
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 通过op参数决定调用的方�?
		String op = request.getParameter("op");
		
		// 根据 op 来执�? 子类 �? 业务方法 �? java 反射：动态执行java方法
		// 获取类对�?
		Class<?> cls = this.getClass();
		try {
			// Method java 方法对象
			Method m = cls.getMethod(op, HttpServletRequest.class, HttpServletResponse.class);
			// 通过 java 的反射机制， 动�?�执行方�?
			m.invoke(this, request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
