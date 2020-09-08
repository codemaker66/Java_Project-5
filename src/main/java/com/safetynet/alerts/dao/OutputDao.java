package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface OutputDao {

	public List<Person> retrievePersonsByFireStationNumber(String fireStationNumber);

	public List<Person> retrievePersonsByAddress(String address);

	public List<Person> retrievePersonsByFireStationNumbers(List<String> stations);

	public List<Person> retrievePersonByFirstAndLastName(String firstName, String lastName);

	public List<Person> retrievePersonsByCity(String city);

}
