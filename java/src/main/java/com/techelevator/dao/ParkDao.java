package com.techelevator.dao;

import java.util.List;

import com.techelevator.models.Park;

public interface ParkDao {
	public List<Park> getAllParks();
	
	public Park getParkByParkCode(String parkCode);
}
