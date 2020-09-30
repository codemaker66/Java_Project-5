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

	@GetMapping(value = "/firestation")
	public MappingJacksonValue getPersonsByFireStationNumber(@RequestParam(name = "stationNumber") int stationNumber) {

		Output output = outputService.findPersonsByFireStationNumber(stationNumber);

		if (output.getPersons() != null && !output.getPersons().isEmpty()) {

			Filter filter = new Filter();

			return filter.jsonFilter(output, 1);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no persons served by the firestation number : " + stationNumber);
		}

	}

	@GetMapping(value = "/childAlert")
	public MappingJacksonValue getChildrensByAddress(@RequestParam(name = "address") String address) {

		Output output = outputService.findChildrensByAddress(address);

		if (output.getChildrens() != null && !output.getChildrens().isEmpty()) {

			Filter filter = new Filter();

			return filter.jsonFilter(output, 0);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, address.isEmpty() ? "You didn't provide an address"
					: "There are no childrens living at this address : " + address);
		}

	}

	@GetMapping(value = "/phoneAlert")
	public MappingJacksonValue getPhoneNumbersByFireStationNumber(@RequestParam(name = "firestation") int firestation) {

		Output output = outputService.findPhoneNumbersByFireStationNumber(firestation);

		if (output.getPhoneNumbers() != null && !output.getPhoneNumbers().isEmpty()) {

			Filter filter = new Filter();

			return filter.jsonFilter(output, 0);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no phone numbers registered by the fire station number : " + firestation);
		}

	}

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

	@GetMapping(value = "/flood/stations")
	public MappingJacksonValue getPersonsByFireStationNumbers(@RequestParam(name = "stations") List<Integer> stations) {

		Output output = outputService.findPersonsByFireStationNumbers(stations);

		if (output.getPersonsGrouped() != null && !output.getPersonsGrouped().isEmpty()) {

			Filter filter = new Filter();

			return filter.jsonFilter(output, 2);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					(stations.isEmpty() ? "You didn't provide any station number"
							: "There are no persons served by these firestation numbers : " + stations));
		}

	}

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
