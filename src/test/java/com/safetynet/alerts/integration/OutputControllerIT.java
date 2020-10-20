package com.safetynet.alerts.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.Output;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OutputControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Autowired
	private ObjectMapper objectMapper;

	private HttpHeaders headers = new HttpHeaders();

	@BeforeEach
	void noFilter() {
		objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
	}

	@Test
	void retrievePersonsByFireStationNumber() throws Exception {

		// Given
		Output person = new Output();
		person.setFirstName("Lily");
		person.setLastName("Cooper");
		person.setAddress("489 Manchester St");
		person.setPhone("841-874-9845");

		List<Output> list = new ArrayList<>();
		list.add(person);

		Output data = new Output();
		data.setPersons(list);
		data.setAdultsCount(1);

		// When
		String URL = "http://localhost:" + port + "/firestation?stationNumber=4";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = objectMapper.writeValueAsString(data);
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	void retrievePersonsWithANonExistentFireStationNumber() {

		// When
		String URL = "http://localhost:" + port + "/firestation?stationNumber=44";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = "There are no persons served by the fire station number : 44";
		assertThat(response.getBody()).isEqualTo(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

	}

	@Test
	void retrieveChildrenByAddress() throws Exception {

		// Given
		Output child = new Output();
		child.setFirstName("Zach");
		child.setLastName("Zemicks");
		child.setAge(3);

		Output mother = new Output();
		mother.setFirstName("Sophia");
		mother.setLastName("Zemicks");
		mother.setAge(32);

		Output father = new Output();
		father.setFirstName("Warren");
		father.setLastName("Zemicks");
		father.setAge(35);

		List<Output> famillyMembers = new ArrayList<>();
		famillyMembers.add(mother);
		famillyMembers.add(father);
		child.setFamillyMembers(famillyMembers);

		List<Output> list = new ArrayList<>();
		list.add(child);

		Output data = new Output();
		data.setChildren(list);

		// When
		String URL = "http://localhost:" + port + "/childAlert?address=892 Downing Ct";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = objectMapper.writeValueAsString(data);
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	void retrieveChildrenWithANonExistentAddress() {

		// When
		String URL = "http://localhost:" + port + "/childAlert?address=random address name";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = "There are no children living at this address : random address name";
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

	}

	@Test
	void retrievePhoneNumbersByFireStationNumber() throws Exception {

		// Given
		Output phone = new Output();
		phone.setPhone("841-874-9845");

		List<Output> list = new ArrayList<>();
		list.add(phone);

		Output data = new Output();
		data.setPhoneNumbers(list);

		// When
		String URL = "http://localhost:" + port + "/phoneAlert?firestation=4";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = objectMapper.writeValueAsString(data);
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void retrievePhoneNumbersWithANonExistentFireStationNumber() {

		// When
		String URL = "http://localhost:" + port + "/phoneAlert?firestation=44";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = "There are no phone numbers registered by the fire station number : 44";
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

	}

	@Test
	void retrievePersonsByAddress() throws Exception {

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

		List<Output> list = new ArrayList<>();
		list.add(person);

		Output data = new Output();
		data.setFireStation("This person is served by the firestaion number : 2");
		data.setPersons(list);

		// When
		String URL = "http://localhost:" + port + "/fire?address=951 LoneTree Rd";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = objectMapper.writeValueAsString(data);
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	void retrievePersonsWithoutAnAddress() {

		// When
		String URL = "http://localhost:" + port + "/fire?address=";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = "You didn't provide an address";
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

	}

	@Test
	void retrievePersonsByFireStationNumbers() throws Exception {

		// Given
		List<String> emptyList = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		medications.add("tradoxidine:400mg");

		Output person = new Output();
		person.setLastName("Cadigan");
		person.setAddress("951 LoneTree Rd");
		person.setPhone("841-874-7458");
		person.setAge(75);
		person.setMedications(medications);
		person.setAllergies(emptyList);

		List<Output> list = new ArrayList<>();
		list.add(person);

		Output person2 = new Output();
		person2.setLastName("Cooper");
		person2.setAddress("489 Manchester St");
		person2.setPhone("841-874-9845");
		person2.setAge(26);
		person2.setMedications(emptyList);
		person2.setAllergies(emptyList);

		List<Output> list2 = new ArrayList<>();
		list2.add(person2);

		List<String> medications2 = new ArrayList<>();
		medications2.add("aznol:60mg");
		medications2.add("hydrapermazol:900mg");
		medications2.add("pharmacol:5000mg");
		medications2.add("terazine:500mg");
		List<String> allergies = new ArrayList<>();
		allergies.add("peanut");
		allergies.add("shellfish");
		allergies.add("aznol");

		Output person3 = new Output();
		person3.setLastName("Zemicks");
		person3.setAddress("892 Downing Ct");
		person3.setPhone("841-874-7878");
		person3.setAge(32);
		person3.setMedications(medications2);
		person3.setAllergies(allergies);

		Output person4 = new Output();
		person4.setLastName("Zemicks");
		person4.setAddress("892 Downing Ct");
		person4.setPhone("841-874-7512");
		person4.setAge(35);
		person4.setMedications(emptyList);
		person4.setAllergies(emptyList);

		Output person5 = new Output();
		person5.setLastName("Zemicks");
		person5.setAddress("892 Downing Ct");
		person5.setPhone("841-874-7512");
		person5.setAge(3);
		person5.setMedications(emptyList);
		person5.setAllergies(emptyList);

		List<Output> list3 = new ArrayList<>();
		list3.add(person3);
		list3.add(person4);
		list3.add(person5);

		Output person6 = new Output();
		person6.setLastName("Marrack");
		person6.setAddress("29 15th St");
		person6.setPhone("841-874-6513");
		person6.setAge(31);
		person6.setMedications(emptyList);
		person6.setAllergies(emptyList);

		List<Output> list4 = new ArrayList<>();
		list4.add(person6);

		List<List<Output>> allLists = new ArrayList<>();
		allLists.add(list);
		allLists.add(list2);
		allLists.add(list3);
		allLists.add(list4);

		Output data = new Output();
		data.setPersonsGroupedByAddress(allLists);

		// When
		String URL = "http://localhost:" + port + "/flood/stations?stations=4,2";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = objectMapper.writeValueAsString(data);
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	void retrievePersonsWithNonExistentFireStationNumbers() {

		// When
		String URL = "http://localhost:" + port + "/flood/stations?stations=6,10";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = "There are no persons served by these fire station numbers : [6, 10]";
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

	}

	@Test
	void retrievePersonByFirstAndLastName() throws Exception {

		// Given
		List<String> emptyList = new ArrayList<>();
		Output person = new Output();
		person.setLastName("Shepard");
		person.setAddress("748 Townings Dr");
		person.setEmail("jaboyd@email.com");
		person.setAge(40);
		person.setMedications(emptyList);
		person.setAllergies(emptyList);

		List<Output> list = new ArrayList<>();
		list.add(person);

		Output data = new Output();
		data.setPersons(list);

		// When
		String URL = "http://localhost:" + port + "/personInfo?firstName=Foster&lastName=Shepard";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = objectMapper.writeValueAsString(data);
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	void retrievePersonByFirstAndLastNameWithErrors() {

		// When
		String URL = "http://localhost:" + port + "/personInfo?firstName=Foster&lastName=";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = "There are no person with the firstname : Foster and the lastname \"null value\" in the list";
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

	}

	@Test
	void retrieveEmailsByCity() throws Exception {

		// Given
		List<String> emails = Arrays.asList("jaboyd@email.com", "drk@email.com", "tenz@email.com", "jaboyd@email.com",
				"jaboyd@email.com", "drk@email.com", "tenz@email.com", "jaboyd@email.com", "jaboyd@email.com",
				"tcoop@ymail.com", "lily@email.com", "soph@email.com", "ward@email.com", "zarc@email.com",
				"reg@email.com", "jpeter@email.com", "jpeter@email.com", "aly@imail.com", "bstel@email.com",
				"ssanw@email.com", "bstel@email.com", "clivfd@ymail.com", "gramps@email.com");

		List<Output> list = new ArrayList<>();

		for (int i = 0; i < emails.size(); i++) {
			Output email = new Output();
			email.setEmail(emails.get(i));
			list.add(email);
		}

		Output data = new Output();
		data.setEmails(list);

		// When
		String URL = "http://localhost:" + port + "/communityEmail?city=Culver";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = objectMapper.writeValueAsString(data);
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	void retrieveEmailsWithANonExistenCity() {

		// When
		String URL = "http://localhost:" + port + "/communityEmail?city=random city name";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		// Then
		String expected = "There are no emails registered for this city : random city name";
		assertThat(response.getBody()).isEqualToIgnoringWhitespace(expected);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

	}

}
