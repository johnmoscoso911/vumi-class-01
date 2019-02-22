package com.vumigroup.models;

public class PostModel {
	private int userId;
	@IDField
	private int id;
	private String title;
	private String body;

	@Override
	public String toString() {
		return String.format("[%d] %s", id, title);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
