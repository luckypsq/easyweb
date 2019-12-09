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

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.EorderitemBiz;

public class EorderitemServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	EorderitemBiz eBiz = new EorderitemBiz();
	//查询
	public void  query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
	}
	//更新
	public void  update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
