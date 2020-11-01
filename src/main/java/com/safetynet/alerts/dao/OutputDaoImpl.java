package com.safetynet.alerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.data.DataManagement;
import com.safetynet.alerts.model.Output;

@Repository
public class OutputDaoImpl implements OutputDao {
	
	@Autowired
	private DataManagement dataManagement;

	/**
	 * @see com.safetynet.alerts.dao.OutputDao#retrievePersonsByFireStationNumber(int)
	 */
	@Override
	public List<Output> retrievePersonsByFireStationNumber(int stationNumber) {

		List<Output> dataList = dataManagement.load();
		List<Output> persons = new ArrayList<>();
		List<Output> list = new ArrayList<>();

		for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).getFireStationNumber() == stationNumber) {
				persons.add(dataList.get(i));
			}
		}

		for (int i = 0; i < persons.size(); i++) {

			Output output = new Output();

			output.setFirstName(persons.get(i).getFirstName());
			output.setLastName(persons.get(i).getLastName());
			output.setAddress(persons.get(i).getAddress());
			output.setPhone(persons.get(i).getPhone());
			output.setAge(persons.get(i).getAge());

			list.add(output);
		}

		return list;
	}

	/**
	 * @see com.safetynet.alerts.dao.OutputDao#retrieveChildrenByAddress(String)
	 */
	@Override
	public List<Output> retrieveChildrenByAddress(String address) {

		List<Output> dataList = dataManagement.load();
		List<Output> persons = new ArrayList<>();
		List<Output> list = new ArrayList<>();
		List<Output> famillyMembers;

		for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).getAddress().equals(address)) {
				persons.add(dataList.get(i));
			}
		}

		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getAge() <= 18) {

				Output output = new Output();
				
				output.setFirstName(persons.get(i).getFirstName());
				output.setLastName(persons.get(i).getLastName());
				output.setAge(persons.get(i).getAge());
				
				list.add(output);
			}
		}

		for (int i = 0; i < list.size(); i++) {
			famillyMembers = new ArrayList<>();
			for (int j = 0; j < persons.size(); j++) {
				if (list.get(i).getLastName().equals(persons.get(j).getLastName())
					&& !list.get(i).getFirstName().equals(persons.get(j).getFirstName())) {

					Output output = new Output();
					
					output.setFirstName(persons.get(j).getFirstName());
					output.setLastName(persons.get(j).getLastName());
					output.setAge(persons.get(j).getAge());
					
					famillyMembers.add(output);
				}
			}
			list.get(i).setFamillyMembers(famillyMembers);
		}

		return list;
	}

	/**
	 * @see com.safetynet.alerts.dao.OutputDao#retrievePhoneNumbersByFireStationNumber(int)
	 */
	@Override
	public List<Output> retrievePhoneNumbersByFireStationNumber(int firestation) {

		List<Output> dataList = dataManagement.load();
		List<Output> list = new ArrayList<>();

		for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).getFireStationNumber() == firestation) {
				Output output = new Output();
				output.setPhone(dataList.get(i).getPhone());
				list.add(output);
			}
		}

		return list;
	}

	/**
	 * @see com.safetynet.alerts.dao.OutputDao#retrievePersonsByAddress(String)
	 */
	@Override
	public List<Output> retrievePersonsByAddress(String address) {

		List<Output> dataList = dataManagement.load();
		List<Output> list = new ArrayList<>();

		for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).getAddress().equals(address)) {

				Output output = new Output();

				output.setLastName(dataList.get(i).getLastName());
				output.setPhone(dataList.get(i).getPhone());
				output.setFireStationNumber(dataList.get(i).getFireStationNumber());
				output.setAge(dataList.get(i).getAge());
				output.setMedications(dataList.get(i).getMedications());
				output.setAllergies(dataList.get(i).getAllergies());

				list.add(output);
			}
		}

		return list;
	}

	/**
	 * @see com.safetynet.alerts.dao.OutputDao#retrievePersonsByFireStationNumbers(List)
	 */
	@Override
	public List<Output> retrievePersonsByFireStationNumbers(List<Integer> stations) {

		List<Output> dataList = dataManagement.load();
		List<Output> list = new ArrayList<>();

		for (int i = 0; i < stations.size(); i++) {
			for (int j = 0; j < dataList.size(); j++) {
				if (dataList.get(j).getFireStationNumber() == stations.get(i)) {

					Output output = new Output();

					output.setLastName(dataList.get(j).getLastName());
					output.setAddress(dataList.get(j).getAddress());
					output.setPhone(dataList.get(j).getPhone());
					output.setAge(dataList.get(j).getAge());
					output.setMedications(dataList.get(j).getMedications());
					output.setAllergies(dataList.get(j).getAllergies());

					list.add(output);
				}
			}
		}

		return list;
	}

	/**
	 * @see com.safetynet.alerts.dao.OutputDao#retrievePersonByFirstAndLastName(String, String)
	 */
	@Override
	public List<Output> retrievePersonByFirstAndLastName(String firstName, String lastName) {

		List<Output> dataList = dataManagement.load();
		List<Output> list = new ArrayList<>();

		for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).getFirstName().equals(firstName) && dataList.get(i).getLastName().equals(lastName)
				|| !dataList.get(i).getFirstName().equals(firstName) && dataList.get(i).getLastName().equals(lastName)) {
				
				Output output = new Output();

				output.setLastName(dataList.get(i).getLastName());
				output.setAddress(dataList.get(i).getAddress());
				output.setAge(dataList.get(i).getAge());
				output.setEmail(dataList.get(i).getEmail());
				output.setMedications(dataList.get(i).getMedications());
				output.setAllergies(dataList.get(i).getAllergies());

				list.add(output);
			}
		}

		return list;
	}

	/**
	 * @see com.safetynet.alerts.dao.OutputDao#retrieveEmailsByCity(String city)
	 */
	@Override
	public List<Output> retrieveEmailsByCity(String city) {

		List<Output> dataList = dataManagement.load();
		List<Output> list = new ArrayList<>();

		for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).getCity().equals(city)) {
				Output output = new Output();
				output.setEmail(dataList.get(i).getEmail());
				list.add(output);
			}
		}
		
		return list;
	}

}
