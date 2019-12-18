package com.yc.easyweb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.easyweb.bean.*;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.BookTypeBiz;

public class BookTypeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookTypeBiz bookTypeBiz = new BookTypeBiz();
	private BookBiz bookBiz = new BookBiz();
	private Gson gson = new Gson();
	private Result result;

	// ���
	public void add(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BookType bookType = new BookType();
		String nameSecond = (String) session.getAttribute("namesecond");
		String nameThird = (String) session.getAttribute("namethird");
		String realType = "";
		try {
			// 1.�ж�����Ϸ�
			if (nameSecond != null && !nameSecond.isEmpty()) {
				bookType.setBtnamesecond(nameSecond);
				realType = nameSecond;
			} else if (request.getParameter("namesecond") != null && !request.getParameter("namesecond").isEmpty()) {
				bookType.setBtnamesecond(request.getParameter("namesecond"));
				realType = request.getParameter("namesecond");
			} else {
				result = Result.failure("δ��д���ݻ����ݲ��Ϸ�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			if (nameThird != null && !nameThird.isEmpty()) {
				bookType.setBtnamethird(nameThird);
				realType = nameThird;
			}
			// 2.����
			// a.��ѯ���ݿ������(adminBtypesEdit�а������е��鼮���͵�����)
			String[] btypes = (String[]) session.getAttribute("adminBtypesEdit");
			// b.�Ƚ�
			for (String string : btypes) {
				if (string != null && !string.isEmpty()) {
					if (string.equals(realType)) {
						result = Result.failure("�Ѵ��ڸ����ͣ�����");
						String json = gson.toJson(result);
						response.setContentType("application/json;charset=UTF-8");
						response.getWriter().append(json);
						return;
					}
				}
			}
			// 3.���
			bookType.setBtname("�̲���");
			int code = bookTypeBiz.insert(bookType);
			if (code > 0) {
				result = Result.success("��ӳɹ�������");
				// �Ự��ԭ
				String st = null;
				session.setAttribute("namesecond", st);
				session.setAttribute("namethird", st);
				// ���������²�ѯ�͸�ֵ
				BookType bType = new BookType();
				bType.setBtname("�̲���");
				List<BookType> btypes1 = bookTypeBiz.selectAll(bType);
				for (int i = 0; i < btypes1.size(); i++) {
					if (btypes1.get(i).getBtnamesecond() != null && !btypes1.get(i).getBtnamesecond().isEmpty()) {
						continue;
					}
					btypes1.remove(i);
					// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
					i--;
				}
				String[] btShow = new String[1000];
				String bType1 = "";
				for (BookType bt : btypes1) {
					if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
						bType1 = bType1 + bt.getBtid() + "-" + bt.getBtname() + "-" + bt.getBtnamesecond();
					}
					if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
						bType1 = bType1 + "-" + bt.getBtnamethird();
					}
					bType1 = bType1 + "-" + bt.getBtstate();
					btShow[(int) bt.getBtid()] = bType1;
					bType1 = "";
				}
				session.setAttribute("adminRealBtypes", btShow);
				session.setAttribute("adminShowBtypes", btypes1);
				// ����Ա��ʼ���鼮����
				BookType bookType1 = new BookType();
				List<BookType> bookTypes = bookTypeBiz.selectAll(bookType1);
				session.setAttribute("adminBtypes", bookTypes);
				String[] type = new String[1000];
				String btname;
				for (BookType bt : bookTypes) {
					if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
						btname = bt.getBtnamethird();
					} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
						btname = bt.getBtnamesecond();
					} else {
						btname = bt.getBtname();
					}
					type[(int) bt.getBtid()] = btname;
				}
				session.setAttribute("adminBtypesEdit", type);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("���ʧ�ܣ�����");
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
				throw new RuntimeException(e);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,�����Ե�һ����ٲ���������");
			String json = gson.toJson(result);

			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		} catch (SQLException e) {
			result = Result.error("ҵ��æ,�����Ե�һ����ٲ���������");
			String json = gson.toJson(result);

			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		}
	}

	// ɾ��
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		BookType bookTypeOld = new BookType();
		HttpSession session = request.getSession();
		String btid = request.getParameter("btid");
		try {
			if (btid != null && !btid.isEmpty()) {
				bookTypeOld.setBtid(Long.parseLong(btid));
			} else {
				result = Result.failure("��ѡ��ɾ�������ͣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			Book bookNew = new Book();
			bookNew.setBtid(1);
			Book bookOld = new Book();
			bookOld.setBtid(Long.parseLong(btid));
			//1.�жϸ������Ƿ����鼮
			List<Book> list = bookBiz.selectAll(bookOld);
			if(list.size() > 0){
				// 2.���ϼܵĸ������鼮���ڽ̲���
				int j = bookBiz.update(bookNew, bookOld);
				// a.ʧ������ɾ��
				if (j <= 0) {
					result = Result.failure("ɾ��ʧ�ܣ�����");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
			}
			//3.����ɾ������
			// b.�ɹ�
			int code = bookTypeBiz.delete(bookTypeOld);
			if (code <= 0) {
				result = Result.failure("ɾ��ʧ�ܣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.success("ɾ���ɹ�������");
			// ����ˢ��
			BookType bType = new BookType();
			bType.setBtname("�̲���");
			List<BookType> btypes1 = bookTypeBiz.selectAll(bType);
			for (int i = 0; i < btypes1.size(); i++) {
				if (btypes1.get(i).getBtnamesecond() != null && !btypes1.get(i).getBtnamesecond().isEmpty()) {
					continue;
				}
				btypes1.remove(i);
				// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
				i--;
			}
			String[] btShow = new String[1000];
			String bType1 = "";
			for (BookType bt : btypes1) {
				if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
					bType1 = bType1 + bt.getBtid() + "-" + bt.getBtname() + "-" + bt.getBtnamesecond();
				}
				if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
					bType1 = bType1 + "-" + bt.getBtnamethird();
				}
				bType1 = bType1 + "-" + bt.getBtstate();
				btShow[(int) bt.getBtid()] = bType1;
				bType1 = "";
			}
			session.setAttribute("adminRealBtypes", btShow);
			session.setAttribute("adminShowBtypes", btypes1);
			// ����Ա��ʼ���鼮����
			BookType bookType1 = new BookType();
			List<BookType> bookTypes = bookTypeBiz.selectAll(bookType1);
			session.setAttribute("adminBtypes", bookTypes);
			String[] type = new String[1000];
			String btname;
			for (BookType bt : bookTypes) {
				if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
					btname = bt.getBtnamethird();
				} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
					btname = bt.getBtnamesecond();
				} else {
					btname = bt.getBtname();
				}
				type[(int) bt.getBtid()] = btname;
			}
			session.setAttribute("adminBtypesEdit", type);
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
				throw new RuntimeException(e);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,�����Ե�һ����ٲ���������");
			String json = gson.toJson(result);

			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		} catch (SQLException e) {
			result = Result.error("ҵ��æ,�����Ե�һ����ٲ���������");
			String json = gson.toJson(result);

			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		}
	}

	// ����
	public void update(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BookType bookType = new BookType();
		BookType bookTypeOld = new BookType();
		String btid = request.getParameter("btid");
		String state = request.getParameter("state");
		String nameSecond = (String) session.getAttribute("namesecond");
		String nameThird = (String) session.getAttribute("namethird");
		String realType = "";

		try {
			// 1.�ж�����Ϸ�
			if (state != null && !state.isEmpty()) {
				bookType.setBtstate(Integer.parseInt(state));
			} else if (nameSecond != null && !nameSecond.isEmpty()) {
				bookType.setBtnamesecond(nameSecond);
				realType = nameSecond;
			} else if (request.getParameter("namesecond") != null && !request.getParameter("namesecond").isEmpty()) {
				bookType.setBtnamesecond(request.getParameter("namesecond"));
				realType = request.getParameter("namesecond");
			} else {
				result = Result.failure("δ��д�޸ĵ����ݻ��޸ĵ����ݲ��Ϸ�������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			if (nameThird != null && !nameThird.isEmpty()) {
				bookType.setBtnamethird(nameThird);
				realType = nameThird;
			}
			// id
			if (btid != null && !btid.isEmpty()) {
				bookTypeOld.setBtid(Long.parseLong(btid));
			} else {
				result = Result.failure("δѡ���鼮���ͣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}

			// ����״̬
			if (state != null && !state.isEmpty()) {
				int code = bookTypeBiz.update(bookType, bookTypeOld);
				if (code > 0) {
					result = Result.success("�����ɹ�������");
					// ���������²�ѯ�͸�ֵ
					BookType bType = new BookType();
					bType.setBtname("�̲���");
					List<BookType> btypes = bookTypeBiz.selectAll(bType);
					for (int i = 0; i < btypes.size(); i++) {
						if (btypes.get(i).getBtnamesecond() != null && !btypes.get(i).getBtnamesecond().isEmpty()) {
							continue;
						}
						btypes.remove(i);
						// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
						i--;
					}
					String[] btShow = new String[1000];
					String bType1 = "";
					for (BookType bt : btypes) {
						if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
							bType1 = bType1 + bt.getBtid() + "-" + bt.getBtname() + "-" + bt.getBtnamesecond();
						}
						if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
							bType1 = bType1 + "-" + bt.getBtnamethird();
						}
						bType1 = bType1 + "-" + bt.getBtstate();
						btShow[(int) bt.getBtid()] = bType1;
						bType1 = "";
					}
					session.setAttribute("adminRealBtypes", btShow);
					session.setAttribute("adminShowBtypes", btypes);
					// ����Ա��ʼ���鼮����
					BookType bookType1 = new BookType();
					List<BookType> bookTypes = bookTypeBiz.selectAll(bookType1);
					session.setAttribute("adminBtypes", bookTypes);
					String[] type = new String[1000];
					String btname;
					for (BookType bt : bookTypes) {
						if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
							btname = bt.getBtnamethird();
						} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
							btname = bt.getBtnamesecond();
						} else {
							btname = bt.getBtname();
						}
						type[(int) bt.getBtid()] = btname;
					}
					session.setAttribute("adminBtypesEdit", type);
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				result = Result.failure("����ʧ�ܣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// ������������
			// 2.����
			// a.��ѯ���ݿ������(adminBtypesEdit�а������е��鼮���͵�����)
			String[] btypes = (String[]) session.getAttribute("adminBtypesEdit");
			// b.�Ƚ�
			for (String string : btypes) {
				if (string != null && !string.isEmpty()) {
					if (string.equals(realType)) {
						result = Result.failure("�Ѵ��ڸ����ͣ�����");
						String json = gson.toJson(result);
						response.setContentType("application/json;charset=UTF-8");
						response.getWriter().append(json);
						return;
					}
				}
			}
			// 3.����
			int code = bookTypeBiz.update(bookType, bookTypeOld);
			if (code > 0) {
				result = Result.success("���³ɹ�������");
				// �Ự��ԭ
				String st = null;
				session.setAttribute("namesecond", st);
				session.setAttribute("namethird", st);
				// ���������²�ѯ�͸�ֵ
				BookType bType = new BookType();
				bType.setBtname("�̲���");
				List<BookType> btypes1 = bookTypeBiz.selectAll(bType);
				for (int i = 0; i < btypes1.size(); i++) {
					if (btypes1.get(i).getBtnamesecond() != null && !btypes1.get(i).getBtnamesecond().isEmpty()) {
						continue;
					}
					btypes1.remove(i);
					// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
					i--;
				}
				String[] btShow = new String[1000];
				String bType1 = "";
				for (BookType bt : btypes1) {
					if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
						bType1 = bType1 + bt.getBtid() + "-" + bt.getBtname() + "-" + bt.getBtnamesecond();
					}
					if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
						bType1 = bType1 + "-" + bt.getBtnamethird();
					}
					bType1 = bType1 + "-" + bt.getBtstate();
					btShow[(int) bt.getBtid()] = bType1;
					bType1 = "";
				}
				session.setAttribute("adminRealBtypes", btShow);
				session.setAttribute("adminShowBtypes", btypes1);
				// ����Ա��ʼ���鼮����
				BookType bookType1 = new BookType();
				List<BookType> bookTypes = bookTypeBiz.selectAll(bookType1);
				session.setAttribute("adminBtypes", bookTypes);
				String[] type = new String[1000];
				String btname;
				for (BookType bt : bookTypes) {
					if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
						btname = bt.getBtnamethird();
					} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
						btname = bt.getBtnamesecond();
					} else {
						btname = bt.getBtname();
					}
					type[(int) bt.getBtid()] = btname;
				}
				session.setAttribute("adminBtypesEdit", type);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("����ʧ�ܣ�����");
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
				throw new RuntimeException(e);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,�����Ե�һ����ٲ���������");
			String json = gson.toJson(result);

			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		} catch (SQLException e) {
			result = Result.error("ҵ��æ,�����Ե�һ����ٲ���������");
			String json = gson.toJson(result);

			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		}
	}

	// ������������
	public void checkNamesecond(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = request.getParameter("namesecond");
		String reg = "^[\u4e00-\u9fa5]{2,20}$";
		try {
			if (name != null && !name.isEmpty()) {
				if (!name.matches(reg)) {
					result = Result.failure("���Ͷ����벻�Ϸ�������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				result = Result.success("����Ϸ�������");
				session.setAttribute("namesecond", name);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("���Ͷ�����Ϊ�գ�����");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
			return;
		} catch (IOException e) {
			result = Result.error("ҵ��æ,�����Ե�һ����ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		}
	}

	// ������������
	public void checkNamethird(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = request.getParameter("namethird");
		String reg = "^[\u4e00-\u9fa5]{2,20}$";
		try {
			if (name != null && !name.isEmpty()) {
				if (!name.matches(reg)) {
					result = Result.failure("���������벻�Ϸ�������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				result = Result.success("����Ϸ�������");
				session.setAttribute("namethird", name);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,�����Ե�һ����ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		}
	}

}
