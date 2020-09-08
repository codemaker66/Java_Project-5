package com.safetynet.alerts.dao;

import java.util.List;
import com.safetynet.alerts.model.MedicalRecord;

public interface MedicalRecordDao {

	public List<MedicalRecord> retrieveAllMedicalRecordsFromTheList();

	public void addAMedicalRecordToTheList(MedicalRecord medicalRecord);

	public void updateAMedicalRecordInTheList(MedicalRecord medicalRecord);

	public void deleteAMedicalRecordFromTheList(String firstName, String lastName);

}
