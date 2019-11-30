package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.dao.EorderitemDao;

/**
 * ����Eorderritem���������
 * @author psq
 *
 */
public class EorderitemBiz {

	private EorderitemDao dao = new EorderitemDao();
	
	//��ѯ����
		public List<Eorderitem> selectAll(Eorderitem eorderitem) throws Exception{
			return dao.selectAll(eorderitem);
			
		}
		public Eorderitem selectSingle(Eorderitem eorderitem) throws Exception{
			return dao.selectSingle(eorderitem);
		}
		//���
		public int insert(Eorderitem eorderitem) throws Exception{
			return dao.insert(eorderitem);
		}
		//ɾ��
		public int delete(Eorderitem eorderitem) throws Exception{
			return dao.delete(eorderitem);
			
		}
		//ɾ��
				public int delete(List<Eorderitem> list) throws Exception{
					return dao.delete(list);
				}
		//����
		public  int update (Eorderitem eoNew, Eorderitem eoOld) throws Exception {
			return dao.update(eoNew, eoOld);
		}
		//����
}
