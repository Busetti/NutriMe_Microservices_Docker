package com.app.meal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbridgedNutrient {
	private String number;
	private String name;
	private double amount;
	private String unitName;
	private String derivationCode;
	private String derivationDescription;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public AbridgedNutrient(String number, String name, double amount, String unitName, String derivationCode,
			String derivationDescription) {
		super();
		this.number = number;
		this.name = name;
		this.amount = amount;
		this.unitName = unitName;
		this.derivationCode = derivationCode;
		this.derivationDescription = derivationDescription;
	}

	public AbridgedNutrient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String nutrientKeyString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(" (");
		builder.append(unitName);
		builder.append(")");
		return builder.toString();
	}
	
	

}
