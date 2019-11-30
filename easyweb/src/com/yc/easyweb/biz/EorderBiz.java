package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.OrderDetial;
import com.yc.easyweb.dao.EorderDao;

/**
 * ����Eorder���������
 * 
 * @author psq
 *
 */
public class EorderBiz {

	private EorderDao dao = new EorderDao();

	// ��ѯ����
	public List<Eorder> selectAll(Eorder eorder) throws Exception {
		return dao.selectAll(eorder);

	}

	// ��ѯ����
	public Eorder selectSingle(Eorder eorder) throws Exception {
		return dao.selectSingle(eorder);
	}

	// ��ѯ��������
	public List<OrderDetial> selectDetail(OrderDetial detial) throws Exception {
		return dao.selectAllDetail(detial);
	}

	// ���
	public int insert(Eorder eorder) throws Exception {
		return dao.insert(eorder);

	}

	// ɾ��
	public int delete(Eorder eorder) throws Exception {
		return dao.delete(eorder);

	}

	// ɾ�����
	public int delete(List<Eorder> list) throws Exception {
		return dao.delete(list);

	}

	// ����
	public int update(Eorder eorderNew, Eorder eorderOld) throws Exception {
		return dao.update(eorderNew, eorderOld);
	}
	// ����
}
