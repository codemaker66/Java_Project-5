package com.safetynet.alerts.integration;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.safetynet.alerts.model.FireStation;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FireStationControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Autowired
	private ObjectMapper objectMapper;

	private HttpHeaders headers = new HttpHeaders();

	@Test
	@Order(1)
	void retrieveAllFireStationsFromTheList() throws JsonProcessingException {

		// Given
		List<FireStation> fireStations = Data.instance().getFireStations();

		// When
		String URL = "http://localhost:" + port + "/firestations";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = objectMapper.writeValueAsString(fireStations);
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	@Order(2)
	void addAFireStationToTheList() {

		// Given
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address number 01");
		fireStation.setStation(6);

		// When
		String URL = "http://localhost:" + port + "/firestation";
		HttpEntity<FireStation> entity = new HttpEntity<FireStation>(fireStation, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

		// Then
		String expected = "The fire station was added to the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

	}

	@Test
	@Order(3)
	void updateAFireStationInTheList() {

		// Given
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address number 01");
		fireStation.setStation(9);

		// When
		String URL = "http://localhost:" + port + "/firestation";
		HttpEntity<FireStation> entity = new HttpEntity<FireStation>(fireStation, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);

		// Then
		String expected = "The fire station was updated in the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	@Order(4)
	void deleteAFireStationFromTheList() {

		// When
		String URL = "http://localhost:" + port + "/firestation?station=9&address=address number 01";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.DELETE, entity, String.class);

		// Then
		String expected = "The fire station was deleted from the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
