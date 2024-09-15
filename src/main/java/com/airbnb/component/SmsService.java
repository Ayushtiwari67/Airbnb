package com.airbnb.component;

import com.airbnb.payload.BookingDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class SmsService {
    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    public void sendSms(String toPhoneNumber, String messageContent) {
       try {
           logger.info("Starting to send SMS - "+ new Date());
           Message message = Message.creator(
                   new PhoneNumber(toPhoneNumber),
                   new PhoneNumber(fromPhoneNumber),
                   messageContent

           ).create();
           logger.info("sent SMS - "+new Date());
       }catch (Exception e){
          logger.error(e.getMessage());
       }
    }
}
