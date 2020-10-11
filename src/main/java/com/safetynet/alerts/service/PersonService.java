package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dao.PersonDaoImpl;
import com.safetynet.alerts.model.Person;

public class PersonService {

	private PersonDaoImpl personDaoImpl = new PersonDaoImpl();

	/**
	 * This method call the personDaoImpl to retrieve all persons from the list.
	 * 
	 * @return a list that contain all the persons.
	 */
	public List<Person> getAllPersons() {

		return personDaoImpl.retrieveAllPersonsFromTheList();

	}

	/**
	 * This method call the personDaoImpl to add a person to the list.
	 * 
	 * @param person is an object of type Person that contain the data of a person.
	 * @return true if the person was added to the list.
	 */
	public boolean addAPerson(Person person) {

		boolean check = false;

		if (personDaoImpl.addAPersonToTheList(person)) {
			check = true;
		}

		return check;

	}

	/**
	 * This method call the personDaoImpl to update a person in the list.
	 * 
	 * @param person is an object of type Person that contain the data of a person.
	 * @return true if the person was updated in the list.
	 */
	public boolean updateAPerson(Person person) {

		boolean check = false;

		if (personDaoImpl.updateAPersonInTheList(person)) {
			check = true;
		}

		return check;

	}

	/**
	 * This method call the personDaoImpl to delete a person from the list.
	 * 
	 * @param firstName represent the first name of a person.
	 * @param lastName  represent the last name of a person.
	 * @return true if the person was deleted from the list.
	 */
	public boolean deleteAPerson(String firstName, String lastName) {

		boolean check = false;

		if (personDaoImpl.deleteAPersonFromTheList(firstName, lastName)) {
			check = true;
		}

		return check;
	}

}
