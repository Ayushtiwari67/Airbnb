package com.airbnb.exception;
import com.airbnb.payload.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(RoomException.class)
    public ResponseEntity<ErrorDetail> Exception(
            RoomException roomException,
            WebRequest webRequest
    ){
        ErrorDetail e = new ErrorDetail();
        e.setUri(webRequest.getDescription(true));
        e.setMessage(roomException.getMessage());
        e.setDate(new Date());
        return  new ResponseEntity<>(e, HttpStatus.OK);
    }

}