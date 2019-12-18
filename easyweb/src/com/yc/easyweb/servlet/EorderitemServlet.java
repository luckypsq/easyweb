package com.yc.easyweb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
	private EorderitemBiz eBiz = new EorderitemBiz();
	private BookBiz bookBiz = new BookBiz();
	private Gson gson = new Gson();
	private Result result;

	// ɾ��
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		Eorderitem eorderitem = new Eorderitem();
		HttpSession session = request.getSession();
		String itemid = request.getParameter("itemid");
		try {
			if (itemid != null && !itemid.isEmpty()) {
				eorderitem.setItemid(itemid);
			} else {
				result = Result.failure("δѡ���ﳵ��Ʒ������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			int code = eBiz.delete(eorderitem);
			if (code > 1) {
				result = Result.success("ɾ���ɹ�������");
				//����ˢ��
				User userOld = (User) session.getAttribute("loginedUser");
				Bought bou = new Bought();
				bou.setUid(userOld.getUid());
				bou.setCartstate(1);
				Page<Bought> Page = eBiz.ePage(1, 6, bou);
				session.setAttribute("cartPage", Page);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("ɾ��ʧ�ܣ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
		} catch (BizException e) {
			result = Result.error(e.getMessage());
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		} catch (SQLException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
	}

	// �û���ӹ�����Ϣ
	public void add(HttpServletRequest request, HttpServletResponse response) {
		Eorderitem eod = new Eorderitem();
		HttpSession session = request.getSession();
		try {
			Book bookOld;
			if (request.getParameter("bid") != null && !request.getParameter("bid").isEmpty()) {
				eod.setBid(Long.parseLong(request.getParameter("bid")));
				BookBiz biz = new BookBiz();
				Book book = new Book();
				book.setBid(Long.parseLong(request.getParameter("bid")));
				book.setBstate(1);
				bookOld = biz.selectSingle(book);
				if (bookOld != null) {
					eod.setTotal(bookOld.getBprice());
				} else {
					result = Result.lack("�ñ����ѱ��¼ܻ�ɾ��������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
			} else {
				result = Result.lack("��ѡ���鼮������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			User userOld = null;
			if (request.getSession().getAttribute("loginedUser") != null) {
				userOld = (User) request.getSession().getAttribute("loginedUser");
				if (userOld.getUid() != 0) {
					eod.setUid(userOld.getUid());
				} else {
					result = Result.lack("���ȵ�¼������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
			} else {
				result = Result.lack("���ȵ�¼������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// ��ȡϵͳʱ��
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			eod.setCarttime(df.format(date));
			// ���ɶ�����
			String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			eod.setItemid(uuid);
			eod.setCount(1);
			int i = eBiz.insert(eod);
			if (i > 0) {
				result = Result.success("��ӳɹ�������");
				//�Ự��ԭ
				Map<String, String> map = new HashMap<String, String>();
				session.setAttribute("customerOrderAdd", map);
				Bought eo = new Bought();
				session.setAttribute("userOrderAddItem", eo);
				//����ˢ��
				// ���ﳵ��Ϣ��ʾ
				Bought bought = new Bought();
				List<Bought> listEo = eBiz.selectAllCart(bought);
				session.setAttribute("userCart", listEo);
				bought.setCartstate(1);
				bought.setUid(userOld.getUid());
				Page<Bought> pageCart =  eBiz.ePage(1, 6, bought);
				session.setAttribute("cartPage", pageCart);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			} else {
				result = Result.failure("���ʧ�ܣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
		} catch (SQLException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
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
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
	}

	// ��ѯ���ﳵ����
	public void query(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String pageParam = request.getParameter("page");
		int ipage = pageParam == null ? 1 : Integer.parseInt(pageParam);
		// ÿҳ����
		int rows = 6;
		User userOld = (User) session.getAttribute("loginedUser");
		Bought bou = new Bought();
		bou.setUid(userOld.getUid());
		bou.setCartstate(1);
		try {
			Page<Bought> Page = eBiz.ePage(ipage, rows, bou);
			session.setAttribute("cartPage", Page);
			if (Page.getData().size() == 0) {
				result = Result.failure("�������ݣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.success("��ѯ�ɹ�������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
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
			}
		}
	}

	// ����
	public void update(HttpServletRequest request, HttpServletResponse response) {
		String itemid = request.getParameter("itemid");
		String count = request.getParameter("count");
		Eorderitem eoNew = new Eorderitem();
		Eorderitem eoOld = new Eorderitem();
		HttpSession session = request.getSession();
		try {
			if (itemid != null && !itemid.isEmpty() && count != null && !count.isEmpty()) {
				Eorderitem eorderitem = new Eorderitem();
				eorderitem.setItemid(itemid);
				Eorderitem eoSelect = eBiz.selectSingle(eorderitem);
				if(eoSelect.getBid() == 0){
					result = Result.failure("�����Ҳ����򲻴��ڣ�����");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				Book book = new Book();
				book.setBid(eoSelect.getBid());
				Book book2 = bookBiz.selectSingle(book);
				double  total = book2.getBprice() * Double.parseDouble(count);
				eoNew.setTotal(total);
				eoNew.setCount(Integer.parseInt(count));
				eoOld.setItemid(itemid);
				int code = eBiz.update(eoNew, eoOld);
				if (code > 0) {
					DecimalFormat df = new DecimalFormat("#.00");
					result = Result.success("�޸ĳɹ�������", df.format(total));
					//����ˢ��
					User userOld = (User) session.getAttribute("loginedUser");
					Bought bou = new Bought();
					bou.setUid(userOld.getUid());
					bou.setCartstate(1);
					Page<Bought> Page = eBiz.ePage(1, 6, bou);
					session.setAttribute("cartPage", Page);
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				result = Result.failure("�޸�ʧ�ܣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("�޸ĵ�����δ��д������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
		} catch (SQLException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
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
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		}
	}

	// ��Ҫ�µ��Ĺ��ﳵid����Ự��
	public void buyBook(HttpServletRequest request, HttpServletResponse response) {
		String itemid = request.getParameter("itemid");
		HttpSession session = request.getSession();
		Bought eo;
		try {
			if (itemid != null && !itemid.isEmpty()) {
				User userOld = (User) session.getAttribute("loginedUser");
				Bought bought = new Bought();
				bought.setItemid(itemid);
				 eo = eBiz.selectSingleCart(bought);
				if(eo.getUid() != userOld.getUid()){
					result = Result.failure("�ù��ﳵ��Ʒ�����ڣ�����");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return ;
				}
				result = Result.success("��ѯ�ɹ�������");
				Map<String, String> map = new HashMap<String, String>();
				map.put("bname", eo.getBname());
				map.put("bprice", eo.getBprice()+"");
				map.put("total", eo.getTotal()+"");
				map.put("bimg", eo.getBimg());
				map.put("count", eo.getCount()+"");
				session.setAttribute("customerOrderAdd", map);
				session.setAttribute("userOrderAddItem", eo);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			result = Result.failure("δָ�����ﳵ��Ʒ������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return ;
		} catch (IOException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
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
			}
		}
	}
}
