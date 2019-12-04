package com.yc.easyweb.dao.lyw;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;

public class UserDaoLyw {
	DbHelper dbHelper =new DbHelper();
	public List<User> selectAll(User user) {
		return null;
	}
	public int update(String uminname,String uphone,String university,String ucollege,
			String umajor,String uclass,Long uid) throws SQLException  {
		String sql = "update user set uminname=?,uphone=?,university=?,"
				+ "ucollege=?,umajor=?,uclass=? where uid=?";
		return dbHelper.update(sql,uminname,uphone,university,ucollege,umajor,uclass,uid);
	}
	public User selectAll(List<Object> params) throws IOException  {
		String sql = "select * from user where uid=?";
		return DbHelper.selectSingle(sql, params, User.class);
	}
}
