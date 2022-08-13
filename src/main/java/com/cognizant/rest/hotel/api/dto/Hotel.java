/**
 * 
 */
package com.cognizant.rest.hotel.api.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author Sabyasachi Rajkumar
 *
 */
@Data
public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer hotelId;
	
	private String hotelName;
	
	private Integer pincode;
	
	private String description;
	
	private String address;
	
	private boolean isActive;
	
	private double ratings;
	
	private List<Rooms> rooms;

}
