package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchUserTest extends BaseTest {
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"Albert", "male", "active", "Albert Einstein", "inactive"},
			{"Robert", "female", "inactive", "Robert De Niro ", "active"},
		};
	}	
	
	@Test(dataProvider = "getUserData")
	public void patchUserTest(String name, String gender, String status, String newName, String newStatus) {
		
		//POST- Creating new user
		User user = new User(name, StringUtils.getRandomEmailId(), gender, status);
		Response response = restClient.post(GOREST_USERS_ALL_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 201);
		int userId = response.jsonPath().getInt("id");
		
		//GET- Fetching the user
		Response responseGet = restClient.get(GOREST_USERS_ALL_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.statusCode(), 200);
		Assert.assertEquals(responseGet.jsonPath().getInt("id"), userId);
		
		user.setName(newName);
		user.setStatus(newStatus);
		
		//PATCH- Updating the user
		Response responsePut = restClient.patch(GOREST_USERS_ALL_ENDPOINT+"/"+userId, user ,null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePut.statusCode(), 200);
		
		//GET- re-checking the update by fetching same user
		Response responseGetAfterUpdate = restClient.get(GOREST_USERS_ALL_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGetAfterUpdate.statusCode(), 200);
		Assert.assertEquals(responseGetAfterUpdate.jsonPath().getInt("id"), userId);
		Assert.assertEquals(responseGetAfterUpdate.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responseGetAfterUpdate.jsonPath().getString("email"), user.getEmail());
		Assert.assertEquals(responseGetAfterUpdate.jsonPath().getString("gender"), user.getGender());
		Assert.assertEquals(responseGetAfterUpdate.jsonPath().getString("status"), user.getStatus());
		
	}

}
