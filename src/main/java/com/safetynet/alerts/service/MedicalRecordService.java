package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dao.MedicalRecordDaoImpl;
import com.safetynet.alerts.model.MedicalRecord;

public class MedicalRecordService {

	public List<MedicalRecord> getAllMedicalRecords() {

		MedicalRecordDaoImpl medicalRecordDaoImpl = new MedicalRecordDaoImpl();

		return medicalRecordDaoImpl.retrieveAllMedicalRecordsFromTheList();

	}

	public void addAMedicalRecord(MedicalRecord medicalRecord) {

		MedicalRecordDaoImpl medicalRecordDaoImpl = new MedicalRecordDaoImpl();

		medicalRecordDaoImpl.addAMedicalRecordToTheList(medicalRecord);

	}

	public void updateAMedicalRecord(MedicalRecord medicalRecord) {

		MedicalRecordDaoImpl medicalRecordDaoImpl = new MedicalRecordDaoImpl();

		medicalRecordDaoImpl.updateAMedicalRecordInTheList(medicalRecord);

	}

	public void deleteAMedicalRecord(String firstName, String lastName) {

		MedicalRecordDaoImpl medicalRecordDaoImpl = new MedicalRecordDaoImpl();

		medicalRecordDaoImpl.deleteAMedicalRecordFromTheList(firstName, lastName);

	}

}
