package com.airbnb.service;
import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;
import com.airbnb.entity.Property;
import com.airbnb.entity.Room;
import com.airbnb.exception.RoomException;
import com.airbnb.exception.RoomNotAvailable;
import com.airbnb.payload.RoomDto;
import com.airbnb.repository.BookingRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{
    private RoomRepository roomRepository;
    private PropertyRepository propertyRepository;
    private BookingRepository bookingRepository;


    public BookingServiceImpl(RoomRepository roomRepository, PropertyRepository propertyRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public RoomDto roomBook(RoomDto roomDto,long propertyId, String roomType, AppUser user,Booking booking ) {
        Room room = roomRepository.findByPropertyIdAndType(propertyId, roomType);
        Property property = propertyRepository.findById(propertyId).get();


        if (room.getCount() == 0) {
            throw new RoomNotAvailable("Room are not Available");
        } else {
            float nightlyPrice= room.getPrice();
            float totalPrice = nightlyPrice * booking.getTotal_Price();
            booking.setTotal_Price(totalPrice);
            booking.setProperty(property);
            booking.setAppUser(user);
            booking.setTypeOfRoom(roomType);

            Booking savedBooking = bookingRepository.save(booking);
            if (savedBooking!=null){
                int val = room.getCount();
                room.setCount(val - 1);
                Room entity = mapToEntity(roomDto);
                Room save = roomRepository.save(entity);
                RoomDto dto = mapToDto(save);
                return dto;

            }
            else {
                throw new RoomException("Room not booked");
            }
        }

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
