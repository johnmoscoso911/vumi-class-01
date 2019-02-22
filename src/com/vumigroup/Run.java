package com.vumigroup;

import java.io.IOException;

import com.vumigroup.services.RESTClient;

public class Run {

	public static void main(String[] args) {
		//
		System.out.println("Hola VUMI");
		try {
			System.out.println(RESTClient.get(Routes.COMMENTS));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
