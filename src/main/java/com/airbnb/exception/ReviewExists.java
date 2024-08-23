package com.airbnb.exception;

public class ReviewExists extends  RuntimeException {
   public ReviewExists(String message){
        super(message);
    }

}
