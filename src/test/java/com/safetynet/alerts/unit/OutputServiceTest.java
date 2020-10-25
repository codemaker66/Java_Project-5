package com.safetynet.alerts.unit;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.safetynet.alerts.dao.OutputDao;
import com.safetynet.alerts.model.Output;
import com.safetynet.alerts.service.OutputService;

@WebMvcTest(OutputService.class)
class OutputServiceTest {

	@MockBean
	private OutputDao outputDaoImpl;

	@Autowired
	private OutputService outputService;

	@Test
	void findPersonsByFireStationNumber() {

		// Given
		Output person = new Output();
		person.setFirstName("Lily");
		person.setLastName("Cooper");
		person.setAddress("489 Manchester St");
		person.setPhone("841-874-9845");
		person.setAge(26);

		List<Output> list = new ArrayList<>();
		list.add(person);

		Output data = new Output();
		data.setPersons(list);
		data.setAdultsCount(1);

		// When
		Mockito.when(outputDaoImpl.retrievePersonsByFireStationNumber(Mockito.anyInt())).thenReturn(list);

		// Then
		Output expected = outputService.findPersonsByFireStationNumber(6);
		assertThat(expected).isEqualToComparingFieldByField(data);
	}

	@Test
	void findChildrenByAddress() {

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
		Mockito.when(outputDaoImpl.retrieveChildrenByAddress(Mockito.anyString())).thenReturn(list);

		// Then
		Output expected = outputService.findChildrenByAddress("address");
		assertThat(expected).isEqualToComparingFieldByField(data);

	}

	@Test
	void findPhoneNumbersByFireStationNumber() {

		// Given
		Output phone = new Output();
		phone.setPhone("841-874-9845");

		List<Output> list = new ArrayList<>();
		list.add(phone);

		Output data = new Output();
		data.setPhoneNumbers(list);

		// When
		Mockito.when(outputDaoImpl.retrievePhoneNumbersByFireStationNumber(Mockito.anyInt())).thenReturn(list);

		// Then
		Output expected = outputService.findPhoneNumbersByFireStationNumber(10);
		assertThat(expected).isEqualToComparingFieldByField(data);
	}

	@Test
	void findPersonsByAddress() {

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
		person.setFireStationNumber(2);

		List<Output> list = new ArrayList<>();
		list.add(person);

		Output data = new Output();
		data.setFireStation("This person is served by the firestaion number : 2");
		data.setPersons(list);

		// When
		Mockito.when(outputDaoImpl.retrievePersonsByAddress(Mockito.anyString())).thenReturn(list);

		// Then
		Output expected = outputService.findPersonsByAddress("address");
		assertThat(expected).isEqualToComparingFieldByField(data);
	}

	@Test
	void findPersonsByFireStationNumbers() {

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

		List<Output> persons = new ArrayList<>();

		persons.add(person);
		persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		persons.add(person5);
		persons.add(person6);

		List<List<Output>> allLists = new ArrayList<>();
		allLists.add(list);
		allLists.add(list2);
		allLists.add(list3);
		allLists.add(list4);

		Output data = new Output();
		data.setPersonsGroupedByAddress(allLists);

		// When
		Mockito.when(outputDaoImpl.retrievePersonsByFireStationNumbers(Mockito.anyList())).thenReturn(persons);

		// Then
		List<Integer> listOfInteger = Arrays.asList(1, 2, 3);
		Output expected = outputService.findPersonsByFireStationNumbers(listOfInteger);
		assertThat(expected).isEqualToComparingFieldByField(data);

	}

	@Test
	void findPersonByFirstAndLastName() {

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
		Mockito.when(outputDaoImpl.retrievePersonByFirstAndLastName(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(list);

		// Then
		Output expected = outputService.findPersonByFirstAndLastName("Foster", "Shepard");
		assertThat(expected).isEqualToComparingFieldByField(data);

	}

	@Test
	void findEmailsByCity() {

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
		Mockito.when(outputDaoImpl.retrieveEmailsByCity(Mockito.anyString())).thenReturn(list);

		// Then
		Output expected = outputService.findEmailsByCity("City");
		assertThat(expected).isEqualToComparingFieldByField(data);

	}

}
