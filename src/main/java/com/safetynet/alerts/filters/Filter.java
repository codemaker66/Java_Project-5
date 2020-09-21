package com.safetynet.alerts.filters;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.Response;

public class Filter {

	public MappingJacksonValue JsonFilter(Response list, int option) {

		Response data = list;
		SimpleBeanPropertyFilter filter = null;

		switch (option) {
		case 1:
			filter = SimpleBeanPropertyFilter.serializeAllExcept("age");
			break;
		case 2:
			filter = SimpleBeanPropertyFilter.serializeAllExcept("fireStationNumber", "address");
			break;
		case 3:
			filter = SimpleBeanPropertyFilter.serializeAllExcept("fireStationNumber", "firstName");
			break;
		default:
			filter = SimpleBeanPropertyFilter.serializeAll();
		}

		FilterProvider filterList = new SimpleFilterProvider().addFilter("DynamicFilter", filter);

		MappingJacksonValue dataFilter = new MappingJacksonValue(data);

		dataFilter.setFilters(filterList);

		return dataFilter;
	}

}
