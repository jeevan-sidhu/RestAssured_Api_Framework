package com.qa.booking.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookingApiTest extends BaseTest {
	
	@Test
	public void getBookingIdsTest() {
		Response response = restClient.get("/booking", null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void deleteBookingTest() {		
		int bookingId = restClient.get("/booking", null, null, AuthType.NO_AUTH, ContentType.ANY).jsonPath().getInt("[0].bookingid");
		
		Response response = restClient.delete("/booking/"+bookingId, null, null, AuthType.BASIC_AUTH, ContentType.ANY);
		Assert.assertEquals(response.getStatusCode(), 201);
	}

}
