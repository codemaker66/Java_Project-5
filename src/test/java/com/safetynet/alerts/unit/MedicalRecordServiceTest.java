package com.safetynet.alerts.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.safetynet.alerts.dao.MedicalRecordDao;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

@WebMvcTest(MedicalRecordService.class)
class MedicalRecordServiceTest {

	@MockBean
	private MedicalRecordDao medicalRecordDaoImpl;

	@Autowired
	private MedicalRecordService medicalRecordService;

	@Test
	void getAllMedicalRecords() {

		// Given
		MedicalRecord medicalRecord = new MedicalRecord();
		List<String> medications = Arrays.asList("medication1", "medication2");
		List<String> allergies = Arrays.asList("allergy1", "allergy2");
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("01/02/1980");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		MedicalRecord medicalRecord2 = new MedicalRecord();
		medicalRecord2.setFirstName("Joe");
		medicalRecord2.setLastName("Boy");
		medicalRecord2.setBirthdate("04/10/1990");
		medicalRecord2.setMedications(medications);
		medicalRecord2.setAllergies(allergies);

		List<MedicalRecord> list = new ArrayList<>();
		list.add(medicalRecord);
		list.add(medicalRecord2);

		// When
		Mockito.when(medicalRecordDaoImpl.retrieveAllMedicalRecordsFromTheList()).thenReturn(list);

		// Then
		List<MedicalRecord> expected = medicalRecordService.getAllMedicalRecords();
		assertThat(list).isEqualTo(expected);
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
		Mockito.when(medicalRecordDaoImpl.addAMedicalRecordToTheList(Mockito.any(MedicalRecord.class))).thenReturn(true);

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
		Mockito.when(medicalRecordDaoImpl.updateAMedicalRecordInTheList(Mockito.any(MedicalRecord.class))).thenReturn(true);

		// Then
		boolean expected = medicalRecordService.updateAMedicalRecord(medicalRecord);
		assertThat(expected).isTrue();
	}

	@Test
	void deleteAMedicalRecord() {

		// When
		Mockito.when(medicalRecordDaoImpl.deleteAMedicalRecordFromTheList(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

		// Then
		boolean expected = medicalRecordService.deleteAMedicalRecord("first name", "last name");
		assertThat(expected).isTrue();
	}

}
