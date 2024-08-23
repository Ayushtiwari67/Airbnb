package com.airbnb.service;

import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;
import org.springframework.stereotype.Service;

@Service
public interface PropertyService {
    public PropertyDto add(PropertyDto propertyDto);
    public Property find(Long id);
    public void delete(Long id);
}
