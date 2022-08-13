package com.cognizant.rest.hotel.api.exception;

public class RoomsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RoomsNotFoundException() {

		super();
	}

	public RoomsNotFoundException(String message) {

		super(message);
	}

}
