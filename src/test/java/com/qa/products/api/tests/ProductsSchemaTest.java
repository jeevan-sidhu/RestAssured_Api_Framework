package com.qa.products.api.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AppConstants;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ProductsSchemaTest extends BaseTest {
	
	@Test
	public void productsSchemaTest() {
		Response response = restClient.get(FAKESTORE_PRODUCTS_ALL_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.JSON);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(AppConstants.PRODUCTS_SCHEMA));
	}

}
