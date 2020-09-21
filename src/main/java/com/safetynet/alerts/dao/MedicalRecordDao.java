package com.safetynet.alerts.dao;

import java.util.List;
import com.safetynet.alerts.model.MedicalRecord;

public interface MedicalRecordDao {

	public List<MedicalRecord> retrieveAllMedicalRecordsFromTheList();

	public boolean addAMedicalRecordToTheList(MedicalRecord medicalRecord);

	public boolean updateAMedicalRecordInTheList(MedicalRecord medicalRecord);

	public boolean deleteAMedicalRecordFromTheList(String firstName, String lastName);

}
