package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.payload.CityDto;
import com.airbnb.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{
    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityDto add(CityDto cityDto) {
        City entity = mapToEntity(cityDto);
        City save = cityRepository.save(entity);
        CityDto dto = mapToDto(save);
        return dto;
    }
    City mapToEntity(CityDto dto){
        City entity = new City();
        entity.setName(dto.getName());
        return entity;
    }

    CityDto mapToDto(City entity){
        CityDto dto = new CityDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public void delete(long id) {
        cityRepository.deleteById(id);
    }

    //search searchProperty

}
