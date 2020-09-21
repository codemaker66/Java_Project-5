package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dao.FireStationDaoImpl;
import com.safetynet.alerts.model.FireStation;

public class FireStationService {

	public List<FireStation> getAllFireStations() {

		FireStationDaoImpl fireStationDaoImpl = new FireStationDaoImpl();

		return fireStationDaoImpl.retrieveAllFireStationsFromTheList();

	}

	public boolean addAFireStation(FireStation fireStation) {

		FireStationDaoImpl fireStationDaoImpl = new FireStationDaoImpl();

		if (fireStationDaoImpl.addAFireStationToTheList(fireStation)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean updateAFireStation(FireStation fireStation) {

		FireStationDaoImpl fireStationDaoImpl = new FireStationDaoImpl();

		if (fireStationDaoImpl.updateAFireStationInTheList(fireStation)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean deleteAFireStation(int station, String address) {

		FireStationDaoImpl fireStationDaoImpl = new FireStationDaoImpl();

		if (fireStationDaoImpl.deleteAFireStationFromTheList(station, address)) {
			return true;
		} else {
			return false;
		}

	}

}
