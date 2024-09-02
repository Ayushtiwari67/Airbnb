package com.airbnb.repository;

import com.airbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
//  @Query("SELECT r from r where r.property.id = :propertyId and r.type = :roomType")
//@Query("SELECT r FROM Room r WHERE r.property.id = :propertId AND r.type = :roomType")
//  Room findByPropertyIdAndType(
//          @Param("propertyId") Long propertyId,
//          @Param("roomType") String roomType
//  );

  @Query("SELECT r FROM Room r WHERE r.property.id = :propertyId AND r.type = :roomType")
  Room findByPropertyIdAndType(@Param("propertyId") Long propertyId, @Param("roomType") String roomType);

}