package com.vumigroup.services;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil<T> {

	private Class<T> clazz;

	public JSONUtil(Class<T> c) {
		this.clazz = c;
	}

	public T convert(String response) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(response, clazz);
	}

}
