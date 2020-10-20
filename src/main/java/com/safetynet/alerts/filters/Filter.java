package com.safetynet.alerts.filters;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.Output;

public class Filter {

	/**
	 * This method filter the json data passed to it.
	 * 
	 * @param output is the list containing the output data.
	 * @param option represent which filter option to use.
	 * @return a MappingJacksonValue containing the filtered output data.
	 */
	public MappingJacksonValue jsonFilter(Output output, int option) {

		Output data = output;
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
