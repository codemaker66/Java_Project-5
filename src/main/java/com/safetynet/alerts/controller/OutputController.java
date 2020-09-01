package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.dao.OutputDaoImpl;
import com.safetynet.alerts.model.Person;

@RestController
public class OutputController {
	
	  @GetMapping(value = "/firestation")
	  public MappingJacksonValue getPersonsByFireStationNumber(@RequestParam(name = "stationNumber") String stationNumber) {
		  
		  OutputDaoImpl output = new OutputDaoImpl();
	      
	      List<Object> persons = output.findPersonsByFireStationNumber(stationNumber);

	      SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName",
	    		  "lastName", "address", "phone");

	      FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", filter);

	      MappingJacksonValue personsFilter = new MappingJacksonValue(persons);

	      personsFilter.setFilters(filterList);

	      return personsFilter;
	  }
	  
	  @GetMapping(value = "/childAlert")
	  public MappingJacksonValue getChildrensByAddress(@RequestParam(name = "address") String address) {
		  
		  OutputDaoImpl output = new OutputDaoImpl();
		  
		  List<Person> persons = output.findChildrensByAddress(address);
		  
		  SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName", 
				  "lastName", "age", "famillyMembers");

	      FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", filter);

	      MappingJacksonValue personsFilter = new MappingJacksonValue(persons);

	      personsFilter.setFilters(filterList);

	      return personsFilter;
		  
	  }
	  
	  @GetMapping(value = "/phoneAlert")
	  public MappingJacksonValue getPhoneNumbersByFireStationNumber(@RequestParam(name = "firestation") String firestation) {
		  
		  OutputDaoImpl output = new OutputDaoImpl();
		  
		  List<Person> persons = output.findPhoneNumbersByFireStationNumber(firestation);
		  
		  SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("phone");

	      FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", filter);

	      MappingJacksonValue personsFilter = new MappingJacksonValue(persons);

	      personsFilter.setFilters(filterList);

	      return personsFilter;
		  
	  }
	  
	  @GetMapping(value = "/fire")
	  public MappingJacksonValue getPersonsByAddress(@RequestParam(name = "address") String address) {
		  
		  OutputDaoImpl output = new OutputDaoImpl();
		  
		  List<Object> persons = output.findPersonsByAddress(address);
		  
		  SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("lastName"
				  , "phone", "age", "medications", "allergies");

	      FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", filter);

	      MappingJacksonValue personsFilter = new MappingJacksonValue(persons);

	      personsFilter.setFilters(filterList);

	      return personsFilter;
		  
	  }
	  
	  @GetMapping(value = "/flood/stations")
	  public MappingJacksonValue getPersonsByFireStationNumbers(@RequestParam(name = "stations") List<String> stations) {
		  
		  OutputDaoImpl output = new OutputDaoImpl();
		  
		  List<Object> persons = output.findPersonsByFireStationNumbers(stations);
		  
		  SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("lastName",
				  "phone", "age", "medications", "allergies");

	      FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", filter);

	      MappingJacksonValue personsFilter = new MappingJacksonValue(persons);

	      personsFilter.setFilters(filterList);

	      return personsFilter;
		  
	  }
	  
	  @GetMapping(value = "/personInfo")
	  public MappingJacksonValue getPersonByFirstAndLastName(@RequestParam(name = "lastName") String lastName) {
		  
		  OutputDaoImpl output = new OutputDaoImpl();
		  
		  List<Person> persons = output.findPersonByFirstAndLastName(lastName);
		  
		  SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("lastName", 
				  "address", "age", "email", "medications", "allergies");

	      FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", filter);

	      MappingJacksonValue personsFilter = new MappingJacksonValue(persons);

	      personsFilter.setFilters(filterList);

	      return personsFilter;
		  
	  }
	  
	  @GetMapping(value = "/communityEmail")
	  public MappingJacksonValue findEmailsByCity(@RequestParam(name = "city") String city) {
		  
		  OutputDaoImpl output = new OutputDaoImpl();
		  
		  List<Person> persons = output.findEmailsByCity(city);
		  
		  SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("email");

	      FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", filter);

	      MappingJacksonValue personsFilter = new MappingJacksonValue(persons);

	      personsFilter.setFilters(filterList);

	      return personsFilter;
		  
	  }

}
