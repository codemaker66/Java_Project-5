package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.dao.OutputDao;
import com.safetynet.alerts.model.Output;

@Service
public class OutputService {

	@Autowired
	private OutputDao outputDaoImpl;
	private static final Logger logger = LogManager.getLogger(OutputService.class);
	
	/**
	 * This method call the outputDaoImpl to retrieve persons by fire station number.
	 * 
	 * @param stationNumber represent the station number of a fire station.
	 * @return a list of persons that are served by the indicated fire station.
	 */
	public Output findPersonsByFireStationNumber(int stationNumber) {

		List<Output> list = outputDaoImpl.retrievePersonsByFireStationNumber(stationNumber);
		int childrensCount = 0;
		int adultsCount = 0;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAge() <= 18) {
				childrensCount++;
			} else {
				adultsCount++;
			}
		}

		Output output = new Output();

		output.setPersons(list);
		output.setChildrenCount(childrensCount);
		output.setAdultsCount(adultsCount);

		logger.debug((output.getPersons() != null && !output.getPersons().isEmpty()) ? "output contains data" : "output is empty");
		return output;
	}

	/**
	 * This method call the outputDaoImpl to retrieve children by address.
	 * 
	 * @param address represent the address where one or many children live.
	 * @return a list of children that live in the indicated address.
	 */
	public Output findChildrenByAddress(String address) {

		List<Output> list = outputDaoImpl.retrieveChildrenByAddress(address);

		Output output = new Output();
		output.setChildren(list);
		
		logger.debug((output.getChildren() != null && !output.getChildren().isEmpty()) ? "output contains data" : "output is empty");
		return output;
	}

	/**
	 * This method call the outputDaoImpl to retrieve phone numbers by fire station number.
	 * 
	 * @param firestation represent the station number of a fire station.
	 * @return a list of phone numbers registered by the indicated fire station.
	 */
	public Output findPhoneNumbersByFireStationNumber(int firestation) {

		List<Output> list = outputDaoImpl.retrievePhoneNumbersByFireStationNumber(firestation);

		Output output = new Output();
		output.setPhoneNumbers(list);
		
		logger.debug((output.getPhoneNumbers() != null && !output.getPhoneNumbers().isEmpty()) ? "output contains data" : "output is empty");
		return output;
	}

	/**
	 * This method call the outputDaoImpl to retrieve persons by address.
	 * 
	 * @param address represent the address where one or many persons live.
	 * @return a list of persons that live in the indicated address.
	 */
	public Output findPersonsByAddress(String address) {

		List<Output> list = outputDaoImpl.retrievePersonsByAddress(address);

		Output output = new Output();

		if (list != null && !list.isEmpty()) {
			if (list.size() == 1) {
				output.setFireStation(
						"This person is served by the firestaion number : " + list.get(0).getFireStationNumber());
			} else {
				output.setFireStation(
						"These persons are served by the firestaion number : " + list.get(0).getFireStationNumber());
			}
		}

		output.setPersons(list);
		
		logger.debug((output.getPersons() != null && !output.getPersons().isEmpty()) ? "output contains data" : "output is empty");
		return output;

	}

	/**
	 * This method call the outputDaoImpl to retrieve persons by fire station numbers.
	 * 
	 * @param stations represent a list that contain one or many fire station numbers.
	 * @return a list of persons that are served by the indicated fire stations.
	 */
	public Output findPersonsByFireStationNumbers(List<Integer> stations) {

		List<Output> list = outputDaoImpl.retrievePersonsByFireStationNumbers(stations);

		Map<String, List<Output>> map = list.stream().collect(Collectors.groupingBy(Output::getAddress));

		Output output = new Output();

		List<List<Output>> values = map.values().stream().collect(Collectors.toList());

		output.setPersonsGroupedByAddress(values);
		
		logger.debug((output.getPersonsGroupedByAddress() != null && !output.getPersonsGroupedByAddress().isEmpty()) ? "output contains data" : "output is empty");
		return output;
	}

	/**
	 * This method call the outputDaoImpl to retrieve one or many persons by first and last name.
	 * 
	 * @param firstName represent the first name of a person.
	 * @param lastName represent the last name of a person.
	 * @return a list of one or many persons (if they have the same last name).
	 */
	public Output findPersonByFirstAndLastName(String firstName, String lastName) {

		List<Output> list = outputDaoImpl.retrievePersonByFirstAndLastName(firstName, lastName);

		Output output = new Output();
		output.setPersons(list);
		
		logger.debug((output.getPersons() != null && !output.getPersons().isEmpty()) ? "output contains data" : "output is empty");
		return output;
	}

	/**
	 * This method call the outputDaoImpl to retrieve emails by city.
	 * 
	 * @param city represent the city where one or many persons live.
	 * @return a list of one or many emails of persons living in the indicated city.
	 */
	public Output findEmailsByCity(String city) {

		List<Output> list = outputDaoImpl.retrieveEmailsByCity(city);

		Output output = new Output();
		output.setEmails(list);
		
		logger.debug((output.getEmails() != null && !output.getEmails().isEmpty()) ? "output contains data" : "output is empty");
		return output;
	}

}
