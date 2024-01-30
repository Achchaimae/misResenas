package com.youcode.misresenas.domains.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_name")
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String password;
    @Enumerated
    private Role role;

}
