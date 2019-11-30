package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.dao.EorderitemDao;

/**
 * 操作Eorderritem表的事务类
 * @author psq
 *
 */
public class EorderitemBiz {

	private EorderitemDao dao = new EorderitemDao();
	
	//查询所有
		public List<Eorderitem> selectAll(Eorderitem eorderitem) throws Exception{
			return dao.selectAll(eorderitem);
			
		}
		public Eorderitem selectSingle(Eorderitem eorderitem) throws Exception{
			return dao.selectSingle(eorderitem);
		}
		//添加
		public int insert(Eorderitem eorderitem) throws Exception{
			return dao.insert(eorderitem);
		}
		//删除
		public int delete(Eorderitem eorderitem) throws Exception{
			return dao.delete(eorderitem);
			
		}
		//删除
				public int delete(List<Eorderitem> list) throws Exception{
					return dao.delete(list);
				}
		//更新
		public  int update (Eorderitem eoNew, Eorderitem eoOld) throws Exception {
			return dao.update(eoNew, eoOld);
		}
		//其他
}
