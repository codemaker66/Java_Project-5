package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.MedicalRecord;

public class MedicalRecordDaoImpl extends Data implements MedicalRecordDao {

	@Override
	public List<MedicalRecord> retrieveAllMedicalRecordsFromTheList() {
		return medicalRecords;
	}

	@Override
	public boolean addAMedicalRecordToTheList(MedicalRecord medicalRecord) {

		boolean check = true;

		for (int i = 0; i < medicalRecords.size(); i++) {
			if (medicalRecords.get(i).getFirstName().equals(medicalRecord.getFirstName())
					&& medicalRecords.get(i).getLastName().equals(medicalRecord.getLastName())) {
				check = false;
				break;
			}
		}

		if (check) {
			medicalRecords.add(medicalRecord);
			return check;
		}

		return check;

	}

	@Override
	public boolean updateAMedicalRecordInTheList(MedicalRecord medicalRecord) {

		boolean check = false;

		for (int i = 0; i < medicalRecords.size(); i++) {
			if (medicalRecords.get(i).getFirstName().equals(medicalRecord.getFirstName())
					&& medicalRecords.get(i).getLastName().equals(medicalRecord.getLastName())) {
				medicalRecords.get(i).setBirthdate(medicalRecord.getBirthdate());
				medicalRecords.get(i).setMedications(medicalRecord.getMedications());
				medicalRecords.get(i).setAllergies(medicalRecord.getAllergies());
				check = true;
				break;
			}
		}

		return check;

	}

	@Override
	public boolean deleteAMedicalRecordFromTheList(String firstName, String lastName) {

		boolean check = false;

		for (int i = 0; i < medicalRecords.size(); i++) {
			if (medicalRecords.get(i).getFirstName().equals(firstName)
					&& medicalRecords.get(i).getLastName().equals(lastName)) {
				medicalRecords.remove(i);
				check = true;
				break;
			}
		}

		return check;

	}

}
