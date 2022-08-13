/**
 * 
 */
package com.cognizant.rest.hotel.api.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Sabyasachi Rajkumar
 *
 */
@Data
public class Rooms implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Integer roomId;
	private String displayName;
	private Integer quantity;
	private Double price;

}
