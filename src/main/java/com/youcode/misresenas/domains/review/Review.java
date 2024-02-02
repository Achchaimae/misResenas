package com.youcode.misresenas.domains.review;


import com.youcode.misresenas.domains.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate date;
    private String title;
    private String message;
    private boolean repport;
    private String reaction;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}