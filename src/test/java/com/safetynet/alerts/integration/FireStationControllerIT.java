package com.safetynet.alerts.integration;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.FireStationController;
import com.safetynet.alerts.model.FireStation;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(FireStationController.class)
class FireStationControllerIT {	
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Test
	@Order(1)
	void addAFireStationToTheList() throws Exception {
		
		FireStation fireStation = new FireStation();

		fireStation.setAddress("address number 01");
		fireStation.setStation(6);
		
		mockMvc.perform(
				post("/firestation").contentType("application/json").content(objectMapper.writeValueAsString(fireStation)))
				.andExpect(status().isCreated()).andExpect(content().string("The firestation was added to the list"));

	}
	
	@Test
	@Order(2)
	void addTheSameFireStationToTheList() throws Exception {

		FireStation fireStation = new FireStation();

		fireStation.setAddress("address number 01");
		fireStation.setStation(6);

		mockMvc.perform(
				post("/firestation").contentType("application/json").content(objectMapper.writeValueAsString(fireStation)))
				.andExpect(status().isBadRequest()).andExpect(content().string("A firestation with the same address already exist"));
	}
	
	@Test
	@Order(3)
	void forgetAPropertyWhenAddingAFireStationToTheList() throws Exception {

		FireStation fireStation = new FireStation();

		fireStation.setAddress("");
		fireStation.setStation(6);

		mockMvc.perform(
				post("/firestation").contentType("application/json").content(objectMapper.writeValueAsString(fireStation)))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"message\":\"Validation failed\",\"details\":[\"address must have at least five characters\"]}"));
	}
	
	@Test
	@Order(4)
	void updateAFireStationInTheList() throws Exception {

		FireStation fireStation = new FireStation();

		fireStation.setAddress("address number 01");
		fireStation.setStation(9);

		mockMvc.perform(
				put("/firestation").contentType("application/json").content(objectMapper.writeValueAsString(fireStation)))
				.andExpect(status().isOk()).andExpect(content().string("The firestation was updated in the list"));
		
	}
	
	@Test
	@Order(5)
	void updateAFireStationThatCantBeFoundInTheList() throws Exception {

		FireStation fireStation = new FireStation();

		fireStation.setAddress("address number 99");
		fireStation.setStation(9);

		mockMvc.perform(
				put("/firestation").contentType("application/json").content(objectMapper.writeValueAsString(fireStation)))
				.andExpect(status().isNotFound()).andExpect(content().string("This firestation does not exist in the list"));
	}
	
	@Test
	@Order(6)
	void forgetAPropertyWhenUpdatingAFireStationInTheList() throws Exception {

		FireStation fireStation = new FireStation();

		fireStation.setAddress("");
		fireStation.setStation(9);

		mockMvc.perform(
				put("/firestation").contentType("application/json").content(objectMapper.writeValueAsString(fireStation)))
				.andExpect(status().isBadRequest()).andExpect(content().string("{\"message\":\"Validation failed\",\"details\":[\"address must have at least five characters\"]}"));
	}
	
	@Test
	@Order(7)
	void deleteAFireStationFromTheList() throws Exception {

		mockMvc.perform(
				delete("/firestation?station=9&address=address number 01"))
				.andExpect(status().isOk()).andExpect(content().string("The firestation was deleted from the list"));
	}
	
	@Test
	@Order(8)
	void deleteAFireStationThatDontExistInTheList() throws Exception {

		mockMvc.perform(
				delete("/firestation?station=9&address=address number 99"))
				.andExpect(status().isNotFound()).andExpect(content().string("There are no firestation with the station : 9 and the address : address number 99 in the list"));
	}

}
