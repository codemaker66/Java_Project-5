package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	@GetMapping(value = "/medicalRecord")
	public List<MedicalRecord> get() {

		MedicalRecordService medicalRecordService = new MedicalRecordService();

		return medicalRecordService.getAllMedicalRecords();

	}

	@PostMapping(value = "/medicalRecord")
	public void post(@RequestBody MedicalRecord medicalRecord) {

		MedicalRecordService medicalRecordService = new MedicalRecordService();

		medicalRecordService.addAMedicalRecord(medicalRecord);

	}

	@PutMapping(value = "/medicalRecord")
	public void put(@RequestBody MedicalRecord medicalRecord) {

		MedicalRecordService medicalRecordService = new MedicalRecordService();

		medicalRecordService.updateAMedicalRecord(medicalRecord);

	}

	@DeleteMapping(value = "/medicalRecord")
	public void delete(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {

		MedicalRecordService medicalRecordService = new MedicalRecordService();

		medicalRecordService.deleteAMedicalRecord(firstName, lastName);

	}

}
