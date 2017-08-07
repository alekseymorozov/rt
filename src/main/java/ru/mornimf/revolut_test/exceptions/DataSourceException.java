package ru.mornimf.revolut_test.exceptions;

public class DataSourceException extends Exception {

	public DataSourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataSourceException(String message) {
		super(message);
	}
	
}
