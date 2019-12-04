package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;
import com.yc.easyweb.bean.Control;
import com.yc.easyweb.bean.Usercontrol;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.ControlBiz;
import com.yc.easyweb.biz.UsercontrolBiz;

public class ControlServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	ControlBiz controlBiz = new ControlBiz();
	UsercontrolBiz userc = new UsercontrolBiz();

	// ��ѯ
	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	// ���
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Control control = new Control();
		PrintWriter out = response.getWriter();
		if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
			control.setConame(request.getParameter("name"));
		}
		if (request.getParameter("namesecond") != null && !request.getParameter("namesecond").isEmpty()) {
			control.setConamesecond(request.getParameter("namesecond"));
		}
		int i;
		try {
			i = controlBiz.insert(control);
			if (i > 0) {
				out.print(1);
			} else {
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

	// ���¹���ԱȨ��
	public void addUserControl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Control control = new Control();
		PrintWriter out = response.getWriter();
		String[] uid = null;
		String[] utype = null;
		if (request.getParameter("uid") != null && !request.getParameter("uid").isEmpty()) {
			uid = request.getParameter("uid").split("/");
		}
		if (request.getParameter("type") != null && !request.getParameter("type").isEmpty()) {
			utype = request.getParameter("type").split("/");
		}
		// ������ӵ�Ȩ�޷��뼯����
		List<Integer> listType = new ArrayList<Integer>();
		if (utype.length != 0 && utype != null) {
			for (String string : utype) {
				listType.add(Integer.parseInt(string));
			}
		}
		// ��ѯ����Ա��ӵ�е�Ȩ��
		Usercontrol userm = null;
		int num = 0;
		if (uid.length != 0 && uid != null) {
			try {
				for (String suid : uid) {
					userm = new Usercontrol();
					userm.setUid(Long.parseLong(suid));
					for (Integer i : listType) {
						userm.setConid(i);
						Usercontrol userS = userc.selectSingle(userm);
						if (userS.getConid() == 0) {
							int j = userc.insert(userm);
							if (j <= 0) {
								out.print(0);
								return;
							}
						}

					}
				}
				out.print(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BizException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// �����봫��������Ƚϣ���ͬ����ӣ�����ͬ�����

	}

	// ɾ��
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	// ����
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
