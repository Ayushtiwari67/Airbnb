package com.airbnb.payload;

import com.airbnb.entity.Property;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RoomDto {
    private Long id;
    private String type;
    private Float price;
    private Integer count;
    private Property property;
    private LocalDate date;
    private Long version;

}
