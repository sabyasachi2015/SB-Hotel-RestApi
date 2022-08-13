package com.cognizant.rest.hotel.api.exception;

/**
 * @author Sabyasachi
 */

public class HotelNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public HotelNotFoundException() {
		super();
	}

	public HotelNotFoundException(String message) {
		super(message);
	}
}
