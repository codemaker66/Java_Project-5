package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dao.PersonDaoImpl;
import com.safetynet.alerts.model.Person;

public class PersonService {

	public List<Person> getAllPersons() {

		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		return personDaoImpl.retrieveAllPersonsFromTheList();

	}

	public void addAPerson(Person person) {

		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		personDaoImpl.addAPersonToTheList(person);

	}

	public void updateAPerson(Person person) {

		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		personDaoImpl.updateAPersonInTheList(person);

	}

	public void deleteAPerson(String firstName, String lastName) {

		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		personDaoImpl.deleteAPersonFromTheList(firstName, lastName);

	}

}
