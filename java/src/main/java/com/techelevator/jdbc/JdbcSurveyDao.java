package com.techelevator.jdbc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.ParkDao;
import com.techelevator.dao.SurveyDao;
import com.techelevator.models.Survey;

@Component
public class JdbcSurveyDao implements SurveyDao{
	
	ParkDao parkDao;
	
	JdbcTemplate jdbcTemplate;
	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public void saveSurvey(Survey aSurvey) {
		String sqlSaveSurvey = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) VALUES(?,?,?,?)";
		
		jdbcTemplate.update(sqlSaveSurvey, aSurvey.getParkCode(), aSurvey.getEmailAddress(), aSurvey.getState(), aSurvey.getActivityLevel() );
			
	}

	@Override
	public LinkedHashMap<String, Integer> getParksSurveyCount() {
		
		LinkedHashMap<String, Integer> parkMap = new LinkedHashMap<String, Integer>();
		
		String parkCode= "";
		int surveyCount = 0;
		
		String sqlGetAllSurveys = "SELECT parkname, park.parkcode, count(*) FROM survey_result " + 
									"JOIN park " + 
									"ON park.parkcode = survey_result.parkcode " + 
									"GROUP BY park.parkcode, parkname ORDER BY count DESC , park.parkname ASC";
							
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllSurveys);
		
		while(results.next()) {
			parkCode = results.getString("parkcode");
			surveyCount = results.getInt("count");
			parkMap.put(parkCode, surveyCount);
		}
		
		
		
		
		return parkMap;
	}

}
