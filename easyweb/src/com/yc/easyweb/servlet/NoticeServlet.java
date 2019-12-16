package com.yc.easyweb.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.bean.Page;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.NoticeBiz;

public class NoticeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private NoticeBiz noticeBiz = new NoticeBiz();
	private  Gson gson = new Gson();
	private Result result ;
	//��ѯ
	public void  query(HttpServletRequest request, HttpServletResponse response){
		// ����չʾ
		HttpSession session = request.getSession();
		Notice notice = new Notice();
		notice.setNstate(1);
		try {
			//�����ҳ��ѯ
			String page = request.getParameter("page");
			String nid = request.getParameter("nid");
			//��ѯ��������
			if(nid != null && !nid.isEmpty()){
				notice.setNid(Long.parseLong(nid));
				Notice notice2 = noticeBiz.selectSingle(notice);
				if(notice2.getNid() != 0){
					session.setAttribute("noticeDetail", notice2);
					result = Result.success("��ѯ�ɹ�������");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return ;
				}else{
					result = Result.failure("��ѯʧ�ܣ�����");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return ;
				}
			}else{
				int iPage;
				if(page != null && !page.isEmpty()){
					iPage = Integer.parseInt(page);
				}else{
					iPage = 1 ;
				}
				Page<Notice> sPage = noticeBiz.noticePage(iPage, 3, notice);
				if(sPage.getData().size() == 0){
					result = Result.failure("�������ݣ�����");
					String json = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json);
					return ;
				}else{
					session.setAttribute("noticePage", sPage);
					result = Result.success("��ѯ�ɹ�������");
					String json1 = gson.toJson(result);
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().append(json1);
					return ;
				}
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
	// TODO Auto-generated catch block
	//���
	public void  add(HttpServletRequest request, HttpServletResponse response) {
	}
	//ɾ��
	public void  delete(HttpServletRequest request, HttpServletResponse response){
	}
	//����
	public void  update(HttpServletRequest request, HttpServletResponse response){
	}
	
}
