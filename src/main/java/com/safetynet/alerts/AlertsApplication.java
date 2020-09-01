package com.safetynet.alerts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.safetynet.alerts.data.Data;

@SpringBootApplication
public class AlertsApplication {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		Data data = new Data();
		data.init();
		data.load();
		SpringApplication.run(AlertsApplication.class, args);
	}

}
