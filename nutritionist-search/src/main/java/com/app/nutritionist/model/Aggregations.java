package com.app.nutritionist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aggregations {
	private DataType dataType;
	private Nutrients nutrients;

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public Nutrients getNutrients() {
		return nutrients;
	}

	public void setNutrients(Nutrients nutrients) {
		this.nutrients = nutrients;
	}

	public Aggregations(DataType dataType, Nutrients nutrients) {
		super();
		this.dataType = dataType;
		this.nutrients = nutrients;
	}

	public Aggregations() {
		super();
		// TODO Auto-generated constructor stub
	}

}
