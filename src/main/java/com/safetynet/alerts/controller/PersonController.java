package com.safetynet.alerts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.alerts.exceptions.ResourceException;
import com.safetynet.alerts.exceptions.ResourceException2;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@RestController
public class PersonController {

	@GetMapping(value = "/persons")
	public List<Person> get() {

		PersonService personService = new PersonService();

		return personService.getAllPersons();

	}

	@PostMapping(value = "/person")
	public void post(@Valid @RequestBody Person person, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (Object object : bindingResult.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;
					details.add(fieldError.getDefaultMessage());
				}
			}
			throw new ResourceException2(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		PersonService personService = new PersonService();

		if (personService.addAPerson(person)) {
			throw new ResourceException(HttpStatus.CREATED, "The person was added to the list");
		} else {
			throw new ResourceException(HttpStatus.BAD_REQUEST,
					"A person with the same first and lastname already exist");
		}

	}

	@PutMapping(value = "/person")
	public void put(@Valid @RequestBody Person person, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (Object object : bindingResult.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;
					details.add(fieldError.getDefaultMessage());
				}
			}
			throw new ResourceException2(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		PersonService personService = new PersonService();

		if (personService.updateAPerson(person)) {
			throw new ResourceException(HttpStatus.OK, "The person was updated in the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "This person does not exist in the list");
		}

	}

	@DeleteMapping(value = "/person")
	public void delete(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {

		PersonService personService = new PersonService();

		if (firstName.isEmpty() && lastName.isEmpty()) {
			throw new ResourceException(HttpStatus.BAD_REQUEST, "You didn't provide a firstname and a lastname");
		}

		if (personService.deleteAPerson(firstName, lastName)) {
			throw new ResourceException(HttpStatus.OK, "The person was deleted from the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no person with the firstname : " + (firstName.isEmpty() ? "\"null value\"" : firstName)
							+ " and the lastname : " + (lastName.isEmpty() ? "\"null value\"" : lastName)
							+ " in the list");
		}

	}

}
