package com.safetynet.alerts.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class FireStation {
	@NotNull(message = "address can not be null")
	@Length(min = 10, message = "address must have at least ten characters")
	private String address;
	@NotNull(message = "station can not be null")
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
