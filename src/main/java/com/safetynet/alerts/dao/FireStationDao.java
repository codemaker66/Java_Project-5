package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.FireStation;

public interface FireStationDao {

	public List<FireStation> retrieveAllFireStationsFromTheList();

	public boolean addAFireStationToTheList(FireStation fireStation);

	public boolean updateAFireStationInTheList(FireStation fireStation);

	public boolean deleteAFireStationFromTheList(int station, String address);

}
