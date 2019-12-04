package com.yc.easyweb.biz;


import com.yc.easyweb.bean.User;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.dao.lyw.PasswordDao;

public class PasswordBiz {
	private PasswordDao dao= new  PasswordDao();
	public void repassword(User user,String oldpassword,String newpassword ,String repassword) throws Exception{
		if(user.getUpassword()==null || user.getUpassword().trim().isEmpty()){
			throw new BizException("������ԭ���룡");
		}
		if(user.getUpassword().equals(oldpassword)==false){
			throw new BizException("ԭ������� ");
		}
		
		if(newpassword.equals(repassword)==false){
			throw new BizException("������������벻һ�£�");
		}
		dao.update(user);
		
	}

}