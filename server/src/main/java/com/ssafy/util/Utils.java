package com.ssafy.util;

public class Utils {
	public static String numberling(String aid) {
		//1. aid를 숫자로 바꾸고
		//2. 1 증가 시키고
		//3. 0으로 붙여준다.
		int temp = Integer.parseInt(aid);
		temp++;
		aid = String.format("%010d", temp);
		return aid;
	}
}
