package com.app.nutritionist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodNutrient {
	private int nutrientId;
	private String nutrientName;
	private String nutrientNumber;
	private String unitName;
	private String derivationCode;
	private String derivationDescription;
	private int derivationId;
	private double value;
	private int foodNutrientSourceId;
	private String foodNutrientSourceCode;
	private String foodNutrientSourceDescription;
	private int rank;
	private int indentLevel;
	private int foodNutrientId;
	private int percentDailyValue;
	public int getNutrientId() {
		return nutrientId;
	}
	public void setNutrientId(int nutrientId) {
		this.nutrientId = nutrientId;
	}
	public String getNutrientName() {
		return nutrientName;
	}
	public void setNutrientName(String nutrientName) {
		this.nutrientName = nutrientName;
	}
	public String getNutrientNumber() {
		return nutrientNumber;
	}
	public void setNutrientNumber(String nutrientNumber) {
		this.nutrientNumber = nutrientNumber;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getDerivationCode() {
		return derivationCode;
	}
	public void setDerivationCode(String derivationCode) {
		this.derivationCode = derivationCode;
	}
	public String getDerivationDescription() {
		return derivationDescription;
	}
	public void setDerivationDescription(String derivationDescription) {
		this.derivationDescription = derivationDescription;
	}
	public int getDerivationId() {
		return derivationId;
	}
	public void setDerivationId(int derivationId) {
		this.derivationId = derivationId;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getFoodNutrientSourceId() {
		return foodNutrientSourceId;
	}
	public void setFoodNutrientSourceId(int foodNutrientSourceId) {
		this.foodNutrientSourceId = foodNutrientSourceId;
	}
	public String getFoodNutrientSourceCode() {
		return foodNutrientSourceCode;
	}
	public void setFoodNutrientSourceCode(String foodNutrientSourceCode) {
		this.foodNutrientSourceCode = foodNutrientSourceCode;
	}
	public String getFoodNutrientSourceDescription() {
		return foodNutrientSourceDescription;
	}
	public void setFoodNutrientSourceDescription(String foodNutrientSourceDescription) {
		this.foodNutrientSourceDescription = foodNutrientSourceDescription;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getIndentLevel() {
		return indentLevel;
	}
	public void setIndentLevel(int indentLevel) {
		this.indentLevel = indentLevel;
	}
	public int getFoodNutrientId() {
		return foodNutrientId;
	}
	public void setFoodNutrientId(int foodNutrientId) {
		this.foodNutrientId = foodNutrientId;
	}
	public int getPercentDailyValue() {
		return percentDailyValue;
	}
	public void setPercentDailyValue(int percentDailyValue) {
		this.percentDailyValue = percentDailyValue;
	}
	public FoodNutrient(int nutrientId, String nutrientName, String nutrientNumber, String unitName,
			String derivationCode, String derivationDescription, int derivationId, double value,
			int foodNutrientSourceId, String foodNutrientSourceCode, String foodNutrientSourceDescription, int rank,
			int indentLevel, int foodNutrientId, int percentDailyValue) {
		super();
		this.nutrientId = nutrientId;
		this.nutrientName = nutrientName;
		this.nutrientNumber = nutrientNumber;
		this.unitName = unitName;
		this.derivationCode = derivationCode;
		this.derivationDescription = derivationDescription;
		this.derivationId = derivationId;
		this.value = value;
		this.foodNutrientSourceId = foodNutrientSourceId;
		this.foodNutrientSourceCode = foodNutrientSourceCode;
		this.foodNutrientSourceDescription = foodNutrientSourceDescription;
		this.rank = rank;
		this.indentLevel = indentLevel;
		this.foodNutrientId = foodNutrientId;
		this.percentDailyValue = percentDailyValue;
	}
	public FoodNutrient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
