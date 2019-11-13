package com.yc.easyweb.dao;

import java.util.List;

import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.util.DbHelper;

/**
 * 操作Eorderitem表的dao类
 * @author psq
 *
 */
public class EorderitemDao {
	DbHelper db = new DbHelper();
	//查询所有
	public List<Eorderitem> selectAll(Eorderitem eorderitem) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(" select itemid,count,bid,eoid,total,eitemp"
				+ " from eorderitem where 1=1 ");
		if(eorderitem != null){
			if(eorderitem.getBid() != 0){
				sb.append(" and bid="+eorderitem.getBid());
			}
			if(eorderitem.getEoid() != null){
				sb.append(" and eoid like'%"+eorderitem.getEoid() +"%'");
			}
			if(eorderitem.getItemid() != null){
				sb.append(" and itemid like'%"+eorderitem.getItemid()+"%'");
			}
		}
		sb.append("  order by  itemid asc");
		System.out.println("eorderitem数据库语句："+sb.toString());
		System.out.println("eorderitem实体类对象："+eorderitem.toString());
		List<Eorderitem> list = db.selectAll(sb.toString(), null, Eorderitem.class);
		return list;
		
	}
	//添加
	public int insert(Eorderitem eorderitem){
		return 0;
		
	}
	//删除
	public int delete(Eorderitem eorderitem){
		return 0;
		
	}
	//更新
	public  int update (Eorderitem eorderitem) {
		return 0;
		
	}
	//其他
}
