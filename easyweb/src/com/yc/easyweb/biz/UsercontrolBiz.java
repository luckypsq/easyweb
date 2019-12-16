package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.Usercontrol;
import com.yc.easyweb.dao.UsercontrolDao;

/**
 * ����UsercontrolBiz���������
 * 
 * @author psq
 *
 */
public class UsercontrolBiz {

	private UsercontrolDao dao = new UsercontrolDao();

	// ��ѯ����
	public List<Usercontrol> selectAll(Usercontrol usercontrol) throws IOException {
		return dao.selectAll(usercontrol);
	}

	// ��ѯ����
	public Usercontrol selectSingle(Usercontrol usercontrol) throws IOException, BizException {
		if(usercontrol == null){
			throw new BizException("����д�û�Ȩ����Ϣ");
		}
		if(usercontrol.getConid() == 0 && usercontrol.getUid() == 0 && usercontrol.getUcon() ==0){
			throw new BizException("����д�û�Ȩ����Ϣ");
		}
		return dao.selectSingle(usercontrol);
	}

	// ���
	public int insert(Usercontrol usercontrol) throws SQLException, BizException {
		if(usercontrol == null){
			throw new BizException("����д�û�Ȩ����Ϣ");
		}
		if(usercontrol.getUid() == 0){
			throw new BizException("��ѡ�����Ա������");
		}
		if(usercontrol.getUcon() == 0){
			throw new BizException("��ѡ��Ȩ�ޣ�����");
		}
		return dao.insert(usercontrol);

	}

	// ɾ��
	public int delete(Usercontrol usercontrol) throws SQLException, BizException {
		if(usercontrol == null){
			throw new BizException("����д�û�Ȩ����Ϣ");
		}
		if(usercontrol.getConid() == 0 && usercontrol.getUid() == 0 && usercontrol.getUcon() ==0){
			throw new BizException("����д��Ҫɾ�����û�Ȩ����Ϣ");
		}
		return dao.delete(usercontrol);

	}

	// ɾ������
	public int deleteAll(List<Usercontrol> list) throws SQLException, BizException {
		if(list.size() == 0){
			throw new BizException("����д�û�Ȩ����Ϣ");
		}
		for (Usercontrol usercontrol : list) {
			if(usercontrol.getConid() == 0 && usercontrol.getUid() == 0 && usercontrol.getUcon() ==0){
				throw new BizException("����д��Ҫɾ�����û�Ȩ����Ϣ");
			}
		}
		return dao.delete(list);

	}

	// ����
	public int update(Usercontrol usercontrolNew, Usercontrol usercontrolOld) throws SQLException, BizException {
		if(usercontrolNew == null){
			throw new BizException("����д�޸ĵ��û�Ȩ����Ϣ");
		}
		if(usercontrolOld == null){
			throw new BizException("����д��Ҫ�޸ĵ��û�Ȩ��");
		}
		if(usercontrolOld.getConid() == 0 && usercontrolOld.getUid() == 0 && usercontrolOld.getUcon() ==0){
			throw new BizException("����д��Ҫ�޸ĵ��û�Ȩ����Ϣ");
		}
		return dao.update(usercontrolNew, usercontrolOld);

	}
	// ����
}
