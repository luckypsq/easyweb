package com.yc.easyweb.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mysql.fabric.xmlrpc.base.Array;
import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
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
				+ " ustate,utemp,utype,uemail,utime,usex,uage,uclass "
				+ " from user where 1=1 ");
		if(user != null){
			if(user.getUminname() != null){
				sb.append(" and uminname like '%"+user.getUminname()+"%'");
			}
			if(user.getUname() != null){
				sb.append(" and uname like '%"+user.getUname()+"%'");
			}
			if(user.getUphone() != null){
				sb.append(" and uphone like '%"+user.getUphone()+"%'");
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
			if(user.getUclass() != null){
				sb.append(" and uclass = '"+user.getUclass()+"'");
			}
			if(user.getUtype() != 0){
				sb.append(" and utype = "+user.getUtype());
			}
			if(user.getUage() != 0){
				sb.append(" and uage = "+user.getUage());
			}
			if(user.getUsex() != 0){
				sb.append(" and usex = "+user.getUsex());
			}
		}
		sb.append("  order by  uid desc");
		/*System.out.println("User表查询语句："+sb.toString());
		System.out.println("user对象的值："+user.toString());*/
		List<User> list = db.selectAll(sb.toString(), null, User.class);
		return list;
		
		
	}
	//添加
	public int insert(User user) throws Exception{
		String sql = "insert into user(uid,uname,uminname,uphone,university,ucollege,umajor,uclass,upassword,"
				+ " ustate,utemp,utype,uemail,utime,usex,uage) "
				+ " values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		return DbHelper.update(sql,user.getUname(),user.getUminname(),user.getUphone()
				,user.getUniversity(),user.getUcollege(),user.getUmajor()
				,user.getUclass(),user.getUpassword(),user.getUstate(),user.getUtemp()
				,user.getUtype(),user.getUemail(),user.getUtime(),user.getUsex(),user.getUage());
	}
	//删除
	public int delete(User user) throws Exception{
		StringBuffer sb = new StringBuffer();
		if (user == null) {
			return 0;
		}
		sb.append("delete from user where 1=1 ");
		if(user.getUminname() != null){
			sb.append(" and uminname ='"+user.getUminname()+"'");
		}
		if(user.getUname() != null){
			sb.append(" and uname ='"+user.getUname()+"'");
		}
		if(user.getUphone() != null){
			sb.append(" and uphone ='"+user.getUphone()+"'");
		}
		if(user.getUniversity() != null){
			sb.append(" and university ='"+user.getUniversity()+"'");
		}
		if(user.getUcollege() != null){
			sb.append(" and ucollege ='"+user.getUcollege()+"'");
		}
		if(user.getUmajor() != null){
			sb.append(" and umajor ='"+user.getUmajor()+"'");
		}
		if(user.getUstate() != 0){
			sb.append(" and ustate = "+user.getUstate());
		}
		if(user.getUclass() != null){
			sb.append(" and uclass = '"+user.getUclass()+"'");
		}
		if(user.getUtype() != 0){
			sb.append(" and utype = "+user.getUtype());
		}
		if(user.getUage() != 0){
			sb.append(" and uage = "+user.getUage());
		}
		if(user.getUsex() != 0){
			sb.append(" and usex = "+user.getUsex());
		}
		return db.update(sb.toString(), null);
		
	}
	//删除多条记录
	public int delete(List<User> list) throws Exception{
		StringBuffer sb = null;
		if(list.size() == 0) {
			return 0;
		}
		List<String> sqList = new ArrayList<String>();
		for (User user:list) {
			sb = new StringBuffer();
			sb.append("delete from user where 1=1 ");
			if(user.getUminname() != null){
				sb.append(" and uminname ='"+user.getUminname()+"'");
			}
			if(user.getUname() != null){
				sb.append(" and uname ='"+user.getUname()+"'");
			}
			if(user.getUphone() != null){
				sb.append(" and uphone ='"+user.getUphone()+"'");
			}
			if(user.getUniversity() != null){
				sb.append(" and university ='"+user.getUniversity()+"'");
			}
			if(user.getUcollege() != null){
				sb.append(" and ucollege ='"+user.getUcollege()+"'");
			}
			if(user.getUmajor() != null){
				sb.append(" and umajor ='"+user.getUmajor()+"'");
			}
			if(user.getUstate() != 0){
				sb.append(" and ustate = "+user.getUstate());
			}
			if(user.getUclass() != null){
				sb.append(" and uclass = '"+user.getUclass()+"'");
			}
			if(user.getUtype() != 0){
				sb.append(" and utype = "+user.getUtype());
			}
			if(user.getUage() != 0){
				sb.append(" and uage = "+user.getUage());
			}
			if(user.getUsex() != 0){
				sb.append(" and usex = "+user.getUsex());
			}
			sqList.add(sb.toString());
		}
		return db.update(sqList, null);
	}
	//更新
	public  int update (User userNew,User userOld) throws Exception {
		StringBuffer sb = new StringBuffer();
		if ( userNew== null || userOld== null) {
			return 0;
		}
		sb.append("update cart set ctemp='' ");
		if(userNew.getUminname() != null){
			sb.append(" , uminname ='"+userNew.getUminname()+"'");
		}
		if(userNew.getUname() != null){
			sb.append(" , uname ='"+userNew.getUname()+"'");
		}
		if(userNew.getUphone() != null){
			sb.append(" , uphone ='"+userNew.getUphone()+"'");
		}
		if(userNew.getUniversity() != null){
			sb.append(" , university ='"+userNew.getUniversity()+"'");
		}
		if(userNew.getUcollege() != null){
			sb.append(" , ucollege ='"+userNew.getUcollege()+"'");
		}
		if(userNew.getUmajor() != null){
			sb.append(" , umajor ='"+userNew.getUmajor()+"'");
		}
		if(userNew.getUstate() != 0){
			sb.append(" , ustate = "+userNew.getUstate());
		}
		if(userNew.getUclass() != null){
			sb.append(" , uclass = '"+userNew.getUclass()+"'");
		}
		if(userNew.getUtype() != 0){
			sb.append(" , utype = "+userNew.getUtype());
		}
		if(userNew.getUage() != 0){
			sb.append(" , uage = "+userNew.getUage());
		}
		if(userNew.getUsex() != 0){
			sb.append(" , usex = "+userNew.getUsex());
		}
		sb.append(" where 1=1 ");
		if(userOld.getUminname() != null){
			sb.append(" and uminname ='"+userOld.getUminname()+"'");
		}
		if(userOld.getUname() != null){
			sb.append(" and uname ='"+userOld.getUname()+"'");
		}
		if(userOld.getUphone() != null){
			sb.append(" and uphone ='"+userOld.getUphone()+"'");
		}
		if(userOld.getUniversity() != null){
			sb.append(" and university ='"+userOld.getUniversity()+"'");
		}
		if(userOld.getUcollege() != null){
			sb.append(" and ucollege ='"+userOld.getUcollege()+"'");
		}
		if(userOld.getUmajor() != null){
			sb.append(" and umajor ='"+userOld.getUmajor()+"'");
		}
		if(userOld.getUstate() != 0){
			sb.append(" and ustate = "+userOld.getUstate());
		}
		if(userOld.getUclass() != null){
			sb.append(" and uclass = '"+userOld.getUclass()+"'");
		}
		if(userOld.getUtype() != 0){
			sb.append(" and utype = "+userOld.getUtype());
		}
		if(userOld.getUage() != 0){
			sb.append(" and uage = "+userOld.getUage());
		}
		if(userOld.getUsex() != 0){
			sb.append(" and usex = "+userOld.getUsex());
		}
		return DbHelper.update(sb.toString(), null);
	}
	//其他
}
