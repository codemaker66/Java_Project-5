package com.safetynet.alerts.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.safetynet.alerts.dao.FireStationDao;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;

@WebMvcTest(FireStationService.class)
class FireStationServiceTest {

	@MockBean
	private FireStationDao FireStationDaoImpl;

	@Autowired
	private FireStationService fireStationService;

	@Test
	void getAllFireStations() {

		// Given
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address number 01");
		fireStation.setStation(5);

		FireStation fireStation2 = new FireStation();
		fireStation2.setAddress("address number 02");
		fireStation2.setStation(6);

		List<FireStation> list = new ArrayList<>();
		list.add(fireStation);
		list.add(fireStation2);

		// When
		Mockito.when(FireStationDaoImpl.retrieveAllFireStationsFromTheList()).thenReturn(list);

		// Then
		List<FireStation> expected = fireStationService.getAllFireStations();
		assertThat(expected).isEqualTo(list);
	}

	@Test
	void addAFireStation() {

		// Given
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address number 01");
		fireStation.setStation(6);

		// When
		Mockito.when(FireStationDaoImpl.addAFireStationToTheList(fireStation)).thenReturn(true);

		// Then
		boolean expected = fireStationService.addAFireStation(fireStation);
		assertThat(expected).isTrue();
	}

	@Test
	void updateAFireStation() {

		// Given
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address number 01");
		fireStation.setStation(9);

		// When
		Mockito.when(FireStationDaoImpl.updateAFireStationInTheList(fireStation)).thenReturn(true);

		// Then
		boolean expected = fireStationService.updateAFireStation(fireStation);
		assertThat(expected).isTrue();
	}

	@Test
	void deleteAFireStation() {

		// When
		Mockito.when(FireStationDaoImpl.deleteAFireStationFromTheList(Mockito.anyInt(), Mockito.anyString())).thenReturn(true);

		// Then
		boolean expected = fireStationService.deleteAFireStation(22, "address");
		assertThat(expected).isTrue();

	}

}
