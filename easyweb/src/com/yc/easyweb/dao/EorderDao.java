package com.yc.easyweb.dao;

import java.util.List;

import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.util.DbHelper;

/**
 * ����Eorder���dao��
 * @author psq
 *
 */
public class EorderDao {
	DbHelper db = new DbHelper();
	//��ѯ����
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
		System.out.println("eorder�����ݿ���䣺"+sb.toString());
		System.out.println("eorder��ʵ�������"+eorder.toString());
		List<Eorder> list = db.selectAll(sb.toString(), null, Eorder.class);
		return list;
	}
	//���
	public int insert(Eorder eorder){
		return 0;
		
	}
	//ɾ��
	public int delete(Eorder eorder){
		return 0;
		
	}
	//����
	public  int update (Eorder eorder) {
		return 0;
		
	}
	//����
}
