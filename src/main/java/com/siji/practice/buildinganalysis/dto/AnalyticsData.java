package com.siji.practice.buildinganalysis.dto;

import com.amazonaws.util.json.JSONObject;

public class AnalyticsData {

	private String manufacturer;
	private String marketName;
	private String codeName;
	private String model;
	private JSONObject usageStatistics;
	
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public JSONObject getUsageStatistics() {
		return usageStatistics;
	}
	public void setUsageStatistics(JSONObject usageStatistics) {
		this.usageStatistics = usageStatistics;
	}
	
}
