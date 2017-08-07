package ru.mornimf.revolut_test.exceptions;

import javax.ws.rs.ext.Provider;

@Provider
public class TransferException extends Exception {
	public TransferException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransferException(String message) {
		super(message);
	}
}
