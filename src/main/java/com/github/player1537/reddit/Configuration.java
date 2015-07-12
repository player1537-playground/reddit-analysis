package com.github.player1537.reddit;

import java.io.InputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.Set;

public class Configuration {
	private static Configuration configuration = null;
	private static final String DEFAULT_FILENAME = "/application.properties";

	private Properties properties;

	public static Configuration getInstance() throws IOException {
		if (configuration == null) {
			configuration = new Configuration();
		}

		return configuration;
	}

	private Configuration() throws IOException {
		InputStream inputStream
			= this.getClass().getResourceAsStream(DEFAULT_FILENAME);

		properties = new Properties();
		properties.load(inputStream);
	}

	public String getProperty(String propName) {
		return properties.getProperty(propName);
	}

	public Set<String> stringPropertyNames() {
		return properties.stringPropertyNames();
	}
}
