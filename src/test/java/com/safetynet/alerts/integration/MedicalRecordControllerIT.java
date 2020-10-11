package com.safetynet.alerts.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.MedicalRecordController;
import com.safetynet.alerts.model.MedicalRecord;

@WebMvcTest(MedicalRecordController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MedicalRecordControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	void addAMedicalRecordToTheList() throws Exception {

		MedicalRecord medicalRecord = new MedicalRecord();
		
		List<String> medications = Arrays.asList("medication1", "medication2");
		List<String> allergies = Arrays.asList("allergy1", "allergy2");

		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/02/1980");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		mockMvc.perform(
				post("/medicalRecord").contentType("application/json").content(objectMapper.writeValueAsString(medicalRecord)))
				.andExpect(status().isCreated()).andExpect(content().string("The medicalrecord was added to the list"));
	}
	
	@Test
	@Order(2)
	void addTheSameMedicalRecordToTheList() throws Exception {

		MedicalRecord medicalRecord = new MedicalRecord();
		
		List<String> medications = Arrays.asList("medication1", "medication2");
		List<String> allergies = Arrays.asList("allergy1", "allergy2");

		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/02/1980");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		mockMvc.perform(
				post("/medicalRecord").contentType("application/json").content(objectMapper.writeValueAsString(medicalRecord)))
				.andExpect(status().isBadRequest()).andExpect(content().string("A medicalrecord with the same first and lastname already exist"));
		
	}
	
	@Test
	@Order(3)
	void forgetAPropertyWhenAddingAMedicalRecordToTheList() throws Exception {

		MedicalRecord medicalRecord = new MedicalRecord();

		medicalRecord.setFirstName("");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/02/1980");

		mockMvc.perform(
				post("/medicalRecord").contentType("application/json").content(objectMapper.writeValueAsString(medicalRecord)))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"message\":\"Validation failed\",\"details\":[\"firstName must have at least one character\"]}"));
	}
	
	@Test
	@Order(4)
	void giveAWrongDateWhenAddingAMedicalRecordToTheList() throws Exception {

		MedicalRecord medicalRecord = new MedicalRecord();

		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/02/3000");

		mockMvc.perform(
				post("/medicalRecord").contentType("application/json").content(objectMapper.writeValueAsString(medicalRecord)))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("The date provided is null or not valid, must be of format : \"dd/MM/yyyy\""));
	}
	
	@Test
	@Order(5)
	void updateAMedicalRecordInTheList() throws Exception {

		MedicalRecord medicalRecord = new MedicalRecord();
		
		List<String> medications = Arrays.asList("medication1");
		List<String> allergies = Arrays.asList("allergy1");

		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/07/1980");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		mockMvc.perform(
				put("/medicalRecord").contentType("application/json").content(objectMapper.writeValueAsString(medicalRecord)))
				.andExpect(status().isOk()).andExpect(content().string("The medicalrecord was updated in the list"));
		
	}
	
	@Test
	@Order(6)
	void updateAMedicalRecordThatCantBeFoundInTheList() throws Exception {

		MedicalRecord medicalRecord = new MedicalRecord();

		medicalRecord.setFirstName("Joe");
		medicalRecord.setLastName("Boy");
		medicalRecord.setBirthdate("01/07/1980");

		mockMvc.perform(
				put("/medicalRecord").contentType("application/json").content(objectMapper.writeValueAsString(medicalRecord)))
				.andExpect(status().isNotFound()).andExpect(content().string("This medicalrecord does not exist in the list"));
	}
	
	@Test
	@Order(7)
	void forgetAPropertyWhenUpdatingAMedicalRecordInTheList() throws Exception {

		MedicalRecord medicalRecord = new MedicalRecord();
		
		List<String> medications = Arrays.asList("medication1");
		List<String> allergies = Arrays.asList("allergy1");

		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("");
		medicalRecord.setBirthdate("01/07/1980");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		mockMvc.perform(
				put("/medicalRecord").contentType("application/json").content(objectMapper.writeValueAsString(medicalRecord)))
				.andExpect(status().isBadRequest()).andExpect(content().string("{\"message\":\"Validation failed\",\"details\":[\"lastName must have at least one character\"]}"));
	}
	
	@Test
	@Order(8)
	void giveAWrongDateWhenUpdatingAMedicalRecordToTheList() throws Exception {

		MedicalRecord medicalRecord = new MedicalRecord();
		
		List<String> medications = Arrays.asList("medication1");
		List<String> allergies = Arrays.asList("allergy1");

		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/07/4000");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		mockMvc.perform(
				put("/medicalRecord").contentType("application/json").content(objectMapper.writeValueAsString(medicalRecord)))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("The date provided is null or not valid, must be of format : \"dd/MM/yyyy\""));
	}
	
	@Test
	@Order(9)
	void deleteAMedicalRecordFromTheList() throws Exception {

		mockMvc.perform(
				delete("/medicalRecord?firstName=Jo&lastName=Boyd"))
				.andExpect(status().isOk()).andExpect(content().string("The medicalrecord was deleted from the list"));
	}
	
	@Test
	@Order(10)
	void deleteAMedicalRecordThatDontExistInTheList() throws Exception {

		mockMvc.perform(
				delete("/medicalRecord?firstName=Joe&lastName=Boy"))
				.andExpect(status().isNotFound()).andExpect(content().string("There are no medicalrecord for a person with the firstname : Joe and the lastname : Boy in the list"));
	}

}
