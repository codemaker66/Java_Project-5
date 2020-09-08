package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@RestController
public class PersonController {

	@GetMapping(value = "/person")
	public MappingJacksonValue get() {

		PersonService personService = new PersonService();

		List<Person> persons = personService.getAllPersons();

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();

		FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", filter);

		MappingJacksonValue personsFilter = new MappingJacksonValue(persons);

		personsFilter.setFilters(filterList);

		return personsFilter;
	}

	@PostMapping(value = "/person")
	public void post(@RequestBody Person person) {

		PersonService personService = new PersonService();

		personService.addAPerson(person);

	}

	@PutMapping(value = "/person")
	public void put(@RequestBody Person person) {

		PersonService personService = new PersonService();

		personService.updateAPerson(person);

	}

	@DeleteMapping(value = "/person")
	public void delete(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {

		PersonService personService = new PersonService();

		personService.deleteAPerson(firstName, lastName);

	}

}
