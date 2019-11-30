package com.yc.easyweb.servlet.pq;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * ������Ҳ�Ƿ��������
 * �����������ã� web.xml ע��
 */
@WebFilter("/LoginFilter")
public class JoinFilter implements Filter {

 
	public void destroy() {	}

	/**
	 * ���˷���
	 *  FilterChain  ������������
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(
			ServletRequest request,
			ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session  = httpRequest.getSession();
		
		if(session.getAttribute("loginedUser") == null) {
			request.setAttribute("msg", "���ȵ�¼ϵͳ");
			request.getRequestDispatcher("join.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
