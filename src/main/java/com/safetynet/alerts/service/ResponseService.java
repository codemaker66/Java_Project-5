package com.safetynet.alerts.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.safetynet.alerts.dao.ResponseDaoImpl;
import com.safetynet.alerts.model.Output;
import com.safetynet.alerts.model.Response;

public class ResponseService {

	public Response findPersonsByFireStationNumber(int stationNumber) {

		ResponseDaoImpl responseDaoImpl = new ResponseDaoImpl();

		List<Output> persons = responseDaoImpl.retrievePersonsByFireStationNumber(stationNumber);
		int childrensCount = 0;
		int adultsCount = 0;

		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getAge() <= 18) {
				childrensCount++;
			} else {
				adultsCount++;
			}
		}

		Response response = new Response();

		response.setPersons(persons);
		response.setChildrensCount(childrensCount);
		response.setAdultsCount(adultsCount);

		return response;
	}

	public Response findChildrensByAddress(String address) {

		ResponseDaoImpl responseDaoImpl = new ResponseDaoImpl();

		List<Output> persons = responseDaoImpl.retrieveChildrensByAddress(address);

		Response response = new Response();
		response.setChildrens(persons);
		return response;
	}

	public Response findPhoneNumbersByFireStationNumber(int firestation) {

		ResponseDaoImpl responseDaoImpl = new ResponseDaoImpl();

		List<Output> list = responseDaoImpl.retrievePhoneNumbersByFireStationNumber(firestation);

		Response response = new Response();
		response.setPhoneNumbers(list);
		return response;
	}

	public Response findPersonsByAddress(String address) {

		ResponseDaoImpl responseDaoImpl = new ResponseDaoImpl();

		List<Output> persons = responseDaoImpl.retrievePersonsByAddress(address);

		Response response = new Response();

		if (persons != null && !persons.isEmpty()) {
			if (persons.size() == 1) {
				response.setFireStation(
						"This person is served by the firestaion number : " + persons.get(0).getFireStationNumber());
			} else {
				response.setFireStation(
						"These persons are served by the firestaion number : " + persons.get(0).getFireStationNumber());
			}
		}

		response.setPersons(persons);
		return response;

	}

	public Response findPersonsByFireStationNumbers(List<Integer> stations) {

		ResponseDaoImpl responseDaoImpl = new ResponseDaoImpl();

		List<Output> persons = responseDaoImpl.retrievePersonsByFireStationNumbers(stations);

		Map<String, List<Output>> map = persons.stream().collect(Collectors.groupingBy(Output::getAddress));

		Response response = new Response();

		List<List<Output>> values = map.values().stream().collect(Collectors.toList());

		response.setPersonsGrouped(values);
		return response;
	}

	public Response findPersonByFirstAndLastName(String firstName, String lastName) {

		ResponseDaoImpl responseDaoImpl = new ResponseDaoImpl();

		List<Output> list = responseDaoImpl.retrievePersonByFirstAndLastName(firstName, lastName);

		Response response = new Response();
		response.setPersons(list);
		return response;
	}

	public Response findEmailsByCity(String city) {

		ResponseDaoImpl responseDaoImpl = new ResponseDaoImpl();

		List<Output> list = responseDaoImpl.retrieveEmailsByCity(city);

		Response response = new Response();
		response.setEmails(list);
		return response;
	}

}
