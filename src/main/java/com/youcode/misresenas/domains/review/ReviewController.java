package com.youcode.misresenas.domains.review;

import com.youcode.misresenas.domains.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/profile/{id}")
    public String getReviews(@PathVariable UUID id,Model model) {
        model.addAttribute("reviews", reviewService.findAllByUser(id));
        return "profile";
    }


    @GetMapping("/{id}")
    public String getReviewById(@PathVariable UUID id,Model model) {
        model.addAttribute("review", reviewService.getReviewById(id).orElse(null));
        return "review";
    }

    @GetMapping("/add")
    public String createReview(Model model) {
        model.addAttribute("review", new Review());
        return "add";
    }

    @GetMapping("/update/{id}")
    public String updateReview(@PathVariable UUID id,Model model) {
        model.addAttribute("review",reviewService.getReviewById(id));
        return "update";
    }

    @PostMapping("/add")
    public String createReview(@ModelAttribute("review") Review review ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        review.setUser(user);
        reviewService.createReview(review);
        return "redirect:/reviews";
    }

    @PutMapping("update/{id}")
    public String updateReview(@PathVariable UUID id, @RequestBody Review updatedReview,Model model) {
        reviewService.updateReview(id, updatedReview);
        return "reviews";
    }
    @GetMapping("repport/{id}")
    public String repportReview(@PathVariable UUID id) {
        reviewService.repportReview(id);
        return "redirect:/reviews";
    }

    @GetMapping("admin")
    public String admin(Model model) {
        model.addAttribute("reviews",reviewService.findAllReviewsRepported());
        return "/admin";
    }

    @GetMapping("delete/{id}")
    public String deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews/profile";
    }
    @GetMapping("deleteAdmin/{id}")
    public String deleteReviewAdmin(@PathVariable UUID id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews/admin";
    }
}