package com.yc.easyweb.servlet.pq;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yc.easyweb.common.DbHelper;
@WebServlet("/EditServlet.s")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		   //注册用户
		  String buniversity=request.getParameter("buniversity").trim();
		  String bucollege=request.getParameter("bucollege").trim();
		  String bumajor=request.getParameter("bumajor").trim();
		  String bclass=request.getParameter("bclass").trim();
		  String bname=request.getParameter("bname").trim();
		  String bprice=request.getParameter("bprice").trim();
		  String bcontent=request.getParameter("bcontent").trim();
		  String bimg=request.getParameter("bimg").trim();
		  
		  if(buniversity==null||bucollege==null||bumajor==null||bclass==null
			 ||bname==null||bprice==null||bcontent==null||bimg==null){
          //设置弹出框
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter pw=response.getWriter();
            pw.write("<script language='javascript'>alert('信息填写不完整')</script>");
		    return ;
		  } 
		  //正则判断
		      String regprice = "^([1-9][0-9]*)+(.[0-9]{1,2})?$";
		      String regname="^[.{2,}\u4e00-\u9fa5]{0,50}$";
		    if(!bprice.matches(regprice)||!bname.matches(regname)){
		      response.setContentType("text/html; charset=UTF-8");
			  PrintWriter pw=response.getWriter();
	          pw.write("<script language='javascript'>alert('信息填写不规范')</script>");
			  return ;  
		  }   
		  else {
		  String sql="insert into user "+
		  "values(null,?,?,?,?,?,?,?,?,null,null,null,null,null,null,null)";
		  try {
			DbHelper.update(sql,bname,buniversity,bucollege,bumajor,bclass,bcontent,bimg,bprice);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter pw=response.getWriter();
			pw.write("<script language='javascript'>alert('编辑成功！')</script>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 }
		 
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
