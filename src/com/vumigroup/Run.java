package com.vumigroup;

import java.io.IOException;

import com.vumigroup.models.PostModel;
import com.vumigroup.services.JSONUtil;
import com.vumigroup.services.RESTClient;

public class Run {

	public static void main(String[] args) {
		//
		System.out.println("Hola VUMI");
		try {
			String response = RESTClient.get(Routes.POSTS, "1");
			System.out.println(response);
			JSONUtil<PostModel> json = new JSONUtil<PostModel>(PostModel.class);
			PostModel obj = json.convert(response);
			System.out.println(obj.getUserId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
