package com.siji.practice.buildinganalysis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.siji.practice.buildinganalysis.dto.AnalyticsData;

@Service
public class DataProcessingService {

	public Double totalPurchaseCost(String manufacture, List<AnalyticsData> analyticsDataList){
		
		Double costInt = 0.0D;
		for (AnalyticsData analyticsDataItem : analyticsDataList) {

			if(analyticsDataItem.getManufacturer().equalsIgnoreCase(manufacture)){
				JSONObject usgaeStatJsonObj = analyticsDataItem.getUsageStatistics();
				try {
					costInt = costInt + (Double)usgaeStatJsonObj.getJSONArray("session_infos").getJSONObject(0).getJSONArray("purchases").getJSONObject(0).get("cost");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} 
		}
		
		return costInt;
	}
	
}
