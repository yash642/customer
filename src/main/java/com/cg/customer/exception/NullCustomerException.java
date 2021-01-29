package com.cg.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NullCustomerException extends Exception {
	public NullCustomerException(String arg0) {
		super(arg0);
	}
}
