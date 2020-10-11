package com.safetynet.alerts.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class Person {
	@Length(min = 1, message = "firstName must have at least one character")
	private String firstName;
	@Length(min = 1, message = "lastName must have at least one character")
	private String lastName;
	@Length(min = 5, message = "address must have at least five characters")
	private String address;
	@Length(min = 2, message = "city must have at least two characters")
	private String city;
	@Min(value = 1, message = "zip must be at least one number")
	private int zip;
	@Pattern(regexp = "^.*([0-9]{3})-([0-9]{3})-([0-9]{4})", message = "phone must be created with the following pattern : 012-345-6789")
	private String phone;
	@Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "email field cannot be empty")
	@Email(message = "email must be a valid email")
	private String email;

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

}
