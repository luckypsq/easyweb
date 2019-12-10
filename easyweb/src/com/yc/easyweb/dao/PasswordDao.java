package com.yc.easyweb.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;


public class PasswordDao {
	DbHelper dbHelper =new DbHelper();
	@SuppressWarnings("static-access")
	public void update(User user) throws SQLException  {
		String sql = "update user set upassword=?  where uid=?";
		dbHelper.update(sql, user.getUpassword(),user.getUid());
	}
	public List<User> selectAll() throws IOException  {
		User user = new User();
		UserDao dao = new UserDao();
		return dao.selectAll(user);
	}
}
