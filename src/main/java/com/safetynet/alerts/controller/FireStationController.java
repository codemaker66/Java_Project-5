package com.safetynet.alerts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.exceptions.ResourceException;
import com.safetynet.alerts.exceptions.ResourceException2;
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
	public void post(@Valid @RequestBody FireStation fireStation, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (Object object : bindingResult.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;
					details.add(fieldError.getDefaultMessage());
				}
			}
			throw new ResourceException2(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		FireStationService fireStationService = new FireStationService();

		if (fireStationService.addAFireStation(fireStation)) {
			throw new ResourceException(HttpStatus.CREATED, "The firestation was added to the list");
		} else {
			throw new ResourceException(HttpStatus.BAD_REQUEST, "A firestation with the same address already exist");
		}

	}

	@PutMapping(value = "/firestation")
	public void put(@Valid @RequestBody FireStation fireStation, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (Object object : bindingResult.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;
					details.add(fieldError.getDefaultMessage());
				}
			}
			throw new ResourceException2(HttpStatus.BAD_REQUEST, "Validation failed", details);
		}

		FireStationService fireStationService = new FireStationService();

		if (fireStationService.updateAFireStation(fireStation)) {
			throw new ResourceException(HttpStatus.OK, "The firestation was updated in the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "This firestation does not exist in the list");
		}

	}

	@DeleteMapping(value = "/firestation")
	public void delete(@RequestParam(name = "station") int station, @RequestParam(name = "address") String address) {

		if (address.isEmpty()) {
			throw new ResourceException(HttpStatus.BAD_REQUEST, "You didn't provide an address");
		}

		FireStationService fireStationService = new FireStationService();

		if (fireStationService.deleteAFireStation(station, address)) {
			throw new ResourceException(HttpStatus.OK, "The firestation was deleted from the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "There are no firestation with the station : " + station
					+ " and the address : " + address + " in the list");
		}

	}

}
