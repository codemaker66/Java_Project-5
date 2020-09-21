package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.alerts.exceptions.ResourceException;
import com.safetynet.alerts.filters.Filter;
import com.safetynet.alerts.model.Response;
import com.safetynet.alerts.service.ResponseService;

@RestController
public class ResponseController {

	@GetMapping(value = "/firestation")
	public MappingJacksonValue getPersonsByFireStationNumber(@RequestParam(name = "stationNumber") int stationNumber) {

		ResponseService responseService = new ResponseService();

		Response response = responseService.findPersonsByFireStationNumber(stationNumber);

		if (response.getPersons() != null && !response.getPersons().isEmpty()) {

			Filter filter = new Filter();

			return filter.JsonFilter(response, 1);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no persons served by the firestation number : " + stationNumber);
		}

	}

	@GetMapping(value = "/childAlert")
	public MappingJacksonValue getChildrensByAddress(@RequestParam(name = "address") String address) {

		ResponseService responseService = new ResponseService();

		Response response = responseService.findChildrensByAddress(address);

		if (response.getChildrens() != null && !response.getChildrens().isEmpty()) {

			Filter filter = new Filter();

			return filter.JsonFilter(response, 0);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, address.isEmpty() ? "You didn't provide an address"
					: "There are no childrens living at this address : " + address);
		}

	}

	@GetMapping(value = "/phoneAlert")
	public MappingJacksonValue getPhoneNumbersByFireStationNumber(@RequestParam(name = "firestation") int firestation) {

		ResponseService responseService = new ResponseService();

		Response response = responseService.findPhoneNumbersByFireStationNumber(firestation);

		if (response.getPhoneNumbers() != null && !response.getPhoneNumbers().isEmpty()) {

			Filter filter = new Filter();

			return filter.JsonFilter(response, 0);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no phone numbers registered by the fire station number : " + firestation);
		}

	}

	@GetMapping(value = "/fire")
	public MappingJacksonValue getPersonsByAddress(@RequestParam(name = "address") String address) {

		ResponseService responseService = new ResponseService();

		Response response = responseService.findPersonsByAddress(address);

		if (response.getPersons() != null && !response.getPersons().isEmpty()) {

			Filter filter = new Filter();

			return filter.JsonFilter(response, 2);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, address.isEmpty() ? "You didn't provide an address"
					: "There are no persons living at this address : " + address);
		}

	}

	@GetMapping(value = "/flood/stations")
	public MappingJacksonValue getPersonsByFireStationNumbers(@RequestParam(name = "stations") List<Integer> stations) {

		ResponseService responseService = new ResponseService();

		Response response = responseService.findPersonsByFireStationNumbers(stations);

		if (response.getPersonsGrouped() != null && !response.getPersonsGrouped().isEmpty()) {

			Filter filter = new Filter();

			return filter.JsonFilter(response, 3);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no persons served by these firestation numbers : " + stations);
		}

	}

	@GetMapping(value = "/personInfo")
	public MappingJacksonValue getPersonByFirstAndLastName(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {

		ResponseService responseService = new ResponseService();

		Response response = responseService.findPersonByFirstAndLastName(firstName, lastName);

		if (response.getPersons() != null && !response.getPersons().isEmpty()) {

			Filter filter = new Filter();

			return filter.JsonFilter(response, 0);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no person with the firstname : " + (firstName.isEmpty() ? "\"null value\"" : firstName)
							+ " and the lastname " + (lastName.isEmpty() ? "\"null value\"" : lastName)
							+ " in the list");
		}

	}

	@GetMapping(value = "/communityEmail")
	public MappingJacksonValue getEmailsByCity(@RequestParam(name = "city") String city) {

		ResponseService responseService = new ResponseService();

		Response response = responseService.findEmailsByCity(city);

		if (response.getEmails() != null && !response.getEmails().isEmpty()) {

			Filter filter = new Filter();

			return filter.JsonFilter(response, 0);

		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					city.isEmpty() ? "You didn't provide a city name" : "There are no emails for this city : " + city);
		}

	}

}
