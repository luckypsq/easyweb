package com.yc.easyweb.bean;


public class Result{
	// ���ص���Ϣ
	private String msg;
	// ���صĽ���� 0 ʧ��  1 �ɹ�
	private int code;
	// ���ص�����
	private Object data;
	
	public Result() {
		super();
	}
	
	public Result(String msg, int code) {
		super();
		this.msg = msg;
		this.code = code;
	}


	public Result(String msg, int code, Object data) {
		super();
		this.msg = msg;
		this.code = code;
		this.data = data;
	}

	/**
	 * Ĭ�ϳɹ��Ľ������
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
	 * Ĭ��ʧ�ܵĽ������
	 * @param msg
	 * @return
	 */
	public static Result failure(String msg){
		Result result = new Result();
		result.code = 0;
		result.msg = msg;
		return result;
	}
	
	public static Result failure(String msg ,Object data){
		Result result = new Result();
		result.code = 0;
		result.msg = msg;
		result.data =data;
		return result;
	}
	/**
	 * Ĭ�ϴ���Ľ������
	 * @param msg
	 * @param data
	 * @return
	 */
	public static Result error(String msg,Object data){
		Result result = new Result();
		result.code = -1;
		result.msg = msg;
		result.data = data;
		return result;
	}
	public static Result error(String msg){
		Result result = new Result();
		result.code = -1;
		result.msg = msg;
		return result;
	}
	
	/**
	 * Ĭ����Ϣ����ȫ�Ľ������
	 * @return
	 */
	public static Result lack(String msg){
		Result result = new Result();
		result.code = -2;
		result.msg = msg;
		return result;
	}
	public static Result lack(String msg,Object data){
		Result result = new Result();
		result.code = -2;
		result.msg = msg;
		result.data = data;
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
