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
		//���
		public int insert(Eorderitem eorderitem){
			return 0;
			
		}
		//ɾ��
		public int delete(Eorderitem eorderitem){
			return 0;
			
		}
		//����
		public  int update (Eorderitem eorderitem) {
			return 0;
			
		}
		//����
}
