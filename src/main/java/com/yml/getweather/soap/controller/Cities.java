package com.yml.getweather.soap.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.yml.getweather.soap.controller.GetAllCities;

@XmlRootElement(name="NewDataSet")
public class Cities {

	public List<GetAllCities> getCityList() {
		return cityList;
	}

	public void setCityList(List<GetAllCities> cityList) {
		this.cityList = cityList;
	}
	
	public void setCityList(GetAllCities city) {
		this.cityList.add(city);
	}

	public Cities () {
		this.cityList = new ArrayList<GetAllCities>();
	}
	@XmlElement(name="Table")
	public List<GetAllCities> cityList;
}
