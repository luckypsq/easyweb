package com.yc.easyweb.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.common.DbHelper;

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
		sb.append(" select itemid,count,bid,eoid,total,eitemp,uid"
				+ " from eorderitem where 1=1 ");
		if(eorderitem != null){
			if(eorderitem.getBid() != 0){
				sb.append(" and bid="+eorderitem.getBid());
			}
			if(eorderitem.getUid() != 0){
				sb.append(" and uid="+eorderitem.getUid());
			}
			if(eorderitem.getEoid() != null){
				sb.append(" and eoid ='"+eorderitem.getEoid() +"'");
			}
			if(eorderitem.getItemid() != null){
				sb.append(" and itemid ='"+eorderitem.getItemid()+"'");
			}
		}
		sb.append("  order by  itemid desc");
		List<Eorderitem> list = db.selectAll(sb.toString(), null, Eorderitem.class);
		return list;
		
	}
	//添加
	public int insert(Eorderitem eorderitem) throws Exception{
		String sql = "insert into eorderitem(itemid,count,bid,eoid,total,eitemp,uid) " 
					+ " values(?,?,?,?,?,?,?);";
		return DbHelper.update(sql, eorderitem.getItemid(),eorderitem.getCount(),
				eorderitem.getBid(),eorderitem.getEoid(),eorderitem.getTotal(),eorderitem.getEitemp(),eorderitem.getUid());
	}
	//删除
	public int delete(Eorderitem eorderitem) throws Exception{
		StringBuffer sb = new StringBuffer();
		if (eorderitem == null) {
			return 0;
		}
		sb.append("delete from eorderitem where 1=1 ");
		if(eorderitem.getBid() != 0){
			sb.append(" and bid="+eorderitem.getBid());
		}
		if(eorderitem.getEoid() != null){
			sb.append(" and eoid ='"+eorderitem.getEoid() +"'");
		}
		if(eorderitem.getUid() != 0){
			sb.append(" and uid="+eorderitem.getUid());
		}
		if(eorderitem.getItemid() != null){
			sb.append(" and itemid ='"+eorderitem.getItemid()+"'");
		}
		return DbHelper.update(sb.toString(), null);
	}
	//删除多条数据
	public int delete(List<Eorderitem > list) throws Exception{
		StringBuffer sb = null;
		if (list.size() == 0) {
			return 0;
		}
		List<String> sqList = new ArrayList<String>();
		for (Eorderitem eorderitem : list) {
			sb = new StringBuffer();
			sb.append("delete from eorderitem where 1=1 ");
			if(eorderitem.getBid() != 0){
				sb.append(" and bid="+eorderitem.getBid());
			}
			if(eorderitem.getEoid() != null){
				sb.append(" and eoid ='"+eorderitem.getEoid() +"'");
			}
			if(eorderitem.getItemid() != null){
				sb.append(" and itemid ='"+eorderitem.getItemid()+"'");
			}
			if(eorderitem.getUid() != 0){
				sb.append(" and uid="+eorderitem.getUid());
			}
			sqList.add(sb.toString());
		}
		return DbHelper.update(sqList);
	}
	
	//更新
	public  int update (Eorderitem eoNew,Eorderitem eoOld) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (eoNew== null || eoOld== null) {
			return 0;
		}
		sb.append("update eorderitem set eitemp='' ");
		if(eoNew.getBid() != 0){
			sb.append(" , bid="+eoNew.getBid());
		}
		if(eoNew.getCount() != 0){
			sb.append(" , count ="+eoNew.getCount());
		}
		if(eoNew.getTotal() != 0){
			sb.append(" , total ="+eoNew.getTotal());
		}
		sb.append(" where 1=1 ");
		if(eoOld.getBid() != 0){
			sb.append(" and bid="+eoOld.getBid());
		}
		if(eoOld.getEoid() != null){
			sb.append(" and eoid ='"+eoOld.getEoid() +"'");
		}
		if(eoOld.getItemid() != null){
			sb.append(" and itemid ='"+eoOld.getItemid()+"'");
		}
		if(eoOld.getUid() != 0){
			sb.append(" and uid="+eoOld.getUid());
		}
		return DbHelper.update(sb.toString(), null);
	}
	//其他
}
