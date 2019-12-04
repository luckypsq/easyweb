package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.Eorderitem;
import com.yc.easyweb.dao.EorderitemDao;

/**
 * 操作Eorderritem表的事务类
 * 
 * @author psq
 *
 */
public class EorderitemBiz {

	private EorderitemDao dao = new EorderitemDao();

	// 查询所有
	public List<Eorderitem> selectAll(Eorderitem eorderitem) throws IOException {
		return dao.selectAll(eorderitem);

	}
	//查询单个
	public Eorderitem selectSingle(Eorderitem eorderitem) throws IOException, BizException {
		if(eorderitem == null){
			throw new BizException("请填写购物车信息");
		}
		return dao.selectSingle(eorderitem);
	}

	// 添加
	public int insert(Eorderitem eorderitem) throws SQLException, BizException {
		if(eorderitem == null){
			throw new BizException("请填写购物车信息");
		}
		return dao.insert(eorderitem);
	}

	// 删除
	public int delete(Eorderitem eorderitem) throws SQLException, BizException {
		if(eorderitem == null){
			throw new BizException("请填写购物车信息");
		}
		return dao.delete(eorderitem);

	}

	// 删除
	public int delete(List<Eorderitem> list) throws SQLException, BizException {
		if(list.size() == 0){
			throw new BizException("请填写购物车信息");
		}
		return dao.delete(list);
	}

	// 更新
	public int update(Eorderitem eoNew, Eorderitem eoOld) throws SQLException, BizException {
		if(eoNew == null){
			throw new BizException("请填写购物车信息");
		}
		if(eoOld == null){
			throw new BizException("请填写需要修改的购物车");
		}
		return dao.update(eoNew, eoOld);
	}
	// 其他
}
