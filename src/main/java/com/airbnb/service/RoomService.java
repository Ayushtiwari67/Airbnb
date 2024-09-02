package com.airbnb.service;

import com.airbnb.payload.RoomDto;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {
    RoomDto create(RoomDto dto,long id);
}
