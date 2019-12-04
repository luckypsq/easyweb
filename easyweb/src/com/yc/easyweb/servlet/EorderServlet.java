package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.EorderBiz;
import com.yc.easyweb.biz.EorderitemBiz;

public class EorderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	EorderitemBiz eBiz = new EorderitemBiz();
	EorderBiz eorderBiz = new EorderBiz();
	BookBiz biz = new BookBiz();
	//查询
	public void  query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	//添加
	public void  add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Eorderitem eod = new Eorderitem();
		Eorderitem eodReal = null;
		Eorder eorder = new Eorder();
		Book book = new Book();
		String path = this.getServletContext().getContextPath();
		String uuidOrder = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		String url = path + "/lywoption/buy.jsp?msg=";
		User user = null;
		long count = 0;
		long bid = 0;
		if(request.getSession().getAttribute("loginedUser") != null ){
			user = (User)request.getSession().getAttribute("loginedUser");
		}else{
			url =url +3;
			response.sendRedirect(url);
			return;
		}
		if (request.getParameter("bid") != null && !request.getParameter("bid").toString().isEmpty()) {
				eod.setBid(Long.parseLong(request.getParameter("bid")));
				bid = Long.parseLong(request.getParameter("bid"));
				BookBiz biz = new BookBiz();
				book.setBid(Long.parseLong(request.getParameter("bid")));
				try {
					Book bookOld = biz.selectSingle(book);
					if(bookOld != null){
						if (request.getParameter("count") != null && !request.getParameter("count").toString().isEmpty()) {
							double  price = bookOld.getBprice() * Double.parseDouble(request.getParameter("count"));
							eod.setTotal(price);
							count = Integer.parseInt(request.getParameter("count"));
							eod.setCount(Integer.parseInt(request.getParameter("count")));
						}else{
							eod.setTotal(bookOld.getBprice());
						}
					}else{
						url =url +3;
						response.sendRedirect(url);
						return;
					}
				} catch (BizException e) {
					e.printStackTrace();
				}
				if(user.getUid() != 0){
					eod.setUid(user.getUid());
				}else{
					url =url +3;
					response.sendRedirect(url);
					return;
				}
			//获取系统时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			eod.setCarttime(df.format(date));
			//生成订单号
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			eod.setItemid(uuid);
			eod.setCartstate(2);
			eod.setEoid(uuidOrder);
			try {
				int i = eBiz.insert(eod);
				if(i <=0 ){
					url =url +3;
					response.sendRedirect(url);
					return;
				}
				eodReal = eBiz.selectSingle(eod);
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BizException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (request.getParameter("itemid") != null && !request.getParameter("itemid").toString().isEmpty()) {
			eod.setItemid(request.getParameter("itemid"));
			Eorderitem eoNew = new Eorderitem();
			if (request.getParameter("count") != null && !request.getParameter("count").toString().isEmpty()) {
				eoNew.setCount(Integer.parseInt(request.getParameter("count")));
			}
			if (request.getParameter("total") != null && !request.getParameter("total").toString().isEmpty()) {
				eoNew.setTotal(Double.parseDouble(request.getParameter("total")));
			}
			try {
				int k = eBiz.update(eoNew, eod);
				if(k <= 0){
					url =url +3;
					response.sendRedirect(url);
					return;
				}else{
					eodReal = eBiz.selectSingle(eod);
					bid = eodReal.getBid();
				}
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BizException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			url =url +3;
			response.sendRedirect(url);
			return;
		}
		//更新明细
		if(eodReal != null){
			Eorderitem eorderitemNew =new  Eorderitem();
			eorderitemNew.setEoid(uuidOrder);
			try {
				int j = eBiz.update(eorderitemNew, eodReal);
				if(j <= 0){
					url =url +3;
					response.sendRedirect(url);
					return;
				}
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BizException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//eodReal为购物车的信息
		if (request.getParameter("eoaddr") != null && !request.getParameter("eoaddr").toString().isEmpty()) {
			eorder.setEoaddr(request.getParameter("eoaddr"));
		}
		if (request.getParameter("uname") != null && !request.getParameter("uname").toString().isEmpty()) {
			eorder.setUname(request.getParameter("uname"));
		}
		if (request.getParameter("uphone") != null && !request.getParameter("uphone").toString().isEmpty()) {
			eorder.setEotemp(request.getParameter("uphone"));
		}
		if (request.getParameter("payType") != null && !request.getParameter("payType").toString().isEmpty()) {
			eorder.setEotype(request.getParameter("payType"));
		}
		if (request.getParameter("payOption") != null && !request.getParameter("payOption").toString().isEmpty()) {
			eorder.setEopaytypeid(Integer.parseInt(request.getParameter("payOption")));
		}
		//获取系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		eorder.setEotime(df.format(date));
		eorder.setEoid(uuidOrder);
		eorder.setEostate(2);
		if(user.getUid() != 0){
			eorder.setUid(user.getUid());
		}else{
			url =url +3;
			response.sendRedirect(url);
			return;
		}
			try {
				int o = eorderBiz.insert(eorder);
				if(o > 0){
					Book book3 = new Book();
					if(bid != 0){
						book3.setBid(bid);
					}
					Book bookOld = biz.selectSingle(book3);
					count = bookOld.getBnum() - count;
					Book bookNew = new Book();
					bookNew.setBnum(count);
					int q = biz.update(bookNew, bookOld);
					if(q <= 0){
						url =url +0;
						response.sendRedirect(url);
						return;
					}
					url =url +1;
				}else{
					url =url +0;
				}
				response.sendRedirect(url);
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
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BizException e) {
			// TODO Auto-generated catch block
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
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
