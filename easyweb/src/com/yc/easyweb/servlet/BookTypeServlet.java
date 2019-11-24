package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.glass.ui.Size;
import com.yc.easyweb.bean.BookType;
import com.yc.easyweb.bean.BookTypeChild;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookTypeBiz;

public class BookTypeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookType bookType = new BookType();
	private BookTypeBiz bookTypeBiz = new BookTypeBiz();

	// 查询
	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BookType> bList = bookTypeBiz.selectAll(bookType);
		List<BookTypeChild> bChilds = new ArrayList<BookTypeChild>();
		BookTypeChild bChild;
		List<BookType> bList1 = new ArrayList<BookType>();
		List<BookType> bList2 = new ArrayList<BookType>();
		List<BookType> bList3 = new ArrayList<BookType>();
		// 将类别分别保存
		for (BookType bookType : bList) {
			if (bookType.getBtname().equals("教材区")) {
				bList1.add(bookType);
			} else if ((bookType.getBtname().equals("工具书区"))) {
				bList2.add(bookType);
			} else {
				bList3.add(bookType);
			}
		}
		int id = 11;
		int pid = 111;
		for (BookType bt : bList1) {
			bChild = new BookTypeChild();
			if (bt.getBtstate() != 0) {
				bChild.setBtid(bt.getBtid());
				bChild.setBtname(bt.getBtname());
				bChild.setBtnamesecond(bt.getBtnamesecond());
				bChild.setBtnamethird(bt.getBtnamethird());
				if (bt.getBtnamesecond() != null) {
					bChild.setName(bt.getBtnamesecond());
					bChild.setId(pid++);
					bChild.setpId(id);
				} else {
					bChild.setName(bt.getBtname());
					bChild.setId(id);
					bChild.setpId(1);
				}
				bChilds.add(bChild);
			}
		}
		id++;
		for (BookType bt : bList2) {
			bChild = new BookTypeChild();
			if (bt.getBtstate() != 0) {
				bChild.setBtid(bt.getBtid());
				bChild.setBtname(bt.getBtname());
				bChild.setBtnamesecond(bt.getBtnamesecond());
				bChild.setBtnamethird(bt.getBtnamethird());
				if (bt.getBtnamesecond() != null) {
					bChild.setName(bt.getBtnamesecond());
					bChild.setId(pid++);
					bChild.setpId(id);
				} else {
					bChild.setName(bt.getBtname());
					bChild.setId(id);
					bChild.setpId(1);
				}
				bChilds.add(bChild);
			}
		}
		id++;
		for (BookType bt : bList3) {
			bChild = new BookTypeChild();
			if (bt.getBtstate() != 0) {
				bChild.setBtid(bt.getBtid());
				bChild.setBtname(bt.getBtname());
				bChild.setBtnamesecond(bt.getBtnamesecond());
				bChild.setBtnamethird(bt.getBtnamethird());
				if (bt.getBtnamesecond() != null) {
					bChild.setName(bt.getBtnamesecond());
					bChild.setId(pid++);
					bChild.setpId(id);
				} else {
					bChild.setName(bt.getBtname());
					bChild.setId(id);
					bChild.setpId(1);
				}
				bChilds.add(bChild);
			}
		}
		BookTypeChild btc = new BookTypeChild();
		btc.setBtstate(0);
		btc.setId(1);
		btc.setpId(0);
		btc.setName("书籍分类列表");
		btc.setOpen("true");
		bChilds.add(btc);

		Gson gson = new Gson();
		String json = gson.toJson(bChilds);
		System.out.println(json);
		String string = "[{ id:1, pId:0, name:'书籍分类列表', open:true}," + "{ id:11, pId:1, name:'教材区'},"
				+ "{ id:111, pId:11, name:'成功励志'}," + "{ id:112, pId:11, name:'法律'}]";
		// 返回json格式数据
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(string);
	}

	// 添加
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookType bookType = new BookType();
		PrintWriter out = response.getWriter();
		String realType = null;
		if (request.getParameter("namefrist") != null && !request.getParameter("namefrist").isEmpty()) {
			bookType.setBtname(request.getParameter("namefrist"));
			realType = request.getParameter("namefrist");
		}
		if (request.getParameter("namesecond") != null && !request.getParameter("namesecond").isEmpty()) {
			bookType.setBtnamesecond(request.getParameter("namesecond"));
			realType = request.getParameter("namesecond");
		}
		if (request.getParameter("namethird") != null && !request.getParameter("namethird").isEmpty()) {
			bookType.setBtnamethird(request.getParameter("namethird"));
			realType = request.getParameter("namethird");
		}
		// 查询数据库的类型
		BookType bookType1 = new BookType();
		bookType1.setBtstate(1);
		List<BookType> btList = bookTypeBiz.selectAll(bookType1);
		HashSet<String> btType = new HashSet<String>();
		for (BookType bt : btList) {
			if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
				btType.add(bt.getBtnamethird());
			} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
				btType.add(bt.getBtnamesecond());
			} else {
				btType.add(bt.getBtname());
			}
		}
		int lengthOld = btType.size();
		btType.add(realType);
		try {
			if (lengthOld != btType.size()) {
				int code = bookTypeBiz.insert(bookType);
				if (code > 0) {
					out.print("添加成功！！！");
				} else {
					out.print("添加失败！！！");
				}
			} else {
				out.print("已存在该类型！！！");
			}
		} catch (BizException e) {
			e.printStackTrace();
		}
	}

	// 删除
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookType bookTypeOld = new BookType();
		PrintWriter out = response.getWriter();
		if (request.getParameter("btid") != null && !request.getParameter("btid").isEmpty()) {
			bookTypeOld.setBtid(Long.parseLong(request.getParameter("btid")));
		} else {
			out.print("请选择删除的类型名！！！");
			return;
		}
		try {
			int code = bookTypeBiz.delete(bookTypeOld);
			if (code > 0) {
				out.print("删除成功！！！");
			} else {
				out.print("删除失败！！！");
			}
		} catch (BizException e) {
			e.printStackTrace();
		}
	}

	// 更新
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookType bookType = new BookType();
		BookType bookTypeOld = new BookType();
		PrintWriter out = response.getWriter();
		String realType = null;
		if (request.getParameter("btid") != null && !request.getParameter("btid").isEmpty()) {
			bookTypeOld.setBtid(Long.parseLong(request.getParameter("btid")));
		} else {
			out.print("请选择修改的类型名！！！");
			return;
		}
		if (request.getParameter("namefrist") != null && !request.getParameter("namefrist").isEmpty()) {
			bookType.setBtname(request.getParameter("namefrist"));
			realType = request.getParameter("namefrist");
		}
		if (request.getParameter("state") != null && !request.getParameter("state").isEmpty()) {
			bookType.setBtstate(Integer.parseInt(request.getParameter("state")));;
		}
		if (request.getParameter("namesecond") != null && !request.getParameter("namesecond").isEmpty()) {
			bookType.setBtnamesecond(request.getParameter("namesecond"));
			realType = request.getParameter("namesecond");
		}
		if (request.getParameter("namethird") != null && !request.getParameter("namethird").isEmpty()) {
			bookType.setBtnamethird(request.getParameter("namethird"));
			realType = request.getParameter("namethird");
		}
		// 查询数据库的类型
		BookType bookType1 = new BookType();
		bookType1.setBtstate(1);
		List<BookType> btList = bookTypeBiz.selectAll(bookType1);
		HashSet<String> btType = new HashSet<String>();
		for (BookType bt : btList) {
			if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
				btType.add(bt.getBtnamethird());
			} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
				btType.add(bt.getBtnamesecond());
			} else {
				btType.add(bt.getBtname());
			}
		}
		int lengthOld = btType.size();
		btType.add(realType);
		try {
			if (lengthOld != btType.size()) {
				int code = bookTypeBiz.update(bookType, bookTypeOld);
				if (code > 0) {
					out.print("更新成功！！！");
				} else {
					out.print("更新失败！！！");
				}
			} else {
				bookType.setBtstate(1);
				int code = bookTypeBiz.update(bookType, bookTypeOld);
				if (code > 0) {
					out.print("更新成功！！！");
				} else {
					out.print("更新失败！！！");
				}
			}
		} catch (BizException e) {
			e.printStackTrace();
		}
	}
}
