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
	public void addAFireStationToTheList(FireStation fireStation) {
		Data.fireStations.add(fireStation);

	}

	@Override
	public void updateAFireStationInTheList(FireStation fireStation) {
		for (int i = 0; i < Data.fireStations.size(); i++) {
			if (Data.fireStations.get(i).getAddress().equals(fireStation.getAddress())) {
				Data.fireStations.get(i).setStation(fireStation.getStation());
			}
		}

	}

	@Override
	public void deleteAFireStationFromTheList(String station, String address) {
		for (int i = 0; i < Data.fireStations.size(); i++) {
			if (Data.fireStations.get(i).getStation().equals(station)
					&& Data.fireStations.get(i).getAddress().equals(address)) {
				Data.fireStations.remove(i);
			}
		}

	}

}
