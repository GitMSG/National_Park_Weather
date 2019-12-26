package com.techelevator.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.ParkDao;
import com.techelevator.models.Park;

@Component
public class JdbcParkDao implements ParkDao {
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		
		List<Park> parkList = new ArrayList<Park>();
		String sqlGetAllParks = "SELECT * FROM park";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		
		while(results.next()) {
			Park aPark = mapRowToPark(results);
			
			parkList.add(aPark);
		}
		return parkList;
	}
	@Override
	public Park getParkByParkCode(String parkCode) {
		Park aPark = new Park();
		String sqlGetParkByParkCode = "SELECT * FROM park WHERE parkcode = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkByParkCode, parkCode.toUpperCase());
		
		while(results.next()) {
			 aPark = mapRowToPark(results);
		}
		return aPark;
	}
	
	
	private Park mapRowToPark(SqlRowSet results) {
		Park aPark = new Park();
		
		aPark.setAcreage(results.getInt("acreage"));
		aPark.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		aPark.setClimate(results.getString("climate"));
		aPark.setElevationInFeet(results.getInt("elevationinfeet"));
		aPark.setEntryFee(results.getInt("entryfee"));
		aPark.setInspirationalQuote(results.getString("inspirationalquote"));
		aPark.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
		aPark.setMilesOfTrail(results.getFloat("milesoftrail"));
		aPark.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		aPark.setNumberOfCampsites(results.getInt("numberofcampsites"));
		aPark.setParkCode(results.getString("parkcode").toLowerCase());
		aPark.setParkDescription(results.getString("parkdescription"));
		aPark.setParkName(results.getString("parkname"));
		aPark.setState(results.getString("state"));
		aPark.setYearFounded(results.getInt("yearfounded"));
		
		return aPark;
	}

}
