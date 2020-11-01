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
import com.safetynet.alerts.dao.OutputDaoImpl;
import com.safetynet.alerts.data.DataManagement;
import com.safetynet.alerts.model.Output;

@WebMvcTest(OutputDaoImpl.class)
class OutputDaoImplTest {

	@MockBean
	DataManagement dataManagement;

	@Autowired
	OutputDao outputDaoImpl;

	@Test
	void retrievePersonsByFireStationNumber() {

		// Given
		Output person = new Output();
		person.setFirstName("Lily");
		person.setLastName("Cooper");
		person.setAddress("489 Manchester St");
		person.setPhone("841-874-9845");
		person.setFireStationNumber(4);

		List<Output> dataList = new ArrayList<>();
		dataList.add(person);

		// When
		Mockito.when(dataManagement.load()).thenReturn(dataList);

		// Then
		List<Output> expected = outputDaoImpl.retrievePersonsByFireStationNumber(4);

		List<Output> list = new ArrayList<>();
		person.setFireStationNumber(0);
		list.add(person);

		assertThat(list).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void retrieveChildrenByAddress() {

		// Given
		Output child = new Output();
		child.setFirstName("Zach");
		child.setLastName("Zemicks");
		child.setAge(3);
		child.setAddress("892 Downing Ct");

		Output mother = new Output();
		mother.setFirstName("Sophia");
		mother.setLastName("Zemicks");
		mother.setAge(32);
		mother.setAddress("892 Downing Ct");

		Output father = new Output();
		father.setFirstName("Warren");
		father.setLastName("Zemicks");
		father.setAge(35);
		father.setAddress("892 Downing Ct");

		List<Output> dataList = new ArrayList<>();
		dataList.add(child);
		dataList.add(mother);
		dataList.add(father);

		// When
		Mockito.when(dataManagement.load()).thenReturn(dataList);

		// Then
		List<Output> expected = outputDaoImpl.retrieveChildrenByAddress("892 Downing Ct");

		List<Output> famillyMembers = new ArrayList<>();
		mother.setAddress(null);
		father.setAddress(null);
		child.setAddress(null);
		famillyMembers.add(mother);
		famillyMembers.add(father);
		child.setFamillyMembers(famillyMembers);

		List<Output> list = new ArrayList<>();
		list.add(child);

		assertThat(list).usingRecursiveComparison().isEqualTo(expected);

	}

	@Test
	void retrievePhoneNumbersByFireStationNumber() {

		// Given
		Output person = new Output();
		person.setPhone("841-874-9845");
		person.setFireStationNumber(4);

		List<Output> dataList = new ArrayList<>();
		dataList.add(person);

		// When
		Mockito.when(dataManagement.load()).thenReturn(dataList);

		// Then
		List<Output> expected = outputDaoImpl.retrievePhoneNumbersByFireStationNumber(4);

		List<Output> list = new ArrayList<>();
		person.setFireStationNumber(0);
		list.add(person);

		assertThat(list).usingRecursiveComparison().isEqualTo(expected);

	}

	@Test
	void retrievePersonsByAddress() {

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
		person.setAddress("951 LoneTree Rd");

		List<Output> dataList = new ArrayList<>();
		dataList.add(person);

		// When
		Mockito.when(dataManagement.load()).thenReturn(dataList);

		// Then
		List<Output> expected = outputDaoImpl.retrievePersonsByAddress("951 LoneTree Rd");

		List<Output> list = new ArrayList<>();
		person.setAddress(null);
		list.add(person);

		assertThat(list).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void retrievePersonsByFireStationNumbers() {

		// Given
		List<String> emptyList = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		medications.add("tradoxidine:400mg");

		Output person = new Output();
		person.setLastName("Cadigan");
		person.setAddress("951 LoneTree Rd");
		person.setPhone("841-874-7458");
		person.setAge(75);
		person.setFireStationNumber(5);
		person.setMedications(medications);
		person.setAllergies(emptyList);

		List<Output> list = new ArrayList<>();
		list.add(person);

		Output person2 = new Output();
		person2.setLastName("Cooper");
		person2.setAddress("489 Manchester St");
		person2.setPhone("841-874-9845");
		person2.setAge(26);
		person2.setFireStationNumber(7);
		person2.setMedications(emptyList);
		person2.setAllergies(emptyList);

		List<Output> list2 = new ArrayList<>();
		list2.add(person2);

		List<Output> dataList = new ArrayList<>();
		dataList.add(person);
		dataList.add(person2);

		// When
		Mockito.when(dataManagement.load()).thenReturn(dataList);

		// Then
		List<Integer> listOfIntegers = Arrays.asList(5, 7);
		List<Output> expected = outputDaoImpl.retrievePersonsByFireStationNumbers(listOfIntegers);

		List<Output> allLists = new ArrayList<>();
		person.setFireStationNumber(0);
		person2.setFireStationNumber(0);
		allLists.add(person);
		allLists.add(person2);

		assertThat(allLists).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void retrievePersonByFirstAndLastName() {

		// Given
		List<String> emptyList = new ArrayList<>();
		Output person = new Output();
		person.setFirstName("Foster");
		person.setLastName("Shepard");
		person.setAddress("748 Townings Dr");
		person.setEmail("jaboyd@email.com");
		person.setAge(40);
		person.setMedications(emptyList);
		person.setAllergies(emptyList);

		List<Output> dataList = new ArrayList<>();
		dataList.add(person);

		// When
		Mockito.when(dataManagement.load()).thenReturn(dataList);

		// Then
		List<Output> expected = outputDaoImpl.retrievePersonByFirstAndLastName("Foster", "Shepard");

		List<Output> list = new ArrayList<>();
		person.setFirstName(null);
		list.add(person);

		assertThat(list).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void retrieveEmailsByCity() {

		// Given
		Output person = new Output();
		person.setEmail("gramps@email.com");
		person.setCity("Culver");
		List<Output> dataList = Arrays.asList(person);

		// When
		Mockito.when(dataManagement.load()).thenReturn(dataList);

		// Then
		List<Output> expected = outputDaoImpl.retrieveEmailsByCity("Culver");

		List<Output> list = new ArrayList<>();
		person.setCity(null);
		list.add(person);

		assertThat(list).usingRecursiveComparison().isEqualTo(expected);
	}

}
