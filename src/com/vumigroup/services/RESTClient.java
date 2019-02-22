package com.vumigroup.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.vumigroup.Routes;

public class RESTClient {

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
			URL url = new URL(route.getRoute(a));
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
//				TODO log
//				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			response = processResponse(conn.getInputStream());
		} finally {
			conn.disconnect();
		}
		return response;
	}

}
