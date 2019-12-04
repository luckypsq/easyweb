package com.yc.easyweb.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	@SuppressWarnings("static-access")
	public List<Eorderitem> selectAll(Eorderitem eorderitem) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(" select itemid,count,bid,eoid,total,eitemp,uid,cartstate,carttime"
				+ " from eorderitem where 1=1 ");
		if(eorderitem != null){
			if(eorderitem.getBid() != 0){
				sb.append(" and bid="+eorderitem.getBid());
			}
			if(eorderitem.getUid() != 0){
				sb.append(" and uid="+eorderitem.getUid());
			}
			if(eorderitem.getCartstate() != 0){
				sb.append(" and cartstate="+eorderitem.getCartstate());
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
	//��ѯ����
		@SuppressWarnings("static-access")
		public Eorderitem selectSingle(Eorderitem eorderitem) throws IOException {
			StringBuffer sb = new StringBuffer();
			sb.append(" select itemid,count,bid,eoid,total,eitemp,uid,cartstate,carttime"
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
				if(eorderitem.getCartstate() != 0){
					sb.append(" and cartstate="+eorderitem.getCartstate());
				}
			}
			sb.append("  order by  itemid desc");
			return db.selectSingle(sb.toString(), null, Eorderitem.class);
			
			
		}
	//���
	public int insert(Eorderitem eorderitem) throws SQLException {
		String sql = "insert into eorderitem(itemid,bid,count,total,eitemp,uid,carttime) " 
					+ " values(?,?,?,?,?,?,?);";
		return DbHelper.update(sql, eorderitem.getItemid(),eorderitem.getBid(),eorderitem.getCount(),
				eorderitem.getTotal(),eorderitem.getEitemp()
				,eorderitem.getUid(),eorderitem.getCarttime());
	}
	//ɾ��
	public int delete(Eorderitem eorderitem) throws SQLException {
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
	//ɾ����������
	public int delete(List<Eorderitem > list) throws SQLException {
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
	
	//����
	public  int update (Eorderitem eoNew,Eorderitem eoOld) throws SQLException  {
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
		if(eoNew.getCartstate() != 0){
			sb.append(" , cartstate="+eoNew.getCartstate());
		}
		if(eoNew.getCarttime() != null){
			sb.append(" , carttime ='"+eoOld.getCarttime() +"'");
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
	//����
}
