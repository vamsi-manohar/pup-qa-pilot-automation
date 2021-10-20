package com.productsup.platform.exceptions;

@SuppressWarnings("serial")
public class PropertyFileException extends FrameworkExceptions {

	public PropertyFileException(String message) {
		super(message);
	}

	public PropertyFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
