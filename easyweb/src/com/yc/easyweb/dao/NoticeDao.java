package com.yc.easyweb.dao;

import java.util.List;

import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.util.DbHelper;

/**
 * 操作notice表的dao类
 * 
 * @author psq
 *
 */
public class NoticeDao {
	DbHelper db = new DbHelper();

	// 查询所有
	public List<Notice> selectAll(Notice notice) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select nid,ntime,nnumber,nauthor,ncontent,nstate,ntemp,ntitle " + " from notice where 1=1 ");
		if (notice != null) {
			// 按发布者查
			if (notice.getNauthor() != null) {
				sb.append(" and nauthor like %'" + notice.getNauthor() + "'%");
			}
			// 按时间查
			if (notice.getNtime() != null) {
				sb.append(" and ntime like %'" + notice.getNtime() + "'%");
			}
			// 按状态查
			if (notice.getNstate() != 0) {
				sb.append(" and nstate = " + notice.getNstate());
			}
		}
		sb.append(" order by  ntime asc");
		/*
		 * System.out.println("notice数据库语句："+sb.toString());
		 * System.out.println("notice实体类对象："+notice.toString());
		 */
		List<Notice> list = db.selectAll(sb.toString(), null, Notice.class);
		return list;
	}

	// 添加
	public int insert(User user) {
		return 0;

	}
	// 删除
	public int delete(User user) {
		return 0;

	}
	// 更新
	public int update(User user) {
		return 0;

	}
}
