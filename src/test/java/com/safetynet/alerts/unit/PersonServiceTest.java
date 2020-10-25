package com.safetynet.alerts.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.safetynet.alerts.dao.PersonDao;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@WebMvcTest(PersonService.class)
class PersonServiceTest {

	@MockBean
	private PersonDao PersonDaoImpl;

	@Autowired
	private PersonService personService;

	@Test
	void getAllPersons() {

		// Given
		Person person = new Person();
		person.setFirstName("Jo");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setZip(97451);
		person.setPhone("841-874-6512");
		person.setEmail("jaboyd@email.com");

		Person person2 = new Person();
		person2.setFirstName("Joe");
		person2.setLastName("Boy");
		person2.setAddress("25 Culver St");
		person2.setCity("Culver");
		person2.setZip(97368);
		person2.setPhone("889-831-4791");
		person2.setEmail("jaboy@email.com");

		List<Person> list = new ArrayList<>();
		list.add(person);
		list.add(person2);

		// When
		Mockito.when(PersonDaoImpl.retrieveAllPersonsFromTheList()).thenReturn(list);

		// Then
		List<Person> expected = personService.getAllPersons();
		assertThat(expected).isEqualTo(list);
	}

	@Test
	void addAPerson() {

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
		Mockito.when(PersonDaoImpl.addAPersonToTheList(person)).thenReturn(true);

		// Then
		boolean expected = personService.addAPerson(person);
		assertThat(expected).isTrue();

	}

	@Test
	void updateAPerson() {

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
		Mockito.when(PersonDaoImpl.updateAPersonInTheList(person)).thenReturn(true);

		// Then
		boolean expected = personService.updateAPerson(person);
		assertThat(expected).isTrue();

	}

	@Test
	void deleteAPerson() {

		// When
		Mockito.when(PersonDaoImpl.deleteAPersonFromTheList(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

		// Then
		boolean expected = personService.deleteAPerson("firstName", "lastName");
		assertThat(expected).isTrue();
	}

}
