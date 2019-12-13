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

import com.google.gson.Gson;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Bought;
import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.bean.Page;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.EorderitemBiz;

public class EorderitemServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	EorderitemBiz eBiz = new EorderitemBiz();
	private  Gson gson = new Gson();
	private Result result ;
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
	
	//添加
		public void  add(HttpServletRequest request, HttpServletResponse response){
			Eorderitem eod = new Eorderitem();
			try {
				Book bookOld ;
				if(request.getParameter("bid") != null && !request.getParameter("bid").isEmpty()){
					eod.setBid(Long.parseLong(request.getParameter("bid")));
					BookBiz biz = new BookBiz();
					Book book = new Book();
					book.setBid(Long.parseLong(request.getParameter("bid")));
					book.setBstate(1);
					bookOld = biz.selectSingle(book);
					if(bookOld != null){
							eod.setTotal(bookOld.getBprice());
					}else{
						result = Result.lack("该本书已被下架或删除！！！");
						String json = gson.toJson(result);
						response.setContentType("application/json;charset=UTF-8");
						response.getWriter().append(json);
						return ;
					}
				}else{
					result = Result.lack("请选择书籍！！！");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return ;
				}
				User user = null;
				if(request.getSession().getAttribute("loginedUser") != null ){
					user = (User)request.getSession().getAttribute("loginedUser");
					if(user.getUid() != 0){
						eod.setUid(user.getUid());
					}else{
						result = Result.lack("请先登录！！！");
						String json = gson.toJson(result);
						response.setContentType("application/json;charset=UTF-8");
						response.getWriter().append(json);
						return ;
					}
				}else{
					result = Result.lack("请先登录！！！");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return ;
				}
				//获取系统时间
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				eod.setCarttime(df.format(date));
				//生成订单号
				String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
				eod.setItemid(uuid);
				eod.setCount(1);
				int i = eBiz.insert(eod);
				if(i > 0 ){
					result = Result.success("添加成功！！！");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);	
				}else{
					result = Result.failure("添加失败！！！");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);	
				}
			} catch (SQLException e) {
				result = Result.error("业务繁忙,请稍等几分钟再操作！！！");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				try {
					response.getWriter().append(json);
				} catch (IOException e1) {
					throw new RuntimeException(e1);
					// TODO Auto-generated catch block
				}			
				e.printStackTrace();
			} catch (BizException e) {
				result = Result.error(e.getMessage());
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				try {
					response.getWriter().append(json);
				} catch (IOException e1) {
					throw new RuntimeException(e1);
					// TODO Auto-generated catch block
				}
			} catch (IOException e) {
				result = Result.error("业务繁忙,请稍等几分钟再操作！！！");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				try {
					response.getWriter().append(json);
				} catch (IOException e1) {
					throw new RuntimeException(e1);
					// TODO Auto-generated catch block
				}			
				e.printStackTrace();
			}
		}
}
