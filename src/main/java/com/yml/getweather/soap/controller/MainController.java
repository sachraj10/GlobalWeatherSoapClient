package com.yml.getweather.soap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yml.getweather.soap.client.requestDTO.GetCityByCountryRequestDTO;
import com.yml.getweather.soap.client.utils.SoapClientConstants.ApiEndPoints;
@RestController
public class MainController extends BaseController{

    @Autowired
    public WeatherService weatherService;

    @RequestMapping(value = "/index", method=RequestMethod.GET)
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = ApiEndPoints.GET_CITIES_API_ENDPOINT, method=RequestMethod.POST)
    public ResponseEntity postIndex(@RequestBody GetCityByCountryRequestDTO Country,ModelMap model) {
    	String country=Country.country;
       
    	List<GetAllCities> result = new ArrayList<>();
    	Cities citiesList= new Cities();
        try {
            
    	citiesList=weatherService.getCitiesByCountry(country);
    	result=citiesList.getCityList();
    	/*result=weatherService.getCitiesByCountryName(country);*/
        } catch (Exception ex) {
            model.addAttribute("error", "Error in reading response ");
        }
        return returnSuccessResponseWithList("Result",result);
    }
} 