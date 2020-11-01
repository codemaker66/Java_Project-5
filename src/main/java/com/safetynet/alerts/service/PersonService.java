package com.safetynet.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.dao.PersonDao;
import com.safetynet.alerts.model.Person;

@Service
public class PersonService {

	@Autowired
	private PersonDao personDaoImpl;
	private static final Logger logger = LogManager.getLogger(PersonService.class);

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

		logger.debug("Check is : " + check);
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

		logger.debug("Check is : " + check);
		return check;

	}

	/**
	 * This method call the personDaoImpl to delete a person from the list.
	 * 
	 * @param firstName represent the first name of a person.
	 * @param lastName represent the last name of a person.
	 * @return true if the person was deleted from the list.
	 */
	public boolean deleteAPerson(String firstName, String lastName) {

		boolean check = false;

		if (personDaoImpl.deleteAPersonFromTheList(firstName, lastName)) {
			check = true;
		}

		logger.debug("Check is : " + check);
		return check;
	}

}
