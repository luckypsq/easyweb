package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.dao.EorderDao;

/**
 * ����Eorder���������
 * @author psq
 *
 */
public class EorderBiz {

	private EorderDao dao = new EorderDao();
	
	//��ѯ����
		public List<Eorder> selectAll(Eorder eorder) throws Exception{
			return dao.selectAll(eorder);
			
		}
		//���
		public int insert(Eorder eorder){
			return 0;
			
		}
		//ɾ��
		public int delete(Eorder eorder){
			return 0;
			
		}
		//����
		public  int update (Eorder eorder) {
			return 0;
			
		}
		//����
}
