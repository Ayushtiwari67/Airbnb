package com.airbnb.controller;

import com.airbnb.entity.Property;
import com.airbnb.repository.PropertyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jpa")
public class JpaController {

    private PropertyRepository propertyRepository;

    public JpaController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping("/propertyResult")
    public List<Property> searchProperty(
        @RequestParam("city") String cityName
    ){
       return propertyRepository.searchProperty(cityName);
    }
}
