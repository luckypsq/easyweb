package com.yc.easyweb.util;

import java.util.UUID;

public class Test {
	
	//≤È—Ø
	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		System.out.println(uuid);
		System.out.println(uuid.length());
	}
}
