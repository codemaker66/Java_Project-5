package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.Output;

public interface OutputDao {

	/**
	 * This method retrieve persons by fire station number.
	 * 
	 * @param stationNumber represent the station number of a fire station.
	 * @return a list of persons that are served by the indicated fire station.
	 */
	public List<Output> retrievePersonsByFireStationNumber(int stationNumber);

	/**
	 * This method retrieve children by address.
	 * 
	 * @param address represent the address where one or many children live.
	 * @return a list of children that live in the indicated address.
	 */
	public List<Output> retrieveChildrenByAddress(String address);

	/**
	 * This method retrieve phone numbers by fire station number.
	 * 
	 * @param firestation represent the station number of a fire station.
	 * @return a list of phone numbers registered by the indicated fire station.
	 */
	public List<Output> retrievePhoneNumbersByFireStationNumber(int firestation);

	/**
	 * This method retrieve persons by address.
	 * 
	 * @param address represent the address where one or many persons live.
	 * @return a list of persons that live in the indicated address.
	 */
	public List<Output> retrievePersonsByAddress(String address);

	/**
	 * This method retrieve persons by fire station numbers.
	 * 
	 * @param stations represent a list that contain one or many fire station numbers.
	 * @return a list of persons that are served by the indicated fire stations.
	 */
	public List<Output> retrievePersonsByFireStationNumbers(List<Integer> stations);

	/**
	 * This method retrieve one or many persons by first and last name.
	 * 
	 * @param firstName represent the first name of a person.
	 * @param lastName  represent the last name of a person.
	 * @return a list of one or many persons (if they have the same last name).
	 */
	public List<Output> retrievePersonByFirstAndLastName(String firstName, String lastName);

	/**
	 * This method retrieve emails by city.
	 * 
	 * @param city represent the city where one or many persons live.
	 * @return a list of one or many emails of persons living in the indicated city.
	 */
	public List<Output> retrieveEmailsByCity(String city);

}
