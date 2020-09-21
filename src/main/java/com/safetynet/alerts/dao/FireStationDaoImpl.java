package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.FireStation;

public class FireStationDaoImpl implements FireStationDao {

	@Override
	public List<FireStation> retrieveAllFireStationsFromTheList() {
		return Data.fireStations;
	}

	@Override
	public boolean addAFireStationToTheList(FireStation fireStation) {

		boolean check = true;

		for (int i = 0; i < Data.fireStations.size(); i++) {
			if (Data.fireStations.get(i).getAddress().equals(fireStation.getAddress())) {
				check = false;
				break;
			}
		}

		if (check == true) {
			Data.fireStations.add(fireStation);
			return check;
		}

		return check;

	}

	@Override
	public boolean updateAFireStationInTheList(FireStation fireStation) {

		boolean check = false;

		for (int i = 0; i < Data.fireStations.size(); i++) {
			if (Data.fireStations.get(i).getAddress().equals(fireStation.getAddress())) {
				Data.fireStations.get(i).setStation(fireStation.getStation());
				check = true;
				break;
			}
		}

		return check;

	}

	@Override
	public boolean deleteAFireStationFromTheList(int station, String address) {

		boolean check = false;

		for (int i = 0; i < Data.fireStations.size(); i++) {
			if (Data.fireStations.get(i).getStation() == station
					&& Data.fireStations.get(i).getAddress().equals(address)) {
				Data.fireStations.remove(i);
				check = true;
				break;
			}
		}

		return check;

	}

}
