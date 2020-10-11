package com.safetynet.alerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonFilter("DynamicFilter")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Output {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private int zip;
	private String phone;
	private String email;
	private int fireStationNumber;
	private int age;
	private List<Output> famillyMembers;
	private List<String> medications;
	private List<String> allergies;
	private String fireStation;
	private List<Output> persons;
	private List<List<Output>> personsGroupedByAddress;
	private List<Output> children;
	private List<Output> phoneNumbers;
	private List<Output> emails;
	private int childrenCount;
	private int adultsCount;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFireStationNumber() {
		return fireStationNumber;
	}

	public void setFireStationNumber(int fireStationNumber) {
		this.fireStationNumber = fireStationNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Output> getFamillyMembers() {
		return famillyMembers;
	}

	public void setFamillyMembers(List<Output> famillyMembers) {
		this.famillyMembers = famillyMembers;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

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

	public List<List<Output>> getPersonsGroupedByAddress() {
		return personsGroupedByAddress;
	}

	public void setPersonsGroupedByAddress(List<List<Output>> personsGroupedByAddress) {
		this.personsGroupedByAddress = personsGroupedByAddress;
	}

	public List<Output> getChildren() {
		return children;
	}

	public void setChildren(List<Output> children) {
		this.children = children;
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

	public int getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(int childrenCount) {
		this.childrenCount = childrenCount;
	}

	public int getAdultsCount() {
		return adultsCount;
	}

	public void setAdultsCount(int adultsCount) {
		this.adultsCount = adultsCount;
	}

}
