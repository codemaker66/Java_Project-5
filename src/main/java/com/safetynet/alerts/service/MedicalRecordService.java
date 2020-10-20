package com.safetynet.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.dao.MedicalRecordDao;
import com.safetynet.alerts.model.MedicalRecord;

@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordDao medicalRecordDaoImpl;
	private static final Logger logger = LogManager.getLogger(MedicalRecordService.class);

	/**
	 * This method call the medicalRecordDaoImpl to retrieve all medical records from the list.
	 * 
	 * @return a list that contain all the medical records.
	 */
	public List<MedicalRecord> getAllMedicalRecords() {

		return medicalRecordDaoImpl.retrieveAllMedicalRecordsFromTheList();

	}

	/**
	 * This method call the medicalRecordDaoImpl to add a medical record to the list.
	 * 
	 * @param medicalRecord is an object of type MedicalRecord that contain the data of a medical record.
	 * @return true if the medical record was added to the list.
	 */
	public boolean addAMedicalRecord(MedicalRecord medicalRecord) {

		boolean check = false;

		if (medicalRecordDaoImpl.addAMedicalRecordToTheList(medicalRecord)) {
			check = true;
		}

		logger.debug("Check is : " + check);
		return check;

	}

	/**
	 * This method call the medicalRecordDaoImpl to update a medical record in the list.
	 * 
	 * @param medicalRecord is an object of type MedicalRecord that contain the data of a medical record.
	 * @return true if the medical record was updated in the list.
	 */
	public boolean updateAMedicalRecord(MedicalRecord medicalRecord) {

		boolean check = false;

		if (medicalRecordDaoImpl.updateAMedicalRecordInTheList(medicalRecord)) {
			check = true;
		}

		logger.debug("Check is : " + check);
		return check;
	}

	/**
	 * This method call the medicalRecordDaoImpl to delete a medical record from the list.
	 * 
	 * @param firstName represent the first name of a person.
	 * @param lastName  represent the last name of a person.
	 * @return true if the medical record was deleted from the list.
	 */
	public boolean deleteAMedicalRecord(String firstName, String lastName) {

		boolean check = false;

		if (medicalRecordDaoImpl.deleteAMedicalRecordFromTheList(firstName, lastName)) {
			check = true;
		}

		logger.debug("Check is : " + check);
		return check;

	}

}
