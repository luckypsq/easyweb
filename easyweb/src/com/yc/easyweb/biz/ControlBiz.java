package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.Control;
import com.yc.easyweb.dao.ControlDao;

/**
 * ����Control���������
 * 
 * @author psq
 *
 */
public class ControlBiz {

	private ControlDao dao = new ControlDao();

	// ��ѯ����
	public List<Control> selectAll(Control control) throws   IOException {
		return dao.selectAll(control);
	}
	// ��ѯ����
	public Control selectSingle(Control control) throws IOException, BizException {
		if(control == null){
			throw new BizException("����д����Ȩ����Ϣ");
		}
		if(control.getConid() == 0 && control.getConame() == null && control.getUid() == 0){
			throw new BizException("��ָ����ѯ�Ĺ���Ȩ�ޣ�����");
		}
		return dao.selectSingle(control);
	}

	// ���
	public int insert(Control control) throws SQLException, BizException {
		if(control == null){
			throw new BizException("����д����Ȩ����Ϣ");
		}
		if(control.getConame() == null ){
			throw new BizException("����д����Ȩ����Ϣ");
		}
		return dao.insert(control);

	}

	// ɾ��
	public int delete(Control control) throws SQLException, BizException {
		if(control == null){
			throw new BizException("����д����Ȩ����Ϣ");
		}
		if(control.getConid() ==0  && control.getConame() == null){
			throw new BizException("��ָ��ɾ���Ĺ���Ȩ��");
		}
		
		return dao.delete(control);

	}

	// ɾ��
	public int deleteAll(List<Control> list) throws SQLException, BizException {
		if(list.size() == 0){
			throw new BizException("����д����Ȩ����Ϣ");
		}
		for(Control control : list){
			if(control.getConid() ==0  && control.getConame() == null){
				throw new BizException("��ָ��ɾ���Ĺ���Ȩ��");
			}
		}
		return dao.delete(list);

	}

	// ����
	public int update(Control controlNew, Control controlOld) throws SQLException, BizException {
		if(controlNew == null){
			throw new BizException("����д�޸ĵĹ���Ȩ����Ϣ");
		}
		if(controlOld == null){
			throw new BizException("����д��Ҫ�Ĺ���Ȩ��");
		}
		if(controlOld.getConid() ==0  && controlOld.getConame() == null){
			throw new BizException("��ָ���޸ĵĹ���Ȩ��");
		}
		return dao.update(controlNew, controlOld);
	}
	// ����
}
