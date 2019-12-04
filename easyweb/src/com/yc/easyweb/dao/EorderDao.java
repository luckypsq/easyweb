package com.yc.easyweb.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.OrderDetial;
import com.yc.easyweb.common.DbHelper;

/**
 * 操作Eorder表的dao类
 * 
 * @author psq
 *
 */
public class EorderDao {
	DbHelper db = new DbHelper();

	// 查询所有
	@SuppressWarnings("static-access")
	public List<Eorder> selectAll(Eorder eorder) throws IOException  {
		StringBuffer sb = new StringBuffer();
		sb.append(" select eoid,uid,eostate,eotime,eotemp,uname,eoaddr,eotype,eoespress,eopaytypeid " + " from eorder where 1=1 ");
		if (eorder != null) {
			if (eorder.getUid() != 0) {
				sb.append(" and uid=" + eorder.getUid());
			}
			if (eorder.getEoid() != null) {
				sb.append(" and eoid='" + eorder.getEoid()+"'");
			}
			if (eorder.getEotime() != null) {
				sb.append(" and eotime like '%" + eorder.getEotime() + "%'");
			}
			if (eorder.getUname() != null) {
				sb.append(" and uname like '%" + eorder.getUname() + "%'");
			}
			if (eorder.getEotype() != null) {
				sb.append(" and eotype like '%" + eorder.getEotype() + "%'");
			}
			if (eorder.getEostate() != 0) {
				sb.append(" and eostate = " + eorder.getEostate());
			}
			if (eorder.getEoaddr() != null) {
				sb.append(" and eoaddr like '%" + eorder.getEoaddr() + "%'");
			}
			if (eorder.getEoespress() != null) {
				sb.append(" and eoespress like '%" + eorder.getEoespress() + "%'");
			}
			if (eorder.getEopaytypeid() != 0) {
				sb.append(" and eopaytypeid = " + eorder.getEopaytypeid());
			}
		}
		sb.append("  order by  eoid desc");
		List<Eorder> list = db.selectAll(sb.toString(), null, Eorder.class);
		return list;
	}
	// 查询所有订单详细信息
		@SuppressWarnings("static-access")
		public List<OrderDetial> selectAllDetail(OrderDetial  detial) throws IOException  {
			StringBuffer sb = new StringBuffer();
			sb.append(" select e.eoid,bname,u.uid,b.bid,total,eotime,eotype,eoaddr,uphone,e.uname,count,eostate,eoespress,eopayname,bimg "
					+ " from eorder e,book b,eorderitem eo,user u,paytype pay "
					+ " where  e.eoid = eo.eoid and b.bid = eo.bid  and pay.eopaytypeid = e.eopaytypeid "
					+ " and u.uid = e.uid and 1=1 ");
			if(detial != null){
				if(detial.getEoid() != null){
					sb.append(" and e.eoid = '" + detial.getEoid() + "'");
				}
				if(detial.getEotime() != null){
					sb.append(" and eotime = '" + detial.getEotime() + "'");
				}
				if(detial.getEostate() != 0){
					sb.append(" and eostate = " + detial.getEostate());
				}
				if(detial.getUid() != 0){
					sb.append(" and u.uid = " + detial.getUid());
				}
				if(detial.getBid() != 0){
					sb.append(" and b.bid = " + detial.getBid());
				}
			}
			sb.append("  order by  e.eoid desc");
			return db.selectAll(sb.toString(), null, OrderDetial.class);
		
		}
	// 查询单个
		@SuppressWarnings("static-access")
		public Eorder selectSingle(Eorder eorder) throws IOException  {
			StringBuffer sb = new StringBuffer();
			sb.append(" select eoid,uid,eostate,eotime,eotemp,uname,eoaddr,eotype " + " from eorder where 1=1 ");
			if (eorder != null) {
				if (eorder.getUid() != 0) {
					sb.append(" and uid=" + eorder.getUid());
				}
				if (eorder.getEoid() != null) {
					sb.append(" and eoid='" + eorder.getEoid()+"'");
				}
				if (eorder.getEotime() != null) {
					sb.append(" and eotime like '%" + eorder.getEotime() + "%'");
				}
				if (eorder.getUname() != null) {
					sb.append(" and uname like '%" + eorder.getUname() + "%'");
				}
				if (eorder.getEotype() != null) {
					sb.append(" and eotype like '%" + eorder.getEotype() + "%'");
				}
				if (eorder.getEostate() != 0) {
					sb.append(" and eostate = " + eorder.getEostate());
				}
				if (eorder.getEoaddr() != null) {
					sb.append(" and eoaddr like '%" + eorder.getEoaddr() + "%'");
				}
				if (eorder.getEoespress() != null) {
					sb.append(" and eoespress like '%" + eorder.getEoespress() + "%'");
				}
				if (eorder.getEopaytypeid() != 0) {
					sb.append(" and eopaytypeid = " + eorder.getEopaytypeid());
				}
			}
			sb.append("  order by  eoid desc");
			return db.selectSingle(sb.toString(), null, Eorder.class);
		}
	// 添加
	public int insert(Eorder eorder) throws SQLException  {
		String sql = "insert into eorder(eoid,uid,eotime,eotemp,uname,eoaddr,eotype,eoespress,eopaytypeid  ) "
				+ " values(?,?,?,?,?,?,?,?,?);";
		return DbHelper.update(sql, eorder.getEoid(), eorder.getUid(),  eorder.getEotime(),
				eorder.getEotemp(), eorder.getUname(), eorder.getEoaddr(), eorder.getEotype(),eorder.getEoespress(),eorder.getEopaytypeid());
	}

