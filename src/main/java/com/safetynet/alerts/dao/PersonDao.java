package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface PersonDao {

	public List<Person> retrieveAllPersonsFromTheList();

	public void addAPersonToTheList(Person person);

	public void updateAPersonInTheList(Person person);

	public void deleteAPersonFromTheList(String firstName, String lastName);

}
