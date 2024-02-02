package com.youcode.misresenas.domains.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @GetMapping
    public String createUser() {
        User user = new User();
        user.setRole(Role.Moderator);
        user.setUsername("mohcine");
        user.setPassword(passwordEncoder.encode("1234"));
        userService.createUser(user);
        return "login";
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

}
