package com.youcode.misresenas.domains.reaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    public List<Reaction> getAllReactions() {
        return reactionRepository.findAll();
    }

    public Optional<Reaction> getReactionById(ReactionId id) {
        return reactionRepository.findById(id);
    }

    public Reaction createReaction(Reaction review) {
        return reactionRepository.save(review);
    }

    public Reaction updateReaction(ReactionId id, Reaction reaction) {
        if (reactionRepository.existsById(id)) {
            reaction.setReactionId(id);
            return reactionRepository.save(reaction);
        } else {
            return null; // or throw an exception indicating review not found
        }
    }

    public void deleteReaction(ReactionId id) {
        reactionRepository.deleteById(id);
    }
}
