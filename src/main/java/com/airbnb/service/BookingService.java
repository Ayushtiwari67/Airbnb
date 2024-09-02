package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;
import com.airbnb.payload.PropertyDto;
import com.airbnb.payload.RoomDto;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    public RoomDto roomBook(RoomDto roomDto,long propertyId, String roomType, AppUser user, Booking booking );
}
