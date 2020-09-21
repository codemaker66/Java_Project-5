package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dao.MedicalRecordDaoImpl;
import com.safetynet.alerts.model.MedicalRecord;

public class MedicalRecordService {

	public List<MedicalRecord> getAllMedicalRecords() {

		MedicalRecordDaoImpl medicalRecordDaoImpl = new MedicalRecordDaoImpl();

		return medicalRecordDaoImpl.retrieveAllMedicalRecordsFromTheList();

	}

	public boolean addAMedicalRecord(MedicalRecord medicalRecord) {

		MedicalRecordDaoImpl medicalRecordDaoImpl = new MedicalRecordDaoImpl();

		if (medicalRecordDaoImpl.addAMedicalRecordToTheList(medicalRecord)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean updateAMedicalRecord(MedicalRecord medicalRecord) {

		MedicalRecordDaoImpl medicalRecordDaoImpl = new MedicalRecordDaoImpl();

		if (medicalRecordDaoImpl.updateAMedicalRecordInTheList(medicalRecord)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean deleteAMedicalRecord(String firstName, String lastName) {

		MedicalRecordDaoImpl medicalRecordDaoImpl = new MedicalRecordDaoImpl();

		if (medicalRecordDaoImpl.deleteAMedicalRecordFromTheList(firstName, lastName)) {
			return true;
		} else {
			return false;
		}

	}

}
