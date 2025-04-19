package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AppConstants;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UsersSchemaTest extends BaseTest {
	
	@Test
	public void usersSchemaTest() {
		Response response = restClient.get(GOREST_USERS_ALL_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(AppConstants.GOREST_USERS_SCHEMA));
	}
	
	@Test
	public void singleUserSchemaTest() {
		//POST- Creating new user
		User user = new User("testname", StringUtils.getRandomEmailId(), "male", "active");
		Response response = restClient.post(GOREST_USERS_ALL_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 201);
		int userId = response.jsonPath().getInt("id");
		
		Response responseGet = restClient.get(GOREST_USERS_ALL_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		responseGet.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(AppConstants.GOREST_USER_SCHEMA));
	}

}
