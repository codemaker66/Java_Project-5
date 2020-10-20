package com.safetynet.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.alerts.dao.FireStationDao;
import com.safetynet.alerts.model.FireStation;

@Service
public class FireStationService {

	@Autowired
	private FireStationDao fireStationDaoImpl;
	private static final Logger logger = LogManager.getLogger(FireStationService.class);

	/**
	 * This method call the fireStationDaoImpl to retrieve all fire stations from the list.
	 * 
	 * @return a list that contain all the fire stations.
	 */
	public List<FireStation> getAllFireStations() {

		return fireStationDaoImpl.retrieveAllFireStationsFromTheList();

	}

	/**
	 * This method call the fireStationDaoImpl to add a fire station to the list.
	 * 
	 * @param fireStationis is an object of type FireStation that contain the data of a fire station.
	 * @return true if the fire station was added to the list.
	 */
	public boolean addAFireStation(FireStation fireStation) {

		boolean check = false;

		if (fireStationDaoImpl.addAFireStationToTheList(fireStation)) {
			check = true;
		}

		logger.debug("Check is : " + check);
		return check;

	}

	/**
	 * This method call the fireStationDaoImpl to update a fire station in the list.
	 * 
	 * @param fireStation is an object of type FireStation that contain the data of a fire station.
	 * @return true if the fire station was updated in the list.
	 */
	public boolean updateAFireStation(FireStation fireStation) {

		boolean check = false;

		if (fireStationDaoImpl.updateAFireStationInTheList(fireStation)) {
			check = true;
		}

		logger.debug("Check is : " + check);
		return check;

	}

	/**
	 * This method call the fireStationDaoImpl to delete a fire station from the list.
	 * 
	 * @param station represent the station number of a fire station.
	 * @param address represent the address of a fire station.
	 * @return true if the fire station was deleted from the list.
	 */
	public boolean deleteAFireStation(int station, String address) {

		boolean check = false;

		if (fireStationDaoImpl.deleteAFireStationFromTheList(station, address)) {
			check = true;
		}

		logger.debug("Check is : " + check);
		return check;

	}

}
