package com.airbnb.service;

import com.airbnb.entity.Property;
import com.airbnb.payload.PropertyDto;
import com.airbnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService{
    private PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyDto add(PropertyDto propertyDto) {
        Property entity = mapToEntity(propertyDto);
        Property save = propertyRepository.save(entity);
        PropertyDto dto = mapToDto(save);
        return dto;
    }

    Property mapToEntity(PropertyDto dto){
        Property entity = new Property();
        entity.setName(dto.getName());
        entity.setCity(dto.getCity());
        entity.setCountry(dto.getCountry());
        entity.setNumberOfBedrooms(dto.getNumberOfBedrooms());
        entity.setNumberOfBeds(dto.getNumberOfBeds());
        entity.setNumberOfGuests(dto.getNumberOfGuests());
        entity.setNumberOfBathrooms(dto.getNumberOfBathrooms());
        return entity;
    }

    PropertyDto mapToDto(Property entity){
        PropertyDto dto = new PropertyDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCity(entity.getCity());
        dto.setCountry(entity.getCountry());
        dto.setNumberOfBeds(entity.getNumberOfBeds());
        dto.setNumberOfBathrooms(entity.getNumberOfBathrooms());
        dto.setNumberOfBedrooms(entity.getNumberOfBedrooms());
        dto.setNumberOfGuests(entity.getNumberOfGuests());
        return dto;
    }
    @Override
    public Property find(Long id) {
        Optional<Property> byId = propertyRepository.findById(id);
        return byId.get();
    }

    @Override
    public void delete(Long id) {
        propertyRepository.deleteById(id);
    }
}
