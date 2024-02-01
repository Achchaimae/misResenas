package com.youcode.misresenas.domains.reaction;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Reaction {
    @EmbeddedId
    @GeneratedValue
    private ReactionId reactionId;
    private String comment;
    private LikeReaction likeReaction;
}
