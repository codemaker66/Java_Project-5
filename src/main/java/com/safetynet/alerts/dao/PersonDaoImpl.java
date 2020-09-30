package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.Person;

public class PersonDaoImpl extends Data implements PersonDao {

	@Override
	public List<Person> retrieveAllPersonsFromTheList() {
		return persons;
	}

	@Override
	public boolean addAPersonToTheList(Person person) {

		boolean check = true;

		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getFirstName().equals(person.getFirstName())
					&& persons.get(i).getLastName().equals(person.getLastName())) {
				check = false;
				break;
			}
		}

		if (check) {
			persons.add(person);
			return check;
		}

		return check;

	}

	@Override
	public boolean updateAPersonInTheList(Person person) {

		boolean check = false;

		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getFirstName().equals(person.getFirstName())
					&& persons.get(i).getLastName().equals(person.getLastName())) {
				persons.get(i).setAddress(person.getAddress());
				persons.get(i).setCity(person.getCity());
				persons.get(i).setZip(person.getZip());
				persons.get(i).setPhone(person.getPhone());
				persons.get(i).setEmail(person.getEmail());
				check = true;
				break;
			}
		}

		return check;

	}

	@Override
	public boolean deleteAPersonFromTheList(String firstName, String lastName) {

		boolean check = false;

		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getFirstName().equals(firstName) && persons.get(i).getLastName().equals(lastName)) {
				persons.remove(i);
				check = true;
				break;
			}
		}

		return check;

	}

}
