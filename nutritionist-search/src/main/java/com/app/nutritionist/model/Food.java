package com.app.nutritionist.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Food {
	private int fdcId;
	private String description;
	private String lowercaseDescription;
	private String dataType;
	private String gtinUpc;
	private String publishedDate;
	private String brandOwner;
	private String ingredients;
	private String marketCountry;
	private String foodCategory;
	private String modifiedDate;
	private String dataSource;
	private String servingSizeUnit;
	private double servingSize;
	private String householdServingFullText;
	private String allHighlightFields;
	private double score;
	private ArrayList<FoodNutrient> foodNutrients;
	private ArrayList<Object> finalFoodInputFoods;
	private ArrayList<Object> foodMeasures;
	private ArrayList<Object> foodAttributes;
	private ArrayList<Object> foodAttributeTypes;
	private ArrayList<Object> foodVersionIds;
	private String brandName;
	private String packageWeight;

	public int getFdcId() {
		return fdcId;
	}

	public void setFdcId(int fdcId) {
		this.fdcId = fdcId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLowercaseDescription() {
		return lowercaseDescription;
	}

	public void setLowercaseDescription(String lowercaseDescription) {
		this.lowercaseDescription = lowercaseDescription;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getGtinUpc() {
		return gtinUpc;
	}

	public void setGtinUpc(String gtinUpc) {
		this.gtinUpc = gtinUpc;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getBrandOwner() {
		return brandOwner;
	}

	public void setBrandOwner(String brandOwner) {
		this.brandOwner = brandOwner;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getMarketCountry() {
		return marketCountry;
	}

	public void setMarketCountry(String marketCountry) {
		this.marketCountry = marketCountry;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getServingSizeUnit() {
		return servingSizeUnit;
	}

	public void setServingSizeUnit(String servingSizeUnit) {
		this.servingSizeUnit = servingSizeUnit;
	}

	public double getServingSize() {
		return servingSize;
	}

	public void setServingSize(double servingSize) {
		this.servingSize = servingSize;
	}

	public String getHouseholdServingFullText() {
		return householdServingFullText;
	}

	public void setHouseholdServingFullText(String householdServingFullText) {
		this.householdServingFullText = householdServingFullText;
	}

	public String getAllHighlightFields() {
		return allHighlightFields;
	}

	public void setAllHighlightFields(String allHighlightFields) {
		this.allHighlightFields = allHighlightFields;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public ArrayList<FoodNutrient> getFoodNutrients() {
		return foodNutrients;
	}

	public void setFoodNutrients(ArrayList<FoodNutrient> foodNutrients) {
		this.foodNutrients = foodNutrients;
	}

	public ArrayList<Object> getFinalFoodInputFoods() {
		return finalFoodInputFoods;
	}

	public void setFinalFoodInputFoods(ArrayList<Object> finalFoodInputFoods) {
		this.finalFoodInputFoods = finalFoodInputFoods;
	}

	public ArrayList<Object> getFoodMeasures() {
		return foodMeasures;
	}

	public void setFoodMeasures(ArrayList<Object> foodMeasures) {
		this.foodMeasures = foodMeasures;
	}

	public ArrayList<Object> getFoodAttributes() {
		return foodAttributes;
	}

	public void setFoodAttributes(ArrayList<Object> foodAttributes) {
		this.foodAttributes = foodAttributes;
	}

	public ArrayList<Object> getFoodAttributeTypes() {
		return foodAttributeTypes;
	}

	public void setFoodAttributeTypes(ArrayList<Object> foodAttributeTypes) {
		this.foodAttributeTypes = foodAttributeTypes;
	}

	public ArrayList<Object> getFoodVersionIds() {
		return foodVersionIds;
	}

	public void setFoodVersionIds(ArrayList<Object> foodVersionIds) {
		this.foodVersionIds = foodVersionIds;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(String packageWeight) {
		this.packageWeight = packageWeight;
	}

}
