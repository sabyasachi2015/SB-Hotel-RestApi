
package com.cognizant.rest.hotel.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cognizant.rest.hotel.api.dto.Hotel;
import com.cognizant.rest.hotel.api.dto.Rooms;
import com.cognizant.rest.hotel.api.entity.HotelEntity;
import com.cognizant.rest.hotel.api.entity.RoomsEntity;
import com.cognizant.rest.hotel.api.exception.HotelNotFoundException;
import com.cognizant.rest.hotel.api.exception.RoomsNotFoundException;
import com.cognizant.rest.hotel.api.repository.HotelEntityRepository;
import com.cognizant.rest.hotel.api.repository.RoomsEntityRepository;
import com.cognizant.rest.hotel.api.service.IHotelService;

/**
 * @author Sabyasachi Rajkumar
 *
 * This class contains all the b.logic
 */
@Service
public class HotelServiceImpl implements IHotelService {

	@Autowired
	HotelEntityRepository hotelRepository;

	private static Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

	@Autowired
	RoomsEntityRepository roomsRepository;

	/*
	 * This method add the hotelDetails in the DB
	 * 
	 * @Param hotelDto
	 * 
	 */

	@Override
	public boolean addHotelDetails(Hotel hotelDto) {

		logger.info("** addHotelDetails() execution started**");

		HotelEntity hotelEntity = new HotelEntity();
		BeanUtils.copyProperties(hotelDto, hotelEntity);

		List<Rooms> lstRoomsFromHotel = hotelDto.getRooms();

		List<RoomsEntity> lstHotelEntity = new ArrayList<>();

		lstRoomsFromHotel.forEach(r -> {
			
			RoomsEntity rEntity=new RoomsEntity();
			
			//convert dto to entity
			BeanUtils.copyProperties(r, rEntity);
			lstHotelEntity.add(rEntity);
		});
		hotelEntity.setRooms(lstHotelEntity);
		
		 if(!hotelRepository.existsById(hotelEntity.getHotelId())) {
			//save hotel details in db
			 hotelRepository.save(hotelEntity);
			 return true;
		 }		
		   return false;
	}

	/*
	 * This method fetches the hotelDetails from the DB based on id
	 * @Param id
	 * 
	 */	
	@Override
	public Hotel getHotelDetailsById(Integer id) throws HotelNotFoundException {

		logger.info("** getHotelDetailsById() execution started**");
		
		Optional<HotelEntity> optionalHotel = hotelRepository.findById(id);
		
		Hotel hotelDto=new Hotel();
		
		  if(optionalHotel.isPresent()) {
			 HotelEntity hotelEntity = optionalHotel.get();
			//get hotel details from entity
			 List<RoomsEntity> listRoomsEntity = hotelEntity.getRooms();
			 
			 List<Rooms>listRoomsDto=new ArrayList<>();	
			 
			 listRoomsEntity.forEach(rooms->{
				 
				 Rooms roomsDto=new Rooms();
				 //convert entity to dto
				 BeanUtils.copyProperties(rooms, roomsDto);
				 listRoomsDto.add(roomsDto);			
			 });
			 hotelDto.setHotelId(hotelEntity.getHotelId());
			 hotelDto.setHotelName(hotelEntity.getHotelName());
			 hotelDto.setPincode(hotelEntity.getPincode());
			 hotelDto.setDescription(hotelEntity.getDescription());
			 hotelDto.setAddress(hotelEntity.getAddress());
			 hotelDto.setActive(hotelEntity.isActive());
			 hotelDto.setRatings(hotelEntity.getRatings());
			 hotelDto.setRooms(listRoomsDto); 
		  }else {
			  throw new HotelNotFoundException("Hotel with id: " + id +"  Not Exist!!!!");
		  }
		return hotelDto;			  
   }

	@Override
	public Rooms getRoomsDetailsByHotelAndRoomId(Integer hotelId, Integer roomId) {
		
     //Call repository method
		Optional<RoomsEntity> optionalRooms = roomsRepository.findRoomByHotelAndRoomId(hotelId, roomId);
		Rooms roomsDto=new Rooms();
		
		if(optionalRooms.isPresent()) {			
		     RoomsEntity roomsEntity = optionalRooms.get();
			//convert entity to dto
		     BeanUtils.copyProperties(roomsEntity, roomsDto);
		}else {
			throw new RoomsNotFoundException(String.format("Room with id : {} in Hotel with id : {} doesn't exist",roomId, hotelId));
		}		
		  return roomsDto;
	}
	
	@Override
	public boolean updateHotelDetails(Hotel hotelDto) {
		
		//Call repository method
		if(hotelRepository.existsById(hotelDto.getHotelId())) {
			
			//hotelRepository.updateHotelDetailsById(hotelDto.getHotelName(),
					//hotelDto.getPincode(),hotelDto.getDescription(), hotelDto.getAddress(),hotelDto.getRatings(),hotelDto.isActive(),hotelDto.getHotelId());
			
			hotelRepository.updateHotelDetailsById(hotelDto.getHotelName(), hotelDto.getPincode(),hotelDto.getDescription(),  hotelDto.getAddress(),
					                               hotelDto.isActive(), hotelDto.getRatings(),hotelDto.getHotelId());
			return true;
			
		}else {
		
		      return false;
		}
	}

	@Override
	public List<Rooms> getAllTheRoomsFromHotel() {

		// call repository layer
		List<RoomsEntity> listRoomsEntity = roomsRepository.findAll();

		List<Rooms> listRoomsDto = new ArrayList<>();

		// convert entity to dto
		BeanUtils.copyProperties(listRoomsEntity, listRoomsDto);

		return listRoomsDto;
	}

	@Override
	public boolean deleteHotelById(Integer hotelId) throws HotelNotFoundException {
		//call repository layer
		
		boolean isHotelPresent = hotelRepository.existsById(hotelId);
			if(isHotelPresent) {
				 
				 hotelRepository.deleteById(hotelId);
			 }else {
				 throw new HotelNotFoundException(String.format("Hotel with id : {} doesn't exist",hotelId));
			 }
		// return false;
		return isHotelPresent;
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
