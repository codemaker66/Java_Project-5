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
public class DataManagement {

	private List<Person> persons = Data.instance().getPersons();
	private List<FireStation> fireStations = Data.instance().getFireStations();
	private List<MedicalRecord> medicalRecords = Data.instance().getMedicalRecords();

	/**
	 * This method initialize the data in the three main lists.
	 * 
	 * @throws IOException.
	 */
	@EventListener(ContextRefreshedEvent.class)
	public void init() throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode rootNode = objectMapper.readTree(new File("resources/data.json"));

		JsonNode personsNode = rootNode.path("persons");
		JsonNode fireStationNode = rootNode.path("firestations");
		JsonNode medicalRecordNode = rootNode.path("medicalrecords");

		for (int i = 0; i < personsNode.size(); i++) {

			Person person = new Person();

			JsonNode node = personsNode.get(i);

			person.setFirstName(node.path("firstName").asText());
			person.setLastName(node.path("lastName").asText());
			person.setAddress(node.path("address").asText());
			person.setCity(node.path("city").asText());
			person.setZip(node.path("zip").asInt());
			person.setPhone(node.path("phone").asText());
			person.setEmail(node.path("email").asText());

			persons.add(person);
		}

		int j = 0;

		while (j < fireStationNode.size()) {

			JsonNode node = fireStationNode.get(j);

			String address = node.path("address").asText();

			if (fireStations.stream().anyMatch(o -> o.getAddress().equals(address))) {
				j++;
			} else {

				FireStation fireStation = new FireStation();

				fireStation.setAddress(node.path("address").asText());
				fireStation.setStation(node.path("station").asInt());

				fireStations.add(fireStation);
				j++;
			}
		}

		List<String> medications;
		List<String> allergies;

		for (int k = 0; k < medicalRecordNode.size(); k++) {

			JsonNode node = medicalRecordNode.get(k);

			MedicalRecord medicalRecord = new MedicalRecord();

			medications = new ArrayList<>();
			allergies = new ArrayList<>();
			
			for (int l = 0; l < node.path("medications").size(); l++) {
				medications.add(node.path("medications").get(l).asText());
			}

			for (int m = 0; m < node.path("allergies").size(); m++) {
				allergies.add(node.path("allergies").get(m).asText());
			}

			medicalRecord.setFirstName(node.path("firstName").asText());
			medicalRecord.setLastName(node.path("lastName").asText());
			medicalRecord.setBirthdate(node.path("birthdate").asText());
			medicalRecord.setMedications(medications);
			medicalRecord.setAllergies(allergies);

			medicalRecords.add(medicalRecord);

		}

	}

	/**
	 * This method load all the data from the three main lists into one.
	 * 
	 * @return a list containing all the data from the three main lists.
	 */
	public List<Output> load() {
		
		Util util = new Util();
		List<Output> outputData = new ArrayList<>();

		for (int i = 0; i < persons.size(); i++) {
			Person person = persons.get(i);
			Output output = new Output();
			output.setFirstName(person.getFirstName());
			output.setLastName(person.getLastName());
			output.setAddress(person.getAddress());
			output.setCity(person.getCity());
			output.setZip(person.getZip());
			output.setPhone(person.getPhone());
			output.setEmail(person.getEmail());
			outputData.add(output);
		}

		for (int i = 0; i < outputData.size(); i++) {

			for (int j = 0; j < fireStations.size(); j++) {
				FireStation fireStation = fireStations.get(j);
				if (outputData.get(i).getAddress().equals(fireStation.getAddress())) {
					outputData.get(i).setFireStationNumber(fireStation.getStation());
				}
			}

			for (int k = 0; k < medicalRecords.size(); k++) {
				MedicalRecord medicalRecord = medicalRecords.get(k);
				if (outputData.get(i).getFirstName().equals(medicalRecord.getFirstName())
					&& outputData.get(i).getLastName().equals(medicalRecord.getLastName())) {
					int age = util.toLocalDate(medicalRecord.getBirthdate());
					outputData.get(i).setAge(age);
					outputData.get(i).setMedications(medicalRecord.getMedications());
					outputData.get(i).setAllergies(medicalRecord.getAllergies());

				}
			}
		}

		return outputData;

	}

}
