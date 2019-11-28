package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Usercontrol;
import com.yc.easyweb.dao.UsercontrolDao;


/**
 * 操作UsercontrolBiz表的事务类
 * @author psq
 *
 */
public class UsercontrolBiz {

	private UsercontrolDao dao = new UsercontrolDao();
	
	//查询所有
		public List<Usercontrol> selectAll(Usercontrol usercontrol) throws Exception{
			return dao.selectAll(usercontrol);
			
		}
		//查询单个
				public Usercontrol selectSingle(Usercontrol usercontrol) throws Exception{
					return dao.selectSingle(usercontrol);
				}
		//添加
		public int insert(Usercontrol usercontrol) throws Exception{
			return dao.insert(usercontrol);
			
		}
		//删除
		public int delete(Usercontrol usercontrol) throws Exception{
			return dao.delete(usercontrol);
			
		}
		//删除多条
		public int deleteAll(List<Usercontrol> list) throws Exception{
			return dao.delete(list);
			
		}
		//更新
		public  int update (Usercontrol usercontrolNew, Usercontrol usercontrolOld) throws Exception {
			return dao.update(usercontrolNew, usercontrolOld);
			
		}
		//其他
}
