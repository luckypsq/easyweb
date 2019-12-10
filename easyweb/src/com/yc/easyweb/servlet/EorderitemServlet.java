package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Bought;
import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.bean.Page;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.EorderitemBiz;

public class EorderitemServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	EorderitemBiz eBiz = new EorderitemBiz();
	//查询
	public void  query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String pageParam = request.getParameter("Page");
		int ipage = pageParam == null ? 1 : Integer.parseInt(pageParam);
		// 每页行数
		int rows = 6;	
		User user =(User)session.getAttribute("loginedUser");
		long uid=user.getUid();
		EorderitemBiz eorderitemBiz = new EorderitemBiz();
		Bought bou = new Bought();
		Page<Bought>  Page = eorderitemBiz.ePage(ipage, rows, bou, uid);
		session.setAttribute("cartDate", Page.getData());
		session.setAttribute("cartPage", Page);
		if(Page.getData().size() == 0){
			out.print(-1);
		}
	}
	//添加
	public void  add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Eorderitem eod = new Eorderitem();
		PrintWriter out = response.getWriter();
		if(request.getParameter("bid") != null && !request.getParameter("bid").isEmpty()){
			eod.setBid(Long.parseLong(request.getParameter("bid")));
			BookBiz biz = new BookBiz();
			Book book = new Book();
			book.setBid(Long.parseLong(request.getParameter("bid")));
			try {
				Book bookOld = biz.selectSingle(book);
				if(bookOld != null){
					eod.setTotal(bookOld.getBprice());
				}else{
					out.print(2);
					return ;
				}
			} catch (BizException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			out.print(2);
			return ;
		}
		User user = null;
		if(request.getSession().getAttribute("loginedUser") != null ){
			user = (User)request.getSession().getAttribute("loginedUser");
			if(user.getUid() != 0){
				eod.setUid(user.getUid());
			}else{
				out.print(2);
				return ;
			}
		}else{
			out.print(2);
			return ;
		}
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		eod.setCarttime(df.format(date));
		//生成订单号
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		eod.setItemid(uuid);
		try {
			int i = eBiz.insert(eod);
			if(i > 0 ){
				out.print(1);
			}else{
				out.print(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//删除
	public void  delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Eorderitem eorderitem = new Eorderitem();
		PrintWriter out = response.getWriter();
		if(request.getParameter("itemid") != null && !request.getParameter("itemid").isEmpty()){
			eorderitem.setItemid(request.getParameter("itemid"));
		}
			
			try {
				int code = eBiz.delete(eorderitem);
				if(code > 1){
					out.print(1);
				}else{
					out.print(-1);
				}
			} catch (SQLException e) {
				out.print(-1);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BizException e) {
				out.print(-1);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	//更新
	public void  update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String itemid = null;
		String count=null;
		if(request.getParameter("itemid") != null && !request.getParameter("itemid").isEmpty()){
			itemid = request.getParameter("itemid");
		}
		if(request.getParameter("count") != null && !request.getParameter("count").isEmpty()){
			count=request.getParameter("count");
		}
		if(count == null || itemid == null){
			out.print(2);
			return;
		}
		Eorderitem eo = new Eorderitem();
		Eorderitem eoOld = new Eorderitem();
		eo.setCount(Integer.parseInt(count));
		eoOld.setItemid(itemid);
		session.setAttribute("cartCount", count);
		try{
			int code = eBiz.update(eo, eoOld);
			if(code > 0){
				out.print(1);
			}else{
				out.print(-1);
			}
		}catch (SQLException e){
			e.printStackTrace();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
