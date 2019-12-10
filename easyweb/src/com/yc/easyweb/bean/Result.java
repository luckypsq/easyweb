package com.yc.easyweb.bean;

import java.io.Serializable;

public class Result implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 返回的消息
	private String msg;
	// 返回的结果码 0 失败  1 成功
	private int code;
	// 返回的数据
	private Object data;
	
	public Result() {
		super();
	}
	
	public Result(String msg, int code, Object data) {
		super();
		this.msg = msg;
		this.code = code;
		this.data = data;
	}

	/**
	 * 默认成功的结果对象
	 * @param msg
	 * @return
	 */
	public static Result success(String msg){
		Result result = new Result();
		result.code = 1;
		result.msg = msg;
		return result;
	}
	public static Result success(String msg,Object data){
		Result result = new Result();
		result.code = 1;
		result.msg = msg;
		result.data = data;
		return result;
	}
	/**
	 * 默认失败的结果对象
	 * @param msg
	 * @return
	 */
	public static Result failure(String msg){
		Result result = new Result();
		result.code = 0;
		result.msg = msg;
		return result;
	}
	public static Result error(String msg){
		Result result = new Result();
		result.code = -1;
		result.msg = msg;
		return result;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
