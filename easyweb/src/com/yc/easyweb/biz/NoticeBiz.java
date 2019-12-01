package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.dao.NoticeDao;

/**
 * 操作Notice表的事务类
 * 
 * @author psq
 *
 */
public class NoticeBiz {

	private NoticeDao dao = new NoticeDao();

	// 查询所有
	public List<Notice> selectAll(Notice notice) throws Exception {

		return dao.selectAll(notice);

	}

	// 查询所有
	public Notice selectSingle(Notice notice) throws Exception {
		return dao.selectSingle(notice);
	}

	// 添加
	public int insert(Notice notice) {
		return 0;

	}

	// 删除
	public int delete(Notice notice) {
		return 0;

	}

	// 更新
	public int update(Notice notice) {
		return 0;

	}
	// 其他
}
