package com.airbnb.controller;
import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.payload.ReviewDto;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import com.airbnb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private ReviewService reviewService;
    private PropertyRepository propertyRepository;
    private ReviewRepository reviewRepository;

    public ReviewController(ReviewService reviewService, PropertyRepository propertyRepository, ReviewRepository reviewRepository) {
        this.reviewService = reviewService;
        this.propertyRepository = propertyRepository;
        this.reviewRepository = reviewRepository;
    }


    @RequestMapping("/createReview")
    public ResponseEntity<?> createReview(
            @RequestBody ReviewDto review,
            @AuthenticationPrincipal AppUser user,
            @RequestParam long propertyId
            ){
        Property property = propertyRepository.findById(propertyId).get();
        Review reviewDetails = reviewRepository.findByUserAndProperty(user, property);
        if (reviewDetails != null){
         return new ResponseEntity<>("Review Exists", HttpStatus.CREATED);
        }
        review.setAppUser(user);
        review.setProperty(property);
        ReviewDto saveReview = reviewService.add(review);
        return new ResponseEntity<>(saveReview, HttpStatus.CREATED);
    }
}