	// 删除
	public int delete(Eorder eorder) throws SQLException  {
		StringBuffer sb = new StringBuffer();
		if (eorder == null) {
			return 0;
		}
		sb.append("delete from eorder where 1=1 ");
		if (eorder.getUid() != 0) {
			sb.append(" and uid=" + eorder.getUid());
		}
		if (eorder.getEoid() != null) {
			sb.append(" and eoid='" + eorder.getEoid()+"'");
		}
		if (eorder.getEotime() != null) {
			sb.append(" and eotime = '" + eorder.getEotime() + "'");
		}
		if (eorder.getUname() != null) {
			sb.append(" and uname = '" + eorder.getUname() + "'");
		}
		if (eorder.getEotype() != null) {
			sb.append(" and eotype = '" + eorder.getEotype() + "'");
		}
		if (eorder.getEostate() != 0) {
			sb.append(" and eostate = " + eorder.getEostate());
		}
		if (eorder.getEoaddr() != null) {
			sb.append(" and eoaddr = '" + eorder.getEoaddr() + "'");
		}
		return DbHelper.update(sb.toString(), null);
	}

	// 删除多条
	public int delete(List<Eorder> list) throws SQLException  {
		StringBuffer sb = null;
		if (list.size() == 0) {
			return 0;
		}
		List<String> sqList = new ArrayList<String>();
		for (Eorder eorder : list) {
			sb = new StringBuffer();
			sb.append("delete from eorder where 1=1 ");
			if (eorder.getUid() != 0) {
				sb.append(" and uid=" + eorder.getUid());
			}
			if (eorder.getEoid() != null) {
				sb.append(" and eoid='" + eorder.getEoid()+"'");
			}
			if (eorder.getEotime() != null) {
				sb.append(" and eotime = '" + eorder.getEotime() + "'");
			}
			if (eorder.getUname() != null) {
				sb.append(" and uname = " + eorder.getUname() + "'");
			}
			if (eorder.getEotype() != null) {
				sb.append(" and eotype = '" + eorder.getEotype() + "'");
			}
			if (eorder.getEostate() != 0) {
				sb.append(" and eostate = " + eorder.getEostate());
			}
			if (eorder.getEoaddr() != null) {
				sb.append(" and eoaddr = '" + eorder.getEoaddr() + "'");
			}
			sqList.add(sb.toString());
		}
		return DbHelper.update(sqList);
	}

	// 更新
	public int update(Eorder eorderNew,Eorder eorderOld) throws SQLException  {
		StringBuffer sb = new StringBuffer();
		if (eorderNew== null ||  eorderOld== null) {
			return 0;
		}
		sb.append("update  eorder set eotemp='' ");
		if (eorderNew.getUid() != 0) {
			sb.append(" , uid=" + eorderNew.getUid());
		}
		if (eorderNew.getEotime() != null) {
			sb.append(" , eotime ='" + eorderNew.getEotime() + "'");
		}
		if (eorderNew.getUname() != null) {
			sb.append(" , uname ='" + eorderNew.getUname() + "'");
		}
		if (eorderNew.getEotype() != null) {
			sb.append(" , eotype ='" + eorderNew.getEotype() + "'");
		}
		if (eorderNew.getEostate() != 0) {
			sb.append(" , eostate = " + eorderNew.getEostate());
		}
		if (eorderNew.getEoaddr() != null) {
			sb.append(" , eoaddr ='" + eorderNew.getEoaddr() + "'");
		}
		if (eorderNew.getEotemp() != null) {
			sb.append(" , eotemp ='" + eorderNew.getEotemp() + "'");
		}
		if (eorderNew.getEoespress() != null) {
			sb.append(" , eoespress = '" + eorderNew.getEoespress() + "'");
		}
		if (eorderNew.getEopaytypeid() != 0) {
			sb.append(" , eopaytypeid = " + eorderNew.getEopaytypeid());
		}
		sb.append(" where 1=1 ");
		if (eorderOld.getUid() != 0) {
			sb.append(" and uid=" + eorderOld.getUid());
		}
		if (eorderOld.getEotime() != null) {
			sb.append(" and eotime ='" + eorderOld.getEotime() + "'");
		}
		if (eorderOld.getUname() != null) {
			sb.append(" and uname ='" + eorderOld.getUname() + "'");
		}
		if (eorderOld.getEotype() != null) {
			sb.append(" and eotype ='" + eorderOld.getEotype() + "'");
		}
		if (eorderOld.getEostate() != 0) {
			sb.append(" and eostate = " + eorderOld.getEostate());
		}
		if (eorderOld.getEoaddr() != null) {
			sb.append(" and eoaddr ='" + eorderOld.getEoaddr() + "'");
		}
		if (eorderOld.getEoid() != null) {
			sb.append(" and eoid ='" + eorderOld.getEoid() + "'");
		}
		return db.update(sb.toString(), null);
	}
	// 其他
}
