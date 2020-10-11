package com.safetynet.alerts.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.model.Person;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@WebMvcTest(PersonController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	void addAPersonToTheList() throws Exception {

		Person person = new Person();

		person.setFirstName("Jo");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6512");
		person.setEmail("jaboyd@email.com");

		mockMvc.perform(
				post("/person").contentType("application/json").content(objectMapper.writeValueAsString(person)))
				.andExpect(status().isCreated()).andExpect(content().string("The person was added to the list"));
	}

	@Test
	@Order(2)
	void addTheSamePersonToTheList() throws Exception {

		Person person = new Person();

		person.setFirstName("Jo");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6512");
		person.setEmail("jaboyd@email.com");

		mockMvc.perform(
				post("/person").contentType("application/json").content(objectMapper.writeValueAsString(person)))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("A person with the same first and lastname already exist"));
	}

	@Test
	@Order(3)
	void forgetAPropertyWhenAddingAPersonToTheList() throws Exception {

		Person person = new Person();

		person.setFirstName("");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6512");
		person.setEmail("jaboyd@email.com");

		mockMvc.perform(
				post("/person").contentType("application/json").content(objectMapper.writeValueAsString(person)))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"message\":\"Validation failed\",\"details\":[\"firstName must have at least one character\"]}"));
	}
	
	@Test
	@Order(4)
	void updateAPersonInTheList() throws Exception {

		Person person = new Person();

		person.setFirstName("Jo");
		person.setLastName("Boyd");
		person.setAddress("1510 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6522");
		person.setEmail("jaboydja@email.com");

		mockMvc.perform(
				put("/person").contentType("application/json").content(objectMapper.writeValueAsString(person)))
				.andExpect(status().isOk()).andExpect(content().string("The person was updated in the list"));
	}
	
	@Test
	@Order(5)
	void updateAPersonThatCantBeFoundInTheList() throws Exception {

		Person person = new Person();

		person.setFirstName("Joe");
		person.setLastName("Boy");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6512");
		person.setEmail("jaboyd@email.com");

		mockMvc.perform(
				put("/person").contentType("application/json").content(objectMapper.writeValueAsString(person)))
				.andExpect(status().isNotFound()).andExpect(content().string("This person does not exist in the list"));
	}
	
	@Test
	@Order(6)
	void forgetAPropertyWhenUpdatingAPersonInTheList() throws Exception {

		Person person = new Person();

		person.setFirstName("Jo");
		person.setLastName("");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6512");
		person.setEmail("jaboyd@email.com");

		mockMvc.perform(
				put("/person").contentType("application/json").content(objectMapper.writeValueAsString(person)))
				.andExpect(status().isBadRequest()).andExpect(content().string("{\"message\":\"Validation failed\",\"details\":[\"lastName must have at least one character\"]}"));
	}
	
	@Test
	@Order(7)
	void deleteAPersonFromTheList() throws Exception {

		mockMvc.perform(
				delete("/person?firstName=Jo&lastName=Boyd"))
				.andExpect(status().isOk()).andExpect(content().string("The person was deleted from the list"));
	}
	
	@Test
	@Order(8)
	void deleteAPersonThatDontExistInTheList() throws Exception {

		mockMvc.perform(
				delete("/person?firstName=Joe&lastName=Boy"))
				.andExpect(status().isNotFound()).andExpect(content().string("There are no person with the firstname : Joe and the lastname : Boy in the list"));
	}

}
