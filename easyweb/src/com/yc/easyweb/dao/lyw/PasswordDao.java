package com.yc.easyweb.dao.lyw;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;
import com.yc.easyweb.dao.UserDao;







public class PasswordDao {
	DbHelper dbHelper =new DbHelper();
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
