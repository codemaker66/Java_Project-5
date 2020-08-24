package com.safetynet.alerts.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	public void load() throws JsonProcessingException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode rootNode = objectMapper.readTree(new File("resources/data.json"));
		
		for (int i = 0; i < rootNode.path("persons").size(); i++) {
			persons.add(new Person(rootNode.path("persons").get(i).path("firstName").asText(),
			rootNode.path("persons").get(i).path("lastName").asText(),
			rootNode.path("persons").get(i).path("address").asText(),
			rootNode.path("persons").get(i).path("city").asText(),
			rootNode.path("persons").get(i).path("zip").asText(),
			rootNode.path("persons").get(i).path("phone").asText(),
			rootNode.path("persons").get(i).path("email").asText()));
		}
		
		
		for (int i = 0; i < rootNode.path("firestations").size(); i++) {
			fireStations.add(new FireStation(rootNode.path("firestations").get(i).path("address").asText(),
			rootNode.path("firestations").get(i).path("station").asText()));	
		
		}
		
		List<String> medications;
		List<String> allergies;
		
		for (int i = 0; i < rootNode.path("medicalrecords").size(); i++) {
			
			medications = new ArrayList<>();
			allergies = new ArrayList<>();
			
			for (int j = 0; j < rootNode.path("medicalrecords").get(i).path("medications").size(); j++) {
				medications.add(rootNode.path("medicalrecords").get(i).path("medications").get(j).asText());
			}
			
			for (int k = 0; k < rootNode.path("medicalrecords").get(i).path("allergies").size(); k++) {
				allergies.add(rootNode.path("medicalrecords").get(i).path("allergies").get(k).asText());
			}
			
			medicalRecords.add(new MedicalRecord(rootNode.path("medicalrecords").get(i).path("firstName").asText(),
			rootNode.path("medicalrecords").get(i).path("lastName").asText(),
			rootNode.path("medicalrecords").get(i).path("birthdate").asText(),
			medications,
			allergies
			));	
		}
			
	}

}
