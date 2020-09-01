package com.safetynet.alerts.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.Person;

public class OutputDaoImpl implements OutputDao {

	@Override
	public List<Object> findPersonsByFireStationNumber(String stationNumber) {
		
		List<Object> list = new ArrayList<>();
		List<String> count = new ArrayList<>();
		int childrensCount = 0;
		int adultsCount = 0;
		
		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getFireStationNumber().equals(stationNumber)) {
				list.add(Data.persons.get(i));
				if (Data.persons.get(i).getAge() <= 18) {
					childrensCount++;
				} else {
					adultsCount++;
				}
			}
			
			
		}
		
		count.add("Childrens count : " + childrensCount);
		count.add("Adults count : " + adultsCount);
		
		list.add(count);
		
		return list;
	}

	@Override
	public List<Person> findChildrensByAddress(String address) {
		
		List<Person> list = new ArrayList<>();
		List<Person> famillyMembers;
		
		for (int i = 0; i < Data.persons.size(); i++) {
			if(Data.persons.get(i).getAddress().equals(address) && Data.persons.get(i).getAge() <= 18) {
				list.add(Data.persons.get(i));
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			famillyMembers = new ArrayList<>();
			for (int j = 0; j < Data.persons.size(); j++) {
				
				if(list.get(i).getLastName().equals(Data.persons.get(j).getLastName())
					&& !list.get(i).getFirstName().equals(Data.persons.get(j).getFirstName())) {
					Person person = new Person();
					person.setFirstName(Data.persons.get(j).getFirstName());
					person.setLastName(Data.persons.get(j).getLastName());
					person.setAge(Data.persons.get(j).getAge());
					famillyMembers.add(person);
				}
				
			}
			
			list.get(i).setFamillyMembers(famillyMembers);
			
		}
		
		return list;
	}

	@Override
	public List<Person> findPhoneNumbersByFireStationNumber(String firestation) {
		
		List<Person> list = new ArrayList<>();
		
		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getFireStationNumber().equals(firestation)) {
				list.add(Data.persons.get(i));
			}
		}
		
		
		return list;
	}

	@Override
	public List<Object> findPersonsByAddress(String address) {
		
		List<Object> list = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		List<String> fireStation = new ArrayList<>();

		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getAddress().equals(address)) {
				persons.add(Data.persons.get(i));
			}
			
		}
		
		if (persons.size() == 1) {
			fireStation.add("This person is served by the firestaion number : " + 
					persons.get(0).getFireStationNumber());
		} else {
			fireStation.add("These persons are served by the firestaion number : " + 
					persons.get(0).getFireStationNumber());
		}
		
		list.add(fireStation);
		
		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getAddress().equals(address)) {
				list.add(Data.persons.get(i));
			}
			
			
		}
		
		return list;
		
	}

	@Override
	public List<Object> findPersonsByFireStationNumbers(List<String> stations) {
		List<Object> list = new ArrayList<>();
		List<String> string;
		List<Person> person = new ArrayList<>();
		
		for (int i = 0; i < stations.size(); i++) {
			for (int j = 0; j < Data.persons.size(); j++) {
				if (Data.persons.get(j).getFireStationNumber().equals(stations.get(i))) {
					person.add(Data.persons.get(j));
				}
			}
		}
		
		Map<String, List<Person>> map = 
			    person.stream().collect(Collectors.groupingBy(Person::getAddress));
		
		
		for (List<Person> value : map.values()) {
			string = new ArrayList<>();
			if (value.size() == 1) {
				string.add("This person is served by the fire station number : " + value.get(0).getFireStationNumber());
			} else {
				string.add("These persons are served by the firestation number : "  + value.get(0).getFireStationNumber());	
			}
			list.add(string);
			list.add(value);
		}
		
		return list;
	}

	@Override
	public List<Person> findPersonByFirstAndLastName(String lastName) {
		List<Person> person = new ArrayList<>();
		
		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getLastName().equals(lastName)) {
				person.add(Data.persons.get(i));
			}
		}
		
		
		return person;
	}

	@Override
	public List<Person> findEmailsByCity(String city) {
		List<Person> person = new ArrayList<>();
		
		for (int i = 0; i < Data.persons.size(); i++) {
			if (Data.persons.get(i).getCity().equals(city)) {
				person.add(Data.persons.get(i));
			}
		}
		
		
		return person;
	}

	

}
