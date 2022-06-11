package com.app.nutritionist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppPropertiesReader {
	
	@Value("${nutritionist.foodsearch.api}")
	private String api;
	
	@Value("${nutritionist.foodsearch.secretKey}")
	private String secretKey;

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}