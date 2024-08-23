package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Review;
import com.airbnb.payload.ReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    public ReviewDto add(ReviewDto reviewDto, long propertyId,AppUser user);
    public List<Review> allList(AppUser dto);
}
