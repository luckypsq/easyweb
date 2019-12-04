package com.yc.easyweb.servlet.pq;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ������Ҳ�Ƿ��������
 * �����������ã� web.xml ע��
 */
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
		HttpServletResponse response2 = (HttpServletResponse)response;
		HttpSession session  = httpRequest.getSession();
		String path = httpRequest.getSession().getServletContext().getContextPath();
		path = path +"/join.jsp";
		if(session.getAttribute("loginedUser") == null) {
			request.setAttribute("msg", "���ȵ�¼ϵͳ");
			response2.sendRedirect(path);
			return;
		}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
