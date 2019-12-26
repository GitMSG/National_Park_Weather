package com.techelevator.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import com.techelevator.models.Survey;

public interface SurveyDao {
	public void saveSurvey(Survey aSurvey);
	public LinkedHashMap<String, Integer> getParksSurveyCount();
}
