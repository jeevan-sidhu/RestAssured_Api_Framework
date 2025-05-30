package com.qa.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AppConstants;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {
	
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"Albert", "male", "active"},
			{"Robert", "female", "inactive"},
		};
	}
	
	@Test(dataProvider = "getUserData")
	public void createUserTest(String name, String gender, String status) {
		//POST- Creating new user
		User user = new User(name, StringUtils.getRandomEmailId(), gender, status);
		Response response = restClient.post(GOREST_USERS_ALL_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 201);
		int userId = response.jsonPath().getInt("id");
		
		//GET- Fetching the user
		Response responseGet = restClient.get(GOREST_USERS_ALL_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.statusCode(), 200);
		Assert.assertEquals(responseGet.jsonPath().getInt("id"), userId);
		Assert.assertEquals(responseGet.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responseGet.jsonPath().getString("email"), user.getEmail());
		Assert.assertEquals(responseGet.jsonPath().getString("gender"), user.getGender());
		Assert.assertEquals(responseGet.jsonPath().getString("status"), user.getStatus());
	}
	
	@Test(enabled=false)
	public void createUserUsingJsonFileTest() {
		
		//POST- Creating new user
		File user = new File(AppConstants.GOREST_USERS_JSON_FILE_PATH);
		Response response = restClient.post(GOREST_USERS_ALL_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 201);
		int userId = response.jsonPath().getInt("id");
		
		//GET- Fetching the user
		Response responseGet = restClient.get(GOREST_USERS_ALL_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.statusCode(), 200);
		Assert.assertEquals(responseGet.jsonPath().getInt("id"), userId);
	}

}
