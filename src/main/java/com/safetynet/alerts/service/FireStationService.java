package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dao.FireStationDaoImpl;
import com.safetynet.alerts.model.FireStation;

public class FireStationService {

	private FireStationDaoImpl fireStationDaoImpl = new FireStationDaoImpl();

	public List<FireStation> getAllFireStations() {

		return fireStationDaoImpl.retrieveAllFireStationsFromTheList();

	}

	public boolean addAFireStation(FireStation fireStation) {

		boolean check = false;

		if (fireStationDaoImpl.addAFireStationToTheList(fireStation)) {
			check = true;
		}

		return check;

	}

	public boolean updateAFireStation(FireStation fireStation) {

		boolean check = false;

		if (fireStationDaoImpl.updateAFireStationInTheList(fireStation)) {
			check = true;
		}

		return check;

	}

	public boolean deleteAFireStation(int station, String address) {

		boolean check = false;

		if (fireStationDaoImpl.deleteAFireStationFromTheList(station, address)) {
			check = true;
		}

		return check;

	}

}
