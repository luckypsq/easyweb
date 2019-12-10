package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.Bought;
import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.bean.Page;
import com.yc.easyweb.dao.EorderitemDao;

/**
 * ����Eorderritem���������
 * 
 * @author psq
 *
 */
public class EorderitemBiz {

	private EorderitemDao dao = new EorderitemDao();

	// ��ѯ����
	public List<Eorderitem> selectAll(Eorderitem eorderitem) throws IOException {
		return dao.selectAll(eorderitem);

	}
	//��ѯ����
	public Eorderitem selectSingle(Eorderitem eorderitem) throws IOException, BizException {
		if(eorderitem == null){
			throw new BizException("����д���ﳵ��Ϣ");
		}
		return dao.selectSingle(eorderitem);
	}

	// ���
	public int insert(Eorderitem eorderitem) throws SQLException, BizException {
		if(eorderitem == null){
			throw new BizException("����д���ﳵ��Ϣ");
		}
		return dao.insert(eorderitem);
	}

	// ɾ��
	public int delete(Eorderitem eorderitem) throws SQLException, BizException {
		if(eorderitem == null){
			throw new BizException("����д���ﳵ��Ϣ");
		}
		return dao.delete(eorderitem);

	}

	// ɾ��
	public int delete(List<Eorderitem> list) throws SQLException, BizException {
		if(list.size() == 0){
			throw new BizException("����д���ﳵ��Ϣ");
		}
		return dao.delete(list);
	}

	// ����
	public int update(Eorderitem eoNew, Eorderitem eoOld) throws SQLException, BizException {
		if(eoNew == null){
			throw new BizException("����д���ﳵ��Ϣ");
		}
		if(eoOld == null){
			throw new BizException("����д��Ҫ�޸ĵĹ��ﳵ");
		}
		return dao.update(eoNew, eoOld);
	}
	//���ﳵ��ҳ
	public Page<Bought> ePage(int page, int rows,Bought bought,Long uid) throws IOException {
		return dao.eoPage(page, rows, bought,uid);
	}
	//��ѯ���ﳵ����
	public List<Bought> selectAllCart(Bought bought) throws IOException{
		return dao.selectAllCart(bought);
	}
	//��ѯ����
	public Bought selectSingleCart(Bought bought) throws IOException, BizException{
		if(bought == null){
			throw new BizException("�����빺�ﳵ��Ϣ������");
		}
		return dao.selectSingleCart(bought);
	}
	// ����
}
