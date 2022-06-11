package com.app.nutritionist.model;

import java.util.ArrayList;

public class MultipleFoodSearchCriteria {
	private ArrayList<Integer> fdcIds;
	private String format = "abridged";
	private ArrayList<Integer> nutrients;

	public ArrayList<Integer> getFdcIds() {
		return fdcIds;
	}

	public void setFdcIds(ArrayList<Integer> fdcIds) {
		this.fdcIds = fdcIds;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public ArrayList<Integer> getNutrients() {
		return nutrients;
	}

	public void setNutrients(ArrayList<Integer> nutrients) {
		this.nutrients = nutrients;
	}

	public MultipleFoodSearchCriteria(ArrayList<Integer> fdcIds, String format, ArrayList<Integer> nutrients) {
		super();
		this.fdcIds = fdcIds;
		this.format = format;
		this.nutrients = nutrients;
	}

	public MultipleFoodSearchCriteria() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MultipleFoodSearchCriteria [fdcIds=" + fdcIds + ", format=" + format + ", nutrients=" + nutrients + "]";
	}
	
	

}