package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;

@RestController
public class FireStationController {

	@GetMapping(value = "/firestations")
	public List<FireStation> get() {

		FireStationService fireStationService = new FireStationService();

		return fireStationService.getAllFireStations();
	}

	@PostMapping(value = "/firestation")
	public void post(@RequestBody FireStation fireStation) {

		FireStationService fireStationService = new FireStationService();

		fireStationService.addAFireStation(fireStation);

	}

	@PutMapping(value = "/firestation")
	public void put(@RequestBody FireStation fireStation) {

		FireStationService fireStationService = new FireStationService();

		fireStationService.updateAFireStation(fireStation);

	}

	@DeleteMapping(value = "/firestation")
	public void delete(@RequestParam(name = "station") String station, @RequestParam(name = "address") String address) {

		FireStationService fireStationService = new FireStationService();

		fireStationService.deleteAFireStation(station, address);

	}

}
