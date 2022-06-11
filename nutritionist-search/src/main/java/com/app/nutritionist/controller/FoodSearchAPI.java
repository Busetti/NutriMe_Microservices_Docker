package com.app.nutritionist.controller;

import java.security.InvalidParameterException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.nutritionist.model.FoodFdcId;
import com.app.nutritionist.model.FoodRoot;
import com.app.nutritionist.model.MultipleFoodSearchCriteria;
import com.app.nutritionist.service.FoodSearchService;
import com.app.nutritionist.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/search")
@CrossOrigin
public class FoodSearchAPI {

	private static final Logger log = LoggerFactory.getLogger(FoodSearchAPI.class);

	@Autowired
	private FoodSearchService foodSearchService;

	/**
	 * @param query
	 * @return HttpStatus.OK (200) for Success
	 * 
	 *         Cache Key: "'foodByName:' + {#query, #pageNumber}"
	 */
	@RequestMapping(path = "/foods",  method = RequestMethod.GET)
	@Cacheable(value = "foodsearch", key = "'foodByName:' + {#query, #pageNumber}", sync = true)
	public ResponseEntity<?> searchFoodByCommonName(@RequestParam("searchQuery") String query,
			@RequestParam(required = false, defaultValue = "1") String pageNumber) {

		HttpClient httpclient = HttpClients.createDefault();
		FoodRoot responseEntity = null;
		HttpEntity entity = null;
		try {
			entity = foodSearchService.fetchFoodSearchResponse(query, pageNumber, httpclient);

			if (entity != null) {
				String resEntityToString = EntityUtils.toString(entity);
				ObjectMapper objectMapper = new ObjectMapper();
				responseEntity = objectMapper.readValue(resEntityToString, FoodRoot.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(responseEntity, HttpStatus.OK);
	}

	/**
	 * @param fdcId
	 * @return HttpStatus.OK (200) for Success
	 * 
	 *         Cache Key: "'foodById:' + {#fdcId, #nutrients}"
	 */
	@RequestMapping(path = "/food/{fdcId}",  method = RequestMethod.GET)
	@Cacheable(value = "food", key = "'foodById:' + {#fdcId, #nutrients}", sync = true)
	public ResponseEntity<?> searchFoodById(@PathVariable("fdcId") String fdcId,
			@RequestParam(required = false) List<Integer> nutrients) {

		HttpClient httpclient = HttpClients.createDefault();
		FoodFdcId responseEntity = null;
		HttpEntity entity = null;
		try {
			entity = foodSearchService.fetchFoodByFdcId(fdcId, nutrients, httpclient);

			if (entity != null) {
				String resEntityToString = EntityUtils.toString(entity);
				ObjectMapper objectMapper = new ObjectMapper();
				responseEntity = objectMapper.readValue(resEntityToString, FoodFdcId.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(responseEntity, HttpStatus.OK);
	}

	/**
	 * @param searchInput
	 * @return Cache Key: "'foodByIds:' + {#searchInput.fdcIds.toString(),
	 *         #searchInput.nutrients.toString()}"
	 */
	@RequestMapping(path = "/foods",  method = RequestMethod.POST)
	@Cacheable(value = "food", key = "'foodByIds:' + {#searchInput.fdcIds.toString(), #searchInput.nutrients.toString()}", condition = "#searchInput.nutrients != null", sync = true)
	public ResponseEntity<?> searchFoodByIds(@RequestBody(required = false) MultipleFoodSearchCriteria searchInput) {
		FoodFdcId[] entity = null;
		try {
			if (searchInput.getFormat().toLowerCase().equals(Constants.ABRIDGED))
				entity = foodSearchService.fetchFoodByFdcIds(searchInput);
			else
				throw new InvalidParameterException("Format field must be " + Constants.ABRIDGED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Please provide valid Parameters", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
}
