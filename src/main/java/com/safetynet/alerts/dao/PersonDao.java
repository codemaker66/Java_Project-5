package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface PersonDao {

	public List<Person> retrieveAllPersonsFromTheList();

	public boolean addAPersonToTheList(Person person);

	public boolean updateAPersonInTheList(Person person);

	public boolean deleteAPersonFromTheList(String firstName, String lastName);

}
