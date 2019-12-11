package com.yc.easyweb.biz;

import java.io.IOException;
import java.util.List;

import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.bean.Page;
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
	public List<Notice> selectAll(Notice notice) throws IOException  {
		return dao.selectAll(notice);
	}

	// 查询所有
	public Notice selectSingle(Notice notice) throws BizException, IOException  {
		if(notice == null){
			throw new BizException("请填写公告信息");
		}
		if(notice.getNid() ==0 && notice.getNtitle() == null && notice.getNtime() == null){
			throw new BizException("请填写需要查询的公告信息");
		}
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
	//公告分页
		public Page<Notice> noticePage(int page, int rows,Notice notice) throws IOException {
			return dao.noticePage(page, rows, notice);
		}
	// 其他
}
