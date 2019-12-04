package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.PayType;
import com.yc.easyweb.dao.PayTypeDao;

/**
 * 操作PayType表的事务类
 * @author psq
 *
 */
public class PayTypeBiz {

	private PayTypeDao dao= new PayTypeDao();
	
	//查询所有
		public List<PayType> selectAll(PayType payType) throws Exception{
			return dao.selectAll(payType);
		}
		//添加
		public int insert(PayType payType) throws Exception{
			return dao.insert(payType);
			
		}
		//删除
		public int delete(PayType payType){
			return 0;
			
		}
		//更新
		public  int update (PayType payType) {
			return 0;
			
		}
		//其他
}
