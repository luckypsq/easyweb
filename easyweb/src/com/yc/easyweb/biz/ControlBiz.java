package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Control;
import com.yc.easyweb.dao.ControlDao;

/**
 * ����Control���������
 * @author psq
 *
 */
public class ControlBiz {

	private ControlDao dao = new ControlDao();
	
	//��ѯ����
		public List<Control> selectAll(Control control) throws Exception{
			return dao.selectAll(control);
			
		}
		public Control selectSingle(Control control) throws Exception{
			return dao.selectSingle(control);
		}
		
		//���
		public int insert(Control control) throws Exception{
			return dao.insert(control);
			
		}
		//ɾ��
		public int delete(Control control) throws Exception{
			return dao.delete(control);
			
		}
		//ɾ��
				public int deleteAll(List<Control> list) throws Exception{
					return dao.delete(list);
					
				}
		//����
		public  int update (Control controlNew,Control controlOld) throws Exception {
			return dao.update(controlNew, controlOld);
		}
		//����
}
