package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.OrderDetial;
import com.yc.easyweb.bean.Page;
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
	public List<Eorder> selectAll(Eorder eorder) throws IOException  {
		return dao.selectAll(eorder);
	}

	// 查询单个
	public Eorder selectSingle(Eorder eorder) throws IOException, BizException  {
		if(eorder == null){
			throw new BizException("请输入订单信息");
		}
		return dao.selectSingle(eorder);
	}

	// 查询订单详情
	public List<OrderDetial> selectDetail(OrderDetial detial) throws IOException, BizException  {
		if(detial == null){
			throw new BizException("请输入订单详情信息");
		}
		return dao.selectAllDetail(detial);
	}

	// 添加
	public int insert(Eorder eorder) throws SQLException, BizException  {
		if(eorder == null){
			throw new BizException("请输入订单信息");
		}
		return dao.insert(eorder);

	}

	// 删除
	public int delete(Eorder eorder) throws SQLException, BizException  {
		if(eorder == null){
			throw new BizException("请输入订单信息");
		}
		return dao.delete(eorder);

	}

	// 删除多个
	public int delete(List<Eorder> list) throws SQLException, BizException  {
		if(list.size() == 0){
			throw new BizException("请输入订单信息");
		}
		return dao.delete(list);

	}

	// 更新
	public int update(Eorder eorderNew, Eorder eorderOld) throws SQLException, BizException  {
		if(eorderNew == null){
			throw new BizException("请输入修改的订单信息");
		}
		if(eorderOld == null){
			throw new BizException("请输入需要修改的订单");
		}
		return dao.update(eorderNew, eorderOld);
	}
	//分页
	public Page<Eorder> eorderPage(int page, int rows,Eorder eorder) throws IOException {
		return dao.eorderPage(page, rows, eorder);
	}
	// 其他
}
