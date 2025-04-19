package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigReader;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	protected static final String GOREST_USERS_ALL_ENDPOINT = "/public/v2/users";
	protected static final String RESTFULBOOKER_BOOKING_IDS_ENDPOINT = "/booking";
	protected static final String REQ_RES_ALL_USERS_ENDPOINT = "/api/users";
	protected static final String FAKESTORE_PRODUCTS_ALL_ENDPOINT = "/products";
	protected static final String CONTACTS_USER_LOGIN_ENDPOINT = "/users/login";
	protected static final String CONTACTS_ALL_ENDPOINT = "/contacts";
	protected static final String CONTACTS_USER_ENDPOINT = "/users/me";
	protected static final String AMADEUS_FLIGHTS_ENDPOINT = "/v1/shopping/flight-destinations";
	
	protected RestClient restClient;
	
	@BeforeSuite
	public void reportConfig() {
		RestAssured.filters(new AllureRestAssured());
		ConfigReader.loadConfigProperties();
	}
	
	@Parameters({ "baseUrl" })
	@BeforeTest
	public void setUp(@Optional String baseUrl) {
		if (baseUrl != null) {
			ConfigReader.set("baseUrl", baseUrl);
		}
		restClient = new RestClient();
	}

}
