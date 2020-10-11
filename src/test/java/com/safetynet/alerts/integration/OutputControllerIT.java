package com.safetynet.alerts.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.controller.OutputController;
import com.safetynet.alerts.data.DataManagement;
import com.safetynet.alerts.model.Output;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(OutputController.class)
class OutputControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeAll
	static void init() throws IOException {
		DataManagement dataManagement = new DataManagement();
		
		dataManagement.init();
	}
	
	@BeforeEach
	void noFilter() {
		objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
	}
	
	@Test
	void retrievePersonsByFireStationNumber() throws Exception {
		
		Output person = new Output();
		
		person.setFirstName("Lily");
		person.setLastName("Cooper");
		person.setAddress("489 Manchester St");
		person.setPhone("841-874-9845");
		
		List<Output> list = new ArrayList<>();
		
		list.add(person);
		
		Output data = new Output();
		data.setPersons(list);
		data.setAdultsCount(1);
		
		MvcResult mvcResult = mockMvc.perform(
				get("/firestation?stationNumber=4")).andExpect(status().isOk()).andReturn();
		
		String Actual = mvcResult.getResponse().getContentAsString();
		
	    String expect = objectMapper.writeValueAsString(data);
	    
		assertThat(expect).isEqualToIgnoringWhitespace(Actual);
	}
	
	@Test
	void retrievePersonsByFireStationNumberWithError() throws Exception {
		
		mockMvc.perform(
				get("/firestation?stationNumber=44")).andExpect(status().isNotFound()).andExpect(content().string("There are no persons served by the fire station number : 44"));
	}
	
	@Test
	void retrieveChildrenByAddress() throws Exception {
		
		Output child = new Output();
		
		child.setFirstName("Zach");
		child.setLastName("Zemicks");
		child.setAge(3);
		
		Output father = new Output();
		
		father.setFirstName("Warren");
		father.setLastName("Zemicks");
		father.setAge(35);
		
		Output mother = new Output();
		
		mother.setFirstName("Sophia");
		mother.setLastName("Zemicks");
		mother.setAge(32);
		
		List<Output> famillyMembers = new ArrayList<>();
		famillyMembers.add(mother);
		famillyMembers.add(father);
		
		
		child.setFamillyMembers(famillyMembers);
		
		List<Output> list = new ArrayList<>();
		
		list.add(child);
		
		Output data = new Output();
		data.setChildren(list);

		MvcResult mvcResult = mockMvc.perform(
				get("/childAlert?address=892 Downing Ct")).andExpect(status().isOk()).andReturn();
		
		String Actual = mvcResult.getResponse().getContentAsString();
		
	    String expect = objectMapper.writeValueAsString(data);
	    
		assertThat(expect).isEqualToIgnoringWhitespace(Actual);
		
	}
	
	@Test
	void retrieveChildrenByAddressWithErrors() throws Exception {
		
		mockMvc.perform(
				get("/childAlert?address=random address name")).andExpect(status().isNotFound()).andExpect(content().string("There are no children living at this address : random address name"));
	}
	
	@Test
	void retrievePhoneNumbersByFireStationNumber() throws Exception {
		
		Output phone = new Output();
		
		phone.setPhone("841-874-9845");
		
		List<Output> list = new ArrayList<>();
		
		list.add(phone);
		
		Output data = new Output();
		data.setPhoneNumbers(list);

		MvcResult mvcResult = mockMvc.perform(
				get("/phoneAlert?firestation=4")).andExpect(status().isOk()).andReturn();
		
		String Actual = mvcResult.getResponse().getContentAsString();
		
	    String expect = objectMapper.writeValueAsString(data);
	    
		assertThat(expect).isEqualToIgnoringWhitespace(Actual);
	}
	
	@Test
	void retrievePhoneNumbersByFireStationNumberWithErrors() throws Exception {
		
		mockMvc.perform(
				get("/phoneAlert?firestation=44")).andExpect(status().isNotFound()).andExpect(content().string("There are no phone numbers registered by the fire station number : 44"));
	}
	
	@Test
	void retrievePersonsByAddress()  throws Exception {
		
		List<String> medications = new ArrayList<>();
		
		List<String> allergies = new ArrayList<>();
		
		medications.add("tradoxidine:400mg");

		Output person = new Output();
		
		person.setLastName("Cadigan");
		person.setPhone("841-874-7458");
		person.setAge(75);
		person.setMedications(medications);
		person.setAllergies(allergies);
		
		
		List<Output> list = new ArrayList<>();
		
		list.add(person);
		
		Output data = new Output();
		data.setFireStation("This person is served by the firestaion number : 2");
		data.setPersons(list);

		MvcResult mvcResult = mockMvc.perform(
				get("/fire?address=951 LoneTree Rd")).andExpect(status().isOk()).andReturn();
		
		String Actual = mvcResult.getResponse().getContentAsString();
		
	    String expect = objectMapper.writeValueAsString(data);
	    
		assertThat(expect).isEqualToIgnoringWhitespace(Actual);
		
	}
	
	@Test
	void retrievePersonsByAddressWithErrors() throws Exception {
		
		mockMvc.perform(
				get("/fire?address=")).andExpect(status().isNotFound()).andExpect(content().string("You didn't provide an address"));
		
	}
	
	@Test
	void retrievePersonsByFireStationNumbers() throws Exception {
		
		List<String> medications = new ArrayList<>();
		
		List<String> allergies = new ArrayList<>();
		
		medications.add("tradoxidine:400mg");

		Output person = new Output();
		
		person.setLastName("Cadigan");
		person.setAddress("951 LoneTree Rd");
		person.setPhone("841-874-7458");
		person.setAge(75);
		person.setMedications(medications);
		person.setAllergies(allergies);
		
		List<Output> list = new ArrayList<>();
		
		list.add(person);
		
		
		
		List<String> medications2 = new ArrayList<>();
		
		List<String> allergies2 = new ArrayList<>();

		Output person2 = new Output();
		
		person2.setLastName("Cooper");
		person2.setAddress("489 Manchester St");
		person2.setPhone("841-874-9845");
		person2.setAge(26);
		person2.setMedications(medications2);
		person2.setAllergies(allergies2);
		
		
		List<Output> list2 = new ArrayList<>();
		
		list2.add(person2);
		
		
		
		List<String> medications3 = new ArrayList<>();
		
		medications3.add("aznol:60mg");
		medications3.add("hydrapermazol:900mg");
		medications3.add("pharmacol:5000mg");
		medications3.add("terazine:500mg");
		
		List<String> allergies3 = new ArrayList<>();
		
		allergies3.add("peanut");
		allergies3.add("shellfish");
		allergies3.add("aznol");

		Output person3 = new Output();
		
		person3.setLastName("Zemicks");
		person3.setAddress("892 Downing Ct");
		person3.setPhone("841-874-7878");
		person3.setAge(32);
		person3.setMedications(medications3);
		person3.setAllergies(allergies3);
		

		List<String> medications4 = new ArrayList<>();
		
		List<String> allergies4 = new ArrayList<>();

		Output person4 = new Output();
		
		person4.setLastName("Zemicks");
		person4.setAddress("892 Downing Ct");
		person4.setPhone("841-874-7512");
		person4.setAge(35);
		person4.setMedications(medications4);
		person4.setAllergies(allergies4);
		
		
		List<String> medications5 = new ArrayList<>();
		
		List<String> allergies5 = new ArrayList<>();

		Output person5 = new Output();
		
		person5.setLastName("Zemicks");
		person5.setAddress("892 Downing Ct");
		person5.setPhone("841-874-7512");
		person5.setAge(3);
		person5.setMedications(medications5);
		person5.setAllergies(allergies5);
		
		
		List<Output> list5 = new ArrayList<>();
		
		list5.add(person3);
		list5.add(person4);
		list5.add(person5);
		
		
		List<String> medications6 = new ArrayList<>();
		
		List<String> allergies6 = new ArrayList<>();

		Output person6 = new Output();
		
		person6.setLastName("Marrack");
		person6.setAddress("29 15th St");
		person6.setPhone("841-874-6513");
		person6.setAge(31);
		person6.setMedications(medications6);
		person6.setAllergies(allergies6);
		
		
		List<Output> list6 = new ArrayList<>();
		
		list6.add(person6);
		
		List<List<Output>> allLists = new ArrayList<>();
		
		allLists.add(list);
		allLists.add(list2);
		allLists.add(list5);
		allLists.add(list6);
		
		Output data = new Output();
		data.setPersonsGroupedByAddress(allLists);

		MvcResult mvcResult = mockMvc.perform(
				get("/flood/stations?stations=4,2")).andExpect(status().isOk()).andReturn();
		
		String Actual = mvcResult.getResponse().getContentAsString();
		
	    String expect = objectMapper.writeValueAsString(data);
	    
		assertThat(expect).isEqualToIgnoringWhitespace(Actual);
		
	}
	
	@Test
	void retrievePersonsByFireStationNumbersWithErrors() throws Exception {
		
		mockMvc.perform(
				get("/flood/stations?stations=6,10")).andExpect(status().isNotFound()).andExpect(content().string("There are no persons served by these fire station numbers : [6, 10]"));
		
	}
	
	@Test
	void retrievePersonByFirstAndLastName() throws Exception {
		
		List<String> medications = new ArrayList<>();
		
		List<String> allergies = new ArrayList<>();

		Output person = new Output();
		
		person.setLastName("Shepard");
		person.setAddress("748 Townings Dr");
		person.setEmail("jaboyd@email.com");
		person.setAge(40);
		person.setMedications(medications);
		person.setAllergies(allergies);
		
		
		List<Output> list = new ArrayList<>();
		
		list.add(person);
		
		Output data = new Output();
		data.setPersons(list);

		MvcResult mvcResult = mockMvc.perform(
				get("/personInfo?firstName=Foster&lastName=Shepard")).andExpect(status().isOk()).andReturn();
		
		String Actual = mvcResult.getResponse().getContentAsString();
		
	    String expect = objectMapper.writeValueAsString(data);
		assertThat(expect).isEqualToIgnoringWhitespace(Actual);
		
	}
	
	@Test
	void retrievePersonByFirstAndLastNameWithErrors() throws Exception {

		mockMvc.perform(
				get("/personInfo?firstName=Foster&lastName=")).andExpect(status().isNotFound()).andExpect(content().string("There are no person with the firstname : Foster and the lastname \"null value\" in the list"));
		
	}
	
	@Test
	void retrieveEmailsByCity() throws Exception {
		
		Output email1 = new Output();
		
		email1.setEmail("jaboyd@email.com");
		
		Output email2 = new Output();
		
		email2.setEmail("drk@email.com");
		
		Output email3 = new Output();
		
		email3.setEmail("tenz@email.com");
		
		Output email4 = new Output();
		
		email4.setEmail("jaboyd@email.com");
		
		Output email5 = new Output();
		
		email5.setEmail("jaboyd@email.com");
		
		Output email6 = new Output();
		
		email6.setEmail("drk@email.com");
		
		Output email7 = new Output();
		
		email7.setEmail("tenz@email.com");
		
		Output email8 = new Output();
		
		email8.setEmail("jaboyd@email.com");
		
		Output email9 = new Output();
		
		email9.setEmail("jaboyd@email.com");
		
		Output email10 = new Output();
		
		email10.setEmail("tcoop@ymail.com");
		
		Output email11 = new Output();
		
		email11.setEmail("lily@email.com");
		
		Output email12 = new Output();
		
		email12.setEmail("soph@email.com");
		
		Output email13 = new Output();
		
		email13.setEmail("ward@email.com");
		
		Output email14 = new Output();
		
		email14.setEmail("zarc@email.com");
		
		Output email15 = new Output();
		
		email15.setEmail("reg@email.com");
		
		Output email16 = new Output();
		
		email16.setEmail("jpeter@email.com");
		
		Output email17 = new Output();
		
		email17.setEmail("jpeter@email.com");
		
		Output email18 = new Output();
		
		email18.setEmail("aly@imail.com");
		
		Output email19 = new Output();
		
		email19.setEmail("bstel@email.com");
		
		Output email20 = new Output();
		
		email20.setEmail("ssanw@email.com");
		
		Output email21 = new Output();
		
		email21.setEmail("bstel@email.com");
		
		Output email22 = new Output();
		
		email22.setEmail("clivfd@ymail.com");
		
		Output email23 = new Output();
		
		email23.setEmail("gramps@email.com");
		
		
		List<Output> list = new ArrayList<>();
		
		list.add(email1);
		list.add(email2);
		list.add(email3);
		list.add(email4);
		list.add(email5);
		list.add(email6);
		list.add(email7);
		list.add(email8);
		list.add(email9);
		list.add(email10);
		list.add(email11);
		list.add(email12);
		list.add(email13);
		list.add(email14);
		list.add(email15);
		list.add(email16);
		list.add(email17);
		list.add(email18);
		list.add(email19);
		list.add(email20);
		list.add(email21);
		list.add(email22);
		list.add(email23);
		
		Output data = new Output();
		data.setEmails(list);
		
		MvcResult mvcResult = mockMvc.perform(
				get("/communityEmail?city=Culver")).andExpect(status().isOk()).andReturn();
		
		String Actual = mvcResult.getResponse().getContentAsString();
		
	    String expect = objectMapper.writeValueAsString(data);
		assertThat(expect).isEqualToIgnoringWhitespace(Actual);
		
	}
	
	@Test
	void retrieveEmailsByCityWithErrors() throws Exception {
		 mockMvc.perform(
					get("/communityEmail?city=random city name")).andExpect(status().isNotFound()).andExpect(content().string("There are no emails for this city : random city name"));
	}

}
