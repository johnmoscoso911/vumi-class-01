package com.vumigroup.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil<T> {

	private Class<T> clazz;

	public JSONUtil(Class<T> c) {
		this.clazz = c;
	}

	public T toObject(String response) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(response, clazz);
	}

	@SuppressWarnings("unchecked")
	public List<T> toList(String response)
			throws ClassNotFoundException, JsonParseException, JsonMappingException, IOException {

		Class<?> obj = Class.forName(String.format("[L%s;", clazz.getTypeName()));

		ObjectMapper mapper = new ObjectMapper();
		T[] array = (T[]) mapper.readValue(response, obj);
		return Arrays.asList(array);
	}

	public byte[] fromObject(T obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsBytes(obj);
	}

}
