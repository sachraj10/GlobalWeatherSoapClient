package com.yml.getweather.soap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.yml.getweather.soap.client.exceptions.GlobalWeatherExceptions;
import com.yml.getweather.soap.client.utils.SoapClientConstants;
import com.yml.getweather.soap.client.utils.SoapClientConstants.ApiFailureMessages;
import com.yml.getweather.soap.client.utils.SoapClientConstants.ResponseVariables;



public class BaseController {
	public static ResponseEntity failureResponse(Exception e) {
		Map<String, String> response = new HashMap<String, String>();
		response.put("error", e.getMessage());
		if(e instanceof GlobalWeatherExceptions) {
			response.put("error", e.getMessage());
			
		} else {
			response.put("error", ApiFailureMessages.TECHNICAL_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity successResponse(String message) {
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", message);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public static ResponseEntity returnSuccessResponse(Map<String, Object> result) {
		/*Map<String, String> response = new HashMap<String, String>();
		response.put("message", message);*/
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	public static ResponseEntity returnSuccessResponseWithListOfObjects(Map<String, List<Object>> result) {
		/*Map<String, String> response = new HashMap<String, String>();
		response.put("message", message);*/
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	public static ResponseEntity returnSuccessResponseWithList(String string, List<GetAllCities> result) {
		Map<String,Object>response =new HashMap<String,Object>();
		response.put(ResponseVariables.RESULT, result);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
}

