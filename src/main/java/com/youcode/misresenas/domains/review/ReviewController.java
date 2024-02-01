package com.youcode.misresenas.domains.review;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public String getAllReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews";
    }

    @GetMapping("/{id}")
    public String getReviewById(@PathVariable UUID id,Model model) {
        model.addAttribute("reviews", reviewService.getReviewById(id).orElse(null));
        return "review";
    }

    @GetMapping("/add")
    public String createReview() {
        return "add";
    }

    @GetMapping("/update/{id}")
    public String updateReview(@PathVariable UUID id,Model model) {
        model.addAttribute("review",reviewService.getReviewById(id));
        return "update";
    }

    @PostMapping("/add")
    public String createReview(@RequestBody Review review ) {
        reviewService.createReview(review);
        return "reviews";
    }

    @PutMapping("update/{id}")
    public String updateReview(@PathVariable UUID id, @RequestBody Review updatedReview,Model model) {
        reviewService.updateReview(id, updatedReview);
        return "reviews";
    }

    @DeleteMapping("delete/{id}")
    public String deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
        return "reviews";
    }
}