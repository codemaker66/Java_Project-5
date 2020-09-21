package com.safetynet.alerts.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Person {
	@NotNull(message = "firstName can not be empty")
	@Length(min = 1, message = "firstName must have at least one character")
	private String firstName;
	@NotNull(message = "lastName can not be empty")
	@Length(min = 1, message = "lastName must have at least one character")
	private String lastName;
	@NotNull(message = "address can not be empty")
	@Length(min = 10, message = "address must have at least ten characters")
	private String address;
	@NotNull(message = "city can not be empty")
	@Length(min = 2, message = "city must have at least two characters")
	private String city;
	@NotNull(message = "zip can not be empty")
	@Min(value = 1, message = "zip must be at least one number")
	private int zip;
	@NotNull(message = "phone can not be empty")
	@Length(max = 10, message = "phone must have ten characters only")
	private String phone;
	@NotNull(message = "email can not be empty")
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
