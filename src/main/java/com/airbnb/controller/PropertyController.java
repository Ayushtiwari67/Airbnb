package com.airbnb.controller;
import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.payload.PropertyDto;
import com.airbnb.repository.CityRepository;
import com.airbnb.repository.CountryRepository;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private PropertyService propertyService;
    private CityRepository cityRepository;
    private CountryRepository countryRepository;

    public PropertyController( PropertyService propertyService, CityRepository cityRepository, CountryRepository countryRepository) {
        this.propertyService = propertyService;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @PostMapping
    public ResponseEntity<PropertyDto> addProperty(
            @RequestBody PropertyDto propertyDto,
            @RequestParam long cityId,
            @RequestParam long countryId
    ){
        City city = cityRepository.findById(cityId).get();
        propertyDto.setCity(city);
        Country country = countryRepository.findById(countryId).get();
        propertyDto.setCountry(country);

        PropertyDto add = propertyService.add(propertyDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }
}
