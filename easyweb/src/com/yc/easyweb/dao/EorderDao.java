package com.yc.easyweb.dao;

import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.User;
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
	public List<Eorder> selectAll(Eorder eorder) throws Exception {
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
		}
		sb.append("  order by  eoid desc");
		List<Eorder> list = db.selectAll(sb.toString(), null, Eorder.class);
		return list;
	}

	// 添加
	public int insert(Eorder eorder) throws Exception {
		String sql = "insert into eorder(eoid,uid,eostate,eotime,eotemp,uname,eoaddr,eotype ) "
				+ " values(?,?,?,?,?,?,?,?);";
		return DbHelper.update(sql, eorder.getEoid(), eorder.getUid(), eorder.getEostate(), eorder.getEotime(),
				eorder.getEotemp(), eorder.getUname(), eorder.getEoaddr(), eorder.getEotype());
	}

	// 删除
	public int delete(Eorder eorder) throws Exception {
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
		return DbHelper.update(sb.toString(), null);
	}

	// 删除多条
	public int delete(List<Eorder> list) throws Exception {
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
			sqList.add(sb.toString());
		}
		return DbHelper.update(sqList);
	}

	// 更新
	public int update(Eorder eorderNew,Eorder eorderOld) throws Exception {
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
		return db.update(sb.toString(), null);
	}
	// 其他
}
