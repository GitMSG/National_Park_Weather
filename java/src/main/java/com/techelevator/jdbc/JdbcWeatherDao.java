package com.techelevator.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.WeatherDao;
import com.techelevator.models.Weather;
@Component
public class JdbcWeatherDao implements WeatherDao {
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public List<Weather> getWeatherByParkCode(String parkCode) {
		List<Weather> weatherList = new ArrayList<Weather>();
		String sqlGetWeatherByParkCode = "SELECT * FROM weather WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetWeatherByParkCode, parkCode.toUpperCase());
		
		while(results.next()) {
			Weather aWeather = mapWeatherToRow(results);
			
			weatherList.add(aWeather);
		}
		return weatherList;
	}
	
	private Weather mapWeatherToRow(SqlRowSet results) {
		Weather aWeather = new Weather();
		
		aWeather.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
		aWeather.setForecast(results.getString("forecast"));
		aWeather.setHigh(results.getInt("high"));
		aWeather.setLow(results.getInt("low"));
		aWeather.setParkCode(results.getString("parkcode"));
		
		
		return aWeather;
	}

}
