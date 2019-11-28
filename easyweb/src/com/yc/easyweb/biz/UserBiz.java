package com.yc.easyweb.biz;

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
	public List<User> selectAll(User user) throws Exception {
		return dao.selectAll(user);
	}

	// 添加
	public int insert(User user) throws Exception {
		return dao.insert(user);
	}

	// 删除
	public int delete(User user) {
		return 0;

	}

	// 删除多条
	public int delete(List<User> list){
					return 0;
	}
	// 更新
	public int update(User userNew,User userOld) throws Exception{
			return dao.update(userNew, userOld);
		
	}
	// 其他

	public int update(List<User> list) throws Exception {
		return dao.update(list);
	}

	public User selectSingle(User user2) throws Exception {
		return dao.selectSingle(user2);
	}
}
