package com.vumigroup;

import com.vumigroup.models.PostModel;
import com.vumigroup.services.JSONUtil;
import com.vumigroup.services.RESTClient;

public class Run {

	public static void main(String[] args) {
		//
		System.out.println("Hola VUMI");
		try {

//			Class<?> obj = Class.forName("[Lcom.vumigroup.models.PostModel;");
//			System.out.println(obj);
//"userId=1"
			String response = RESTClient.get(Routes.POSTS, null);
			System.out.println(response);
			JSONUtil<PostModel> json = new JSONUtil<PostModel>(PostModel.class);
//			PostModel obj = json.toObject(response);
//			System.out.println(obj.getUserId());
			for (PostModel p : json.toList(response)) {
				System.out.println(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
