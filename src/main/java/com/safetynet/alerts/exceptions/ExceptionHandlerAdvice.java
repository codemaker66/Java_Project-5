package com.safetynet.alerts.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static final Logger logger = LogManager.getLogger(ExceptionHandlerAdvice.class);

	/**
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException, HttpHeaders, HttpStatus, WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("Httpstatus : " + status + ", Message : This request method is not allowed");
		return ResponseEntity.status(status).body("This request method is not allowed");
	}

	/**
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	 */
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.error("Httpstatus : " + status + ", Message : The parameter : " + request.getParameterNames().next() + " is null or not valid");
		return ResponseEntity.status(status)
				.body("The parameter : " + request.getParameterNames().next() + " is null or not valid");
	}

	/**
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("Httpstatus : " + status + ", Message : " + ex.getMessage());
		return ResponseEntity.status(status).body(ex.getMessage());
	}

	/**
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.error("Httpstatus : " + status + ", Message : This url is not valid");
		return ResponseEntity.status(status).body("This url is not valid");
	}

	/**
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("Httpstatus : " + status + ", Message : Malformed or empty json data");
		return ResponseEntity.status(status).body("Malformed or empty json data");
	}

	/**
	 * This method is called whenever there are a resource exception.
	 * 
	 * @param e is an object of type ResourceException.
	 * @return a ResponseEntity containing the HttpStatus and a message.
	 */
	@ExceptionHandler(ResourceException.class)
	public ResponseEntity<String> handleResourceException(ResourceException e) {
		logger.error("Httpstatus : " + e.getHttpStatus() + ", Message : " + e.getMessage());
		return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
	}

	/**
	 * This method is called whenever there are properties exception.
	 * 
	 * @param e is an object of type PropertiesException.
	 * @return a ResponseEntity containing the HttpStatus with a message and details.
	 */
	@ExceptionHandler(PropertiesException.class)
	public ResponseEntity<Object> handlePropertiesException(PropertiesException e) {
		Errors errors = new Errors();
		errors.setMessage(e.getMessage());
		errors.setDetails(e.getDetails());
		logger.error("Httpstatus : " + e.getHttpStatus() + ", Message : " + errors.getMessage() + ", Details : " + errors.getDetails());
		return ResponseEntity.status(e.getHttpStatus()).body(errors);
	}
}