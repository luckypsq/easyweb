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
	public List<User> selectAll(User user) throws IOException {
		return dao.selectAll(user);
	}

	// ���
	public int insert(User user) throws SQLException, BizException {
		if (user == null) {
			throw new BizException("�������û���Ϣ");
		}
		return dao.insert(user);
	}

	// ɾ��
	public int delete(User user) {
		return 0;

	}

	// ɾ������
	public int delete(List<User> list) {
		return 0;
	}

	// ����
	public int update(User userNew, User userOld) throws SQLException, BizException {
		if (userNew == null) {
			throw new BizException("�������û���Ϣ");
		}
		if (userOld == null) {
			throw new BizException("��������Ҫ�޸ĵ��û�");
		}
		return dao.update(userNew, userOld);

	}
	// ���¶��

	public int update(List<User> list) throws SQLException, BizException {
		if (list.size() == 0) {
			throw new BizException("�������û���Ϣ");
		}
		return dao.update(list);
	}

	// ��ѯ����
	public User selectSingle(User user) throws IOException, BizException {
		if (user == null) {
			throw new BizException("�������û���Ϣ");
		}
		return dao.selectSingle(user);
	}
}
