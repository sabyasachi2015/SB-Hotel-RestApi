package com.cognizant.rest.hotel.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.rest.hotel.api.dto.Rooms;
import com.cognizant.rest.hotel.api.entity.RoomsEntity;

@Repository
public interface RoomsEntityRepository extends JpaRepository<RoomsEntity, Integer> {

	@Query(value = "select * from ROOMS_TBL where hotel_id = ? and room_id = ? ", nativeQuery = true)
	public Optional<RoomsEntity> findRoomByHotelAndRoomId(Integer hotelId, Integer roomId);
	
	
    
}
