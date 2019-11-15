package com.yc.easyweb.dao;

import java.util.List;

import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.util.DbHelper;

/**
 * ����notice���dao��
 * 
 * @author psq
 *
 */
public class NoticeDao {
	DbHelper db = new DbHelper();

	// ��ѯ����
	public List<Notice> selectAll(Notice notice) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select nid,ntime,nnumber,nauthor,ncontent,nstate,ntemp,ntitle " + " from notice where 1=1 ");
		if (notice != null) {
			// �������߲�
			if (notice.getNauthor() != null) {
				sb.append(" and nauthor like %'" + notice.getNauthor() + "'%");
			}
			// ��ʱ���
			if (notice.getNtime() != null) {
				sb.append(" and ntime like %'" + notice.getNtime() + "'%");
			}
			// ��״̬��
			if (notice.getNstate() != 0) {
				sb.append(" and nstate = " + notice.getNstate());
			}
		}
		sb.append(" order by  ntime asc");
		/*
		 * System.out.println("notice���ݿ���䣺"+sb.toString());
		 * System.out.println("noticeʵ�������"+notice.toString());
		 */
		List<Notice> list = db.selectAll(sb.toString(), null, Notice.class);
		return list;
	}

	// ���
	public int insert(User user) {
		return 0;

	}
	// ɾ��
	public int delete(User user) {
		return 0;

	}
	// ����
	public int update(User user) {
		return 0;

	}
}
