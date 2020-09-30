package com.safetynet.alerts.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Output;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.utils.Util;

@Component
public class Data {

	protected static List<Person> persons = new ArrayList<>();
	protected static List<FireStation> fireStations = new ArrayList<>();
	protected static List<MedicalRecord> medicalRecords = new ArrayList<>();

	@EventListener(ContextRefreshedEvent.class)
	public void init() throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode rootNode = objectMapper.readTree(new File("resources/data.json"));

		JsonNode personsNode = rootNode.path("persons");
		JsonNode fireStationNode = rootNode.path("firestations");
		JsonNode medicalRecordNode = rootNode.path("medicalrecords");

		for (int i = 0; i < personsNode.size(); i++) {

			Person person = new Person();

			person.setFirstName(personsNode.get(i).path("firstName").asText());
			person.setLastName(personsNode.get(i).path("lastName").asText());
			person.setAddress(personsNode.get(i).path("address").asText());
			person.setCity(personsNode.get(i).path("city").asText());
			person.setZip(personsNode.get(i).path("zip").asInt());
			person.setPhone(personsNode.get(i).path("phone").asText());
			person.setEmail(personsNode.get(i).path("email").asText());

			persons.add(person);
		}

		int j = 0;

		while (j < fireStationNode.size()) {

			String address = fireStationNode.get(j).path("address").asText();

			if (fireStations.stream().anyMatch(o -> o.getAddress().equals(address))) {
				j++;
			} else {

				FireStation fireStation = new FireStation();

				fireStation.setAddress(fireStationNode.get(j).path("address").asText());
				fireStation.setStation(fireStationNode.get(j).path("station").asInt());

				fireStations.add(fireStation);
				j++;
			}
		}

		List<String> medications;
		List<String> allergies;

		for (int k = 0; k < medicalRecordNode.size(); k++) {

			MedicalRecord medicalRecord = new MedicalRecord();

			medications = new ArrayList<>();
			allergies = new ArrayList<>();

			for (int l = 0; l < medicalRecordNode.get(k).path("medications").size(); l++) {
				medications.add(medicalRecordNode.get(k).path("medications").get(l).asText());
			}

			for (int m = 0; m < medicalRecordNode.get(k).path("allergies").size(); m++) {
				allergies.add(medicalRecordNode.get(k).path("allergies").get(m).asText());
			}

			medicalRecord.setFirstName(medicalRecordNode.get(k).path("firstName").asText());
			medicalRecord.setLastName(medicalRecordNode.get(k).path("lastName").asText());
			medicalRecord.setBirthdate(medicalRecordNode.get(k).path("birthdate").asText());
			medicalRecord.setMedications(medications);
			medicalRecord.setAllergies(allergies);

			medicalRecords.add(medicalRecord);

		}

	}

	public List<Output> load() {

		List<Output> outputData = new ArrayList<>();

		for (int i = 0; i < persons.size(); i++) {
			Output output = new Output();
			output.setFirstName(persons.get(i).getFirstName());
			output.setLastName(persons.get(i).getLastName());
			output.setAddress(persons.get(i).getAddress());
			output.setCity(persons.get(i).getCity());
			output.setZip(persons.get(i).getZip());
			output.setPhone(persons.get(i).getPhone());
			output.setEmail(persons.get(i).getEmail());
			outputData.add(output);
		}

		for (int i = 0; i < outputData.size(); i++) {

			for (int j = 0; j < fireStations.size(); j++) {
				if (outputData.get(i).getAddress().equals(fireStations.get(j).getAddress())) {
					outputData.get(i).setFireStationNumber(fireStations.get(j).getStation());
				}
			}

			for (int k = 0; k < medicalRecords.size(); k++) {
				if (outputData.get(i).getFirstName().equals(medicalRecords.get(k).getFirstName())
						&& outputData.get(i).getLastName().equals(medicalRecords.get(k).getLastName())) {
					Util util = new Util();
					int age = util.toLocalDate(medicalRecords.get(k).getBirthdate());
					outputData.get(i).setAge(age);
					outputData.get(i).setMedications(medicalRecords.get(k).getMedications());
					outputData.get(i).setAllergies(medicalRecords.get(k).getAllergies());

				}
			}
		}

		return outputData;

	}

}
