package com.yc.easyweb.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yc.easyweb.bean.Book;
import com.yc.easyweb.bean.BookType;
import com.yc.easyweb.bean.Result;
import com.yc.easyweb.bean.User;
import com.yc.easyweb.bean.Usercontrol;
import com.yc.easyweb.biz.BizException;
import com.yc.easyweb.biz.BookBiz;
import com.yc.easyweb.biz.BookTypeBiz;
import com.yc.easyweb.biz.UserBiz;
import com.yc.easyweb.biz.UsercontrolBiz;

public class JoinServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBiz();
	
	public void join(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		/*// 获取验证码
		String vcode01 = (String) session.getAttribute("vcode");
		String vcode02 = request.getParameter("vcode");
		if(vcode01 != null && !vcode01.isEmpty() && !vcode02.isEmpty() && vcode02 != null){
			if (!vcode01.equalsIgnoreCase(vcode02)) {
				out.print(-2);//验证码错误
				return;
			} 
		}
		*/
				
		// 接收 用户名 和 密码
		String username = request.getParameter("uname");
		String password = request.getParameter("upassword");
		String loginkeeping = request.getParameter("loginkeeping");//验证码
		session.setAttribute("loginUname", username);
		User user = new User();
		Result result;
		try {
			//添加用户名条件
			if(username != null && !username.isEmpty()){
				user.setUname(username);
			}else{
				result = Result.failure("用户名为空！！！", username);
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			//添加密码条件
			if(password != null && !password.isEmpty()){
				user.setUpassword(password);
			}else{
				result = Result.failure("密码为空！！！");
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			
		
			User userShow = userBiz.selectSingle(user);//保存用户信息
			if(userShow.getUid() == 0){
				//用户名不存在
				result =Result.failure("用户名不存在！！！", username);
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			if(userShow.getUstate() != 1){
				result = Result.failure("您已被冻结或账号被删除！！！", username);
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
				return ;
			}
			session.setAttribute("loginedUser", userShow);
			// 获取系统当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
			Date date = new Date();
			String[] dateStr = df.format(date).split("/");
			//将登录时间放入会话中
			session.setAttribute("date", dateStr);
			String path = this.getServletContext().getContextPath();
			//将项目名字放入会话中
			session.setAttribute("path",path);
			//将各种状态的类型放入会话中
			String[] userType = {"","","普通用户","普通会员","钻石会员"};
			String[] uType = {"","","2-普通用户","3-普通会员","4-钻石会员"};
			session.setAttribute("userType", userType);
			session.setAttribute("uType", uType);
			String[] userSex = {"保密","男","女"};
			session.setAttribute("userSex", userSex);
			String[] adminState = {"","已启用","已冻结","已删除"};
			session.setAttribute("adminStateC", adminState);// 存储所有存在的管理员信息
			String[] bookState = {"未上架","已上架","已下架","售罄","审核不通过","未审核"};
			session.setAttribute("bookState", bookState);
			String[] eorderState = {"","待付款","待发货","已发货","待处理","已退款","已接收","退货失败"};
			session.setAttribute("eoderState", eorderState);
			String[] eorderMessage = {"","等待支付","等待发货","等待揽件","等待处理","退款成功","买家已揽件","条件不符合"};
			session.setAttribute("eoderMessage", eorderMessage);
			
			BookBiz bookBiz = new BookBiz();
			Book book = new Book();
			List<Book> bookList_add = bookBiz.selectAll(book);
			HashSet<String> bookUniver = new HashSet<String>();
			HashSet<String> bookUcollage = new HashSet<String>();
			HashSet<String> bookUmagor = new HashSet<String>();
			for (Book bookSet : bookList_add) {
				if (null != bookSet.getBuniversity() && !bookSet.getBuniversity().isEmpty()) {
					bookUniver.add(bookSet.getBuniversity());
				}
				if (null != bookSet.getBucollege() && !bookSet.getBucollege().isEmpty()) {
					bookUcollage.add(bookSet.getBucollege());
				}
				if (null != bookSet.getBumajor() && !bookSet.getBumajor().isEmpty()) {
					bookUmagor.add(bookSet.getBumajor());
				}
			}
			session.setAttribute("userUni", bookUniver);
			session.setAttribute("userUcol", bookUcollage);
			session.setAttribute("userUmar", bookUmagor);
			
			BookType bookType = new BookType();
			bookType.setBtstate(1);
			BookTypeBiz btBiz = new BookTypeBiz();
			
			List<BookType> btList = btBiz.selectAll(bookType);
			HashSet<String> btType = new HashSet<String>();
			for(BookType bt : btList){
				if(bt.getBtnamethird() != null && !bt.getBtnamethird() .isEmpty()){
					btType.add(bt.getBtid()+"-"+bt.getBtnamethird());
				}else if(bt.getBtnamesecond() != null && !bt.getBtnamesecond().isEmpty()){
					btType.add(bt.getBtid()+"-"+bt.getBtnamesecond());
				}else{
					btType.add(bt.getBtid()+"-"+bt.getBtname());
				}
			}
			session.setAttribute("btTypeEdit", btType);
			
			
			String adminType = null;
			if(userShow.getUtype() == 1){
			 	adminType = "超级管理员";
			}else{
			 	adminType = "普通管理员";
			}
			request.getSession().setAttribute("adminType", adminType);
			
			//保存登录
			/**	 
			  Cookie cookie = new Cookie("loginedUsername", URLEncoder.encode(username, "GBK")); // 默认为临时Cookie,MaxAge<0
				// 创建一个cookie对象
				Cookie cookie01 = new Cookie("loginedPassword", password);	// 解决Cookie存中文的乱码问题
				cookie.setMaxAge(60);// 设置有效时间 1分钟f
				cookie01.setMaxAge(60);
				response.addCookie(cookie);// 将cookie添加到响应对象中
				response.addCookie(cookie01);
				//从请求对象中获取浏览器发送回服务器的cookie数据
			     Cookie[] cookies=request.getCookies();
			     //Cookie loginedUserCookie=null;
			     Cookie loginedPasswordCookie=null;
			     if(cookies!=null){
			    	 for(Cookie cookie:cookies){
			    		 if(cookie.getName().equalsIgnoreCase("loginedUsername")) {
			    			 //解决读取中文乱码问题
			    			 request.setAttribute("username",URLDecoder.decode(cookie.getValue(),"GBK"));
			    		 }
			    		 if("loginedPassword".equals(cookie.getName())){
			    			 loginedPasswordCookie=cookie;
			    		 }
			     }
     			}
			*/
			
			if (userShow.getUtype() != 1 && userShow.getUtype() != 5) {
				result = Result.success("用户登录成功！！！", username);
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			} else {
				UsercontrolBiz ucBiz = new UsercontrolBiz();
				Usercontrol usercontrolOld = new Usercontrol();
				usercontrolOld.setUid(userShow.getUid());
				List<Usercontrol> controlList = ucBiz.selectAll(usercontrolOld);
				
				List<Long> conList = new ArrayList<Long>();
				if(controlList != null){
					for(Usercontrol ucon : controlList){
					 	conList.add(ucon.getConid());
					}
				}
				session.setAttribute("adminControl", conList);
				result = new Result("管理员登录成功！！！",2);
				String json = gson.toJson(result);
				// 返回json格式数据
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(json);
			}
		} catch (BizException e) {
			result = Result.error(e.getMessage());
			String json = gson.toJson(result);
			// 返回json格式数据
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
		} catch (IOException e) {
			result = Result.error("业务繁忙,请您稍等一会儿再操作！！！");
			String json = gson.toJson(result);
			// 返回json格式数据
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().append(json);
			} catch (IOException e1) {
				throw new RuntimeException(e);
			}
			e.printStackTrace();
		}
	}
}
