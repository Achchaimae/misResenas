package com.youcode.misresenas.domains.review;

import com.youcode.misresenas.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findAllByUser(User user);
    List<Review> findAllByRepportIs(boolean repported);
}
