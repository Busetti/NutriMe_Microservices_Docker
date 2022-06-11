package com.app.nutritionist.serviceImpl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.app.nutritionist.config.AppPropertiesReader;
import com.app.nutritionist.model.FoodFdcId;
import com.app.nutritionist.model.MultipleFoodSearchCriteria;
import com.app.nutritionist.service.FoodSearchService;
import com.app.nutritionist.utils.Constants;

@Service
public class FoodSearchServiceImpl implements FoodSearchService {

	private static final Logger log = LoggerFactory.getLogger(FoodSearchServiceImpl.class);

	@Autowired
	private AppPropertiesReader propertiesReader;

	@Override
	public HttpEntity fetchFoodSearchResponse(String query, String pageNumber, HttpClient httpclient)
			throws URISyntaxException, IOException, ClientProtocolException {
		URIBuilder builder = new URIBuilder(propertiesReader.getApi() + Constants.FOODS_SEARCH_PATH);

		builder.setParameter("query", query);
		builder.setParameter("pageSize", "25");
		builder.setParameter("pageNumber", (null != pageNumber) ? pageNumber : "1");
		builder.setParameter("sortOrder", "asc");
		log.info("Executing Third Party API " + builder.toString());

		return extractApiResponse(httpclient, builder);
	}

	@Override
	public HttpEntity fetchFoodByFdcId(String fdcId, List<Integer> nutrients, HttpClient httpclient)
			throws URISyntaxException, ClientProtocolException, IOException {

		StringBuilder path = new StringBuilder();
		path.append(propertiesReader.getApi() + Constants.FOOD_BYID_PATH + fdcId);
		path.append("?format=abridged");

		if (!CollectionUtils.isEmpty(nutrients)) {
			for (Integer i : nutrients) {
				path.append("&nutrients=" + String.valueOf(i));
			}
		}

		URIBuilder builder = new URIBuilder(path.toString());
		log.info("Executing Third Party API " + builder.toString());
		return extractApiResponse(httpclient, builder);
	}

	@Override
	public FoodFdcId[] fetchFoodByFdcIds(MultipleFoodSearchCriteria searchInput)
			throws URISyntaxException, RestClientException {

		RestTemplate restTemplate = new RestTemplate();

		String baseUrl = propertiesReader.getApi() + Constants.FOODS_PATH;

		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set(Constants.ACCEPT, Constants.APPLICATION_JSON);
		headers.set(Constants.API_KEY, propertiesReader.getSecretKey());
		org.springframework.http.HttpEntity<MultipleFoodSearchCriteria> request = new org.springframework.http.HttpEntity<>(
				searchInput, headers);

		log.info("foods by multiple by Ids  "+ uri.toString() );
		log.info("Request Body: " + request.toString());
		ResponseEntity<FoodFdcId[]> result = restTemplate.postForEntity(uri, request, FoodFdcId[].class);
		if(result.getStatusCode().is2xxSuccessful()) {
			return result.getBody();
		}else {
			throw new RuntimeException("RestTemplate Failed to fetch response");
		}
	}

	private HttpEntity extractApiResponse(HttpClient httpclient, URIBuilder builder)
			throws URISyntaxException, IOException, ClientProtocolException {
		URI uri = builder.build();
		HttpGet request = new HttpGet(uri);
		request.setHeader(Constants.ACCEPT, Constants.APPLICATION_JSON);
		request.setHeader(Constants.API_KEY, propertiesReader.getSecretKey());

		HttpResponse response = httpclient.execute(request);
		HttpEntity entity = response.getEntity();
		return entity;
	}

}
