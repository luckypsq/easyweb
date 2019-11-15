package com.yc.easyweb.dao;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.binding.SelectBinding.AsBoolean;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.util.DbHelper;

/**
 * 操作notice表的dao类
 * 
 * @author psq
 *
 */
public class BookDao {
	DbHelper db = new DbHelper();

	// 查询所有
	public List<Book> selectAll(Book book) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select bid,bname,buniversity,bucollege,bumajor,bclass, "
				+ " bcontent,bimg,bprice,bstate,btid,btemp,btemp1,bnum,bauthor,bdate" + " from book where 1=1 ");
		if (book != null) {
			if (book.getBname() != null) {
				sb.append(" and bname like '%" + book.getBname() + "%'");
			}
			if (book.getBuniversity() != null) {
				sb.append(" and buniversity like '%" + book.getBuniversity() + "%'");
			}

			if (book.getBucollege() != null) {
				sb.append(" and bucollege like '%" + book.getBucollege() + "%'");
			}

			if (book.getBumajor() != null) {
				sb.append(" and bumajor like '%" + book.getBumajor() + "%'");
			}

			if (book.getBclass() != null) {
				sb.append(" and bclass like '%" + book.getBclass() + "%'");
			}

			if (book.getBauthor() != null) {
				sb.append(" and bauthor like '%" + book.getBauthor() + "%'");
			}
			if (book.getBtid() != 0) {
				sb.append(" and btid = " + book.getBtid());
			}
			if (book.getBstate() != 0) {
				sb.append(" and bstate = " + book.getBstate());
			}
			if (book.getBtemp() != null) {
				sb.append(" and btemp like '%" + book.getBtemp() + "%'");
			}
			if (book.getBdate() != null) {
				sb.append(" and bdate like '%" + book.getBdate() + "%'");
			}
		}
		sb.append(" order by  bid asc");
		List<Book> list = db.selectAll(sb.toString(), null, Book.class);
		return list;
	}

	// 添加
	public int insert(Book book) throws Exception {
		String sql = "insert into book(bid,bname,buniversity,bucollege," + "bumajor,bclass,bcontent,bimg,bprice,btid,"
				+ "btemp,bnum,bauthor,bdate) " + " values(null,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		return DbHelper.update(sql, book.getBname(), book.getBuniversity(), book.getBucollege(), book.getBumajor(),
				book.getBclass(), book.getBcontent(), book.getBimg(), book.getBprice(), book.getBtid(), book.getBtemp(),
				book.getBnum(), book.getBauthor(), book.getBdate());
	}

	// 删除
	public int delete(Book book) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (book == null) {
			return 0;
		}
		sb.append("delete from book where 1=1 ");
		if (book.getBname() != null) {
			sb.append(" and bname =  '" + book.getBname() + "'");
		}
		if (book.getBuniversity() != null) {
			sb.append(" and buniversity =  '" + book.getBuniversity() + "'");
		}

		if (book.getBucollege() != null) {
			sb.append(" and bucollege =  '" + book.getBucollege() + "'");
		}

		if (book.getBumajor() != null) {
			sb.append(" and bumajor =  '" + book.getBumajor() + "'");
		}

		if (book.getBclass() != null) {
			sb.append(" and bclass =  '" + book.getBclass() + "'");
		}
		if (book.getBauthor() != null) {
			sb.append(" and bauthor =  '" + book.getBauthor() + "'");
		}
		if (book.getBtid() != 0) {
			sb.append(" and btid = " + book.getBtid());
		}
		if (book.getBstate() != 0) {
			sb.append(" and bstate = " + book.getBstate());
		}
		if (book.getBtemp() != null) {
			sb.append(" and btemp = '" + book.getBtemp() + "'");
		}
		if (book.getBdate() != null) {
			sb.append(" and bdate = '" + book.getBdate() + "'");
		}
		return db.update(sb.toString(), null);
	}

	// 删除多条数据
	public int delete(List<Book> list) throws Exception {
		StringBuffer sb = null;
		if (list.size() == 0) {
			return 0;
		}
		// 保存所有的数据库语句
		List<String> sqList = new ArrayList<String>();
		for (Book book : list) {
			sb = new StringBuffer();
			sb.append("delete from book where 1=1");
			if (book.getBname() != null) {
				sb.append(" and bname =  '" + book.getBname() + "'");
			}
			if (book.getBuniversity() != null) {
				sb.append(" and buniversity =  '" + book.getBuniversity() + "'");
			}

			if (book.getBucollege() != null) {
				sb.append(" and bucollege =  '" + book.getBucollege() + "'");
			}

			if (book.getBumajor() != null) {
				sb.append(" and bumajor =  '" + book.getBumajor() + "'");
			}

			if (book.getBclass() != null) {
				sb.append(" and bclass =  '" + book.getBclass() + "'");
			}
			if (book.getBauthor() != null) {
				sb.append(" and bauthor =  '" + book.getBauthor() + "'");
			}
			if (book.getBtid() != 0) {
				sb.append(" and btid = " + book.getBtid());
			}
			if (book.getBstate() != 0) {
				sb.append(" and bstate = " + book.getBstate());
			}
			if (book.getBtemp() != null) {
				sb.append(" and btemp = '" + book.getBtemp() + "'");
			}
			if (book.getBdate() != null) {
				sb.append(" and bdate = '" + book.getBdate() + "'");
			}
			sqList.add(sb.toString());
		}
		return db.update(sqList, null);
	}

	// 更新
	public int update(Book bookNew, Book bookOld) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (bookNew == null || bookOld == null) {
			return 0;
		}
		sb.append("update book set btemp1='' ");
		if (bookNew.getBname() != null) {
			sb.append(" ,bname = '" + bookNew.getBname() + "'");
		}
		if (bookNew.getBuniversity() != null) {
			sb.append(" ,buniversity =  '" + bookNew.getBuniversity() + "'");
		}
		if (bookNew.getBucollege() != null) {
			sb.append(" ,bucollege =  '" + bookNew.getBucollege() + "'");
		}

		if (bookNew.getBumajor() != null) {
			sb.append(" ,bumajor =  '" + bookNew.getBumajor() + "'");
		}
		if (bookNew.getBclass() != null) {
			sb.append(" ,bclass =  '" + bookNew.getBclass() + "'");
		}

		if (bookNew.getBauthor() != null) {
			sb.append(" ,bauthor =  '" + bookNew.getBauthor() + "'");
		}
		if (bookNew.getBtid() != 0) {
			sb.append(" ,btid = " + bookNew.getBtid());
		}
		if (bookNew.getBstate() != 0) {
			sb.append(" ,bstate = " + bookNew.getBstate());
		}
		if (bookNew.getBtemp() != null) {
			sb.append(" ,btemp = '" + bookNew.getBtemp() + "'");
		}
		if (bookNew.getBdate() != null) {
			sb.append(" ,bdate =  '" + bookNew.getBdate() + "'");
		}
		sb.append(" where 1=1 ");
		if (bookOld.getBname() != null) {
			sb.append(" and bname =  '" + bookOld.getBname() + "'");
		}
		if (bookOld.getBuniversity() != null) {
			sb.append(" and buniversity =  '" + bookOld.getBuniversity() + "'");
		}

		if (bookOld.getBucollege() != null) {
			sb.append(" and bucollege =  '" + bookOld.getBucollege() + "'");
		}

		if (bookOld.getBumajor() != null) {
			sb.append(" and bumajor =  '" + bookOld.getBumajor() + "'");
		}

		if (bookOld.getBclass() != null) {
			sb.append(" and bclass =  '" + bookOld.getBclass() + "'");
		}
		if (bookOld.getBauthor() != null) {
			sb.append(" and bauthor =  '" + bookOld.getBauthor() + "'");
		}
		if (bookOld.getBtid() != 0) {
			sb.append(" and btid = " + bookOld.getBtid());
		}
		if (bookOld.getBstate() != 0) {
			sb.append(" and bstate = " + bookOld.getBstate());
		}
		if (bookOld.getBtemp() != null) {
			sb.append(" and btemp = '" + bookOld.getBtemp() + "'");
		}
		if (bookOld.getBdate() != null) {
			sb.append(" and bdate = '" + bookOld.getBdate() + "'");
		}
		return db.update(sb.toString(), null);

	}
	//更新多条
	// 其他
}
