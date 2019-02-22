package com.vumigroup.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vumigroup.Routes;
import com.vumigroup.models.IDField;

public class RESTClient {

	static HttpURLConnection connection(String method, Routes route, String a) throws IOException {
		HttpURLConnection conn = null;
		URL url = new URL(route.getRoute(a));
		System.out.println(url);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-type", "application/json; charset=UTF-8");
		return conn;
	}

	static String processResponse(InputStream in) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader((in)))) {
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
		}
		return sb.toString();
	}

	public static String get(Routes route, String a) throws IOException {
		String response = null;
		HttpURLConnection conn = null;
		try {
			conn = connection("GET", route, a);
			int responseCode = conn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
//				TODO log
			}
			response = processResponse(conn.getInputStream());
		} finally {
			conn.disconnect();
		}
		return response;
	}

	static <T> void processRequest(HttpURLConnection conn, T data, Class<T> clazz)
			throws JsonProcessingException, IOException {
		JSONUtil<T> json = new JSONUtil<T>(clazz);
		try (OutputStream os = conn.getOutputStream()) {
			os.write(json.fromObject(data));
			os.flush();
		}
	}

	public static <T> String post(Routes route, T data, Class<T> clazz) throws IOException {
		String response = null;
		HttpURLConnection conn = null;
		try {
			conn = connection("POST", route, null);
			conn.setDoOutput(true);
			processRequest(conn, data, clazz);
			int responseCode = conn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_CREATED) {
//				TODO log
			}
			response = processResponse(conn.getInputStream());
		} finally {
			conn.disconnect();
		}
		return response;
	}

	static <T> Object getID(T data, Class<T> clazz) throws IllegalArgumentException, IllegalAccessException {
		for (Field f : clazz.getDeclaredFields()) {
			System.out.println(f.getName());
			IDField idf = f.getAnnotation(IDField.class);
			if (idf != null) {
				f.setAccessible(true);
				return f.get(data);
			}
		}
		return null;
	}

	public static <T> String put(Routes route, T data, Class<T> clazz)
			throws IOException, IllegalArgumentException, IllegalAccessException {
		String response = null;
		HttpURLConnection conn = null;
		try {
			Object id = getID(data, clazz);
			conn = connection("PUT", route, id == null ? null : id.toString());
			conn.setDoOutput(true);
			processRequest(conn, data, clazz);
			int responseCode = conn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
//				TODO log
			}
			response = processResponse(conn.getInputStream());
		} finally {
			conn.disconnect();
		}
		return response;
	}

}
