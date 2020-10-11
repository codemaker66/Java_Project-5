package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.alerts.exceptions.ResourceException;
import com.safetynet.alerts.filters.Filter;
import com.safetynet.alerts.model.Output;
import com.safetynet.alerts.service.OutputService;

@RestController
public class OutputController {

	private OutputService outputService = new OutputService();

	/**
	 * This method call the outputService to find persons by fire station number.
	 * 
	 * @param stationNumber represent the station number of a fire station.
	 * @return a MappingJacksonValue containing the filtered output data.
	 */
	@GetMapping(value = "/firestation")
	public MappingJacksonValue getPersonsByFireStationNumber(@RequestParam(name = "stationNumber") int stationNumber) {

		Output output = outputService.findPersonsByFireStationNumber(stationNumber);

		if (output.getPersons() != null && !output.getPersons().isEmpty()) {

			Filter filter = new Filter();

			return filter.jsonFilter(output, 1);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "There are no persons served by the fire station number : " + stationNumber);
		}

	}

	/**
	 * This method call the outputService to find children by address.
	 * 
	 * @param address represent the address where one or many children live.
	 * @return a MappingJacksonValue containing the filtered output data.
	 */
	@GetMapping(value = "/childAlert")
	public MappingJacksonValue getChildrenByAddress(@RequestParam(name = "address") String address) {

		Output output = outputService.findChildrenByAddress(address);

		if (output.getChildren() != null && !output.getChildren().isEmpty()) {

			Filter filter = new Filter();

			return filter.jsonFilter(output, 0);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, address.isEmpty() ? "You didn't provide an address"
					: "There are no children living at this address : " + address);
		}

	}

	/**
	 * This method call the outputService to find phone numbers by fire station number.
	 * 
	 * @param firestation represent the station number of a fire station.
	 * @return a MappingJacksonValue containing the filtered output data.
	 */
	@GetMapping(value = "/phoneAlert")
	public MappingJacksonValue getPhoneNumbersByFireStationNumber(@RequestParam(name = "firestation") int firestation) {

		Output output = outputService.findPhoneNumbersByFireStationNumber(firestation);

		if (output.getPhoneNumbers() != null && !output.getPhoneNumbers().isEmpty()) {

			Filter filter = new Filter();

			return filter.jsonFilter(output, 0);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "There are no phone numbers registered by the fire station number : " + firestation);
		}

	}

	/**
	 * This method call the outputService to find persons by address.
	 * 
	 * @param address represent the address where one or many persons live.
	 * @return a MappingJacksonValue containing the filtered output data.
	 */
	@GetMapping(value = "/fire")
	public MappingJacksonValue getPersonsByAddress(@RequestParam(name = "address") String address) {

		Output output = outputService.findPersonsByAddress(address);

		if (output.getPersons() != null && !output.getPersons().isEmpty()) {

			Filter filter = new Filter();

			return filter.jsonFilter(output, 2);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, address.isEmpty() ? "You didn't provide an address"
					: "There are no persons living at this address : " + address);
		}

	}

	/**
	 * This method call the outputService to find persons by fire station numbers.
	 * 
	 * @param stations represent a list that contain one or many fire station numbers.
	 * @return a MappingJacksonValue containing the filtered output data.
	 */
	@GetMapping(value = "/flood/stations")
	public MappingJacksonValue getPersonsByFireStationNumbers(@RequestParam(name = "stations") List<Integer> stations) {

		Output output = outputService.findPersonsByFireStationNumbers(stations);

		if (output.getPersonsGroupedByAddress() != null && !output.getPersonsGroupedByAddress().isEmpty()) {

			Filter filter = new Filter();

			return filter.jsonFilter(output, 2);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					(stations.isEmpty() ? "You didn't provide any fire station number"
							: "There are no persons served by these fire station numbers : " + stations));
		}

	}

	/**
	 * This method call the outputService to find one or many persons by first and last name.
	 * 
	 * @param firstName represent the first name of a person.
	 * @param lastName  represent the last name of a person.
	 * @return a MappingJacksonValue containing the filtered output data.
	 */
	@GetMapping(value = "/personInfo")
	public MappingJacksonValue getPersonByFirstAndLastName(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {

		Output output = outputService.findPersonByFirstAndLastName(firstName, lastName);

		if (output.getPersons() != null && !output.getPersons().isEmpty()) {

			Filter filter = new Filter();

			return filter.jsonFilter(output, 0);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no person with the firstname : " + (firstName.isEmpty() ? "\"null value\"" : firstName)
							+ " and the lastname " + (lastName.isEmpty() ? "\"null value\"" : lastName)
							+ " in the list");
		}

	}

	/**
	 * This method call the outputService to find emails by city.
	 * 
	 * @param city represent the city where one or many persons live.
	 * @return a MappingJacksonValue containing the filtered output data.
	 */
	@GetMapping(value = "/communityEmail")
	public MappingJacksonValue getEmailsByCity(@RequestParam(name = "city") String city) {

		Output output = outputService.findEmailsByCity(city);

		if (output.getEmails() != null && !output.getEmails().isEmpty()) {

			Filter filter = new Filter();

			return filter.jsonFilter(output, 0);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					city.isEmpty() ? "You didn't provide a city name" : "There are no emails for this city : " + city);
		}

	}

}
