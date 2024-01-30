package com.youcode.misresenas.domains.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepositoy extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
