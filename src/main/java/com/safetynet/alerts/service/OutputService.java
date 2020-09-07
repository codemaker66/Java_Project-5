package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.safetynet.alerts.dao.OutputDaoImpl;
import com.safetynet.alerts.model.Person;

public class OutputService {
	
	public List<Object> findPersonsByFireStationNumber(String stationNumber) {
		
		OutputDaoImpl output = new OutputDaoImpl();
	      
	    List<Person> persons = output.retrievePersonsByFireStationNumber(stationNumber);
			
		List<Object> list = new ArrayList<>();
		List<String> count = new ArrayList<>();
		int childrensCount = 0;
		int adultsCount = 0;
			
		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getAge() <= 18) {
				childrensCount++;
			} else {
				adultsCount++;
			}
		}
			
		list.add(persons);
		count.add("Childrens count : " + childrensCount);
		count.add("Adults count : " + adultsCount);
		
		list.add(count);
		
		return list;
	}
	
	public List<Person> findChildrensByAddress(String address) {
		
		OutputDaoImpl output = new OutputDaoImpl();
	      
	    List<Person> persons = output.retrievePersonsByAddress(address);
		
		List<Person> list = new ArrayList<>();
		List<Person> famillyMembers;
		
		for (int i = 0; i < persons.size(); i++) {
			if(persons.get(i).getAge() <= 18) {
				list.add(persons.get(i));
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			famillyMembers = new ArrayList<>();
			for (int j = 0; j < persons.size(); j++) {
				
				if(list.get(i).getLastName().equals(persons.get(j).getLastName())
					&& !list.get(i).getFirstName().equals(persons.get(j).getFirstName())) {
					Person person = new Person();
					person.setFirstName(persons.get(j).getFirstName());
					person.setLastName(persons.get(j).getLastName());
					person.setAge(persons.get(j).getAge());
					famillyMembers.add(person);
				}
				
			}
			
			list.get(i).setFamillyMembers(famillyMembers);
			
		}
		
		return list;
	}
	
	public List<Person> findPhoneNumbersByFireStationNumber(String firestation) {
		
		OutputDaoImpl output = new OutputDaoImpl();
	      
	    List<Person> list = output.retrievePersonsByFireStationNumber(firestation);
		
		return list;
	}
	
	public List<Object> findPersonsByAddress(String address) {
		
		OutputDaoImpl output = new OutputDaoImpl();
	      
	    List<Person> persons = output.retrievePersonsByAddress(address);
		
		List<Object> list = new ArrayList<>();
		List<String> fireStation = new ArrayList<>();
		
		if (persons.size() == 1) {
			fireStation.add("This person is served by the firestaion number : " + 
					persons.get(0).getFireStationNumber());
		} else {
			fireStation.add("These persons are served by the firestaion number : " + 
					persons.get(0).getFireStationNumber());
		}
		
		list.add(fireStation);
		
		list.add(persons);
		
		return list;
		
	}
	
	public List<Object> findPersonsByFireStationNumbers(List<String> stations) {
		
		OutputDaoImpl output = new OutputDaoImpl();
	      
	    List<Person> persons = output.retrievePersonsByFireStationNumbers(stations);
		
		List<Object> list = new ArrayList<>();
		List<String> string;
		
		Map<String, List<Person>> map = 
			    persons.stream().collect(Collectors.groupingBy(Person::getAddress));
		
		
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
	
	public List<Person> findPersonByFirstAndLastName(String firstName, String lastName) {
		
		OutputDaoImpl output = new OutputDaoImpl();
	      
	    List<Person> list = output.retrievePersonByFirstAndLastName(firstName, lastName);
		
		return list;
	}
	
	public List<Person> findEmailsByCity(String city) {
		OutputDaoImpl output = new OutputDaoImpl();
	      
	    List<Person> list = output.retrieveEmailsByCity(city);
		
		return list;
	}

}
