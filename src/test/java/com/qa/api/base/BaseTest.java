package com.qa.api.base;

import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;

public class BaseTest {
	protected RestClient restClient;

	@BeforeTest
	public void setUp() {
		restClient = new RestClient();
	}

}
