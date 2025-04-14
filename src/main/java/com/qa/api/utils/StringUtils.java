package com.qa.api.utils;

public class StringUtils {
	
	public static String getRandomEmailId() {
		return "api_"+System.currentTimeMillis()+"@test.com";
	}

}
