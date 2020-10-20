package com.safetynet.alerts.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.dao.PersonDao;
import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@WebMvcTest(PersonController.class)
@ContextConfiguration(classes = PersonService.class)
class PersonControllerTest {

	@MockBean
	private PersonDao PersonDaoImpl;

	@Autowired
	private PersonService personService;

	@Test
	void getAllPersons() {

		// Given
		List<Person> list = Data.instance().getPersons();

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
