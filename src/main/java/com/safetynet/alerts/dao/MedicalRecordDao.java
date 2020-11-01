package com.safetynet.alerts.dao;

import java.util.List;
import com.safetynet.alerts.model.MedicalRecord;

public interface MedicalRecordDao {

	/**
	 * This method retrieve all medical records from the list.
	 * 
	 * @return a list that contain all the medical records.
	 */
	public List<MedicalRecord> retrieveAllMedicalRecordsFromTheList();

	/**
	 * This method add a medical record to the list.
	 * 
	 * @param medicalRecord is an object of type MedicalRecord that contain the data of a medical record.
	 * @return true if the medical record was added to the list.
	 */
	public boolean addAMedicalRecordToTheList(MedicalRecord medicalRecord);

	/**
	 * This method update a medical record in the list.
	 * 
	 * @param medicalRecord is an object of type MedicalRecord that contain the data of a medical record.
	 * @return true if the medical record was updated in the list.
	 */
	public boolean updateAMedicalRecordInTheList(MedicalRecord medicalRecord);

	/**
	 * This method delete a medical record from the list.
	 * 
	 * @param firstName represent the first name of a person.
	 * @param lastName represent the last name of a person.
	 * @return true if the medical record was deleted from the list.
	 */
	public boolean deleteAMedicalRecordFromTheList(String firstName, String lastName);

}
