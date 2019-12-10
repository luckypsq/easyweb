package com.yc.easyweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.NoticeBiz;

public class NoticeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private NoticeBiz noticeBiz = new NoticeBiz();
	
	//��ѯ
	public void  query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ����չʾ
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
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
		
		String nid = request.getParameter("uid");
		
		if(nid != null && !nid.isEmpty()){
			notice.setNid(Long.parseLong(nid));
		}
		try {
			Notice notice2 = noticeBiz.selectSingle(notice);
			if(notice2.getNid() != 0){
				session.setAttribute("noticeDetail", notice2);
			}else{
				out.print(-1);
			}
		} catch (BizException e) {
			e.printStackTrace();
		}
	}
	//���
	public void  add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	//ɾ��
	public void  delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	//����
	public void  update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}
