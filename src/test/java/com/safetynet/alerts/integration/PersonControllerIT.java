package com.safetynet.alerts.integration;

import org.junit.jupiter.api.Test;
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
import com.safetynet.alerts.model.Person;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Autowired
	private ObjectMapper objectMapper;

	private HttpHeaders headers = new HttpHeaders();

	@Test
	@Order(1)
	void retrieveAllPersonsFromTheList() throws JsonProcessingException {

		// Given
		List<Person> persons = Data.instance().getPersons();

		// When
		String URL = "http://localhost:" + port + "/persons";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = objectMapper.writeValueAsString(persons);
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	@Order(2)
	void addAPersonToTheList() {

		// Given
		Person person = new Person();
		person.setFirstName("Jo");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6512");
		person.setEmail("jaboyd@email.com");

		// When
		String URL = "http://localhost:" + port + "/person";
		HttpEntity<Person> entity = new HttpEntity<Person>(person, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

		// Then
		String expected = "The person was added to the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	@Order(3)
	void addTheSamePersonToTheList() {

		// Given
		Person person = new Person();
		person.setFirstName("Jo");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6512");
		person.setEmail("jaboyd@email.com");

		// When
		String URL = "http://localhost:" + port + "/person";
		HttpEntity<Person> entity = new HttpEntity<Person>(person, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

		// Then
		String expected = "A person with the same first and last name already exist in the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	@Order(4)
	void forgetAPropertyWhenAddingAPersonToTheList() {

		// Given
		Person person = new Person();
		person.setFirstName("");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6512");
		person.setEmail("jaboyd@email.com");

		// When
		String URL = "http://localhost:" + port + "/person";
		HttpEntity<Person> entity = new HttpEntity<Person>(person, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

		// Then
		String expected = "{\"message\":\"validation failed\",\"details\":[\"firstName must have at least one character\"]}";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	@Order(5)
	void updateAPersonInTheList() {

		// Given
		Person person = new Person();
		person.setFirstName("Jo");
		person.setLastName("Boyd");
		person.setAddress("1510 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6522");
		person.setEmail("jaboydja@email.com");

		// When
		String URL = "http://localhost:" + port + "/person";
		HttpEntity<Person> entity = new HttpEntity<Person>(person, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);

		// Then
		String expected = "The person was updated in the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	@Order(6)
	void updateAPersonThatCantBeFoundInTheList() {

		// Given
		Person person = new Person();
		person.setFirstName("Joe");
		person.setLastName("Boy");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6512");
		person.setEmail("jaboyd@email.com");

		// When
		String URL = "http://localhost:" + port + "/person";
		HttpEntity<Person> entity = new HttpEntity<Person>(person, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);

		// Then
		String expected = "This person does not exist in the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	@Order(7)
	void forgetAPropertyWhenUpdatingAPersonInTheList() {

		// Given
		Person person = new Person();
		person.setFirstName("Jo");
		person.setLastName("");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6512");
		person.setEmail("jaboyd@email.com");

		// When
		String URL = "http://localhost:" + port + "/person";
		HttpEntity<Person> entity = new HttpEntity<Person>(person, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);

		// Then
		String expected = "{\"message\":\"validation failed\",\"details\":[\"lastName must have at least one character\"]}";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	@Order(8)
	void deleteAPersonFromTheList() {

		// When
		String URL = "http://localhost:" + port + "/person?firstName=Jo&lastName=Boyd";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.DELETE, entity, String.class);

		// Then
		String expected = "The person was deleted from the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	@Order(9)
	void deleteAPersonThatDontExistInTheList() {

		// When
		String URL = "http://localhost:" + port + "/person?firstName=Joe&lastName=Boy";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.DELETE, entity, String.class);

		// Then
		String expected = "There are no person with the first name : Joe and the last name : Boy in the list";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

	}

}
