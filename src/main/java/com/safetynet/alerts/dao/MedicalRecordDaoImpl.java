package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.MedicalRecord;

public class MedicalRecordDaoImpl implements MedicalRecordDao {
	
	private List<MedicalRecord> medicalRecords = Data.instance().getMedicalRecords();

	/**
	 * @see com.safetynet.alerts.dao.MedicalRecordDao#retrieveAllMedicalRecordsFromTheList()
	 */
	@Override
	public List<MedicalRecord> retrieveAllMedicalRecordsFromTheList() {
		return medicalRecords;
	}

	/**
	 * @see com.safetynet.alerts.dao.MedicalRecordDao#addAMedicalRecordToTheList(MedicalRecord)
	 */
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

	/**
	 * @see com.safetynet.alerts.dao.MedicalRecordDao#updateAMedicalRecordInTheList(MedicalRecord)
	 */
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

	/**
	 * @see com.safetynet.alerts.dao.MedicalRecordDao#deleteAMedicalRecordFromTheList(String, String)
	 */
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
