package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface OutputDao {
	
	public List<Object> findPersonsByFireStationNumber(String stationNumber);
	
	public List<Person> findChildrensByAddress(String address);
	
	public List<Person> findPhoneNumbersByFireStationNumber(String firestation);
	
	public List<Object> findPersonsByAddress(String address);
	
	public List<Object> findPersonsByFireStationNumbers(List<String> stations);
	
	public List<Person> findPersonByFirstAndLastName(String lastName);
	
	public List<Person> findEmailsByCity(String city);
	

}
