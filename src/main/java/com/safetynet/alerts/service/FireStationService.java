package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dao.FireStationDaoImpl;
import com.safetynet.alerts.model.FireStation;

public class FireStationService {

	public List<FireStation> getAllFireStations() {

		FireStationDaoImpl fireStationDaoImpl = new FireStationDaoImpl();

		return fireStationDaoImpl.retrieveAllFireStationsFromTheList();

	}

	public void addAFireStation(FireStation fireStation) {

		FireStationDaoImpl fireStationDaoImpl = new FireStationDaoImpl();

		fireStationDaoImpl.addAFireStationToTheList(fireStation);

	}

	public void updateAFireStation(FireStation fireStation) {

		FireStationDaoImpl fireStationDaoImpl = new FireStationDaoImpl();

		fireStationDaoImpl.updateAFireStationInTheList(fireStation);

	}

	public void deleteAFireStation(String station, String address) {

		FireStationDaoImpl fireStationDaoImpl = new FireStationDaoImpl();

		fireStationDaoImpl.deleteAFireStationFromTheList(station, address);

	}

}
