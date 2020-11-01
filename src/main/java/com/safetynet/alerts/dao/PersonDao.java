package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface PersonDao {

	/**
	 * This method retrieve all persons from the list.
	 * 
	 * @return a list that contain all the persons.
	 */
	public List<Person> retrieveAllPersonsFromTheList();

	/**
	 * This method add a person to the list.
	 * 
	 * @param person is an object of type Person that contain the data of a person.
	 * @return true if the person was added to the list.
	 */
	public boolean addAPersonToTheList(Person person);

	/**
	 * This method update a person in the list.
	 * 
	 * @param person is an object of type Person that contain the data of a person.
	 * @return true if the person was updated in the list.
	 */
	public boolean updateAPersonInTheList(Person person);

	/**
	 * This method delete a person from the list.
	 * 
	 * @param firstName represent the first name of a person.
	 * @param lastName represent the last name of a person.
	 * @return true if the person was deleted from the list.
	 */
	public boolean deleteAPersonFromTheList(String firstName, String lastName);

}
