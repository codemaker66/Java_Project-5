package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.FireStation;

public class FireStationDaoImpl extends Data implements FireStationDao {

	@Override
	public List<FireStation> retrieveAllFireStationsFromTheList() {
		return fireStations;
	}

	@Override
	public boolean addAFireStationToTheList(FireStation fireStation) {

		boolean check = true;

		for (int i = 0; i < fireStations.size(); i++) {
			if (fireStations.get(i).getAddress().equals(fireStation.getAddress())) {
				check = false;
				break;
			}
		}

		if (check) {
			fireStations.add(fireStation);
			return check;
		}

		return check;

	}

	@Override
	public boolean updateAFireStationInTheList(FireStation fireStation) {

		boolean check = false;

		for (int i = 0; i < fireStations.size(); i++) {
			if (fireStations.get(i).getAddress().equals(fireStation.getAddress())) {
				fireStations.get(i).setStation(fireStation.getStation());
				check = true;
				break;
			}
		}

		return check;

	}

	@Override
	public boolean deleteAFireStationFromTheList(int station, String address) {

		boolean check = false;

		for (int i = 0; i < fireStations.size(); i++) {
			if (fireStations.get(i).getStation() == station && fireStations.get(i).getAddress().equals(address)) {
				fireStations.remove(i);
				check = true;
				break;
			}
		}

		return check;

	}

}
