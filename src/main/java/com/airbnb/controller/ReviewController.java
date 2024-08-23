package com.airbnb.controller;
import com.airbnb.entity.AppUser;
import com.airbnb.entity.Review;
import com.airbnb.payload.ReviewDto;
import com.airbnb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;

    }

    @RequestMapping("/createReview")
    public ResponseEntity<?> createReview(
            @RequestBody ReviewDto review,
            @AuthenticationPrincipal AppUser user,
            @RequestParam long propertyId
            ){
        ReviewDto saveReview = reviewService.add(review,propertyId,user);
        return new ResponseEntity<>(saveReview, HttpStatus.CREATED);
    }

    @GetMapping("/userReviews")
    public ResponseEntity<List<Review>>listReviewsOfUser(
            @AuthenticationPrincipal AppUser user
    ){
        List<Review> reviews = reviewService.allList(user);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }
}
