package com.airbnb.service;
import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.exception.ReviewExists;
import com.airbnb.payload.ReviewDto;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public ReviewDto add(ReviewDto reviewDto, long propertyId,AppUser user) {
        Property property = propertyRepository.findById(propertyId).get();
        Review reviewDetails = reviewRepository.findByUserAndProperty(user, property);
        if (reviewDetails != null){
           throw new ReviewExists("Review Already Exists");
        }
        reviewDto.setAppUser(user);
        reviewDto.setProperty(property);
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

    public List<Review> allList(AppUser dto){
        List<Review> reviews = reviewRepository.findReviewsByUser(dto);
        return reviews;
    }
}
