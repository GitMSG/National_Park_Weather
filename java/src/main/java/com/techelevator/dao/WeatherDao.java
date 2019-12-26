package com.techelevator.dao;

import java.util.List;

import com.techelevator.models.Weather;

public interface WeatherDao {
	public List<Weather> getWeatherByParkCode(String parkCode);
}
