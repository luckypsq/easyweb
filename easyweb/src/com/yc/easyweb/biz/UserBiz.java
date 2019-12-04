package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.dao.UserDao;

/**
 * 操作User表的事务类
 * 
 * @author psq
 *
 */
public class UserBiz {

	private UserDao dao = new UserDao();

	// 查询所有
	public List<User> selectAll(User user) throws IOException {
		return dao.selectAll(user);
	}

	// 添加
	public int insert(User user) throws SQLException, BizException {
		if (user == null) {
			throw new BizException("请输入用户信息");
		}
		return dao.insert(user);
	}

	// 删除
	public int delete(User user) {
		return 0;

	}

	// 删除多条
	public int delete(List<User> list) {
		return 0;
	}

	// 更新
	public int update(User userNew, User userOld) throws SQLException, BizException {
		if (userNew == null) {
			throw new BizException("请输入用户信息");
		}
		if (userOld == null) {
			throw new BizException("请输入需要修改的用户");
		}
		return dao.update(userNew, userOld);

	}
	// 更新多个

	public int update(List<User> list) throws SQLException, BizException {
		if (list.size() == 0) {
			throw new BizException("请输入用户信息");
		}
		return dao.update(list);
	}

	// 查询单个
	public User selectSingle(User user) throws IOException, BizException {
		if (user == null) {
			throw new BizException("请输入用户信息");
		}
		return dao.selectSingle(user);
	}
}
