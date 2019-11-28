package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Usercontrol;
import com.yc.easyweb.dao.UsercontrolDao;


/**
 * ����UsercontrolBiz���������
 * @author psq
 *
 */
public class UsercontrolBiz {

	private UsercontrolDao dao = new UsercontrolDao();
	
	//��ѯ����
		public List<Usercontrol> selectAll(Usercontrol usercontrol) throws Exception{
			return dao.selectAll(usercontrol);
			
		}
		//��ѯ����
				public Usercontrol selectSingle(Usercontrol usercontrol) throws Exception{
					return dao.selectSingle(usercontrol);
				}
		//���
		public int insert(Usercontrol usercontrol) throws Exception{
			return dao.insert(usercontrol);
			
		}
		//ɾ��
		public int delete(Usercontrol usercontrol) throws Exception{
			return dao.delete(usercontrol);
			
		}
		//ɾ������
		public int deleteAll(List<Usercontrol> list) throws Exception{
			return dao.delete(list);
			
		}
		//����
		public  int update (Usercontrol usercontrolNew, Usercontrol usercontrolOld) throws Exception {
			return dao.update(usercontrolNew, usercontrolOld);
			
		}
		//����
}
