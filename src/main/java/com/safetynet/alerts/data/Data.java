package com.safetynet.alerts.data;

import java.util.ArrayList;
import java.util.List;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

public class Data {

	private static Data data = null;
	private List<Person> persons = new ArrayList<>();
	private List<FireStation> fireStations = new ArrayList<>();
	private List<MedicalRecord> medicalRecords = new ArrayList<>();

	private Data() {

	}

	public static synchronized Data instance() {

		if (data == null) {
			data = new Data();
		}

		return data;

	}

	public List<Person> getPersons() {
		return persons;
	}

	public List<FireStation> getFireStations() {
		return fireStations;
	}

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

}
