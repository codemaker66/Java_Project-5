package com.safetynet.alerts.filters;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.Output;

public class Filter {

	public MappingJacksonValue jsonFilter(Output list, int option) {

		Output data = list;
		SimpleBeanPropertyFilter filter = null;

		switch (option) {
		case 1:
			filter = SimpleBeanPropertyFilter.serializeAllExcept("age");
			break;
		case 2:
			filter = SimpleBeanPropertyFilter.serializeAllExcept("fireStationNumber");
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
