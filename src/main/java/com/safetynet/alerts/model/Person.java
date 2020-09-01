package com.safetynet.alerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("DynamicFilter")
public class Person {
	
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
	private List<Person> famillyMembers;
	private String fireStationNumber;
	private int age;
	private List<String> medications;
	private List<String> allergies;


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


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
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


	public List<Person> getFamillyMembers() {
		return famillyMembers;
	}


	public void setFamillyMembers(List<Person> famillyMembers) {
		this.famillyMembers = famillyMembers;
	}


	public String getFireStationNumber() {
		return fireStationNumber;
	}


	public void setFireStationNumber(String fireStationNumber) {
		this.fireStationNumber = fireStationNumber;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
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
	
	
	
	

}
