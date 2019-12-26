package com.techelevator.models;

public class Weather {
	
	private String parkCode;
	private int fiveDayForecastValue;
	private int low;
	private int high;
	private String forecast;
	
	
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}
	public int getLow() {
		return low;
	}
	public void setLow(int low) {
		this.low = low;
	}
	public int getHigh() {
		return high;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		
		//partly cloudy
		//partlyCloudy
		
		//if forecast has a space, then remove the space and capitalize the second word
		if(forecast.contains(" ")) {
			
			String[] forecastArray;
			
			forecastArray = forecast.split(" ");  //forecastArray = {"partly", "cloudy"}
			
			forecastArray[1] = forecastArray[1].substring(0,1).toUpperCase() + forecastArray[1].substring(1);
			
			forecast = forecastArray [0] +  forecastArray[1];
			
		}
		
		
		this.forecast = forecast;
	}
	
	

}
