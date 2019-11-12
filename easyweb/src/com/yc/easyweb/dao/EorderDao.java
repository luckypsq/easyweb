package com.yc.easyweb.dao;

import java.util.List;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.util.DbHelper;

/**
 * 操作Eorder表的dao类
 * @author psq
 *
 */
public class EorderDao {
	DbHelper db = new DbHelper();
	//查询所有
	public List<Eorder> selectAll(Eorder eorder) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(" select eoid,uid,eostate,eotime,eotemp,uname,eoaddr,eotype "
				+ " from eorder where 1=1 ");
		if(eorder != null){
			if(eorder.getUid() != 0){
				sb.append(" and uid="+eorder.getUid());
			}
			if(eorder.getEotime() != null){
				sb.append(" and eotime like '%"+eorder.getEotime()+"%'");
			}
			if(eorder.getUname() != null){
				sb.append(" and uname like '%"+eorder.getUname()+"%'");
			}
			if(eorder.getEotype() != null){
				sb.append(" and eotype like '%"+eorder.getEotype()+"%'");
			}
			if(eorder.getEostate() != 0){
				sb.append(" and eostate like '%"+eorder.getEostate()+"%'");
			}
		}
		sb.append("  order by  eoid asc");
		System.out.println("eorder的数据库语句："+sb.toString());
		System.out.println("eorder的实体类对象："+eorder.toString());
		List<Eorder> list = db.selectAll(sb.toString(), null, Eorder.class);
		return list;
	}
	//添加
	public int insert(Eorder eorder){
		return 0;
		
	}
	//删除
	public int delete(Eorder eorder){
		return 0;
		
	}
	//更新
	public  int update (Eorder eorder) {
		return 0;
		
	}
	//其他
}
