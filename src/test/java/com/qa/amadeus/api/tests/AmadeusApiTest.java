package com.qa.amadeus.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AmadeusApiTest extends BaseTest {
	
	@Test
	public void getFlightInfoTest() {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("origin", "PAR");
		queryMap.put("maxPrice", "200");
		Response response = restClient.get(AMADEUS_FLIGHTS_ENDPOINT, queryMap, null, AuthType.OAUTH2, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
