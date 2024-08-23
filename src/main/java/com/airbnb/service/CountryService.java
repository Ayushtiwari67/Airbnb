package com.airbnb.service;

import com.airbnb.payload.CountryDto;
import org.springframework.stereotype.Service;

@Service
public interface CountryService {
    public CountryDto add(CountryDto countryDto);
    public void delete(Long id);
}
