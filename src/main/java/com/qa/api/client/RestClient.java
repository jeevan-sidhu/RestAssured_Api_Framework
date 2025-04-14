package com.qa.api.client;

import java.io.File;
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
		case BASIC_AUTH:
			request.header("Authorization", "Basic ");
			break;
//		case BEARER_TOKEN:
//			request.header("Authorization", ConfigReader.get("bearerToken"));
//			break;
//		case BEARER_TOKEN:
//			request.header("Authorization", ConfigReader.get("bearerToken"));
//			break;
		case NO_AUTH:
			System.out.println("Auth is not required");
			break;
		default:
			System.out.println("Authorization type not supported. Please pass the right AuthType");
			throw new FrameworkException("AUTH TYPE NOT SUPPORTED");
		}
		return request;
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
	
	public <T>Response post(String endPoint, T body, Map<String, String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType) {
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
