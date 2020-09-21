package com.safetynet.alerts.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class MedicalRecord {
	@NotNull(message = "firstName can not be null")
	@Length(min = 1, message = "firstName can not be less than 1 character")
	private String firstName;
	@NotNull(message = "lastName can not be null")
	@Length(min = 1, message = "lastName can not be less than 1 character")
	private String lastName;
	private String birthdate;
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

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
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
