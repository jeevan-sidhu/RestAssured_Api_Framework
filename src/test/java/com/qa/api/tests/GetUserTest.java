package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.JsonUtils;

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
	
	@Test(enabled = false)
	public void getUserTest() {
		Response response = restClient.get("/public/v2/users/7482843", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void getUsersWithDeserializationTest() {
		Response response = restClient.get("/public/v2/users", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		User user[] = JsonUtils.deserialize(response, User[].class);
		System.out.println("------------------------------------------");
		for (User usr : user) {
			System.out.println("ID: "+usr.getId());
			System.out.println("Name: "+usr.getName());
			System.out.println("Email: "+usr.getEmail());
			System.out.println("Gender: "+usr.getGender());
			System.out.println("Status: "+usr.getStatus());
			System.out.println("------------------------------------------");
		}
	}

}
