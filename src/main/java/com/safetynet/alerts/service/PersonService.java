package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dao.PersonDaoImpl;
import com.safetynet.alerts.model.Person;

public class PersonService {

	public List<Person> getAllPersons() {

		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		return personDaoImpl.retrieveAllPersonsFromTheList();

	}

	public boolean addAPerson(Person person) {

		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		if (personDaoImpl.addAPersonToTheList(person)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean updateAPerson(Person person) {

		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		if (personDaoImpl.updateAPersonInTheList(person)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean deleteAPerson(String firstName, String lastName) {

		PersonDaoImpl personDaoImpl = new PersonDaoImpl();

		if (personDaoImpl.deleteAPersonFromTheList(firstName, lastName)) {
			return true;
		} else {
			return false;
		}

	}

}
