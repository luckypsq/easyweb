package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.Bought;
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
	public List<Eorderitem> selectAll(Eorderitem eorderitem) throws IOException, BizException {
		if(eorderitem == null){
			throw new BizException("����д���ﳵ��Ϣ������");
		}
		return dao.selectAll(eorderitem);

	}
	//��ѯ����
	public Eorderitem selectSingle(Eorderitem eorderitem) throws IOException, BizException {
		if(eorderitem == null){
			throw new BizException("����д���ﳵ��Ϣ������");
		}
		if(eorderitem.getBid() == 0 && eorderitem.getEoid() == null && eorderitem.getItemid() == null && eorderitem.getUid() == 0){
			throw new BizException("����д��Ҫ��ѯ�Ĺ��ﳵ��Ϣ������");
		}
		return dao.selectSingle(eorderitem);
	}

	// ���
	public int insert(Eorderitem eorderitem) throws SQLException, BizException {
		if(eorderitem == null){
			throw new BizException("����д���ﳵ��Ϣ������");
		}
		if(eorderitem.getBid() == 0){
			throw new BizException("��ѡ����빺�ﳵ���鼮������");
		}
		if(eorderitem.getCarttime() == null){
			throw new BizException("����д���빺�ﳵ��ʱ�䣡����");
		}else if( eorderitem.getCarttime().isEmpty()){
			throw new BizException("����д���빺�ﳵ��ʱ�䣡����");
		}
		if(eorderitem.getCount() == 0){
			throw new BizException("����д�鱾����������");
		}
		if(eorderitem.getItemid() == null){
			throw new BizException("����д���ﳵ�ţ�����");
		}else if( eorderitem.getItemid().isEmpty()){
			throw new BizException("����д���ﳵ�ţ�����");
		}
		
		if(eorderitem.getTotal() == 0){
			throw new BizException("����д�ܼۣ�����");
		}
		if(eorderitem.getUid() == 0){
			throw new BizException("����д�û�id������");
		}
		return dao.insert(eorderitem);
	}

	// ɾ��
	public int delete(Eorderitem eorderitem) throws SQLException, BizException {
		if(eorderitem == null){
			throw new BizException("����д���ﳵ��Ϣ������");
		}
		if(eorderitem.getBid() == 0 && eorderitem.getEoid() == null && eorderitem.getItemid() == null && eorderitem.getUid() == 0){
			throw new BizException("����д��Ҫɾ���Ĺ��ﳵ��Ϣ������");
		}
		return dao.delete(eorderitem);

	}

	// ɾ��
	public int delete(List<Eorderitem> list) throws SQLException, BizException {
		if(list.size() == 0){
			throw new BizException("����д���ﳵ��Ϣ������");
		}
		for(Eorderitem eorderitem : list){
			if(eorderitem.getBid() == 0 && eorderitem.getEoid() == null && eorderitem.getItemid() == null && eorderitem.getUid() == 0){
				throw new BizException("����д��Ҫ��ѯ�Ĺ��ﳵ��Ϣ������");
			}
		}
		return dao.delete(list);
	}

	// ����
	public int update(Eorderitem eoNew, Eorderitem eoOld) throws SQLException, BizException {
		if(eoNew == null){
			throw new BizException("����д���ﳵ��Ϣ������");
		}
		if(eoOld == null){
			throw new BizException("����д��Ҫ�޸ĵĹ��ﳵ����");
		}
		if(eoOld.getBid() == 0 && eoOld.getEoid() == null && eoOld.getItemid() == null && eoOld.getUid() == 0){
			throw new BizException("����д��Ҫ�޸ĵĹ��ﳵ��Ϣ������");
		}
		return dao.update(eoNew, eoOld);
	}
	//���ﳵ��ҳ
	public Page<Bought> ePage(int page, int rows,Bought bought) throws IOException, BizException {
		if(bought == null){
			throw new BizException("�����빺�ﳵ��Ϣ������");
		}
		return dao.eoPage(page, rows, bought);
	}
	//��ѯ���ﳵ����
	public List<Bought> selectAllCart(Bought bought) throws IOException, BizException{
		if(bought == null){
			throw new BizException("�����빺�ﳵ��Ϣ������");
		}
		return dao.selectAll(bought);
	}
	//��ѯ����
	public Bought selectSingleCart(Bought bought) throws IOException, BizException{
		if(bought == null){
			throw new BizException("�����빺�ﳵ��Ϣ������");
		}
		if(bought.getBid() == 0  && bought.getItemid() == null && bought.getUid() == 0){
			throw new BizException("����д��Ҫ��ѯ�Ĺ��ﳵ��Ϣ������");
		}
		return dao.selectSingle(bought);
	}
	// ����
}
