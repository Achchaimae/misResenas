package com.youcode.misresenas.domains.reaction;

import com.youcode.misresenas.domains.review.Review;
import com.youcode.misresenas.domains.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class ReactionController {
    private final ReactionService reactionService;

    @PostMapping("/add/{user_id}/{review_Id}")
    public String createReaction(@RequestBody Reaction reaction ,@PathVariable UUID user_id , @PathVariable UUID review_Id) {
        ReactionId reactionId = new ReactionId();
        User user =new User();
        Review review =new Review();
        user.setId(user_id);
        review.setId(review_Id);
        reactionId.setUser(user);
        reactionId.setReview(review);
        reactionService.createReaction(reaction);
        return "redirect:/review/"+reaction.getReactionId().getReview().getId();
    }

    @PutMapping("update/{user_id}/{review_Id}")
    public String updateReaction(@RequestBody Reaction reaction,@PathVariable UUID user_id , @PathVariable UUID review_Id) {
        ReactionId reactionId = new ReactionId();
        User user =new User();
        Review review =new Review();
        user.setId(user_id);
        review.setId(review_Id);
        reactionId.setUser(user);
        reactionId.setReview(review);
        reactionService.updateReaction(reaction.getReactionId() , reaction);
        return "redirect:/review/"+review_Id;
    }

    @DeleteMapping("delete/{user_id}/{review_Id}")
    public String deleteReaction(@PathVariable UUID user_id , @PathVariable UUID review_Id) {
        ReactionId reactionId = new ReactionId();
        User user =new User();
        Review review =new Review();
        user.setId(user_id);
        review.setId(review_Id);
        reactionId.setUser(user);
        reactionId.setReview(review);
        reactionService.deleteReaction(reactionId);
        return "redirect:/reviews";
    }
}
