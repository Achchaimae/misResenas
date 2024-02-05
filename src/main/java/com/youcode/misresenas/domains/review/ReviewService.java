package com.youcode.misresenas.domains.review;


import com.youcode.misresenas.domains.user.User;
import com.youcode.misresenas.domains.user.UserRepositoy;
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
    @Autowired
    private UserRepositoy userRepositoy;

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

    public Review repportReview(UUID id) {
        Optional<Review> review = reviewRepository.findById(id);
         return review.map(review1 -> {
            review1.setRepport(true);
            return reviewRepository.save(review1);
        }).orElse(null);
    }

    public List<Review> findAllByUser(UUID id) {
        Optional<User> user = userRepositoy.findById(id);
        return user.map(
                user1 -> {
                    return reviewRepository.findAllByUser(user1);
                }
        ).orElse(null);
    }

    public List<Review> findAllReviewsRepported() {
        return reviewRepository.findAllByRepportIs(true);
    }


    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }
}