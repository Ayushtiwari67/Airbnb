package com.airbnb.controller;
import com.airbnb.payload.RoomDto;
import com.airbnb.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(
            @RequestBody RoomDto roomDto,
            @RequestParam Long id
    ){
        RoomDto dto = roomService.create(roomDto, id);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
