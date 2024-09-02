package com.airbnb.service;

import com.airbnb.entity.Review;
import com.airbnb.payload.ReviewDto;
import com.airbnb.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewDto add(ReviewDto reviewDto) {
        Review entity = mapToEntity(reviewDto);
        Review save = reviewRepository.save(entity);
        ReviewDto dto = mapToDto(save);
        return dto;
    }

    Review mapToEntity(ReviewDto dto){
        Review entity = new Review();
        entity.setRating(dto.getRating());
        entity.setDescription(dto.getDescription());
        entity.setProperty(dto.getProperty());
        entity.setAppUser(dto.getAppUser());
        return entity;
    }

    ReviewDto mapToDto(Review entity){
        ReviewDto dto = new ReviewDto();
        dto.setId(entity.getId());
        dto.setRating(entity.getRating());
        dto.setDescription(entity.getDescription());
        dto.setAppUser(entity.getAppUser());
        dto.setProperty(entity.getProperty());
        return dto;
    }
}
