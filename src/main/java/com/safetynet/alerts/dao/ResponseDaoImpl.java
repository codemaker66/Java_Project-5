package com.safetynet.alerts.dao;

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

import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.Output;
import com.safetynet.alerts.model.Person;

public class ResponseDaoImpl implements ResponseDao {

	@Override
	public List<Output> retrievePersonsByFireStationNumber(int fireStationNumber) {

		List<Person> persons = new ArrayList<>();

		List<Output> list = new ArrayList<>();

		for (int i = 0; i < Data.persons.size(); i++) {
			for (int j = 0; j < Data.fireStations.size(); j++) {
				if (Data.persons.get(i).getAddress().equals(Data.fireStations.get(j).getAddress())
						&& Data.fireStations.get(j).getStation() == fireStationNumber) {
					persons.add(Data.persons.get(i));
				}
			}
		}

		for (int i = 0; i < persons.size(); i++) {
			for (int j = 0; j < Data.medicalRecords.size(); j++) {
				if (persons.get(i).getFirstName().equals(Data.medicalRecords.get(j).getFirstName())
						&& persons.get(i).getLastName().equals(Data.medicalRecords.get(j).getLastName())) {

					Output output = new Output();

					output.setFirstName(persons.get(i).getFirstName());
					output.setLastName(persons.get(i).getLastName());
					output.setAddress(persons.get(i).getAddress());
					output.setPhone(persons.get(i).getPhone());
					output.setAge(calculateAge(Data.medicalRecords.get(j).getBirthdate()));

					list.add(output);

				}
			}
		}

		return list;
	}

