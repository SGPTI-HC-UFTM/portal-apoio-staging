package net.ebserh.hctm.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class CustomRuntimeException extends RuntimeException {
	
	public CustomRuntimeException(String message) {
		super(message);
	}

}
