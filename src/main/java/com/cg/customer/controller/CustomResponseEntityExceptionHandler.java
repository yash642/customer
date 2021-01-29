package com.cg.customer.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.customer.exception.CustomerNotFoundException;
import com.cg.customer.exception.EmptyListException;
import com.cg.customer.exception.ExceptionResponse;
import com.cg.customer.exception.NullCustomerException;


@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomResponseEntityExceptionHandler.class);
	@ExceptionHandler({ CustomerNotFoundException.class })
	public final ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest req) {
		logger.error(ex.toString());
		ExceptionResponse expResp = new ExceptionResponse(new Date(), ex.getMessage(), "Customer Not found");
		return new ResponseEntity(expResp, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ EmptyListException.class })
	public final ResponseEntity<Object> handleEmptyListException(EmptyListException ex, WebRequest req) {
		logger.error(ex.toString());
		ExceptionResponse expResp = new ExceptionResponse(new Date(), ex.getMessage(), "List is empty");
		return new ResponseEntity(expResp, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler({ NullCustomerException.class })
	public final ResponseEntity<Object> handleNullCustomerExceptionn(NullCustomerException ex, WebRequest req) {
		logger.error(ex.toString());
		ExceptionResponse expResp = new ExceptionResponse(new Date(), ex.getMessage(), "Not valid value");
		return new ResponseEntity(expResp, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {
		logger.error(ex.toString());
		ExceptionResponse expResp = new ExceptionResponse(new Date(), ex.getMessage(), "Server down");
		return new ResponseEntity(expResp, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
