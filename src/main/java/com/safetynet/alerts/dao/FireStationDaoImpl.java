package com.safetynet.alerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.FireStation;

@Repository
public class FireStationDaoImpl implements FireStationDao {
	
	private List<FireStation> fireStations = Data.instance().getFireStations();

	/**
	 * @see com.safetynet.alerts.dao.FireStationDao#retrieveAllFireStationsFromTheList()
	 */
	@Override
	public List<FireStation> retrieveAllFireStationsFromTheList() {
		return fireStations;
	}

	/**
	 * @see com.safetynet.alerts.dao.FireStationDao#addAFireStationToTheList(FireStation)
	 */
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

	/**
	 * @see com.safetynet.alerts.dao.FireStationDao#updateAFireStationInTheList(FireStation)
	 */
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

	/**
	 * @see com.safetynet.alerts.dao.FireStationDao#deleteAFireStationFromTheList(int, String)
	 */
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
