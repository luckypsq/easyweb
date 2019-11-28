package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Control;
import com.yc.easyweb.dao.ControlDao;

/**
 * 操作Control表的事务类
 * @author psq
 *
 */
public class ControlBiz {

	private ControlDao dao = new ControlDao();
	
	//查询所有
		public List<Control> selectAll(Control control) throws Exception{
			return dao.selectAll(control);
			
		}
		public Control selectSingle(Control control) throws Exception{
			return dao.selectSingle(control);
		}
		
		//添加
		public int insert(Control control) throws Exception{
			return dao.insert(control);
			
		}
		//删除
		public int delete(Control control) throws Exception{
			return dao.delete(control);
			
		}
		//删除
				public int deleteAll(List<Control> list) throws Exception{
					return dao.delete(list);
					
				}
		//更新
		public  int update (Control controlNew,Control controlOld) throws Exception {
			return dao.update(controlNew, controlOld);
		}
		//其他
}