	@Override
	public List<Output> retrieveChildrensByAddress(String address) {

		List<Output> list = new ArrayList<>();
		List<Output> data = new ArrayList<>();
		List<Output> famillyMembers;

		for (int i = 0; i < Data.persons.size(); i++) {
			for (int j = 0; j < Data.medicalRecords.size(); j++) {
				if (Data.persons.get(i).getAddress().equals(address)
						&& Data.persons.get(i).getFirstName().equals(Data.medicalRecords.get(j).getFirstName())
						&& Data.persons.get(i).getLastName().equals(Data.medicalRecords.get(j).getLastName())) {

					Output output = new Output();

					output.setFirstName(Data.persons.get(i).getFirstName());
					output.setLastName(Data.persons.get(i).getLastName());
					output.setAge(calculateAge(Data.medicalRecords.get(j).getBirthdate()));

					data.add(output);

				}
			}
		}

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getAge() <= 18) {
				list.add(data.get(i));
			}
		}

		for (int i = 0; i < list.size(); i++) {

			famillyMembers = new ArrayList<>();

			for (int j = 0; j < data.size(); j++) {
				if (list.get(i).getLastName().equals(data.get(j).getLastName())
						&& !list.get(i).getFirstName().equals(data.get(j).getFirstName())) {

					Output output = new Output();

					output.setFirstName(data.get(j).getFirstName());
					output.setLastName(data.get(j).getLastName());
					output.setAge(data.get(j).getAge());

					famillyMembers.add(output);

				}
			}
			list.get(i).setFamillyMembers(famillyMembers);
		}

		return list;
	}

	@Override
	public List<Output> retrievePhoneNumbersByFireStationNumber(int firestation) {

		List<Person> persons = new ArrayList<>();

		List<Output> list = new ArrayList<>();

		for (int i = 0; i < Data.persons.size(); i++) {
			for (int j = 0; j < Data.fireStations.size(); j++) {
				if (Data.persons.get(i).getAddress().equals(Data.fireStations.get(j).getAddress())
						&& Data.fireStations.get(j).getStation() == firestation) {
					persons.add(Data.persons.get(i));
				}
			}
		}

		for (int i = 0; i < persons.size(); i++) {
			Output output = new Output();
			output.setPhone(persons.get(i).getPhone());
			list.add(output);
		}

		return list;
	}

	@Override
	public List<Output> retrievePersonsByAddress(String address) {

		List<Output> list = new ArrayList<>();

		for (int i = 0; i < Data.persons.size(); i++) {
			for (int j = 0; j < Data.medicalRecords.size(); j++) {
				if (Data.persons.get(i).getAddress().equals(address)
						&& Data.persons.get(i).getFirstName().equals(Data.medicalRecords.get(j).getFirstName())
						&& Data.persons.get(i).getLastName().equals(Data.medicalRecords.get(j).getLastName())) {

					Output output = new Output();

					output.setLastName(Data.persons.get(i).getLastName());
					output.setAddress(Data.persons.get(i).getAddress());
					output.setPhone(Data.persons.get(i).getPhone());
					output.setAge(calculateAge(Data.medicalRecords.get(j).getBirthdate()));
					output.setMedications(Data.medicalRecords.get(j).getMedications());
					output.setAllergies(Data.medicalRecords.get(j).getAllergies());

					list.add(output);

				}
			}
		}

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < Data.fireStations.size(); j++) {
				if (list.get(i).getAddress().equals(Data.fireStations.get(j).getAddress())) {
					list.get(i).setFireStationNumber(Data.fireStations.get(j).getStation());
				}
			}
		}

		return list;
	}

	@Override
	public List<Output> retrievePersonsByFireStationNumbers(List<Integer> stations) {

		List<Output> list = new ArrayList<>();

		for (int i = 0; i < stations.size(); i++) {
			for (int j = 0; j < Data.persons.size(); j++) {
				for (int k = 0; k < Data.fireStations.size(); k++) {
					if (Data.persons.get(j).getAddress().equals(Data.fireStations.get(k).getAddress())
							&& Data.fireStations.get(k).getStation() == stations.get(i)) {

						Output output = new Output();

						output.setFirstName(Data.persons.get(j).getFirstName());
						output.setLastName(Data.persons.get(j).getLastName());
						output.setAddress(Data.persons.get(j).getAddress());
						output.setPhone(Data.persons.get(j).getPhone());
						output.setFireStationNumber(stations.get(i));

						list.add(output);
					}
				}
			}
		}

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < Data.medicalRecords.size(); j++) {
				if (list.get(i).getFirstName().equals(Data.medicalRecords.get(j).getFirstName())
						&& list.get(i).getLastName().equals(Data.medicalRecords.get(j).getLastName())) {

					list.get(i).setAge(calculateAge(Data.medicalRecords.get(j).getBirthdate()));
					list.get(i).setMedications(Data.medicalRecords.get(j).getMedications());
					list.get(i).setAllergies(Data.medicalRecords.get(j).getAllergies());

				}
			}
		}

		return list;
	}

	@Override
	public List<Output> retrievePersonByFirstAndLastName(String firstName, String lastName) {

		List<Person> persons = new ArrayList<>();

		List<Output> list = new ArrayList<>();

		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getFirstName().equals(firstName)
					&& Data.persons.get(i).getLastName().equals(lastName)
					|| !Data.persons.get(i).getFirstName().equals(firstName)
							&& Data.persons.get(i).getLastName().equals(lastName)) {
				persons.add(Data.persons.get(i));
			}
		}

		for (int i = 0; i < persons.size(); i++) {
			for (int j = 0; j < Data.medicalRecords.size(); j++) {
				if (persons.get(i).getFirstName().equals(Data.medicalRecords.get(j).getFirstName())
						&& persons.get(i).getLastName().equals(Data.medicalRecords.get(j).getLastName())) {

					Output output = new Output();

					output.setLastName(persons.get(i).getLastName());
					output.setAddress(persons.get(i).getAddress());
					output.setAge(calculateAge(Data.medicalRecords.get(j).getBirthdate()));
					output.setEmail(persons.get(i).getEmail());
					output.setMedications(Data.medicalRecords.get(j).getMedications());
					output.setAllergies(Data.medicalRecords.get(j).getAllergies());

					list.add(output);

				}
			}
		}

		return list;
	}

	@Override
	public List<Output> retrieveEmailsByCity(String city) {

		List<Output> list = new ArrayList<>();

		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getCity().equals(city)) {
				Output output = new Output();
				output.setEmail(Data.persons.get(i).getEmail());
				list.add(output);
			}
		}
		return list;
	}

	private int calculateAge(String birthdate) {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
		String test = birthdate;
		Date date = null;

		try {
			date = format.parse(test);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		LocalDate local = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = LocalDate.now();
		int age = Period.between(local, now).getYears();

		return age;

	}

}
