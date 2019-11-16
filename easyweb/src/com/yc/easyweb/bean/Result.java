package com.yc.easyweb.bean;

public class Result {
	
	// ���ص���Ϣ
	private String msg;
	// ���صĽ���� 0 ʧ��  1 �ɹ�
	private int code;
	// ���ص�����
	private Object data;
	
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
	
	/**
	 * Ĭ�ϳɹ��Ľ������
	 * @param msg
	 * @return
	 */
	public static Result failure(String msg){
		Result result = new Result();
		result.code = 0;
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
