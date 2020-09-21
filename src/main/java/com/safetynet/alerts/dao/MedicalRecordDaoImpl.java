package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.MedicalRecord;

public class MedicalRecordDaoImpl implements MedicalRecordDao {

	@Override
	public List<MedicalRecord> retrieveAllMedicalRecordsFromTheList() {
		return Data.medicalRecords;
	}

	@Override
	public boolean addAMedicalRecordToTheList(MedicalRecord medicalRecord) {

		boolean check = true;

		for (int i = 0; i < Data.medicalRecords.size(); i++) {
			if (Data.medicalRecords.get(i).getFirstName().equals(medicalRecord.getFirstName())
					&& Data.medicalRecords.get(i).getLastName().equals(medicalRecord.getLastName())) {
				check = false;
				break;
			}
		}

		if (check == true) {
			Data.medicalRecords.add(medicalRecord);
			return check;
		}

		return check;

	}

	@Override
	public boolean updateAMedicalRecordInTheList(MedicalRecord medicalRecord) {

		boolean check = false;

		for (int i = 0; i < Data.medicalRecords.size(); i++) {
			if (Data.medicalRecords.get(i).getFirstName().equals(medicalRecord.getFirstName())
					&& Data.medicalRecords.get(i).getLastName().equals(medicalRecord.getLastName())) {
				Data.medicalRecords.get(i).setBirthdate(medicalRecord.getBirthdate());
				Data.medicalRecords.get(i).setMedications(medicalRecord.getMedications());
				Data.medicalRecords.get(i).setAllergies(medicalRecord.getAllergies());
				check = true;
				break;
			}
		}

		return check;

	}

	@Override
	public boolean deleteAMedicalRecordFromTheList(String firstName, String lastName) {

		boolean check = false;

		for (int i = 0; i < Data.medicalRecords.size(); i++) {
			if (Data.medicalRecords.get(i).getFirstName().equals(firstName)
					&& Data.medicalRecords.get(i).getLastName().equals(lastName)) {
				Data.medicalRecords.remove(i);
				check = true;
				break;
			}
		}

		return check;

	}

}
