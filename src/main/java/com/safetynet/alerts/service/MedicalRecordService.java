package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.dao.MedicalRecordDaoImpl;
import com.safetynet.alerts.model.MedicalRecord;

public class MedicalRecordService {

	private MedicalRecordDaoImpl medicalRecordDaoImpl = new MedicalRecordDaoImpl();

	public List<MedicalRecord> getAllMedicalRecords() {

		return medicalRecordDaoImpl.retrieveAllMedicalRecordsFromTheList();

	}

	public boolean addAMedicalRecord(MedicalRecord medicalRecord) {

		boolean check = false;

		if (medicalRecordDaoImpl.addAMedicalRecordToTheList(medicalRecord)) {
			check = true;
		}

		return check;

	}

	public boolean updateAMedicalRecord(MedicalRecord medicalRecord) {

		boolean check = false;

		if (medicalRecordDaoImpl.updateAMedicalRecordInTheList(medicalRecord)) {
			check = true;
		}

		return check;
	}

	public boolean deleteAMedicalRecord(String firstName, String lastName) {

		boolean check = false;

		if (medicalRecordDaoImpl.deleteAMedicalRecordFromTheList(firstName, lastName)) {
			check = true;
		}

		return check;

	}

}
