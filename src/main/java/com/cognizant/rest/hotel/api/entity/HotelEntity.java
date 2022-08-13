/**
 * 
 */
package com.cognizant.rest.hotel.api.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sabyasachi
 *
 */
@Entity
@Table(name = "HOTEL_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelEntity {
	@Id
	private Integer hotelId;

	@Column(length = 30)
	private String hotelName;

	private Integer pincode;

	@Column(length = 50)
	private String description;
	
	@Column(length = 30)
	private String address;

	private boolean isActive;
	
	private double ratings;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "HOTEL_ID")
	private List<RoomsEntity> rooms;
}
