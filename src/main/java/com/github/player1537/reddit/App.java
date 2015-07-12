package com.github.player1537.reddit;

import java.io.IOException;

import java.util.Map;

public class App {
	public static void main(String... args) throws IOException {
		Configuration configuration = Configuration.getInstance();

		for (String key : configuration.stringPropertyNames()) {
			System.out.println(String.format("%s = %s",
			                                 key,
			                                 configuration.getProperty(key)));
		}

		BufferedReader reader = RedditInputStream.getReader(configuration);

		for (int i=0; i<10; ++i) {
			System.out.println(reader.getLine());
		}
	}
}
