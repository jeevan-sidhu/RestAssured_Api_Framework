package com.qa.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.exceptions.FrameworkException;

import io.restassured.response.Response;

public class JsonUtils {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static <T> T deserialize(Response response, Class<T> targetClass) {
			try {
				return objectMapper.readValue(response.getBody().asString(), targetClass);
			} catch (JsonProcessingException e) {
				throw new FrameworkException("De-serialization is failed...");
			}
		 
	}

}
