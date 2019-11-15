package com.yc.easyweb.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.common.DbHelper;

/**
 * ����Eorderitem���dao��
 * @author psq
 *
 */
public class EorderitemDao {
	DbHelper db = new DbHelper();
	//��ѯ����
	public List<Eorderitem> selectAll(Eorderitem eorderitem) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(" select itemid,count,bid,eoid,total,eitemp"
				+ " from eorderitem where 1=1 ");
		if(eorderitem != null){
			if(eorderitem.getBid() != 0){
				sb.append(" and bid="+eorderitem.getBid());
			}
			if(eorderitem.getEoid() != null){
				sb.append(" and eoid ='"+eorderitem.getEoid() +"'");
			}
			if(eorderitem.getItemid() != null){
				sb.append(" and itemid ='"+eorderitem.getItemid()+"'");
			}
		}
		sb.append("  order by  itemid asc");
		/*System.out.println("eorderitem���ݿ���䣺"+sb.toString());
		System.out.println("eorderitemʵ�������"+eorderitem.toString());*/
		List<Eorderitem> list = db.selectAll(sb.toString(), null, Eorderitem.class);
		return list;
		
	}
	//���
	public int insert(Eorderitem eorderitem) throws Exception{
		String sql = "insert into eorderitem(itemid,count,bid,eoid,total,eitemp) " 
					+ " values(?,?,?,?,?,?);";
		return DbHelper.update(sql, eorderitem.getItemid(),eorderitem.getCount(),
				eorderitem.getBid(),eorderitem.getEoid(),eorderitem.getTotal(),eorderitem.getEitemp());
	}
	//ɾ��
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
		if(eorderitem.getItemid() != null){
			sb.append(" and itemid ='"+eorderitem.getItemid()+"'");
		}
		return DbHelper.update(sb.toString(), null);
	}
	//ɾ����������
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
			sqList.add(sb.toString());
		}
		return DbHelper.update(sqList, null);
	}
	
	//����
	public  int update (Eorderitem eoNew,Eorderitem eoOld) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (eoNew== null || eoOld== null) {
			return 0;
		}
		sb.append("update eorderitem set eitemp='' ");
		if(eoNew.getBid() != 0){
			sb.append(" , bid="+eoNew.getBid());
		}
		if(eoNew.getEoid() != null){
			sb.append(" , eoid ='"+eoNew.getEoid() +"'");
		}
		if(eoNew.getItemid() != null){
			sb.append(" , itemid ='"+eoNew.getItemid()+"'");
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
		return DbHelper.update(sb.toString(), null);
	}
	//����
}
