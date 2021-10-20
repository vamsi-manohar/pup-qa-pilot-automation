package com.productsup.platform.exceptions;

@SuppressWarnings("serial")
public class BrowserInvocationFailedException extends FrameworkExceptions {

	public BrowserInvocationFailedException(String message) {
		super(message);
	}
	
	public BrowserInvocationFailedException(String message, Throwable cause) {
		super(message,cause);
	}
	


}
