package com.airbnb.controller;
import com.airbnb.payload.CountryDto;
import com.airbnb.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto countryDto){
        CountryDto add = countryService.add(countryDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCountry(@RequestParam long id){
        countryService.delete(id);
        return new ResponseEntity<>("Delete SuccessFully", HttpStatus.OK);
    }
}
