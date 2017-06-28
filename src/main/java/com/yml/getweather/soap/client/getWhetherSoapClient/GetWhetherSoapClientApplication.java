package com.yml.getweather.soap.client.getWhetherSoapClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.yml.getweather.soap.controller.BaseController;
import com.yml.getweather.soap.controller.CurrentWeather;
import com.yml.getweather.soap.controller.WeatherService;

import net.webservicex.GlobalWeatherSoap;



@SpringBootApplication(scanBasePackages={"com.yml.getweather.soap.client.utils","com.yml.getwhether.soap.client.exceptions","com.yml.getweather.soap.controller","net.webservicex"})
@ComponentScan(basePackageClasses={BaseController.class,WeatherService.class,GlobalWeatherSoap.class})
@EnableAutoConfiguration
@EntityScan(basePackageClasses={CurrentWeather.class})
public class GetWhetherSoapClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetWhetherSoapClientApplication.class, args);
	}
}
