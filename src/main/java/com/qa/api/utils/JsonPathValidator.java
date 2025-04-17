package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonPathValidator {
	
	private static String getJsonResponseAsString(Response response) {
		return response.getBody().asString();
	}
	
	public static <T> T read(Response response, String jsonPath) {
//		return JsonPath.read(getJsonResponseAsString(response), jsonPath);
		return JsonPath.parse(getJsonResponseAsString(response)).read(jsonPath);
	}
	
	public static <T> List<T> readList(Response response, String jsonPath) {
		return JsonPath.parse(getJsonResponseAsString(response)).read(jsonPath);
	}
	
	public static <T> List<Map<String, T>> readListOfMaps(Response response, String jsonPath) {
		return JsonPath.parse(getJsonResponseAsString(response)).read(jsonPath);
	}

}
