package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.Person;

public class PersonDaoImpl implements PersonDao {

	@Override
	public List<Person> retrieveAllPersonsFromTheList() {
		return Data.persons;
	}

	@Override
	public void addAPersonToTheList(Person person) {
		Data.persons.add(person);

	}

	@Override
	public void updateAPersonInTheList(Person person) {
		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getFirstName().equals(person.getFirstName())
					&& Data.persons.get(i).getLastName().equals(person.getLastName())) {
				Data.persons.get(i).setAddress(person.getAddress());
				Data.persons.get(i).setCity(person.getCity());
				Data.persons.get(i).setZip(person.getZip());
				Data.persons.get(i).setPhone(person.getPhone());
				Data.persons.get(i).setEmail(person.getEmail());
			}
		}

	}

	@Override
	public void deleteAPersonFromTheList(String firstName, String lastName) {
		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getFirstName().equals(firstName)
					&& Data.persons.get(i).getLastName().equals(lastName)) {
				Data.persons.remove(i);
			}
		}

	}

}
