package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dao.PersonDaoImpl;
import com.safetynet.alerts.model.Person;

public class PersonService {

	private PersonDaoImpl personDaoImpl = new PersonDaoImpl();

	public List<Person> getAllPersons() {

		return personDaoImpl.retrieveAllPersonsFromTheList();

	}

	public boolean addAPerson(Person person) {

		boolean check = false;

		if (personDaoImpl.addAPersonToTheList(person)) {
			check = true;
		}

		return check;

	}

	public boolean updateAPerson(Person person) {

		boolean check = false;

		if (personDaoImpl.updateAPersonInTheList(person)) {
			check = true;
		}

		return check;

	}

	public boolean deleteAPerson(String firstName, String lastName) {

		boolean check = false;

		if (personDaoImpl.deleteAPersonFromTheList(firstName, lastName)) {
			check = true;
		}

		return check;
	}

}
