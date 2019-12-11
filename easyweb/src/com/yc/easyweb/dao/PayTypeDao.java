package com.yc.easyweb.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.PayType;
import com.yc.easyweb.common.DbHelper;

/**
 * 操作PayType表的dao类
 * 
 * @author psq
 *
 */
public class PayTypeDao {
	// 查询所有
	public List<PayType> selectAll(PayType payType) throws IOException  {
		StringBuffer sb = new StringBuffer();
		sb.append(" select eopaytypeid,eopayname,eopaystate,eopaytemp from paytype where 1=1 ");
		if (payType != null) {
			if (payType.getEopaytypeid() != 0) {
				sb.append(" and eopaytypeid = " + payType.getEopaytypeid() );
			}
			if (payType.getEopayname() != null) {
				sb.append(" and eopayname like'%" + payType.getEopayname()+"%'" );
			}
		}
		sb.append(" order by  eopaytypeid desc");
		List<PayType> list = DbHelper.selectAll(sb.toString(), null, PayType.class);
		return list;
	}

	// 添加
	public int insert(PayType payType) throws SQLException  {
		String sql = "insert into paytype(eopaytypeid,eopayname,eostatetemp) " + " values(null,?,?);";
		return DbHelper.update(sql, payType.getEopayname(),payType.getEopaytemp());
	}
	// 删除
	public int delete(PayType payType) throws SQLException  {
		StringBuffer sb = new StringBuffer();
		if (payType == null) {
			return 0;
		}
		sb.append("delete from paytype where 1=1 ");
		if (payType != null) {
			if (payType.getEopaytypeid() != 0) {
				sb.append(" and eopaytypeid = " + payType.getEopaytypeid() );
			}
			if (payType.getEopayname() != null) {
				sb.append(" and eopayname ='" + payType.getEopayname()+"'" );
			}
		}
		return DbHelper.update(sb.toString(), null);

	}

	
}
