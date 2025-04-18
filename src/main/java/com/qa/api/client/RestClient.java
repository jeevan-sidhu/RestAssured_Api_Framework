package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.Map;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameworkException;
import com.qa.api.manager.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
	private String baseUrl = ConfigReader.get("baseUrl");

	private RequestSpecification setRequest(AuthType authType, ContentType contentType) {
		RequestSpecification request = RestAssured.given().log().all()
										.baseUri(baseUrl)
										.contentType(contentType)
										.accept(contentType);
		switch (authType) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer "+ConfigReader.get("bearerToken"));
			break;
		case CONTACTS_BEARER_TOKEN:
			request.header("Authorization", "Bearer "+ConfigReader.get("contactsBearerToken"));
			break;
		case BASIC_AUTH:
			request.header("Authorization", "Basic "+generateBasicAuthToken());
			break;
		case OAUTH2:
			request.header("Authorization", "Bearer "+generateOAuth2Token());
			break;
		case API_KEY:
			request.header("api_key", ConfigReader.get("apiKey"));
			break;
		case NO_AUTH:
			System.out.println("Auth is not required");
			break;
		default:
			System.out.println("Authorization type not supported. Please pass the right AuthType");
			throw new FrameworkException("AUTH TYPE NOT SUPPORTED");
		}
		return request;
	}
	
	public String generateBasicAuthToken() {
		String credentials = ConfigReader.get("basicUsername")+":"+ConfigReader.get("basicPassword");
		return Base64.getEncoder().encodeToString(credentials.getBytes());
	}
	
	public String generateOAuth2Token() {
		return RestAssured.given()
			.contentType(ContentType.URLENC)
			.formParam("grant_type", ConfigReader.get("grantType"))
			.formParam("client_id", ConfigReader.get("clientId"))
			.formParam("client_secret", ConfigReader.get("clientSecret"))
			.post(ConfigReader.get("tokenUrl"))
			.then()
			.extract()
			.path("access_token");
	}
	
	private void addParams(RequestSpecification request, Map<String, String> queryParams, Map<String, String> pathParams) {
		if(queryParams!=null) {
			request.queryParams(queryParams);
		}
		if(pathParams!=null) {
			request.pathParams(pathParams);
		}
	}
	
	public Response get(String endPoint, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
		RequestSpecification request = setRequest(authType, contentType);
		addParams(request, queryParams, pathParams);
		Response response = request.get(endPoint).then().extract().response();
		response.prettyPrint();
		return response;
	}
	
	public <T> Response post(String endPoint, T body, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
		RequestSpecification request = setRequest(authType, contentType);
		addParams(request, queryParams, pathParams);
		Response response = request.body(body).post(endPoint).then().extract().response();
		response.prettyPrint();
		return response;
	}
	
	public Response post(String endPoint, File body, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
		RequestSpecification request = setRequest(authType, contentType);
		addParams(request, queryParams, pathParams);
		Response response = request.body(body).post(endPoint).then().extract().response();
		response.prettyPrint();
		return response;
	}
	
	public <T>Response put(String endPoint, T body, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
		RequestSpecification request = setRequest(authType, contentType);
		addParams(request, queryParams, pathParams);
		Response response = request.body(body).put(endPoint).then().extract().response();
		response.prettyPrint();
		return response;
	}
	
	public <T>Response patch(String endPoint, T body, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
		RequestSpecification request = setRequest(authType, contentType);
		addParams(request, queryParams, pathParams);
		Response response = request.body(body).patch(endPoint).then().extract().response();
		response.prettyPrint();
		return response;
	}
	
	public Response delete(String endPoint, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
		RequestSpecification request = setRequest(authType, contentType);
		addParams(request, queryParams, pathParams);
		Response response = request.delete(endPoint).then().extract().response();
		response.prettyPrint();
		return response;
	}

}
