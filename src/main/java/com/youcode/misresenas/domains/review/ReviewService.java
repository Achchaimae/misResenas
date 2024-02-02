package com.youcode.misresenas.domains.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(UUID id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(Review review) {
        review.setDate(LocalDate.now());
        review.setReaction(null);
        return reviewRepository.save(review);
    }

    public Review updateReview(UUID id, Review updatedReview) {
        if (reviewRepository.existsById(id)) {
            updatedReview.setId(id);
            return reviewRepository.save(updatedReview);
        } else {
            return null;
        }
    }

    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }
}