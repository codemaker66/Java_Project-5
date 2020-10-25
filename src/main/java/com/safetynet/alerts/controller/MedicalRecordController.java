package com.safetynet.alerts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private MedicalRecordService medicalRecordService;
	private static final Logger logger = LogManager.getLogger(MedicalRecordController.class);

	/**
	 * This method call the medicalRecordService to get all the medical records.
	 * 
	 * @return a list that contain all the medical records.
	 */
	@GetMapping(value = "/medicalRecords")
	public List<MedicalRecord> get() {
		
		logger.info("The user requested the url : /medicalRecords with the GET method");
		
		logger.info("Httpstatus : " + HttpStatus.OK + ", Message : Response received with success");

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
		
		logger.info("The user requested the url : /medicalRecord with the POST method");

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "validation failed", details);
		}
		
		Util util = new Util();

		if (!util.isValid(medicalRecord.getBirthdate())) {
			throw new ResourceException(HttpStatus.BAD_REQUEST,
					"The date provided is null or not valid, must be of format : \"dd/MM/yyyy\"");
		}

		if (medicalRecordService.addAMedicalRecord(medicalRecord)) {
			logger.info("Httpstatus : " + HttpStatus.CREATED + ", Message : The medicalrecord was added to the list");
			return ResponseEntity.status(HttpStatus.CREATED).body("The medicalrecord was added to the list");
		} else {
			throw new ResourceException(HttpStatus.BAD_REQUEST,
					"A medicalrecord with the same first and last name already exist");
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
		
		logger.info("The user requested the url : /medicalRecord with the PUT method");

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "validation failed", details);
		}
		
		Util util = new Util();

		if (!util.isValid(medicalRecord.getBirthdate())) {
			throw new ResourceException(HttpStatus.BAD_REQUEST,
					"The date provided is null or not valid, must be of format : \"dd/MM/yyyy\"");
		}

		if (medicalRecordService.updateAMedicalRecord(medicalRecord)) {
			logger.info("Httpstatus : " + HttpStatus.OK + ", Message : The medicalrecord was updated in the list");
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
		
		logger.info("The user requested the url : /medicalRecord with the DELETE method");

		if (medicalRecordService.deleteAMedicalRecord(firstName, lastName)) {
			logger.info("Httpstatus : " + HttpStatus.OK + ", Message : The medicalrecord was deleted from the list");
			return ResponseEntity.status(HttpStatus.OK).body("The medicalrecord was deleted from the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no medicalrecord for a person with the first name : "
							+ (firstName.isEmpty() ? "\"null value\"" : firstName) + " and the last name : "
							+ (lastName.isEmpty() ? "\"null value\"" : lastName) + " in the list");
		}

	}

}
