package com.safetynet.alerts.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.safetynet.alerts.controller.MedicalRecordController;
import com.safetynet.alerts.dao.MedicalRecordDao;
import com.safetynet.alerts.data.Data;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

@WebMvcTest(MedicalRecordController.class)
@ContextConfiguration(classes = MedicalRecordService.class)
class MedicalRecordControllerTest {

	@MockBean
	private MedicalRecordDao medicalRecordDaoImpl;

	@Autowired
	private MedicalRecordService medicalRecordService;

	@Test
	void getAllMedicalRecords() {

		// Given
		List<MedicalRecord> list = Data.instance().getMedicalRecords();

		// When
		Mockito.when(medicalRecordDaoImpl.retrieveAllMedicalRecordsFromTheList()).thenReturn(list);

		// Then
		List<MedicalRecord> expected = medicalRecordService.getAllMedicalRecords();
		assertThat(expected).isEqualTo(list);
	}

	@Test
	void addAMedicalRecord() {

		// Given
		MedicalRecord medicalRecord = new MedicalRecord();
		List<String> medications = Arrays.asList("medication1", "medication2");
		List<String> allergies = Arrays.asList("allergy1", "allergy2");
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/02/1980");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		// When
		Mockito.when(medicalRecordDaoImpl.addAMedicalRecordToTheList(medicalRecord)).thenReturn(true);

		// Then
		boolean expected = medicalRecordService.addAMedicalRecord(medicalRecord);
		assertThat(expected).isTrue();

	}

	@Test
	void updateAMedicalRecord() {

		// Given
		MedicalRecord medicalRecord = new MedicalRecord();
		List<String> medications = Arrays.asList("medication1");
		List<String> allergies = Arrays.asList("allergy1");
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/07/1980");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		// When
		Mockito.when(medicalRecordDaoImpl.updateAMedicalRecordInTheList(medicalRecord)).thenReturn(true);

		// Then
		boolean expected = medicalRecordService.updateAMedicalRecord(medicalRecord);
		assertThat(expected).isTrue();
	}

	@Test
	void deleteAMedicalRecord() {

		// When
		Mockito.when(medicalRecordDaoImpl.deleteAMedicalRecordFromTheList(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

		// Then
		boolean expected = medicalRecordService.deleteAMedicalRecord("firstName", "lastName");
		assertThat(expected).isTrue();
	}

}
