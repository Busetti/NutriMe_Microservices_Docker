package com.app.nutritionist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataType {
	@JsonProperty("Branded")
	private int branded;
	@JsonProperty("SR Legacy")
	private int sRLegacy;
	@JsonProperty("Survey (FNDDS)")
	private int surveyFNDDS;
	@JsonProperty("Foundation")
	private int foundation;

	public int getBranded() {
		return branded;
	}

	public void setBranded(int branded) {
		this.branded = branded;
	}

	public int getsRLegacy() {
		return sRLegacy;
	}

	public void setsRLegacy(int sRLegacy) {
		this.sRLegacy = sRLegacy;
	}

	public int getSurveyFNDDS() {
		return surveyFNDDS;
	}

	public void setSurveyFNDDS(int surveyFNDDS) {
		this.surveyFNDDS = surveyFNDDS;
	}

	public int getFoundation() {
		return foundation;
	}

	public void setFoundation(int foundation) {
		this.foundation = foundation;
	}

	public DataType(int branded, int sRLegacy, int surveyFNDDS, int foundation) {
		super();
		this.branded = branded;
		this.sRLegacy = sRLegacy;
		this.surveyFNDDS = surveyFNDDS;
		this.foundation = foundation;
	}

	public DataType() {
		super();
		// TODO Auto-generated constructor stub
	}

}
