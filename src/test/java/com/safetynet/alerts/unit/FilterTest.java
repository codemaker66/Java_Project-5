package com.safetynet.alerts.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.filters.Filter;
import com.safetynet.alerts.model.Output;

@WebMvcTest(Filter.class)
class FilterTest {

	@Autowired
	private Filter filter;

	@Test
	void filterTheAge() {

		// Given
		Output person = new Output();
		person.setFirstName("Lily");
		person.setLastName("Cooper");
		person.setAddress("489 Manchester St");
		person.setPhone("841-874-9845");
		person.setAge(26);

		List<Output> list = new ArrayList<>();
		list.add(person);

		Output data = new Output();
		data.setPersons(list);
		data.setAdultsCount(1);

		// When
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("age");

		FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", simpleBeanPropertyFilter);

		MappingJacksonValue value = new MappingJacksonValue(data);

		value.setFilters(filterList);

		// Then
		MappingJacksonValue expected = filter.jsonFilter(data, 1);
		assertThat(value).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void filterTheFireStationNumber() {

		// Given
		List<String> emptyList = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		medications.add("tradoxidine:400mg");

		Output person = new Output();
		person.setLastName("Cadigan");
		person.setPhone("841-874-7458");
		person.setAge(75);
		person.setMedications(medications);
		person.setAllergies(emptyList);
		person.setFireStationNumber(2);

		List<Output> list = new ArrayList<>();
		list.add(person);

		Output data = new Output();
		data.setFireStation("This person is served by the firestaion number : 2");
		data.setPersons(list);

		// When
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("fireStationNumber");

		FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", simpleBeanPropertyFilter);

		MappingJacksonValue value = new MappingJacksonValue(data);

		value.setFilters(filterList);

		// Then
		MappingJacksonValue expected = filter.jsonFilter(data, 2);
		assertThat(value).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void noFilter() {

		// Given
		Output phone = new Output();
		phone.setPhone("841-874-9845");

		List<Output> list = new ArrayList<>();
		list.add(phone);

		Output data = new Output();
		data.setPhoneNumbers(list);

		// When
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAll();

		FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", simpleBeanPropertyFilter);

		MappingJacksonValue value = new MappingJacksonValue(data);

		value.setFilters(filterList);

		// Then
		MappingJacksonValue expected = filter.jsonFilter(data, 0);
		assertThat(value).usingRecursiveComparison().isEqualTo(expected);
	}

}
