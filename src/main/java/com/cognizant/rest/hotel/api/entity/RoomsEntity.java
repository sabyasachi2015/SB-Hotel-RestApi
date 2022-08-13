/**
 * 
 */
package com.cognizant.rest.hotel.api.entity;

/**
 * @author Sabyasachi Rajkumar
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table( name = "ROOMS_TBL" )
@Data
public class RoomsEntity {
	@Id
	private Integer roomId;
	
	@Column( length = 20 )
	private String displayName;
	
	private Integer quantity;
	
	private Double price;

}