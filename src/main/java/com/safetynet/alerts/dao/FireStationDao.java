package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.FireStation;

public interface FireStationDao {

	/**
	 * This method retrieve all fire stations from the list.
	 * 
	 * @return a list that contain all the fire stations.
	 */
	public List<FireStation> retrieveAllFireStationsFromTheList();

	/**
	 * This method add a fire station to the list.
	 * 
	 * @param fireStation is an object of type FireStation that contain the data of a fire station.
	 * @return true if the fire station was added to the list.
	 */
	public boolean addAFireStationToTheList(FireStation fireStation);

	/**
	 * This method update a fire station in the list.
	 * 
	 * @param fireStation is an object of type FireStation that contain the data of a fire station.
	 * @return true if the fire station was updated in the list.
	 */
	public boolean updateAFireStationInTheList(FireStation fireStation);

	/**
	 * This method delete a fire station from the list.
	 * 
	 * @param station represent the station number of a fire station.
	 * @param address represent the address of a fire station.
	 * @return true if the fire station was deleted from the list.
	 */
	public boolean deleteAFireStationFromTheList(int station, String address);

}
