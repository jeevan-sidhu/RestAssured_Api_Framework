package com.qa.contacts.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AppConstants;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigReader;
import com.qa.api.pojo.Contact;
import com.qa.api.utils.ExcelUtil;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsApiTest extends BaseTest {

	@BeforeMethod
	public void getToken() {
		File credentials = new File(AppConstants.CONTACTS_CREDENTIALS_JSON_FILE_PATH);
		Response response = restClient.post(CONTACTS_USER_LOGIN_ENDPOINT, credentials, null, null, AuthType.NO_AUTH, ContentType.JSON);
		String token = response.jsonPath().getString("token");
		ConfigReader.set("contactsBearerToken", token);
	}

	@Test
	public void getContactListTest() {
		Response response = restClient.get(CONTACTS_ALL_ENDPOINT, null, null, AuthType.CONTACTS_BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Test
	public void getUserProfileTest() {
		Response response = restClient.get(CONTACTS_USER_ENDPOINT, null, null, AuthType.CONTACTS_BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}

	@DataProvider
	public Object[][] getContactsTestData() {
		return ExcelUtil.getTestData(AppConstants.CONTACTS_DATA_SHEET);
	}

	@Test(dataProvider = "getContactsTestData")
	public void CreateUserTest(String firstName, String lastName, String birthDate, String phone, String street1,
			String street2, String city, String province, String postalCode, String country) {
		
		Contact contact = new Contact(firstName, lastName, birthDate, StringUtils.getRandomEmailId(), phone, street1,
							street2, city, province, postalCode, country);
		
		Response response = restClient.post(CONTACTS_ALL_ENDPOINT, contact, null, null, AuthType.CONTACTS_BEARER_TOKEN,
				ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 201);
	}

}
