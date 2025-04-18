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
		Response response = restClient.get(RESTFULBOOKER_BOOKING_IDS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void deleteBookingTest() {		
		int bookingId = restClient.get(RESTFULBOOKER_BOOKING_IDS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY).jsonPath().getInt("[0].bookingid");
		
		Response response = restClient.delete(RESTFULBOOKER_BOOKING_IDS_ENDPOINT+"/"+bookingId, null, null, AuthType.BASIC_AUTH, ContentType.ANY);
		Assert.assertEquals(response.getStatusCode(), 201);
	}

}
