package com.safetynet.alerts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.exceptions.PropertiesException;
import com.safetynet.alerts.exceptions.ResourceException;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;

@RestController
public class FireStationController {

	private FireStationService fireStationService = new FireStationService();

	@GetMapping(value = "/firestations")
	public List<FireStation> get() {

		return fireStationService.getAllFireStations();
	}

	@PostMapping(value = "/firestation")
	public ResponseEntity<String> post(@Valid @RequestBody FireStation fireStation, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		if (fireStationService.addAFireStation(fireStation)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("The firestation was added to the list");
		} else {
			throw new ResourceException(HttpStatus.BAD_REQUEST, "A firestation with the same address already exist");
		}

	}

	@PutMapping(value = "/firestation")
	public ResponseEntity<String> put(@Valid @RequestBody FireStation fireStation, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		if (fireStationService.updateAFireStation(fireStation)) {
			return ResponseEntity.status(HttpStatus.OK).body("The firestation was updated in the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "This firestation does not exist in the list");
		}

	}

	@DeleteMapping(value = "/firestation")
	public ResponseEntity<String> delete(@RequestParam(name = "station") int station,
			@RequestParam(name = "address") String address) {

		if (fireStationService.deleteAFireStation(station, address)) {
			return ResponseEntity.status(HttpStatus.OK).body("The firestation was deleted from the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "There are no firestation with the station : " + station
					+ " and the address : " + (address.isEmpty() ? "\"null value\"" : address + " in the list"));
		}

	}

}
