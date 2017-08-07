package ru.mornimf.revolut_test.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class HandlerException extends Exception implements
ExceptionMapper<TransferException> {

	public HandlerException() {
		super();
	}

	public HandlerException(String message, Throwable cause) {
		super(message, cause);
	}

	public HandlerException(String message) {
		super(message);
	}

	public HandlerException(Throwable cause) {
		super(cause);
	}

	public Response toResponse(TransferException arg0) {
		return null;
	}

}
