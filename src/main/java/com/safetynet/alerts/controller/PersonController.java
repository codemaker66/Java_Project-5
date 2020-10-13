package com.safetynet.alerts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.exceptions.PropertiesException;
import com.safetynet.alerts.exceptions.ResourceException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@RestController
public class PersonController {

	private PersonService personService = new PersonService();
	private static final Logger logger = LogManager.getLogger(PersonController.class);

	/**
	 * This method call the personService to get all the persons.
	 * 
	 * @return a list that contain all the persons.
	 */
	@GetMapping(value = "/persons")
	public List<Person> get() {
		
		logger.info("The user requested the url : /persons with the GET method");
		
		logger.info("Httpstatus : " + HttpStatus.OK + ", Message : Response received with success");

		return personService.getAllPersons();

	}

	/**
	 * This method call the personService to add a person.
	 * 
	 * @param person is an object of type Person that contain the data of a person.
	 * @param bindingResult general interface that represents binding results.
	 * @return a ResponseEntity if the request was successful.
	 */
	@PostMapping(value = "/person")
	public ResponseEntity<String> post(@Valid @RequestBody Person person, BindingResult bindingResult) {
		
		logger.info("The user requested the url : /person with the POST method");

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		if (personService.addAPerson(person)) {
			logger.info("Httpstatus : " + HttpStatus.CREATED + ", Message : The person was added to the list");
			return ResponseEntity.status(HttpStatus.CREATED).body("The person was added to the list");
		} else {
			throw new ResourceException(HttpStatus.BAD_REQUEST, "A person with the same first and lastname already exist");
		}

	}

	/**
	 * This method call the personService to update a person.
	 * 
	 * @param person is an object of type Person that contain the data of a person.
	 * @param bindingResult general interface that represents binding results.
	 * @return a ResponseEntity if the request was successful.
	 */
	@PutMapping(value = "/person")
	public ResponseEntity<String> put(@Valid @RequestBody Person person, BindingResult bindingResult) {
		
		logger.info("The user requested the url : /person with the PUT method");

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		if (personService.updateAPerson(person)) {
			logger.info("Httpstatus : " + HttpStatus.OK + ", Message : The person was updated in the list");
			return ResponseEntity.status(HttpStatus.OK).body("The person was updated in the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "This person does not exist in the list");
		}

	}

	/**
	 * This method call the personService to delete a person.
	 * 
	 * @param firstName represent the first name of a person.
	 * @param lastName  represent the last name of a person.
	 * @return a ResponseEntity if the request was successful.
	 */
	@DeleteMapping(value = "/person")
	public ResponseEntity<String> delete(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {
		
		logger.info("The user requested the url : /person with the DELETE method");

		if (personService.deleteAPerson(firstName, lastName)) {
			logger.info("Httpstatus : " + HttpStatus.OK + ", Message : The person was deleted from the list");
			return ResponseEntity.status(HttpStatus.OK).body("The person was deleted from the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no person with the firstname : " + (firstName.isEmpty() ? "\"null value\"" : firstName)
							+ " and the lastname : " + (lastName.isEmpty() ? "\"null value\"" : lastName)
							+ " in the list");
		}

	}

}
