package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.BookType;
import com.yc.easyweb.bean.Page;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.BookTypeBiz;

public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookBiz bookBiz = new BookBiz();
	private BookTypeBiz btBiz = new BookTypeBiz();
	private Gson gson = new Gson();
	private Result result;
	
	// ����鼮��֤����
	public void checkBnum(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String bnum = request.getParameter("bnum");
		String reg = "^[0-9]{1,8}$";

		try {
			if (bnum != null && !bnum.isEmpty()) {
				if (!bnum.matches(reg)) {
					result = Result.failure("�������벻�Ϸ�������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				result = Result.success("������ȷ������");
				session.setAttribute("insertBnum", bnum);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
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

	// ����鼮��֤�۸�
	public void checkBprice(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String bprice = request.getParameter("bprice");
		String reg = "^[0-9]+(.[0-9]{0,2})?$";

		try {
			if (bprice != null && !bprice.isEmpty()) {
				if (!bprice.matches(reg)) {
					result = Result.failure("�۸����벻�Ϸ�������");
					String json = gson.toJson(result);
					// ����json��ʽ����
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				result = Result.success("����Ϸ�������");
				session.setAttribute("insertBprice", bprice);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("�۸�Ϊ�գ�����");
			String json = gson.toJson(result);
			// ����json��ʽ����
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

	// ����鼮��֤������
	public void checkBauthor(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String bauthor = request.getParameter("bauthor");
		String reg = "^[\u4e00-\u9fa5a-zA-Z]{1,100}$";

		try {
			if (bauthor != null && !bauthor.isEmpty()) {
				if (!bauthor.matches(reg)) {
					result = Result.failure("���������벻�Ϸ�������");
					String json = gson.toJson(result);
					// ����json��ʽ����
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				result = Result.success("������ȷ������");
				session.setAttribute("insertBauthor", bauthor);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("������Ϊ�գ�����");
			String json = gson.toJson(result);
			// ����json��ʽ����
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

	// ����鼮��֤����
	public void checkBname(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String bname = request.getParameter("bname");
		String reg = "^[\u4e00-\u9fa5a-zA-Z]{1,150}$";
		User oldUser = (User) session.getAttribute("loginedUser");
		try {
			if (bname != null && !bname.isEmpty()) {
				if (!bname.matches(reg)) {
					result = Result.failure("�������Ϸ�������");
					String json = gson.toJson(result);
					// ����json��ʽ����
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				// �ж��Ƿ��ϴ���ͬ���鼮
				Book book = new Book();
				book.setBname(bname);
				book.setBstate(1);
				book.setUid(oldUser.getUid());
				Book book2 = bookBiz.selectSingle(book);
				if (book2.getBid() != 0) {
					result = Result.failure("�����ϴ����飡����");
					String json = gson.toJson(result);
					// ����json��ʽ����
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				result = Result.success("��������ʹ�ã�����");
				session.setAttribute("insertBname", bname);
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.failure("����Ϊ�գ�����");
			String json = gson.toJson(result);
			// ����json��ʽ����
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

	// ����鼮��֤ϵ����
	public void checkBtemp(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String btemp = request.getParameter("btemp");
		String reg = "^[\u4e00-\u9fa5a-zA-Z]{2,50}$";

		try {
			if (btemp != null && !btemp.isEmpty()) {
				if (!btemp.matches(reg)) {
					result = Result.failure("ϵ�������Ϸ�������");
					String json = gson.toJson(result);
					// ����json��ʽ����
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				}
				session.setAttribute("insertBtemp", btemp);
				result = Result.success("��ϵ�������ã�����");
				String json = gson.toJson(result);
				// ����json��ʽ����
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
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

	// ����鼮�ϴ�ͼƬ
	public void uploadImage(HttpServletRequest request, HttpServletResponse response) {
		// �����ļ��ϴ�����
		SmartUpload su = new SmartUpload();
		try {
			// ��ʼ��������ҳ�������Ķ���
			su.initialize(getServletConfig(), request, response);
			// �����ϴ���������Ϣ
			// �޶��ļ�����׺
			su.setAllowedFilesList("jpg,png,gif,bmp");
			// �޶���С
			su.setMaxFileSize(1024 * 1024 * 10);
			// ִ���ϴ�
			su.upload();
			// �ж��Ƿ����ϴ��ļ�
			String webPath = null;
			String filename = null;// �ļ���
			String diskPath = null;// ����·��
			if (su.getFiles().getSize() > 0) {
				// Ϊ��ֹ�ϴ����ļ����ظ������ļ����޸�ΪΨһ
				// ��ȡϵͳ��ǰʱ��
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				Date date = new Date();
				Files files = su.getFiles();
				filename = df.format(date) + files.getFile(0).getFileName();
				String path = this.getServletContext().getContextPath();
				// ʹ��application��Ӧ�������Ķ��� web·�� ת���� ����·��
				// getServletContext���� === application
				diskPath = getServletContext().getRealPath("/back/upload");
				files.getFile(0).saveAs(diskPath + "/" + filename);
				webPath = path + "/back/upload/" + filename;
			}
			if (webPath != null && !webPath.isEmpty()) {
				result = Result.success("�ϴ�ͼƬ�ɹ���", webPath);
			} else {
				result = Result.failure("�ϴ�ͼƬʧ�ܣ�");
			}
			String json = gson.toJson(result);
			// ����json��ʽ����
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);
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
		} catch (SmartUploadException e) {
			result = Result.error("ҵ��æ,���Եȼ������ٲ���������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
		} catch (ServletException e) {
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

	// ��ʾlistҳ����鼮
	public void listBook(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String uni = request.getParameter("university");
		String ucol = request.getParameter("ucollege");
		String umaj = request.getParameter("umajor");
		String ucla = request.getParameter("uclass");
		String page = request.getParameter("page");
		int ipage;
		Book book = new Book();
		book.setBstate(1);
		if (uni != null && !uni.isEmpty()) {
			book.setBuniversity(uni);
		}
		if (ucol != null && !uni.isEmpty()) {
			book.setBucollege(ucol);
		}
		if (umaj != null && !umaj.isEmpty()) {
			book.setBumajor(umaj);
		}
		if (ucla != null && !ucla.isEmpty()) {
			book.setBclass(ucla);
		}
		if (page != null && !page.isEmpty()) {
			ipage = Integer.parseInt(page);
		} else {
			ipage = 1;
		}
		try {
			Page<Book> page2 = bookBiz.bookPage(ipage, 21, book);
			if (page2.getData().size() == 0) {
				result = Result.failure("�������ݣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			result = Result.success("��ѯ�ɹ�������");
			session.setAttribute("listBookPage", page2);
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);

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

	// ��ʾ�鱾����
	public void bookDetail(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Book book1 = new Book();
		if (request.getParameter("bid") != null && !request.getParameter("bid").isEmpty()) {
			book1.setBid(Long.parseLong(request.getParameter("bid")));
		}
		try {
			Book book = bookBiz.selectSingle(book1);
			if (book.getBid() == 0) {
				result = Result.failure("�����ѱ�ɾ�������¼�");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			Book book2 = new Book();
			book2.setBtid(book.getBtid());
			List<Book> list = bookBiz.selectAll(book2);
			session.setAttribute("bookDetail", book);
			session.setAttribute("similarBook", list);
			result = Result.success("��ѯ�ɹ�������");
			String json = gson.toJson(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(json);

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

	// �û������鼮
	public void userAddBook(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String buniversity = request.getParameter("buniversity");
		String bucollege = request.getParameter("bucollege");
		String bumajor = request.getParameter("bumajor");
		String bclass = request.getParameter("bclass");
		String bcontent = request.getParameter("bcontent");
		String btid = request.getParameter("btid");
		String bimg = request.getParameter("img_path");
		String bid = request.getParameter("bid");

		// ��Ҫ��֤����Ϣ
		String btemp = (String) session.getAttribute("insertBtemp");
		String bname = (String) session.getAttribute("insertBname");
		String bprice = (String) session.getAttribute("insertBprice");
		String bnum = (String) session.getAttribute("insertBnum");
		String bauthor = (String) session.getAttribute("insertBauthor");

		User userOld = (User) session.getAttribute("loginedUser");
		Book book = new Book();

		if (!buniversity.equals("ͼ��������ѧ")) {
			book.setBuniversity(buniversity);
		}
		if (!bucollege.equals("ͼ������ѧԺ")) {
			book.setBucollege(bucollege);
		}
		if (!bumajor.equals("ͼ������רҵ")) {
			book.setBumajor(bumajor);
		}
		if (!bclass.equals("ͼ�������꼶")) {
			book.setBclass(bclass);
		}
		if (bimg != null && !bimg.isEmpty()) {
			book.setBimg(bimg);
		}
		if (bcontent != null && !bcontent.isEmpty()) {
			bcontent = bcontent.trim();
			if (bcontent.length() > 20000) {
				bcontent = bcontent.substring(0, 19998);
			}
			book.setBcontent(bcontent);
		}
		if (btemp != null && !btemp.isEmpty()) {
			book.setBtemp(btemp);
		}
		if (bnum != null && !bnum.isEmpty()) {
			book.setBnum(Long.parseLong(bnum));
		} else {
			book.setBnum(Long.parseLong("1"));
		}
		String check = "1";
		// ����
		if (bname != null && !bname.isEmpty()) {
			book.setBname(bname);
			check = check + "/1";
		} else {
			if (request.getParameter("bname") != null && !request.getParameter("bname").isEmpty()) {
				book.setBname(request.getParameter("bname"));
				check = check + "/1";
			} else {
				check = check + "/-1";
			}
		}
		// ��۸�
		if (bprice != null && !bprice.isEmpty()) {
			book.setBprice(Double.parseDouble(bprice));
			check = check + "/1";
		} else {
			if (request.getParameter("bprice") != null && !request.getParameter("bprice").isEmpty()) {
				book.setBprice(Double.parseDouble(request.getParameter("bprice")));
				check = check + "/1";
			} else {
				check = check + "/-1";
			}
		}
		// ������
		if (bauthor != null && !bauthor.isEmpty()) {
			book.setBauthor(bauthor);
			check = check + "/1";
		} else {
			if (request.getParameter("bauthor") != null && !request.getParameter("bauthor").isEmpty()) {
				book.setBauthor(request.getParameter("bauthor"));
				check = check + "/1";
			} else {
				check = check + "/-1";
			}
		}

		try {
			if (!check.equals("1/1/1/1")) {
				result = Result.lack("���ʧ�ܣ�����", check);
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			if (!btid.equals("ͼ����������")) {
				book.setBtid(Long.parseLong(btid));
			} else {
				result = Result.failure("��ѡ���鼮���ͣ�����");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}
			book.setUid(userOld.getUid());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			book.setBdate(df.format(date));

			if (bid != null && !bid.isEmpty()) {
				Book oldBook = new Book();
				book.setBid(Long.parseLong(bid));
				int code = bookBiz.update(book, oldBook);
				if (code > 0) {
					result = Result.success("�޸ĳɹ�������");
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
			int code = bookBiz.insert(book);
			if (code > 0) {
				result = Result.success("�����ɹ�������");
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

	// ɾ��
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		Book book2;
		HttpSession session = request.getSession();
		User userOld = (User) session.getAttribute("loginedUser");
		List<Book> list = new ArrayList<Book>();
		String bid = request.getParameter("bid");
		int i;
		try {
			if (bid != null && !bid.isEmpty()) {
				if (bid.equals("all")) {
					result = Result.failure("���ܽ��д˲���������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return;
				} else {
					String[] bids = bid.split("/");
					for (String string : bids) {
						book2 = new Book();
						if (!string.equals("-1") && !string.isEmpty() && string != null) {
							book2.setBid(Long.parseLong(string));
							list.add(book2);
						}
					}
				}
			} else {
				result = Result.failure("δѡ���鼮������");
				String json = gson.toJson(result);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return;
			}

			if (list.size() != 0) {
				i = bookBiz.deleteMore(list);
				if (i > 0) {
					result = Result.success("ɾ���ɹ�������");
					Book book = new Book();
					book.setUid(userOld.getUid());
					Page<Book>  page = bookBiz.bookPage(1, 5, book);
					session.setAttribute("userBookPage", page);
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
			result = Result.failure("δѡ���鼮������");
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
	// TODO Auto-generated catch block
	// ��ѯ
	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String bauthor = null;
		String btime = null;
		String btid = null;
		Book book = new Book();

		// ��ȡ��ѯ����
		if (request.getParameter("bauthor") != null && !request.getParameter("bauthor").isEmpty()) {
			bauthor = request.getParameter("bauthor");
			book.setBauthor(bauthor);
		}
		if (request.getParameter("btime") != null && !request.getParameter("btime").isEmpty()) {
			btime = request.getParameter("btime");
			book.setBdate(btime);
		}
		if (request.getParameter("btid") != null && !request.getParameter("btid").isEmpty()) {
			btid = request.getParameter("btid");
			book.setBtid(Long.parseLong(request.getParameter("btid")));
		}
		// ��ѯ���
		BookType bookType = new BookType();
		bookType.setBtstate(1);
		List<BookType> btList = btBiz.selectAll(bookType);
		Map<Long, String> btType = new HashMap<Long, String>();
		for (BookType bt : btList) {
			if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
				btType.put(bt.getBtid(), bt.getBtnamethird());
			} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
				btType.put(bt.getBtid(), bt.getBtnamesecond());
			} else {
				btType.put(bt.getBtid(), bt.getBtname());
			}
		}
		List<Book> bookList = bookBiz.selectAll(book);
		session.setAttribute("bookList", bookList);// ���������鼮��Ϣ
		session.setAttribute("bookType", btType);// ���������鼮������Ϣ

		if (bauthor != null) {
			session.setAttribute("bauthor", bauthor);
		} else {
			session.setAttribute("bauthor", "");
		}
		if (btime != null) {
			session.setAttribute("btime", btime);
		} else {
			session.setAttribute("btime", "");
		}
		if (bookList.size() <= 0) {
			out.print(0);
		}
	}

	// ���
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SmartUploadException {
		Book bookNew = new Book();
		String path = this.getServletContext().getContextPath();
		if (request.getParameter("bname") != null && !request.getParameter("bname").isEmpty()) {
			bookNew.setBname(request.getParameter("bname"));
		} else {
			String url = path + "/back/book/picture-add.jsp?msg=" + 2;
			response.sendRedirect(url);
		}
		if (request.getParameter("buniversity") != null && !request.getParameter("buniversity").equals("��ѡ��")) {
			bookNew.setBuniversity(request.getParameter("buniversity"));
		}
		if (request.getParameter("bcollege") != null && !request.getParameter("bcollege").equals("��ѡ��")) {
			bookNew.setBucollege(request.getParameter("bcollege"));
		}
		if (request.getParameter("bmajor") != null && !request.getParameter("bmajor").equals("��ѡ��")) {
			bookNew.setBumajor(request.getParameter("bmajor"));
		}
		if (request.getParameter("bclass") != null && !request.getParameter("bclass").equals("��ѡ��")) {
			bookNew.setBclass(request.getParameter("bclass"));
		}

		if (request.getParameter("btemp") != null && !request.getParameter("btemp").isEmpty()) {
			bookNew.setBtemp(request.getParameter("btemp"));
		}
		// ���
		if (request.getParameter("btype") != null && !request.getParameter("btype").equals("��ѡ��")) {
			String btid = request.getParameter("btype").toString().split("-")[0];
			bookNew.setBtid(Long.parseLong(btid));
		}
		if (request.getParameter("bauthor") != null && !request.getParameter("bauthor").isEmpty()) {
			bookNew.setBauthor(request.getParameter("bauthor"));
		}
		if (request.getParameter("bprice") != null && !request.getParameter("bprice").isEmpty()) {
			bookNew.setBprice(Double.parseDouble(request.getParameter("bprice")));
		} else {
			String url = path + "/back/book/picture-add.jsp?msg=" + 3;
			response.sendRedirect(url);
		}
		if (request.getParameter("bnum") != null && !request.getParameter("bnum").isEmpty()) {
			bookNew.setBnum(Long.parseLong(request.getParameter("bnum")));
		}
		if (request.getParameter("bdate") != null && !request.getParameter("bdate").isEmpty()) {
			bookNew.setBdate(request.getParameter("bdate"));
		}
		if (request.getParameter("bcontent") != null && !request.getParameter("bcontent").isEmpty()) {
			bookNew.setBcontent(request.getParameter("bcontent"));
		}
		if (request.getParameter("img_path") != null && !request.getParameter("img_path").isEmpty()) {
			bookNew.setBimg(request.getParameter("img_path"));
		}
		try {
			int code;
			try {
				code = bookBiz.insert(bookNew);
				String url = null;
				if (code > 0) {
					url = path + "/back/book/picture-add.jsp?msg=" + 1;
				} else {
					url = path + "/back/book/picture-add.jsp?msg=" + 0;
				}
				response.sendRedirect(url);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (BizException e) {
			e.printStackTrace();
		}
	}

	// ����״̬
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book bookNew = new Book();
		Book bookOld = new Book();
		PrintWriter out = response.getWriter();
		if (request.getParameter("bid") != null && !request.getParameter("bid").isEmpty()) {
			bookOld.setBid(Long.parseLong(request.getParameter("bid").toString()));
		}
		if (request.getParameter("bstate") != null && !request.getParameter("bstate").isEmpty()) {
			bookNew.setBstate(Integer.parseInt(request.getParameter("bstate").toString()));
		}
		try {
			Book book = bookBiz.selectSingle(bookOld);
			if (book.getBstate() == 1 || book.getBstate() == 2) {
				int code = bookBiz.update(bookNew, bookOld);
				String json = "{code:'" + code + "'}";
				out.print(json);
			} else {
				String json = "{code:'-1'}";
				out.print(json);
			}
		} catch (BizException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// �����鱾��Ϣ
	public void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = this.getServletContext().getContextPath();
		Book bookNew = new Book();
		Book bookOld = new Book();
		// ����Ҫ�޸��鼮��id
		if (request.getParameter("bid") != null && !request.getParameter("bid").isEmpty()) {
			bookOld.setBid(Long.parseLong(request.getParameter("bid").toString()));
		} else {
			String url = path + "/back/book/bookEdit.jsp?bid=" + bookOld.getBid() + "&msg=" + 2;
			response.sendRedirect(url);
		}
		// �޸ĵ�ֵ
		if (request.getParameter("bname") != null && !request.getParameter("bname").isEmpty()) {
			bookNew.setBname(request.getParameter("bname"));
		}
		if (request.getParameter("buniversity") != null && !request.getParameter("buniversity").equals("��ѡ��")) {
			bookNew.setBuniversity(request.getParameter("buniversity"));
		}
		if (request.getParameter("bcollege") != null && !request.getParameter("bcollege").equals("��ѡ��")) {
			bookNew.setBucollege(request.getParameter("bcollege"));
		}
		if (request.getParameter("bmajor") != null && !request.getParameter("bmajor").equals("��ѡ��")) {
			bookNew.setBumajor(request.getParameter("bmajor"));
		}
		if (request.getParameter("bclass") != null && !request.getParameter("bclass").equals("��ѡ��")) {
			bookNew.setBclass(request.getParameter("bclass"));
		}

		if (request.getParameter("btemp") != null && !request.getParameter("btemp").isEmpty()) {
			bookNew.setBtemp(request.getParameter("btemp"));
		}
		// ���
		if (request.getParameter("btype") != null && !request.getParameter("btype").equals("��ѡ��")) {
			String btid = request.getParameter("btype").toString().split("-")[0];
			bookNew.setBtid(Long.parseLong(btid));
		}
		if (request.getParameter("bauthor") != null && !request.getParameter("bauthor").isEmpty()) {
			bookNew.setBauthor(request.getParameter("bauthor"));
		}
		if (request.getParameter("bprice") != null && !request.getParameter("bprice").isEmpty()) {
			bookNew.setBprice(Double.parseDouble(request.getParameter("bprice")));
		}
		if (request.getParameter("bnum") != null && !request.getParameter("bnum").isEmpty()) {
			bookNew.setBnum(Long.parseLong(request.getParameter("bnum")));
		}
		if (request.getParameter("bdate") != null && !request.getParameter("bdate").isEmpty()) {
			bookNew.setBdate(request.getParameter("bdate"));
		}
		if (request.getParameter("bcontent") != null && !request.getParameter("bcontent").isEmpty()) {
			bookNew.setBcontent(request.getParameter("bcontent"));
		}
		if (request.getParameter("img_path") != null && !request.getParameter("img_path").isEmpty()) {
			bookNew.setBimg(request.getParameter("img_path"));
		}
		try {
			int code = bookBiz.update(bookNew, bookOld);
			String url = null;
			if (code > 0) {
				url = path + "/back/book/bookEdit.jsp?bid=" + bookOld.getBid() + "&msg=" + 1;
			} else {
				url = path + "/back/book/bookEdit.jsp?bid=" + bookOld.getBid() + "&msg=" + 0;
			}
			response.sendRedirect(url);

		} catch (BizException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �����鱾��Ϣҳ����ʾ(bookEdit.jsp)
	public void editShow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		// ��ȡ��ʾ��Ϣ
		BookBiz bizBook = new BookBiz();
		// ��ȡid
		Book showBook = new Book();
		Book book2 = new Book();
		Long bid = null;
		if (request.getParameter("bid") != null && !request.getParameter("bid").isEmpty()) {
			bid = Long.parseLong(request.getParameter("bid").toString());
			book2.setBid(bid);
		}
		try {
			showBook = bizBook.selectSingle(book2);
			session.setAttribute("showBookEdit", showBook);
			// չʾ������
			String showType = null;
			// �����ݿ��в�ѯ���еĴ�ѧ��רҵ����Ϣ
			Book book = new Book();
			List<Book> bookList_add = bizBook.selectAll(book);
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
			session.setAttribute("bookUniverEdit", bookUniver);
			session.setAttribute("bookUcollageEdit", bookUcollage);
			session.setAttribute("bookUmagorEdit", bookUmagor);

			BookType bookType = new BookType();
			bookType.setBtstate(1);
			BookTypeBiz btBiz = new BookTypeBiz();
			List<BookType> btList = btBiz.selectAll(bookType);
			HashSet<String> btType = new HashSet<String>();
			for (BookType bt : btList) {
				if (showBook.getBtid() == bt.getBtid()) {
					if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
						showType = bt.getBtid() + "-" + bt.getBtnamethird();
					} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
						showType = bt.getBtid() + "-" + bt.getBtnamesecond();
					} else {
						showType = bt.getBtid() + "-" + bt.getBtname();
					}
				}
				if (bt.getBtnamethird() != null && !bt.getBtnamethird().isEmpty()) {
					btType.add(bt.getBtid() + "-" + bt.getBtnamethird());
				} else if (bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()) {
					btType.add(bt.getBtid() + "-" + bt.getBtnamesecond());
				} else {
					btType.add(bt.getBtid() + "-" + bt.getBtname());
				}
			}
			session.setAttribute("showTypeEdit", showType);
			session.setAttribute("btTypeEdit", btType);
			if (btType.size() == 0) {
				out.print(-1);
			}
		} catch (BizException e) {
			e.printStackTrace();
		}
	}
}
