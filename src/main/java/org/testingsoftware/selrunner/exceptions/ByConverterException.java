package org.testingsoftware.selrunner.exceptions;

public class ByConverterException extends RuntimeException {

	public ByConverterException(String byString) {
		super("Cannot interpret element locator: " + byString);
	}

	private static final long serialVersionUID = 1L;

}
