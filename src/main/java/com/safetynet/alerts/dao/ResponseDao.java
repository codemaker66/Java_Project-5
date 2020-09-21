package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.Output;

public interface ResponseDao {

	public List<Output> retrievePersonsByFireStationNumber(int fireStationNumber);

	public List<Output> retrieveChildrensByAddress(String address);

	public List<Output> retrievePhoneNumbersByFireStationNumber(int firestation);

	public List<Output> retrievePersonsByAddress(String address);

	public List<Output> retrievePersonsByFireStationNumbers(List<Integer> stations);

	public List<Output> retrievePersonByFirstAndLastName(String firstName, String lastName);

	public List<Output> retrieveEmailsByCity(String city);

}
