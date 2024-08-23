package com.airbnb.controller;

import com.airbnb.entity.City;
import com.airbnb.payload.CityDto;
import com.airbnb.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto cityDto){
        CityDto add = cityService.add(cityDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCity(@RequestParam Long id){
        cityService.delete(id);
        return new ResponseEntity<>("Delete SuccessFully", HttpStatus.OK);
    }
}
