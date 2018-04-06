package com.siji.practice.buildinganalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.siji.practice.buildinganalysis.service.DataProcessingService;
import com.siji.practice.buildinganalysis.service.JsonReaderService;

@Controller
public class SessionDataController {

	@Autowired
	private JsonReaderService jsonReaderService;
	@Autowired
	private DataProcessingService dataProcessingService;
	
	/*@RequestMapping("/getManufactureCost")
	public String showTotalCostofManufacture(){
		
		Double totalCost = dataProcessingService.totalPurchaseCost("Samsung", analyticsDataList);
		
		return "totalCost is " + totalCost;
		
	}*/

}
