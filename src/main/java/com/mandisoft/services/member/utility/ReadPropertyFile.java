package com.mandisoft.services.member.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {

	public Object readPropertyFile(String propertyFileName) {
		Properties prop = new Properties();
		try {

			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream stream = loader.getResourceAsStream(propertyFileName);

			prop.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
