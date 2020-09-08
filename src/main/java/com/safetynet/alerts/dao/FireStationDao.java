package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.FireStation;

public interface FireStationDao {

	public List<FireStation> retrieveAllFireStationsFromTheList();

	public void addAFireStationToTheList(FireStation fireStation);

	public void updateAFireStationInTheList(FireStation fireStation);

	public void deleteAFireStationFromTheList(String station, String address);

}
