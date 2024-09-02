package com.airbnb.payload;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import lombok.Getter;
import lombok.Setter;

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
    private Float total_Price;
}
