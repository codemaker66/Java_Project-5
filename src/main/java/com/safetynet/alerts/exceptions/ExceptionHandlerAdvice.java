package com.safetynet.alerts.exceptions;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.safetynet.alerts.model.Errors;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status).body("This request method is not allowed");
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		return ResponseEntity.status(status)
				.body("The parameter : " + request.getParameterNames().next() + " is null or not valid");
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status).body(ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status).body("This url is not valid");
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Malformed or empty json data");
	}

	@ExceptionHandler(ResourceException.class)
	public ResponseEntity<String> handleResourceException(ResourceException e) {
		return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
	}

	@ExceptionHandler(PropertiesException.class)
	public ResponseEntity<Object> handlePropertiesException(PropertiesException e) {
		Errors errors = new Errors();
		errors.setMessage(e.getMessage());
		errors.setDetails(e.getDetails());
		return ResponseEntity.status(e.getHttpStatus()).body(errors);
	}
}