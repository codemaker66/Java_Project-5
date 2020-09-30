package com.safetynet.alerts.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

public class FireStation {
	@Length(min = 5, message = "address must have at least five characters")
	private String address;
	@Min(value = 1, message = "station must be at least one number")
	private int station;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

}
