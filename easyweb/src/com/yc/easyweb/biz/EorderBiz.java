package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.OrderDetial;
import com.yc.easyweb.dao.EorderDao;

/**
 * 操作Eorder表的事务类
 * 
 * @author psq
 *
 */
public class EorderBiz {

	private EorderDao dao = new EorderDao();

	// 查询所有
	public List<Eorder> selectAll(Eorder eorder) throws Exception {
		return dao.selectAll(eorder);

	}

	// 查询单个
	public Eorder selectSingle(Eorder eorder) throws Exception {
		return dao.selectSingle(eorder);
	}

	// 查询订单详情
	public List<OrderDetial> selectDetail(OrderDetial detial) throws Exception {
		return dao.selectAllDetail(detial);
	}

	// 添加
	public int insert(Eorder eorder) throws Exception {
		return dao.insert(eorder);

	}

	// 删除
	public int delete(Eorder eorder) throws Exception {
		return dao.delete(eorder);

	}

	// 删除多个
	public int delete(List<Eorder> list) throws Exception {
		return dao.delete(list);

	}

	// 更新
	public int update(Eorder eorderNew, Eorder eorderOld) throws Exception {
		return dao.update(eorderNew, eorderOld);
	}
	// 其他
}
