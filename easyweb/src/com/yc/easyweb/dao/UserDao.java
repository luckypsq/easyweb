package com.yc.easyweb.dao;

import java.util.List;

import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.util.DbHelper;

import sun.security.x509.URIName;

/**
 * 操作User表的dao类
 * @author psq
 *
 */
public class UserDao {
	DbHelper db = new DbHelper();
	//查询所有
	public List<User> selectAll(User user) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(" select uid,uname,uminname,uphone,university,ucollege,umajor,upassword, "
				+ " ustate,utemp,utype,uemail,utime,usex,uage "
				+ " from user where 1=1 ");
		if(user != null){
			if(user.getUname() != null){
				sb.append(" and uname like '%"+user.getUname()+"%'");
			}
			if(user.getUniversity() != null){
				sb.append(" and university like '%"+user.getUniversity()+"%'");
			}
			
			if(user.getUcollege() != null){
				sb.append(" and ucollege like '%"+user.getUcollege()+"%'");
			}
			if(user.getUmajor() != null){
				sb.append(" and umajor like '%"+user.getUmajor()+"%'");
			}
			if(user.getUstate() != 0){
				sb.append(" and ustate = "+user.getUstate());
			}
		}
		sb.append("  order by  uid asc");
		System.out.println("User表查询语句："+sb.toString());
		System.out.println("user对象的值："+user.toString());
		List<User> list = db.selectAll(sb.toString(), null, User.class);
		return list;
		
		
	}
	//添加
	public int insert(User user){
		return 0;
		
	}
	//删除
	public int delete(User user){
		return 0;
		
	}
	//更新
	public  int update (User user) {
		return 0;
		
	}
	//其他
}
