package com.yc.easyweb.dao;

import java.util.List;

import com.yc.easyweb.bean.Notice;

/**
 * ����notice���dao��
 * @author psq
 *
 */
public class NoticeDao {
	//��ѯ����
	public List<Notice> selectAll(Notice notice){
		StringBuffer sb = new StringBuffer();
		sb.append(" select nid,ntime,nnumber,nauthor,ncontent,nstate,ntemp "
				+ " from notice where 1=1 ");
		if(notice != null){
			if(notice.getNauthor() != null){
				sb.append(" and nnauthor like %'" + notice.getNauthor()+"'%");
			}
			if(notice.getNtime() != null){
				sb.append(" and ntime like %'"+notice.getNtime()+"'%");
			}
			if(notice.getNstate() != 0){
				sb.append(" and nstate = "+notice.getNstate());
			}
		}
		return null;
		
	}
}
