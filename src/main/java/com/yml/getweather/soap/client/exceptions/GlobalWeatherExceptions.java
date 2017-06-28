package com.yml.getweather.soap.client.exceptions;

public class GlobalWeatherExceptions extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public GlobalWeatherExceptions () {}
	
	public GlobalWeatherExceptions(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}