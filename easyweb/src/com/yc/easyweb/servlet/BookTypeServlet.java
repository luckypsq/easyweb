package com.yc.easyweb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yc.easyweb.bean.BookType;
import com.yc.easyweb.bean.BookTypeChild;
import com.yc.easyweb.biz.BookTypeBiz;

public class BookTypeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private  BookType bookType = new BookType();
	private  BookTypeBiz bookTypeBiz = new BookTypeBiz();
	//��ѯ
	public void  query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<BookType> bList = bookTypeBiz.selectAll(bookType);
		List<BookTypeChild> bChilds =new ArrayList<BookTypeChild>();
		BookTypeChild bChild ;
		List<BookType> bList1 = new ArrayList<BookType>();
		List<BookType> bList2 = new ArrayList<BookType>();
		List<BookType> bList3 = new ArrayList<BookType>();
		//�����ֱ𱣴�
		for (BookType bookType : bList) {
			if(bookType.getBtname().equals("�̲���")){
				bList1.add(bookType);
			}else if((bookType.getBtname().equals("��������"))){
				bList2.add(bookType);
			}else {
				bList3.add(bookType);
			}
		}
		int id = 11;
		int pid = 111;
		for (BookType bt : bList1) {
			bChild = new BookTypeChild();
			if(bt.getBtstate() != 0){
				bChild.setBtid(bt.getBtid());
				bChild.setBtname(bt.getBtname());
				bChild.setBtnamesecond(bt.getBtnamesecond());
				bChild.setBtnamethird(bt.getBtnamethird());
				if(bt.getBtnamesecond() != null){
					bChild.setName(bt.getBtnamesecond());
					bChild.setId(pid++);
					bChild.setpId(id);
				}else{
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
			if(bt.getBtstate() != 0){
				bChild.setBtid(bt.getBtid());
				bChild.setBtname(bt.getBtname());
				bChild.setBtnamesecond(bt.getBtnamesecond());
				bChild.setBtnamethird(bt.getBtnamethird());
				if(bt.getBtnamesecond() != null){
					bChild.setName(bt.getBtnamesecond());
					bChild.setId(pid++);
					bChild.setpId(id);
				}else{
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
			if(bt.getBtstate() != 0){
				bChild.setBtid(bt.getBtid());
				bChild.setBtname(bt.getBtname());
				bChild.setBtnamesecond(bt.getBtnamesecond());
				bChild.setBtnamethird(bt.getBtnamethird());
				if(bt.getBtnamesecond() != null){
					bChild.setName(bt.getBtnamesecond());
					bChild.setId(pid++);
					bChild.setpId(id);
				}else{
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
		btc.setName("�鼮�����б�");
		btc.setOpen("true");
		bChilds.add(btc);
		
		Gson gson = new Gson();
		String json = gson.toJson(bChilds);
		System.out.println(json);
		String string ="[{ id:1, pId:0, name:'�鼮�����б�', open:true},"
				+ "{ id:11, pId:1, name:'�̲���'},"
				+ "{ id:111, pId:11, name:'�ɹ���־'},"
				+ "{ id:112, pId:11, name:'����'}]";
		// ����json��ʽ����
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(string);
	}
	
	//���
	public void  add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	}
	//ɾ��
	public void  delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	}
	//����
	public void  update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	}
}
