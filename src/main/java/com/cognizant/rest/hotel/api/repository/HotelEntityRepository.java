package com.cognizant.rest.hotel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.rest.hotel.api.entity.HotelEntity;

@Repository
public interface HotelEntityRepository extends JpaRepository<HotelEntity, Integer> {

	@Query(value = "update HotelEntity he set he.hotelName=:hotelName,he.pincode=:pincode,he.description=:description, "
			+ "he.address=:address,he.isActive=:isActive, he.ratings=:ratings where he.hotelId=:hotelId")
	@Transactional
	@Modifying
	int updateHotelDetailsById(@Param("hotelName") String hotelName, @Param("pincode") Integer pincode,
			@Param("description") String description, @Param("address") String address,
			@Param("isActive") boolean isActive, @Param("ratings") double ratings, @Param("hotelId") Integer hotelId);

}
