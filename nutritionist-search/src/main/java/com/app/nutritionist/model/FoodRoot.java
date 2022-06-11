package com.app.nutritionist.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodRoot {
	private int totalHits;
	private int currentPage;
	private int totalPages;
	private ArrayList<Integer> pageList;
	private FoodSearchCriteria foodSearchCriteria;
	private ArrayList<Food> foods;
	private Aggregations aggregations;

	public int getTotalHits() {
		return totalHits;
	}

	public void setTotalHits(int totalHits) {
		this.totalHits = totalHits;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public ArrayList<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<Integer> pageList) {
		this.pageList = pageList;
	}

	public FoodSearchCriteria getFoodSearchCriteria() {
		return foodSearchCriteria;
	}

	public void setFoodSearchCriteria(FoodSearchCriteria foodSearchCriteria) {
		this.foodSearchCriteria = foodSearchCriteria;
	}

	public ArrayList<Food> getFoods() {
		return foods;
	}

	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}

	public Aggregations getAggregations() {
		return aggregations;
	}

	public void setAggregations(Aggregations aggregations) {
		this.aggregations = aggregations;
	}

	public FoodRoot(int totalHits, int currentPage, int totalPages, ArrayList<Integer> pageList,
			FoodSearchCriteria foodSearchCriteria, ArrayList<Food> foods, Aggregations aggregations) {
		super();
		this.totalHits = totalHits;
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.pageList = pageList;
		this.foodSearchCriteria = foodSearchCriteria;
		this.foods = foods;
		this.aggregations = aggregations;
	}

	public FoodRoot() {
		super();
		// TODO Auto-generated constructor stub
	}

}
