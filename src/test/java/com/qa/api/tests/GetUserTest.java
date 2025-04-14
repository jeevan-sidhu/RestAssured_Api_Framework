package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest {
	
	@Test
	public void getAllUsersTest() {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("name", "singh");
		queryParams.put("status", "active");
		
		Response response = restClient.get("/public/v2/users", queryParams, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void getUserTest() {
		Response response = restClient.get("/public/v2/users/7380158", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}

}
