package com.yc.easyweb.dao.lyw;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;
import com.yc.easyweb.dao.UserDao;

public class MemberDao {
	DbHelper dbHelper =new DbHelper();
	public int update(String uminname,String uphone,String university,String ucollege,
			String umajor,String uclass) throws SQLException  {
		String sql = "update user set uminname=?,uphone=?,university=?,"
				+ "ucollege=?,umajor=?,uclass=? where uid=?";
		return dbHelper.update(sql,uminname,uphone,university,ucollege,umajor,uclass);
	}
	public List<User> selectAll(int uid) throws IOException  {
		User user = new User();
		user.setUid(uid);
		UserDao dao = new UserDao();
		return dao.selectAll(user);
	}
}
