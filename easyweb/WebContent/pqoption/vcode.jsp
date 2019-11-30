<%@ page import="com.yc.easyweb.util.VerifyCodeUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 随机生成验证码
	String verifyCode = VerifyCodeUtils.outputImage(response);
	// 将验证码添加到会话中，注意：在会话中保存的验证码的名称 vscode
	request.getSession().setAttribute("vcode", verifyCode);
	/**
	getOutputStream() has already been called for this response
	在JSP中输出图片，控制台会抛出该异常，必须添加以下代码，解决错误
	*/
	out.clear();
	out = pageContext.pushBody();
%>
