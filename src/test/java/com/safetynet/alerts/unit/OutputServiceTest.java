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
		assertThat(data).isEqualToComparingFieldByField(expected);
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
		assertThat(data).isEqualToComparingFieldByField(expected);

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
		assertThat(data).isEqualToComparingFieldByField(expected);
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
		assertThat(data).isEqualToComparingFieldByField(expected);
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

		Output person3 = new Output();
		person3.setLastName("Marrack");
		person3.setAddress("29 15th St");
		person3.setPhone("841-874-6513");
		person3.setAge(31);
		person3.setMedications(emptyList);
		person3.setAllergies(emptyList);

		List<Output> list3 = new ArrayList<>();
		list3.add(person3);

		List<Output> persons = new ArrayList<>();

		persons.add(person);
		persons.add(person2);
		persons.add(person3);

		List<List<Output>> allLists = new ArrayList<>();
		allLists.add(list);
		allLists.add(list2);
		allLists.add(list3);

		Output data = new Output();
		data.setPersonsGroupedByAddress(allLists);

		// When
		Mockito.when(outputDaoImpl.retrievePersonsByFireStationNumbers(Mockito.anyList())).thenReturn(persons);

		// Then
		List<Integer> listOfInteger = Arrays.asList(1, 2, 3);
		Output expected = outputService.findPersonsByFireStationNumbers(listOfInteger);
		assertThat(data).isEqualToComparingFieldByField(expected);

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
		Output expected = outputService.findPersonByFirstAndLastName("first name", "last name");
		assertThat(data).isEqualToComparingFieldByField(expected);

	}

	@Test
	void findEmailsByCity() {

		// Given
		List<String> emails = Arrays.asList("jaboyd@email.com", "jpeter@email.com", "gramps@email.com");

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
		assertThat(data).isEqualToComparingFieldByField(expected);

	}

}
