package com.siji.practice.buildinganalysis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonaws.util.json.JSONArray;
import com.siji.practice.buildinganalysis.dto.AnalyticsData;
import com.siji.practice.buildinganalysis.dto.BuildingInfos;
import com.siji.practice.buildinganalysis.service.DataProcessingService;
import com.siji.practice.buildinganalysis.service.JsonReaderService;

@Controller
public class MainController {

	@Autowired
	private JsonReaderService jsonReaderService;
	@Autowired
	private DataProcessingService dataProcessingService;
	
	List<BuildingInfos> buildingInfoList = new ArrayList<BuildingInfos>();
	List<AnalyticsData> analyticsDataList = new ArrayList<AnalyticsData>();


	@RequestMapping("/app")
	public String showSampleMessage(Model model) {

		String loadStatus = "Failure";
		//clear list
		buildingInfoList.clear();
		analyticsDataList.clear();
		
		try {
			JSONArray json = jsonReaderService.readJsonFromUrl("http://jobs.mapsted.com/api/Values/GetBuildingData");

			// System.out.println(json.toString());

			int jsonLength = json.length();

			for (int i = 0; i < jsonLength; i++) {
				// System.out.println(json.getJSONObject(i).get("building_id"));
				BuildingInfos buildingInfo = new BuildingInfos();
				buildingInfo.setBuildingId(Integer.parseInt(json.getJSONObject(i).get("building_id").toString()));
				buildingInfo.setBuildingName(json.getJSONObject(i).get("building_name").toString());
				buildingInfo.setCity(json.getJSONObject(i).get("city").toString());
				buildingInfo.setState(json.getJSONObject(i).get("state").toString());
				buildingInfo.setCountry(json.getJSONObject(i).get("country").toString());

				buildingInfoList.add(buildingInfo);

			}

			// Read second JSON

			JSONArray json2 = jsonReaderService.readJsonFromUrl("http://jobs.mapsted.com/api/Values/GetAnalyticsData");
			jsonLength = json2.length();

			for (int i = 0; i < jsonLength; i++) {
				// System.out.println(json.getJSONObject(i).get("building_id"));
				AnalyticsData analyticsData = new AnalyticsData();

				analyticsData.setManufacturer(json2.getJSONObject(i).get("manufacturer").toString());
				analyticsData.setMarketName(json2.getJSONObject(i).get("market_name").toString());
				analyticsData.setCodeName(json2.getJSONObject(i).get("codename").toString());
				analyticsData.setModel(json2.getJSONObject(i).get("model").toString());
				analyticsData.setUsageStatistics(json2.getJSONObject(i).getJSONObject("usage_statistics"));

				analyticsDataList.add(analyticsData);

			}

			loadStatus = "Success";
			
			System.out.println(buildingInfoList.toString());
			System.out.println(analyticsDataList.toString());

		} catch (Exception e) {
			e.printStackTrace();
			loadStatus = "Failure";
		}
		
		
		model.addAttribute("message", loadStatus);
		return "welcome";
		//return "Both JSON loaded to Lists";
	}
	
	
	@RequestMapping("/showResults")
	public String showResults(Model model) {
		
		//Samsung total cost
		Double totalCostSamsung = dataProcessingService.totalPurchaseCost("Samsung", analyticsDataList);
		model.addAttribute("totalCostSamsung", totalCostSamsung);
		
		//countofItemPurchased if id 47
		int itemPurchaseCount = dataProcessingService.countOfItemPurchase(47, analyticsDataList);
		model.addAttribute("itemPurchaseCount", itemPurchaseCount);
		
		//ItemCatPurchaseCost
		Double totalCostofItemCat = dataProcessingService.totalPurchaseCostOfItemCategory(7, analyticsDataList);
		model.addAttribute("totalCostofItemCat", totalCostofItemCat);
		
		//totalPurchaseCostForprovince
		Double totalCostForProvince = dataProcessingService.totalPurchaseCostForProvince("Ontario", analyticsDataList, buildingInfoList);
		model.addAttribute("totalCostForProvince", totalCostForProvince);
		
		//totalPurchaseCostForCountry
		Double totalPurchaseCostForCountry = dataProcessingService.totalPurchaseCostForCountry("United States", analyticsDataList, buildingInfoList);
		model.addAttribute("totalPurchaseCostForCountry", totalPurchaseCostForCountry);
		
		return "result";
	}
}
