package com.safetynet.alerts.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResourceException2 extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	private List<String> details = null;

	public List<String> getDetails() {
		return details;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ResourceException2(HttpStatus notFound, String string, List<String> details) {
		super(string);
		this.httpStatus = notFound;
		this.details = details;
	}

}
