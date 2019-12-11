package com.yc.easyweb.biz;

import java.io.IOException;
import java.util.List;

import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.bean.Notice;
import com.yc.easyweb.bean.Page;
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
	public List<Notice> selectAll(Notice notice) throws IOException  {
		return dao.selectAll(notice);
	}

	// ��ѯ����
	public Notice selectSingle(Notice notice) throws BizException, IOException  {
		if(notice == null){
			throw new BizException("����д������Ϣ");
		}
		if(notice.getNid() ==0 && notice.getNtitle() == null && notice.getNtime() == null){
			throw new BizException("����д��Ҫ��ѯ�Ĺ�����Ϣ");
		}
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
	//�����ҳ
		public Page<Notice> noticePage(int page, int rows,Notice notice) throws IOException {
			return dao.noticePage(page, rows, notice);
		}
	// ����
}
