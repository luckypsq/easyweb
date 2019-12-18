package com.yc.easyweb.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.bean.Page;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.bean.Usercontrol;
import com.yc.easyweb.common.DbHelper;

/**
 * ����notice���dao��
 * 
 * @author psq
 *
 */
public class NoticeDao {
	DbHelper db = new DbHelper();

	// ��ѯ����
	@SuppressWarnings("static-access")
	public List<Notice> selectAll(Notice notice) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(" select nid,ntime,nnumber,nauthor,ncontent,nstate,ntemp,ntitle " + " from notice where 1=1 ");
		if (notice != null) {
			// �������߲�
			if (notice.getNauthor() != null ) {
				sb.append(" and nauthor like '%" + notice.getNauthor() + "%'");
			}
			// ��ʱ���
			if (notice.getNtime() != null) {
				sb.append(" and ntime like '%" + notice.getNtime() + "%'");
			}
			// ��״̬��
			if (notice.getNstate() != 0) {
				sb.append(" and nstate = " + notice.getNstate());
			}
		}
		sb.append(" order by  ntime desc");
		List<Notice> list = db.selectAll(sb.toString(), null, Notice.class);
		return list;
	}

	// ��ѯ����
	@SuppressWarnings("static-access")
	public Notice selectSingle(Notice notice) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(" select nid,ntime,nnumber,nauthor,ncontent,nstate,ntemp,ntitle " + " from notice where 1=1 ");
		if (notice != null) {
			// �������߲�
			if (notice.getNauthor() != null) {
				sb.append(" and nauthor = '" + notice.getNauthor() + "'");
			}
			// ��ʱ���
			if (notice.getNtime() != null) {
				sb.append(" and ntime = '" + notice.getNtime() + "'");
			}
			// ��״̬��
			if (notice.getNstate() != 0) {
				sb.append(" and nstate = " + notice.getNstate());
			}
			if (notice.getNid() != 0) {
				sb.append(" and nid = " + notice.getNid());
			}
		}
		sb.append(" order by  ntime desc");
		return db.selectSingle(sb.toString(), null, Notice.class);
	}

	// ���
	public int insert(Notice notice) {
		String sql = "insert into notice(nid,ntime,nnumber,nauthor,ncontent,ntitle,ntemp,nstate) " + " values(null,?,?,?,?,?,?,default);";
		return DbHelper.update(sql, notice.getNtime(),notice.getNnumber(), notice.getNauthor(), notice.getNcontent(), notice.getNtitle(),notice.getNtemp());
	}

	// ɾ��
	public int delete(Notice notice) {
		StringBuffer sb = new StringBuffer();
		if (notice == null) {
			return 0;
		}
		sb.append("delete from notice where 1=1 ");
		if (notice.getNid() != 0) {
			sb.append(" and nid = " + notice.getNid());
		}
		if (notice.getNtitle() != null) {
			sb.append(" and ntitle = '" + notice.getNtitle() + "'");
		}
		return DbHelper.update(sb.toString(), null);
	}

	// ɾ������
	public int deleteMore(List<Notice> list) throws SQLException {
		StringBuffer sb = null;
		if (list.size() == 0) {
			return 0;
		}
		List<String> sqList = new ArrayList<String>();
		for (Notice notice : list) {
			sb = new StringBuffer();
			sb.append("delete from usercontrol where 1=1 ");
			if (notice.getNid() != 0) {
				sb.append(" and nid = " + notice.getNid());
			}
			if (notice.getNtitle() != null) {
				sb.append(" and ntitle = '" + notice.getNtitle() + "'");
			}
			sqList.add(sb.toString());
		}
		return DbHelper.update(sqList);
	}

	// ����
	public int update(Notice noticeOld, Notice noticeNew) {
		StringBuffer sb = new StringBuffer();
		if (noticeNew== null || noticeOld== null) {
			return 0;
		}
		sb.append("update notice set ctemp='' ");
		if (noticeNew.getNauthor() != null && !noticeNew.getNauthor().isEmpty()) {
			sb.append(" , nauthor = '" + noticeNew.getNauthor() +"'");
		}
		if (noticeNew.getNtitle() != null && !noticeNew.getNtitle().isEmpty()) {
			sb.append(" , ntitle = '" + noticeNew.getNtitle() + "'");
		}
		if (noticeNew.getNcontent() != null && !noticeNew.getNcontent().isEmpty()) {
			sb.append(" and ncontent = '" + noticeNew.getNcontent() + "'");
		}
		if(noticeNew.getNnumber() != 0){
			sb.append(" and nnumber = " + noticeNew.getNnumber());
		}
		if(noticeNew.getNstate() != 0){
			sb.append(" and nstate = " + noticeNew.getNstate());
		}
		if(noticeNew.getNtemp() != null && !noticeNew.getNtemp().isEmpty()){
			sb.append(" and ntemp = '" + noticeNew.getNtemp() + "'");
		}
		if(noticeNew.getNtime() != null && !noticeNew.getNtime().isEmpty()){
			sb.append(" and ntime = '" + noticeNew.getNtime() + "'");
		}
		sb.append(" where 1=1 ");
		
		if(noticeOld.getNid() != 0){
			sb.append(" and nid = " + noticeOld.getNid());
		}
		if(noticeOld.getNtitle() != null && !noticeNew.getNtitle().isEmpty()){
			sb.append(" and ntitle = '"+noticeOld.getNtitle() + "'");
		}
		return DbHelper.update(sb.toString(), null);
	}

	// �����ҳ
	@SuppressWarnings({ "unchecked", "static-access" })
	public Page<Notice> noticePage(int page, int rows, Notice notice) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("select nid,ntime,nnumber,nauthor,ncontent,nstate,ntemp,ntitle from notice where 1=1 ");
		if (notice != null) {
			if (notice.getNtime() != null) {
				sb.append(" and ntime like '%" + notice.getNtime() + " %' ");
			}
			if (notice.getNauthor() != null) {
				sb.append(" and nauthor like '%" + notice.getNauthor() + " %'");
			}
			if (notice.getNcontent() != null) {
				sb.append(" and ncontent like '%" + notice.getNcontent() + " %'");
			}
			if (notice.getNstate() != 0) {
				sb.append("and nstate =" + notice.getNstate());
			}
			if (notice.getNtitle() != null) {
				sb.append(" and ntitle like'%" + notice.getNtitle() + " %'");
			}
		}
		sb.append("  order by  nid asc");
		return db.selectPageForMysql(page, rows, Notice.class, sb.toString());
	}
}
