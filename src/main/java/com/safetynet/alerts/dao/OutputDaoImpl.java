package com.safetynet.alerts.dao;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.Person;

public class OutputDaoImpl implements OutputDao {

	@Override
	public List<Person> retrievePersonsByFireStationNumber(String fireStationNumber) {
		List<Person> list = new ArrayList<>();

		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getFireStationNumber().equals(fireStationNumber)) {
				list.add(Data.persons.get(i));
			}
		}

		return list;
	}

	@Override
	public List<Person> retrievePersonsByAddress(String address) {
		List<Person> list = new ArrayList<>();

		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getAddress().equals(address)) {
				list.add(Data.persons.get(i));
			}
		}

		return list;
	}

	@Override
	public List<Person> retrievePersonsByFireStationNumbers(List<String> stations) {
		List<Person> list = new ArrayList<>();

		for (int i = 0; i < stations.size(); i++) {
			for (int j = 0; j < Data.persons.size(); j++) {
				if (Data.persons.get(j).getFireStationNumber().equals(stations.get(i))) {
					list.add(Data.persons.get(j));
				}
			}
		}

		return list;
	}

	@Override
	public List<Person> retrievePersonByFirstAndLastName(String firstName, String lastName) {
		List<Person> list = new ArrayList<>();

		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getFirstName().equals(firstName)
					&& Data.persons.get(i).getLastName().equals(lastName)
					|| Data.persons.get(i).getFirstName().equals(firstName)
							&& !Data.persons.get(i).getLastName().equals(lastName)) {
				list.add(Data.persons.get(i));
			}
		}

		return list;
	}

	@Override
	public List<Person> retrievePersonsByCity(String city) {
		List<Person> list = new ArrayList<>();

		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getCity().equals(city)) {
				list.add(Data.persons.get(i));
			}
		}

		return list;
	}

}
