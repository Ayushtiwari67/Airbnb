package com.airbnb.service;

import com.airbnb.entity.Property;
import com.airbnb.entity.Room;
import com.airbnb.payload.RoomDto;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService{

    private RoomRepository roomRepository;
    private PropertyRepository propertyRepository;

    public RoomServiceImpl(RoomRepository roomRepository, PropertyRepository propertyRepository) {
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public RoomDto create(RoomDto roomDto,long id) {
        Optional<Property> byId = propertyRepository.findById(id);
        Property propertyId = byId.get();
        roomDto.setProperty(propertyId);
        Room entity = mapToEntity(roomDto);
        Room save = roomRepository.save(entity);
        RoomDto dto = mapToDto(save);
        return dto;
    }

    Room mapToEntity(RoomDto dto){
        Room entity = new Room();
        entity.setCount(dto.getCount());
        entity.setType(dto.getType());
        entity.setPrice(dto.getPrice());
        entity.setProperty(dto.getProperty());
        return entity;
    }

    RoomDto mapToDto(Room entity){
        RoomDto dto = new RoomDto();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setType(entity.getType());
        dto.setCount(entity.getCount());
        dto.setProperty(entity.getProperty());
        return dto;
    }
}
