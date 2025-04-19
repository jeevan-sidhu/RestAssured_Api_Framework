package com.qa.api.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.api.constants.AppConstants;

public class ConfigReader {

	private static Properties prop;

	public static void loadConfigProperties() {
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(AppConstants.CONFIG_PROPERTIES_FILE_PATH);
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static String get(String key) {
		return prop.getProperty(key);
	}
	
	public static void set(String key, String value) {
		prop.setProperty(key, value);
	}
}
