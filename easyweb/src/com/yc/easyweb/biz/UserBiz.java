package com.yc.easyweb.biz;

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
	public List<User> selectAll(User user) throws Exception {
		return dao.selectAll(user);
	}

	// ���
	public int insert(User user) throws Exception {
		return dao.insert(user);
	}

	// ɾ��
	public int delete(User user) {
		return 0;

	}

	// ɾ������
	public int delete(List<User> list){
					return 0;
	}
	// ����
	public int update(User userNew,User userOld) throws Exception{
			return dao.update(userNew, userOld);
		
	}
	// ����

	public int update(List<User> list) throws Exception {
		return dao.update(list);
	}

	public User selectSingle(User user2) throws Exception {
		return dao.selectSingle(user2);
	}
}
