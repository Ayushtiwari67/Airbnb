package com.airbnb.controller;
import com.airbnb.payload.PropertyDto;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController( PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<PropertyDto> addProperty(
            @RequestBody PropertyDto propertyDto,
            @RequestParam long cityId,
            @RequestParam long countryId
    ){
        PropertyDto add = propertyService.add(propertyDto,cityId,countryId);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }
}
