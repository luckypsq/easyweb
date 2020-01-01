package com.yc.easyweb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.easyweb.bean.*;
import com.yc.easyweb.biz.*;

public class EorderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private EorderitemBiz itemBiz = new EorderitemBiz();
	private EorderBiz eorderBiz = new EorderBiz();
	private BookBiz biz = new BookBiz();
	private Gson gson = new Gson();
	private Result result;

	// ɾ��
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		Eorder eorder;
		Eorder eorder1 = new Eorder();
		List<Eorder> list = new ArrayList<Eorder>();
		String eoid1 = request.getParameter("eoid");
		HttpSession session = request.getSession();
		try {
			// ��ȡeoid
			if (eoid1 != null && !eoid1.isEmpty()) {
				String[] eoid = eoid1.split("/");
				if (eoid.length == 1) {
					if (!eoid[0].isEmpty() && eoid[0] != null) {
						eorder1.setEoid(eoid[0]);
						Eorder eorder2 = eorderBiz.selectSingle(eorder1);
						if (eorder2.getEostate() != 5 && eorder2.getEostate() != 6) {
							result = Result.failure("�ý�����δ��ɲ���ɾ��������");
							String json = gson.toJson(result);
							response.setContentType("application/json;charset=UTF-8");
							response.getWriter().append(json);
							return;
						}
						int code = eorderBiz.delete(eorder1);
						if (code > 0) {
							result = Result.success("ɾ���ɹ�������");
							// ����ˢ��
							Eorder eorder3 = new Eorder();
							Page<Eorder> page = eorderBiz.eorderPage(1, 5, eorder3);
							session.setAttribute("userOrderPage", page);
							
							//����Ա��������ˢ��
							OrderDetial orderDetial = new OrderDetial();
							List<OrderDetial> order_show = eorderBiz.selectAllDetail(orderDetial);
							for (int i = 0; i < order_show.size(); i++) {
								if (order_show.get(i).getEostate() == 4 || order_show.get(i).getEostate() == 5
										|| order_show.get(i).getEostate() == 7) {
									order_show.remove(i);
									i--;
								}
							}
							session.setAttribute("eorderHand", order_show);
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
					}
					result = Result.failure("δѡ�񶩵����޷����д˲���������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				for (String string : eoid) {
					eorder = new Eorder();
					if (!string.isEmpty() && string != null) {
						eorder.setEoid(string);
						Eorder eorder2 = eorderBiz.selectSingle(eorder);
						if (eorder2.getEostate() != 5 && eorder2.getEostate() != 6) {
							result = Result.failure("���ڽ�����δ��ɵĶ��������ܽ��д˲���������");
							String json = gson.toJson(result);
							response.setContentType("application/json;charset=UTF-8");
							response.getWriter().append(json);
							return;
						}
						list.add(eorder);
					}
				}
				if (list.size() != 0) {
					int j = eorderBiz.delete(list);
					if (j > 0) {
						result = Result.success("ɾ���ɹ�������");
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
				} else {
					result = Result.failure("δѡ�񶩵�,���ܽ��д˲���������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
			}
			result = Result.failure("δѡ�񶩵�,���ܽ��д˲���������");
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

	// �û�������ѯ
	public void queryUserOrder(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String pageParam = request.getParameter("page");
		int ipage = pageParam == null ? 1 : Integer.parseInt(pageParam);
		// ÿҳ����
		int rows = 5;
		User user = (User) session.getAttribute("loginedUser");

		Eorder eorder = new Eorder();
		if (user != null) {
			eorder.setUid(user.getUid());
		} else {
			eorder.setUid(0);
		}
		try {
			Page<Eorder> page = eorderBiz.eorderPage(ipage, rows, eorder);
			session.setAttribute("userOrderPage", page);
			if (page.getData().size() == 0) {
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

	// �û���д������Ϣչʾ
	public void showEorder(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BookBiz biz = new BookBiz();

		String bid = request.getParameter("bid");
		try {
			if (bid != null && !bid.isEmpty()) {
				Book book = new Book();
				book.setBid(Long.parseLong(bid));
				Book book2 = biz.selectSingle(book);
				if (book2.getBid() != 0) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("bname", book2.getBname());
					map.put("bprice", book2.getBprice() + "");
					map.put("total", book2.getBprice() + "");
					map.put("bimg", book2.getBimg());
					map.put("count", "1");
					session.setAttribute("customerOrderAdd", map);
					result = Result.success("��ѯ�ɹ�������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				} else {
					result = Result.failure("���鼮�ѱ�ɾ�����¼ܣ�����");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
				}
			} else {
				result = Result.failure("δѡ���鼮������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
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

	// �˶���Ӷ����ĵ�ַ
	public void checkEoaddr(HttpServletRequest request, HttpServletResponse response) {
		String reg = "^[\u4e00-\u9fa5A-Za-z0-9]{5,300}$";
		HttpSession session = request.getSession();
		String eoaddr = request.getParameter("eoaddr");
		try {
			if (eoaddr != null && !eoaddr.isEmpty()) {
				if (!eoaddr.matches(reg)) {
					result = Result.failure("���벻�Ϸ�(5~100���ַ�����)");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				// ����ַ�����ڻỰ��
				session.setAttribute("addOrderEoaddr", eoaddr);
				result = Result.success("����Ϸ�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("��ַδ���룡����");
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
		}
	}

	// �˶���Ӷ������ռ���
	public void checkUname(HttpServletRequest request, HttpServletResponse response) {
		String reg = "^[\u4e00-\u9fa5a-zA-Z]{2,20}$";
		HttpSession session = request.getSession();
		String uname = request.getParameter("uname");
		try {
			if (uname != null && !uname.isEmpty()) {
				if (!uname.matches(reg)) {
					result = Result.failure("���벻�Ϸ�(2~10���ַ�����)������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				// ���ռ��˱����ڻỰ��
				session.setAttribute("addOrderUname", uname);
				result = Result.success("����Ϸ�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("�ռ���δ���룡����");
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
		}
	}

	// �˶���Ӷ���������
	public void checkCount(HttpServletRequest request, HttpServletResponse response) {
		String reg = "^[0-9]{1,10}$";
		HttpSession session = request.getSession();
		String count = request.getParameter("count");
		String price = request.getParameter("price");
		try {
			if (count != null && !count.isEmpty()) {
				if (!count.matches(reg)) {
					result = Result.failure("���벻�Ϸ�(1~10λ����)");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				double total = Double.parseDouble(price) * Double.parseDouble(count);
				DecimalFormat df = new DecimalFormat("#.00");
				session.setAttribute("addOrderCount", count);
				result = Result.success("����Ϸ�������", df.format(total));
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("���벻�Ϸ�(1~10λ����)");
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
		}
	}

	// �˶���Ӷ����ĵ绰
	public void checkUphone(HttpServletRequest request, HttpServletResponse response) {
		String reg = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		HttpSession session = request.getSession();
		String uphone = request.getParameter("uphone");
		try {
			if (uphone != null && !uphone.isEmpty()) {
				if (!uphone.matches(reg)) {
					result = Result.failure("���벻�Ϸ�(11λ����)������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				// ���绰�����ڻỰ��
				session.setAttribute("addOrderUphone", uphone);
				result = Result.success("����Ϸ�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("�绰δ���룡����");
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
		}
	}

	// �û���Ӷ���
	public void add(HttpServletRequest request, HttpServletResponse response) {
		// ����id
		String uuidOrder = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		HttpSession session = request.getSession();
		User userOld = (User) session.getAttribute("loginedUser");
		Eorderitem eoitem = new Eorderitem();// ͨ���鼮��Ӳ����Ĺ��ﳵ��Ϣ
		Eorderitem eoReal = null;// ��ʵ�Ĺ��ﳵ��Ϣ
		Eorder eorder = new Eorder();// ������Ϣ

		// 1.�ȴӻỰ�л�ȡ��id��itemid
		String bid = request.getParameter("bid");
		// 2.�����е����ݻ�ȡ����
		String count = (String) session.getAttribute("addOrderCount");// request.getParameter("count");//����
		String eoaddr = (String) session.getAttribute("addOrderEoaddr");// request.getParameter("eoaddr");//��ַ
		String uname = (String) session.getAttribute("addOrderUname");// request.getParameter("uname");//�ջ���
		String uphone = (String) session.getAttribute("addOrderUphone"); // request.getParameter("uphone");//�绰

		String type = request.getParameter("payType");// �ͻ���ʽ
		String payType = request.getParameter("payOption");// ֧����ʽ
		String totalOld = request.getParameter("total");// �ܼ�

		double total = 0;
		String sendType;
		int pay = 0;
		try {
			Bought itemOld = (Bought) session.getAttribute("userOrderAddItem");
			// 3.�ж����ݵĺϷ�
			String check = "1";
			// a.��֤����������
			if (count != null && !count.isEmpty()) {
				check = check + "/1";
			} else {
				if (request.getParameter("count") != null && !request.getParameter("count").isEmpty()) {
					count = request.getParameter("count");
					check = check + "/1";
				} else {
					check = check + "/-1";
				}
			}
			// b.��֤��ַ������
			if (eoaddr != null && !eoaddr.isEmpty()) {
				check = check + "/1";
			} else {
				check = check + "/-1";
			}
			// c.��֤�ջ��˵�����
			if (uname != null && !uname.isEmpty()) {
				check = check + "/1";
			} else {
				check = check + "/-1";
			}
			// d.��֤�绰������
			if (uphone != null && !uphone.isEmpty()) {
				check = check + "/1";
			} else {
				check = check + "/-1";
			}
			if (!check.equals("1/1/1/1/1")) {
				result = Result.lack("������Ϣ���㣡����", check);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// �ж��ܼ��Ƿ�Ϊ��
			if (totalOld != null && !totalOld.isEmpty()) {
				total = Double.parseDouble(totalOld);
			} else {
				result = Result.failure("ϵͳ��æ,���Ժ����ԣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// �ж��ͻ���ʽ
			if (!type.equals("��ѡ��")) {
				sendType = type;
			} else {
				sendType = "����֧��";
			}
			// �ж�֧����ʽ
			if (!payType.equals("��ѡ��")) {
				pay = Integer.parseInt(payType);
			} else {
				pay = 2;
			}
			// 4.�ж���ͨ���鱾�µ����ǹ��ﳵ�µ�
			if (userOld == null) {
				result = Result.failure("�����ȵ�¼������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// �����������Ķ���
			if (bid != null && !bid.isEmpty()) {
				// ��ѯ�����Ƿ��ϼ�
				Book checkBook = new Book();
				checkBook.setBid(Long.parseLong(bid));
				Book bookOld = biz.selectSingle(checkBook);
				if (bookOld != null && bookOld.getBstate() == 1 && bookOld.getBnum() > 0) {
					eoitem.setCount(Integer.parseInt(count));// �������ֵ
					eoitem.setTotal(total);// ����ܼ�
				} else {
					result = Result.failure("�������¼ܻ�����,������ѡ�񣡣���");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				if (userOld.getUid() != 0) {
					eoitem.setUid(userOld.getUid());// ���uidֵ
				} else {
					result = Result.failure("�����ȵ�¼������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				// ��ȡϵͳʱ��
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				eoitem.setCarttime(df.format(date));// ��ӹ��ﳵ����ʱ��
				// ���ɶ�����
				String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
				eoitem.setItemid(uuid);// ��ӹ��ﳵ��
				eoitem.setBid(Long.parseLong(bid));// �����id
				int i = itemBiz.insert(eoitem);
				if (i <= 0) {
					result = Result.failure("�µ�ʧ��,�����Ժ��ڲ���������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				eoReal = itemBiz.selectSingle(eoitem);
			} else if (itemOld != null && itemOld.getItemid() !=null && !itemOld.getItemid().isEmpty()) {
				// ��ѯ�˹��ﳵ�����Ƿ����
				Bought item = itemBiz.selectSingleCart(itemOld);
				Eorderitem itemOld1 = new Eorderitem();
				itemOld1.setItemid(item.getItemid());
				Eorderitem itemNew = new Eorderitem();
				if (item.getBid() != 0 && item.getUid() == userOld.getUid() && item.getCartstate() != 2) {
					itemNew.setCount(Integer.parseInt(count));
					itemNew.setTotal(total);
				}
				
				int k = itemBiz.update(itemNew, itemOld1);
				if (k <= 0) {
					result = Result.failure("�µ�ʧ��,�����Ժ��ڲ���������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				eoReal = itemBiz.selectSingle(itemOld1);
			} else {
				result = Result.failure("��δѡ���κ���Ʒ���ܽ��д˲���������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}

			// 5.��eorder������
			// ��ȡϵͳʱ��
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			eorder.setEotime(df.format(date));// �µ�ʱ��
			eorder.setEoid(uuidOrder);// ������
			eorder.setEostate(2);// ����״̬(������)
			eorder.setUid(userOld.getUid());// �û�id
			eorder.setEoaddr(eoaddr);// ��ַ
			eorder.setEotype(sendType);// �ͻ�����
			eorder.setEopaytypeid(pay);// ֧����ʽ
			eorder.setUname(uname);// �ջ���
			eorder.setEotemp(uphone);// �ջ��绰

			// �Ƚ�����ȥ
			Book book = new Book();
			book.setBid(eoReal.getBid());
			Book bookOld = biz.selectSingle(book);
			Book bookNew = new Book();
			// ��治�������µ�
			if (bookOld.getBnum() < Long.parseLong(count)) {
				result = Result.failure("��治��,��������ѡ������������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			long number = bookOld.getBnum() - Long.parseLong(count);
			bookNew.setBnum(number);
			int q = biz.update(bookNew, bookOld);
			// �鼮��Ϣû�и��£������µ�
			if (q <= 0) {
				result = Result.failure("�µ�ʧ��,�����Ժ����ԣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			int j = eorderBiz.insert(eorder);
			if (j > 0) {
				// �����ﳵ��Ϣ����(eoRealΪ���ﳵ����Ϣ)
				Eorderitem eoRealNew = new Eorderitem();
				eoRealNew.setCartstate(2);
				eoRealNew.setEoid(uuidOrder);
				int n = itemBiz.update(eoRealNew, eoReal);
				if (n > 0) {
					result = Result.success("�µ��ɹ�������");

					// ����ˢ��
					
					// ��ѯ���ж���������Ϣ
					Eorder eorder1 = new Eorder();
					eorder.setUid(userOld.getUid());
					Page<Eorder> Page = eorderBiz.eorderPage(1, 3, eorder1);
					session.setAttribute("userOrderPage", Page);
					// ���ﳵ��Ϣ��ʾ
					Bought bought = new Bought();
					bought.setUid(userOld.getUid());
					bought.setCartstate(1);
					Page<Bought> pageCart = itemBiz.ePage(1, 6, bought);
					session.setAttribute("cartPage", pageCart);
					bought.setCartstate(2);
					List<Bought> listEo = itemBiz.selectAllCart(bought);
					session.setAttribute("userCart", listEo);
					// �Ự��ԭ
					String string = null;
					session.setAttribute("addOrderCount", string);
					session.setAttribute("addOrderEoaddr", string);
					session.setAttribute("addOrderUname", string);
					session.setAttribute("addOrderUphone", string);

					Eorderitem eorderitem = new Eorderitem();
					session.setAttribute("userOrderAddItem", eorderitem);
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
			}
			bookNew.setBnum(bookOld.getBnum());
			int p = biz.update(bookNew, bookOld);
			// �µ�ʧ�ܱ��뽫�鼮��滹ԭ
			if (p > 0) {
				result = Result.failure("�µ�ʧ��,�����Ժ����ԣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			} else {
				throw new RuntimeException();
			}
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

	// �����ѯ����
	public void query(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		OrderDetial orderDetial = new OrderDetial();
		String eotime = request.getParameter("eotime");
		String eoid = request.getParameter("eoid");
		String eostate = request.getParameter("eostate");
		String type = request.getParameter("type");
		// ��ȡ��ѯ����
		if (eotime != null && !eotime.isEmpty()) {
			orderDetial.setEotime(eotime);
		}
		if (eoid != null && !eoid.isEmpty()) {
			orderDetial.setEoid(eoid);
		}
		if (eostate != null && !eostate.isEmpty()) {
			orderDetial.setEostate(Integer.parseInt(eostate));
		}
		try {
			// ���ݸ���
			List<OrderDetial> order_show = eorderBiz.selectAllDetail(orderDetial);
			List<OrderDetial> orderRefund = new ArrayList<OrderDetial>();
			List<OrderDetial> orderHand = new ArrayList<OrderDetial>();
			for (int i = 0; i < order_show.size(); i++) {
				if (order_show.get(i).getEostate() == 4 || order_show.get(i).getEostate() == 5
						|| order_show.get(i).getEostate() == 7) {
					orderRefund.add(order_show.get(i));
				}else{
					orderHand.add(order_show.get(i));
				}
			}
			session.setAttribute("eorderHand", orderHand);
			long[] numHand = { 0, 0, 0, 0 };
			for (OrderDetial order_main : orderHand) {
				if (order_main.getEostate() == 1) {
					numHand[0] = numHand[0] + 1;
				} else if (order_main.getEostate() == 2) {
					numHand[1] = numHand[1] + 1;
				} else if (order_main.getEostate() == 3) {
					numHand[2] = numHand[2] + 1;
				} else if (order_main.getEostate() == 6) {
					numHand[3] = numHand[3] + 1;
				}
			}
			session.setAttribute("orderNum", numHand);
			//��ѯ�˿�����
			session.setAttribute("eorderRefund", orderRefund);
			if(type != null && !type.isEmpty()){
				if(type.equals("0")){
					if (orderRefund.size() > 0) {
						result = Result.success("��ѯ�ɹ�������");
						String json = gson.toJson(result);
						response.setContentType("application/json;charset=UTF-8");
						response.getWriter().append(json);
						return;
					}
					result = Result.failure("�������ݣ�����");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				if(type.equals("1")){
					if (orderHand.size() > 0) {
						result = Result.success("��ѯ�ɹ�������");
						String json = gson.toJson(result);
						response.setContentType("application/json;charset=UTF-8");
						response.getWriter().append(json);
						return;
					}
					result = Result.failure("�������ݣ�����");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				
			}
			if (order_show.size() > 0) {
				result = Result.success("��ѯ�ɹ�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("�������ݣ�����");
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

	// ����Ա���¶���
	public void update(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Eorder eorderNew = new Eorder();
		Eorder eorderOld = new Eorder();
		String eoid = request.getParameter("eoid");
		String eostate = request.getParameter("eostate");
		String eopress = request.getParameter("eopress");
		String type = request.getParameter("type");
		if (eostate != null && !eostate.isEmpty()) {
			eorderNew.setEostate(Integer.parseInt(eostate));
		}
		if (eopress != null && !eopress.isEmpty()) {
			eorderNew.setEoespress(eopress);
		}
		if (type != null && !type.isEmpty()) {
			eorderNew.setEotype(type);
		}
		try {
			// ��ȡeoid
			if (eoid != null && !eoid.isEmpty()) {
				eorderOld.setEoid(eoid);
			} else {
				result = Result.failure("δѡ�񶩵�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			//����
			int code = eorderBiz.update(eorderNew, eorderOld);
			if (code <= 0) {
				result = Result.failure("����ʧ�ܣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.success("���³ɹ�������");
			//����ˢ��
			//�洢�����˿�����Ķ���
			OrderDetial eorder1 = new OrderDetial();
			List<OrderDetial> order_show = eorderBiz.selectAllDetail(eorder1);
			for (int i = 0; i < order_show.size(); i++) {
				if (order_show.get(i).getEostate() == 4 || order_show.get(i).getEostate() == 5
						|| order_show.get(i).getEostate() == 7) {
					order_show.remove(i);
					i--;
				}
			}
			session.setAttribute("eorderHand", order_show);
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
	// ��ѯ������������
	public void querySingle(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		OrderDetial order_Detial = new OrderDetial();
		String eoid = request.getParameter("eoid");
		// ��ȡ��ѯ����
		if (eoid != null && !eoid.isEmpty()) {
			order_Detial.setEoid(eoid);
		}
		try {
			OrderDetial rDetial = eorderBiz.selectSingleDetail(order_Detial);
			if (rDetial == null) {
				result = Result.failure("����������,���ѱ�ɾ��������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			session.setAttribute("orderdetialshow", rDetial);
			result = Result.success("��ѯ�ɹ�������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
		}catch (IOException e) {
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
