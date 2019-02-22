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
			String response = RESTClient.get(Routes.POSTS, "1");
			System.out.println(response);
			JSONUtil<PostModel> json = new JSONUtil<PostModel>(PostModel.class);
			PostModel obj = json.toObject(response);
			obj.setTitle("Edicion desde VUMI");
			String newPostResponse = RESTClient.put(Routes.POSTS, obj, PostModel.class);
			System.out.println(newPostResponse);
			System.out.println(json.toObject(newPostResponse));

//			System.out.println(obj.getUserId());

//			PostModel[]array=new PostModel[] {};
//			array.

//			for (PostModel p : json.toList(response)) {
//				System.out.println(p);
//			}

//			PostModel newPost = new PostModel();
//			newPost.setBody("Hola");
//			newPost.setTitle("VUMI");
//			newPost.setUserId(9);
//			String newPostResponse = RESTClient.post(Routes.POSTS, newPost, PostModel.class);
//			System.out.println(newPostResponse);
//			System.out.println(json.toObject(newPostResponse));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
