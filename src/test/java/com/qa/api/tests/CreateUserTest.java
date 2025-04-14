package com.qa.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {
	
	@Test
	public void createUserTest() {
		//POST
		User user = new User("Jeevan", "abc124fw@gmail.com", "male", "active");
		Response response = restClient.post("/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 201);
		String userId = response.jsonPath().getString("id");
		
		//GET
		Response responseGet = restClient.get("/public/v2/users/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.statusCode(), 200);
		Assert.assertEquals(responseGet.jsonPath().getString("id"), userId);
		Assert.assertEquals(responseGet.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responseGet.jsonPath().getString("email"), user.getEmail());
		Assert.assertEquals(responseGet.jsonPath().getString("gender"), user.getGender());
		Assert.assertEquals(responseGet.jsonPath().getString("status"), user.getStatus());
	}
	
	@Test
	public void createUserUsingJsonFileTest() {
		File user = new File("./src/test/resources/jsons/user.json");
		Response response = restClient.post("/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 201);
	}

}
