package com.airbnb.payload;
import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class BookingDto {
    private Long id;
    private int noOfGuests;
    private String guestsName;
    private String mobile;
    private String email;
    private String typeOfRoom;
    private Property property;
    private AppUser appUser;
    private Float totalPrice;
    private int totalNights;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}
