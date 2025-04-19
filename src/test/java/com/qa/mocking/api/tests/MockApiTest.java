package com.qa.mocking.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.MockApi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockApiTest extends BaseTest {
	
	@Test
	public void getDummyUserTest(){
		MockApi.getDummyUser();
		Response response = restClient.get("/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("name"), "api");
	}
	
	@Test
	public void getDummyUserWithJsonFileTest(){
		MockApi.getDummyUserWithJsonFile();
		Response response = restClient.get("/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("name"), "api");
	}
	
	@Test
	public void getDummyProductsWithJsonFileTest(){
		MockApi.getDummyProductsWithJsonFile();
		Response response = restClient.get("/api/products", null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void createDummyUserTest(){
		MockApi.createDummyUser();
		String dummyUser = "{\"name\": \"api\"}";
		Response response = restClient.post("/api/users", dummyUser, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 201 user is created");
	}
	
	@Test
	public void deleteDummyUSerTest(){
		MockApi.deleteDummyUSer();
		Response response = restClient.delete("/api/users/1", null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 204);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 204 USER DELETED");
		Assert.assertEquals(response.header("server"), "ApiServer");
	}

}
