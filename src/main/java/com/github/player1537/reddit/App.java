package com.github.player1537.reddit;

import java.io.InputStream;
import java.io.IOException;

import java.util.Properties;

public class App {
	public static void main(String... args) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream
			= App.class.getResourceAsStream("/application.properties");
		properties.load(inputStream);

		properties.list(System.out);
	}
}
