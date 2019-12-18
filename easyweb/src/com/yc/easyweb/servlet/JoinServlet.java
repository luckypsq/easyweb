package com.yc.easyweb.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.easyweb.bean.*;
import com.yc.easyweb.biz.*;

public class JoinServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBiz();
	private NoticeBiz noticeBiz = new NoticeBiz();
	private BookBiz bookBiz = new BookBiz();
	private BookTypeBiz btBiz = new BookTypeBiz();
	private PayTypeBiz payTypeBiz = new PayTypeBiz();
	private EorderitemBiz eorderitemBiz = new EorderitemBiz();
	private EorderBiz eorderBiz = new EorderBiz();
	private Gson gson = new Gson();
	private Result result;

	public void join(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		/*
		 * // ��ȡ��֤�� String vcode01 = (String) session.getAttribute("vcode");
		 * String vcode02 = request.getParameter("vcode"); if(vcode01 != null &&
		 * !vcode01.isEmpty() && !vcode02.isEmpty() && vcode02 != null){ if
		 * (!vcode01.equalsIgnoreCase(vcode02)) { out.print(-2);//��֤����� return;
		 * } }
		 */

		// ���� �û��� �� ����
		String username = request.getParameter("uname");
		String password = request.getParameter("upassword");
		String loginkeeping = request.getParameter("loginkeeping");// ��֤��
		session.setAttribute("loginUname", username);
		User user = new User();
		try {
			// ����û�������
			if (username != null && !username.isEmpty()) {
				user.setUname(username);
			} else {
				result = Result.failure("�û���Ϊ�գ�����", username);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			// �����������
			if (password != null && !password.isEmpty()) {
				user.setUpassword(password);
			} else {
				result = Result.failure("����Ϊ�գ�����");
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			User userShow = userBiz.selectSingle(user);// �����û���Ϣ
			if (userShow.getUid() == 0) {
				// �û���������
				result = Result.failure("�û��������ڣ�����", username);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			if (userShow.getUstate() != 1) {
				result = Result.failure("���ѱ�������˺ű�ɾ��������", username);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			session.setAttribute("loginedUser", userShow);

			String adminType = null;
			if (userShow.getUtype() == 1) {
				adminType = "��������Ա";
			} else {
				adminType = "��ͨ����Ա";
			}
			String path = this.getServletContext().getContextPath();
			// ����Ŀ���ַ���Ự��
			session.setAttribute("path", path);
			request.getSession().setAttribute("adminType", adminType);
			JoinServlet joinServlet = new JoinServlet();
			joinServlet.init(request, response);
			joinServlet.adminInit(request, response);
			// �����¼
			/**
			 * Cookie cookie = new Cookie("loginedUsername",
			 * URLEncoder.encode(username, "GBK")); // Ĭ��Ϊ��ʱCookie,MaxAge<0 //
			 * ����һ��cookie���� Cookie cookie01 = new Cookie("loginedPassword",
			 * password); // ���Cookie�����ĵ��������� cookie.setMaxAge(60);// ������Чʱ��
			 * 1����f cookie01.setMaxAge(60); response.addCookie(cookie);//
			 * ��cookie��ӵ���Ӧ������ response.addCookie(cookie01);
			 * //����������л�ȡ��������ͻط�������cookie���� Cookie[]
			 * cookies=request.getCookies(); //Cookie loginedUserCookie=null;
			 * Cookie loginedPasswordCookie=null; if(cookies!=null){ for(Cookie
			 * cookie:cookies){
			 * if(cookie.getName().equalsIgnoreCase("loginedUsername")) {
			 * //�����ȡ������������
			 * request.setAttribute("username",URLDecoder.decode(cookie.getValue
			 * (),"GBK")); } if("loginedPassword".equals(cookie.getName())){
			 * loginedPasswordCookie=cookie; } } }
			 */

			if (userShow.getUtype() != 1 && userShow.getUtype() != 5) {
				result = Result.success("�û���¼�ɹ�������", username);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			} else {
				UsercontrolBiz ucBiz = new UsercontrolBiz();
				Usercontrol usercontrolOld = new Usercontrol();
				usercontrolOld.setUid(userShow.getUid());
				List<Usercontrol> controlList = ucBiz.selectAll(usercontrolOld);

				List<Long> conList = new ArrayList<Long>();
				if (controlList != null) {
					for (Usercontrol ucon : controlList) {
						conList.add(ucon.getConid());
					}
				}
				// ����ԱȨ��
				session.setAttribute("adminControl", conList);
				result = new Result("����Ա��¼�ɹ�������", 2);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
		} catch (BizException e) {
			result = Result.error(e.getMessage());
			String json = gson.toJson(result);
			// ����json��ʽ����
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
		} catch (IOException e) {
			result = Result.error("ҵ��æ,�����Ե�һ����ٲ���������");
			String json = gson.toJson(result);
			// ����json��ʽ����
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		}
	}
	// TODO Auto-generated catch block
	private void init(HttpServletRequest request, HttpServletResponse response) throws IOException, BizException {
		// ��ȡϵͳ��ǰʱ��
		HttpSession session = request.getSession();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
		Date date = new Date();
		String[] dateStr = df.format(date).split("/");
		// ����¼ʱ�����Ự��
		session.setAttribute("date", dateStr);
		// ������״̬�����ͷ���Ự��
		String[] userType = { "", "", "��ͨ�û�", "��ͨ��Ա", "��ʯ��Ա" };
		String[] uType = { "", "", "2-��ͨ�û�", "3-��ͨ��Ա", "4-��ʯ��Ա" };
		session.setAttribute("userType", userType);
		session.setAttribute("uType", uType);
		String[] userSex = { "����", "��", "Ů" };
		session.setAttribute("userSex", userSex);
		String[] adminState = { "", "������", "�Ѷ���", "��ɾ��" };
		session.setAttribute("adminStateC", adminState);// �洢���д��ڵĹ���Ա��Ϣ
		String[] bookState = { "δ�ϼ�", "���ϼ�", "���¼�", "����", "��˲�ͨ��", "δ���" };
		session.setAttribute("bookState", bookState);
		// ����Ա����״̬
		String[] eorderState = { "", "������", "������", "�ѷ���", "������", "���˿�", "�ѽ���", "�˻�ʧ��" };
		session.setAttribute("eoderState", eorderState);
		// �û�����״̬
		String[] userOrder = { "", "�ȴ�֧��", "�ȴ�����", "�ȴ�����", "�ȴ�����", "�˿�ɹ�", "�ѽ���", "�˻�ʧ��" };
		session.setAttribute("userEorderState", userOrder);
		String[] eorderMessage = { "", "�ȴ�֧��", "�ȴ�����", "�ȴ�����", "�ȴ�����", "�˿�ɹ�", "���������", "����������" };
		session.setAttribute("eoderMessage", eorderMessage);
		// ��ʼ����ѧ��ѧԺ��רҵ
		Book book = new Book();
		List<Book> bookList_add = bookBiz.selectAll(book);
		HashSet<String> bookUniver = new HashSet<String>();
		HashSet<String> bookUcollage = new HashSet<String>();
		HashSet<String> bookUmagor = new HashSet<String>();
		for (Book bookSet : bookList_add) {
			if (null != bookSet.getBuniversity() && !bookSet.getBuniversity().isEmpty()) {
				bookUniver.add(bookSet.getBuniversity());
			}
			if (null != bookSet.getBucollege() && !bookSet.getBucollege().isEmpty()) {
				bookUcollage.add(bookSet.getBucollege());
			}
			if (null != bookSet.getBumajor() && !bookSet.getBumajor().isEmpty()) {
				bookUmagor.add(bookSet.getBumajor());
			}
		}
		session.setAttribute("userUni", bookUniver);
		session.setAttribute("userUcol", bookUcollage);
		session.setAttribute("userUmar", bookUmagor);

		// ��ʼ���鼮����
		// �鼮����
		BookType bookType = new BookType();
		bookType.setBtstate(1);
		List<BookType> bookTypes = btBiz.selectAll(bookType);
		session.setAttribute("btypes", bookTypes);
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
		session.setAttribute("btTypeEdit", type);
		// ����չʾ��ʼ������
		Notice notice = new Notice();
		notice.setNstate(1);
		List<Notice> nList;
		nList = noticeBiz.selectAll(notice);
		List<Notice> nShow = new ArrayList<Notice>();
		if (nList.size() != 0) {
			for (int i = 0; i < nList.size(); i++) {
				if (i == 6) {
					break;
				}
				nShow.add(nList.get(i));
			}
		}
		// �洢���µ���������չʾ����
		session.setAttribute("noticeShow", nShow);
		Page<Notice> pageNotice = noticeBiz.noticePage(1, 3, notice);
		session.setAttribute("noticePage", pageNotice);
		// ��ʼ��֧������

		PayType payType = new PayType();
		payType.setEopaystate(1);
		List<PayType> payTypeList = payTypeBiz.selectAll(payType);
		session.setAttribute("payType", payTypeList);
		// listҳ���鼮
		Book bookList = new Book();
		bookList.setBstate(1);
		Page<Book> page2 = bookBiz.bookPage(1, 21, bookList);
		session.setAttribute("listBookPage", page2);
		// �û�index��ʼ����
		// �鼮չʾ
		BookChild bookChild = new BookChild();
		bookChild.setBstate(1);
		// �̲���
		bookChild.setBtname("�̲���");
		Page<Book> pPage = bookBiz.bookChildPage(1, 12, bookChild);
		session.setAttribute("teachBook", pPage.getData());
		session.setAttribute("teachPage", pPage);

		// ������
		bookChild.setBtname("��������");
		Page<Book> pPaget = bookBiz.bookChildPage(1, 7, bookChild);
		session.setAttribute("toolBook", pPaget.getData());
		session.setAttribute("toolPage", pPaget);
		// ������
		bookChild.setBtid(Long.parseLong("3"));
		Page<Book> pPages = bookBiz.bookPage(1, 7, bookChild);
		session.setAttribute("shareBook", pPages.getData());
		session.setAttribute("sharePage", pPages);

		// �û��ѷ����鼮չʾ
		User userOld = (User) session.getAttribute("loginedUser");
		Book userBook = new Book();
		Page<Book> page;
		if (userOld.getUid() != 0) {
			userBook.setUid(userOld.getUid());
		} else {
			userBook.setUid(0);
		}
		page = bookBiz.bookPage(1, 5, userBook);
		session.setAttribute("userBookPage", page);

		// ��ѯ���ж���������Ϣ
		Eorder eorder = new Eorder();
		eorder.setUid(userOld.getUid());
		Page<Eorder> Page = eorderBiz.eorderPage(1, 3, eorder);
		session.setAttribute("userOrderPage", Page);
		// ���ﳵ��Ϣ��ʾ
		Bought bought = new Bought();
		List<Bought> listEo = eorderitemBiz.selectAllCart(bought);
		session.setAttribute("userCart", listEo);
		bought.setCartstate(1);
		bought.setUid(userOld.getUid());
		Page<Bought> pageCart = eorderitemBiz.ePage(1, 6, bought);
		session.setAttribute("cartPage", pageCart);
	}
	// TODO Auto-generated catch block
	// ����Աҳ�����Ϣչʾ��ʼ��
	public void adminInit(HttpServletRequest request, HttpServletResponse response) throws IOException, BizException {
		HttpSession session = request.getSession();
		// ��ȡ�û���Ϣ
		User user = new User();
		// ��ȡuser��������Ϣ�洢�ڻỰ��
		List<User> userListAll = userBiz.selectAll(user);
		session.setAttribute("userAll", userListAll);// �洢��user���������Ϣ
		List<User> customerExit = userBiz.selectAll(user);// �洢�����û���Ϣ
		// �洢����Ա������Ϣ
		user.setUtype(1);
		List<User> adminListAll = userBiz.selectAll(user);
		user.setUtype(5);
		List<User> adminList = userBiz.selectAll(user);
		if (adminList.size() != 0) {
			for (User u : adminList) {
				adminListAll.add(u);
			}
		}
		session.setAttribute("adminExit", adminListAll);// �洢���й���Ա��Ϣ
		// �洢�����û���Ϣ
		for (int i = 0; i < customerExit.size(); i++) {
			for (int j = 0; j < adminListAll.size(); j++) {
				if (customerExit.get(i).equals(adminListAll.get(j))
						&& customerExit.get(i).hashCode() == adminListAll.hashCode()) {
					customerExit.remove(i);
					// ��ʱ��ע�⣬��Ϊlist�ᶯ̬�仯���������ռλ�����Ե�ǰ����Ӧ�ú���һλ
					i--;
				}
			}
		}
		session.setAttribute("customerExit", customerExit);// �洢�����û���Ϣ
		// ��ȡ�鼮��Ϣ
		Book book = new Book();
		List<Book> bookAll = bookBiz.selectAll(book);
		session.setAttribute("bookAll", bookAll);// �洢�����鼮
		// ��ȡȫ��������
		Eorder eorder = new Eorder();
		OrderDetial eorder1 = new OrderDetial();
		String[] date = (String[]) session.getAttribute("date");
		eorder.setEotime(date[0]);
		eorder1.setEotime(date[0]);
		List<OrderDetial> eorderList;
		eorderList = eorderBiz.selectAllDetail(eorder1);
		List<Eorder> eorders = eorderBiz.selectAll(eorder);
		session.setAttribute("eorderSuccess", eorderList);// �洢���еĶ�����Ϣ
		session.setAttribute("eorderAll", eorders);// �洢���еĶ�����Ϣ
		double num = 0.0;
		if (eorderList.size() != 0) {
			for (OrderDetial oShow : eorderList) {
				num = num + oShow.getTotal();// �洢�����ܶ�
			}
		}
		session.setAttribute("eorderTotal", num);// �洢ÿһ�������ܼ�
		// eorderList������ȫ������
		// ״̬1.������2.������3.�ѷ���4.�˻�������5.�˿�ɹ�6.�ѽ���7.�˻�ʧ��
		long[] count = { 0, 0, 0, 0, 0 };
		for (Eorder eo : eorders) {
			if (eo.getEostate() == 1) {
				// �����㶩��eotype==1
				count[2] = count[2] + 1;
			} else if (eo.getEostate() == 2) {
				// ����������eostate == 2
				count[1] = count[1] + 1;
			} else if (eo.getEostate() == 4) {
				// δ������eostate == 4
				count[0] = count[0] + 1;
			} else if (eo.getEostate() == 6 || eo.getEostate() == 3) {
				// �ѳɽ�������eostate == 6
				count[3] = count[3] + 1;
			} else if (eo.getEostate() == 5) {
				// ����ʧ��eostate == 5
				count[4] = count[4] + 1;
			}
		}
		session.setAttribute("eorderCount", count);// �洢ÿһ״̬��ֵ
		// bookList�洢�����鼮��Ϣ
		// bstate;//״̬(1���ã�2.ɾ��3.����)
		long[] bookCount = { 0, 0, 0 };
		for (Book book1 : bookAll) {
			if (book1.getBstate() == 3) {
				bookCount[0] = bookCount[0] + 1;
			} else if (book1.getBstate() == 1) {
				bookCount[1] = bookCount[1] + 1;
			} else if (book1.getBstate() == 2) {
				bookCount[2] = bookCount[2] + 1;
			}
		}
		session.setAttribute("bookCount", bookCount);// �洢ÿһ״̬��ֵ

		// ����չʾ
		Notice notice = new Notice();
		List<Notice> nList = noticeBiz.selectAll(notice);
		List<Notice> nShow = new ArrayList<Notice>();
		session.setAttribute("noticeAll", nList);// �洢���й���
		if (nList.size() != 0) {
			for (int i = 0; i < nList.size(); i++) {
				if (i == 6) {
					break;
				}
				nShow.add(nList.get(i));
			}
		}
		// �洢���µ���������չʾ����
		session.setAttribute("noticeShow", nShow);
		// ����Ա�������޸ĵ�����
		BookType bType = new BookType();
		bType.setBtname("�̲���");
		List<BookType> btypes = btBiz.selectAll(bType);
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
		BookType bookType = new BookType();
		List<BookType> bookTypes = btBiz.selectAll(bookType);
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
	}
}
