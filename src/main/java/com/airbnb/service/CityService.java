package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.payload.CityDto;
import org.springframework.stereotype.Service;

@Service
public interface CityService {
    public CityDto add(CityDto cityDto);
    public void delete(long id);

}
