package com.yc.easyweb.biz;

import java.io.IOException;
import java.util.List;

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
	public List<Notice> selectAll(Notice notice) throws IOException, BizException  {
		if(notice == null){
			throw new BizException("����д������Ϣ������");
		}
		return dao.selectAll(notice);
	}

	// ��ѯ����
	public Notice selectSingle(Notice notice) throws BizException, IOException  {
		if(notice == null){
			throw new BizException("����д������Ϣ������");
		}
		if(notice.getNid() ==0 && notice.getNtitle() == null && notice.getNtime() == null){
			throw new BizException("����д��Ҫ��ѯ�Ĺ�����Ϣ������");
		}
		return dao.selectSingle(notice);
	}

	// ���
	public int insert(Notice notice) throws BizException {
		if(notice == null){
			throw new BizException("����д��Ҫ��ӵĹ�����Ϣ������");
		}
		if(notice.getNtime() == null ){
			throw new BizException("����д����ķ���ʱ�䣡����");
		}else if(notice.getNtime().isEmpty() ){
			throw new BizException("����д����ķ���ʱ�䣡����");
		}
		
		if (notice.getNcontent() == null ) {
			throw new BizException("����д����ķ������ݣ�����");
		}else if(notice.getNcontent().isEmpty()){
			throw new BizException("����д����ķ������ݣ�����");
		}
		
		if (notice.getNauthor() == null ) {
			throw new BizException("����д����ķ����ߣ�����");
		}else if(notice.getNauthor().isEmpty()){
			throw new BizException("����д����ķ������⣡����");
		}
		
		if (notice.getNtitle()== null ) {
			throw new BizException("����д����ķ������⣡����");
		}else if(notice.getNtitle().isEmpty()){
			throw new BizException("����д����ķ������⣡����");
		}
		return dao.insert(notice);
	}

	// ɾ��
	public int delete(Notice notice) throws BizException {
		if(notice == null){
			throw new BizException("����д��Ҫɾ���Ĺ�����Ϣ������");
		}
		if (notice.getNtitle()== null && notice.getNauthor() == null && notice.getNtime() == null && notice.getNid() == 0) {
			throw new BizException("����д��Ҫɾ���Ĺ�����Ϣ������");
		}
		return dao.delete(notice);
	}

	// ����
	public int update(Notice noticeOld, Notice noticeNew) throws BizException {
		if(noticeNew ==null || noticeOld == null){
			throw new BizException("����д��Ҫ���µĹ��棡����");
		}
		if(noticeOld.getNid() == 0 && noticeOld.getNtitle() == null){
			throw new BizException("����д��Ҫ���µĹ�����Ϣ������");
		}
		return dao.update(noticeOld, noticeNew);

	}
	//�����ҳ
		public Page<Notice> noticePage(int page, int rows,Notice notice) throws IOException, BizException {
			if(notice == null){
				throw new BizException("����д��Ҫɾ���Ĺ�����Ϣ������");
			}
			return dao.noticePage(page, rows, notice);
		}
	// ����
}
