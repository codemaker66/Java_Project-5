package com.safetynet.alerts.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	@GetMapping(value = "/medicalRecords")
	public List<MedicalRecord> get() {

		MedicalRecordService medicalRecordService = new MedicalRecordService();

		return medicalRecordService.getAllMedicalRecords();

	}

	@PostMapping(value = "/medicalRecord")
	public void post(@Valid @RequestBody MedicalRecord medicalRecord, BindingResult bindingResult) {

		if (!isValid(medicalRecord.getBirthdate())) {
			throw new ResourceException(HttpStatus.BAD_REQUEST, "The date provided is null or not valid");
		}

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

		MedicalRecordService medicalRecordService = new MedicalRecordService();

		if (medicalRecordService.addAMedicalRecord(medicalRecord)) {
			throw new ResourceException(HttpStatus.CREATED, "The medicalrecord was added to the list");
		} else {
			throw new ResourceException(HttpStatus.BAD_REQUEST,
					"A medicalrecord with the same first and lastname already exist");
		}
	}

	@PutMapping(value = "/medicalRecord")
	public void put(@Valid @RequestBody MedicalRecord medicalRecord, BindingResult bindingResult) {

		if (!isValid(medicalRecord.getBirthdate())) {
			throw new ResourceException(HttpStatus.BAD_REQUEST, "The date provided is null or not valid");
		}

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

		MedicalRecordService medicalRecordService = new MedicalRecordService();

		if (medicalRecordService.updateAMedicalRecord(medicalRecord)) {
			throw new ResourceException(HttpStatus.OK, "The medicalrecord was updated in the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "This medicalrecord does not exist in the list");
		}

	}

	@DeleteMapping(value = "/medicalRecord")
	public void delete(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {

		if (firstName.isEmpty() && lastName.isEmpty()) {
			throw new ResourceException(HttpStatus.BAD_REQUEST, "You didn't provide a firstname and a lastname");
		}

		MedicalRecordService medicalRecordService = new MedicalRecordService();

		if (medicalRecordService.deleteAMedicalRecord(firstName, lastName)) {
			throw new ResourceException(HttpStatus.OK, "The medicalrecord was deleted from the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND,
					"There are no medicalrecord with the firstname : "
							+ (firstName.isEmpty() ? "\"null value\"" : firstName) + " and the lastname : "
							+ (lastName.isEmpty() ? "\"null value\"" : lastName) + " in the list");
		}

	}

	private boolean isValid(String dateStr) {

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
		Date currentDate = new Date();
		Date enteredDate = null;

		try {
			enteredDate = sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}

		if (enteredDate.after(currentDate)) {
			return false;
		} else {
			return true;
		}

	}

}
