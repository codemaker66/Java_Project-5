package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.safetynet.alerts.dao.OutputDaoImpl;
import com.safetynet.alerts.model.Output;

public class OutputService {

	private OutputDaoImpl outputDaoImpl = new OutputDaoImpl();

	public Output findPersonsByFireStationNumber(int stationNumber) {

		List<Output> persons = outputDaoImpl.retrievePersonsByFireStationNumber(stationNumber);
		int childrensCount = 0;
		int adultsCount = 0;

		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getAge() <= 18) {
				childrensCount++;
			} else {
				adultsCount++;
			}
		}

		Output output = new Output();

		output.setPersons(persons);
		output.setChildrensCount(childrensCount);
		output.setAdultsCount(adultsCount);

		return output;
	}

	public Output findChildrensByAddress(String address) {

		List<Output> persons = outputDaoImpl.retrieveChildrensByAddress(address);

		Output output = new Output();
		output.setChildrens(persons);
		return output;
	}

	public Output findPhoneNumbersByFireStationNumber(int firestation) {

		List<Output> list = outputDaoImpl.retrievePhoneNumbersByFireStationNumber(firestation);

		Output output = new Output();
		output.setPhoneNumbers(list);
		return output;
	}

	public Output findPersonsByAddress(String address) {

		List<Output> persons = outputDaoImpl.retrievePersonsByAddress(address);

		Output output = new Output();

		if (persons != null && !persons.isEmpty()) {
			if (persons.size() == 1) {
				output.setFireStation(
						"This person is served by the firestaion number : " + persons.get(0).getFireStationNumber());
			} else {
				output.setFireStation(
						"These persons are served by the firestaion number : " + persons.get(0).getFireStationNumber());
			}
		}

		output.setPersons(persons);
		return output;

	}

	public Output findPersonsByFireStationNumbers(List<Integer> stations) {

		List<Output> persons = outputDaoImpl.retrievePersonsByFireStationNumbers(stations);

		Map<String, List<Output>> map = persons.stream().collect(Collectors.groupingBy(Output::getAddress));

		Output output = new Output();

		List<List<Output>> values = map.values().stream().collect(Collectors.toList());

		output.setPersonsGrouped(values);
		return output;
	}

	public Output findPersonByFirstAndLastName(String firstName, String lastName) {

		List<Output> list = outputDaoImpl.retrievePersonByFirstAndLastName(firstName, lastName);

		Output output = new Output();
		output.setPersons(list);
		return output;
	}

	public Output findEmailsByCity(String city) {

		List<Output> list = outputDaoImpl.retrieveEmailsByCity(city);

		Output output = new Output();
		output.setEmails(list);
		return output;
	}

}
