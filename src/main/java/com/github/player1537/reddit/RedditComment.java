package com.github.player1537.reddit;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;

import java.util.Map;

public class RedditComment {
	private final Map<String, Object> data;

	public RedditComment(String rawJsonData) throws IOException,
	                                                JsonParseException,
	                                                JsonMappingException {
		final ObjectMapper mapper = new ObjectMapper();
		final TypeFactory typeFactory = mapper.getTypeFactory();
		final MapType type = typeFactory.constructMapType(Map.class,
		                                                  String.class,
		                                                  Object.class);

		data = mapper.readValue(rawJsonData, type);
	}

	public Map<String, Object> getData() {
		return data;
	}
}
