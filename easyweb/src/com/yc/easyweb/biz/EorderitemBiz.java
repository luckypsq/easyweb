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
		//添加
		public int insert(Eorderitem eorderitem){
			return 0;
			
		}
		//删除
		public int delete(Eorderitem eorderitem){
			return 0;
			
		}
		//更新
		public  int update (Eorderitem eorderitem) {
			return 0;
			
		}
		//其他
}
