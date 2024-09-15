package com.airbnb.service;
import com.airbnb.entity.AppUser;
import com.airbnb.payload.BookingDto;
import org.springframework.stereotype.Service;



@Service
public interface BookingService {
//    BookingDto createBooking(long propertyId, AppUser user, BookingDto bookingDto, String roomType);
//public BookingDto createBooking(long propertyId, String roomType);
    public BookingDto createBooking(long propertyId,  String roomType,BookingDto bookingDto, AppUser user);
}
