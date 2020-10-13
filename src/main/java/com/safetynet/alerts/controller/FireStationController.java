package com.safetynet.alerts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static final Logger logger = LogManager.getLogger(FireStationController.class);

	/**
	 * This method call the fireStationService to get all the fire stations.
	 * 
	 * @return a list that contain all the fire stations.
	 */
	@GetMapping(value = "/firestations")
	public List<FireStation> get() {
		
		logger.info("The user requested the url : /firestations with the GET method");
		
		logger.info("Httpstatus : " + HttpStatus.OK + ", Message : Response received with success");

		return fireStationService.getAllFireStations();
	}

	/**
	 * This method call the fireStationService to add a fire station.
	 * 
	 * @param fireStation is an object of type FireStation that contain the data of a fire station.
	 * @param bindingResult general interface that represents binding results.
	 * @return a ResponseEntity if the request was successful.
	 */
	@PostMapping(value = "/firestation")
	public ResponseEntity<String> post(@Valid @RequestBody FireStation fireStation, BindingResult bindingResult) {
		
		logger.info("The user requested the url : /firestation with the POST method");

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "validation failed", details);
		}

		if (fireStationService.addAFireStation(fireStation)) {
			logger.info("Httpstatus : " + HttpStatus.CREATED + ", Message : The firestation was added to the list");
			return ResponseEntity.status(HttpStatus.CREATED).body("The firestation was added to the list");
		} else {
			throw new ResourceException(HttpStatus.BAD_REQUEST, "A firestation with the same address already exist");
		}

	}

	/**
	 * This method call the fireStationService to update a fire station.
	 * 
	 * @param fireStation is an object of type FireStation that contain the data of a fire station.
	 * @param bindingResult general interface that represents binding results.
	 * @return a ResponseEntity if the request was successful.
	 */
	@PutMapping(value = "/firestation")
	public ResponseEntity<String> put(@Valid @RequestBody FireStation fireStation, BindingResult bindingResult) {
		
		logger.info("The user requested the url : /firestation with the PUT method");

		if (bindingResult.hasErrors()) {
			List<String> details = new ArrayList<>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				details.add(fieldError.getDefaultMessage());
			}
			throw new PropertiesException(HttpStatus.BAD_REQUEST, "validation failed", details);
		}

		if (fireStationService.updateAFireStation(fireStation)) {
			logger.info("Httpstatus : " + HttpStatus.OK + ", Message : The firestation was updated in the list");
			return ResponseEntity.status(HttpStatus.OK).body("The firestation was updated in the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "This firestation does not exist in the list");
		}

	}

	/**
	 * This method call the fireStationService to delete a fire station.
	 * 
	 * @param station represent the station number of a fire station.
	 * @param address represent the address of a fire station.
	 * @return a ResponseEntity if the request was successful.
	 */
	@DeleteMapping(value = "/firestation")
	public ResponseEntity<String> delete(@RequestParam(name = "station") int station,
			@RequestParam(name = "address") String address) {
		
		logger.info("The user requested the url : /firestation with the DELETE method");

		if (fireStationService.deleteAFireStation(station, address)) {
			logger.info("Httpstatus : " + HttpStatus.OK + ", Message : The firestation was deleted from the list");
			return ResponseEntity.status(HttpStatus.OK).body("The firestation was deleted from the list");
		} else {
			throw new ResourceException(HttpStatus.NOT_FOUND, "There are no firestation with the station : " + station
					+ " and the address : " + (address.isEmpty() ? "\"null value\"" : address + " in the list"));
		}

	}

}
