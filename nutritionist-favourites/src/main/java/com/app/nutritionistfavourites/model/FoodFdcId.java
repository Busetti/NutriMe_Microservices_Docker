package com.app.nutritionistfavourites.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodFdcId {
	private int fdcId;
	private String description;
	private String dataType;
	private String privateationDate;
	private String brandOwner;
	private String gtinUpc;
	private ArrayList<AbridgedNutrient> foodNutrients;

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

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getPrivateationDate() {
		return privateationDate;
	}

	public void setPrivateationDate(String privateationDate) {
		this.privateationDate = privateationDate;
	}

	public String getBrandOwner() {
		return brandOwner;
	}

	public void setBrandOwner(String brandOwner) {
		this.brandOwner = brandOwner;
	}

	public String getGtinUpc() {
		return gtinUpc;
	}

	public void setGtinUpc(String gtinUpc) {
		this.gtinUpc = gtinUpc;
	}

	public ArrayList<AbridgedNutrient> getFoodNutrients() {
		return foodNutrients;
	}

	public void setFoodNutrients(ArrayList<AbridgedNutrient> foodNutrients) {
		this.foodNutrients = foodNutrients;
	}

	public FoodFdcId(int fdcId, String description, String dataType, String privateationDate, String brandOwner,
			String gtinUpc, ArrayList<AbridgedNutrient> foodNutrients) {
		super();
		this.fdcId = fdcId;
		this.description = description;
		this.dataType = dataType;
		this.privateationDate = privateationDate;
		this.brandOwner = brandOwner;
		this.gtinUpc = gtinUpc;
		this.foodNutrients = foodNutrients;
	}

	public FoodFdcId() {
		super();
		// TODO Auto-generated constructor stub
	}

}