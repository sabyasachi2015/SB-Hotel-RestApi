package com.cognizant.rest.hotel.api.service;

import java.util.List;

import com.cognizant.rest.hotel.api.dto.Hotel;
import com.cognizant.rest.hotel.api.dto.Rooms;
import com.cognizant.rest.hotel.api.exception.HotelNotFoundException;

/**
 * @author Sabyasachi
 * 
 * This interface contains all abstract methods and will implement in his implementation class
 */
public interface IHotelService {
	
	public boolean addHotelDetails(Hotel hotelDto);
	
	public Hotel getHotelDetailsById(Integer id) throws HotelNotFoundException;
	
	public Rooms  getRoomsDetailsByHotelAndRoomId(Integer hotelId,Integer roomId);
	
	public boolean updateHotelDetails(Hotel hotelDto);
	
	//public List<Rooms> getAllTheRoomsFromHotel(Integer hotelId);

	public List<Rooms> getAllTheRoomsFromHotel();
	
	public boolean deleteHotelById(Integer hotelId) throws HotelNotFoundException;
	
	  
}
