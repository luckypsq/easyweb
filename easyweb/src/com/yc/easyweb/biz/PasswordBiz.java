package com.yc.easyweb.biz;


import java.sql.SQLException;

import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.dao.PasswordDao;

public class PasswordBiz {
	private PasswordDao dao= new  PasswordDao();
	public void repassword(User user,String oldpassword,String newpassword ,String repassword) throws BizException, SQLException {
		if(user.getUpassword()==null || user.getUpassword().trim().isEmpty()){
			throw new BizException("请输入原密码！");
		}
		if(user.getUpassword().equals(oldpassword)==false){
			throw new BizException("原密码错误 ");
		}
		
		if(newpassword.equals(repassword)==false){
			throw new BizException("两次输入的密码不一致！");
		}
		dao.update(user);
		
	}

}
