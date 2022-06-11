package com.app.nutritionist.security;

import java.io.Serializable;

import com.app.nutritionist.entity.User;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final User user;

	public JwtResponse(String jwttoken, User users) {
		this.jwttoken = jwttoken;
		this.user = users;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public User getUser() {
		return user;
	}

	
}