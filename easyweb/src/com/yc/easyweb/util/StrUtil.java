package com.yc.easyweb.util;

public class StrUtil {

	/*
	 * 	�ж��ַ����Ƿ�Ϊ��
	 */
	public static boolean isEmpty(String str) {
		if(null == str || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	/*
	 * 	����ת��Ϊ�ַ���
	 */
	public static String objToString(Object obj) {
		if(obj == null) {
			return null;
		}
		return obj.toString();
	}
}
