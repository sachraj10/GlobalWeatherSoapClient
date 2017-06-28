package com.yml.getweather.soap.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.yml.getweather.soap.client.utils.SoapClientConstants.ResponseVariables;

import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;;

@Service
public class WeatherService {

	
	public GlobalWeatherSoap soapService=new GlobalWeatherSoap() {
		
		@Override
		public String getWeather(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getCitiesByCountry(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	/*@Autowired
	public GlobalWeatherSoap soapService;*/

    public CurrentWeather getCurrentWeather (String country, String city) throws JAXBException, ParserConfigurationException, SAXException, IOException {
    	GlobalWeather weather = new GlobalWeather();
        GlobalWeatherSoap soapWeather = weather.getGlobalWeatherSoap();
        String response = soapWeather.getWeather(city, country);
    	
        String weatherStr = soapService.getWeather (city, country);
       
        JAXBContext jaxbContext = JAXBContext.newInstance (CurrentWeather.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller ();

        StringReader reader = new StringReader (weatherStr);
        return (CurrentWeather) unmarshaller.unmarshal (reader);
    }
    
    public Cities getCitiesByCountry (String country) throws JAXBException, ParserConfigurationException, SAXException, IOException {
    	Cities allCities = new Cities();
    	
    	GlobalWeather weather = new GlobalWeather();
        GlobalWeatherSoap soap = weather.getGlobalWeatherSoap();
        String cities = soap.getCitiesByCountry(country);
        
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(cities));
        Document doc = documentBuilder.parse(is);
        NodeList nodes = doc.getElementsByTagName(ResponseVariables.TABLE);
        
        
        for (int i = 0; i < nodes.getLength(); i++) {
        	GetAllCities eachCity =new GetAllCities();
        	Element element = (Element) nodes.item(i);
        	NodeList city = element.getElementsByTagName(ResponseVariables.CITY);
        	Element subelement = (Element) city.item(0);
        	
        	NodeList countryName = element.getElementsByTagName(ResponseVariables.COUNTRY);
        	Element countryNameSubelement = (Element) countryName.item(0);
        	
        	eachCity.setCity(subelement.getTextContent());
        	eachCity.setCountry(countryNameSubelement.getTextContent());
        	allCities.cityList.add(eachCity);
        }
        
        return allCities;
    }
    
    public List<Map<String, String>> getCitiesByCountryName (String country) throws JAXBException, ParserConfigurationException, SAXException, IOException {
    	List<Map<String, String>> cityList = new ArrayList<>();
    	Map<String, String> responseMap = new HashMap<>();
    	GlobalWeather weather = new GlobalWeather();
        GlobalWeatherSoap soap = weather.getGlobalWeatherSoap();
        String cities = soap.getCitiesByCountry(country);
        System.out.println(cities);
        JAXBContext jaxbContext = JAXBContext.newInstance (Cities.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller ();

        StringReader reader = new StringReader (cities);
        return (List<Map<String, String>>) unmarshaller.unmarshal (reader);
        
    }
    
    
    
} 
