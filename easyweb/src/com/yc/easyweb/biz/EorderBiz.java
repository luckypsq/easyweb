package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.dao.EorderDao;

/**
 * 操作Eorder表的事务类
 * @author psq
 *
 */
public class EorderBiz {

	private EorderDao dao = new EorderDao();
	
	//查询所有
		public List<Eorder> selectAll(Eorder eorder) throws Exception{
			return dao.selectAll(eorder);
			
		}
		//添加
		public int insert(Eorder eorder){
			return 0;
			
		}
		//删除
		public int delete(Eorder eorder){
			return 0;
			
		}
		//更新
		public  int update (Eorder eorder) {
			return 0;
			
		}
		//其他
}
