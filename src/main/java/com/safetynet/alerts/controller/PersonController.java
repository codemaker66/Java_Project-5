package com.safetynet.alerts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

	@GetMapping(value = "/persons")
	public List<Person> get() {

		return personService.getAllPersons();

	}

	@PostMapping(value = "/person")
	public ResponseEntity<String> post(@Valid @RequestBody Person person, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		if (personService.addAPerson(person)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("The person was added to the list");
		} else {
			throw new ResourceException(HttpStatus.BAD_REQUEST,
					"A person with the same first and lastname already exist");
		}

	}

	@PutMapping(value = "/person")
	public ResponseEntity<String> put(@Valid @RequestBody Person person, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		if (personService.updateAPerson(person)) {
			return ResponseEntity.status(HttpStatus.OK).body("The person was updated in the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "This person does not exist in the list");
		}

	}

	@DeleteMapping(value = "/person")
	public ResponseEntity<String> delete(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {

		if (personService.deleteAPerson(firstName, lastName)) {
			return ResponseEntity.status(HttpStatus.OK).body("The person was deleted from the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no person with the firstname : " + (firstName.isEmpty() ? "\"null value\"" : firstName)
							+ " and the lastname : " + (lastName.isEmpty() ? "\"null value\"" : lastName)
							+ " in the list");
		}

	}

}
