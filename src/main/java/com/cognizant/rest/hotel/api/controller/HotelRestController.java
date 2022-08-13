
package com.cognizant.rest.hotel.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.rest.hotel.api.constatnts.AppConstants;
import com.cognizant.rest.hotel.api.dto.Hotel;
import com.cognizant.rest.hotel.api.dto.Rooms;
import com.cognizant.rest.hotel.api.exception.HotelNotFoundException;
import com.cognizant.rest.hotel.api.properties.AppProperties;
import com.cognizant.rest.hotel.api.service.IHotelService;

/**
 * @author Sabyasachi Rajkumar
 *
 */

@RestController
@RequestMapping("/api/v1")
public class HotelRestController {

	@Autowired
	private IHotelService hotelService;
	
	@Autowired
	private AppProperties appProps;

	static Logger logger = LoggerFactory.getLogger(HotelRestController.class);
	
	/*
	 * This method add the hotelDetails 
	 * 
	 * @Param hotelDto
	 * @RequestBody
	 */

	@PostMapping(value="/saveHotel")
	public ResponseEntity<? extends Object> addHotelDetails(@RequestBody Hotel hotelDto) {

		logger.info("** HotelRestController::addHotelDetails() execution started **");

		boolean status = hotelService.addHotelDetails(hotelDto);

		if (status) {
			// Display success message
			String successMsg = appProps.getProperties().get(AppConstants.ADD_HOTEL_SUCCESS);
			logger.info("*** Hotel details are saved successfully in DB***");

			return new ResponseEntity<Object>(successMsg, HttpStatus.CREATED);
		} 
		else {
			// Display unsuccess message
			String failureMsg = appProps.getProperties().get(AppConstants.ADD_HOTEL_FAILURE);
			logger.debug("*** failed to save Hotel details in DB***");

			return new ResponseEntity<Object>(failureMsg, HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * This method fetches the hotelDetails based on the id 
	 * @Param id 
	 * @GetMapping
	 */
	@GetMapping(value = "/hotel/{id}")
	public ResponseEntity<? extends Object> getHotelById(@PathVariable Integer id) throws HotelNotFoundException {

		logger.info("** getHotelById() execution started {} **");
		// call service method

		Hotel hotelDetails = hotelService.getHotelDetailsById(id);
		logger.info("----Hotel details retrieved by id {} : " + id + "----");

		  return new ResponseEntity<>(hotelDetails, HttpStatus.OK);
	}
	
	/*
	 * This method fetches the room details based on the hotelId and roomId 
	 * @Param hotelId,roomId
	 * @GetMapping
	 */	
	@GetMapping(value = "/hotel/{hotelId}/room/{roomId}")
	public ResponseEntity<? extends Object> getRoomByHotelAndRoomId(@PathVariable Integer hotelId,
			                                                        @PathVariable Integer roomId) {

		logger.info("** getRoomByHotelAndRoomId() execution started {} **");
		Rooms roomsDetailsDto = hotelService.getRoomsDetailsByHotelAndRoomId(hotelId, roomId);

		logger.info("** getRoomByHotelAndRoomId() execution ended {} **");
		return new ResponseEntity<>(roomsDetailsDto, HttpStatus.OK);
	}	

	/*
	 * This method update the hotel details in the db
	 * @Param hotelDto
	 * @PutMapping
	 */	
	@PutMapping(value="/hotel/room/update")
	public ResponseEntity<? extends Object> updateHotelDetailsById(@RequestBody Hotel hotelDto){
		
		logger.info("** updateHotelDetailsById() execution started {} **");		
		ResponseEntity<?>resEntity=null;
		
		//Check hotelId exist 
		boolean updateHotelDetails = hotelService.updateHotelDetails(hotelDto);
		
		 if(updateHotelDetails) {
			 //display success message
			 String successMessage = appProps.getProperties().get(AppConstants.UPDATE_HOTEL_SUCCESS);			 
			 resEntity=new ResponseEntity<String>(successMessage, HttpStatus.OK);
			 logger.info("** Hotel details are updated successfully **");
		 }else {
			//display failure message
			 String failureMessage = appProps.getProperties().get(AppConstants.UPDATE_HOTEL_SUCCESS);
			 
			 resEntity=new ResponseEntity<String>(failureMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			 logger.error("Error occured while updating the hotel details");
		 }
		 logger.info("** updateHotelDetailsById() execution ended {} **");		
		 return resEntity;
	}
	
	/*
	 * This method fetches all the rooms details from the db
	 * 
	 * @GetMapping
	 */	
	
	@GetMapping(value="/hotel/listRooms")
	public ResponseEntity<? extends Object> getAllTheRoomsfromHotel() {

		ResponseEntity<?> respEntity = null;

		logger.info("** getAllTheRoomsfromHotel() execution started {} **");
		// call service method
		List<Rooms> listRoomsFromHotel = hotelService.getAllTheRoomsFromHotel();

		//if (null != listRoomsFromHotel && !listRoomsFromHotel.isEmpty()) {

			respEntity = new ResponseEntity<List<Rooms>>(listRoomsFromHotel, HttpStatus.OK);
			logger.info(" **All rooms details fetched successfully **");
	  // } 
		/*else {
			respEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			logger.error(" **Unable to fetched rooms details {}**");
		}*/
		return respEntity;
	}

	/*
	 * This method delete the hotel from the db based on id
	 * @param hotelId
	 * @DeleteMapping
	 */	
	@DeleteMapping(value = "hotel/{hotelId}")
	public ResponseEntity<? extends Object> deleteHotelById(@PathVariable Integer hotelId)
			throws HotelNotFoundException {

		logger.info("** deleteHotelById() execution started {} **");
		// call service layer
		boolean isHotelDeleted = hotelService.deleteHotelById(hotelId);

		if (isHotelDeleted) {
			// display success message
			String successMessage = appProps.getProperties().get(AppConstants.DELETE_HOTEL_SUCCESS);
			logger.info("** Hotel deleted with id successfully:" + hotelId);
			return new ResponseEntity<Object>(successMessage, HttpStatus.OK);
		} else {

			// display failure message
			String failureMessage = appProps.getProperties().get(AppConstants.DELETE_HOTEL_FAILURE);
			logger.error("** Hotel with id not found for delete **");
			return new ResponseEntity<>(failureMessage, HttpStatus.BAD_REQUEST);
		}
}
	
	
	
	
	
}
