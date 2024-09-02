package com.airbnb.payload;
import com.airbnb.entity.Property;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDto {
    private Long id;
    private String url;
    private Property property;
}
