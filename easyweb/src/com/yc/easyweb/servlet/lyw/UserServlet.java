package com.yc.easyweb.servlet.lyw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.dao.lyw.UserDaoLyw;
import com.yc.easyweb.servlet.BaseServlet;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoLyw dao =new UserDaoLyw();
	
	public void remember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user =(User)request.getSession().getAttribute("loginedUser");
		String path = this.getServletContext().getContextPath();
		String uminname = request.getParameter("uminname");
		String uphone = request.getParameter("uphone");
		String university = request.getParameter("university");
		String ucollege = request.getParameter("ucollege");
		String umajor = request.getParameter("umajor");
		String uclass = request.getParameter("uclass");
		Long uid = user.getUid();
		String url = path+"/lywoption/member.jsp";
		try {
			int i=dao.update(uminname,uphone,university,ucollege,umajor,uclass,uid);
			if(i==1){
				request.setAttribute("result","个人信息修改成功");
				request.getRequestDispatcher(url).forward(request, response);
				
            }else{
                request.setAttribute("result","个人信息修改失败");
                request.getRequestDispatcher(url).forward(request, response);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}