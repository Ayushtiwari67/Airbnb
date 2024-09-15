package com.airbnb.controller;
import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;
import com.airbnb.payload.BookingDto;
import com.airbnb.payload.RoomDto;
import com.airbnb.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private BookingDto bookingDto;
    private AppUser user;
    private RoomDto roomDto;
    private BookingService bookingService;

    public BookingController( BookingService bookingService) {

        this.bookingService = bookingService;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<BookingDto> createBooking(
            @RequestParam long propertyId,
            @RequestParam String roomType,
            @RequestBody BookingDto bookingDto,
            @AuthenticationPrincipal AppUser appUser
    ){
        BookingDto createdBooking = bookingService.createBooking(propertyId, roomType,bookingDto,appUser);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }
    }

