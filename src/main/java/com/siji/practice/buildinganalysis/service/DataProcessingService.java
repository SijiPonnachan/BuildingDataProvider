package com.siji.practice.buildinganalysis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.siji.practice.buildinganalysis.dto.AnalyticsData;
import com.siji.practice.buildinganalysis.dto.BuildingInfos;

@Service
public class DataProcessingService {

	public Double totalPurchaseCost(String manufacture, List<AnalyticsData> analyticsDataList) {

		Double costInt = 0.0D;
		for (AnalyticsData analyticsDataItem : analyticsDataList) {

			if (analyticsDataItem.getManufacturer().equalsIgnoreCase(manufacture)) {
				JSONObject usgaeStatJsonObj = analyticsDataItem.getUsageStatistics();
				try {
					costInt = costInt + (Double) usgaeStatJsonObj.getJSONArray("session_infos").getJSONObject(0)
							.getJSONArray("purchases").getJSONObject(0).get("cost");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return costInt;
	}

	public int countOfItemPurchase(int itemId, List<AnalyticsData> analyticsDataList) {

		int itemIdInJson = 0;
		int count = 0;

		for (AnalyticsData analyticsDataItem : analyticsDataList) {

			JSONObject usgaeStatJsonObj = analyticsDataItem.getUsageStatistics();
			try {
				itemIdInJson = (Integer) usgaeStatJsonObj.getJSONArray("session_infos").getJSONObject(0)
						.getJSONArray("purchases").getJSONObject(0).get("item_id");

				if (itemId == itemIdInJson) {
					count++;
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return count;
	}

	public Double totalPurchaseCostOfItemCategory(int itemCatId, List<AnalyticsData> analyticsDataList) {

		int itemCatIdInJson = 0;
		Double totalCost = 0.0D;

		for (AnalyticsData analyticsDataItem : analyticsDataList) {

			JSONObject usgaeStatJsonObj = analyticsDataItem.getUsageStatistics();
			try {
				itemCatIdInJson = (Integer) usgaeStatJsonObj.getJSONArray("session_infos").getJSONObject(0)
						.getJSONArray("purchases").getJSONObject(0).get("item_category_id");

				if (itemCatIdInJson == itemCatId) {
					totalCost = totalCost + (Double) usgaeStatJsonObj.getJSONArray("session_infos").getJSONObject(0)
							.getJSONArray("purchases").getJSONObject(0).get("cost");
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return totalCost;
	}
	
	
	public Double totalPurchaseCostForProvince(String province, List<AnalyticsData> analyticsDataList, List<BuildingInfos> buildingInfoList) {

		Double totalCost = 0.0D;

		for (BuildingInfos buildingInfoItem : buildingInfoList) {
			if(buildingInfoItem.getState().equalsIgnoreCase(province)){
				int buildingId = buildingInfoItem.getBuildingId();
				
				//Get total purchase cost for this province
				for (AnalyticsData analyticsDataItem : analyticsDataList) {
					JSONObject usgaeStatJsonObj = analyticsDataItem.getUsageStatistics();
					try {
						int buildingIdInJson = (Integer) usgaeStatJsonObj.getJSONArray("session_infos").getJSONObject(0).get("building_id");

						if (buildingIdInJson == buildingId) {
							totalCost = totalCost + (Double) usgaeStatJsonObj.getJSONArray("session_infos").getJSONObject(0)
									.getJSONArray("purchases").getJSONObject(0).get("cost");
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
			}
		}

		return totalCost;
	}
	
	public Double totalPurchaseCostForCountry(String country, List<AnalyticsData> analyticsDataList, List<BuildingInfos> buildingInfoList) {

		Double totalCost = 0.0D;

		for (BuildingInfos buildingInfoItem : buildingInfoList) {
			if(buildingInfoItem.getCountry().equalsIgnoreCase(country)){
				int buildingId = buildingInfoItem.getBuildingId();
				
				//Get total purchase cost for this province
				for (AnalyticsData analyticsDataItem : analyticsDataList) {
					JSONObject usgaeStatJsonObj = analyticsDataItem.getUsageStatistics();
					try {
						int buildingIdInJson = (Integer) usgaeStatJsonObj.getJSONArray("session_infos").getJSONObject(0).get("building_id");

						if (buildingIdInJson == buildingId) {
							totalCost = totalCost + (Double) usgaeStatJsonObj.getJSONArray("session_infos").getJSONObject(0)
									.getJSONArray("purchases").getJSONObject(0).get("cost");
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
			}
		}

		return totalCost;
	}
}
