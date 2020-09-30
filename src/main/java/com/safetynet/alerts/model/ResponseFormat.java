package com.safetynet.alerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResponseFormat {

	private String fireStation;
	private List<Output> persons;
	private List<List<Output>> personsGrouped;
	private List<Output> childrens;
	private List<Output> phoneNumbers;
	private List<Output> emails;
	private int childrensCount;
	private int adultsCount;

	public String getFireStation() {
		return fireStation;
	}

	public void setFireStation(String fireStation) {
		this.fireStation = fireStation;
	}

	public List<Output> getPersons() {
		return persons;
	}

	public void setPersons(List<Output> persons) {
		this.persons = persons;
	}

	public List<Output> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Output> childrens) {
		this.childrens = childrens;
	}

	public List<Output> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<Output> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<Output> getEmails() {
		return emails;
	}

	public void setEmails(List<Output> emails) {
		this.emails = emails;
	}

	public int getChildrensCount() {
		return childrensCount;
	}

	public void setChildrensCount(int childrensCount) {
		this.childrensCount = childrensCount;
	}

	public int getAdultsCount() {
		return adultsCount;
	}

	public void setAdultsCount(int adultsCount) {
		this.adultsCount = adultsCount;
	}

	public List<List<Output>> getPersonsGrouped() {
		return personsGrouped;
	}

	public void setPersonsGrouped(List<List<Output>> personsGrouped) {
		this.personsGrouped = personsGrouped;
	}

}
