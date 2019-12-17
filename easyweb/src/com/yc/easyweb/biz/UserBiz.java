package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.dao.UserDao;

/**
 * ����User���������
 * 
 * @author psq
 *
 */
public class UserBiz {

	private UserDao dao = new UserDao();

	// ��ѯ����
	public List<User> selectAll(User user) throws IOException, BizException {
		if (user == null) {
			throw new BizException("�������û���Ϣ");
		}
		return dao.selectAll(user);
	}

	// ���
	public int insert(User user) throws SQLException, BizException {
		if (user == null) {
			throw new BizException("�������û���Ϣ");
		}
		if(user.getUemail() == null && user.getUphone() == null){
			throw new BizException("���������������绰���룬�����Ժ���֤");
		}else if(user.getUemail().isEmpty() && user.getUphone().isEmpty() ){
			throw new BizException("���������������绰���룬�����Ժ���֤");
		}
		if(user.getUname() == null ){
			throw new BizException("�����������û���");
		}else if(user.getUname().isEmpty()){
			throw new BizException("�����������û���");
		}
		if(user.getUpassword() == null ){
			throw new BizException("��������������");
		}else if( user.getUpassword().isEmpty() ){
			throw new BizException("��������������");
		}
		return dao.insert(user);
	}

	// ����
	public int update(User userNew, User userOld) throws SQLException, BizException {
		if (userNew == null) {
			throw new BizException("�������û���Ϣ");
		}
		if (userOld == null) {
			throw new BizException("��������Ҫ�޸ĵ��û�");
		}
		if(userOld.getUcollege() == null && userOld.getUid() == 0 && userOld.getUminname() ==null && 
				userOld.getUname() == null && userOld.getUphone() == null 
				&& userOld.getUemail() == null){
			throw new BizException("��������Ҫ�޸ĵ��û���Ϣ");
		}
		return dao.update(userNew, userOld);

	}
	// ���¶��

	public int update(List<User> list) throws SQLException, BizException {
		if (list.size() == 0) {
			throw new BizException("�������û���Ϣ");
		}
		for(User user : list){
			if( user.getUid() == 0 && user.getUminname() ==null && 
					user.getUname() == null && user.getUphone() == null 
					&& user.getUemail() == null && user.getUstate() == 0){
				throw new BizException("��������Ҫ�޸ĵ��û���Ϣ");
			}
		}
		return dao.update(list);
	}

	// ��ѯ����
	public User selectSingle(User user) throws IOException, BizException {
		if (user == null) {
			throw new BizException("�������û���Ϣ");
		}
		if(user.getUcollege() == null && user.getUid() == 0 && user.getUminname() ==null && 
				user.getUname() == null && user.getUphone() == null 
				&& user.getUemail() == null){
			throw new BizException("��������Ҫ��ѯ���û���Ϣ");
		}
		return dao.selectSingle(user);
	}
}
