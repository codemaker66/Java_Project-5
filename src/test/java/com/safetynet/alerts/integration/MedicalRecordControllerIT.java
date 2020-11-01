package com.safetynet.alerts.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.MedicalRecord;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MedicalRecordControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Autowired
	private ObjectMapper objectMapper;

	private HttpHeaders headers = new HttpHeaders();

	@Test
	@Order(1)
	void retrieveAllMedicalRecordsFromTheList() throws JsonProcessingException {

		// Given
		List<MedicalRecord> medicalRecords = Data.instance().getMedicalRecords();

		// When
		String URL = "http://localhost:" + port + "/medicalRecords";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = objectMapper.writeValueAsString(medicalRecords);
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	@Order(2)
	void addAMedicalRecordToTheList() {

		// Given
		MedicalRecord medicalRecord = new MedicalRecord();
		List<String> medications = Arrays.asList("medication1", "medication2");
		List<String> allergies = Arrays.asList("allergy1", "allergy2");
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/02/1980");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		// When
		String URL = "http://localhost:" + port + "/medicalRecord";
		HttpEntity<MedicalRecord> entity = new HttpEntity<MedicalRecord>(medicalRecord, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

		// Then
		String expected = "The medical record was added to the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	@Order(3)
	void giveAWrongBirthDateWhenAddingAMedicalRecordToTheList() {

		// Given
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/02/3000");

		// When
		String URL = "http://localhost:" + port + "/medicalRecord";
		HttpEntity<MedicalRecord> entity = new HttpEntity<MedicalRecord>(medicalRecord, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

		// Then
		String expected = "The date provided is null or not valid, must be of format : \"dd/MM/yyyy\"";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	@Order(4)
	void updateAMedicalRecordInTheList() {

		// Given
		MedicalRecord medicalRecord = new MedicalRecord();
		List<String> medications = Arrays.asList("medication1");
		List<String> allergies = Arrays.asList("allergy1");
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/07/1980");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		// When
		String URL = "http://localhost:" + port + "/medicalRecord";
		HttpEntity<MedicalRecord> entity = new HttpEntity<MedicalRecord>(medicalRecord, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);

		// Then
		String expected = "The medical record was updated in the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	@Order(5)
	void deleteAMedicalRecordFromTheList() {

		// When
		String URL = "http://localhost:" + port + "/medicalRecord?firstName=Jo&lastName=Boyd";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.DELETE, entity, String.class);

		// Then
		String expected = "The medical record was deleted from the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

}
