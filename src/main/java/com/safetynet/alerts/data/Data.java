package com.safetynet.alerts.data;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

public class Data {
	
	public static List<Person> persons = new ArrayList<>();
	public static List<FireStation> fireStations = new ArrayList<>();
	public static List<MedicalRecord> medicalRecords = new ArrayList<>();
	
	public void init() throws JsonProcessingException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode rootNode = objectMapper.readTree(new File("resources/data.json"));
		
		for (int i = 0; i < rootNode.path("persons").size(); i++) {
			Person person = new Person();
			person.setFirstName(rootNode.path("persons").get(i).path("firstName").asText());
			person.setLastName(rootNode.path("persons").get(i).path("lastName").asText());
			person.setAddress(rootNode.path("persons").get(i).path("address").asText());
			person.setCity(rootNode.path("persons").get(i).path("city").asText());
			person.setZip(rootNode.path("persons").get(i).path("zip").asText());
			person.setPhone(rootNode.path("persons").get(i).path("phone").asText());
			person.setEmail(rootNode.path("persons").get(i).path("email").asText());
			
			persons.add(person);
		}
		
		
		for (int i = 0; i < rootNode.path("firestations").size(); i++) {
			FireStation fireStation = new FireStation();
			fireStation.setAddress(rootNode.path("firestations").get(i).path("address").asText());
			fireStation.setStation(rootNode.path("firestations").get(i).path("station").asText());
			
			fireStations.add(fireStation);
		
		}
		
		List<String> medications;
		List<String> allergies;
		
		for (int i = 0; i < rootNode.path("medicalrecords").size(); i++) {
			
			MedicalRecord medicalRecord = new MedicalRecord();
			
			medications = new ArrayList<>();
			allergies = new ArrayList<>();
			
			for (int j = 0; j < rootNode.path("medicalrecords").get(i).path("medications").size(); j++) {
				medications.add(rootNode.path("medicalrecords").get(i).path("medications").get(j).asText());
			}
			
			for (int k = 0; k < rootNode.path("medicalrecords").get(i).path("allergies").size(); k++) {
				allergies.add(rootNode.path("medicalrecords").get(i).path("allergies").get(k).asText());
			}
			
			medicalRecord.setFirstName(rootNode.path("medicalrecords").get(i).path("firstName").asText());
			medicalRecord.setLastName(rootNode.path("medicalrecords").get(i).path("lastName").asText());
			medicalRecord.setBirthdate(rootNode.path("medicalrecords").get(i).path("birthdate").asText());
			medicalRecord.setMedications(medications);
			medicalRecord.setAllergies(allergies);
			
			medicalRecords.add(medicalRecord);
			
		}
			
	}
	
	public void load() {
		
		for (int i = 0; i < persons.size(); i++) {
			for (int j = 0; j < fireStations.size(); j++) {
				if (persons.get(i).getAddress().equals(fireStations.get(j).getAddress())) {
					persons.get(i).setFireStationNumber(fireStations.get(j).getStation());
				}
			}
			
			for (int k = 0; k < medicalRecords.size(); k++) {
				if (persons.get(i).getFirstName().equals(medicalRecords.get(k).getFirstName())
					&& persons.get(i).getLastName().equals(medicalRecords.get(k).getLastName())) {
					DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
					String test = medicalRecords.get(k).getBirthdate();
					Date date = null;
					try {
						date = format.parse(test);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					LocalDate local = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate now = LocalDate.now();
					int age =  Period.between(local, now).getYears();
					persons.get(i).setAge(age);
					persons.get(i).setMedications(medicalRecords.get(k).getMedications());
					persons.get(i).setAllergies(medicalRecords.get(k).getAllergies());
					
				}
			}
		}
		
	}

}
