package com.youcode.misresenas.domains.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoy extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
