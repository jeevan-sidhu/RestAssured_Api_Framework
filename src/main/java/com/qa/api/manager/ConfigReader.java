package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop = new Properties();

	static {
		try {
			InputStream ip = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties");
			if (ip != null) {
				prop.load(ip);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return prop.getProperty(key);
	}
}
