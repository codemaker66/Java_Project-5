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
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import com.safetynet.alerts.utils.Util;

@RestController
public class MedicalRecordController {

	private MedicalRecordService medicalRecordService = new MedicalRecordService();

	/**
	 * This method call the medicalRecordService to get all the medical records.
	 * 
	 * @return a list that contain all the medical records.
	 */
	@GetMapping(value = "/medicalRecords")
	public List<MedicalRecord> get() {

		return medicalRecordService.getAllMedicalRecords();

	}

	/**
	 * This method call the medicalRecordService to add a medical record.
	 * 
	 * @param medicalRecord is an object of type MedicalRecord that contain the data of a medical record.
	 * @param bindingResult general interface that represents binding results.
	 * @return a ResponseEntity if the request was successful.
	 */
	@PostMapping(value = "/medicalRecord")
	public ResponseEntity<String> post(@Valid @RequestBody MedicalRecord medicalRecord, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		Util util = new Util();

		if (!util.isValid(medicalRecord.getBirthdate())) {
			throw new ResourceException(HttpStatus.BAD_REQUEST,
					"The date provided is null or not valid, must be of format : \"dd/MM/yyyy\"");
		}

		if (medicalRecordService.addAMedicalRecord(medicalRecord)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("The medicalrecord was added to the list");
		} else {
			throw new ResourceException(HttpStatus.BAD_REQUEST,
					"A medicalrecord with the same first and lastname already exist");
		}
	}

	/**
	 * This method call the medicalRecordService to update a medical record.
	 * 
	 * @param medicalRecord is an object of type MedicalRecord that contain the data of a medical record.
	 * @param bindingResult general interface that represents binding results.
	 * @return a ResponseEntity if the request was successful.
	 */
	@PutMapping(value = "/medicalRecord")
	public ResponseEntity<String> put(@Valid @RequestBody MedicalRecord medicalRecord, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		Util util = new Util();

		if (!util.isValid(medicalRecord.getBirthdate())) {
			throw new ResourceException(HttpStatus.BAD_REQUEST,
					"The date provided is null or not valid, must be of format : \"dd/MM/yyyy\"");
		}

		if (medicalRecordService.updateAMedicalRecord(medicalRecord)) {
			return ResponseEntity.status(HttpStatus.OK).body("The medicalrecord was updated in the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "This medicalrecord does not exist in the list");
		}

	}

	/**
	 * This method call the medicalRecordService to delete a medical record.
	 * 
	 * @param firstName represent the first name of a person.
	 * @param lastName  represent the last name of a person.
	 * @return a ResponseEntity if the request was successful.
	 */
	@DeleteMapping(value = "/medicalRecord")
	public ResponseEntity<String> delete(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {

		if (medicalRecordService.deleteAMedicalRecord(firstName, lastName)) {
			return ResponseEntity.status(HttpStatus.OK).body("The medicalrecord was deleted from the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no medicalrecord for a person with the firstname : "
							+ (firstName.isEmpty() ? "\"null value\"" : firstName) + " and the lastname : "
							+ (lastName.isEmpty() ? "\"null value\"" : lastName) + " in the list");
		}

	}

}
