package com.yc.easyweb.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Test {
	
	//≤È—Ø
	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		String tString = null;
		Map<String ,String> map  =new HashMap<String, String>();
		map.put("str", tString);
		System.out.println(map.get("str"));
		System.out.println(map.get("str33"));
	}
}
