package com.vumigroup;

public enum Routes {

	POSTS("posts"), //
	COMMENTS("comments"), //
	ALBUMS("albums"), //
	PHOTOS("photos"), //
	TODOS("todos"), //
	USERS("users");

	public static final String URL = "https://jsonplaceholder.typicode.com/%s";
	
	private String route;

	private Routes(String r) {
		this.route = r;
	}

	public String getRoute() {
		return String.format(URL, route);
	}
}
