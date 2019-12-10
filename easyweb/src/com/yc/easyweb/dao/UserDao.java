package com.yc.easyweb.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;

/**
 * 操作User表的dao类
 * 
 * @author psq
 *
 */
public class UserDao {
	DbHelper db = new DbHelper();

	// 查询所有
	@SuppressWarnings("static-access")
	public List<User> selectAll(User user) throws IOException  {
		StringBuffer sb = new StringBuffer();
		sb.append(" select uid,uname,uminname,uphone,university,ucollege,umajor,upassword, "
				+ " ustate,utemp,utype,uemail,utime,usex,uage,uclass " + " from user where 1=1 ");
		if (user != null) {
			if (user.getUminname() != null) {
				sb.append(" and uminname like '%" + user.getUminname() + "%'");
			}
			if (user.getUname() != null) {
				sb.append(" and uname like '%" + user.getUname() + "%'");
			}
			if (user.getUphone() != null) {
				sb.append(" and uphone like '%" + user.getUphone() + "%'");
			}
			if (user.getUniversity() != null) {
				sb.append(" and university like '%" + user.getUniversity() + "%'");
			}
			if (user.getUcollege() != null) {
				sb.append(" and ucollege like '%" + user.getUcollege() + "%'");
			}
			if (user.getUmajor() != null) {
				sb.append(" and umajor like '%" + user.getUmajor() + "%'");
			}
			if (user.getUstate() != 0) {
				sb.append(" and ustate = " + user.getUstate());
			}
			if (user.getUclass() != null) {
				sb.append(" and uclass = '" + user.getUclass() + "'");
			}
			if (user.getUtype() != 0) {
				sb.append(" and utype = " + user.getUtype());
			}
			if (user.getUage() != 0) {
				sb.append(" and uage = " + user.getUage());
			}
			if (user.getUsex() != 0) {
				sb.append(" and usex = " + user.getUsex());
			}
		}
		sb.append("  order by  uid desc");
		List<User> list = db.selectAll(sb.toString(), null, User.class);
		return list;

	}
	// 查询单个
		@SuppressWarnings("static-access")
		public User selectSingle(User user) throws IOException  {
			StringBuffer sb = new StringBuffer();
			sb.append(" select uid,uname,uminname,uphone,university,ucollege,umajor,upassword, "
					+ " ustate,utemp,utype,uemail,utime,usex,uage,uclass " + " from user where 1=1 ");
			if (user != null) {
				if (user.getUminname() != null) {
					sb.append(" and uminname like '%" + user.getUminname() + "%'");
				}
				if (user.getUname() != null) {
					sb.append(" and uname like '%" + user.getUname() + "%'");
				}
				if (user.getUphone() != null) {
					sb.append(" and uphone like '%" + user.getUphone() + "%'");
				}
				if (user.getUniversity() != null) {
					sb.append(" and university like '%" + user.getUniversity() + "%'");
				}
				if (user.getUcollege() != null) {
					sb.append(" and ucollege like '%" + user.getUcollege() + "%'");
				}
				if (user.getUmajor() != null) {
					sb.append(" and umajor like '%" + user.getUmajor() + "%'");
				}
				//邮箱
				if (user.getUemail()!= null) {
					sb.append(" and uemail like '%" + user.getUemail() + "%'");
				}
				if (user.getUstate() != 0) {
					sb.append(" and ustate = " + user.getUstate());
				}
				if (user.getUclass() != null) {
					sb.append(" and uclass = '" + user.getUclass() + "'");
				}
				if (user.getUtype() != 0) {
					sb.append(" and utype = " + user.getUtype());
				}
				if (user.getUage() != 0) {
					sb.append(" and uage = " + user.getUage());
				}
				if (user.getUsex() != 0) {
					sb.append(" and usex = " + user.getUsex());
				}
				if (user.getUid() != 0) {
					sb.append(" and uid = " + user.getUid());
				}
			}
			sb.append("  order by  uid desc");
			return db.selectSingle(sb.toString(), null,User.class);
		}
	// 添加
	public int insert(User user) throws SQLException  {
		String sql = "insert into user(uid,uname,uminname,uphone,university,ucollege,umajor,uclass,upassword,"
				+ " utemp,utype,uemail,utime,usex,uage) " + " values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		return DbHelper.update(sql, user.getUname(), user.getUminname(), user.getUphone(), user.getUniversity(),
				user.getUcollege(), user.getUmajor(), user.getUclass(), user.getUpassword(), 
				user.getUtemp(), user.getUtype(), user.getUemail(), user.getUtime(), user.getUsex(), user.getUage());
	}

	// 删除
	public int delete(User user) throws SQLException  {
		StringBuffer sb = new StringBuffer();
		if (user == null) {
			return 0;
		}
		sb.append("delete from user where 1=1 ");
		if (user.getUminname() != null) {
			sb.append(" and uminname ='" + user.getUminname() + "'");
		}
		if (user.getUname() != null) {
			sb.append(" and uname ='" + user.getUname() + "'");
		}
		if (user.getUid() != 0) {
			sb.append(" and uid = " + user.getUid());
		}
		if (user.getUstate() != 0) {
			sb.append(" and ustate = " + user.getUstate());
		}
		return db.update(sb.toString(), null);

	}

	// 删除多条记录
	@SuppressWarnings("static-access")
	public int delete(List<User> list) throws SQLException  {
		StringBuffer sb = null;
		if (list.size() == 0) {
			return 0;
		}
		List<String> sqList = new ArrayList<String>();
		for (User user : list) {
			sb = new StringBuffer();
			sb.append("delete from user where 1=1 ");
			if (user.getUminname() != null) {
				sb.append(" and uminname ='" + user.getUminname() + "'");
			}
			if (user.getUname() != null) {
				sb.append(" and uname ='" + user.getUname() + "'");
			}
			if (user.getUid() != 0) {
				sb.append(" and uid = " + user.getUid());
			}
			if (user.getUstate() != 0) {
				sb.append(" and ustate = " + user.getUstate());
			}
			sqList.add(sb.toString());
		}
		return db.update(sqList);
	}

	// 更新
	public int update(User userNew, User userOld) throws SQLException  {
		StringBuffer sb = new StringBuffer();
		if (userNew == null || userOld == null) {
			return 0;
		}
		sb.append("update user set utemp='' ");
		if (userNew.getUminname() != null) {
			sb.append(" , uminname ='" + userNew.getUminname() + "'");
		}
		if (userNew.getUname() != null) {
			sb.append(" , uname ='" + userNew.getUname() + "'");
		}
		if (userNew.getUphone() != null) {
			sb.append(" , uphone ='" + userNew.getUphone() + "'");
		}
		if (userNew.getUniversity() != null) {
			sb.append(" , university ='" + userNew.getUniversity() + "'");
		}
		if (userNew.getUcollege() != null) {
			sb.append(" , ucollege ='" + userNew.getUcollege() + "'");
		}
		if (userNew.getUmajor() != null) {
			sb.append(" , umajor ='" + userNew.getUmajor() + "'");
		}
		if (userNew.getUstate() != 0) {
			sb.append(" , ustate = " + userNew.getUstate());
		}
		if (userNew.getUclass() != null) {
			sb.append(" , uclass = '" + userNew.getUclass() + "'");
		}
		if (userNew.getUtype() != 0) {
			sb.append(" , utype = " + userNew.getUtype());
		}
		if (userNew.getUage() != 0) {
			sb.append(" , uage = " + userNew.getUage());
		}
		if (userNew.getUsex() != 0) {
			sb.append(" , usex = " + userNew.getUsex());
		}
		if (userNew.getUemail() != null) {
			sb.append(" , uemail = '" + userNew.getUemail() + "'");
		}
		if (userNew.getUpassword() != null) {
			sb.append(" , upassword = '" + userNew.getUpassword() + "'");
		}
		
		
		sb.append(" where 1=1 ");
		
		if (userOld.getUname() != null) {
			sb.append(" and uname ='" + userOld.getUname() + "'");
		}
		if (userOld.getUid() != 0) {
			sb.append(" and uid = " + userOld.getUid());
		}
		return DbHelper.update(sb.toString(), null);
	}

	// 更新多条
	public int update(List<User> list) throws SQLException  {
		StringBuffer sb = new StringBuffer();
		List<String> sqList = new ArrayList<String>();
		if (list.size()== 0) {
			return 0;
		}
		for (User user  : list) {
			sb.append("update user set utemp='' ");
			if (user.getUstate() != 0) {
				sb.append(" , ustate = " + user.getUstate());
			}
			sb.append(" where 1=1 ");
			if (user.getUid() != 0) {
				sb.append(" and uid = " + user.getUid());
			}
			sqList.add(sb.toString());
		}
		return DbHelper.update(sqList);
	}
	// 其他
}
