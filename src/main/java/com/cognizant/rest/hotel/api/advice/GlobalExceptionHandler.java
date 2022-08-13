/**
 * 
 */
package com.cognizant.rest.hotel.api.advice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.rest.hotel.api.error.ApiError;
import com.cognizant.rest.hotel.api.exception.HotelNotFoundException;
import com.cognizant.rest.hotel.api.exception.RoomsNotFoundException;

/**
 * @author sabyasachi Rajkumar
 * 
 * This class will handle exception globally
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<Object> handleHotelNotFoundException(HotelNotFoundException ex){
		
		List<String> details=new ArrayList<>();
		details.add(ex.getMessage());
		
		ApiError error=new ApiError();
		
		error.setTimestamp(LocalDateTime.now());
		error.setMessage("Hotel Not Found");
		error.setStatusCode(HttpStatus.NOT_FOUND);
		error.setErrors(details);
		
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RoomsNotFoundException.class)
	public ResponseEntity<Object> handleRoomsNotFoundException(RoomsNotFoundException ex){
		
		List<String> details=new ArrayList<>();
		details.add(ex.getMessage());
		
		ApiError error=new ApiError();
		
		error.setTimestamp(LocalDateTime.now());
		error.setMessage("Room Not Found");
		error.setStatusCode(HttpStatus.NOT_FOUND);
		error.setErrors(details);
		
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
}
