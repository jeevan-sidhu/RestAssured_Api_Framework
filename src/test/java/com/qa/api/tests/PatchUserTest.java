package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchUserTest extends BaseTest {
	
	@Test
	public void patchUserTest() {
		
		//POST- Creating new user
		User user = new User("testname", StringUtils.getRandomEmailId(), "male", "active");
		Response response = restClient.post("/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 201);
		String userId = response.jsonPath().getString("id");
		
		//GET- Fetching the user
		Response responseGet = restClient.get("/public/v2/users/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.statusCode(), 200);
		Assert.assertEquals(responseGet.jsonPath().getString("id"), userId);
		
		user.setStatus("inactive");
		user.setGender("female");
		
		//PATCH- Updating the user
		Response responsePut = restClient.patch("/public/v2/users/"+userId, user ,null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePut.statusCode(), 200);
		
		//GET- re-checking the update by fetching same user
		Response responseGetAfterUpdate = restClient.get("/public/v2/users/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGetAfterUpdate.statusCode(), 200);
		Assert.assertEquals(responseGetAfterUpdate.jsonPath().getString("id"), userId);
		Assert.assertEquals(responseGetAfterUpdate.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responseGetAfterUpdate.jsonPath().getString("email"), user.getEmail());
		Assert.assertEquals(responseGetAfterUpdate.jsonPath().getString("gender"), user.getGender());
		Assert.assertEquals(responseGetAfterUpdate.jsonPath().getString("status"), user.getStatus());
		
	}

}
