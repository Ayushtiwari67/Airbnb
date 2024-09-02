package com.airbnb.service;

import com.airbnb.payload.ReviewDto;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    public ReviewDto add(ReviewDto reviewDto);
}
