package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.dao.NoticeDao;

/**
 * ����Notice���������
 * 
 * @author psq
 *
 */
public class NoticeBiz {

	private NoticeDao dao = new NoticeDao();

	// ��ѯ����
	public List<Notice> selectAll(Notice notice) throws Exception {

		return dao.selectAll(notice);

	}

	// ��ѯ����
	public Notice selectSingle(Notice notice) throws Exception {
		return dao.selectSingle(notice);
	}

	// ���
	public int insert(Notice notice) {
		return 0;

	}

	// ɾ��
	public int delete(Notice notice) {
		return 0;

	}

	// ����
	public int update(Notice notice) {
		return 0;

	}
	// ����
}
