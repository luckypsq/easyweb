package com.yc.easyweb.dao.lyw;

import java.util.List;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;
import com.yc.easyweb.dao.UserDao;

public class MemberDao {
	DbHelper dbHelper =new DbHelper();
	public int update(String uminname,String uphone,String university,String ucollege,
			String umajor,String uclass) throws Exception {
		String sql = "update user set uminname=?,uphone=?,university=?,"
				+ "ucollege=?,umajor=?,uclass=? where uid=?";
		return dbHelper.update(sql,uminname,uphone,university,ucollege,umajor,uclass);
	}
	public List<User> selectAll(int uid) throws Exception {
		User user = new User();
		user.setUid(uid);
		UserDao dao = new UserDao();
		return dao.selectAll(user);
	}
}
