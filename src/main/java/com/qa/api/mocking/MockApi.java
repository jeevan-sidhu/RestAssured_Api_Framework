package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;

public class MockApi {
	
	public static void getDummyUser() {
		//http://localhost:8081/api/users
		//*********************** Stubs for GET CALLs**************************
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBody("{\n"
								+ "    \"name\": \"api\"\n"
								+ "}")
						)
				);
				
	}
	
	
	public static void getDummyUserWithJsonFile() {
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("user.json")
						)
				);
				
	}
	
	
	public static void getDummyUserWithQueryParams() {
		stubFor(get(urlPathEqualTo("/api/users"))
				.withQueryParam("name", equalTo("api"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("user.json")
						)
				);
				
	}
		
	
	public static void getDummyProductsWithJsonFile() {
		stubFor(get(urlEqualTo("/api/products"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("product.json")
						)
				);
				
	}
	
	
	
	//*********************** Stubs for POST CALLs**************************

	public static void createDummyUser() {
		stubFor(post(urlEqualTo("/api/users"))
				.withHeader("Content-Type", equalTo("application/json"))
				.willReturn(aResponse()
					.withStatus(201)
					.withHeader("Content-Type", "application/json")
					.withStatusMessage("user is created")
					.withBody("{\"id\": 1,\"name\": \"api\"}")
						));
	}
	
	
	public static void createDummyUserWithJsonFile() {
		stubFor(post(urlEqualTo("/api/users"))
				.withHeader("Content-Type", equalTo("application/json"))
				.willReturn(aResponse()
					.withStatus(201)
					.withHeader("Content-Type", "application/json")
					.withStatusMessage("user is created")
					.withBodyFile("user.json")
						));
	}
	
	
	//*********************** Stubs for DELETE CALLs**************************

	public static void deleteDummyUSer() {
		
		stubFor(delete(urlEqualTo("/api/users/1"))
				.willReturn(aResponse()
						.withStatus(204)
						.withStatusMessage("USER DELETED")
						.withHeader("server", "NALServer")
						));
		
	}

}
