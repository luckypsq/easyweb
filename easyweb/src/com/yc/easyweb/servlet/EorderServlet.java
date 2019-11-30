package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.EorderBiz;

public class EorderServlet extends BaseServlet {
	private static final long serialVersioneoid = 1L;
	EorderBiz eorderBiz = new EorderBiz();
	//查询
	public void  query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	//添加
	public void  add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	//删除
	public void  delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Eorder eorder;
		Eorder eorder1 =  new Eorder();
		
		List<Eorder> list = new ArrayList<Eorder>();
		try {
			//获取eoid
			if (request.getParameter("eoid") != null && !request.getParameter("eoid").toString().isEmpty()) {
				String[] eoid = request.getParameter("eoid").split("/");
				if (eoid.length == 1) {
					if (!eoid[0].isEmpty() && eoid[0] != null) {
						eorder1.setEoid(eoid[0]);
						int i= eorderBiz.delete(eorder1);
						if(i > 0 ){
							out.print(1);
						}else{
							out.print(0);
						}
						return;
					}
				}
				for (String string : eoid) {
					eorder = new Eorder();
					if (!string.isEmpty() && string != null) {
						eorder.setEoid(string);
						list.add(eorder);
					}
				}
				if (list.size() != 0) {
					int j = eorderBiz.delete(list);
					if (j > 0) {
						out.print(1);
					} else {
						out.print(0);
					}
				} else {
					out.print(0);
				}
			}else{
				out.print(2);
				return ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//更新
	public void  update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Eorder eorderNew = new Eorder();
		Eorder eorderOld = new Eorder();
		//获取eoid
		if (request.getParameter("eoid") != null && !request.getParameter("eoid").toString().isEmpty()) {
			eorderOld.setEoid(request.getParameter("eoid"));
		}
		if (request.getParameter("eostate") != null && !request.getParameter("eostate").toString().isEmpty()) {
			eorderNew.setEostate(Integer.parseInt(request.getParameter("eostate")));
		}
		if (request.getParameter("eopress") != null && !request.getParameter("eopress").toString().isEmpty()) {
			eorderNew.setEoespress(request.getParameter("eopress"));
		}
		if (request.getParameter("type") != null && !request.getParameter("type").toString().isEmpty()) {
			eorderNew.setEotype(request.getParameter("type"));
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		eorderNew.setEotime(df.format(date));
		try {
			int i = eorderBiz.update(eorderNew, eorderOld);
			if(i > 0){
				out.print(1);
			}else{
				out.print(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
