package com.airbnb.service;
import com.airbnb.component.PDFService;
import com.airbnb.component.SmsService;
import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;
import com.airbnb.entity.Property;
import com.airbnb.entity.Room;
import com.airbnb.exception.RoomException;
import com.airbnb.payload.BookingDto;
import com.airbnb.repository.BookingRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{
    private final SmsService smsService;
    private BookingRepository bookingRepository;
    private PropertyRepository propertyRepository;
    private BucketService bucketService;
    private PDFService pdfService;
    private RoomRepository roomRepository;
    private SmsService service;



//
    public BookingServiceImpl(BookingRepository bookingRepository, PropertyRepository propertyRepository, BucketService bucketService, PDFService pdfService, RoomRepository roomRepository, SmsService service, SmsService smsService) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.bucketService = bucketService;
        this.pdfService = pdfService;
        this.roomRepository = roomRepository;
        this.service = service;
        this.smsService = smsService;
    }


    @Override
    public BookingDto createBooking(long propertyId,  String roomType,BookingDto bookingDto, AppUser user) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new RoomException("this id not valid")
        );

        List<LocalDate> dateBetween = getDateBetween(bookingDto.getCheckInDate(), bookingDto.getCheckOutDate());
        if (dateBetween.isEmpty()) {
            throw new IllegalArgumentException("Check-in date must be before check-out date.");
        }
        for (LocalDate date: dateBetween){
            Room rooms = roomRepository.findByPropertyIdAndType(propertyId,roomType);

            if (rooms.getCount() == 0) {
                throw new RoomException("Room are not Available");
            }
              else {
                float nightlyPrice = rooms.getPrice();
                float totalPrice = nightlyPrice * bookingDto.getTotalPrice();
                bookingDto.setTotalPrice(totalPrice);
                bookingDto.setProperty(property);
                bookingDto.setAppUser(user);
                bookingDto.setTypeOfRoom(roomType);
                Booking bookingEntity = mapToEntity(bookingDto);
                Booking save = bookingRepository.save(bookingEntity);
                BookingDto savedBooking = mapToDto(save);
                if (savedBooking!=null){
                    int val = rooms.getCount();
                    rooms.setCount(val-1);
                    roomRepository.save(rooms);
                }

                //Generate PDF Document
                pdfService.generatePdf(savedBooking);

                //Send SMS conformation
                smsService.sendSms("+91"+bookingDto.getMobile(),"Your booking is confirmed. Your booking id is: "+ bookingDto.getId());


                throw new RoomException("Room booked");
            }
        }
        return bookingDto;
    }


    //dto to entity
    Booking mapToEntity(BookingDto dto){
        Booking entity = new Booking();
        entity.setNoOfGuests(dto.getNoOfGuests());
        entity.setGuestsName(dto.getGuestsName());
        entity.setMobile(dto.getMobile());
        entity.setEmail(dto.getEmail());
        entity.setTypeOfRoom(dto.getTypeOfRoom());
        entity.setProperty(dto.getProperty());
        entity.setAppUser(dto.getAppUser());
        entity.setTotalPrice(dto.getTotalPrice());
        entity.setTotalNights(dto.getTotalNights());
        entity.setCheckInDate(dto.getCheckInDate());
        entity.setCheckOutDate(dto.getCheckOutDate());
        return entity;

    }
    //entity to Dto
    BookingDto mapToDto(Booking entity){
        BookingDto dto = new BookingDto();
        dto.setId(entity.getId());
        dto.setNoOfGuests(entity.getNoOfGuests());
        dto.setGuestsName(entity.getGuestsName());
        dto.setMobile(entity.getMobile());
        dto.setEmail(entity.getEmail());
        dto.setTypeOfRoom(entity.getTypeOfRoom());
        dto.setProperty(entity.getProperty());
        dto.setAppUser(entity.getAppUser());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setTotalNights(entity.getTotalNights());
        dto.setCheckInDate(entity.getCheckInDate());
        dto.setCheckOutDate(entity.getCheckOutDate());
        return dto;

    }

    public static List<LocalDate> getDateBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        return dates;
    }

}