package com.airbnb.controller;
import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;
import com.airbnb.payload.RoomDto;
import com.airbnb.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController( BookingService bookingService) {

        this.bookingService = bookingService;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(
            @RequestBody RoomDto roomDto,
            @RequestParam long propertyId,
            @RequestParam  String roomType,
            @RequestBody Booking booking,
            @RequestBody AppUser appUser
            ){

        RoomDto room = bookingService.roomBook(roomDto,propertyId, roomType, appUser,booking);

        if (room.getCount() == 0 ){
            return new ResponseEntity<>("Room Are not Available", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Room Booked", HttpStatus.CREATED);
        }
    }

