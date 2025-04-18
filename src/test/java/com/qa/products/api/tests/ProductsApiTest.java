package com.qa.products.api.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductsApiTest extends BaseTest {
	
	@Test
	public void getAllProductsTest() {
		Response response = restClient.get(FAKESTORE_PRODUCTS_ALL_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void getProductsTest() {
		Response response = restClient.get(FAKESTORE_PRODUCTS_ALL_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		
		Double minPrice = JsonPathValidator.read(response, "min($[*].price)");
		Double maxPrice = JsonPathValidator.read(response, "max($[*].price)");
		System.out.println("Minimum Price: "+minPrice);
		System.out.println("Maximum Price: "+maxPrice);
		
		List<Number> ids = JsonPathValidator.readList(response, "$[?(@.price<20)].id");
		List<Number> title = JsonPathValidator.readList(response, "$[?(@.price<20)].title");
		List<Number> price = JsonPathValidator.readList(response, "$[?(@.price<20)].price");		
		List<Number> rates = JsonPathValidator.readList(response, "$[?(@.price<20)].rating.rate");		
		List<Number> counts = JsonPathValidator.readList(response, "$[?(@.price<20)].rating.count");
		
		System.out.println("*** Products with Price Less than 20 ***");
		System.out.println("-----------------------------------------------");
		for (int i=0; i<ids.size(); i++) {
			System.out.println("ID: "+ids.get(i));
			System.out.println("Title: "+title.get(i));
			System.out.println("Price: "+price.get(i));
			System.out.println("Rate: "+rates.get(i));
			System.out.println("Count: "+counts.get(i));
			System.out.println("-----------------------------------------------");
		}
		
		List<Map<String, Object>> jeweleryList = JsonPathValidator.readListOfMaps(response, "$[?(@.category == 'jewelery')].['id','title','price']");
		System.out.println("*** Jewelery Products ***");
		for(Map<String, Object> product : jeweleryList) {
			System.out.println("ID: "+product.get("id"));
			System.out.println("Title: "+product.get("title"));
			System.out.println("Price: "+product.get("price"));
			System.out.println("-----------------------------------------------");
		}	
		
	}

}
