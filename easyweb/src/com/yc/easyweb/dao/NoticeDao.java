package com.yc.easyweb.dao;

import java.io.IOException;
import java.util.List;

import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.bean.Page;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.common.DbHelper;

/**
 * 操作notice表的dao类
 * 
 * @author psq
 *
 */
public class NoticeDao {
	DbHelper db = new DbHelper();

	// 查询所有
	@SuppressWarnings("static-access")
	public List<Notice> selectAll(Notice notice) throws IOException  {
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
		sb.append(" order by  ntime desc");
		List<Notice> list = db.selectAll(sb.toString(), null, Notice.class);
		return list;
	}

	// 查询所有
		@SuppressWarnings("static-access")
		public Notice selectSingle(Notice notice) throws IOException  {
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
			sb.append(" order by  ntime desc");
			return db.selectSingle(sb.toString(), null, Notice.class);
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
	//公告分页
		@SuppressWarnings({ "unchecked", "static-access" })
		public Page<Notice> noticePage(int page, int rows,Notice notice) throws IOException {
			StringBuffer sb = new StringBuffer();
	    	sb.append("select * from notice where 1=1 ");
			if(notice != null) {
				if(notice.getNtime() !=null) {
					sb.append(" and ntime like '%" + notice.getNtime() + " %' ");
				}
				if(notice.getNnumber()!=null) {
					sb.append(" and nnumber = "+notice.getNnumber());
				}
				if(notice.getNauthor()!=null) {
					sb.append(" and nauthor like '%" + notice.getNauthor() +" %'");
				}
				if(notice.getNcontent()!=null) {
					sb.append(" and ncontent like '%" + notice.getNcontent() +" %'");
				}
				if(notice.getNstate()!=0) {
					sb.append("and nstate =" +notice.getNstate());
				}
				if(notice.getNtitle() !=null) {
					sb.append(" and ntitle like'%" + notice.getNtitle() +" %'");
				}
			}
				sb.append("  order by  nid asc");
			return db.selectPageForMysql(page, rows, Notice.class, sb.toString());
		}
}
