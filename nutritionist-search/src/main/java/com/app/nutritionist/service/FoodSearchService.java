package com.app.nutritionist.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.springframework.web.client.RestClientException;

import com.app.nutritionist.model.FoodFdcId;
import com.app.nutritionist.model.MultipleFoodSearchCriteria;


public interface FoodSearchService {
	
	public HttpEntity fetchFoodSearchResponse(String query, String pageNumber, HttpClient httpclient) throws URISyntaxException, IOException, ClientProtocolException;

	public HttpEntity fetchFoodByFdcId(String fdcId, List<Integer> nutrients, HttpClient httpclient) throws URISyntaxException, ClientProtocolException, IOException;

	public FoodFdcId[] fetchFoodByFdcIds(MultipleFoodSearchCriteria searchInput) throws URISyntaxException, RestClientException;
	
}
